package com.hnguigu.demo.systemModule.controller;

import com.hnguigu.demo.common.util.ResponseResult;
import com.hnguigu.demo.common.util.VerifyCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @program: BOS
 * @description:
 * @author: 徐子楼
 * @create: 2018-10-10 09:23
 **/
@Controller
public class LoginController {


    @RequestMapping(value = {"/toLogin", "/"})
    public String toLogon() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult login(String loginName, String password, String verifyCode, HttpSession session) {
        //获得验证码
        String code = (String) session.getAttribute("verifyCode");
        //首先判断验证码是否正确
        if (StringUtils.isEmpty(verifyCode) || !verifyCode.equals(code)) {
            return ResponseResult.fail("请输入正确的验证码");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //登录
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return ResponseResult.fail("没有此用户!");
        } catch (IncorrectCredentialsException ice) {
            return ResponseResult.fail("密码不正确!");
        } catch (LockedAccountException lae) {
            return ResponseResult.fail("账户已锁定!");
        } catch (ExcessiveAttemptsException eae) {
            return ResponseResult.fail("用户名或密码错误次数过多!");
        } catch (AuthenticationException ae) {
            return ResponseResult.fail("用户名或密码有误,请重新输入!");
        }

        if (subject.isAuthenticated()) {
            return ResponseResult.success();
        } else {
            token.clear();
            return ResponseResult.fail("系统正在维护请稍后重试!");
        }
    }

    /**
     * 验证码图片
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getVerifyCodeImage")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute("verifyCode", verifyCode);
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "redirect:/toLogin";
    }
}
