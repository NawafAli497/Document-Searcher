import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class dataReader {
	
	public static int tokens;
	public static int vocab;
	public static ArrayList<String> stopWords;
	public static ArrayList<LinkedList<String>> documents;
	public static MyBST<LinkedListobj> BSTinverted_index;
	public static LinkedList<LinkedListobj> linkedInverted_index;
	public static hashMap<String, LinkedListobj> invertedIndexHash;

	public static void ReadFiles() { // this method is O(S + D*M*log(S))

		documents = new ArrayList<LinkedList<String>>(countNumberOfDocuments());
		stopWords = new ArrayList<String>(countNumberOfStopWords()); // could be changed to hashMap
		BSTinverted_index = new MyBST<LinkedListobj>();
		linkedInverted_index = new LinkedList<LinkedListobj>();

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

				LinkedList<String> tmpList = noStopWordsList(wordsSeperator(line));
				tmpList.findFirst();
				for (int i = 0; i < tmpList.length(); i++) {

					tmpList.update(tmpList.retrieve().replace("'", ""));
					tmpList.findNext();
				}
				documents.insert(tmpList); // O(D*M*log(S))

			}
			
			setToken();
			linkedSetInvertedIndex();
			vocab = linkedInverted_index.length();
			setInvertedIndexHash(vocab);
			BSTsetInvertedIndex();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	private static boolean binarySearchStopWords(String word) { // this method checks if the given string is in the stop
																// word list, it is O(log(S)) where S is the number of
																// Stop words Note: every line in the stop words file
																// contains one stop word

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

	private static String[] wordsSeperator(String lineOfTexts) {
//      
		String formatedText = lineOfTexts.substring(lineOfTexts.indexOf(",") + 1, lineOfTexts.length() - 1)
				.replaceAll("[^'\\p{L}0-9\\s-_]", "").toLowerCase().trim();
//                         
		return formatedText.split("\\s+");
	}

	private static LinkedList<String> noStopWordsList(String[] texts) { 
		
		LinkedList<String> tmp = new LinkedList<>();

		for (int i = 0; i < texts.length; i++)
			if (!binarySearchStopWords(texts[i]))
				tmp.insert(texts[i]);

		return tmp;

	}

	// this method returns a Linked priority queue
	// the data is the Documents ID and the priority is the number of times that
	// each word is in the Document(this only takes one word)

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

			br = new BufferedReader(new FileReader("src/resources/dataset.csv"));
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

	/*
	 * private static void BSTsetInvertedIndex() { documents.findFirst();
	 * 
	 * for(int i = 0 ; i < documents.length(); i++) {
	 * 
	 * documents.retrieve().findFirst(); for(int j = 0 ; j
	 * <documents.retrieve().length() ; j++) {
	 * if(BSTinverted_index.findkey(documents.retrieve().retrieve())) {
	 * 
	 * BSTinverted_index.retrieve().insertDoc(i); //if i find the word i will call
	 * insert to doc which will check if the index is there or not if its there it
	 * will +1 } else { BSTobj b= new BSTobj(i);
	 * BSTinverted_index.insert(documents.retrieve().retrieve() , b); }
	 * documents.retrieve().findNext(); }
	 * 
	 * documents.findNext();
	 * 
	 * } }
	 */

	private static void BSTsetInvertedIndex() {

		documents.findFirst();

		for (int i = 0; i < documents.length(); i++) {

			documents.retrieve().findFirst();
			for (int j = 0; j < documents.retrieve().length(); j++) {
					
				if (!BSTinverted_index.findkey(documents.retrieve().retrieve())) {
					LinkedListobj tmp = new LinkedListobj(documents.retrieve().retrieve(), i);
					BSTinverted_index.insert(documents.retrieve().retrieve(), tmp);
				}

				else{
					BSTinverted_index.retrieve().insertDoc(i);

				}
				documents.retrieve().findNext();
			}

			documents.findNext();

		}

	}

	private static void linkedSetInvertedIndex() {
		documents.findFirst();

		for (int i = 0; i < documents.length(); i++) {

			documents.retrieve().findFirst();
			for (int j = 0; j < documents.retrieve().length(); j++) {
					
				if (!linkedListFindWord(documents.retrieve().retrieve())) {
					LinkedListobj temp = new LinkedListobj(documents.retrieve().retrieve(), i);
					linkedInverted_index.insert(temp);
				}

				else{
					linkedInverted_index.retrieve().insertDoc(i);

				}
				documents.retrieve().findNext();
			}

			documents.findNext();

		}

	}

	private static void setInvertedIndexHash(int size) {
		invertedIndexHash = new hashMap<>(size);

		documents.findFirst();

		for (int i = 0; i < documents.length(); i++) {// d
			documents.retrieve().findFirst();
			for (int j = 0; j < documents.retrieve().length(); j++) {// s

				if (invertedIndexHash.get(documents.retrieve().retrieve()) == null) {
					LinkedListobj tmp = new LinkedListobj(null, i);

					invertedIndexHash.put(documents.retrieve().retrieve(), tmp);
				} else {
					invertedIndexHash.get(documents.retrieve().retrieve()).insertDoc(i);
				}
				
				documents.retrieve().findNext();

			}
			documents.findNext();

		}
	}

	public static boolean linkedListFindWord(String word) {

		if (linkedInverted_index.length() == 0)
			return false;
		linkedInverted_index.findFirst();
		while (!linkedInverted_index.last()) {
			if (linkedInverted_index.retrieve().key.equalsIgnoreCase(word)) {
				return true;
			}
			linkedInverted_index.findNext();
		}
		if (linkedInverted_index.retrieve().key.equalsIgnoreCase(word)) {
			return true;
		}

		return false;

	}
	
	private static void setToken() {
		documents.findFirst();
		tokens = 0;
		
		for (int i = 0; i < documents.length(); i++) {// d
			 
			tokens+=documents.retrieve().length();
			documents.findNext();

		}
	}
}
