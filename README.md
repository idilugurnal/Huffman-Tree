Idil Ugurnal	
CS206 Data Structure
Term Project Part II Report
1. Detailed Description of Huffman Tree constructing Process:
•	In order to construct Huffman Tree from the target file, firstly I have implemented a HuffmanNode class with needed variables and methods, then I used below steps:
•	Reading every character from the target file and add them to AVL Tree using compareWords() method. If the character was already in the tree, I incremented the occurrence of the character by one.
•	After generating AVL Tree. I have generated a BinomialHeap with the number of occurrences of each character. Thus I had a sorted BinomialHeap from the count to the most counted characters. 
•	Next, I have created an arraylist of HuffmanNodes and inserted every character as a HuffmanNode to the arraylist from least to most occurred ones.
•	After creating an arraylist I merged first and second HuffmanNodes together, then sorted araylist from least to most occurred characters. I repeated this step until my arraylist left with only one HuffmanNode. Thus I set this node to hfRoot and HuffmanTree were constructed.
•	Finally, I used hfRoot to create a Huffman code table and encode text file to binary file.
2. Usage of AVL Tree
I used AVL Tree for two purposes in my Project:
1.	I used AVL Tree for comparing and adding each character to the  AVL Tree, so that I could compare by ANSI and increment the occurrence of an already existing character.
2.	I used the same constructed AVL Tree to set the decoded value of each character after constructing the HuffmanTree.
3.  Usage of Binomial Heap
I used Binomial Heap to sort characters by number of occurrences. Beacause, in Binomial Heap there is a method to find and remove minimum item. It was easy for me to sort with BinomialHeap.

4. Compress Ratio
Target File size=182KB, Encoded File size=105KB, Ratio=105/182=0.577
