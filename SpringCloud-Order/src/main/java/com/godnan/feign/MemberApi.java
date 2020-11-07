package com.godnan.feign;

import com.godnan.fallback.MemberApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * fallback=容错类
 */
@FeignClient(name = "http://127.0.0.1:8002/",url="http://127.0.0.1:8002/",fallback = MemberApiFallBack.class)
public interface MemberApi {

    @RequestMapping("getMember")
    public String getMember();



}
