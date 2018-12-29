import java.io.*;
import java.nio.file.*;

public class Main {

	public static void main(String args[]){
		//writeBinaryFile(encodedString, 20170000);
                String path="C:\\Users\\User\\Desktop\\";     //path to target file
                String fileName="long.txt";
                String codeTableFile="200170769.txt";
                String binaryFile="200170769";
                HuffmanTree tree= new HuffmanTree(path+fileName, path+codeTableFile);
                tree.generate();
                writeBinaryFile(tree.getBinaryString(), path+binaryFile);
                String binaryString=readBinaryFile(path+binaryFile);
                System.out.println(binaryString);
                
	}



	static private String readBinaryFile(String filePath)
	{
		FileReader inputStream = null;
		Node head = new Node<Character>(null, null);
		BufferedReader in;
		Node cursor = head;
		String binaryStr = "";
		try
		{
			Path path = Paths.get(filePath);
			byte[] data = Files.readAllBytes(path);

			for(int i=0; i<data.length; i++)
			{
				int c = (int) data[i];
				String s1 = String.format("%8s", Integer.toBinaryString(c & 0xFF)).replace(' ', '0');
				binaryStr += s1;
			}

		} catch (Exception e)
		{
			e.printStackTrace(System.out);
			System.out.println("READ ERROR");
		}

		return binaryStr;
	}

	static private void writeBinaryFile(String input, String filePath) 
	{
		if(input.length() == 0)
		{
			return;
		}

		// calculate byte size
		int numByte = input.length() / 8;
		if (input.length() % 8 > 0)
		{
			numByte += 1;
			for(int i = 0 ; i<(input.length() % 8); i++)
			{
				input+="0";
			}
		}

		int[] outBytes = new int[numByte];

		int i = 0;
		for(; i < numByte-1 ; i++)
		{
			int fromIdx = i*8;
			int toIdx = fromIdx+8;
			String byteString = input.substring(fromIdx, toIdx);
			outBytes[i] = Integer.parseInt(byteString, 2);
		}
		// last condition
		String lastSubString = input.substring(i*8, input.length());
		outBytes[numByte-1] = Integer.parseInt(lastSubString, 2);

		try
		{
			FileOutputStream fos = new FileOutputStream(filePath);
			for(i = 0;i<numByte;i++)
			{
				fos.write(outBytes[i]);
			}
			fos.close();
		}
		catch(Exception e)
		{
			System.out.println("FileWriteError");
		}
	}

}
