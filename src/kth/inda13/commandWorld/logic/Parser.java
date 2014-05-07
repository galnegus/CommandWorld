package kth.inda13.commandWorld.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;


/**
 * An object that parses Aeiol sentences and performs actions on a given
 * World object.
 * 
 * @author rodrigo
 *
 */
public class Parser {
	

	World world;
	
	public Parser(World world){
		this.world= world;
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
		LinkedList<String>[] description = new LinkedList[NUM];// [0] agents  [1] events [2] intents
		
		//Initialize description
		for (int i = 0; i < NUM; i++) {
			 description[i]= new LinkedList<String>();	
		}
		
		//Divide string into words. Identify tags and store the words in their respective lists.
		StringTokenizer st = new StringTokenizer(input);
		int index = -1;
		
		String token;
		
		while(st.hasMoreTokens()){
			token = st.nextToken();
			
			if(token.matches("[a|e|i]")){//Token is a tag
				//Store last word of array as the main word of the previous tag
				System.out.println(index);
				if(index!=-1) mainWord[index] = description[index].pop();
				
				//Find out which one is the new tag
				switch(token){
				case "a": {index = 0; break;}
				case "e": {index = 1; break;}
				case "i": {index = 2; break;}
				}
			}else{//Token is a word
				description[index].add(token);
			}
		}
		//Store last word of array as the main word of the last tag
		if(index!=-1) mainWord[index] = description[index].pop();
		
		//Temporal output to test parsing
		output = String.format("Agent: %s, Event: %s, Intent: %s", mainWord[0], mainWord[1], mainWord[2]) ;
		
		return output;
		
	}
}
