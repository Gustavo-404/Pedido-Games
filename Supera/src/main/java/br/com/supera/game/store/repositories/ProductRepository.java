package br.com.supera.game.store.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.supera.game.store.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p ORDER BY price")
	Optional<List<Product>> getByPrice();
	
	@Query("SELECT p FROM Product p ORDER BY score")
	Optional<List<Product>> getByScore();
	
	@Query("SELECT p FROM Product p ORDER BY name")
	Optional<List<Product>> getByName();
		
}
