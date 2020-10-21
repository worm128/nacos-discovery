package com.forezp.web;

import com.forezp.common.GetIpAndPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Created by winlone on 2020/10/21.
 */

@Api(tags = "地区进行操作")
@RestController
public class ProviderController {

    Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/services")
    @ApiOperation(value = "查询", httpMethod = "GET")
    public String getServices() {
        List<String> serviceNames = discoveryClient.getServices();

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : serviceNames) {
            stringBuilder.append(s).append("\n");
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(s);
            if (serviceInstances != null && serviceInstances.size() > 0) {
                for (ServiceInstance serviceInstance : serviceInstances) {
                    logger.info("serviceName:" + s + " host:" + serviceInstance.getHost() + " port:" + serviceInstance.getPort());
                }
            }
        }
        return stringBuilder.toString();
    }

    @GetMapping("/hi")
    public Mono<String> hi(@RequestParam(value = "name", defaultValue = "forezp", required = false) String name, ServerWebExchange serverWebExchange) throws InterruptedException {
        String temp = "hi " + name + "<br/>" + GetIpAndPort.getIp(serverWebExchange);
        //Thread.sleep(3000);
        return Mono.just(temp);
    }
}
