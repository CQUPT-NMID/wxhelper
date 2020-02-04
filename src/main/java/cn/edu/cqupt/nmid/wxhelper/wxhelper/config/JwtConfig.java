package cn.edu.cqupt.nmid.wxhelper.wxhelper.config;

import io.jsonwebtoken.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/2 16:41
 */

@ConfigurationProperties(prefix = "config.jwt")
@Component
public class JwtConfig {

    private String secret;

    private long expire;

    private String header;

    public static final String ROLE = "ROLE";

    /**
     * 生成token
     * @param userid   用户id
     * @return
     */
    public String createToken (String userid, String role){
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

            HashMap<String, Object> map = new HashMap<>();
            map.put(ROLE, role);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userid)
                .setClaims(map)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    /**
     * 获取token中注册信息
     * @param token
     * @return
     */
    public Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            System.out.println("jwt 过期");
            return null;
        }catch (SignatureException e){
            System.out.println("解码失败");
            return null;
        } catch (Exception e){
            return null;
        }
    }
    /**
     * 验证token是否过期失效
     * @param expirationTime
     * @return
     */
    public boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * 获取token失效时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }
    /**
     * 获取用户id从token中
     */
    public String getUserIdFromToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getTokenClaim(token).getIssuedAt();
    }

    // --------------------- getter & setter ---------------------

    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public long getExpire() {
        return expire;
    }
    public void setExpire(long expire) {
        this.expire = expire;
    }
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
}

