package kth.inda13.commandWorld.logic;

import java.util.List;

import kth.inda13.commandWorld.data.Info;
import kth.inda13.commandWorld.data.Word;

/**
 * An Object that stores an Info object, which contains all the information necessary to draw the object on the screen.
 * 
 * @author rodrigo
 * 
 */
public class Entity {
	private Word word;
	private final Info info;

	/**
	 * The constructor of the class.
	 */
	public Entity(Word word) {
		this.word = word;
		Info wordInfo = word.getInfo();
		this.info = new Info(wordInfo.color, wordInfo.size, wordInfo.location, wordInfo.image);
	}

	/**
	 * Returns an all the information about this Entity
	 * 
	 * @return info all the Entity's information.
	 */
	public Info getInfo() {
		return this.info;
	}

	/**
	 * matchesDescriptions checks if this entity matches all given descriptions. <br />
	 * <br />
	 * For example, if this entity's info object is a superset of Word.RED's info object and a superset of Word.LEFT's
	 * info object, it would match the following descriptions: (Word.RED, Word.LEFT).
	 * 
	 * @param descriptions a list of words
	 * @return true if it matches, false if there isn't
	 */
	public boolean matchesDescriptions(List<Word> descriptions) {
		for (Word word : descriptions) {
			if (!info.contains(word.getInfo())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * getWord returns the word that this entity represents. This is needed, otherwise it won't be possible to
	 * differentiate between different entities.
	 * 
	 * @return the word that created this entity
	 */
	public Word getWord() {
		return word;
	}

}
