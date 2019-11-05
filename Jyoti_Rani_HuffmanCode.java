


/* Name: Jyoti Rani
Assignment Final Program: HuffmanCode
Date: 05/21/19
Program en/decodes using Huffman Algorithm
*/

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Scanner;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Jyoti_Rani_HuffmanCode extends Application {
  public static String[] codes = new String[2 * 128];
  public static Text t = new Text(200, 450, "");
  public static Tree tree;
  public static BTView view;
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    launch(args);

}

  /** Get Huffman codes for the characters
   * This method is called once after a Huffman tree is built
   */
  public static String[] getCode(Tree.Node root) {
    if (root == null) return null;
    String[] codes = new String[2 * 128];
    assignCode(root, codes);
    return codes;
  }

  /* Recursively get codes to the leaf node */
  private static void assignCode(Tree.Node root, String[] codes) {
    if (root.left != null) {
      root.left.code = root.code + "0";
      assignCode(root.left, codes);

      root.right.code = root.code + "1";
      assignCode(root.right, codes);
    }
    else {
      codes[(int)root.element] = root.code;
    }
  }

  /** Get a Huffman tree from the codes */
  public static Tree getHuffmanTree(int[] counts) {
    // Create a heap to hold trees
    Heap<Tree> heap = new Heap<>(); // Defined in Listing 24.10
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > 0)
        heap.add(new Tree(counts[i], (char)i)); // A leaf node tree
    }

    while (heap.getSize() > 1) {
      Tree t1 = heap.remove(); // Remove the smallest weight tree
      Tree t2 = heap.remove(); // Remove the next smallest weight
      heap.add(new Tree(t1, t2)); // Combine two trees
    }

    return heap.remove(); // The final tree
  }

  /** Get the frequency of the characters */
  public static int[] getCharacterFrequency(String text) {
    int[] counts = new int[256]; // 256 ASCII characters

    for (int i = 0; i < text.length(); i++)
      counts[(int)text.charAt(i)]++; // Count the character in text

    return counts;
  }

  public void start(Stage primaryStage) {
    //Tree tree = new Tree(); // Create a tree
    //String arr[] = new String[]{"George", "Michael", "Tom", "Adam", "Jones", "Peter", "Daniel"};


    BorderPane pane = new BorderPane();
    //BTView view = new BTView(tree); // Create a View
    //pane.setCenter(view);

    TextField tfKey = new TextField();
    TextField tfKey2 = new TextField();
    tfKey.setPrefColumnCount(11);
    tfKey2.setPrefColumnCount(11);

    tfKey.setAlignment(Pos.BASELINE_RIGHT);
    tfKey2.setAlignment(Pos.BASELINE_RIGHT);
    Button btInsert = new Button("Show Huffman Tree");
    Button btDelete = new Button("Decode to Text");

    HBox hBox = new HBox(5);
      HBox hBox2 = new HBox(5);
    VBox vbox = new VBox(5);

    hBox.getChildren().addAll(new Label("Enter a text: "),
      tfKey,  btInsert);
    hBox2.getChildren().addAll( new Label("Enter a bit string:"),
       tfKey2,  btDelete);
    hBox.setAlignment(Pos.CENTER);
    hBox2.setAlignment(Pos.CENTER);

    pane.setTop(vbox);
    vbox.getChildren().addAll(hBox, hBox2);




    btInsert.setOnAction(e -> {


      String text = tfKey.getText();
      int[] counts = getCharacterFrequency(text); // Count frequency
       tree = getHuffmanTree(counts);

       // Create a Huffman tree
     codes = getCode(tree.root); // Get codes

        String encode = "";

    for (int i = 0; i < text.length(); i++){
        char pos = text.charAt(i);
        int indx = (int)pos;
        encode += codes[indx];

    }
    System.out.println(encode);
     view = new BTView(tree); // Create a View
     pane.setCenter(view);
     view.displayTree();
    //pane.getChildren().remove(t);
    //Text t = new Text(200, 450, );
    //pane.getChildren().addAll(t);


      view.setStatus("Encoded text is: " + encode);


    });


  btDelete.setOnAction(e -> {
    String decode = "";
   String part = "";
    String inp = tfKey2.getText();
    /*for (int i = 0; i < codes.length; i++){
        //if (counts[i] != 0) // (char)i is not in text if counts[i] is 0
          System.out.println(codes[i]);
    }
    */

    for(int i = 0; i< inp.length(); i++){
        part += inp.charAt(i);
        //System.out.println(part);
        for(int j = 0; j< codes.length; j++){
          if(codes[j] != null){
            //System.out.println("NOT NULL!");
          if((codes[j].equals(part))){
            char c = (char)j;
            //System.out.println("This is char " + c);
            decode+=c;
            part = "";


          }
        }

        }

      }
          //pane.getChildren().remove(t);
          //pane.getChildren().addAll(new Text(200, 470, "Decoded text is: " + decode));
          view.getChildren().clear();
          view.displayTree();
        view.setStatus("Decoded text is: " + decode);

    });




    // Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 1000, 1000);
    primaryStage.setTitle("Spring 2019 Final Project ~ Huffman Encoding, Jyoti Rani "); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /** Define a Huffman coding tree */

}
  class Tree implements Comparable<Tree> {
  Node root; // The root of the tree

  /** Create a tree with two subtrees */
  public Tree(Tree t1, Tree t2) {
    root = new Node();
    root.left = t1.root;
    root.right = t2.root;
    root.weight = t1.root.weight + t2.root.weight;
  }

  /** Create a tree containing a leaf node */
  public Tree(int weight, char element) {
    root = new Node(weight, element);
  }

  @Override /** Compare trees based on their weights */
  public int compareTo(Tree t) {
    if (root.weight < t.root.weight) // Purposely reverse the order
      return 1;
    else if (root.weight == t.root.weight)
      return 0;
    else
      return -1;
  }

  public class Node {
    char element; // Stores the character for a leaf node
    int weight; // weight of the subtree rooted at this node
    Node left; // Reference to the left subtree
    Node right; // Reference to the right subtree
    String code = ""; // The code of this node from the root

    /** Create an empty node */
    public Node() {
    }

    /** Create a node with the specified weight and character */
    public Node(int weight, char element) {
      this.weight = weight;
      this.element = element;
    }
  }
}






 class Heap<E extends Comparable> {
  private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

  /** Create a default heap */
  public Heap() {
  }

  /** Create a heap from an array of objects */
  public Heap(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      add(objects[i]);
  }

  /** Add a new object into the heap */
  public void add(E newObject) {
    list.add(newObject); // Append to the heap
    int currentIndex = list.size() - 1; // The index of the last node

    while (currentIndex > 0) {
      int parentIndex = (currentIndex - 1) / 2;
      // Swap if the current object is greater than its parent
      if (list.get(currentIndex).compareTo(
          list.get(parentIndex)) > 0) {
        E temp = list.get(currentIndex);
        list.set(currentIndex, list.get(parentIndex));
        list.set(parentIndex, temp);
      }
      else
        break; // the tree is a heap now

      currentIndex = parentIndex;
    }
  }

  /** Remove the root from the heap */
  public E remove() {
    if (list.size() == 0) return null;

    E removedObject = list.get(0);
    list.set(0, list.get(list.size() - 1));
    list.remove(list.size() - 1);

    int currentIndex = 0;
    while (currentIndex < list.size()) {
      int leftChildIndex = 2 * currentIndex + 1;
      int rightChildIndex = 2 * currentIndex + 2;

      // Find the maximum between two children
      if (leftChildIndex >= list.size()) break; // The tree is a heap
      int maxIndex = leftChildIndex;
      if (rightChildIndex < list.size()) {
        if (list.get(maxIndex).compareTo(
            list.get(rightChildIndex)) < 0) {
          maxIndex = rightChildIndex;
        }
      }

      // Swap if the current node is less than the maximum
      if (list.get(currentIndex).compareTo(
          list.get(maxIndex)) < 0) {
        E temp = list.get(maxIndex);
        list.set(maxIndex, list.get(currentIndex));
        list.set(currentIndex, temp);
        currentIndex = maxIndex;
      }
      else
        break; // The tree is a heap
    }

    return removedObject;
  }

  /** Get the number of nodes in the tree */
  public int getSize() {
    return list.size();
  }
}


