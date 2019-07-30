package com.imooc.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTestDemo1 {

  @Test
  public void demo1() {
    // set IP address and port
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    // store data
    jedis.set("name", "imooc");
    // get data
    String val = jedis.get("name");
    System.out.println(val);
    // release resource
    jedis.close();
  }

  @Test
  public void demo2() {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(30);
    config.setMaxIdle(10);
    // get JedisPool
    JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);

    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.set("name", "abv");
      String val = jedis.get("name");
      System.out.println(val);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (jedis != null) {
        jedis.close();
      }
      if (jedisPool != null) {
        jedisPool.close();
      }
    }
  }
}
