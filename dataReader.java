import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class dataReader {
	
	public static ArrayList<String> stopWords;
	public static ArrayList<LinkedList<String>> documents;
	public static MyBST<String> BSTinverted_index;
	public static MyLinkedList<String,MyLinkedList<Integer, Integer>> linkedInverted_index;
	public static void ReadFiles() { // this method is O(S + D*M*log(S))
		long start = System.nanoTime();
		documents = new ArrayList<LinkedList<String>>(countNumberOfDocuments());
		stopWords = new ArrayList<String>(countNumberOfStopWords()); // could be changed to hashMap
		BSTinverted_index= new MyBST<String>();
		linkedInverted_index = new MyLinkedList<String,MyLinkedList<Integer, Integer>>();
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
			BSTsetInvertedIndex();
			linkedSetInvertedIndex();

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

	private static String[] wordsSeperator(String lineOfTexts) {
//      
String formatedText = lineOfTexts.substring(lineOfTexts.indexOf(",") + 1, lineOfTexts.length() - 1).replaceAll("[^'\\p{L}0-9\\s-_]", "").toLowerCase().trim();
//                         
return formatedText.split("\\s+");
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
 

	  private static void BSTsetInvertedIndex() { 
		  documents.findFirst();
	  
		  for(int i = 0 ; i < documents.length(); i++) {
		  
			  documents.retrieve().findFirst(); 
			  for(int j = 0 ; j <documents.retrieve().length() ; j++) {
		  
				  BSTinverted_index.insert(documents.retrieve().retrieve() , i);
	  
				  documents.retrieve().findNext(); 
			  } 
		  
		  documents.findNext();
	  
		  }
	  }
	  private static void linkedSetInvertedIndex() { 
		  documents.findFirst();
	  
	  for(int i = 0 ; i < documents.length(); i++) {
		  
		  documents.retrieve().findFirst(); 
		  for(int j = 0 ; j <documents.retrieve().length() ; j++) {
			 
			  
			  if(linkedInverted_index.findKey(documents.retrieve().retrieve())) {
				  if(linkedInverted_index.retrieveSecond().findKey(i)){
				  
				  int TF=linkedInverted_index.retrieveSecond().retrieveSecond()+1;
				  
				  linkedInverted_index.retrieveSecond().updateSecond(TF);
				  }
				  else {
					 
					  linkedInverted_index.retrieveSecond().insert(i,1);
				  }
					  
			  }
			  else {
				  MyLinkedList<Integer, Integer> temp= new MyLinkedList<Integer, Integer>();
				  temp.insert(i,1);
			  
				 linkedInverted_index.insert(documents.retrieve().retrieve() , temp);
			  
			  }
			 documents.retrieve().findNext();
		} 
		  
		  documents.findNext();
	  
	  }
	 
	  }
	  public static void printLinkedInvertedIndex() {
		    linkedInverted_index.findFirst(); // Start with the first element

		    while (!linkedInverted_index.last()) { // Iterate through all elements
		        // Retrieve the key (word)
		        String word = linkedInverted_index.retrieveFirst();

		        // Retrieve the linked list of document IDs and frequencies
		        MyLinkedList<Integer, Integer> docList = linkedInverted_index.retrieveSecond();

		        System.out.print("Word: " + word + " -> ");

		        docList.findFirst();
		        while (!docList.last()) { // Print document ID and frequency pairs
		            System.out.print("(DocID: " + docList.retrieveFirst() + ", Frequency: " + docList.retrieveSecond() + ") ");
		            docList.findNext();
		        }
		        // Print the last element
		        System.out.print("(DocID: " + docList.retrieveFirst() + ", Frequency: " + docList.retrieveSecond() + ") ");
		        System.out.println(); // New line for the next word

		        linkedInverted_index.findNext();
		    }
		    // Print the last word
		    String lastWord = linkedInverted_index.retrieveFirst();
		    MyLinkedList<Integer, Integer> lastDocList = linkedInverted_index.retrieveSecond();

		    System.out.print("Word: " + lastWord + " -> ");
		    lastDocList.findFirst();
		    while (!lastDocList.last()) {
		        System.out.print("(DocID: " + lastDocList.retrieveFirst() + ", Frequency: " + lastDocList.retrieveSecond() + ") ");
		        lastDocList.findNext();
		    }
		    System.out.print("(DocID: " + lastDocList.retrieveFirst() + ", Frequency: " + lastDocList.retrieveSecond() + ") ");
		    System.out.println(); // New line for the last word
		}

 
 
 
}
