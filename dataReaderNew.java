import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class dataReaderNew {

	public static hashMap<String, Boolean> stopWords = new hashMap<>(countNumberOfStopWords()); // s
	public static ArrayList<LinkedList<String>> documents = new ArrayList<>(countNumberOfDocuments()); // d
	public static hashMap<String, Integer> invertedIndexHash = null; // b
	// public static BST<String> invertedIndex = new BST<>();

	private dataReaderNew(){} // this prevents the user from making an instance of the class since all data
		// members are static

	public static void ReadFiles() {
		long start = System.nanoTime();
		String line;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/resources/stop.txt"));

			while ((line = br.readLine()) != null) {
				stopWords.put(line, true);
			}
			br.close();

			br = new BufferedReader(new FileReader("src/resources/dataset.csv"));
			br.readLine();

			while ((line = br.readLine()) != null) {
				if (line.equals(",,"))
					break;

				LinkedList<String> tmpList = noStopWordsList(wordsSeperator(line));

				tmpList.findFirst();
				for (int i = 0; i < tmpList.length(); i++) {
					tmpList.update(tmpList.retrieve().replace("'", ""));
					tmpList.findNext();
				}

				documents.insert(tmpList);

			}
			br.close();

			int invertedIndexSize = 0;

			documents.findFirst();
			for (int i = 0; i < documents.length(); i++) {
				invertedIndexSize += documents.retrieve().length();
			}
			// setInvertedIndex();
			setInvertedIndexHash(invertedIndexSize);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		System.out.println(System.nanoTime() - start);

	}

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
	/*
	  private static boolean binarySearchStopWords(String word) { // this method
	  checks if the given string is in the stop word list, it is O(log(S)) where S
	  is the number of Stop words Note: every line in the stop words file contains
	  one stop word
	  
	  int left = 0; int right = stopWords.length() - 1;
	  
	  while (left <= right) { int mid = (right + left) / 2;
	  
	  stopWords.findIndex(mid);
	 
	  int compare = stopWords.retrieve().compareTo(word);
	 
	  if (compare == 0) return true; else if (compare < 0) left = mid + 1; else
	  right = mid - 1;
	  
	  } return false; 
	  }
	 */

	private static String[] wordsSeperator(String lineOfTexts) {

		String formatedText = lineOfTexts.substring(lineOfTexts.indexOf(",") + 1, lineOfTexts.length() - 1).replaceAll("[^'\\p{L}0-9\\s]", "").toLowerCase().trim();
		return formatedText.split("\\s+");
	}

	private static LinkedList<String> noStopWordsList(String[] texts) {
		
		LinkedList<String> tmp = new LinkedList<>();

		for (int i = 0; i < texts.length; i++)
			if (stopWords.get(texts[i]) == null)
				tmp.insert(texts[i]);

		return tmp;

	}

	/*
	 * private static void setInvertedIndex() { documents.findFirst();
	 * 
	 * for(int i = 0 ; i < documents.length(); i++) {
	 * documents.retrieve().findFirst(); for(int j = 0 ; j <
	 * documents.retrieve().length() ; j++) {
	 * invertedIndex.insert(documents.retrieve().retrieve() , i);
	 * documents.retrieve().findNext(); } documents.findNext();
	 * 
	 * }
	 * 
	 * }
	 */

	private static void setInvertedIndexHash(int size) {
		invertedIndexHash = new hashMap<>(size);

		documents.findFirst();

		for (int i = 0; i < documents.length(); i++) {//d
			documents.retrieve().findFirst();
			for (int j = 0; j < documents.retrieve().length(); j++) {//s

				Integer lastValue = invertedIndexHash.lastInsertedValue(documents.retrieve().retrieve());

				if (lastValue == null || !lastValue.equals(i))
					invertedIndexHash.put(documents.retrieve().retrieve(), i);
				documents.retrieve().findNext();
			}
			documents.findNext();

		}

	}

}
