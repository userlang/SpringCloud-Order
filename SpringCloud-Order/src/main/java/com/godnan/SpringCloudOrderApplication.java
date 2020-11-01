package com.godnan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class SpringCloudOrderApplication {
	// @EnableEurekaClient
	// 将当前服务注册到Eureka上
	public static void main(String[] args) {

		SpringApplication.run(SpringCloudOrderApplication.class, args);
	}

	//把RestTemplate 模板让 SpringBoot容器来管理，单例

	@Bean
	@LoadBalanced //@LoadBalanced 开启负载均衡调用
	public RestTemplate getRestTemplate(){
		return  new RestTemplate();
	}

}
