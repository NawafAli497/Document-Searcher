import java.util.Scanner;

public class Ranking {
	
	
	//this is the Document ranking 
    public static void indexRanking() { //big(o) is O(D*W*L)
   	 
   	 rankingArray<Integer> elements = indexRankingDoc(getSearchWords());
   	if(elements.length()==0) {
      	 System.out.println("there is no matching resaults");
      	 return;
      	 }
   	 System.out.println("DocID\tScore");
   	 for (int i = 0; i < elements.length(); i++) {
			
			System.out.println(elements.elements[i].data +"\t"+elements.elements[i].p);
		}
   	 
   	 
   	 
   	 
   	 
    } 
    //this method returns a sorted array  
	 // the data is the Documents ID and the priority is the number of times that each word is in the Document
    private static rankingArray<Integer> indexRankingDoc(String[] words) {
		 long start = System.nanoTime();
		 rankingArray<Integer> rankedDoc= new rankingArray<Integer>(dataReader.documents.length());
		 
		 dataReader.documents.findFirst();
		 for (int i = 0; i < dataReader.documents.length(); i++) {//D
			int count=0;
			
			for (int j = 0; j < words.length; j++) {//W
				
				count+=termFrequency(words[j] , dataReader.documents.retrieve());//N
			}
				 
			if(count !=0)
				rankedDoc.insert(i, count);	
			dataReader.documents.findNext();	
		 }	 	
		rankedDoc.mergeSort();
		System.out.println(System.nanoTime() - start);
		return rankedDoc; 
	 }
	
	
	 
	
     	
		 
	 
