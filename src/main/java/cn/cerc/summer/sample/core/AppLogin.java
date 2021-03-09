package cn.cerc.summer.sample.core;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.stereotype.Component;

import cn.cerc.mis.core.IAppLogin;
import cn.cerc.mis.core.IForm;
import cn.cerc.mis.core.RequestData;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppLogin implements IAppLogin {
    private static String LOGIN_JSPFILE = "login.jsp";
    private static String TEMP_SESSIONID = "88888888";
    private IForm form;

    @Override
    public void init(IForm form) {
        this.form = form;
    }

    @Override
    public String checkToken(String token) throws IOException, ServletException {
        if (token == null)
            return LOGIN_JSPFILE;

        // 此处应去数据库检验
        if (TEMP_SESSIONID.equals(token))
            return null;

        // 显示登录页面的jsp文件名
        return LOGIN_JSPFILE;
    }

    @Override
    public String checkLogin(String userCode, String password) throws IOException, ServletException {
        log.info("userCode:{}, password:{}", userCode, password);
        if ("admin".equals(userCode) && "123456".equals(password)) {
            //验证通过后赋值token
            form.getRequest().getSession().setAttribute(RequestData.TOKEN, TEMP_SESSIONID);
            return null;
        }

        // 显示登录页面的jsp文件名
        return LOGIN_JSPFILE;
    }

}
