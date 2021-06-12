package com.example.ex4.Filter;

import com.example.ex4.Bean.Label;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


/**
 * this class intercepts all requests and displays statistics: request processing duration
 *
 * it also demonstrates how to inject a bean (you cannot use Spring @Autowired in a
 * HandlerInterceptor but you can receive the bean via the ctor - from the configuration class)
 */
@EnableWebMvc
public class LoggingInterceptor implements HandlerInterceptor {

    public LoggingInterceptor() {

    }
       @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
       Label temp= (Label) request.getSession().getAttribute("scopedTarget.sessionBean");
           Enumeration<String> attributes = request.getSession().getAttributeNames();
           while (attributes.hasMoreElements()) {
               String attribute = (String) attributes.nextElement();
               System.out.println(attribute+" : "+request.getSession().getAttribute(attribute));
           }
       // location

        if(temp==null||!temp.getConnected()) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
                                Object handler, Exception ex) throws Exception {
    }

}