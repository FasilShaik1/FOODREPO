package foodapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import foodapp.AuthConfig.AuthenticationResponse;
import foodapp.AuthConfig.JwtUtil;
import foodapp.model.User;
import foodapp.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Validated @RequestBody User user) throws Exception {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginCredentials) throws Exception {
        String username = userService.findByFirstNameAndPassword(loginCredentials.getFirstName(), loginCredentials.getPassword());
        if (username == null) {
            throw new Exception("Invalid first name or password");
        }
        
        String token = jwtUtil.generateToken(username, null);
       
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
