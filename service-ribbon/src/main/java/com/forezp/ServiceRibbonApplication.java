package com.forezp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//SpringCloud Edgware之后，不需加Client注册也可以被发现
@EnableDiscoveryClient//除了Eureka注册中心之外，其他注册中心也能扫描该服务
@EnableHystrix//新增加，开启Hystrix断路器
public class ServiceRibbonApplication {

	public static void main(String[] args) {
		//负载均衡项目，会交替使用多个client的服务
		SpringApplication.run(ServiceRibbonApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
