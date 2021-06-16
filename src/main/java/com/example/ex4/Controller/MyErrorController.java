package com.example.ex4.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping(value="/error")
    public String handleError() {
        return "error";
    }

}