package com.imooc.controller;

import com.imooc.model.Card;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping(value = "/jwt")
public class JwtController {
    private static final Logger logger = LoggerFactory.getLogger(JwtController.class);
    // jwt介绍 - https://www.jianshu.com/p/576dbf44b2ae




    @RequestMapping(value = "/createToken", method = RequestMethod.GET)
    public Object createToken() {
        String userId = "20";
        String subject = "userName";
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);


        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(userId)
                .setSubject(subject)
                .setIssuer("tom")
                .setIssuedAt(date)
                // 指定加密算法，指定密钥
                .signWith(SignatureAlgorithm.HS256,"2691894")
                // 指定过期时间
                .setExpiration(calendar.getTime());
       return jwtBuilder.compact();
    }

    @RequestMapping(value = "/parseToken", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Claims parseToken(@RequestParam(value = "accessToken") String accessToken) {
        String message;
        try{
            String key = "2691894";
            return Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(accessToken)
                    .getBody();
        }catch (ExpiredJwtException e){
            message = "token已过期";
        }catch (SignatureException e){
            message = "校验失败";
        }catch (Exception e){

        }
        return null;
    }
}
