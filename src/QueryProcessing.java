import java.util.Scanner;

public class QueryProcessing {

	public void booleanQuery(int choice) {

		System.out.println("################### Boolean Retrieval ####################");
		System.out.print("Enter your Query: ");
		Scanner keyboard = new Scanner(System.in);
		String exp = keyboard.nextLine();


		if (choice == 1) {
			LinkedList<Integer> test = evalExpIndex(exp);

			if (test == null) {
				System.out.println("invalid entry not in the system");
				return;
			} else {
				System.out.print("Result doc IDs: {");
				test.findFirst();
				for (int i = 0; i < test.length(); i++) {
					if (i == 0)
						System.out.print(test.retrieve());
					else
						System.out.print(", " + test.retrieve());
					test.findNext();
				}
				System.out.print("}\n");
				return;
			}
		}
		LinkedList<Pair> test = null;
		if (choice == 2) {
			test = evalExpLinked(exp);
		} else if (choice == 3) {
			test = evalExpBST(exp);
		} else if (choice == 4) {
			test = evalExpHash(exp);
		}

		if (test == null)
			System.out.println("invalid entry not in the system");
		else {
			System.out.print("Result doc IDs: {");
			test.findFirst();
			for (int i = 0; i < test.length(); i++) {
				if (i == 0)
					System.out.print(test.retrieve().id);
				else
					System.out.print(", " + test.retrieve().id);
				test.findNext();
			}
			System.out.print("}\n");

		}

	}

	private LinkedList<Pair> evalExpHash(String expression) {

		LinkedStack<String> opStk = new LinkedStack<>();
		LinkedStack<LinkedList<Pair>> valStk = new LinkedStack<>();
		valStk = new LinkedStack<>();

		String[] token = expression.toLowerCase().split("\\s+");

		for (int i = 0; i < token.length; i++) {//O(Q)
			if (!token[i].equals("and") && !token[i].equals("or")) {
				if (dataReader.invertedIndexHash.get(token[i]) == null) //O(1)
					return null;
				valStk.push(dataReader.invertedIndexHash.get(token[i]).data); //O(1)
			} else {
				repeatOpsHash(token[i], valStk, opStk);// since the max number of ops is 2 in the stack then O(Q(n1+n2))
				opStk.push(token[i]);//O(1)
			}
		}
		repeatOpsHash("$", valStk, opStk);// O(Q(n1+n2))

		return valStk.pop();
	}

	private void doOpHash(LinkedStack<LinkedList<Pair>> valStk, Stack<String> op) { //(O(n1+n2)
		LinkedList<Pair> x = valStk.pop(); // n1
		LinkedList<Pair> y = valStk.pop(); // n2
		LinkedList<Pair> result = new LinkedList<>();
		hashMap<Integer, Boolean> tmp = new hashMap<>(x.length());
		String tmpOp = op.pop();

		if (tmpOp.equals("and")) {
			x.findFirst();
			for (int i = 0; i < x.length(); i++) { //O(n1)
				tmp.put(x.retrieve().id, true); //O(1)
				x.findNext();
			}

			y.findFirst();
			for (int i = 0; i < y.length(); i++) { //O(n2)
				if (tmp.get(y.retrieve().id) != null) // O(1)
					result.insert(y.retrieve()); //O(1)
				y.findNext();
			}
		} else {

			x.findFirst();
			for (int i = 0; i < x.length(); i++) { //O(n1)
				result.insert(x.retrieve());//O(1)
				tmp.put(x.retrieve().id, true);//O(1)
				x.findNext();
			}

			y.findFirst();
			for (int i = 0; i < y.length(); i++) { //O(n2)
				if (tmp.get(y.retrieve().id) == null) //O(1)
					result.insert(y.retrieve());//O(1)
				y.findNext();
			}

		}
		valStk.push(result); //O(1)
	}

	private void repeatOpsHash(String refOp, LinkedStack<LinkedList<Pair>> valStk, Stack<String> op) { // O(V(n1+n2)) where V is the size of the valStack
		while (valStk.size() > 1 && precedence(refOp) <= precedence(op.peek())) { //O(V)
			doOpHash(valStk, op); //O(n1+n2)
		}
	}

	////////////////////////// BST
	////////////////////////// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private LinkedList<Pair> evalExpBST(String expression) {

		LinkedStack<String> opStk = new LinkedStack<>();
		LinkedStack<LinkedListobj> valStk = new LinkedStack<>();
		valStk = new LinkedStack<>();

		String[] token = expression.toLowerCase().split("\\s+"); 

		for (int i = 0; i < token.length; i++) { //O(Q)
			if (!token[i].equals("and") && !token[i].equals("or")) {
				if (!dataReader.BSTinverted_index.findkey(token[i])) //O(log U)  where U is the number of unique words in BST inverted index
					return null;
				valStk.push(dataReader.BSTinverted_index.retrieve()); //O(1)
			} else {
				repeatOpsLinkedAndBST(token[i], valStk, opStk);
				opStk.push(token[i]);
			}
		}
		repeatOpsLinkedAndBST("$", valStk, opStk);// O(S(n1+n2))

