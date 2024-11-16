import java.util.Scanner;

public class Ranking {
	
	
	//this is the Document ranking 
    public static void ranking() { //big(o) is O(D*W*L)
   	 
   	 PQArray<Integer> elements = rankingDoc(getSearchWords());
   	 System.out.println("DocID\tScore");
   	 for (int i = 0; i < elements.length(); i++) {
			
			System.out.println(elements.elements[i].data +"\t"+elements.elements[i].p);
		}
   	 
   	 
   	 
   	 
   	 
    }
	
	
	 
	 //this method returns a Linked priority queue 
	 // the data is the Documents ID and the priority is the number of times that each word is in the Document
    private static PQArray<Integer> rankingDoc(LinkedList<String> words) {
		 long start = System.nanoTime();
		 PQArray<Integer> rankedDoc= new PQArray<Integer>(dataReaderNew.documents.length());
		 
		 for (int i = 0; i < dataReaderNew.documents.length(); i++) {
			int count=0;
			dataReaderNew.documents.findIndex(i);
			words.findFirst();
			
			 while(!words.last()) {
				String s1= words.retrieve();
				count+=termFrequency(s1 , dataReaderNew.documents.retrieve());
				 
				words.findNext();
		 	} 
			 String s1= words.retrieve();
				count+=termFrequency(s1 , dataReaderNew.documents.retrieve());
				 
				if(count !=0)
				rankedDoc.insert(i, count);	
				
		 }	 	
		rankedDoc.mergeSort();
		System.out.println(System.nanoTime() - start);
		return rankedDoc; 
	 }
	 //count the number of times that a word is in the Document
	 private static int termFrequency(String s1,LinkedList<String> theDoc) {
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
	 
	     // this is a method that asks the user to input words to search in this search engine and retrun a linked list of words 
	 	 //without spaces and without "AND" or"OR"
	 private static LinkedList<String> getSearchWords() {
	         Scanner scanner = new Scanner(System.in);
	         System.out.println("Enter your search query : ");
	         String input = scanner.nextLine();
	         
	         LinkedList<String> wordsList = new LinkedList<>();
	         
	         // Split the input by spaces
	         String[] words = input.split("\\s");
	         
	         
	         for (int i = 0; i < words.length; i++) {
	             String word = words[i];
	            
	                wordsList.insert(word);
	             
	         }
	         
	         
	         return wordsList;
	     }
	     
	     
	    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	     //these are the same ranking  methods but with using hash map for better big(O) 
	     
	     public static void hashRanking() {//big(o) is O(D*L)
	       	 
	       	 PQArray<Integer> elements = hashRankingDoc(hashGetSearchWords());
	       	 System.out.println("DocID\tScore");
	       	 for (int i = 0; i < elements.length(); i++) {
	    			
	    			System.out.println(elements.elements[i].data +"\t"+elements.elements[i].p);
	    		}
	       	 
	       	 
	       	 
	       	 
	       	 
	        }
	     private static PQArray<Integer> hashRankingDoc(hashMap<String, Integer> hashWords) {
	    	 long start = System.nanoTime();
			 PQArray<Integer> rankedDoc= new PQArray<Integer>(dataReaderNew.documents.length());
			 
			 for (int i = 0; i < dataReaderNew.documents.length(); i++) {
				int count=0;
				dataReaderNew.documents.findIndex(i);
				
					count+=hashTermFrequency(hashWords , dataReaderNew.documents.retrieve());
					 
					if(count !=0)
					rankedDoc.insert(i, count);	
					
			 }	 	
			rankedDoc.mergeSort();
			System.out.println(System.nanoTime() - start);
			return rankedDoc; 
		 }
	     
	     private static int hashTermFrequency(hashMap<String, Integer> hashWords,LinkedList<String> theDoc) {
			 int count=0;
			 theDoc.findFirst();
			 while(!theDoc.last()) {
			 		if(hashWords.get(theDoc.retrieve())!=null) {
			 			count++;
			 		}
			 		theDoc.findNext();
			
			 }
			 if(hashWords.get(theDoc.retrieve())!=null) {
				 count++;
			 }
			 return count;
			 
		 
		 }
	     private static  hashMap<String, Integer> hashGetSearchWords() {
	         Scanner scanner = new Scanner(System.in);
	         System.out.println("Enter your search query : ");
	         String input = scanner.nextLine();
	         
	        
	         
	         // Split the input by spaces
	         String[] words = input.split("\\s");
	         hashMap<String, Integer> hashWords= new hashMap<String, Integer>(words.length);
	         
	         for (int i = 0; i < words.length; i++) {
	             
	             hashWords.put(words[i], 0);
	               
	             
	         }
	         
	         scanner.close();
	         return hashWords;
	     }
}
