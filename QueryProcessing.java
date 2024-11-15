
public class QueryProcessing {



public static void main(String[] args) {

long start = System.nanoTime();
dataReaderNew.ReadFiles();


LinkedList<Integer> a = dataReaderNew.invertedIndexHash.get("sports");

a.findFirst();
for(int i = 0 ; i < a.length() ; i++) {
System.out.print(a.retrieve() + " ");
a.findNext();
}

System.out.println();

System.out.println(System.nanoTime()-start);


}
}

