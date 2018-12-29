
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class HuffmanNode
{
  private int key;
  private String value;
  private HuffmanNode left;
  private HuffmanNode right;

  public HuffmanNode(int k, String v){
      key=k;
      value=v;
      left=null;
      right=null;
  }
    public void insertNode(HuffmanNode node){
        HuffmanNode leftChild=new HuffmanNode(key, value);
        leftChild.setLeft(left);
        leftChild.setRight(right);
        key=node.getKey()+key;
        value="";
        right=node;
        left=leftChild;
    }
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }
  
    public boolean isLeaf(){
        if(left==null && right==null)
            return true;
        return false;
    }
    public void print(){
        if(isLeaf()){
            System.out.println(value+": "+key);
        }
        else{
            left.print();
            right.print();
        }
    }
    public void printCode(String s, String pathFile){
        if(isLeaf()){
            //System.out.println(value+": "+s);
            String code="^"+value+"^"+key+"^"+s;
            writeToFile(code, pathFile);
        }
        else{
            left.printCode(s+"0", pathFile);
            right.printCode(s+"1", pathFile);
        }
    }
    public void setValuesToAVL(String s, AVLTreeBag avl){
        if(isLeaf()){
            avl.addValue(value, s);
        }
        else{
            left.setValuesToAVL(s+"0", avl);
            right.setValuesToAVL(s+"1", avl);
        }
    }
    
    public void writeToFile(String string, String path){
        File file = new File(path);
        try{
        if(file.exists()==false){
                System.out.println("We had to make a new file.");
                file.createNewFile();
        }
        PrintWriter out = new PrintWriter(new FileWriter(file, true));
        out.append(string);
        out.close();
        }catch(IOException e){
            System.out.println("COULD NOT LOG!!");
        }
    }
}
