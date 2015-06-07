package models.path;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.Set;

import models.bus.Stop;
import models.bus.Direction;

public class StopGraph {
	protected TreeSet<StopNode> ts;

	public StopGraph(Set<Direction> directions) {
		if (directions == null || directions.isEmpty()) throw new IllegalArgumentException();
		ts = new TreeSet<StopNode>(new CordinateGradientComparator());
		
		for (Direction d: directions) {
			addNewDirection(d);
		}
		
		computeWalkingDistanceNeighbors();
	}
	
	public void addNewDirection(Direction d) {
		// Stop A in Direction 1's Node is different 
		// from same Stop's Node in a different direction.
		// Multiple StopNode could share the same stop.
				
		if (d == null) return;
		
		List<Stop> list = d.getStops();
		
		int i = 0;
		StopNode prev = null;
		
		// Add the edges for bus routes
		while (i < list.size()) {
			if (prev == null) {
				prev = new StopNode(list.get(i));
				i++;
				continue;
			}
			
			StopNode curr = new StopNode(list.get(i));
			prev.addRouteStop(curr);
			ts.add(curr);
			prev = curr;
			i++;
		}
	}
	
	public void computeWalkingDistanceNeighbors() {
		for (StopNode n: ts) {
			Set<StopNode> withinRange = ts.subSet(
					new StopNode(n.getLongitude(), n.getLatitude() - GeoUtil.getWalkDistance()), true,
					new StopNode(n.getLongitude(), n.getLatitude() + GeoUtil.getWalkDistance()), true);
			n.addWalkStop(withinRange);
		}
		
	}
	
	public Set<StopNode> getStopsWithinWalkingDistance(double lon, double lat) {
		return getStopsWithinWalkingDistance(new StopNode(lon, lat));
	}
	
	public Set<StopNode> getStopsWithinWalkingDistance(StopNode n) {
		if (n == null) return null;
		Set<StopNode> withinRange = ts.subSet(
				new StopNode(n.getLongitude(), n.getLatitude() - GeoUtil.getWalkDistance()), true,
				new StopNode(n.getLongitude(), n.getLatitude() + GeoUtil.getWalkDistance()), true);
		return withinRange;
	}
	
	public class CordinateGradientComparator implements Comparator<StopNode> {
		
		@Override
		public int compare(StopNode o1, StopNode o2) {
			return (int) (o1.getLatitude() * o1.getLatitude() + o1.getLongitude() * o1.getLongitude()
					- o2.getLatitude() * o2.getLatitude() - o2.getLongitude() - o2.getLongitude());
		}
	}
}
