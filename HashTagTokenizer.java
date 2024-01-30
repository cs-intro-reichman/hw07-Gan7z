

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		hashTag.toLowerCase();
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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
	 * if the given word appears in the dictonary return true, otherwise false
	 * */
	public static boolean existInDictionary(String word, String []dictionary) {
		for(int i = 0; i < dictionary.length;i++){
			if (dictionary[i].equals(word)){
				return true;
			}
		}
		return false;
	}
	/**
	 * This function receives two inputs: hastag (as String) and a dictonary (array of Strings)
	 * using the dictonary it seperates the connected hashtag String to seperate words that appear in the dictonary
	 * 
	 */
	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		/* checks all the characters of the given string, if it found a word that appears in the dictionary
		*  recursively sends the remaining substring to the same function, untill it has reached the end of the word
		*/
        for (int i = 1; i <= N; i++) { 
			
			if (existInDictionary(hashtag.substring(0, i), dictionary) == true){
				System.out.println(hashtag.substring(0, i));
				breakHashTag(hashtag.substring(i, N), dictionary); 
				return;
			}
			
        }
    }

}
