package com.example.demomybatis.system.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.demomybatis.system.entity.SystemUser;
import com.example.demomybatis.system.service.SystemUserService;
import com.example.demomybatis.utils.ResultUtils;
import com.example.demomybatis.utils.ShiroUtils;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.code.kaptcha.Constants;

/**
 * @liuhongchun
 * @Date 2019/9/16  11:12
 * @des
 */
@RestController
@RequestMapping("/system/")
@Slf4j
@Api(tags = "系统登录接口")
public class SystemLoginController extends BaseController {

    @Autowired
    SystemUserService systemUserService;

    @Autowired
    private Producer producer;

    /**
     * 获取验证码
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("captcha")
    @ApiOperation("获取验证码信息")
    public void getVerificationCode(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @return
     */
    @PostMapping(value = "user")
    @ResponseBody
    @ApiOperation("用户登录")
    public ResultUtils login(String username, String password, String captcha) {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);

        if (kaptcha.equals(false)) {
            return ResultUtils.ResultError("验证码已失效");
        }
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultUtils.ResultOk("验证码不正确");
        }
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResultUtils.ResultError(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResultUtils.ResultError("账号或密码不正确");
        } catch (LockedAccountException e) {
            return ResultUtils.ResultError("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return ResultUtils.ResultError("账户验证失败");
        }

        SystemUser systemUser = new SystemUser();
        systemUser.setPassWord(password);
        systemUser.setUserId(getUserId());

        return ResultUtils.ResultOk().put("code", HttpStatus.OK.value());
    }

    /**
     * 注销
     *
     * @return
     */
    @GetMapping(value = "logout")
    @ApiOperation("用户注销")
    public ResultUtils logout() {
        ShiroUtils.logout();
        return ResultUtils.ResultOk().put("code", HttpStatus.OK.value());
    }
}
