package E1_Array;

import java.util.LinkedList;
import java.util.Queue;

public class E8_DecompressRunLengthEncodedList {

    public int[] decompressRLElist(int[] nums) {
        Queue<Integer> queue=new LinkedList<>();
        int n=nums.length;
        int i=0;
        while(i*2+1<n){
            int freq=nums[2*i];
            int value=nums[2*i+1];

            for(int j=0;j<freq;j++){
                queue.add(value);
            }
            i++;
        }
        i=0;
        int[] rs=new int[queue.size()];

        while(!queue.isEmpty()){
            rs[i++]=queue.poll();
        }
        return rs;
    }

    public int[] decompressRLElistRefactor(int[] nums) {
        int n=nums.length;
        int size=0;

        for(int i=0;2*i+1<n;i++){
            size+=nums[2*i];
        }
        int[] rs=new int[size];
        int index=0;

        for(int i=0;2*i+1<n;i++){
            int freq=nums[2*i];
            int val=nums[2*i+1];

            for(int j=0;j<freq;j++){
                rs[index++]=val;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given nums
        //- Each pair of nums ( nums[2*i], nums[2*i+1] ):
        //+ nums[2*i] chính là frequency của phần tử nums[2*i+1]
        //Ex:
        //(2,1) -> [2]
        //(3,4) -> [4,4,4]
        //* return result sau khi concat all of them.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //2 <= nums.length <= 100
        //nums.length % 2 == 0
        //1 <= nums[i] <= 100
        //- nums[i] khá nhỏ
        //
        //- Brainstorm
        //- Ta sẽ chạy index dần dần:
        //+ i sao cho (2*i+1<n)
        //
        //1.1, Optimization
        //- Ta sẽ refactor 1 chút bằng cách bỏ không dùng queue --> Tìm size của rs trước bằng cách sum all nums[2*i]
        //--> Sau đó gán dần dần
        //
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n)
        //
        //#Reference:
        //833. Find And Replace in String
        //1394. Find Lucky Integer in an Array
        //2549. Count Distinct Numbers on Board
        //2451. Odd String Difference
        //2790. Maximum Number of Groups With Increasing Length
        //927. Three Equal Parts
    }
}
