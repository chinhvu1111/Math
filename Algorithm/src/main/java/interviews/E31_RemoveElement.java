package interviews;

public class E31_RemoveElement {

    public static int removeElement(int[] nums, int val) {
        int left=0;
        int right=nums.length-1;
        int n=nums.length;
        int rs[]=new int[n];
        int index=0;

        while (left<=right){
            if(nums[left]!=val){
                rs[index]=nums[left];
                index++;
            }
            if(left==right){
                break;
            }
            left++;
            if(index<n&&nums[right]!=val){
                rs[index]=nums[right];
                index++;
            }
            right--;
        }
        for(int i=0;i<index;i++){
            nums[i]=rs[i];
        }
        return index;
    }

    public int removeElementOptimize(int[] nums, int val) {
        int j = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != val){
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{3,2,2,3};
        int arr[]=new int[]{0,1,2,2,3,0,4,2};
//        int arr[]=new int[]{2};
//        int arr[]=new int[]{2,2,3};
//        System.out.println(removeElement(arr, 3));
        System.out.println(removeElement(arr, 2));
//        System.out.println(removeElement(arr, 3));
//        System.out.println(removeElement(arr, 3));
        //Bài này thế hiện tư duy liên quan đến
    }
}
