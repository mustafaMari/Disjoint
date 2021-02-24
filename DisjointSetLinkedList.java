package Lab9;


public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		public Element(int element) {
			representant = element;  
			last = element;
			
		}
		int representant;
		int next = -1;
		int length = 1;
		int last;
	}
	
	private static final int NULL=-1;
	
	Element arr[];
	
	public DisjointSetLinkedList(int size) {
		 arr = new Element[size];
	}
	
	@Override
	public void makeSet(int item) {
		arr[item] = new Element(item);
	}

	@Override
	public int findSet(int item) {
		if (arr[item] == null)
			return -1;
		if (item != arr[item].representant) {
			arr[item].representant = findSet(arr[item].representant);
		}
		return arr[item].representant;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		return link(findSet(itemA), findSet(itemB));
	}
	private boolean link(int itemA, int itemB) {
		if (itemA == -1 || itemB == -1 ||itemA == itemB)
		if (arr[itemA].length < arr[itemB].length) {
			int temp = itemA;
			itemA = itemB;
			itemB = temp;
		}
		arr[arr[itemA].last].next = itemB;
		arr[itemA].length += arr[itemB].length;
		arr[itemA].last = arr[itemB].last;
		while (itemB != -1)
		{
			arr[itemB].representant = itemA;
			itemB = arr[itemB].next;
		}
		return true;
	}
	
	@Override
	public String toString() {
		//TODO . . . . 
		String str = "Disjoint sets as linked list:";
		for (int x = 0; x<arr.length; x++) {
			if (arr[x].representant != x)
				continue;
			str +="\n";
			while (true) {
				str += x;
				if (arr[x].next != NULL) 
					str += ", ";
				else
				{
					x = arr[x].representant;
					
					break;
				}
				x = arr[x].next; 
			}
		}
		return str;
	}

}