	 //count the number of times that a word is in the Document
	 private static int termFrequency(String s1,LinkedList<String> theDoc) {//n
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
	 private static String[] getSearchWords() {
	         Scanner scanner = new Scanner(System.in);
	         System.out.println("Enter your search query : ");
	         String input = scanner.nextLine();
	         
	        input.toLowerCase();
	         
	         // Split the input by spaces
	         String[] words = input.split("\\s");
	         
	        
	         
	         
	         return words;
	     }
	 
	 
	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 public static void Linkedranking() { //
	   	 
	   	 rankingArray<Integer> elements = linkedRankingDoc(getSearchWords());
	   	if(elements.length()==0) {
	      	 System.out.println("there is no matching resaults");
	      	 return;
	      	 }
	   	 System.out.println("DocID\tScore");
	   	 for (int i = 0; i < elements.length(); i++) {
				
				System.out.println(elements.elements[i].data +"\t"+elements.elements[i].p);
			}
	   	 
	   	 
	   	 
	   	 
	   	 
	    }
	 private static rankingArray<Integer> linkedRankingDoc(String[] words) {//W*(L+D*X)
		 long start = System.nanoTime();
		 rankingArray<Integer> rankedDoc= new rankingArray<Integer>(dataReader.documents.length());
		 int[] docTF= new int[dataReader.documents.length()];
		 
		 for (int i = 0; i < words.length; i++) {//W
			if(dataReader.linkedInverted_index.findKey(words[i])) {//L
				
				
				
				for (int j = 0; j < dataReader.documents.length(); j++) {//D
					int count=0;
					
					if(dataReader.linkedInverted_index.retrieveSecond().findKey(j)) {//X
						count+=dataReader.linkedInverted_index.retrieveSecond().retrieveSecond();
					}
					docTF[j]+=count;
					
				}
			}
			
		 }
		 
		for (int j = 0; j < docTF.length; j++) {
			if(docTF[j] !=0)
				rankedDoc.insert(j, docTF[j]);	
			
		}	 
		rankedDoc.mergeSort();
		System.out.println(System.nanoTime() - start);
		return rankedDoc;	
	}	
		
		
		 
		 
		 
	 
	    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	     //these are the same ranking  methods but with using hash map for better big(O) 
	     
	     public static void hashRanking() {//big(o) is O(D*L)
	       	 
	       	 rankingArray<Integer> elements = hashRankingDoc(hashGetSearchWords());
	       	 if(elements.length()==0) {
	       	 System.out.println("there is no matching resaults");
	       	 return;
	       	 }
	       	 System.out.println("DocID\tScore");
	       	 for (int i = 0; i < elements.length(); i++) {
	    			
	    			System.out.println(elements.elements[i].data +"\t"+elements.elements[i].p);
	    		}
	     }
	     
	       	 
	       	 public static void hashRanking2() {//big(o) is O(D*L)
		       	 
		       	 rankingArray<Integer> elements = hashRankingDoc2(getSearchWords());
		       	 if(elements.length()==0) {
		       	 System.out.println("there is no matching resaults");
		       	 return;
		       	 }
		       	 System.out.println("DocID\tScore");
		       	 for (int i = 0; i < elements.length(); i++) {
		    			
		    			System.out.println(elements.elements[i].data +"\t"+elements.elements[i].p);
		    		}
	       	 
	       	 
	       	 
	       	 
	        }
	     private static rankingArray<Integer> hashRankingDoc2(String[] words) {//w*d*logN+nlogn
	    	 long start = System.nanoTime();
			 rankingArray<Integer> rankedDoc= new rankingArray<Integer>(dataReader.documents.length());
			 int[] docTF= new int[dataReader.documents.length()];
			 
			 for (int i = 0; i < words.length; i++) {//W
				if(dataReader.invertedIndexHash.get(words[i])!= null) {//1
					dataReader.invertedIndexHash.get(words[i]).findFirst();
					
						
					
					for (int j = 0; j < dataReader.documents.length(); j++) {//D
						int count=0;
						if(dataReader.invertedIndexHash.get(words[i]).retrieve().data.findkey(j)) {//D*logN
							count+=dataReader.invertedIndexHash.get(words[i]).retrieve().data.retrieve();
						}
						docTF[j]+=count;
						
					}
				}
				
			 }
			 
			for (int j = 0; j < docTF.length; j++) {
				if(docTF[j] !=0)
					rankedDoc.insert(j, docTF[j]);	
				
			}	 
			rankedDoc.mergeSort();
			System.out.println(System.nanoTime() - start);
			return rankedDoc;	
		}	
		 
	     private static rankingArray<Integer> hashRankingDoc(hashMap<String, Integer> hashWords) {
	    	 long start = System.nanoTime();
			 rankingArray<Integer> rankedDoc= new rankingArray<Integer>(dataReader.documents.length());
			 
			 dataReader.documents.findFirst();
			 for (int i = 0; i < dataReader.documents.length(); i++) {
				 	int count=0;
				 	dataReader.documents.findIndex(i);
				
					count+=hashTermFrequency(hashWords , dataReader.documents.retrieve());
					 
					if(count !=0)
						rankedDoc.insert(i, count);	
					dataReader.documents.findNext();
			 }	 	
			rankedDoc.mergeSort();
			System.out.println(System.nanoTime() - start);
			return rankedDoc; 
		 }
	     
	     private static int hashTermFrequency(hashMap<String, Integer> hashWords,LinkedList<String> theDoc) {
			 int count=0;
			 theDoc.findFirst();
			 while(!theDoc.last()) {//L
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
	         
	         input.toLowerCase();
	         
	         // Split the input by spaces
	         String[] words = input.split("\\s");
	         hashMap<String, Integer> hashWords= new hashMap<String, Integer>(words.length);
	         
	         for (int i = 0; i < words.length; i++) {
	             
	             hashWords.put(words[i], 0);
	               
	             
	         }
	         
	        
	         return hashWords;
	     }
	     ////////////////////////////////////////////////////////////////////////////////////////////////
	     //BST ranking
	     
	     
	     
	     public static void BSTranking() { //big(o) is W*L*logN
	       	 
	       	 rankingArray<Integer> elements = BSTrankingDoc(getSearchWords());
	       	if(elements.length()==0) {
	          	 System.out.println("there is no matching resaults");
	          	 return;
	          	 }
	       	 System.out.println("DocID\tScore");
	       	 for (int i = 0; i < elements.length(); i++) {
	    			
	    			System.out.println(elements.elements[i].data +"\t"+elements.elements[i].p);
	    		}
	       	 
	       	 
	       	 
	       	 
	       	 
	        }
	     
	     
	     
	     
	     
	     private static rankingArray<Integer> BSTrankingDoc(String[] words) {// W*logB + W*L*logN + M*logM
			
	    	 long start = System.nanoTime();
			 rankingArray<Integer> rankedDoc= new rankingArray<Integer>(dataReader.documents.length());
			 int[] docTF= new int[dataReader.documents.length()];
			 
			 for (int i = 0; i < words.length; i++) {// W
				if(!dataReader.BSTinverted_index.findkey(words[i]))//W logB
					continue;
				
				BST<Integer> b1= dataReader.BSTinverted_index.retrieve().data;
				
				dataReader.documents.findFirst();
				for (int q = 0; q < dataReader.documents.length(); q++) {//W*L
						int count =0;
						
						if(b1.findkey(q)) {//W*L*logN
							count+=b1.retrieve();
						}
					docTF[q]+=count;
					dataReader.documents.findNext();
				}
			
				
			
				
					
					
			}
			 for (int j = 0; j < docTF.length; j++) {
				if(docTF[j]!=0)
				 rankedDoc.insert(j, docTF[j]);
			}
					 
					
						 	 	
			rankedDoc.mergeSort();//M*logM
			System.out.println(System.nanoTime() - start);
			return rankedDoc; 
		 }
		
		 
		     
		
		 
		 
		 
		 
		 
		 
		 
}