		return valStk.pop().data;
	}


	/////////// LinkedList/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private LinkedList<Pair> evalExpLinked(String expression) {

		LinkedStack<String> opStk = new LinkedStack<>();// Stack for operators
		LinkedStack<LinkedListobj> valStk = new LinkedStack<>();
		valStk = new LinkedStack<>();

		String[] token = expression.toLowerCase().split("\\s+");

		for (int i = 0; i < token.length; i++) {// O(s)
			if (!token[i].equals("and") && !token[i].equals("or")) {
				if (!dataReader.linkedListFindWord(token[i]))
					return null;
				valStk.push(dataReader.linkedInverted_index.retrieve());
			} else {
				repeatOpsLinkedAndBST(token[i], valStk, opStk);// O(S(n1+n2))
				opStk.push(token[i]);
			}
		}
		repeatOpsLinkedAndBST("$", valStk, opStk);// O(S(n1+n2))


		return valStk.pop().data;
	}

	private void doOpLinkedAndBST(LinkedStack<LinkedListobj> valStk, Stack<String> op) {
		LinkedListobj x = valStk.pop();
		LinkedList<Pair> y = valStk.pop().data;
		LinkedListobj result = new LinkedListobj();
		String tmpOp = op.pop();

		if (tmpOp.equals("and")) {

			y.findFirst();
			for (int i = 0; i < y.length(); i++) {
				if (x.findDoc(y.retrieve().id))
					result.data.insert(y.retrieve());
				y.findNext();
			}
		} else {

			x.data.findFirst();
			for (int i = 0; i < x.data.length(); i++) {
				result.data.insert(x.data.retrieve());
				x.data.findNext();
			}

			y.findFirst();
			for (int i = 0; i < y.length(); i++) {
				if (!x.findDoc(y.retrieve().id))
					result.data.insert(y.retrieve());
				y.findNext();
			}

		}
		valStk.push(result);
	}

	private void repeatOpsLinkedAndBST(String refOp, LinkedStack<LinkedListobj> valStk, Stack<String> op) {// O(s)
		while (valStk.size() > 1 && precedence(refOp) <= precedence(op.peek())) {
			doOpLinkedAndBST(valStk, op);
		}
	}

	///////////////// index
	///////////////// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private LinkedList<Integer> evalExpIndex(String expression) {

		LinkedStack<String> opStk = new LinkedStack<>();// Stack for operators
		LinkedStack<LinkedList<Integer>> valStk = new LinkedStack<>();
		valStk = new LinkedStack<>();

		String[] token = expression.toLowerCase().split("\\s+");

		for (int i = 0; i < token.length; i++) {// O(s)
			if (!token[i].equals("and") && !token[i].equals("or")) {
				LinkedList<Integer> tmp = makeAList(token[i]);
				if (tmp.length() == 0)
					return null;
				valStk.push(tmp);
			} else {
				repeatOpsindex(token[i], valStk, opStk);// O(S(n1+n2))
				opStk.push(token[i]);
			}
		}
		repeatOpsindex("$", valStk, opStk);// O(S(n1+n2))


		return valStk.pop();
	}

	private void doOpindex(LinkedStack<LinkedList<Integer>> valStk, Stack<String> op) {
		LinkedList<Integer> x = valStk.pop();
		LinkedList<Integer> y = valStk.pop();
		LinkedList<Integer> result = new LinkedList<Integer>();
		String tmpOp = op.pop();

		if (tmpOp.equals("and")) {

			y.findFirst();
			for (int i = 0; i < y.length(); i++) {
				if (x.findKey(y.retrieve()))
					result.insert(y.retrieve());
				y.findNext();
			}
		} else {

			x.findFirst();
			for (int i = 0; i < x.length(); i++) {
				result.insert(x.retrieve());
				x.findNext();
			}

			y.findFirst();
			for (int i = 0; i < y.length(); i++) {
				if (!x.findKey(y.retrieve()))
					result.insert(y.retrieve());
				y.findNext();
			}

		}
		valStk.push(result);
	}

	private void repeatOpsindex(String refOp, LinkedStack<LinkedList<Integer>> valStk, Stack<String> op) {// O(s)
		while (valStk.size() > 1 && precedence(refOp) <= precedence(op.peek())) {
			doOpindex(valStk, op);
		}
	}

	private LinkedList<Integer> makeAList(String word) {
		LinkedList<Integer> list = new LinkedList<>();
		dataReader.documents.findFirst();

		for (int i = 0; i < dataReader.documents.length(); i++) {

			dataReader.documents.retrieve().findFirst();
			for (int j = 0; j < dataReader.documents.retrieve().length(); j++) {

				if (!list.findKey(i) && dataReader.documents.retrieve().retrieve().equals(word)) {
					list.insert(i);
				}
				dataReader.documents.retrieve().findNext();
			}
			dataReader.documents.findNext();
		}

		return list;

	}

	private int precedence(String op) {
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
