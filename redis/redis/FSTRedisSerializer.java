package com.yogu.p2p.common.redis;

import org.nustaq.serialization.FSTConfiguration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

/**
 * ClassName: FSTRedisSerializer
 * Function: 高效序列化反序列化框架FST的实现
 * @author qinjun
 * @version v1.0.0
 * @date 2017-10-24 10:33:22
 * @Copyright (c) 杭州优谷数据技术有限公司 P2P网贷管理系统 All Rights Reserved
 * @Declare 未经授权不得进行修改、复制、出售及商业使用
 */
@Component("fstRedisSerializer")
public class FSTRedisSerializer<T> implements RedisSerializer<T> {

    private static final FSTConfiguration fstConfiguration = FSTConfiguration.createDefaultConfiguration();

    /**
     * 序列化
     *
     * @param t
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        byte[] target = null;
        try {
            target = fstConfiguration.asByteArray(t);
        } catch (Exception e) {
            throw new SerializationException(" FST serialize error", e);
        }
        return target;
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        T target = null;
        try {
            if (bytes == null || bytes.length < 1) {
                return null;
            }
            target = (T) fstConfiguration.asObject(bytes);
        } catch (Exception e) {
            throw new SerializationException("FST deserialize error", e);
        }

        return target;
    }

}