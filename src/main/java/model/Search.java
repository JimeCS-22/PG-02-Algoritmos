package model;

public class Search {

    public static int binarySearch(int [] sortedArray, int value, int low, int high){

        //Caso base
        if (low > high) {
            return -1;
        }

        int mid = (low+high)/2;

        if (value == sortedArray[mid]){
            return mid;
        } else if (value < sortedArray[mid]) {
            return binarySearch(sortedArray, value, low, mid - 1);
        } else return binarySearch(sortedArray, value, mid + 1, high);

    }
}
