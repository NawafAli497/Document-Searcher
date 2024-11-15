import java.util.*;
public class QueryProcessing {



public static void main(String[] args) {
Scanner input = new Scanner(System.in);

//long start = System.nanoTime();
dataReaderNew.ReadFiles();

System.out.println("enter the words !");
String query = input.nextLine();

String[] parts = query.split("\\s+");

for(int i = 0;i<parts.length;i++)
	System.out.println(parts[i]);

if (parts.length < 3) {
    System.out.println("Invalid query format. Use 'word1 AND/OR word2'.");
    return;
}


// Initial result is based on the first word
LinkedList<Integer> currentResult = dataReaderNew.invertedIndexHash.get(parts[0]);
currentResult.findFirst();
for(int i = 0 ; i < currentResult.length() ; i++) {
System.out.print(currentResult.retrieve() + " ");
currentResult.findNext();
}
//LinkedList<Integer> a = dataReaderNew.invertedIndexHash.get("sports");
//a.findFirst();
//for(int i = 0 ; i < a.length() ; i++) {
//System.out.print(a.retrieve() + " ");
//a.findNext();
//}
//
//System.out.println();

//System.out.println(System.nanoTime()-start);



}}
