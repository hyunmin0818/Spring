package com.codingbox.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.element.Name;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data","firstData");
        return "hello";
    }

    /*
    url : get방식
        /hello-mvc
    data: key : name , value : UserName
    화면: hello-template.html
     */
    @GetMapping("/hello-mvc")
    public String hellomvc(@RequestParam(value = "name", required = false, defaultValue = "이중원") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
}
