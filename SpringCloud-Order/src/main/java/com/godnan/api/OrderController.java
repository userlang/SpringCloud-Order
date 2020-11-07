package com.godnan.api;

import com.godnan.feign.MemberApi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api("订单服务")
public class OrderController {

    /**
     * 在Springloud中 有两种方式调用 rest，feigin
     * rest =RestTemplate 是由SpringBoot的web组件提供的 底层是使用HttpClient
     * @return
     */
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping("discoveryClient1")
    public  String discoveryClient1(){
        List<ServiceInstance> list=discoveryClient.getInstances("godnan-member");
        for (ServiceInstance item: list
             ) {

            System.out.println(item.getUri()+"\t"+item.getServiceId()+"\t"+item.getMetadata().keySet().toString()+"\t");
        }

        return  null;
    }
    @RequestMapping("getOrder")
    public String getOrder(){
       //在使用restTemplate调用的时候同样有两种方式调用，第一种采用服务的别名调用，第二种采用直接调用
        //使用别名调用则是注册中心的别名，直接调用不会走注册中心
      //  String result= restTemplate.getForObject("http://127.0.0.1:8001/getMember",String.class);
        String MemberUrl="http://godnan-member/getMember";//注意 如果这里使用了别名调用  那么必须要开启负载均衡机制， @LoadBalanced 注解是开启负载均衡机制
        String result1= restTemplate.getForObject(MemberUrl,String.class);

        return "this is orderServer 订单服务调用了会员服务得到的返回结果是="+result1;
    }

    @Resource
    private MemberApi memberApi;

    @RequestMapping("feignOrder")
    public String feignOrder(){
        return  memberApi.getMember();

    }
    //解决服务的雪崩效应
    // @HystrixCommand(fallbackMethod = "message")
    @RequestMapping("feignOrderHystrix")
    public String feignOrderHystrix(){
        System.out.println("线程池名称"+Thread.currentThread().getName());
        return  memberApi.getMember();
    }
  /* 服务降级执行方法
  public String message(){
        return "服务降级 ,你好当前服务不可用";
    }*/

    @RequestMapping("feignOrder1")
    public String feignOrder1(){
        System.out.println("线程池名称"+Thread.currentThread().getName());
        return  null;
    }
}
