package com.example.ex4.Controller;
import com.example.ex4.Bean.Label;
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
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class mainController {

    @Resource(name = "sessionBean")
    public Label sessionObj;



    @Resource(name="sessionListenerWithMetrics")
    private ServletListenerRegistrationBean<SessionListener> metrics;

    @Autowired
    private UserRepository repository;
    @Autowired
    private MessagesRepository mesRepo;

    @GetMapping(value={"/","/login"})
    public String loadingPage(User user ,Messages messages, HttpSession session,Model model) {
        if(sessionObj.getConnected()){
            model.addAttribute("User", sessionObj.getUserName());
            return "ChatRooms";
        }
         return "LoginPage";
    }

    @PostMapping(value="/login")
    public String loginPage(@Valid User user, BindingResult result, Model model,Messages message) {

     if (result.hasErrors())
         return "LoginPage";
     else if (repository.existsByUserName(user.getUserName())||user.getUserName().trim().equals(""))
     {
         model.addAttribute("error",repository.existsByUserName(user.getUserName()));
         model.addAttribute("space",user.getUserName().trim().equals(""));
         return "LoginPage";
     }
        user.setTime(java.time.LocalTime.now().minusSeconds(10));
        sessionObj.setConnected(true);
        sessionObj.setUserName(user.getUserName());
        repository.save(user);
        model.addAttribute("User", user.getUserName());
        return "ChatRooms";
    }

    @PostMapping(value="/addMessages")
    public String addMessages(Messages messages, Model model, HttpSession session,User user) {
        messages.setUserNames(sessionObj.getUserName());
        messages.setTime(java.time.LocalTime.now());
        mesRepo.save(messages);
        model.addAttribute("User", sessionObj.getUserName());
        return "ChatRooms";
    }



    @GetMapping(value="/logOut")
    public  String logOut(User user,Messages messages, Model model){
        repository.delete(repository.findByUserName(sessionObj.getUserName()));
        sessionObj.setConnected(false);
       return "LoginPage";
    }

    @GetMapping(value = "/showResultOfResearch/search/{param}/byUser/{true}")
    public String searchBytext(@PathVariable("param") String text, @PathVariable("true") boolean byUser){
        sessionObj.setSearchByUser(byUser);
        sessionObj.setToSearch(text);
        return "searchPage";
    }
}
