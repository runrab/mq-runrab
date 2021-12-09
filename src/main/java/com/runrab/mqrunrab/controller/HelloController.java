package com.runrab.mqrunrab.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
@Controller
public class HelloController {
    @RequestMapping("/m1")
    @ResponseBody public String m1(){
        return "Hello Spring Boot m1";
    }
    @RequestMapping("/m2")
    @ResponseBody
    public List<String> m2(){
        List<String> result = new ArrayList<String>();
        result.add("a");
        result.add("b");
        result.add("c");
        result.add("d");
        return result;
    }
}