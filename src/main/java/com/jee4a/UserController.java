package com.jee4a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p> 
 * @author tpeng 2018年1月26日
 * @email 398222836@qq.com
 */
@RestController
public class UserController {
	
	private final Logger  logger = LoggerFactory.getLogger(getClass()) ;
	 
	
	@RequestMapping(value="/test")
	public String test(@PathVariable Integer id) {
		return "test" ;
	}
	 
}
