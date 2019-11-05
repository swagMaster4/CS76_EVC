
/* Name: Jyoti Rani

Date: 04/08/19
Program that clocks sort routines
*/

import java.util.*;

public class Jyoti_Rani_Sorting
{

  public static void main(String args[])
    {

          System.out.println("\n\n\n\n");
         System.out.println("Array Size \t|\tSelection\t|\t  Merge\t|\t Quick\t|\t Heap\t|\t Radix\t|\t");
         System.out.println("------------------------------------------------------------------------------------------------");
        int j = 0;
         for( j = 0; j < 6; j++){
            int val = (j*50000) + 50000;
           System.out.print(val + " \t|");


          int arr[] =  new int[val];
          int count = 0;
          while(count < val){

          int x = (int)(Math.random() *1000);

          arr[count] = x;
          count++;
          }//end of arr creation
          /*
          printArray(arr);
          System.out.println("NEXT ARRAY \n\n\n\n\n\n");

*/
          for(int i = 0; i < 1; i++){
            if(j == 0){
              System.out.print("\t");
            }
              System.out.print("\t\t" + selectionSort(arr));

              long startTime = System.nanoTime( );
              mergeSort(arr);
              long endTime = System.nanoTime( );
              long executionTime = endTime - startTime;
              System.out.print("\t\t" + executionTime);

              //startTime = System.nanoTime( );
              double eT = quickSort(arr);
              //printArray(arr);
              //endTime = System.nanoTime( );

               //executionTime = endTime - startTime;
              System.out.print("\t\t" + eT);


              Integer[] newArray = new Integer[arr.length];
              int ii = 0;
              for (int value : arr) {
              newArray[ii++] = Integer.valueOf(value);
              }


              startTime = System.nanoTime( );
              heapSort(newArray);
              endTime = System.nanoTime( );
               executionTime = endTime - startTime;
              System.out.print("\t\t\t" + executionTime);


              startTime = System.nanoTime( );
              bucketSort(newArray);
              endTime = System.nanoTime( );
               executionTime = endTime - startTime;
              System.out.print("\t\t\t" + executionTime);





              System.out.println("\n");


          }





         }//END OF FORLOOP
        //int arr[] = mkArr();


       /* System.out.println("Time " + selectionSort(arr));
        long startTime = System.nanoTime( );
        mergeSort(arr);
        long endTime = System.nanoTime( );

        long executionTime = endTime - startTime;
          System.out.println("Merge Sorted array time:" + executionTime);

          */

        //generate array methiod loop i - 6, do all 5 sorts clock time there go across and print arr merg/t then \n and print num returned from genArray method + clock time for each

    }
      public static long selectionSort(int arr[]) {

        long startTime = System.nanoTime( );
        int n = arr.length;

          // One by one move boundary of unsorted subarray
          for (int i = 0; i < n-1; i++)
          {
              // Find the minimum element in unsorted array
              int min_idx = i;
              for (int j = i+1; j < n; j++)
                  if (arr[j] < arr[min_idx])
                      min_idx = j;

              // Swap the found minimum element with the first
              // element
              int temp = arr[min_idx];
              arr[min_idx] = arr[i];
              arr[i] = temp;
          }
          long endTime = System.nanoTime( );
          long executionTime = endTime - startTime;
          //System.out.println("Selection Sorted array");
          //printArray(arr);
          return executionTime;
      }

      public static void mergeSort(int[] list) {
        long startTime = System.nanoTime( );
    if (list.length > 1) {
      // Merge sort the first half
      int[] firstHalf = new int[list.length / 2];
      System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
      mergeSort(firstHalf);

      // Merge sort the second half
      int secondHalfLength = list.length - list.length / 2;
      int[] secondHalf = new int[secondHalfLength];
      System.arraycopy(list, list.length / 2,
        secondHalf, 0, secondHalfLength);
      mergeSort(secondHalf);

      // Merge firstHalf with secondHalf into list
      merge(firstHalf, secondHalf, list);
    }
    long endTime = System.nanoTime( );

  }//end fo mergeSort method

