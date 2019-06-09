package com.forezp;

import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

	@Override
	public String sayHiFromClientOne(String name) {
		//fallback方法
		return "May be Client happens Error, Sorry " + name;
	}

}
