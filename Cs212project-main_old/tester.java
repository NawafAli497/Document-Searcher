
public class tester {

	public static void main(String[] args) {
		
		
		dataReaderNew.ReadFiles();
		dataReader.ReadFiles();
		//dataReader.printLinkedInvertedIndex();
		//dataReaderNew.invertedIndexHash.display();
		Ranking.indexRanking();
		
		Ranking.Linkedranking();
		
		Ranking.BSTranking();

		Ranking.hashRanking();
		Ranking.hashRanking2();
		
		
	}
}
