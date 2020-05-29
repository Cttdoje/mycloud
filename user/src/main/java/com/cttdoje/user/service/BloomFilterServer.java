package com.cttdoje.user.service;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class BloomFilterServer {

    private static final int EXPECTED_INSERTIONS = 100000;

    private static final double FPP = 0.001;

    private static final BloomFilter<String> BLOOM_FILTER = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),EXPECTED_INSERTIONS,FPP);

    private static BloomFilterHelper<String> orderBloomFilterHelper = new BloomFilterHelper<>((Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8)
            .putString(from, Charsets.UTF_8), EXPECTED_INSERTIONS , FPP);

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean isExist(String value){
        return BLOOM_FILTER.mightContain(value);
    }

    public void put(String value){
        BLOOM_FILTER.put(value);
    }

    public static void main(String[] args) {
        BloomFilterServer server = new BloomFilterServer();

        server.put("hello world");
        server.put("guava");
        System.err.println(server.isExist("guava"));


        // 使用redis实现布隆过滤器
        server.addByBloomFilter(orderBloomFilterHelper,"key","value");
    }

    /**
     * 根据给定的布隆过滤器添加值
     */
    public <T> void addByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            redisTemplate.opsForValue().setBit(key, i, true);
        }
    }

    /**
     * 根据给定的布隆过滤器判断值是否存在
     */
    public <T> boolean includeByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            if (!redisTemplate.opsForValue().getBit(key, i)) {
                return false;
            }
        }

        return true;
    }


}
