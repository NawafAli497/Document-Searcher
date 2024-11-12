import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class dataReader {
	
	public static ArrayList<String> stopWords;
	public static ArrayList<LinkedList<String>> documents;
	public static BST<String> inverted_index;
	public static void ReadFiles() { // this method is O(S + D*M*log(S))
		long start = System.nanoTime();
		documents = new ArrayList<>();
		stopWords = new ArrayList<>(); // could be changed to hashMap
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
 public static LinkedPQ<Integer> ranking(String s1) {
	 //1- navigate 						done
	 //2- term frequency 				done
	 //3- for all 						done
	 //4- order the doc (priority queue)done
	 //5- multiple words				done
	 //6- corner cases
	 
	 Relative root= Relative.Root;
	 
	 inverted_index.find(root);
	 
	 inverted_index.findkey(s1);
	 LinkedPQ<Integer> rankedDoc=new LinkedPQ<Integer>();
	 
	LinkedList<Integer> indexes = inverted_index.retrieve();
	indexes.findFirst();
	
	while(!indexes.last())  {
		documents.findIndex(indexes.retrieve());
		 
		int count=termFrequency(s1 , documents.retrieve());
		rankedDoc.enqueue(indexes.retrieve(), count);
	 	indexes.findNext(); 
	 			
	 }	 
	 documents.findIndex(indexes.retrieve());
	 
		int count=termFrequency(s1, documents.retrieve());
		rankedDoc.enqueue(indexes.retrieve(), count);
	 	 	
	 	

	return rankedDoc; 
 }
 public static LinkedPQ<Integer> ranking(LinkedList<String> words) {
	 //1- navigate 						done
	 //2- term frequency 				done
	 //3- for all 						done
	 //4- order the doc (priority queue)done
	 //5- multiple words				done
	 //6- corner cases
	 
	 LinkedPQ<Integer> rankedDoc=new LinkedPQ<Integer>();
	 
	 
	 for (int i = 0; i < documents.length(); i++) {
		int count=0;
		 documents.findIndex(i);
		words.findFirst();
		
		 while(!words.last()) {
			String s1= words.retrieve();
			count+=termFrequency(s1 , documents.retrieve());
			 
			words.findNext();
	 	} 
		 String s1= words.retrieve();
			count+=termFrequency(s1 , documents.retrieve());
			 
			if(count !=0)
			rankedDoc.enqueue(i, count);
	 }	 
	
	return rankedDoc; 
 }
 
 public static int termFrequency(String s1,LinkedList<String> theDoc) {
	 int count=0;
	 theDoc.findFirst();
	 while(!theDoc.last()) {
	 		if(s1.equalsIgnoreCase(theDoc.retrieve())) {
	 			count++;
	 		}
	 		theDoc.findNext();
	
	 }
	 if(s1.equalsIgnoreCase(theDoc.retrieve())) {
		 count++;
	 }
	 return count;
	 
 
 }
 
     
     public static LinkedList<String> getSearchWords() {
         Scanner scanner = new Scanner(System.in);
         System.out.println("Enter your search query (use 'AND' or 'OR' between words): ");
         String input = scanner.nextLine();
         
         LinkedList<String> wordsList = new LinkedList<>();
         
         // Split the input by spaces
         String[] words = input.split(" ");
         
         // Using a regular for loop instead of an enhanced for loop
         for (int i = 0; i < words.length; i++) {
             String word = words[i];
             // Ignore "AND" and "OR" and empty strings
             if (!word.equalsIgnoreCase("AND") && !word.equalsIgnoreCase("OR") && !word.trim().isEmpty()) {
                 wordsList.insert(word.trim());
             }
         }
         
         scanner.close();
         return wordsList;
     }
     
     public static void rankingall() {
    	 ArrayList<PQElement<Integer>> elements = ranking(getSearchWords()).getAllElements();
    	 System.out.println("DocID\tScore");
    	 for (int i = 0; i < elements.length(); i++) {
			elements.findIndex(i);
			System.out.println(elements.retrieve().data +"\t"+elements.retrieve().p);
		}
    	 
    	 
    	 
    	 
    	 
     }


 
 
 
 
 
}
