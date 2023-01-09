import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanDecompression implements IDecompression {
	
	@Override
	public void decompress(String compressedFile, String deCompressedFile) {
		try {
            FileInputStream inStream = new FileInputStream(compressedFile);
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            byte[] encodedHuffBytes = (byte[]) objectInStream.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInStream.readObject();
            byte[] originalBytes = decode(huffmanCodes, encodedHuffBytes);
            OutputStream outStream = new FileOutputStream(deCompressedFile);
            outStream.write(originalBytes);
            inStream.close();
            objectInStream.close();
            outStream.close();
        } catch (Exception e) { 
        	e.printStackTrace(); 
        }
	}
	
	private byte[] decode(Map<Byte, String> huffmanCodes, byte[] encodedBytes) {
        StringBuilder sb1 = new StringBuilder();
        for (int i=0; i<encodedBytes.length; i++) {
            byte b = encodedBytes[i];
            boolean flag = (i == encodedBytes.length - 1);
            sb1.append(byteToBit(!flag, b));
        }
        
        Map<String, Byte> mp = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            mp.put(entry.getValue(), entry.getKey());
        }
        
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < sb1.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (i + count < sb1.length() && flag) {
                String key = sb1.substring(i, i + count);
                b = mp.get(key);
                if (b == null) count++;
                else flag = false;
            }
            list.add(b);
            i += count;
        }
        byte b[] = new byte[list.size()];
//        System.out.println(list.size());
        for (int i = 0; i < b.length; i++) {
//        	System.out.println(list.get(i));
        	if(list.get(i) != null)
        		b[i] = list.get(i);
        }
            
        return b;
    }
	
	private String byteToBit(boolean flag, byte b) {
        int byte1 = b;
        if (flag) byte1 |= 256;
        String str = Integer.toBinaryString(byte1);
        if (flag || byte1 < 0)
            return str.substring(str.length() - 8);
        else return str;
    }

	
	

}
