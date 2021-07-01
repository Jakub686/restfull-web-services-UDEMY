package com.in28minutes.rest.webservices.restfullwebservices.user;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


import javax.validation.Valid;
import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.ResourceBundle;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping(
            "/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user==null)
            throw new UserNotFoundException("id-" + id);

        //HATEOAS

        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if(user==null)
            throw new UserNotFoundException("id-" + id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
