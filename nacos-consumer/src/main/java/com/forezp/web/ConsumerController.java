package com.forezp.web;

import com.forezp.client.ProviderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

/**
 * Created by winlone on 2020/10/21.
 */
@Slf4j
@RestController
public class ConsumerController {

    @Autowired
    ProviderClient providerClient;


    @GetMapping("/hi-feign")
    public Mono<String> hiFeign() {
        log.info("打印日志winlone");
        return Mono.just(providerClient.hi("winlone"));
    }
}
