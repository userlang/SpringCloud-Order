package com.godnan.feign;

import com.godnan.fallback.MemberApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * fallback=容错类
 */
@FeignClient(name = "godnan-member",fallback = MemberApiFallBack.class)
public interface MemberApi {

    @RequestMapping("getMember")
    public String getMember();



}
