package com.zdsy.drivingschoolreservationsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zdsy.drivingschoolreservationsystem.constant.WxCodeType;
import com.zdsy.drivingschoolreservationsystem.constant.WxConstant;
import com.zdsy.drivingschoolreservationsystem.model.User;
import com.zdsy.drivingschoolreservationsystem.model.UserPrincipal;
import com.zdsy.drivingschoolreservationsystem.model.WxSession;
import com.zdsy.drivingschoolreservationsystem.repository.UserRepository;
import com.zdsy.drivingschoolreservationsystem.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("anonymous")
public class AnonymousController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/login")
    public Result login(@RequestParam String code) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code";
        String body = restTemplate.getForObject(url, String.class, WxConstant.appId, WxConstant.appSecret, code);
        ObjectMapper mapper = new ObjectMapper();
        WxSession wxSession = mapper.readValue(body, WxSession.class);

        Integer errcode = wxSession.getErrcode();
        if (errcode != WxCodeType.success.getCode()) {
            return Result.failed();
        }

        String openid = wxSession.getOpenid();
        User user = userRepository.findByOpenid(openid);
        if (user == null) {
            user = new User(openid);
            userRepository.save(user);
        }
        UserPrincipal principal = new UserPrincipal();
        principal.setUserId(user.getId());
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null,
                AuthorityUtils.createAuthorityList("ROLE_USER"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Result.success(user);
    }
}