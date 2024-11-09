
public class tester {

	public static void main(String[] args) {
		
		long start = System.nanoTime();
		dataReader.ReadFiles();
		
		dataReader.documents.findFirst();
		
		for(int i = 0 ; i < dataReader.documents.length(); i++) {
			dataReader.documents.retrieve().findFirst();
			for(int j = 0 ; j < dataReader.documents.retrieve().length() ; j++) {
				System.out.print(dataReader.documents.retrieve().retrieve() + " ");
				dataReader.documents.retrieve().findNext();
			}
			dataReader.documents.findNext();
			System.out.println();
		}
		System.out.println(System.nanoTime() - start);
	}
}
