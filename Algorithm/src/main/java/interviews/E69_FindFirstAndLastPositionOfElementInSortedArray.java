package interviews;

public class E69_FindFirstAndLastPositionOfElementInSortedArray {

    public static int searchElement(int arr[], int target, int low, int high){
        if(low>high){
            return -1;
        }
        int mid=(low+high)/2;
        int index=-1;

        if(arr[mid]>target){
            index=searchElement(arr, target, mid+1, high);
        }else
    }

    public static int[] searchRange(int[] nums, int target) {
        int rs[]=new int[]{-1, -1};


    }

    public static void main(String[] args) {
        int arr[]=new int[]{5,7,7,8,8,10};
    }
}
