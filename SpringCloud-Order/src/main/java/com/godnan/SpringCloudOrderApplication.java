package com.godnan;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient /*关联注册中心，作为Eureka的客户端*/
@EnableFeignClients /*开启Feign*/
@EnableHystrix /*开启熔断器*/
@EnableSwagger2Doc /*开启swagger*/
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
