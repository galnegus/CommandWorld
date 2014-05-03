package kth.inda13.commandWorld.logic;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import kth.inda13.commandWorld.data.Info;
import kth.inda13.commandWorld.data.Location;
import kth.inda13.commandWorld.data.Size;
import kth.inda13.commandWorld.data.Word;

/**
 * An Object that stores an Info object, which contains all the information 
 * necessary to draw the object on the screen.
 * 
 * @author rodrigo
 *
 */
public class Entity {
	private final Info info;
	
	/**
	 * The constructor of the class. 
	 * It creates an empty info object which can be modified later on.
	 */
	public Entity(){
		info = new Info();
	}
	
	/**
	 * Modifies the values in the info field of this Entity to make them match
	 * the non-null values of a Word's info field. 
	 * 
	 * @param word how to alter the entity.
	 */
	public void modify(Word word){
		Info how = word.getInfo();
		
		if(how.color != null)
			info.color = how.color;
		if(how.size != null)
			info.size = how.size;
		if(how.location != null)
			info.location = how.location;
		if(how.image != null)
			info.image = how.image;
	}
	
	/**
	 * Returns an all the information about this Entity
	 * 
	 * @return info all the Entity's information.
	 */
	public Info getInfo(){
		return this.info;
	}
	
}
