package com.allphoneleads.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent>{

	
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		logger.info("------------------------------------ Start loading applicatin pre required data  ----------------------------------");
	
		
		logger.info("------------------------------------ Start loading applicatin pre required data  ----------------------------------");
	}

}
