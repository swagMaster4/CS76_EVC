/*
 * Name: Jyoti Rani
 * Assignment: Efficiency
 Date: 04/01/19
 * Assignment Description:  Finds the smallest distance between two points recursively.
 */
import java.util.Comparator;
import java.util.Arrays;
import java.util.*;
public class Jyoti_Rani_Algorithm {




  public static double listXY[][] = new double[2][10];

  public static Pair pr = new Pair(new Point(30,10), new Point(35,10) );
  public static void main(String[] args) {
    int count = 0;
    while(count < 10){

      double x = Math.random() *100;
      double y = Math.random() *100;
      listXY[0][count] = x;
      listXY[1][count] = y;
    /*Point p = new Point((Math.random() *100), (Math.random() *100));
    */

    //listP[count] = p;
    count++;
    }

pr.getClosestPair(listXY);
    /*listP[8] = new Point(30,10);
    listP[2] = new Point(35,10);
Point listPY[] = listP.clone();
//int [] = a.clone();


System.out.println("\n\nARR UNSORT");
      for(Point p: listP){
    System.out.print("X coordinate is: " +  p.getpointX() );
    System.out.print("\tY coordinate is: " +  p.getpointY() );
    System.out.print("\n");
  }
  Arrays.sort(listP, new CompareX());
  //sortX();



System.out.println("\n\nARR FINAL, sorted by x coordinates");
      for(Point p: listP){
    System.out.print("X coordinate is: " +  p.getpointX() );
    System.out.print("\tY coordinate is: " +  p.getpointY() );
    System.out.print("\n");
  }
  Arrays.sort(listPY, new CompareY());
  //sortY();



System.out.println("\n\nARR FINAL sorted by y coordinates");
      for(Point p: listPY){
    System.out.print("X coordinate is: " +  p.getpointX() );
    System.out.print("\tY coordinate is: " +  p.getpointY() );
    System.out.print("\n");
  }

  int indexMid = 0;
Point[] tmpArrayLow = null;
Point[] tmpArrayHigh = null;
if(listP.length%2 == 0){
  indexMid = (listP.length/2);
   tmpArrayLow = Arrays.copyOfRange(listP, 0, indexMid);
   tmpArrayHigh = Arrays.copyOfRange(listP, indexMid, listP.length);


}

System.out.println("Low");
for(Point i : tmpArrayLow){
  System.out.println((i.getpointX() + " " + i.getpointY()));
    ;

}
System.out.println("High");
for(Point i : tmpArrayHigh){
  System.out.println((i.getpointX() + " " + i.getpointY()));
    ;

}

//Point[] tmpArrayLowY = Arrays.sort(tmpArrayLow, new CompareY());

//System.out.println("SHORTEST DISTANCE IN LOW: " + pr.distance(tmpArrayLow, 0, tmpArrayLow.length-1, ));



*/









  }

 /* public static void sortX(){
    CompareX c = new CompareX();
    CompareY cY = new CompareY();


      for (int low = 0; low < listP.length; low++)
        {
            for (int high = low + 1;high < listP.length; high++)
            {
          int value = c.compare(listP[low], listP[high]);

          if(value == -1){
            Point tempH = listP[high];
            listP[high] = listP[low];
            listP[low] = tempH;
      }
      else if(value == 0){
        value = cY.compare(listP[low], listP[high]);
        if(value == -1){
            Point tempH = listP[high];
            listP[high] = listP[low];
            listP[low] = tempH;
      }


      }




      }

      }

  }



  public static void sortY(){
   CompareX c = new CompareX();
   CompareY cY = new CompareY();

      for (int low = 0; low < listP.length; low++)
        {
            for (int high = low + 1;high < listP.length; high++)
            {
          int value = cY.compare(listP[low], listP[high]);

          if(value == -1){
            Point tempH = listP[high];
            listP[high] = listP[low];
            listP[low] = tempH;

  }
  else if(value == 0){
        value = c.compare(listP[low], listP[high]);
        if(value == -1){
            Point tempH = listP[high];
            listP[high] = listP[low];
            listP[low] = tempH;
      }


      }





      }

      }

  }


*/


}

class CompareX implements Comparator<Point>, java.io.Serializable {


  public int compare(Point o1, Point o2) {

    double x = o1.getpointX();
    double x2 = o2.getpointX();

    if(x > x2){
      return 1;

    }
    else if(x == x2){
      return 0;

    }
    else{
      return -1;
    }
  }



}




class CompareY implements Comparator<Point>, java.io.Serializable {


