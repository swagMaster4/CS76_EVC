/*
 * Name: Jyoti Rani
 * Assignment: Efficiency
 Date: 04/01/19
 * Assignment Description:  Finds the smallest distance between two points recursively.
 */
import java.util.Comparator;
import java.util.Arrays;
import java.util.*;
public class Jyoti_Rani_AlgorithmReal {
  public static double listXY[][] = new double[2][100];
  public static Pair pr = new Pair(new Point(30,10), new Point(35,10) );
  public static void main(String[] args) {
      int count = 0;
      while(count < 100){
        double x = Math.random() *100;
        double y = Math.random() *100;
        System.out.print("("+x+","+y+") " + "\n");
        listXY[0][count] = x;
        listXY[1][count] = y;
        count++;
      }
      Pair result=pr.getClosestPair(listXY);
      System.out.println("\n Closest pair is Point 1: "+ result.getPoint1()+", and Point 2: "+result.getPoint2());
  }
}

class CompareX implements Comparator<Point>, java.io.Serializable {
  public int compare(Point o1, Point o2) {
    double x = o1.getpointX();
    double x2 = o2.getpointX();
    if(x > x2) return 1;
    else if(x == x2) return 0;
    else return -1;
  }
}

class CompareY implements Comparator<Point>, java.io.Serializable {
  public int compare(Point o1, Point o2) {
    double x = o1.getpointY();
    double x2 = o2.getpointY();
    if(x > x2) return 1;
    else if(x == x2) return 0;
    else return -1;
  }
}

class Point implements Comparator<Point>, java.io.Serializable {
  private double pointX;
  private double pointY;
  public Point(double x, double y) {
    this.pointX = x;
    this.pointY = y;
  }

   public void setpointX(double x){
    pointX = x;
  }

   public void setpointY(double y){
    pointY = y;
  }

  public String toString() {
    return this.pointX+" "+this.pointY;
  }

  public double getpointX(){
    return pointX;
  }
   public double getpointY(){
    return pointY;
  }

  public int compare(Point o1, Point o2) {

    double x = o1.getpointX();
    double x2 = o2.getpointX();

    if(x > x2){
      return 1;

    }
    else if(x == x2)
      return 0;
    else
      return -1;
  }
}

class Pair{
  public static Point p1;
  public static Point p2;

  public Pair(Point p1, Point p2){
    this.p1 = p1;
    this.p2= p2;
  }
  public Point getPoint1(){
    return p1;
  }
  public Point  getPoint2(){
    return p2;
  }
  /** Populates listP with Points with coordinates passed in points[][], then calls getClosestPair with listP */
  public static Pair getClosestPair(double [ ] [ ]  points){
    int count = 0;
    int size = points[0].length;

    Point listP[] = new Point[size];
    while(count < size){
      double x = points[0][count];
      double y = points[1][count];
      listP[count] = new Point(x, y);
      count++;
    }
    return getClosestPair(listP);
  }

  /** Sorts Points in points[] by X and Y coordinates and calls distance() */
  public static Pair getClosestPair(Point[ ] points){
    Point [ ] pointsOrderedOnX = points.clone();
    Point [ ] pointsOrderedOnY = points.clone();
    Arrays.sort(pointsOrderedOnX,new CompareY());
    Arrays.sort(pointsOrderedOnY,new CompareX());
    //System.out.println("first call w 0 "+(pointsOrderedOnX.length-1));
    return distance(pointsOrderedOnX, 0, pointsOrderedOnX.length-1,pointsOrderedOnY);
}

/** Return the distance of the closest pair of points in pointsOrderedOnX[low, high]. This is a recursive method. pointsOrderedOnX and pointsOrderedOnY are not changed in the subsequent recursive calls.*/
public static Pair distance(Point[ ] pointsOrderedOnX, int low, int high, Point [] pointsOrderedOnY ){
  Pair closestPair = null;
  if((high - low) < 3){ //Base case. Return brute force.
    double d = Double.MAX_VALUE;
    for(int j = low; j <= high; j++)
      for(int k = low; k <= high; k++){
          if(j==k) continue;
          Pair p = new Pair(pointsOrderedOnX[j], pointsOrderedOnX[k]);
          if((p.distance(p.getPoint1(), p.getPoint2()) < d)){
            d = p.distance(p.getPoint1(), p.getPoint2());
            closestPair = new Pair(p.getPoint1(), p.getPoint2());
          }
      }
    return closestPair;
  }
  ArrayList<Point> stripL = new ArrayList<>();
  ArrayList<Point> stripR = new ArrayList<>();
  //int mid = ((int)(high -low)/2)+ low; //+1?
  int lowS1 = low;
  int highS1 = (low + high)/2;
  int lowS2 = (low + high)/2 + 1;
  int highS2 = high;

  //System.out.println("calling S1 "+lowS1+" "+highS1);
  Pair p1 = distance(pointsOrderedOnX, lowS1,  highS1, pointsOrderedOnY); //
  double d1 = p1.distance(p1.getPoint1(), p1.getPoint2());
  //System.out.println("calling S2 "+lowS2+" "+highS2);
  Pair p2 = distance(pointsOrderedOnX, lowS2,  highS2,pointsOrderedOnY);
  double d2 = p2.distance(p2.getPoint1(), p2.getPoint2());

  closestPair = (d1 < d2) ? p1: p2;
  double d = Math.min(d1, d2);

  for(Point p : pointsOrderedOnY){
    double midX = pointsOrderedOnX[(low + high)/2].getpointX();
    double  delta = midX - p.getpointX();
    if ((delta>=0) &&(delta<=d)) stripL.add(p);
    else if ((delta<0) &&((delta*-1.0)<=d)) stripR.add(p);
  }
  int r = 0;  // r is the index of a point in stripR
  for (Point p : stripL) {
    // Skip the points in stripR below p.y - d
    while (r < stripR.size() && stripR.get(r).getpointY() <= p.getpointY() - d) {
      r++;
    }
    int r1 = r;
    while (r1 < stripR.size() && Math.abs(stripR.get(r1).getpointY() - p.getpointY()) <= d) {
      // Check if (p, q[r1] is a possible closest pair
      if (distance(p,stripR.get(r1)) < d) {
        d = distance(p, stripR.get(r1));
        closestPair = new Pair(p,stripR.get(r1));
      }
      r1++;
    }
  }
  return closestPair;
}
  /** Compute the distance between two points p1 and p2 */
  public static double distance(Point p1, Point p2){
    double x1 = p1.getpointX();
    double x2 = p2.getpointX();
    double y1 = p1.getpointY();
    double y2 = p2.getpointY();
    return (distance(x1, y1, x2, y2));
  }
  /** Compute the distance between points (x1, y1) and (x2, y2) */
  public static double distance(double x1, double y1, double x2, double y2){
    return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
  }
}