  public static void merge(int[] list1, int[] list2, int[] temp) {


    int current1 = 0; // Current index in list1
    int current2 = 0; // Current index in list2
    int current3 = 0; // Current index in temp

    while (current1 < list1.length && current2 < list2.length) {
      if (list1[current1] < list2[current2])
        temp[current3++] = list1[current1++];
      else
        temp[current3++] = list2[current2++];
    }

    while (current1 < list1.length)
      temp[current3++] = list1[current1++];

    while (current2 < list2.length)
      temp[current3++] = list2[current2++];
  }




      public static double quickSort(int[] numbers) {
          long startTime = System.nanoTime( );

          quickSort(numbers, 0, numbers.length - 1);

          long endTime = System.nanoTime( );
          return endTime - startTime;
      }

      private static void quickSort(int[] numbers, int first, int last) {
          if (last > first) {
              int pivotIndex = partition(numbers, first, last);
              quickSort(numbers, first, pivotIndex - 1);
              quickSort(numbers, pivotIndex + 1, last);
          }
      }

      /** Partition the array numbers[first..last] */
      private static int partition(int[] numbers, int first, int last) {
          int pivot = numbers[first]; // Choose the first element as the pivot
          int low = first + 1; // Index for forward search
          int high = last; // Index for backward search

          while (high > low) {
              // Search forward from left
              while (low <= high && numbers[low] <= pivot)
                  low++;

              // Search backward from right
              while (low <= high && numbers[high] > pivot)
                  high--;

              // Swap two elements in the numbers
              if (high > low) {
                  int temp = numbers[high];
                  numbers[high] = numbers[low];
                  numbers[low] = temp;
              }
          }

          while (high > first && numbers[high] >= pivot)
              high--;

          // Swap pivot with numbers[high]
          if (pivot > numbers[high]) {
              numbers[first] = numbers[high];
              numbers[high] = pivot;
              return high;
          }
          else {
              return first;
          }
      }
  public static <E extends Comparable> void heapSort(E[] list) {
    // Create a Heap of integers
    Heap<E> heap = new Heap<E>();

    // Add elements to the heap
    for (int i = 0; i < list.length; i++)
      heap.add(list[i]);

    // Remove elements from the heap
    for (int i = list.length - 1; i >= 0; i--)
      list[i] = heap.remove();
  }





    /*public static int[] mkArr(int i){



       int val = (i*50000) + 50000;

        int arr[] =  new int[val];
       int count = 0;
       while(count < val){

      int x = (int)(Math.random() *100);

      arr[count] = x;


    //listP[count] = p;
    count++;
    }

return val;
     }
     */

      // Prints the array
      public static  void printArray(int arr[])
      {
          int n = arr.length;
          for (int i=0; i<n; ++i)
              System.out.print(arr[i]+" ");
          System.out.println();
      }

      // Driver code to test above

      public static int getLNum(Integer[] list){
  int maxLength = 0;

  for(Integer obj: list){
     int tempLength = String.valueOf(obj).length();
     if(tempLength > maxLength){
       maxLength = tempLength;
     }

  }
  return maxLength;

}

//public static void getDigits

      public static  void bucketSort(Integer[] list) {
          int max = getLNum(list);
          int count = 1;

           List<Integer>[] bucket = new List[10];
        //Integer[] bucket = (Integer[])new java.util.ArrayList[10];
        // Distribute the elements from list to buckets
        while(count < (max+1)){
              int key = 0;
        for (int i = 0; i < list.length; i++) {

          int num = list[i];
          for (int j = 0; j < count; j++) {
            if(String.valueOf(list[i]).length()== 1){
              key = num;
              num/=10;
            }
                key = num%10;
                num/=10;

          }//end of 2nd fr lp

          if (bucket[key] == null){
          bucket[key] = new java.util.ArrayList<Integer>();
          }
        (bucket[key]).add(list[i]);
        count++;

        }


        // Now move the elements from the buckets back to list
        int k = 0; // k is an index for list
        for (int i = 0; i < bucket.length; i++) {
          if (bucket[i] != null) {
            for (int j = 0; j < bucket[i].size(); j++)
              list[k++] = bucket[i].get(j);
             }
        }//end of loop


         }// end of while loop

        }//end of method



        public static  void printArray(Integer[] arr)
      {
          //int n = arr.length;
          for (Integer obj: arr)

          System.out.print(obj + " ");
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
