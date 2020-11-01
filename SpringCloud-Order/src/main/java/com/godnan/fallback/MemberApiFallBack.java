package com.godnan.fallback;

import com.godnan.feign.MemberApi;
import org.springframework.stereotype.Component;

/**
 * 服务降级类
 */
@Component
public class MemberApiFallBack implements MemberApi {

    @Override
    public String getMember() {
        return "服务降级 出问题了";
    }
}
