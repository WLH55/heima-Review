package com.hmdp.utils;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 增加过期时间的redis数据
 */

@Data
public class RedisData {
    private LocalDateTime expireTime;
    private Object data;
}
