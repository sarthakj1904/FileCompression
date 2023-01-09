import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		HuffmanCompression obj = new HuffmanCompression("C:\\Users\\PRIYANSHI JAIN\\Desktop\\file.txt", "C:\\Users\\PRIYANSHI JAIN\\Desktop\\compressed.txt");
//		obj.compress();
		String inputFile = "", outputFile = "", decompressedFile = "";
		IZipper obj = new HuffmanZipper();
		obj.compress(inputFile, outputFile);
		obj.decompress(outputFile, decompressedFile);
		
	}

}
