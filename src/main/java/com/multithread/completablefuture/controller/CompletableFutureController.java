package com.multithread.completablefuture.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multithread.completablefuture.model.AllData;
import com.multithread.completablefuture.model.Person;
import com.multithread.completablefuture.model.Product;
import com.multithread.completablefuture.service.CompletableFutureService;

@RestController
public class CompletableFutureController {
	
	private static Logger log = LoggerFactory.getLogger(CompletableFutureController.class);
	
	@Autowired
	CompletableFutureService completableFutureService;
	
	@GetMapping("/result")
	public AllData getResult() throws InterruptedException, ExecutionException {
		log.info("getResult Start");
		
		CompletableFuture<Person> person = completableFutureService.getPersons();
		CompletableFuture<Product> product = completableFutureService.getProducts();
		
		CompletableFuture<String> combineResult = person.thenCombine(product, (s1,s2)->"Person name : "+s1.getFirstName()+" : "+" Product Name : "+s2.getName());
		
		log.info("Combined Result : "+combineResult.get());
		
		log.info("Person Details --- " + person.get());
		log.info("Product Details --- " + product.get());
		
		AllData allData = new AllData();
		allData.setPerson(person.get());
		allData.setProduct(product.get());
		return allData;
		
	}

}
