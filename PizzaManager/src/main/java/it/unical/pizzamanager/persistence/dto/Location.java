package it.unical.pizzamanager.persistence.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.unical.pizzamanager.persistence.dao.DatabaseHandler;

@Entity
@Table(name = "addresses")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@OneToOne(mappedBy = "location", fetch = FetchType.LAZY)
	private Account account;

	public Location() {
		this.id = DatabaseHandler.NO_ID;
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.account = null;
	}

	public Location(Double latitude, Double longitude) {
		this.id = DatabaseHandler.NO_ID;
		this.longitude = latitude;
		this.latitude = longitude;
		this.account = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitudeInRad() {
		return Math.toRadians(latitude);
	}

	public Double getLongitudeInRad() {
		return Math.toRadians(longitude);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}