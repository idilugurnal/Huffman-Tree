
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

 
public class HuffmanTree
{
  private HuffmanNode htRoot; 
  private String filePath;
  private String codeTablePath;
  private String binaryString;
  private AVLTreeBag avl;
  private IntBinomialHeap heap;
  private ArrayList<HuffmanNode> huffmanList;
  private int totalNumberOfCharacters;
  private int numberOfCharacters;
  public HuffmanTree(String file, String codeTable)
  {
   filePath=file;
   codeTablePath=codeTable;
   htRoot=null;
   avl= new AVLTreeBag();
   heap=new IntBinomialHeap();
   huffmanList = new ArrayList();
   totalNumberOfCharacters=0;
   numberOfCharacters=0;
  }
  
  private void generateAVLTree() {
    File file = new File(filePath);
    if (!file.exists()) {
      System.out.println(filePath + " does not exist.");
      return;
    }
    if (!(file.isFile() && file.canRead())) {
      System.out.println(file.getName() + " cannot be read from.");
      return;
    }
    try {
      FileInputStream fis = new FileInputStream(file);
      char current;
      while (fis.available() > 0) {
        totalNumberOfCharacters++;
        current = (char) fis.read();
        avl.add(current+"", null);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void generateBinomialHeap(){
      avl.generateHeap(heap);
      //avl.print();
  }
  
  private void generateHuffmanList(){
      while(heap.manyItems>0){
          IntBinomialTree binTree=heap.findMinTree();
          //heap.print();
          heap.remove();
          HuffmanNode node=new HuffmanNode(binTree.getKey(), binTree.getValue());
          huffmanList.add(node);
          
      }
      numberOfCharacters=huffmanList.size();
//      for(int i=0;i<huffmanList.size();i++){
//          huffmanList.get(i).print();
//      }
  }
  private void generateHuffmanTree(){
      while(huffmanList.size()>=2){
          huffmanList.get(1).insertNode(huffmanList.get(0));
          huffmanList.remove(0);
          sortHuffmanList();
      }
      htRoot=huffmanList.get(0);
      //htRoot.print();
  }
  private void sortHuffmanList(){
      HuffmanNode node=huffmanList.get(0);
      huffmanList.remove(0);
      boolean sorted=false;
      for(int i=0;i<huffmanList.size();i++){
          if(node.getKey()<=huffmanList.get(i).getKey() && sorted==false){
              huffmanList.add(i, node);
              sorted=true;
          }
      }
      if(sorted==false)
          huffmanList.add(node);
  }
  
   private void writeToFile(String string, String path){
        File file = new File(path);
        try{
        if(file.exists()==false){
                System.out.println("We had to make a new file.");
                file.createNewFile();
        }
        PrintWriter out = new PrintWriter(new FileWriter(file, false));
        out.print(string);
        out.close();
        }catch(IOException e){
            System.out.println("COULD NOT LOG!!");
        }
    }
   
   private void generateCodeTable(){
       String write=""+totalNumberOfCharacters + "^" + numberOfCharacters;
        writeToFile(write, codeTablePath);
        htRoot.printCode("", codeTablePath);
   }
   
   private void generateBinaryString(){
       htRoot.setValuesToAVL("", avl);
       binaryString="";
       File file = new File(filePath);
        if (!file.exists()) {
          System.out.println(filePath + " does not exist.");
          return;
        }
        if (!(file.isFile() && file.canRead())) {
          System.out.println(file.getName() + " cannot be read from.");
          return;
        }
        try {
          FileInputStream fis = new FileInputStream(file);
          char current;
          while (fis.available() > 0) {
            current = (char) fis.read();
            binaryString+=avl.getValueByKey(current+"");
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
   }
   
   public void generate(){
       generateAVLTree();
       generateBinomialHeap();
       generateHuffmanList();
       generateHuffmanTree();
       generateCodeTable();
       generateBinaryString();
   }
   
   public String getBinaryString(){
       return binaryString;
   }

}
