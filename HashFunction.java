package dataStructures;

import java.util.Scanner;

public class HashFunction {

	WordList[] theArray;

	int arraySize;

	public String[][] elementsToAdd = { { "ace", "Very good" }, { "act", "Take action" },
			{ "add", "Join (something) to something else" }, { "age", "Grow old" }, { "ago", "Before the present" },
			{ "aid", "Help, assist, or support" }, { "aim", "Point or direct" },
			{ "air", "Invisible gaseous substance" }, { "all", "Used to refer to the whole quantity" },
			{ "amp", "Unit of measure for the strength of an electrical current" }, { "and", "Used to connect words" },
			{ "ant", "A small insect" }, { "any", "Used to refer to one or some of a thing" },
			{ "ape", "A large primate" }, { "apt", "Appropriate or suitable in the circumstances" },
			{ "arc", "A part of the circumference of a curve" },
			{ "are", "Unit of measure, equal to 100 square meters" }, { "ark", "The ship built by Noah" },
			{ "arm", "Two upper limbs of the human body" },
			{ "art", "Expression or application of human creative skill" },
			{ "ash", "Powdery residue left after the burning" },
			{ "ask", "Say something in order to obtain information" }, { "asp", "Small southern European viper" },
			{ "ass", "Hoofed mammal" }, { "ate", "To put (food) into the mouth and swallow it" },
			{ "atm", "Unit of pressure" }, { "awe", "A feeling of reverential respect" },
			{ "axe", "Edge tool with a heavy bladed head" }, { "aye", "An affirmative answer" } };

	public HashFunction(int size) {
		// uses separete chaining for collision resolution
		// arrays inside of arrays
		arraySize = size;
		theArray = new WordList[size];

		for (int i = 0; i < arraySize; i++) {
			theArray[i] = new WordList();
		}

		addTheArray(elementsToAdd);

	}

	public void insert(Word newWord) {

		String wordToHash = newWord.theWord;
		int hashKey = stringHashFunction(wordToHash);
		theArray[hashKey].insert(newWord, hashKey);

	}

	public void addTheArray(String[][] elementsToAdd) {
		for (int i = 0; i < elementsToAdd.length; i++) {

			String word = elementsToAdd[i][0];
			String definition = elementsToAdd[i][1];

			Word newWord = new Word(word, definition);
			insert(newWord);
		}

	}

	public Word find(String wordToFind) {

		int hashKey = stringHashFunction(wordToFind);

		Word theWord = theArray[hashKey].find(hashKey, wordToFind);

		return theWord;
	}

	public int stringHashFunction(String wordToHash) {

		int hashKeyValue = 0;

		for (int i = 0; i < wordToHash.length(); i++) {

			int charCode = wordToHash.charAt(i) - 96;

			int hKVTemp = hashKeyValue;

			hashKeyValue = (hashKeyValue * 27 + charCode) % arraySize;

			System.out.println("Hash key value " + hKVTemp + " * 27 + Character Code " + charCode + " % arraySize "
					+ arraySize + " = " + hashKeyValue);

		}

		System.out.println();

		return hashKeyValue;
	}

	public void displayTheArray() {

		for (int i = 0; i < arraySize; i++) {

			System.out.println("The Array Index " + i);
			theArray[i].displayWordList();
		}

	}

	class Word {

		public String theWord;
		public String definition;

		public int key;
		
		public Word next;

		public Word(String theWord, String definition) {

			this.theWord = theWord;
			this.definition = definition;
		}

		public String toString() {
			return theWord + " : " + definition;
		}

	}

	class WordList {

		public Word firstWord = null;

		public void insert(Word newWord, int hashKey) {

			Word previous = null;
			Word current = firstWord;

			newWord.key = hashKey;

			while (current != null && newWord.key > current.key) {

				previous = current;
				current = current.next;

			}

			if (previous == null) {
				firstWord = newWord;
			} else {
				previous.next = newWord;
			}

			newWord.next = previous;
		}

		public void displayWordList() {

			Word current = firstWord;

			while (current != null) {
				System.out.println(current);
				current = current.next;
			}

		}

		public Word find(int hashKey, String wordToFind) {

			Word current = firstWord;

			while (current != null && current.key <= hashKey) {
				
				if (current.theWord.equals(wordToFind)) {
					return current;
				}

				current = current.next;
			}

			return null;
		}

	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		HashFunction wordHashTable = new HashFunction(11);
		String wordLookUp = "a";
		
		while (!wordLookUp.equalsIgnoreCase("x")) {
			
			System.out.println("\nChoose one of these words please -"
					+ " \nace, act, add, age, ago, aid, aim, air, all, "
					+ "\namp,and, any, ape, apt, arc, are, art, "
					+"\nash, ask, asp, ass, ate, atm, awe, axe, aye : ");
			System.out.println("\nEnter : ");
			wordLookUp = input.nextLine();
			System.out.println(wordHashTable.find(wordLookUp));
		}
		
		wordHashTable.displayTheArray();
		
	}

}
