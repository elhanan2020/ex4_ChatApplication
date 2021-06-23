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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * this class is a second controller class that  care to ajax request
 */
@RestController
public class AjaxController {

    /**
     * here i inject a label class in session
     */
    @Resource(name = "sessionBean")
    public Label sessionObj;
    /**
     * here i inject a user class in repository
     */
    @Autowired
    private UserRepository repository;
    /**
     * here i inject a user message in repository
     */
    @Autowired
    private MessagesRepository mesRepo;

    /**
     * this requestmapping return a List of message only it was a new message from a user anf  it not new he doesnt send any list
     * @param productId it get a number of the last message that have
     * @return
     */
    @GetMapping(value = "/getMessages/{id}")
    public @ResponseBody    List<Messages> getMessage(@PathVariable("id") int productId){
        User user = repository.findByUserName(sessionObj.getUserName());
        user.setTime(java.time.LocalTime.now());
        repository.save(user);
        if(mesRepo.findFirst1ByOrderByIdDesc()==null ||mesRepo.findFirst1ByOrderByIdDesc().getId()==productId)
            return  new ArrayList<Messages>();
        return mesRepo.findFirst5ByOrderByIdDesc();
    }
    /**
     * this requestmapping return a List of user only it was a new message from a user anf  it not new he doesnt send any list
     * @param productId it get a number of the last user that have in frontend of the application
     * @return
     */
    @GetMapping(value = "/getParticipants/{id}")
    public @ResponseBody List<User> getParticipants(@PathVariable("id") int productId){
        List<User> users = repository.findAll();
        List<User> userToReturn = new ArrayList<>();
        users.forEach(user ->{
           int time = LocalTime.now().minusSeconds(12).compareTo(user.getTime());
           if(time <= 0)
                userToReturn.add(user);
       });
        return userToReturn;
    }

    /**
     * this request mapping return the name of the current user
     * @return  name user
     */
    @GetMapping(value="/getUserName")
    public @ResponseBody    HashMap<String, Object> getUserName(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", sessionObj.getUserName());
        return map;
    }

    /**
     * this request return all message from a giving user name
     * @param nameOfUser some username
     * @return list of message from some user
     */
    @GetMapping(value = "/searchByUser/{nameOfUser}")
    public @ResponseBody List<Messages> searchByUser(@PathVariable("nameOfUser") String nameOfUser){
        return mesRepo.findAllByUserName(nameOfUser);
    }
    /**
     * this request return all message from a giving user name
    * @return list of message that contain some string
     */
    @GetMapping(value = "/searchByMessages")
    public @ResponseBody List<Messages> searchByText(){
        if(sessionObj.isSearchByUser())
            return mesRepo.findAllByUserName(sessionObj.getToSearch());
        return mesRepo.findAllByMessageContains(sessionObj.getToSearch());

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



