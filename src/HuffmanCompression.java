import java.util.*;
import java.io.*;


public class HuffmanCompression implements ICompression{
	private StringBuilder sb = new StringBuilder();
	private Map<Byte, String> huffMap = new HashMap<>();
	private long ipFileSize = 0, opFileSize = 0;
	private String cmpSummary = "";
	
	
	@Override
	public void compress(String inputFile, String outputFile) {
		try {
			FileInputStream inStream = new FileInputStream(inputFile);
			byte[] arr = new byte[inStream.available()];
			ipFileSize = inStream.available();
			inStream.read(arr);
			byte[] compressedHuffBytes = encode(arr);
			OutputStream outStream = new FileOutputStream(outputFile);
			ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
			objectOutStream.writeObject(compressedHuffBytes);
            objectOutStream.writeObject(huffMap);
            inStream.close();
            objectOutStream.close();
            outStream.close();
		} catch(Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	private byte[] encode(byte[] bytes) {
		PriorityQueue<Node> nodes = createNodes(bytes);
		Node root = createHuffTree(nodes);
		Map<Byte, String> huffmanCodes = getHuffCodes(root);
		byte[] huffmanCodeBytes = getCompressedBytes(bytes, huffmanCodes);
        return huffmanCodeBytes;
	}
	
	private PriorityQueue<Node> createNodes(byte[] bytes) {
		PriorityQueue<Node> nodes = new MinPriorityQueue<Node>();
		Map<Byte, Integer> freqMap = new HashMap<>();
		for (byte b : bytes) {
			freqMap.put(b, freqMap.getOrDefault(b, 0) + 1);
        }
		for (Map.Entry<Byte, Integer> entry : freqMap.entrySet())
            nodes.add(new Node(entry.getKey(), entry.getValue()));
		
        return nodes;
	}
	
	private Node createHuffTree(PriorityQueue<Node> nodes) {
		while(nodes.len() > 1) {
			Node left  = nodes.poll();
			Node right = nodes.poll();
			Node parent = new Node(null, left.freq + right.freq);
			parent.left = left;
			parent.right = right;
			nodes.add(parent);
		}
		return nodes.poll();
	}
	
	private Map<Byte, String> getHuffCodes(Node root) {
		if (root == null) return null;
        getHuffCodes(root.left, "0", sb);
        getHuffCodes(root.right, "1", sb);
        return huffMap;
	}
	
	private void getHuffCodes(Node node, String code, StringBuilder sb1) {
		StringBuilder sb2 = new StringBuilder(sb1);
        sb2.append(code);
        if (node != null) {
            if (node.data == null) {
                getHuffCodes(node.left, "0", sb2);
                getHuffCodes(node.right, "1", sb2);
            } else
                huffMap.put(node.data, sb2.toString());
        }
	}
	
	private byte[] getCompressedBytes(byte[] bytes, Map<Byte, String> huffCodes) {
        StringBuilder sb1 = new StringBuilder();
        for (byte b : bytes)
            sb1.append(huffCodes.get(b));

        int length=(sb1.length()+7)/8;
        byte[] huffCodeBytes = new byte[length];
        int idx = 0;
        for (int i = 0; i < sb1.length(); i += 8) {
            String sbyte;
            if (i + 8 > sb1.length())
                sbyte = sb1.substring(i);
            else sbyte = sb1.substring(i, i + 8);
            huffCodeBytes[idx] = (byte) Integer.parseInt(sbyte, 2);
            idx++;
        }
        return huffCodeBytes;
    }

	
}
