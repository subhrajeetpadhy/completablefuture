package com.multithread.completablefuture.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.multithread.completablefuture.model.Person;
import com.multithread.completablefuture.model.Product;

@Service
public class CompletableFutureService {

	private static Logger log = LoggerFactory.getLogger(CompletableFutureService.class);
	
	RestTemplate restTemplate = new RestTemplate();
	Executor executor = Executors.newFixedThreadPool(5);
	

	public CompletableFuture<Person> getPersons() throws InterruptedException, ExecutionException {
		log.info("getPersons starts");
		CompletableFuture<Person> personCompletableFuture = CompletableFuture.supplyAsync(()->{
			Person person = restTemplate.getForObject("http://localhost:8080/persons", Person.class);
			System.out.println("getPersons - Thread Name -"+Thread.currentThread().getName());
			return person;
		}, executor);
		//Thread.sleep(1000L);	//delay
		log.info("getPersons completed");
		return personCompletableFuture;
	}
	
	public CompletableFuture<Product> getProducts() throws InterruptedException, ExecutionException {
		log.info("getProducts starts");
		CompletableFuture<Product> productCompletableFuture = CompletableFuture.supplyAsync(()->{
			Product product = restTemplate.getForObject("http://localhost:8080/products", Product.class);
			System.out.println("getProducts - Thread Name -"+Thread.currentThread().getName());
			return product;
		}, executor);
		log.info("getProducts completed");
		//Thread.sleep(1000L);	//delay
		return productCompletableFuture;
	}
	
}
