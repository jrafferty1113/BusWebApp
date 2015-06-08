package models.path;

import play.Play;

public class GeoUtil {
	private GeoUtil() {}
	
	public static final double walkSpeed = Play.application().configuration().getDouble("geo.walkspeed"); // m/s
	public static final double busSpeed = Play.application().configuration().getDouble("geo.busspeed"); // m/s
	public static final double walkTime = Play.application().configuration().getDouble("geo.walktime");
	
	public static int getTimeInSecondByWalk(double meters) {
		return (int) (meters/walkSpeed);
	}
	
	public static int getTimeInSecondByBus(double meters) {
		return (int) (meters/busSpeed);
	}
	
	public static double getDistanceInMeters(double lat1, double lon1, double lat2, double lon2) {
		double rlat1 = toRadians(lat1);
//		double rlon1 = toRadians(lon1);
		double rlat2 = toRadians(lat2);
//		double rlon2 = toRadians(lon2);
		
		double R = 6371000; // meters
		
		double dlat = toRadians(lat2-lat1);
		double dlon = toRadians(lon2-lon1);
		
		double a = Math.sin(dlat/2) * Math.sin(dlat/2) + Math.cos(rlat1) * Math.cos(rlat2) * Math.sin(dlon/2) * Math.sin(dlon/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return R * c;
	}
	
	public static double toRadians(double degree) {
		return Math.PI * degree/180.0;
	}
	
	public static double getWalkDistance() {
		return walkTime * walkSpeed;
	}
}
