package com.sergio.app.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sergio.app.item.models.Item;
import com.sergio.app.commons.models.entities.Product;
import com.sergio.app.item.service.ItemService;

@RefreshScope //permite actualizar componentes,clases con anotaciones
@RestController
// @RequestMapping("v1/items")
public class ItemController {
	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	@Value("${configuracion.texto}")
	private String text;
	
	
	@GetMapping
	public List<Item> getAll() {
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/{id}/quantity/{quantity}")
	public Item get(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}
	
	public Item metodoAlternativo(Long id, Integer quantity) {
		Item item = new Item();
		Product product = new Product();
		item.setQuantity(quantity);
		product.setId(id);
		product.setName("Camara Sony");
		product.setPrice(500.00);
		item.setProduct(product);
		return item;
	}
	
	 @GetMapping("/get-config")
	 public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
		 log.info(text);
		 
		 Map<String, String> json = new HashMap<>();
		 json.put("text", text);
		 json.put("port", port);
		 
		 if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			 json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			 json.put("autor.email", env.getProperty("configuracion.autor.email"));
		 }
		 
		 return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	 }
	 
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	 public Product create(@RequestBody Product product) {
		 return itemService.save(product);
	 }
	 
	 @PutMapping("/{id}")
	 @ResponseStatus(HttpStatus.CREATED)
	 public Product edit(@RequestBody Product product, @PathVariable Long id) {
		 return itemService.update(product, id);
	 }
	 
	 @DeleteMapping("/{id}")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	 public void delete(@PathVariable Long id) {
		 itemService.delete(id);
	 }
}
