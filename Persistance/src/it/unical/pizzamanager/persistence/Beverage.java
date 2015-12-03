package it.unical.pizzamanager.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beverages")
public class Beverage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4456395642459391534L;

	@Id
	@Column(name ="code")
	private int code;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="brand",nullable=false)
	private String brand;
	
	public Beverage() {
		code=0;
		name="";
		brand="";
	}
	
	public Beverage(int code, String name,String brand){
		this.code=code;
		this.name=name;
		this.brand=brand;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
}
