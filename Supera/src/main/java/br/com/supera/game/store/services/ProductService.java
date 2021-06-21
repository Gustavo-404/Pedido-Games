package br.com.supera.game.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.supera.game.store.entities.Product;
import br.com.supera.game.store.repositories.ProductRepository;
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Optional<List<Product>> filterByName() {
		Optional<List<Product>> productsFiltrados = productRepository.getByName();
		return productsFiltrados;
	}
	
	public Optional<List<Product>> filterByPrice() {
		Optional<List<Product>> productsFiltrados = productRepository.getByPrice();
		return productsFiltrados;
	}
	
	public Optional<List<Product>> filterByScore() {
		Optional<List<Product>> productsFiltrados = productRepository.getByScore();
		return productsFiltrados;
	}
	
	/*public Optional<Product> filterBy(String filtro) {
		Optional<Product> productsFiltrados = productRepository.getBy(filtro);
		return productsFiltrados;
	}*/

	public Product create(Product product) {
		product = productRepository.save(product);
		return product;
	}

	public Optional<Product> getById(Long id) {
		return productRepository.findById(id);
	}

	public Product update(Product product) {
		product = productRepository.save(product);
		return product;
	}

	public void delete(Long id) {
		productRepository.deleteById(id);
	}

}
