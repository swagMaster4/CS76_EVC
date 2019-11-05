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

public class BSTAnimation extends Application {
  @Override // Override the start method in the Application class
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


      //HuffmanCode h = new HuffmanCode();
       int[] counts = Tree.getCharacterFrequency(text); // Count frequency

       //System.out.printf("%-15s%-15s%-15s%-15s\n",


       Tree tree = Tree.getHuffmanTree(counts); // Create a Huffman tree
       String[] codes = Tree.getCode(tree.root); // Get codes
       BTView view = new BTView(tree); // Create a View
       pane.setCenter(view);
       for (int i = 0; i < codes.length; i++)
         if (counts[i] != 0) // (char)i is not in text if counts[i] is 0
           System.out.printf("%-15d%-15s%-15d%-15s\n",
             i, (char)i + "", counts[i], codes[i]);
       //String[] codes = new String[value.length()];
       /*for(int i = 0; i < value.length(); i++){
         codes[i] = value.charAt(i);

       }*/
      /*if (tree.search(key)) { // key is in the tree already
        view.displayTree();
        view.setStatus(key + " is already in the tree");
      }
      else {
        tree.insert(key); // Insert a new key
        view.displayTree();
        view.setStatus(key + " is inserted in the tree");
      }
*/
    });

    btDelete.setOnAction(e -> {
      /*int key = Integer.parseInt(tfKey.getText());
      if (!tree.search(key)) { // key is not in the tree
        view.displayTree();
        view.setStatus(key + " is not in the tree");
      }
      else {
        tree.delete(key); // Delete a key
        view.displayTree();
        view.setStatus(key + " is deleted from the tree");
      }
      */
    });


/*btView.setOnAction(e -> {

  for(int i = 0; i < arr.length; i++){
    tree.insert(arr[i]);
  view.displayTree();
}
});
*/



    // Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 500, 500);
    primaryStage.setTitle("BSTAnimation"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

















 class Tree implements Comparable<Tree> {
  Node root; // The root of the tree

  /** Create a tree with two subtrees */
  public Tree(){

  }
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
