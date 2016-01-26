package it.unical.pizzamanager.utils.geo;

import it.unical.pizzamanager.persistence.entities.Location;

public class Geolocalization {
	private static final double RADIUS_OF_EARTH = 6371.0072;

	public static BoundingRectangle getBoundingRectangle(Location center, double radiusInKm) {
		double angularRadius = radiusInKm / RADIUS_OF_EARTH;

		double minLatitude = center.getLatitudeInRad() - angularRadius;
		double maxLatitude = center.getLatitudeInRad() + angularRadius;

		double dLongitude = Math
				.asin(Math.sin(angularRadius) / Math.cos(center.getLatitudeInRad()));

		double minLongitude = center.getLongitudeInRad() - dLongitude;
		double maxLongitude = center.getLongitudeInRad() + dLongitude;

		return new BoundingRectangle(minLatitude, maxLatitude, minLongitude, maxLongitude);
	}

	public static double greatCircleDistance(Location start, Location end) {
		return Math
				.acos(Math.sin(start.getLatitudeInRad()) * Math.sin(end.getLatitudeInRad())
						+ Math.cos(start.getLatitudeInRad()) * Math.cos(end.getLatitudeInRad())
								* Math.cos(start.getLongitudeInRad() - end.getLongitudeInRad()))
				* RADIUS_OF_EARTH;
	}

	public static void main(String[] args) {
		System.out.println(greatCircleDistance(new Location(39.3190698, 16.3124703999999),
				new Location(45.475732, 9.1378105)));
	}
}
