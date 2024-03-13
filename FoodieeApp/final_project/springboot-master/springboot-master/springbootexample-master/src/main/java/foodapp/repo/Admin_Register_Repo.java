

package foodapp.repo;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foodapp.model.Admin_Register;

@Repository
public interface Admin_Register_Repo extends JpaRepository<Admin_Register,Integer> {
   

	Optional<Admin_Register> findOneByFirstNameAndPassword(String firstName, String encodedPassword);

	Optional<Admin_Register> findOneByFirstName(String firstName);
}
