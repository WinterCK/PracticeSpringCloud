package com.forezp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient//注解只适用于Eureka为注册中心
@RestController
public class EurekaClientApp {

	public static void main(String[] args) {
		System.out.println("Client 启动");
		//跑多个client，可以使用 -Dserver.port=8888 等跑多个client
        SpringApplication.run(EurekaClientApp.class, args );
    }
	
	@Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

}
