
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);

	}
	/**
	 * returns the given String without the first letter in it
	 * if given empty string, returns and empty string
	 */
	public static String tail(String str) {
		if (str.length() == 1){
			return "";
		}
		return str.substring(1,str.length());
	}

	/**
	 * calculate the minimum number of single character edits needed to transform one word to the other word.
	 * returns it in an integer value.
	 */
	public static int levenshtein(String word1, String word2) {
		int sum = 0;
		if (sum == 0){ //the words are needed to be lowercase for spellchecking
			word1=word1.toLowerCase();
			word2=word2.toLowerCase();	
		}
		if (word2.length() == 0){
			return word1.length();
		}
		else if (word1.length() == 0){
			return word2.length();
		}
		else if (word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));
		}
		else{
			sum++;
			return sum += Math.min(Math.min(levenshtein(tail(word1),word2),
			 	levenshtein(word1,tail(word2))), 
			 	levenshtein(tail(word1),tail(word2)));
		}
	}	

	/**
	 *Reads the dictonary.txt file and organizes the words in an array
	 */
	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for(int i = 0; i < dictionary.length;i++){
			dictionary[i] = in.readString();
		}

		return dictionary;
	}
	/**
	 * this function recieves a word, a threshold value for distance and a dictionary
	 * It returns the word from the dictionary that most closely resembles the given word.
	 * It uses the levenshtein function to determines the similarities
	 * if the given word has a similar word in the dictionary that distance is bigger than the given threshold
	 * it will return the original word.
	 */
	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String closestWord = word;
		int distance = 1;
		int closestDistance = 100;
		for (int i =0;i < dictionary.length;i++){
			distance = levenshtein(word, dictionary[i]);
			if (distance < closestDistance ){
				closestDistance = distance;
				if (closestDistance <= threshold){
					closestWord = dictionary[i];
				}
			}
			if (closestDistance == 0){ // the word appears in the dictionary
				break;
			}
		}
		return closestWord;
	}

}
