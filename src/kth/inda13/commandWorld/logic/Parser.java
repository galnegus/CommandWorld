package kth.inda13.commandWorld.logic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

import kth.inda13.commandWorld.data.Word;


/**
 * An object that parses Aeiol sentences and performs actions on a given
 * World object.
 * 
 * @author rodrigo
 *
 */
public class Parser {


	final private World world;
	final private HashMap<String, Word> stringToWord;

	public Parser(World world){
		this.world= world;

		//World interaction
		stringToWord = new HashMap<String, Word>();
		for(Word w : Word.values()) 
			stringToWord.put(w.toString().toLowerCase(), w);
	}

	/**
	 * Parses Aeiol strings in order to determine Agent, Event and Intent.
	 * In a future it will also modify the World after the inpu sentence.
	 * 
	 * @param input the sentence sent
	 * @return output an answer to the sentence sent
	 */
	public String Parse(String input){
		//Variable declaration
		String output;
		int NUM = 3;

		@SuppressWarnings("unchecked")
		LinkedList<Word>[] words = new LinkedList[NUM];// [0] agents  [1] events [2] intents

		//Initialize description
		for (int i = 0; i < NUM; i++) {
			 words[i]= new LinkedList<Word>();	
		}

		//Divide string into words. Identify tags and store the words in their respective lists.
		StringTokenizer st = new StringTokenizer(input.toLowerCase());
		int index = -1;
		Boolean valid = false; // Invariant: true as long all words exist in Word and the begins with a tag
		String token;

		while(st.hasMoreTokens()){
			token = st.nextToken();

			if(token.matches("[a|e|i]")){//Token is a tag
				valid = true; // Sentence beging with a tag.
				//Find out which one is the new tag
				switch(token){
				case "a": {index = 0; break;}
				case "e": {index = 1; break;}
				case "i": {index = 2; break;}
				}
			}else if(valid){//Token is a word
				//Check if still valid
				valid = stringToWord.containsKey(token);
				//If not valid interrupt parsing
				if(!valid) break;
				//Store information in  the proper list
				words[index].push(stringToWord.get(token));
			}
		}
		//If valid send parsed input to world. Otherwise return "invalid sentence".
		if(valid){
			output = String.format("Agent: %s, Event: %s, Intent: %s", words[0].peek(), words[1].peek(), words[2].peek()) ;
			if(!words[1].isEmpty()) world.sentence(words[0], words[1], words[2]);
		}else{
			output = "Invalid sentence.";
		}
		return output;
	}
}