package cn.edu.cqupt.nmid.wxhelper.wxhelper.aspect;

import cn.edu.cqupt.nmid.wxhelper.wxhelper.config.JwtConfig;
import cn.edu.cqupt.nmid.wxhelper.wxhelper.utils.Role;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;

/**
 * @author MaYunHao
 * @version 1.0
 * @description  拦截器  判断请求头中的token是否有效,并为后面传递userid
 * @date 2020/2/2 16:47
 */

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JwtConfig jwtConfig;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws SignatureException {
        /** 地址过滤 */
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.contains("/login") || uri.contains("/error")) {
            return true;
        }
        /** Token 验证 */
        String token = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtConfig.getHeader());
        }
        if (StringUtils.isEmpty(token)) {
            throw new SignatureException(jwtConfig.getHeader() + "不能为空");
        }

        Claims claims = null;
        try {
            claims = jwtConfig.getTokenClaim(token);
            if (claims == null || jwtConfig.isTokenExpired(claims.getExpiration())) {
                throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
            }
        } catch (Exception e) {
            throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
        }

        //管理端登陆
        if (uri.contains("/admin")){
            String role= (String) claims.get(JwtConfig.ROLE);
            if(role != Role.ADMIN){
                return false;
            }
        }

        /** 设置 userid 用户身份ID */
        request.setAttribute("userid", claims.getSubject());
        return true;
    }

}
