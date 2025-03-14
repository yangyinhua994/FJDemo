package com.example.fjdemo.service;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.exception.*;
import com.aliyun.datahub.client.model.*;
import com.aliyun.datahub.clientlibrary.config.ProducerConfig;
import com.aliyun.datahub.clientlibrary.producer.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: kannabis
 * Date: 2024/1/25 22:25
 */
@Service
@Slf4j
public class DataHubProduceService {

    public static final String accessId = "";
    public static final String accessKey = "";
    public static final String endpoint = "http://datahub.cn-cq-xczwy-d01.dh.res.alicloud.cqxczwy.com";
    public static final String projectName = "";

    public static Map<String, Producer> producerMap = new ConcurrentHashMap<>();
    public static Map<String, GetTopicResult> topicResultMap = new ConcurrentHashMap<>();
    public static DatahubClient datahubClient = DatahubClientBuilder.newBuilder().setDatahubConfig(new DatahubConfig(endpoint, new AliyunAccount(accessId, accessKey))).build();

    //获取生产者实例
    public static Producer initProducer(String topicName) {
        Producer producer = producerMap.get(topicName);
        if (producer != null) {
            return producer;
        }
        synchronized ("producerMap") {
            producer = producerMap.get(topicName);
            if (producer == null) {
                //初始化生产者
                log.info("初始化客户端：{}", topicName);
                ProducerConfig pConfig = new ProducerConfig(endpoint, accessId, accessKey);
                producer = new Producer(projectName, topicName, pConfig);
                producerMap.put(topicName, producer);
            }
            return producer;
        }
    }

    //获取topic的scheme
    public RecordSchema getScheme(String topicName) {
        GetTopicResult result = this.getTopic(topicName);
        return result.getRecordSchema();
    }

    //获取topic数据
    public GetTopicResult getTopic(String topicName) {
        GetTopicResult topicResult = topicResultMap.get(topicName);
        if (topicResult != null) {
            return topicResult;
        }
        synchronized ("topicResultMap") {
            topicResult = topicResultMap.get(topicName);
            if (topicResult == null) {
                topicResult = datahubClient.getTopic(projectName, topicName);
                topicResultMap.put(topicName, topicResult);
            }
            return topicResult;
        }
    }

    //组装数据
    public List<RecordEntry> genRecord(JSONObject jsonObject, RecordSchema schema) {
        //表结构
        List<Field> fields = schema.getFields();
        // 数据
        List<RecordEntry> recordEntries = new ArrayList<>();
        RecordEntry recordEntry = new RecordEntry();

        //由于topic的scheme字段名字只能为全消息，将JSONObjec的key全部转为小写
        JSONObject jsonObjectLower = new JSONObject();
        for (Map.Entry<String, Object> objectEntry : jsonObject.entrySet()) {
            jsonObjectLower.put(objectEntry.getKey().toLowerCase(), objectEntry.getValue());
        }
        TupleRecordData data = new TupleRecordData(schema);
        for (Field field : fields) {
            data.setField(field.getName(), jsonObjectLower.getString(field.getName()));
        }
        recordEntry.setRecordData(data);
        //可添加多个
        recordEntries.add(recordEntry);
        return recordEntries;
    }

    //发送tuple数据
    public void sendTuple(List<RecordEntry> recordEntries, String topicName) {
        if (CollectionUtils.isEmpty(recordEntries)) {
            return;
        }
        Producer producer = initProducer(topicName);
        try {
            // 自动选择shard写入
            producer.send(recordEntries, 100);
            // 指定写入shard "0"
            // producer.send(recordEntries, "0", maxRetry);
            //log.info("send records: {}", recordEntries.size());
        } catch (MalformedRecordException e) {
            // record 格式非法，根据业务场景选择忽略或直接抛异常
            log.error("write fail", e);
            throw e;
        } catch (InvalidParameterException |
                 AuthorizationFailureException |
                 NoPermissionException e) {
            // 请求参数非法
            // 签名不正确
            // 没有权限
            log.error("write fail", e);
            throw e;
        } catch (ShardNotFoundException e) {
            // shard 不存在, 如果不是写入自己指定的shard，可以不用处理
            log.error("write fail", e);
        } catch (ResourceNotFoundException e) {
            // project, topic 或 shard 不存在
            log.error("write fail", e);
            throw e;
        } catch (DatahubClientException e) {
            // 基类异常，包含网络问题等，可以选择重试
            log.error("write fail", e);
            throw e;
        }
    }

    //发送blob格式的数据，数据格式为字符串或者字节组数
    public void sendBlob(List<String> dataList, String topicName) {
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        List<RecordEntry> list = new ArrayList<>();
        for (String str : dataList) {
            if (StringUtils.isNotBlank(str)) {
                try {
                    RecordEntry recordEntry = new RecordEntry();
                    BlobRecordData blobRecordData = new BlobRecordData(StringUtils.getBytes(str, "UTF-8"));
                    recordEntry.setRecordData(blobRecordData);
                    list.add(recordEntry);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        Producer producer = null;
        try {
            producer = initProducer(topicName);
            producer.send(list, 10);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } /*finally {
                if (producer != null) {
                    producer.close();
                }
            }*/
    }
}
