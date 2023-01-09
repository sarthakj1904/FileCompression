
public class Node implements Comparable<Node>{
	
	Byte data;
	int freq;
	Node left, right;
	
	public Node(Byte data, int weight) {
		this.data = data;
		this.freq = weight;
	}
	
	public int compareTo(Node a) {
		return this.freq - a.freq;
	}

}
