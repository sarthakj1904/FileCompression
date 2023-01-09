
public class HuffmanZipper implements IZipper {

	@Override
	public void compress(String inputFile, String outputFile) {
		// TODO Auto-generated method stub
		ICompression obj = new HuffmanCompression();
		obj.compress(inputFile, outputFile);
	}

	@Override
	public void decompress(String outputFile, String decompressedFile) {
		// TODO Auto-generated method stub
		IDecompression obj = new HuffmanDecompression();
		obj.decompress(outputFile, decompressedFile);
	}

}
