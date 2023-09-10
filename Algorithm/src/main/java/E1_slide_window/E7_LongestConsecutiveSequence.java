package E1_slide_window;

import java.util.HashMap;
import java.util.HashSet;

public class E7_LongestConsecutiveSequence {

    public static int longestConsecutiveOptimization(int[] nums) {
        HashSet<Integer> numExist=new HashSet<>();

        for(int num: nums){
            numExist.add(num);
        }
        int rs=0;

        for(int num: numExist){
            if(!numExist.contains(num-1)){
                int currentNum=num;
                int currentLengthSeq=1;

                while(numExist.contains(currentNum+1)){
                    currentNum++;
                    currentLengthSeq++;
                }
                rs=Math.max(rs, currentLengthSeq);
            }
        }
        // System.out.println(numCount);
        return rs;
    }

    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> numExist=new HashSet<>();
        HashMap<Integer, Integer> numCount=new HashMap<>();

        for(int num: nums){
            numExist.add(num);
            numCount.put(num, 1);
        }
        int rs=0;

        for(int num: nums){
            if(numCount.containsKey(num)){
                int count=0;
                Integer nextNum=num+1;

                //Ex: (3),4,5, count=2
                while(numCount.containsKey(nextNum)){
                    count++;
                    if(numCount.containsKey(nextNum+1)){
                        numCount.remove(nextNum);
                    }
                    nextNum++;
                }
                int newValue=numCount.get(nextNum-1)+count;
                // numCount.remove(nextNum-1);
                rs=Math.max(rs, newValue);
                numCount.put(num, newValue);
            }
        }
        // System.out.println(numCount);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //-
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //0 <= nums.length <= 10^5
        //-10^9 <= nums[i] <= 10^9
        //--> Số khá lớn
        //
        //- Brainstorm
        //- Cần xử lý bài này trong O(n) time
        //Ex:
        //nums=[0,3,7,2,5,8,4,6,0,1]
        //+ Ở đây ta sẽ dùng thử hashmap để lưu lại độ dài trước đó
        //[0]=1
        //[1]=1
        //[2]=1
        //[3]=1
        //[4]=1
        //[5]=1
        //...
        //[8]=1
        //
        //- Làm sao để có thể tìm được đánh số tìm được length các số liên tiếp nhau?
        //Ex:
        //[0,3,7,2,5,8,4,6,0,1]
        //+ 0 --> Tìm 1,2,3,4,5,6,7,8 : return 9
        //
        //Ex:
        //[3,7,2,5,8,4,6,0,1]
        //3 --> Tìm 4,5,6,7,8 result[3]=5
        //7
        //2 --> Tìm 2,3 ==> result[2]=result[3]+1
        //5
        //...
        //0 --> Tìm 1,2 ==> result[0]=result[2]+2
        //
        //1.1, Optimization
        //- Ở đây thuật toán ta sẽ refactor lại 1 chút:
        //+ Chỉ xét những number không có phần tử theo sau nó:
        //Ex:
        //nums={0,3,7,2,5,8,4,6,0,1}
        //- Ta chỉ xét:
        //0 mà thôi ==> Vì khi xét như thế thì ta đã xét full dãy rồi ==> Không cần tư duy remove hashset như của mình bên trên.
        //
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n)
        //
        int[] nums=new int[]{0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutive(nums));
        System.out.println(longestConsecutiveOptimization(nums));
        //#Reference:
        //298. Binary Tree Longest Consecutive Sequence
        //2177. Find Three Consecutive Integers That Sum to a Given Number
        //2274. Maximum Consecutive Floors Without Special Floors
    }
}
