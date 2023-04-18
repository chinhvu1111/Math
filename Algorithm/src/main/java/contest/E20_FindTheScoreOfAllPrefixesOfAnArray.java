package contest;

public class E20_FindTheScoreOfAllPrefixesOfAnArray {
    //** Đề bài:
    //- Tìm conversion array
    //- conver[i] = arr[i] + max(arr[0..i]) where max(arr[0..i]) is the maximum value of arr[j] over 0 <= j <= i.
    //
    //** Bài này tư duy như sau:
    //1.
    //1.0, Idea
    //
    public static long[] findPrefixScore(int[] nums) {
        int n=nums.length;
        int[]tmp=new int[n];
        long[] rs=new long[n];
        int max=Integer.MIN_VALUE;
        long total=0;

        for(int i=0;i<n;i++){
            max=Math.max(max, nums[i]);
            tmp[i]=nums[i]+max;
            total+=tmp[i];
            rs[i]=total;
        }
        return rs;
    }
    public static void main(String[] args) {
        int[] nums = {1,1,2,4,8,16};
    }
}
