package uis.stockapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uis.stockapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUserNameAndPassword(String username, String password);
	User findByUserName(String userName);
	
}
