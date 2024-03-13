package foodapp.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import foodapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);


	Optional<User> findByFirstName(String firstName);

	
	
	


}

