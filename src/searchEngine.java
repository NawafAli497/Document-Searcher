import java.util.Scanner;

public class searchEngine {

	public static void main(String[] args) {
		dataReader.ReadFiles();
		QueryProcessing queryProcessing = new QueryProcessing();
		Ranking ranking = new Ranking();
		Scanner Keyboard = new Scanner(System.in);
		
		
		
		int choice = 0;
		do {
			
				System.out.println("\n=== Search Engine Menu ===");
	            System.out.println("1. Boolean Retrieval");
	            System.out.println("2. Ranked Retrieval");
	            System.out.println("3. Show Indexed Documents");
	            System.out.println("4. Show Indexed Tokens");
	            System.out.println("5. Exit");
	            System.out.print("Enter your choice: ");
			
			choice = Keyboard.nextInt();
			Keyboard.nextLine();
			
			if(choice == 1) {
				
				System.out.println("\n=== Boolean Retrieval ===");
		        System.out.println("Choose the indexing structure:");
		        System.out.println("1. Normal index");
		        System.out.println("2. LinkedList Inverted Index");
		        System.out.println("3. BST Inverted Index");
		        System.out.println("4. HashMap Inverted Index");
		        System.out.print("Enter your choice: ");
		        int choice2 = Keyboard.nextInt();
		        Keyboard.nextLine();
		        
		        System.out.println();
		        
		        if(choice2 > 0 && choice2 < 5)
		        	queryProcessing.booleanQuery(choice);
		        else {
		        	System.out.println("invalid input");
		        }
		        
			}
			else if(choice == 2) {
				System.out.println("\n=== Ranking ===");
		        System.out.println("Choose the indexing structure:");
		        System.out.println("1. Normal index");
		        System.out.println("2. LinkedList Inverted Index");
		        System.out.println("3. BST Inverted Index");
		        System.out.println("4. HashMap Inverted Index");
		        System.out.print("Enter your choice: ");
		        int choice2 = Keyboard.nextInt();
		        Keyboard.nextLine();
		        
		        System.out.println();
		        
		        if(choice2 > 0 && choice2 < 5)
		        	 ranking.ranking(choice2);
		        else 
		        	System.out.println("invalid input");
		        
			}
			else if(choice == 3) {
				System.out.print("Number of documents is: " + dataReader.documents.length());
				System.out.println();
				
			}
			else if(choice == 4) {
				System.out.println("Number of vocabulary: " + dataReader.vocab);
				System.out.println("Number of tokens: " + dataReader.tokens);
				
			}
			else if(choice > 5 || choice < 1)
				System.out.println("invalid input");
				

		}while(choice != 5);
		
		Keyboard.close();
	}

}
