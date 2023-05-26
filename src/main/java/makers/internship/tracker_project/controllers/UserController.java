package makers.internship.tracker_project.controllers;

import lombok.RequiredArgsConstructor;
import makers.internship.tracker_project.entities.User;
import makers.internship.tracker_project.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    @GetMapping("/{username}")
    public Optional<User> getUser(@PathVariable("username") String username){
        return service.findByUsername(username);
    }

}
