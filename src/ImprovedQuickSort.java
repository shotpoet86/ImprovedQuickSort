/*Programmer:Austin Parker --- Date: Nov 6,2020
 * Assignment: PE 23.4
 * Description: Program takes in populated list, splits it into separate partitions
 * by using the objectToSort element in the list, sorts the elements in ascending order,
 * and displays results  */

import java.util.Scanner;

public class ImprovedQuickSort {
    /*variables*/
    private final int[] list;
    private int length;

    /*initializer constructor for variables*/
    public ImprovedQuickSort(int max) {
        list = new int[max];
        length = 0;
    }

    /*method inserting element into the list*/
    public void insertElement(int value) {
        list[length] = value;
        length++;
    }

    /*method to display elements into list*/
    public void displayList() {
        for (int i = 0; i < length; i++)
            System.out.print(list[i] + " ");
        System.out.println("\n");
    }

    /*method calling recursive quick sort method*/
    public void quickSort() {
        recQuickSort(0, length - 1);

    }

    public void recQuickSort(int first, int last) {
        int sizeOfList = last - first + 1;
        /*manual sort if small*/
        if (sizeOfList <= 3)
            manualSort(first, last);
            /*quick sort if larger*/
        else {
            /*call method to find mediaan of fist, middle, and last elements*/
            int objectToSort = objectToSortListElement(first, last);
            /*call method to partition list*/
            int partition = partitionList(first, last, objectToSort);
            /*recursive call to method recQuickSort*/
            recQuickSort(first, partition - 1);
            recQuickSort(partition + 1, last);

        }
    }

    /*method to find the objectToSort of first, middle, and last elements*/
    public int objectToSortListElement(int first, int last) {
        int middle = (first + last) / 2;
        /*orders first and middle*/
        if (list[first] > list[middle])
            swap(first, middle);
        /*orders first and last*/
        if (list[first] > list[last])
            swap(first, last);
        /*orders first and last*/
        if (list[middle] > list[last])
            swap(middle, last);
        /*pivot is put on last element*/
        swap(middle, last - 1);
        /*returns objectToSort value*/
        return list[last - 1];

    }

    /*swap elements method*/
    public void swap(int index1, int index2) {
        int temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    /*partition the list method*/
    public int partitionList(int first, int last, int pivot) {
        int firstPartition = first;
        int lastPartition = last - 1;
        while (true) {
            /*finds element bigger than pivot*/
            while (list[++firstPartition] < pivot) ;
            /*finds element smaller than pivot*/
            while (list[--lastPartition] > pivot) ;
            /*if the pointers cross, the partition is done*/
            if (firstPartition >= lastPartition)
                break;
            else
                /*if the pointers dont cross, program will swap elements*/
                swap(firstPartition, lastPartition);
        }
        /*will restore the pivot elements*/
        swap(firstPartition, last - 1);
        /*returns pivot elements location*/
        return firstPartition;
    }

    /*method sorting list that has three or less elements*/
    public void manualSort(int first, int last) {
        int sizeOfList = last - first + 1;
        if (sizeOfList <= 1)
            return;
        if (sizeOfList == 2) {
            if (list[first] > list[last])
                swap(first, last);
        } else {
            /*sorts first, middle, and last*/
            if (list[first] > list[last - 1])
                swap(first, last - 1);//swaps first and middle
            if (list[first] > list[last])
                swap(first, last);//swaps first and last
            if (list[last - 1] > list[last])
                swap(last - 1, last);//swaps middle and last
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        while (!stop) {

            /*creates a objectToSort object*/
            int maxsizeOfList = 10;
            ImprovedQuickSort objectToSort = new ImprovedQuickSort(maxsizeOfList);
            objectToSort.insertElement(2);
            objectToSort.insertElement(3);
            objectToSort.insertElement(2);
            objectToSort.insertElement(5);
            objectToSort.insertElement(6);
            objectToSort.insertElement(1);
            objectToSort.insertElement(-2);
            objectToSort.insertElement(3);
            objectToSort.insertElement(14);
            objectToSort.insertElement(12);

            /*displays elements before sort*/
            System.out.println("\nList of elements before sort: ");
            objectToSort.displayList();
            objectToSort.quickSort();

            /*displays elements after sort*/
            System.out.println("List of elements after sort: ");
            objectToSort.displayList();
            System.out.println("To end program enter \"n\". To run program again enter \"y\"");
            String name = scanner.nextLine().toLowerCase();
            if (name.equals("n")) {
                stop = true;
            }
        }/*end of while loop*/
    }/*end of main*/
}/*end of ImprovedQuickSort*/
