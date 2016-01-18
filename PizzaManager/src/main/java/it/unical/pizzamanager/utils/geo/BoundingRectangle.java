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
		return Math.toDegrees(minLatitude);
	}

	public double getMaxLatitude() {
		return Math.toDegrees(maxLatitude);
	}

	public double getMinLongitude() {
		return Math.toDegrees(minLongitude);
	}

	public double getMaxLongitude() {
		return Math.toDegrees(maxLongitude);
	}

	public double getMinLatitudeInRad() {
		return minLatitude;
	}

	public double getMaxLatitudeInRad() {
		return maxLatitude;
	}

	public double getMinLongitudeInRad() {
		return minLongitude;
	}

	public double getMaxLongitudeInRad() {
		return maxLongitude;
	}

	@Override
	public String toString() {
		return "BoundingRectangle [minLatitude=" + minLatitude + ", maxLatitude=" + maxLatitude
				+ ", minLongitude=" + minLongitude + ", maxLongitude=" + maxLongitude + "]";
	}
}