class BTView extends Pane {
  //private HuffmanCode tree;
  public static Tree tree;
  private double radius = 15; // Tree node radius
  private double vGap = 50; // Gap between two levels in a tree

  BTView(Tree tree) {
    this.tree = tree;
    setStatus("Tree is empty");
  }

  public void setStatus(String msg) {

    getChildren().add(new Text(400, 450, msg));


  }

  public void displayTree() {
    this.getChildren().clear(); // Clear the pane
  //  this.setAlignment(Pos.CENTER);
    if (tree.root != null) {
      // Display tree recursively
      displayTree(tree.root, 500, vGap,
        250);
    }
  }

  /** Display a subtree rooted at position (x, y) */
  private void displayTree(Tree.Node root,
      double x, double y, double hGap) {
    if (root.left != null) {
      // Draw a line to the left node
      getChildren().add(new Line(x - hGap, y + vGap, x, y));
      getChildren().add(new Text(x - hGap/2 - 3,y + vGap/2 -3, "0"));

      // Draw the left subtree recursively
      displayTree(root.left, x - hGap, y + vGap, hGap / 2);
    }

    if (root.right != null) {
      // Draw a line to the right node
      getChildren().add(new Line(x + hGap, y + vGap, x, y));
      getChildren().add(new Text(x + hGap/2 - 3,y + vGap/2 -3, "1"));
      // Draw the right subtree recursively
      displayTree(root.right, x + hGap, y + vGap, hGap / 2);
    }

    // Display a node
    Circle circle = new Circle(x, y, radius);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    getChildren().addAll(circle,
      new Text(x - 4, y + 4, root.weight + ""), new Text(x - 7, y + 25, Character.toString((root.element))));
  }









}
