package com.multithread.completablefuture.model;

public class AllData {

	Person person;
	Product product;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "AllData [person=" + person + ", product=" + product + "]";
	}
	
}
