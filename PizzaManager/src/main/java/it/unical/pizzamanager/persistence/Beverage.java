package it.unical.pizzamanager.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beverages")
public class Beverage implements Serializable {

	/**
	 * 
	 */
	public static final String MEDIUM_SIZE="MEDIUM";
	public static final String SMALL_SIZE="SMALL";
	
	private static final long serialVersionUID = -4456395642459391534L;

	@Id
	@Column(name = "code")
	private int code;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "brand", nullable = false)
	private String brand;

	@Column(name = "containerType", nullable = false)
	private String containerType;

	@Column(name = "size", nullable = false)
	private String size;

	@Column(name = "category", nullable = false)
	private String category;

	public Beverage() {
		code = 0;
		name = "";
		brand = "";
		containerType = "";
		size = "";
		category = "";
	}

	public Beverage(int code, String name, String brand, String containerType, String size, String category) {
		this.code = code;
		this.name = name;
		this.brand = brand;
		this.containerType = containerType;
		this.size = size;
		this.category = category;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
