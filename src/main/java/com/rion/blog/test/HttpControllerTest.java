package com.rion.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

    @GetMapping("/http/get")
    public String getTest(Member member) {
        return "get Request: " + member.getId() + ", " + member.getUsername() + ", " + member.getPassword() + ", " + member.getEmail();
    }

    @PostMapping("/http/post")
    public String postTest(@RequestBody Member member) {
        return "post Request: " + member.getId() + ", " + member.getUsername() + ", " + member.getPassword() + ", " + member.getEmail();
        //return "post Requset: " + text;
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member member) {
        return "put Request: " + member.getId() + ", " + member.getUsername() + ", " + member.getPassword() + ", " + member.getEmail();
    }

    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete Request";
    }
}