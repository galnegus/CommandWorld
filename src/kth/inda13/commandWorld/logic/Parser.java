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
		
		String mainWord[] = new String[NUM]; // [0] agents  [1] events [2] intents
		@SuppressWarnings("unchecked")
		LinkedList<String>[] description = new LinkedList[NUM];// [0] agents  [1] events [2] intents
		
		//Initialize description
		for (int i = 0; i < NUM; i++) {
			 description[i]= new LinkedList<String>();	
		}
		
		//Divide string into words. Identify tags and store the words in their respective lists.
		StringTokenizer st = new StringTokenizer(input.toLowerCase());
		int index = -1;
		Boolean valid = true; // Invariant: true as long all words exist in Word
		String token;
		
		while(st.hasMoreTokens()){
			token = st.nextToken();
			
			if(token.matches("[a|e|i]")){//Token is a tag
				//Store last word of array as the main word of the previous tag
				if(index!=-1) mainWord[index] = description[index].pop();
				
				//Find out which one is the new tag
				switch(token){
				case "a": {index = 0; break;}
				case "e": {index = 1; break;}
				case "i": {index = 2; break;}
				}
			}else{//Token is a word
				description[index].add(token);
				if(valid) valid = stringToWord.containsKey(token);
			}
		}
		//Store last word of array as the main word of the last tag
		if(index!=-1) mainWord[index] = description[index].pop();
		
		//World interaction
		if(valid){
			//Case #1: creation. No agent, no intent.
			if(mainWord[0] == null && mainWord[2] == null){
				//sentence lacks agent and intent, create Entity
				world.add(stringToWord.get(mainWord[1]));
				//Description support
//				while(!description[1].isEmpty())
//					world.event(world.get(mainWord[1]), stringToWord.get(description[1].pop()));
			}else if(mainWord[0] == null){//Case #2: modification. No agent.
				world.event(world.get(stringToWord.get(mainWord[2])), stringToWord.get(mainWord[1]));
			}
		}
		//Temporal output to test parsing
		if(valid){
		output = String.format("Agent: %s, Event: %s, Intent: %s", mainWord[0], mainWord[1], mainWord[2]) ;
		}else{
			output = "Invalid sentence.";
		}
		return output;
	}
	
	public Word fetchWord(String s){
		
		return null;
	}
	
}
