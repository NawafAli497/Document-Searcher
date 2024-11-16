import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class dataReader {
	
	public static ArrayList<String> stopWords;
	public static ArrayList<LinkedList<String>> documents;
	public static BST<String> inverted_index;
	public static void ReadFiles() { // this method is O(S + D*M*log(S))
		long start = System.nanoTime();
		documents = new ArrayList<LinkedList<String>>(countNumberOfDocuments());
		stopWords = new ArrayList<String>(countNumberOfStopWords()); // could be changed to hashMap
		inverted_index= new BST<String>();
		String line;
		try (BufferedReader bc = new BufferedReader(new FileReader("src/resources/stop.txt"))) {
			while ((line = bc.readLine()) != null) { // O(S)
				stopWords.insert(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		try (BufferedReader br = new BufferedReader(new FileReader("src/resources/dataset.csv"))) {
			br.readLine();
			while ((line = br.readLine()) != null) { // O(D)
				if (line.equals(",,"))
					break;

				String[] tmp = wordsSeperator(line);

				documents.insert(noStopWordsList(tmp)); // O(D*M*log(S))

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(System.nanoTime() - start);

	}


	private static boolean binarySearchStopWords(String word) { // this method checks if the given string is in the stop word list, it is O(log(S)) where S is the number of Stop words Note: every line in the stop words file contains one stop word

		int left = 0;
		int right = stopWords.length() - 1;

		while (left <= right) {
			int mid = (right + left) / 2;

			stopWords.findIndex(mid);

			int compare = stopWords.retrieve().compareTo(word);

			if (compare == 0)
				return true;
			else if (compare < 0)
				left = mid + 1;
			else
				right = mid - 1;

		}
		return false;
	}

	private static String[] wordsSeperator(String lineOfTexts) { // this method removes the removes every non alphaNumeric and ' in the String, it is O(1)
		
		String formatedText = lineOfTexts.substring(lineOfTexts.indexOf(",") + 1 , lineOfTexts.length()-1).replaceAll("[^a-zA-Z0-9\\s']", "").toLowerCase();
		String[] tmp = formatedText.split(" ");
		return tmp;
		
	}	
	
	private static LinkedList<String> noStopWordsList(String[] texts) { // this method inserts the elements from the given string array in the LinkedList and returns it , it is O(M*log(S)) where M is the number of strings in the array
		LinkedList<String> tmp = new LinkedList<>();
		
		for(int i = 0 ; i < texts.length ; i++) 
			if(!binarySearchStopWords(texts[i]))
				tmp.insert(texts[i]);		

		return tmp;
		
	}
	
	//this method returns a Linked priority queue 
	 // the data is the Documents ID and the priority is the number of times that each word is in the Document(this only takes one word)
 

     private static int countNumberOfStopWords() {
 		
 		BufferedReader br = null;
 		int counter = 0;
 		
 		try {
 			
 			br = new BufferedReader(new FileReader("src/resources/stop.txt"));
 			String line;
 			while (br.readLine() != null) {
 				counter++;
 			}
 			
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		} finally {
 			
 			try {
 				br.close();
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			
 		}

 		return counter;
 	}

 	private static int countNumberOfDocuments() {
 		BufferedReader br = null;
 		int counter = 0;
 		
 		try {
 			
 			br = new BufferedReader(new FileReader("src/resources/stop.txt"));
 			String line;
 			while ((line = br.readLine()) != null) {
 				if (line.equals(",,"))
 					break;
 				counter++;
 			}
 			
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		} finally {
 			
 			try {
 				br.close();
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			
 		}

 		return counter;

 	}
 
 
 
 
}
