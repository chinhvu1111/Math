package interviews;

import java.util.HashMap;

public class E238_MostFrequentNumberFollowingKeyInAnArray_hashmap {
    public static int mostFrequentSlow(int[] nums, int key) {
        HashMap<String, Integer> mapCount=new HashMap<>();
        int n=nums.length;
        int rsCount=0;
        int rsValue=0;

        for(int i=0;i<n-1;i++){
            String currentKey=nums[i]+"_"+nums[i+1];
            Integer value=mapCount.get(currentKey);

            if(value==null){
                value=1;
            }else{
                value++;
            }
            mapCount.put(currentKey, value);
            if(rsCount<=value&&nums[i]==key){
                rsCount=value;
                rsValue=nums[i+1];
            }
        }
        return rsValue;
    }

    public static int mostFrequentArrRefactor(int[] nums, int key) {
        int[][] arr =new int[1001][1001];
        int n=nums.length;
        int rsCount=0;
        int rsValue=0;

        for(int i=0;i<n-1;i++){
            if(nums[i]!=key){
                continue;
            }
            arr[nums[i]][nums[i+1]]++;
            if(rsCount<arr[nums[i]][nums[i+1]]){
                rsCount=arr[nums[i]][nums[i+1]];
                rsValue=nums[i+1];
            }
        }
        return rsValue;
    }

    public static int mostFrequentOptimization(int[] nums, int key) {
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int n=nums.length, rsCount=0, rsValue=0;

        for(int i=0;i<n-1;i++){
            if(nums[i]==key){
                int nextValue=nums[i+1];
                Integer count=mapCount.get(nextValue);

                if(count==null){
                    count=1;
                }else{
                    count++;
                }
                mapCount.put(nextValue, count);
                if(count>rsCount){
                    rsCount=count;
                    rsValue=nextValue;
                }
            }
        }
        return rsValue;
    }
    public static void main(String[] args) {
//        int[] arr=new int[]{2,2,2,2,3};
//        int[] arr=new int[]{1,100,200,1,100};
        int[] arr=new int[]{1,1000,2};
        System.out.println(mostFrequentSlow(arr, 1000));
        System.out.println(mostFrequentArrRefactor(arr, 1000));
        System.out.println(mostFrequentOptimization(arr, 1000));
        //
        //** Đề bài:
        //- Bài này nói về việc tìm số có số lần xuất hiện nhiều nhất nums[i+1] sao cho số đằng trước nó nums[i]==key
        //
        //** Bài này tư duy như sau:
        //1,
        //1.1,
        //- Dùng phương pháp nối <String, count>:
        //+ string : chính là nối của nums[i] + '_'+ nums[i+1] : Tính được count nhưng slow
        //- Dùng phương pháp arr[1001][1001] : Vẫn slow vì vẫn phải dùng array has 2 directions
        //1.2,
        //- Dùng 1 array has 1 direction bằng cách check (nums[i]==key)
        //--> Thì ta sẽ check map.get(nums[i+1], value)
        //1.3, Optimization:
        //- Lưu nums[i+1] vào registration: Tối ưu query.
        //
        //#Reference
        //2174. Remove All Ones With Row and Column Flips II
        //864. Shortest Path to Get All Keys
        //801. Minimum Swaps To Make Sequences Increasing
    }
}
