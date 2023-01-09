import java.util.Map;

public interface IHuffmanAlgo {
	
	void compress(String inputFile, String outputFile);
	
	PriorityQueue<Node> createHuffmanNodes(byte[] bytes);
	
	Node buildHuffmanTree(PriorityQueue<Node> nodes);
	
	Map<Byte, String> generateHuffmanCodes(Node root);
	
	byte[] getEncodedBytes(Map<Byte, String> huffCodes, byte[] bytes);
	
}
