
public class LinkedListobj {
	public String key;
	public LinkedList<Pair> data;
	/** Creates a new instance of BSTNode */
	public LinkedListobj(String k,int val) {
		key=k;
		data=new LinkedList<Pair>();
		
		insertDoc(val);
		
	}
	public LinkedListobj() {
		data=new LinkedList<Pair>();
		
	}
	
	public void insertDoc(int x) {
		
		if(data.length() == 0) {
			Pair p= new Pair(x,1);
			data.insert(p);
		}	
		else if(data.retrieve().id == x) {
			int t=data.retrieve().TF+1;
			Pair p= new Pair(x,t);
			data.update(p);
			return;
		}
		else {
			Pair p= new Pair(x,1);
			data.insert(p);
		}
	}
	
	
	public boolean findDoc(int x) {
		if(data.length()==0)
			return false;
		data.findFirst();
		while(!data.last()) {
			if(data.retrieve().id==x) {
				return true;
			}
			data.findNext();
		}
		if(data.retrieve().id==x) {
			return true;
		}
		
		return false;
	}
	
}
