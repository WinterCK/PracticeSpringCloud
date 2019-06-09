package com.forezp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {
	
	@Autowired
    RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "hiError")//添加熔断方法
    public String hiService(String name) {
		System.out.println("进入ribbon方法");
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }
	
//    public String hiService(String name) {
//	        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
//    }
	
	/**
	 * 新增熔断方法
	 * @param name
	 * @return
	 */
	public String hiError(String name) {
		System.out.println("进入hiError方法");
        return "hi,"+name+",sorry,error!";
    }

}
