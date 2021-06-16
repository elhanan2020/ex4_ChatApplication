package com.example.ex4.Controller;

import com.example.ex4.Bean.Label;
import com.example.ex4.Repository.Messages;
import com.example.ex4.Repository.MessagesRepository;
import com.example.ex4.Repository.User;
import com.example.ex4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class AjaxController {

    @Resource(name = "sessionBean")
    public Label sessionObj;

    @Autowired
    private UserRepository repository;
    @Autowired
    private MessagesRepository mesRepo;

    @GetMapping(value = "/getMessages/{id}")
    public @ResponseBody    List<Messages> getMessage(@PathVariable("id") int productId){
        User user = repository.findByUserName(sessionObj.getUserName());
        user.setTime(java.time.LocalTime.now());
        repository.save(user);
        if(mesRepo.findFirst1ByOrderByIdDesc()==null ||mesRepo.findFirst1ByOrderByIdDesc().getId()==productId)
            return  new ArrayList<Messages>();
        return mesRepo.findFirst5ByOrderByIdDesc();
    }

    @GetMapping(value = "/getParticipants/{id}")
    public @ResponseBody List<User> getParticipants(@PathVariable("id") int productId){
        List<User> users = repository.findAll();
        List<User> userToReturn = new ArrayList<>();
        users.forEach(user ->{
           int time = LocalTime.now().minusSeconds(12).compareTo(user.getTime());
           System.out.println("l heure maintenant  "+ LocalTime.now()+ "l heure maintenant  - 10second "
                   + LocalTime.now().minusSeconds(10)+ "la derniere fois quil a fait fetch "+ user.getUserName()+"  "+user.getTime()+ "      "+time);
           if(time <= 0)
                userToReturn.add(user);
       });
        return userToReturn;
    }

    @GetMapping(value="/getUserName")
    public @ResponseBody    HashMap<String, Object> getUserName(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", sessionObj.getUserName());
        return map;
    }
    @GetMapping(value = "/searchByUser/{nameOfUser}")
    public @ResponseBody List<Messages> searchByUser(@PathVariable("nameOfUser") String nameOfUser){
        return mesRepo.findAllByUserName(nameOfUser);
    }

    @GetMapping(value = "/searchByMessages/{text}")
    public @ResponseBody List<Messages> searchBytext(@PathVariable("text") String text){
        return mesRepo.findAllByMessageContains(text);
    }
    @GetMapping(value = "/disconnected")
    public @ResponseBody List<HashMap<String, Object>> isDisconnected(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("disconnect","isDisconnected");
        List<HashMap<String, Object>> myList = new ArrayList<HashMap<String, Object>>();
        myList.add(map);
        return myList;
    }
}
