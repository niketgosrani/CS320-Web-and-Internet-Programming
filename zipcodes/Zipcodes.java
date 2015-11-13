package zipcodes;

public class Zipcodes {
	

	private double zip ; 
	private double lat ;
	private double lon ; 
	private String city;
	private String state ;
	private double distance ; 

	public Zipcodes (Double zip , Double Lat , Double Lon , String City , String State , double distance){
	
		this.zip = zip ; 
		this.lat = Lat ; 
		this.lon = Lon ; 
		this.city = City ;
		this.state = State ;
		this.distance= distance;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public double getZip() {
		return zip;
	}

	public void setZip(double zip) {
		this.zip = zip;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public double getDistanceBetweenPoints(double latitude1, double longitude1,
			double latitude2, double longitude2) {
		double theta = longitude1 - longitude2;
		double distance = (Math.sin(deg2rad(latitude1)) * Math
				.sin(deg2rad(latitude2)))
				+ (Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(latitude2)) * Math
						.cos(deg2rad(theta)));
		distance = Math.acos(distance);
		distance = rad2deg(distance);
		distance = distance * 60 * 1.1515;
		
		return (Math.round(distance));
	}

	private double deg2rad(double deg) {

		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
}
