package br.com.supera.game.store.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.game.store.entities.Product;
import br.com.supera.game.store.services.ProductService;

@CrossOrigin
@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> productSearch = productService.findAll();
		if (productSearch.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Product>>(productSearch, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable Long id) {
		Optional<Product> productId = productService.getById(id);
		if (productId.isPresent()) {
			return new ResponseEntity<Product>(productId.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/name")
	public ResponseEntity<List<Product>> getByName() {
		Optional<List<Product>> productId = productService.filterByName();
		if (productId.isPresent()) {
			return new ResponseEntity<List<Product>>(productId.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/price")
	public ResponseEntity<List<Product>> getByPrice() {
		Optional<List<Product>> productId = productService.filterByPrice();
		if (productId.isPresent()) {
			return new ResponseEntity<List<Product>>(productId.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/score")
	public ResponseEntity<List<Product>> getByScore() {
		Optional<List<Product>> productId = productService.filterByScore();
		if (productId.isPresent()) {
			return new ResponseEntity<List<Product>>(productId.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	/*@GetMapping("/{filter}")
	public ResponseEntity<?> getById(@PathVariable String filter) {
		Optional<Product> productId = productService.filterBy(filter);
		if (productId.isPresent()) {
			return new ResponseEntity<Product>(productId.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		return new ResponseEntity<Product>(productService.create(product), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Product product) {
		Optional<Product> updateProduct = productService.getById(product.getId());
		Map<String, String> error = new HashMap<>();
		error.put("Error", "Item não encontrado");
		error.put("Code", "404");
		if (updateProduct.isPresent()) {
			return new ResponseEntity<Product>(productService.update(updateProduct.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
	}

	

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Product> delById = productService.getById(id);
		Map<String, String> error = new HashMap<>();
		error.put("Error", "Item não encontrado");
		error.put("Code", "404");
		if (!delById.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			productService.delete(delById.get().getId());
			return new ResponseEntity<>(error, HttpStatus.OK);
		}
	}

}
