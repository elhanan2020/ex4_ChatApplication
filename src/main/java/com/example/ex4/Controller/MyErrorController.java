package com.example.ex4.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * tis controller class care about a 404 page to redirect to html page
 */
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping(value="/error")
    public String handleError() {
        return "error";
    }

}