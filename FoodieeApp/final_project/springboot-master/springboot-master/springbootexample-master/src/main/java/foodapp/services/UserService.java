package foodapp.services;


import java.util.Optional;

import foodapp.model.User;

public interface UserService {

    User registerUser(User user) throws Exception;

    String findByFirstNameAndPassword(String firstName, String password) throws Exception;
    Optional<User> getUserById(int userId);
	
    
}

