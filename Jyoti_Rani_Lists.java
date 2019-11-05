/* Name: Jyoti Rani

Date: 04/24/19
Program that tests all different functions regarding LinkedList data structure.
*/

public class Jyoti_Rani_Lists {
  /** Main method */
  public static void main(String[] args) {
    // Create a list for strings
    MyLinkedList<String> list = new MyLinkedList<>();

    // Add elements to the list
    list.add("America"); // Add it to the list
    System.out.println("(1) " + list);

    list.add(0, "Canada"); // Add it to the beginning of the list
    System.out.println("(2) " + list);

    list.add("Russia"); // Add it to the end of the list
    System.out.println("(3) " + list);

    list.addLast("France"); // Add it to the end of the list
    System.out.println("(4) " + list);

    list.add(2, "Germany"); // Add it to the list at index 2
    System.out.println("(5) " + list);

    list.add(5, "Norway"); // Add it to the list at index 5
    System.out.println("(6) " + list);

    list.add(0, "Poland"); // Same as list.addFirst("Poland")
    System.out.println("(7) " + list);






    // Remove elements from the list
    list.remove(0); // Same as list.remove("Australia") in this case
    System.out.println("(8) " + list);

    list.remove(2); // Remove the element at index 2
    System.out.println("(9) " + list);

    list.remove(list.size() - 1); // Remove the last element
    System.out.print("(10) " + list + "\n(11) ");

    for (String s: list){
      System.out.print(s.toUpperCase() + " ");
    }
    list.add("Russia");
    list.add("Russia");

    System.out.println("First item in the list is: " + list.getFirst());
    System.out.println("Last item in the list is: " + list.getLast());

    list.addFirst("HI");

    list.addLast("AWSM");
    System.out.println("First item in the list is removed: "+ list.removeFirst());
    System.out.println("Last item in the list is removed: "+ list.removeLast());
    System.out.println("\n 2nd element is: " + list.get(2));
    System.out.println("Contains Russia? "+ list.contains("Russia"));
    System.out.println("Index of Russia? "+list.indexOf("Russia"));
    System.out.println("Last index of Russia is "+list.lastIndexOf("Russia"));
    System.out.println("Removing France: "+list.remove("France"));
    System.out.println("Setting Index 2 to AMERC: ");

    list.set(2, "AMERC");
    for (String s: list){
      System.out.print(s+ " ");
    }

    list.clear();
    System.out.println("\nAfter clearing the list, the list size is "
      + list.size());
  }
}


 class MyLinkedList<E> extends MyAbstractList<E> {
  private Node<E> head, tail;

  /** Create a default list */
  public MyLinkedList() {
  }

  /** Create a list from an array of objects */
  public MyLinkedList(E[] objects) {
    super(objects);
  }

  /** Return the head element in the list */
  public E getFirst() {
    if (size == 0) {
      return null;
    }
    else {
      return head.element;
    }
  }

  /** Return the last element in the list */
  public E getLast() {
    if (size == 0) {
      return null;
    }
    else {
      return tail.element;
    }
  }

  /** Add an element to the beginning of the list */
  public void addFirst(E e) {
    Node<E> newNode = new Node<E>(e); // Create a new node
    newNode.next = head; // link the new node with the head
    head = newNode; // head points to the new node
    size++; // Increase list size

    if (tail == null) // the new node is the only node in list
      tail = head;
  }

  /** Add an element to the end of the list */
  public void addLast(E e) {
    Node<E> newNode = new Node<E>(e); // Create a new for element e

    if (tail == null) {
      head = tail = newNode; // The new node is the only node in list
    }
    else {
      tail.next = newNode; // Link the new with the last node
      tail = tail.next; // tail now points to the last node
    }

    size++; // Increase size
  }


   /** Add a new element at the specified index
   * in this list. The index of the head element is 0 */
  public void add(int index, E e) {
    if (index == 0) {
      addFirst(e);
    }
    else if (index >= size) {
      addLast(e);
    }
    else {
      Node<E> current = head;
      for (int i = 1; i < index; i++) {
        current = current.next;
      }
      Node<E> temp = current.next;
      current.next = new Node<E>(e);
      (current.next).next = temp;
      size++;
    }
  }

  /** Remove the head node and
   *  return the object that is contained in the removed node. */
  public E removeFirst() {
    if (size == 0) {
      return null;
    }
    else {
      Node<E> temp = head;
      head = head.next;
      size--;
      if (head == null) {
        tail = null;
      }
      return temp.element;
    }
  }

  /** Remove the last node and
   * return the object that is contained in the removed node. */
  public E removeLast() {
    if (size == 0) {
      return null;
    }
    else if (size == 1) {
      Node<E> temp = head;
      head = tail = null;
      size = 0;
      return temp.element;
    }
    else {
      Node<E> current = head;

      for (int i = 0; i < size - 2; i++) {
        current = current.next;
      }

      Node<E> temp = tail;
      tail = current;
      tail.next = null;
      size--;
      return temp.element;
    }
  }

  /** Remove the element at the specified position in this
   *  list. Return the element that was removed from the list. */
  public E remove(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    else if (index == 0) {
      return removeFirst();
    }
    else if (index == size - 1) {
      return removeLast();
    }
    else {
      Node<E> previous = head;

      for (int i = 1; i < index; i++) {
        previous = previous.next;
      }

      Node<E> current = previous.next;
      previous.next = current.next;
      size--;
      return current.element;
    }
  }

/** Override toString() to return elements in the list */
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      result.append(current.element);
      current = current.next;
      if (current != null) {
        result.append(", "); // Separate two elements with a comma
      }
      else {
        result.append("]"); // Insert the closing ] in the string
      }
    }

    return result.toString();
  }

 /** Clear the list */
  public void clear() {
    size = 0;
    head = tail = null;
  }

 /** Return true if this list contains the element e */
  public boolean contains(E e) {
    Node<E> current = head;
    for(int i = 0; i < size; i++){
      if(current.element.equals(e)){
        return true;
      }
      else{
      current = current.next;
    }

    }

    return false;
  }

 /** Return the element at the specified index */

  public E get(int index) {
    Node<E> curr = head;
    for (int i = 1; i <= index; i++) {
      curr = curr.next;
    }

    return curr.element;
  }

 /** Return the index of the head matching element in
   *  this list. Return -1 if no match. */
  public int indexOf(E e) {

    Node<E> current = head;
    for(int i = 0; i < size; i++){
      if(current.element.equals(e)){
        return i;
      }
      else{
      current = current.next;
    }

    }


    return -1;
  }

 /** Return the index of the last matching element in
   *  this list. Return -1 if no match. */
  public int lastIndexOf(E e) {
    Node<E> current = head;
    int lastIndx = -1;
    for(int i = 0; i < size; i++){
      if(current.element.equals(e)){
        lastIndx = i;
      }
      else{
      current = current.next;
    }

    }




    return lastIndx;

  }

 /** Replace the element at the specified position
   *  in this list with the specified element. */
  public E set(int index, E e) {

    Node<E> currentBefore = head;
    for(int i = 0; i < index; i++){
       currentBefore = currentBefore.next;

      }

      Node<E> currentNext = currentBefore.next.next;
      remove(index);
      add(index, e);
        Node<E> current = currentBefore.next;





    return null;
  }

 /** Override iterator() defined in Iterable */
  public java.util.Iterator<E> iterator() {
    return new LinkedListIterator();
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException
        ("Index: " + index + ", Size: " + size);
  }

  private class LinkedListIterator
      implements java.util.Iterator<E> {
    private Node<E> current = head; // Current index


    public boolean hasNext() {
      return (current != null);
    }


    public E next() {
      E e = current.element;
      current = current.next;
      return e;
    }


    /*public void remove() {
      private Node<E> currentBef = head;

      if(current == null){
        return;
      }
      //only one element
      if(current == head){


          if(current.next == null){
            head = tail = null;
          }

          else{
            head = current.next;
          }
          for (int i = 1; i < index; i++) {
            previous = previous.next;
          }

          Node<E> current = previous.next;
          previous.next = current.next;
          size--;



      }
      System.out.println("Implementation left as an exercise");
    }
    */
  }

  private static class Node<E> {
    E element;
    Node<E> next;

    public Node(E element) {
      this.element = element;
    }
  }
}


 interface MyList<E> extends java.lang.Iterable<E> {
  /** Add a new element at the end of this list */
  public void add(E e);

  /** Add a new element at the specified index in this list */
  public void add(int index, E e);

  /** Clear the list */
  public void clear();

  /** Return true if this list contains the element */
  public boolean contains(E e);

  /** Return the element from this list at the specified index */
  public E get(int index);

  /** Return the index of the first matching element in this list.
   *  Return -1 if no match. */
  public int indexOf(E e);

  /** Return true if this list contains no elements */
  public boolean isEmpty();

  /** Return the index of the last matching element in this list
   *  Return -1 if no match. */
  public int lastIndexOf(E e);

  /** Remove the first occurrence of the element o from this list.
   *  Shift any subsequent elements to the left.
   *  Return true if the element is removed. */
  public boolean remove(E e);

  /** Remove the element at the specified position in this list
   *  Shift any subsequent elements to the left.
   *  Return the element that was removed from the list. */
  public E remove(int index);

  /** Replace the element at the specified position in this list
   *  with the specified element and returns the new set. */
  public Object set(int index, E e);

  /** Return the number of elements in this list */
  public int size();
}


 abstract class MyAbstractList<E> implements MyList<E> {
  protected int size = 0; // The size of the list

  /** Create a default list */
  protected MyAbstractList() {
  }

  /** Create a list from an array of objects */
  protected MyAbstractList(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      add(objects[i]);
  }

 /** Add a new element at the end of this list */
  public void add(E e) {
    add(size, e);
  }

 /** Return true if this list contains no elements */
  public boolean isEmpty() {
    return size == 0;
  }

 /** Return the number of elements in this list */
  public int size() {
    return size;
  }

 /** Remove the first occurrence of the element e
   *  from this list. Shift any subsequent elements to the left.
   *  Return true if the element is removed. */
  public boolean remove(E e) {
    if (indexOf(e) >= 0) {
      remove(indexOf(e));
      return true;
    }
    else
      return false;
  }
}
