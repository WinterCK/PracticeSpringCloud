package com.forezp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//feignClient添加熔断器直接加 fallback = 
@FeignClient(value = "service-hi" , fallback = SchedualServiceHiHystric.class)//指定调用哪个服务（eureka-client）
public interface SchedualServiceHi {

	@RequestMapping(value = "/hi",method = RequestMethod.GET)//指定服务使用的urlMapping
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
