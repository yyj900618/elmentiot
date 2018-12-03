package com.cmcc.iot.filter;


import com.cmcc.iot.common.ResponseModel;
import com.cmcc.iot.model.User;
import com.cmcc.iot.utills.JsonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

public class Loginfilter extends AuthenticatingFilter {


    private String kickoutUrl; //踢出后到的地址
    private boolean kickoutAfter; //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
    private int maxSession; //同一个帐号最大会话数 默认1
    private SessionManager sessionManager;
    private Cache<String, Deque<String>> cache;


    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-activeSessionCache");
    }


    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return null;
    }

    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue)  {
        if (isLoginRequest(servletRequest, servletResponse)) {
            return true;
        }
        else{
//            Subject subject=SecurityUtils.getSubject();

            Subject subject= getSubject(servletRequest,servletResponse);
            Session session= subject.getSession();
            User user=(User) session.getAttribute("userinfo");
            if (user==null)
                return false;

            String username= user.getLoginname();
            String sessionId = session.getId().toString();
            Deque<String> deque = cache.get(username);
            if(deque == null) {
                deque = new LinkedList<>();
                cache.put(username, deque);
            }
            if(!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
                deque.push(sessionId);
            }
            while(deque.size() > maxSession) {
                Serializable kickoutSessionId = null;
                if(kickoutAfter) { //如果踢出后者
//                        kickoutSessionId=deque.getFirst();
//                        System.out.println("前一个登录Sessionid是："+kickoutSessionId);
                    kickoutSessionId = deque.removeFirst();
                } else { //否则踢出前者
                    kickoutSessionId = deque.removeLast();
                }
                try {
                    SessionKey sessionKey= new DefaultSessionKey(kickoutSessionId);
                    Session kickoutSession = sessionManager.getSession(sessionKey);
                    if(kickoutSession != null) {
                        //设置会话的kickout属性表示踢出了
                        kickoutSession.setAttribute("kickout", "1");
                    }
                } catch (Exception e) {//ignore exception
                    e.printStackTrace();
                }
            }
            if (session.getAttribute("kickout") != null) {
                return false;
            }
            return  true;
        }



    }

    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse httpResponse ;
        httpResponse = WebUtils.toHttp(servletResponse);
        httpResponse.setStatus(200);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin,No-Cache,X-Requested-With,If-Modified-Since,Pragma,Last-Modified,Cache-Control,Expires,Content-Type,X-E4M-With");
        httpResponse.setHeader("Access-Control-Allow-Credentials","true");
        PrintWriter out = null;
        try{
            Subject subject= SecurityUtils.getSubject();
            Session session =subject.getSession();
            User user=(User) session.getAttribute("userinfo");
            if (user==null){
                out = httpResponse.getWriter();
                ResponseModel Return = new ResponseModel(1001l,"未登录",null);
                String jsonString= JsonUtils.toJson(Return);
                out.append(jsonString);
            }
            else  if (session.getAttribute("kickout").toString().equals("1")) {
                subject.logout();
                out = httpResponse.getWriter();
                ResponseModel Return = new ResponseModel(10086l,"该账号在其他地方登录,您已被踢出",null);
                String jsonString= JsonUtils.toJson(Return);
                out.append(jsonString);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (out != null) {
                out.close();
            }
        }
        return false;
    }
}