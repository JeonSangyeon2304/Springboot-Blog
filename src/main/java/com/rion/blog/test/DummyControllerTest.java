package com.rion.blog.test;

import java.util.List;

import javax.transaction.Transactional;

import com.rion.blog.model.RoleType;
import com.rion.blog.model.User;
import com.rion.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Sort;

@RestController
public class DummyControllerTest {
    
    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다.";
        }
        
        return "삭제되었습니다. id : " + id;
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requsetUser) {
        System.out.println("id: " + id);
        System.out.println("password: " + requsetUser.getPassword());
        System.out.println("email: " + requsetUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requsetUser.getPassword());
        user.setEmail(requsetUser.getEmail());
        //userRepository.save(user);
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();        
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        //User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
        //    @Override
        //    public IllegalArgumentException get() {
        //        return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
        //    }  
        //});
        User user = userRepository.findById(id).orElseThrow( ()->{
            return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
        });
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user) {
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}