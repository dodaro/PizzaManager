package it.unical.pizzamanager.utils.geo;

public class BoundingRectangle {

	private double minLatitude;
	private double maxLatitude;
	private double minLongitude;
	private double maxLongitude;

	BoundingRectangle(double minLatitude, double maxLatitude, double minLongitude,
			double maxLongitude) {
		this.minLatitude = minLatitude;
		this.maxLatitude = maxLatitude;
		this.minLongitude = minLongitude;
		this.maxLongitude = maxLongitude;
	}

	public double getMinLatitude() {
		return minLatitude;
	}

	public double getMaxLatitude() {
		return maxLatitude;
	}

	public double getMinLongitude() {
		return minLongitude;
	}

	public double getMaxLongitude() {
		return maxLongitude;
	}

	public double getMinLatitudeInRad() {
		return Math.toRadians(minLatitude);
	}

	public double getMaxLatitudeInRad() {
		return Math.toRadians(maxLatitude);
	}

	public double getMinLongitudeInRad() {
		return Math.toRadians(minLongitude);
	}

	public double getMaxLongitudeInRad() {
		return Math.toRadians(maxLongitude);
	}

	@Override
	public String toString() {
		return "BoundingRectangle [minLatitude=" + minLatitude + ", maxLatitude=" + maxLatitude
				+ ", minLongitude=" + minLongitude + ", maxLongitude=" + maxLongitude + "]";
	}
}
