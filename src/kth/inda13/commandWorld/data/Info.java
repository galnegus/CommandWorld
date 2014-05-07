package kth.inda13.commandWorld.data;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * A class that stores all the information about an Entity
 * 
 * @author rodrigo
 * 
 */
public class Info {
	public Color color;
	public Size size;
	public Location location;
	public Image image;

	public Info(Color color, Size size, Location location, Image image) {
		this.color = color;
		this.size = size;
		this.location = location;
		this.image = image;
	}

	/**
	 * Returns true if otherInfo is a subset of info. <br />
	 * All non-null fields of otherInfo must reference the same objects as this Info for otherInfo to be considered a subset.
	 * 
	 * @param otherInfo
	 *            the other info object that is checked
	 * @return true if otherInfo if a subset of Info, false if it isn't.
	 */
	public boolean contains(Info otherInfo) {
		if ((otherInfo.color != null && otherInfo.color != color) || 
			(otherInfo.size != null && otherInfo.size != size) || 
			(otherInfo.location != null && otherInfo.location != location) || 
			(otherInfo.image != null) && otherInfo.image != image) {
			return false;
		}
		return true;
	}

}
