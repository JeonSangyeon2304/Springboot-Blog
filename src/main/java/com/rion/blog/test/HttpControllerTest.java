package com.rion.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest : ";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member member = Member.builder().username("SSAR").password("1234").email("ASDFASD@ASDFASDF.com").build();

        System.out.println(TAG + "getter: " + member.getUsername());
        member.setUsername("CCSSSDO");
        System.out.println(TAG + "setter: " + member.getUsername());
        
        return "LombokTest 완료";
    }

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