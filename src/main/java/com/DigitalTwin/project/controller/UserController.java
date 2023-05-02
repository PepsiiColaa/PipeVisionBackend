package com.DigitalTwin.project.controller;


import com.DigitalTwin.project.common.CustomException;
import com.DigitalTwin.project.common.R;
import com.DigitalTwin.project.entity.User;
import com.DigitalTwin.project.service.UserService;
import com.DigitalTwin.project.utils.MailUtils;
import com.DigitalTwin.project.utils.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param request HttpServletRequest
     * @param user    用户
     * @return R
     */
    @PostMapping("/login")
    public R<User> login(@ApiIgnore HttpServletRequest request, @RequestBody User user) {
        //1 将页面提交的密码password进行md5加密处理
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2 根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User userOne = userService.getOne(queryWrapper);

        //3 如果没有查询到，则返回登录失败结果
        if (userOne == null) {
            return R.error("登陆失败");
        }

        //4 密码比对，如果不一致则返回登录失败结果
        if (!userOne.getPassword().equals(password)) {
            return R.error("登录失败");
        }

        // 5 查看用户状态，如果为已禁用状态，则返回用户已禁用结果
        if (userOne.getStatus() == 0) {
            return R.error("账号已禁用");
        }

        //6 登陆成功，将用户ID存入Session并返回登陆成功结果
        request.getSession().setAttribute("user", userOne.getId());
        return R.success(userOne);
    }


    /**
     * 用户注册
     *
     * @param session HttpSession
     * @param map     包含username password email code
     * @return R
     */
    @PostMapping("/register")
    public R<User> register(@ApiIgnore HttpSession session, @RequestBody Map map) {
        log.info("开始注册");
        String username = map.get("username").toString();
        String email = map.get("email").toString();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User userTemp = userService.getOne(queryWrapper);
        if (userTemp != null)
            return R.error("该用户名已存在！");
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        userTemp = userService.getOne(queryWrapper);
        if (userTemp != null)
            return R.error("该邮箱已存在！");
        if (username != null) {
            String password = map.get("password").toString();
            String code = map.get("code").toString();
            Object codeInSession = session.getAttribute(email);
            if (!codeInSession.equals(code)) {
                return R.error("注册码填写错误！请重新填写！");
            } else {
                password = DigestUtils.md5DigestAsHex(password.getBytes());
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setStatus(1);
                userService.save(user);
                session.setAttribute("user", user.getId());
                return R.success(user);
            }
        }
        return R.error("注册失败，请检查填写项目！");

    }

    /**
     * 发送邮件
     *
     * @param session HttpSession
     * @param user    user用户
     * @return R
     */
    @PostMapping("/sendMail")
    public R<String> sendMail(@ApiIgnore HttpSession session, @RequestBody User user) {
        //获取手机号
        String email = user.getEmail();

        if (StringUtils.isNotEmpty(email)) {
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);


            //调用阿里云提供的短信服务API完成发送短信
            //SMSUtils.sendMessage(phone, code);

            //需要将生成的验证码保存到Session
            session.setAttribute(email, code);

            //将生成的验证码缓存到Redis中，并设置有效期为5分钟
            //redisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES);
            try {
                MailUtils.sendMail("1109896198@qq.com", code);
                return R.success("邮箱验证码发送成功");
            } catch (CustomException e) {
                return R.error(e.getMessage());
            }
        }
        return R.error("邮箱验证码发送失败");
    }

}
