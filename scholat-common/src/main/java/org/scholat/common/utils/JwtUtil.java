package org.scholat.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.scholat.common.pojo.UserLoginInfo;

import java.util.Date;

@Slf4j
public class JwtUtil {

    public static final String SUBJECT = "xmz";
    /**
     * 设置token的过期时间
     */
    public static final int EXPIRITION = 1000 * 10;
    /**
     * 秘钥，不同的环境应该配置不同的秘钥，注意保存好，不要泄露
     */
    public static final String APPSECRET_KEY = "gsgss454646^^%$##";

    /**
     * 加密生成token
     * @param user
     * @return
     */
    public static String generateJsonWebToken(UserLoginInfo user){

        if(user.getUserId() == null || user.getUserMail()==null||user.getRole()==null){
            return null;
        }

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("userId", user.getUserId())
                .claim("userMail",user.getUserMail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION ))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    /**
     * 解密token获取用户信息
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            System.out.println("校验不通过");
            return null;
        }

    }



}
