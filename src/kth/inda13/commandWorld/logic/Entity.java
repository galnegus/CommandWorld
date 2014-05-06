package kth.inda13.commandWorld.logic;

import kth.inda13.commandWorld.data.Info;
import kth.inda13.commandWorld.data.Word;

/**
 * An Object that stores an Info object, which contains all the information 
 * necessary to draw the object on the screen.
 * 
 * @author rodrigo
 *
 */
public class Entity {
	private Word word;
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
		this.word = word;
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
	
	/**
	 * getWord returns the word that this entity represents.
	 * This is needed, otherwise it won't be possible to differentiate between different entities.
	 * 
	 * @return the word that created this entity
	 */
	public Word getWord() {
		return word;
	}
	
}
