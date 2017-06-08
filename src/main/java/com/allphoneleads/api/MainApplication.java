package com.allphoneleads.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author chander
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.allphoneleads.api","com.allphoneleads.quartz"})
@EnableJpaRepositories
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainApplication extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);
	public static void main(String[] args) {

	SpringApplication.run(MainApplication.class, args);
	String startupMsg ="<<<================================  APL SERVER STARTED AND RUNNING ================================ >>>";
	System.out.println(startupMsg);
	
	logger.info(startupMsg);
	
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainApplication.class);
	}

	/*
	 * @Bean MultipartConfigElement multipartConfigElement() {
	 * MultipartConfigFactory factory = new MultipartConfigFactory();
	 * factory.setMaxFileSize("128KB"); factory.setMaxRequestSize("128KB");
	 * return factory.createMultipartConfig(); }
	 */

	/*
	 * @Bean public MultipartConfigElement multipartConfigElement() { return new
	 * MultipartConfigElement(""); }
	 * 
	 * // This works
	 * 
	 * @Bean(name="multipartResolver") public StandardServletMultipartResolver
	 * multipartResolver() { return new MultipartResolverWrapper(); }
	 * 
	 * 
	 * 
	 * 
	 * @Bean public MultipartResolver multipartResolver() {
	 * org.springframework.web.multipart.commons.CommonsMultipartResolver
	 * multipartResolver = new
	 * org.springframework.web.multipart.commons.CommonsMultipartResolver();
	 * multipartResolver.setMaxUploadSize(1000000); return multipartResolver; }
	 */
	/*
	 * 
	 * @Bean public Filter logFilter() { CommonsRequestLoggingFilter filter =
	 * new CommonsRequestLoggingFilter(); filter.setIncludeQueryString(true);
	 * filter.setIncludePayload(true); filter.setMaxPayloadLength(10240); return
	 * filter; }
	 */
	
	/*public void addResourceHandlers(ResourceHandlerRegistry registry) {

	       registry.addResourceHandler("swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");

	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");

	}*/
	
/*	 @Bean
	  public Docket callXAPI() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("CallX Services")
	        .apiInfo(apiInfo())
	        .select()
	         .apis(RequestHandlerSelectors.any())
	         .paths(PathSelectors.ant("/japi/*"))
	         .build();
	    
	     
	  }

	 */
	/* private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("CallX REST Sample with Swagger")
	                .description("CallX REST Sample with Swagger")
	                .contact(new Contact("CallX Team", "http://www.callx.com/", "support@callx.com"))
	                .version("1.0")
	                .build();
	    }*/
}