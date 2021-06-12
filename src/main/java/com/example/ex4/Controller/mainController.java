package com.example.ex4.Controller;


import com.example.ex4.Bean.CounterMess;
import com.example.ex4.Bean.Label;

import com.example.ex4.Filter.LoggingInterceptor;
import com.example.ex4.Listener.SessionListener;
import com.example.ex4.Repository.Messages;
import com.example.ex4.Repository.MessagesRepository;
import com.example.ex4.Repository.User;
import com.example.ex4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class mainController {
    @Resource(name = "sessionBean")
    public Label sessionObj;

    @Resource(name = "CouterBean")
    private CounterMess couter;

    @Resource(name="sessionListenerWithMetrics")
    private ServletListenerRegistrationBean<SessionListener> metrics;

    @Autowired
    private UserRepository repository;
    @Autowired
    private MessagesRepository mesRepo;

    @GetMapping({"/"})
    public String loadingPage(User user ,Messages messages, HttpSession session,Model model) {
        System.out.println(sessionObj.getConnected());
        if(sessionObj.getConnected())
            return "ChatRoom";
         return "LoginPage";
    }

    @PostMapping("/login")
    public String loginPage(@Valid User user, BindingResult result, Model model,Messages message) {

     if (result.hasErrors())
            return "LoginPage";

    if(repository.existsByUserName(user.getUserName())){
        model.addAttribute("error",true);
        return "LoginPage";
    }
        sessionObj.setConnected(true);
        sessionObj.setUserName(user.getUserName());
        repository.save(user);
        model.addAttribute("User", user.getUserName());
        return "ChatRoom";
    }

    @PostMapping("/addMessages")
    public String addMessages(@Valid Messages messages, BindingResult result, Model model, HttpSession session,User user) {
        if (result.hasErrors())
            return "ChatRoom";
        messages.setUserNames(sessionObj.getUserName());
        messages.setTime(java.time.LocalTime.now());
        mesRepo.save(messages);
        return "ChatRoom";
    }


    @GetMapping(value = "/getMessages/{id}")
    public @ResponseBody List<Messages> getMessage(@PathVariable("id") int productId){
        if(mesRepo.findFirst1ByOrderByIdDesc()==null ||mesRepo.findFirst1ByOrderByIdDesc().getId()==productId)
            return  new ArrayList<Messages>();
        return mesRepo.findFirst5ByOrderByIdDesc();
    }

    @GetMapping(value = "/getParticipants/{id}")
    public @ResponseBody List<User> getParticipants(@PathVariable("id") int productId){
        if(repository.findFirst1ByOrderByIdDesc()==null ||repository.findFirst1ByOrderByIdDesc().getId()==productId)
            return  new ArrayList<User>();
        return repository.findAll();
    }

    @GetMapping("/logOut")
    public  String logOut(User user,Messages messages, Model model){
        repository.delete(repository.findByUserName(sessionObj.getUserName()));
        sessionObj.setConnected(false);
       return "LoginPage";
    }


    @GetMapping("/getUserName")
    public @ResponseBody HashMap<String, Object> getUserName(HttpSession session){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", sessionObj.getUserName());
            return map;
    }
}
