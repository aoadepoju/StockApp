package uis.stockapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uis.stockapp.model.Portfolio;
import uis.stockapp.model.User;

@Repository
public interface PortfolioRepository extends CrudRepository<Portfolio, Integer>{
	List<Portfolio> findByUserDetailsAndActive(User userDetails,Integer active);
}
