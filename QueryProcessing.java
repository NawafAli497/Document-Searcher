import java.util.Scanner;

public class QueryProcessing {

	static LinkedStack<String> opStk;// Stack for operators
	static LinkedStack<LinkedList<Integer>> valStk; // Stack for values

	public static void main(String[] args) {

		long start = System.nanoTime();
		dataReaderNew.ReadFiles();


		
		Scanner keyboard = new Scanner(System.in);
		String exp = keyboard.nextLine();
		LinkedList<Integer> test = evalExp(exp);

		if (test == null)
			System.out.println("No Results");
		else {
			System.out.print("Result doc IDs: {");
			test.findFirst();
			for (int i = 0; i < test.length(); i++) {
				if(i == 0)
					System.out.print(test.retrieve());
				else
					System.out.print(", " + test.retrieve());
				test.findNext();
			}
			System.out.print("}");
		}
		
	}

	public static LinkedList<Integer> evalExp(String expression) {
		opStk = new LinkedStack<>();
		valStk = new LinkedStack<>();
		
		String[] token = expression.toLowerCase().split("\\s+");

		for (int i = 0; i < token.length; i++) {//O(s)
			if (!token[i].equals("and") && !token[i].equals("or")) {
				if (dataReaderNew.invertedIndexHash.get(token[i]) == null)
					return null;
				valStk.push(dataReaderNew.invertedIndexHash.get(token[i]));
			} else {
				repeatOps(token[i]);//O(S(n1+n2))
				opStk.push(token[i]);
			}
		}
		repeatOps("$");//O(S(n1+n2))

		return valStk.pop();
	}

	private static void doOp() {
		LinkedList<Integer> x = valStk.pop();
		LinkedList<Integer> y = valStk.pop();
		LinkedList<Integer> result = new LinkedList<>();
		hashMap<Integer , Boolean> tmp = new hashMap<>(x.length());
		String op = opStk.pop();

		if (op.equals("and")) {
			x.findFirst();
			for(int i = 0 ; i < x.length() ; i++) {
				tmp.put(x.retrieve(), true);
				x.findNext();
			}
			
			y.findFirst();
			for(int i = 0 ; i < y.length() ; i++) {
				if(tmp.get(y.retrieve()) != null)
					result.insert(y.retrieve());
				y.findNext();
			}
		}
		else {
			
			x.findFirst();
			for(int i = 0 ; i < x.length() ; i++) {
				result.insert(x.retrieve());
				tmp.put(x.retrieve(), true);
				x.findNext();
			}
			
			y.findFirst();
			for(int i = 0 ; i < y.length() ; i++) {
				if(tmp.get(y.retrieve()) == null) {
					result.insert(y.retrieve());
				}
				y.findNext();
			}
		
		}
		valStk.push(result);
	}

	private static void repeatOps(String refOp) {//O(s)
		while (valStk.size() > 1 && precedence(refOp) <= precedence(opStk.peek())) {
			doOp();
		}
	}

	private static int precedence(String op) {
		switch (op) {
		case "$":
			return 0;
		case "or":
			return 1;
		case "and":
			return 2;
		default:
			throw new IllegalArgumentException("Unsupported operator: " + op);
		}
	}
}
