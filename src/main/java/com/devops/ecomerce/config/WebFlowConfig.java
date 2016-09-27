package com.devops.ecomerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.*;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;

@Configuration
public class WebFlowConfig {//extends AbstractFlowConfiguration {

	@Bean
	public FlowDefinitionRegistry flowRegistry() {
	    return null;/*getFlowDefinitionRegistryBuilder()
	        .addFlowLocation("/WEB-INF/flows/booking/booking.xml")
	        .build();*/
	}

	@Bean
	public FlowExecutor flowExecutor() {
	    return null;//getFlowExecutorBuilder(flowRegistry()).build();
	}
}
