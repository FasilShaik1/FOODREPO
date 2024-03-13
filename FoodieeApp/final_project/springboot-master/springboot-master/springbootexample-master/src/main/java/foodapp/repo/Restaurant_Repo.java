package foodapp.repo;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foodapp.model.Restaurant;

@Repository
public interface Restaurant_Repo extends JpaRepository<Restaurant, Integer> {


	
   
}
