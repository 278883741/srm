package com.imooc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.model.AuthToken;
import com.imooc.model.AuthUser;
import com.imooc.model.Card;
import com.imooc.service.AuthTokenService;
import com.imooc.service.AuthUserService;
import com.imooc.utils.AESEncryptUtil;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    ObjectMapper objectMapper;
    // jwt介绍 - https://www.jianshu.com/p/576dbf44b2ae

    @Autowired
    AuthUserService authUserService;
    @Autowired
    AuthTokenService authTokenService;

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
                .signWith(SignatureAlgorithm.HS256, "2691894")
                // 指定过期时间
                .setExpiration(calendar.getTime());
        return jwtBuilder.compact();
    }

    @RequestMapping(value = "/parseToken", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Claims parseToken(@RequestParam(value = "accessToken") String accessToken) {
        String message;
        try {
            String key = "2691894";
            return Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            message = "token已过期";
        } catch (SignatureException e) {
            message = "校验失败";
        } catch (Exception e) {

        }
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity login(@RequestBody AuthUser authUser) throws Exception {
        authUser = authUserService.get(authUser);
        if (authUser != null) {
            authUser.setTimestamp(System.currentTimeMillis());
            AuthToken authToken = new AuthToken();
            authToken.setUserId(authUser.getId());
            authToken = authTokenService.get(authToken);

            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            String accessToken = AESEncryptUtil.aesEncrypt(objectMapper.writeValueAsString(authUser), "1234567812345678");

            if(authToken == null){
                authToken = new AuthToken();
                authToken.setUserId(authUser.getId());
                authToken.setCreateTime(date);
                authToken.setAccessExpire(calendar.getTime());
                authToken.setAccessToken(accessToken);
                authTokenService.add(authToken);
            }else{
                authToken.setUpdateTime(date);
                authToken.setAccessExpire(calendar.getTime());
                authToken.setAccessToken(accessToken);
                authTokenService.update(authToken);
            }
            return new ResponseEntity<>(authToken, HttpStatus.OK);
        }
        return new ResponseEntity<>("账号或密码错误", HttpStatus.OK);
    }
}