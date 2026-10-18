import java.util.Scanner;

public class Ranking {
	
	public void ranking(int choice) {
		
		System.out.println("################## Ranked Retrieval ###########################");
		if(choice == 1)
			indexRanking();
		else if(choice == 2)
			linkedRanking();
		else if(choice == 3)
			BSTranking();
		else if(choice == 4)
			hashRanking();
			
	}

	//this is the Document ranking 
    private void indexRanking() { //big(o) is O(D*W*L)
   	 
   	 rankingArray<Integer> elements = indexRankingDoc(getSearchWords());
   	if(elements.length()==0) {
      	 System.out.println("there is no matching results");
      	 return;
      	 }
   	 System.out.println("DocID\tScore");
   	 for (int i = 0; i < elements.length(); i++) {
			
			System.out.println(elements.elements[i].data +"\t"+elements.elements[i].p);
		}
   	 
   	 
   	 
   	 
   	 
    } 
    //this method returns a sorted array  
	 // the data is the Documents ID and the priority is the number of times that each word is in the Document
    private rankingArray<Integer> indexRankingDoc(String[] words) {
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
		return rankedDoc; 
	 }
	
	
	 
	
     	
		 
	 
	 //count the number of times that a word is in the Document
	 private int termFrequency(String s1,LinkedList<String> theDoc) {//n
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
	 private String[] getSearchWords() {
	         Scanner scanner = new Scanner(System.in);
	         System.out.print("Enter your search query: ");
	         String input = scanner.nextLine();
	         
	        input.toLowerCase();
	         
	         // Split the input by spaces
	         String[] words = input.split("\\s");
	         
	        
	         
	         
	         return words;
	     }
	 
	 
	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 private void linkedRanking() { //
	   	 
	   	 rankingArray<Integer> elements = linkedRankingDoc(getSearchWords());
	   	if(elements.length()==0) {
	      	 System.out.println("there is no matching results");
	      	 return;
	      	 }
	   	 System.out.println("DocID\tScore");
	   	 for (int i = 0; i < elements.length(); i++) {
				
				System.out.println(elements.elements[i].data +"\t"+elements.elements[i].p);
			}
	   	 
	   	 
	   	 
	   	 
	   	 
	    }
	 private rankingArray<Integer> linkedRankingDoc(String[] words) {//W*(L+D*X)
		 rankingArray<Integer> rankedDoc= new rankingArray<Integer>(dataReader.documents.length());
		 int[] docTF= new int[dataReader.documents.length()];
		 
		 for (int i = 0; i < words.length; i++) {//W
			if(dataReader.linkedListFindWord(words[i])) {//L
				
				
				
				for (int j = 0; j < dataReader.documents.length(); j++) {//D
					int count=0;
					
					if(dataReader.linkedInverted_index.retrieve().findDoc(j)) {//X
						count+=dataReader.linkedInverted_index.retrieve().data.retrieve().TF;
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
		return rankedDoc;	
	}	
		
		
		 
		 
		 
	 
	    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	     //these are the same ranking  methods but with using hash map for better big(O)        	 
	       	 private void hashRanking() {//big(o) is O(D*L)
		       	 
		       	 rankingArray<Integer> elements = hashRankingDoc(getSearchWords());
		       	 if(elements.length()==0) {
		       	 System.out.println("there is no matching resaults");
		       	 return;
		       	 }
		       	 System.out.println("DocID\tScore");
		       	 for (int i = 0; i < elements.length(); i++) {
		    			
		    			System.out.println(elements.elements[i].data +"\t"+elements.elements[i].p);
		    		}
	       	 
	       	 
	       	 
	       	 
	        }
	     private rankingArray<Integer> hashRankingDoc(String[] words) {//w*d*logN+nlogn
			 rankingArray<Integer> rankedDoc= new rankingArray<Integer>(dataReader.documents.length());
			 int[] docTF= new int[dataReader.documents.length()];
			 
			 for (int i = 0; i < words.length; i++) {//W
				if(dataReader.invertedIndexHash.get(words[i])!= null) {//1
					dataReader.invertedIndexHash.get(words[i]);
					
						
					
					for (int j = 0; j < dataReader.documents.length(); j++) {//D
						int count=0;
						if(dataReader.invertedIndexHash.get(words[i]).findDoc(j)) {//D*logN
							count+=dataReader.invertedIndexHash.get(words[i]).data.retrieve().TF;
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
			return rankedDoc;	
		}	

	    
	     ////////////////////////////////////////////////////////////////////////////////////////////////
	     //BST ranking
	     
	     
	     
	     private void BSTranking() { //big(o) is W*L*logN
	       	 
	       	 rankingArray<Integer> elements = BSTrankingDoc(getSearchWords());
	       	if(elements.length()==0) {
	          	 System.out.println("there is no matching results");
	          	 return;
	          	 }
	       	 System.out.println("DocID\tScore");
	       	 for (int i = 0; i < elements.length(); i++) {
	    			
	    			System.out.println(elements.elements[i].data +"\t"+elements.elements[i].p);
	    		}
	       	 
	       	 
	       	 
	       	 
	       	 
	        }
	     
	     
	     
	     
	     
	     private rankingArray<Integer> BSTrankingDoc(String[] words) {// W*logB + W*L*logN + M*logM
			
			 rankingArray<Integer> rankedDoc= new rankingArray<Integer>(dataReader.documents.length());
			 int[] docTF= new int[dataReader.documents.length()];
			 
			 for (int i = 0; i < words.length; i++) {// W
				if(!dataReader.BSTinverted_index.findkey(words[i]))//W logB
					continue;
				
				LinkedListobj l= dataReader.BSTinverted_index.retrieve();
				
				dataReader.documents.findFirst();
				for (int q = 0; q < dataReader.documents.length(); q++) {//W*L
						int count =0;
						
						if(l.findDoc(q)) {//W*L*logN
							count+=l.data.retrieve().TF;
						}
					docTF[q]+=count;
				}
			
				
			
				
					
					
			}
			 for (int j = 0; j < docTF.length; j++) {
				if(docTF[j]!=0)
				 rankedDoc.insert(j, docTF[j]);
			}
					 
					
						 	 	
			rankedDoc.mergeSort();//M*logM
			return rankedDoc; 
		 }	 
		 
}