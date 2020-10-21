//package com.forezp.filter;
//
//import com.atomscat.entitybase.request.CommonRequest;
//import com.atomscat.filter.utils.RequestLogUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.ArrayUtils;
//import org.slf4j.MDC;
//import org.springframework.core.Ordered;
//import org.springframework.http.codec.ServerCodecConfigurer;
//import org.springframework.http.codec.json.Jackson2JsonDecoder;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//import java.util.function.BiConsumer;
//
//@Slf4j
//public class WebFluxFilter implements WebFilter, Ordered, WebFluxConfigurer {
//    @Override
//    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
//        long startTime = System.currentTimeMillis();
//        String path = serverWebExchange.getRequest().getURI().getPath();
//        String method = serverWebExchange.getRequest().getMethod().toString();
//        CommonRequest commonRequest = new CommonRequest();
//        serverWebExchange.getRequest().getHeaders().forEach(new BiConsumer<String, List<String>>() {
//            @Override
//            public void accept(String s, List<String> strings) {
//                if(s.contains(RequestLogUtils.TRACE_ID)) {
//                    commonRequest.setTraceId(ArrayUtils.toString(strings));
//                }
//                if (s.contains(RequestLogUtils.OPER_LEVEL)) {
//                    commonRequest.setOperLevel(ArrayUtils.toString(strings));
//                }
//                if(s.contains(RequestLogUtils.TOKEN)){
//                    MDC.put(RequestLogUtils.TOKEN,ArrayUtils.toString(strings));
//                }
//            }
//        });
//        return webFilterChain.filter(serverWebExchange).doOnRequest(consumer->{
//            RequestLogUtils.initLog(commonRequest);
//            log.info("Serving '{}' , method: '{}'", path, method);
//        }).doFinally((onFinally) -> {
//            log.info("Served '{}' as {} in {} msec", path, serverWebExchange.getResponse().getStatusCode(), System.currentTimeMillis() - startTime);
//            RequestLogUtils.logClear();
//        });
//    }
//
//    @Override
//    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
//        configurer.customCodecs().reader(new HttpMessageReader(new Jackson2JsonDecoder()));
//
//    }
//
//        @Override
//    public int getOrder() {
//        return -10000;
//    }
//}
