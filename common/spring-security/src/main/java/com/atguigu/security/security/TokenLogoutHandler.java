package com.atguigu.security.security;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ResponseUtil;
import com.atguigu.commonutils.vo.ResultVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xudongzhou
 * @date 2022-06-28
 * @desc 登出业务逻辑
 */
public class TokenLogoutHandler implements LogoutHandler{

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String token = request.getHeader("token");

        if (token != null) {
            tokenManager.removeToken(token);

            //清空当前用户缓存中的权限数据
            String userName = tokenManager.getUserFromToken(token);
            redisTemplate.delete(userName);
        }

        ResponseUtil.out(response, ResultVo.ok());

    }
}
