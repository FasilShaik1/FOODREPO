package foodapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import foodapp.model.Order;

@Repository
public interface Order_Repo extends JpaRepository<Order, Integer>{

	@Query("SELECT o FROM Order o WHERE o.user.id = ?1")
	List<Order> findAllByUserId(int userId);


}
