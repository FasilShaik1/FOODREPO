package foodapp.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import foodapp.model.User;
import foodapp.repo.UserRepository;
import  foodapp.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String findByFirstNameAndPassword(String firstName, String password) throws Exception {
        Optional<User> user = userRepository.findByFirstName(firstName);
        if (!user.isPresent() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new Exception("Invalid first name or password");
        }
        return ("{\r\n"
        		+ "    \"message\": \"Login Success\",\r\n"
        		+ "    \"status\": true,\r\n"
        		+ "    \"role\": \"user\"\r\n"
        		+ "}");
    }

	
    @Override
    public Optional<User> getUserById(int userId) {
        // Fetch user from the repository based on userId
        return userRepository.findById(userId);
    }
    
}
