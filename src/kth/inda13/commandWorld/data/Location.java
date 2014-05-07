package kth.inda13.commandWorld.data;

public class Location extends BidimensionalConstruct {

	public Location(double x, double y) {
		super(x, y);
	}
	
	public Location(Location location) {
		super(location.x, location.y);
	}

}
