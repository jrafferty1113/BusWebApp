package models.path;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import models.bus.Stop;

public class StopNode {
	private Stop stop;
	/*
	 * Route stops stores a list of bus stops within the same route
	 * that we can get to from this node
	 * Integer value is how many seconds it take to go from current
	 * location to the neighbor location
	 */
	private Map<StopNode, Integer> routeStops;
	private Map<StopNode, Integer> walkStops;
	
	private void init() {
		routeStops = new HashMap<StopNode, Integer>();
		walkStops = new HashMap<StopNode, Integer>();
	}
	
	public StopNode(double lon, double lat) {
		stop = new Stop(lon, lat);
		init();
	}
	
	public StopNode(Stop s) {
		stop = s;
		init();
	}
	
	public double getLongitude() {
		return stop.getLongitude();
	}
	
	public double getLatitude() {
		return stop.getLatitude();
	}
	
	public void addRouteStop(StopNode n) {
		if (n == null) return;
		if (routeStops.containsKey(n)) return;
		
		double distance = GeoUtil.getDistanceInMeters(n.getLatitude(), n.getLongitude(), stop.getLatitude(), stop.getLongitude());
		routeStops.put(n, GeoUtil.getTimeInSecondByBus(distance));
	}
	
	public void addWalkStop(Set<StopNode> s) {
		if (s == null || s.isEmpty()) return;
		for (StopNode n: s) {
			addWalkStop(n);
		}
	}
	
	public void addWalkStop(StopNode n) {
		if (n == this || n == null) return;
		if (walkStops.containsKey(n)) return;
		
		double distance = GeoUtil.getDistanceInMeters(n.getLatitude(), n.getLongitude(), stop.getLatitude(), stop.getLongitude());
		walkStops.put(n, GeoUtil.getTimeInSecondByWalk(distance));
	}
	
}
