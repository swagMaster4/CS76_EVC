/* Name: Jyoti Rani
Test #2
Date: 04/10/19
Program that generically quickSorts
*/


import java.util.*;
class Jyoti_Rani_Test2 {


  public static void main(String[] args) {
     //Circle c = new Circle(10);
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    quickSort(list);
    for (int i = 0; i < list.length; i++){
      System.out.print(list[i] + " ");
    }
       System.out.println();


      // System.out.println("CIRCE" + c);

       Circle[] list1 = {new Circle(2), new Circle(3), new Circle(2),new Circle(5),new Circle(6),new Circle(1),new Circle(2),new Circle(3),new Circle(14),new Circle(12)    };
       quickSort(list1, new GeometricObjectComparator());

       for (Circle c : list1){
         c.printCircle();
      System.out.print(" ");
    }






  }// end of main


  public static <E extends Comparable<E>> void quickSort(E[] list) {
    quickSort(list, 0, list.length - 1);
  }

  public static <E> void quickSort(E[] list, Comparator<? super E> comparator) {
    quickSort(list, 0, list.length - 1, comparator);
  }

  private static <E extends Comparable<E>> void quickSort(E[] list, int  first, int  last) {
    if (last > first) {
      int pivotIndex = partition(list, first, last);
      quickSort(list, first, pivotIndex - 1);
      quickSort(list, pivotIndex + 1, last);
    }
  }
  private static <E> void quickSort(E[] list, int  first, int  last, Comparator<? super E> comparator) {
    if (last > first) {
      int pivotIndex = partition(list, first, last, comparator);
      quickSort(list, first, pivotIndex - 1, comparator);
      quickSort(list, pivotIndex + 1, last, comparator);
    }
  }




  /** Partition the array list[first..last] */
  private static <E extends Comparable<E>> int partition(E[] list, int first, int  last) {
    E  pivot = list[first]; // Choose the first element as the pivot
    int  low = first + 1; // Index for forward search
    int  high = last; // Index for backward search

    while (high > low) {
      // Search forward from left
      while (low <= high && (list[low].compareTo(pivot)< 0))
        low++;

      // Search backward from right
      while (low <= high && (list[high].compareTo(pivot)> 0))
        high--;

      // Swap two elements in the list
      if (high > low) {
        E temp = list[high];
        list[high] = list[low];
        list[low] = temp;
      }
    }

    while (high > first && (list[high].compareTo(pivot)>= 0))
      high--;

    // Swap pivot with list[high]

    if ((list[high].compareTo(pivot)< 0)) {
      list[first] = list[high];
      list[high] = pivot;
      return high;
    }
    else {
      return first;
    }
  }

    /** Partition the array list[first..last] */
  private static <E> int partition(E[] list, int first, int  last, Comparator<? super E> comparator) {
    E  pivot = list[first]; // Choose the first element as the pivot
    int  low = first + 1; // Index for forward search
    int  high = last; // Index for backward search

    while (high > low) {
      // Search forward from left
      while (low <= high && (comparator.compare(list[low], pivot)<= 0))
        low++;

      // Search backward from right
      while (low <= high && (comparator.compare(list[high], pivot)> 0))
        high--;

      // Swap two elements in the list
      if (high > low) {
        E temp = list[high];
        list[high] = list[low];
        list[low] = temp;
      }
    }

    while (high > first && (comparator.compare(list[high], pivot)>= 0))
      high--;

    // Swap pivot with list[high]

    if ((comparator.compare(list[high], pivot)< 0)) {
      list[first] = list[high];
      list[high] = pivot;
      return high;
    }
    else {
      return first;
    }
  }

  /** A test method */




}






 class Circle extends GeometricObject {
  private double radius;

  public Circle() {
  }

  public Circle(double radius) {
    this.radius = radius;
  }

  /** Return radius */
  public double getRadius() {
    return radius;
  }

  /** Set a new radius */
  public void setRadius(double radius) {
    this.radius = radius;
  }

  @Override /** Return area */
  public double getArea() {
    return radius * radius * Math.PI;
  }

  /** Return diameter */
  public double getDiameter() {
    return 2 * radius;
  }

  @Override /** Return perimeter */
  public double getPerimeter() {
    return 2 * radius * Math.PI;
  }

  /* Print the circle info */
  public void printCircle() {
    System.out.println("The circle is created " + getDateCreated() +
      " and the radius is " + radius);
  }
}


 abstract class GeometricObject {
  private String color = "white";
  private boolean filled;
  private java.util.Date dateCreated;

  /** Construct a default geometric object */
  protected GeometricObject() {
    dateCreated = new java.util.Date();
  }

  /** Construct a geometric object with color and filled value */
  protected GeometricObject(String color, boolean filled) {
    dateCreated = new java.util.Date();
    this.color = color;
    this.filled = filled;
  }

  /** Return color */
  public String getColor() {
    return color;
  }

  /** Set a new color */
  public void setColor(String color) {
    this.color = color;
  }

  /** Return filled. Since filled is boolean,
   *  the get method is named isFilled */
  public boolean isFilled() {
    return filled;
  }

  /** Set a new filled */
  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  /** Get dateCreated */
  public java.util.Date getDateCreated() {
    return dateCreated;
  }

  /** Return a string representation of this object */
  public String toString() {
    return "created on " + dateCreated + "\ncolor: " + color +
      " and filled: " + filled;
  }

  /** Abstract method getArea */
  public abstract double getArea();

  /** Abstract method getPerimeter */
  public abstract double getPerimeter();
}





class GeometricObjectComparator
    implements Comparator<GeometricObject>, java.io.Serializable {
  public int compare(GeometricObject o1, GeometricObject o2) {
    double area1 = o1.getArea();
    double area2 = o2.getArea();

    if (area1 < area2)
      return -1;
    else if (area1 == area2)
      return 0;
    else
      return 1;
  }
}
