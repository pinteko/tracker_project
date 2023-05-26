package makers.internship.tracker_project.controllers;

import lombok.RequiredArgsConstructor;
import makers.internship.tracker_project.dto.JwtRequest;
import makers.internship.tracker_project.dto.UserDto;
import makers.internship.tracker_project.entities.User;
import makers.internship.tracker_project.repositories.RoleRepository;
import makers.internship.tracker_project.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
   private final BCryptPasswordEncoder passwordEncoder;
   private final RoleRepository roleRepository;
   private final UserRepository userRepository;

   @Transactional
   @PostMapping("/registration")
   public ResponseEntity<?> addUser(@RequestBody JwtRequest reqRequest) {
      User user = new User();
      user.setUsername(reqRequest.getUsername());
      user.setPassword(passwordEncoder.encode(reqRequest.getPassword()));
      user.setEmail(reqRequest.getEmail());
      user.setRoles(Collections.singleton(roleRepository.getReferenceById(1L)));

      userRepository.save(user);

      return ResponseEntity.ok(new UserDto(user.getUsername(), user.getEmail()));
   }
}
