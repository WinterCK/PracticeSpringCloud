package com.forezp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@EnableEurekaClient//注解只适用于Eureka为注册中心
@RestController
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class EurekaClientApp {

	public static void main(String[] args) {
		System.out.println("Client 启动");
		//跑多个client，可以使用 -Dserver.port=8888 等跑多个client
        SpringApplication.run(EurekaClientApp.class, args );
    }
	
	@Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    public String hiError(String name) {
    	//添加断路器
        return "hi,"+name+",sorry,error : goto Hystrix断路器!";
    }
    //http://localhost:8762/actuator/hystrix.stream为
//    @Bean//防止404
//    public ServletRegistrationBean getServlet(){
//       HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//       ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//       registrationBean.setLoadOnStartup(1);
//       registrationBean.addUrlMappings("/actuator/hystrix.stream");
//       registrationBean.setName("HystrixMetricsStreamServlet");
//       return registrationBean;
//    }
}
