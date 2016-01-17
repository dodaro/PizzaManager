package it.unical.pizzamanager.forms;

public class NearbyPizzeriasForm {

	private Double latitude;
	private Double longitude;
	private Double radius;

	public NearbyPizzeriasForm() {
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.radius = 0.0;
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

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}
}
