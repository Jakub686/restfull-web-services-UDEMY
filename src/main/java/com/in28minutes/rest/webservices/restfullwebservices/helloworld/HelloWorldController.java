package com.in28minutes.rest.webservices.restfullwebservices.helloworld;


import org.springframework.web.bind.annotation.*;

//Controller
@RestController
public class HelloWorldController {


    @GetMapping("/hello-World")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World,%s",name));
    }

}