  public int compare(Point o1, Point o2) {

    double x = o1.getpointY();
    double x2 = o2.getpointY();

    if(x > x2){
      return 1;

    }
    else if(x == x2){
      return 0;

    }
    else{
      return -1;
    }
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
    else if(x == x2){
      return 0;

    }
    else{
      return -1;
    }
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
   /** Return the distance of the closest pair of points */

public static Pair getClosestPair(double [ ] [ ]  points){
 int count = 0;
 int size = points[0].length;


  Point listP[] = new Point[size];
   while(count < 10){
     double x = points[0][count];
     double y = points[1][count];
     listP[count] = new Point(x, y);


   count++;
   }
/*for(Point p: listP){
 System.out.print("X coordinate is: " +  p.getpointX() );
   System.out.print("\tY coordinate is: " +  p.getpointY() + "\n" );
}
*/
getClosestPair(listP);

}




/** Return the distance of the closest pair of points */

public static Pair getClosestPair(Point[ ] points){


Point [ ] pointsOrderedOnX = Arrays.clone(Arrays.sort(points, new CompareX()));
Point [ ] pointsOrderedOnY = Arrays.clone(Arrays.sort(points, new CompareY()));
distance(pointsOrderedOnX, 0, pointsOrderedOnX.length,pointsOrderedOnY );





}




/** Return the distance of the closest pair of points in pointsOrderedOnX[low, high]. This is a recursive method. pointsOrderedOnX and pointsOrderedOnY are not changed in the subsequent recursive calls.*/

public static double min(double d1, double d2){
 if(d1 < d2){
   return(d1);

 }
 else if(d2 < d1){
   return(d2);

 }
 else{
   return d1;
 }

}

public static Pair distance(Point[ ] pointsOrderedOnX, int low, int high, Point [] pointsOrderedOnY ){

 ArrayList<Point> stripL = new ArrayList<>();
 ArrayList<Point> stripR = new ArrayList<>();


 Pair closestPair = null;

 if((high - low) <= 3){

   double d = Double.MAX_VALUE;

   for(int j = low; j <= high; j++){
     for(int k = low; k <= high; k++){
       if(j ==k){
         continue;

       }
       Pair p = new Pair(pointsOrderedOnX[j], pointsOrderedOnX[k] );
       if((p.distance(p.getPoint1(), p.getPoint2()) < d)){
         d = p.distance(p.getPoint1(), p.getPoint2());
         closestPair = new Pair(p.getPoint1(), p.getPoint2());
       }

   }


 }
 return closestPair;
 }


 int mid = ((int)(high -low)/2) + 1 + low;
 int lowS1 = low;
 int highS1 = (low + high)/2;
 int lowS2 = (low + high)/2 + 1;
 int highS2 = high;

 Pair p1 = distance(pointsOrderedOnX, lowS1,  highS1, pointsOrderedOnY);
 double d1 = p1.distance(p1.getPoint1(), p1.getPoint2());
 Pair p2 = distance(pointsOrderedOnX, lowS2,  highS2,pointsOrderedOnY);
 double d2 = p2.distance(p2.getPoint1(), p2.getPoint2());

 closestPair = (d1 < d2) ? p1: p2;
 double d = Math.min(d1, d2);





 for(Point p : pointsOrderedOnY){
   double midX = pointsOrderedOnX[(low + high)/2].getpointX();
   double  delta = midX - p.getpointX();


 if ((delta>=0) &&(delta<=d)){
   stripL.add(p);
 }
 else if ((delta<0) &&((delta*-1.0)<=d)){

    stripL.add(p);
 }

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

 /*int p1 = 0, p2 = 1; // Initial two points
   double shortestDistance = distance(pointsOrderedOnX[p1].getpointX(), pointsOrderedOnX[p1].getpointY(),
     pointsOrderedOnX[p2].getpointX(), pointsOrderedOnX[p2].getpointY()); // Initialize shortestDistance

   // Compute distance for every two points
   for (int i = 0; i < pointsOrderedOnX.length; i++) {

       double distance = distance(pointsOrderedOnX[low].getpointX(), pointsOrderedOnX[low].getpointY(),
     pointsOrderedOnX[i].getpointX(), pointsOrderedOnX[i].getpointY()); // Find distance

       if (shortestDistance > distance && (shortestDistance!= 0) && (distance!= 0)) {
         p1 = i; // Update p1
         //p2 = j; // Update p2
         shortestDistance = distance; // Update shortestDistance
         System.out.println(shortestDistance);
       }
     }
     if(low < pointsOrderedOnX.length-1){
   distance(pointsOrderedOnX, low+1, pointsOrderedOnX.length-1);
   }


   return shortestDistance;
   */

















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
 return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

}












}
