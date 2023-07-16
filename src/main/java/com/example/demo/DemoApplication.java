package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

@SpringBootApplication
@RestController
public class DemoApplication {

    private final Jedis jedis;

    public DemoApplication() {
        // Replace "redis-cluster-endpoint" with the endpoint of your Elasticache Redis cluster
        jedis = new Jedis("INPUT_REDIS_ENDPOINT");
    }

    @GetMapping("/")
    public String getData() {
        String value = jedis.get("myKey");
        return "Retrieved value from Redis: " + value;
    }

    @PostMapping("/")
    public String setData(@RequestBody String data) {
        jedis.set("myKey", data);
        return "Data stored in Redis successfully!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}
