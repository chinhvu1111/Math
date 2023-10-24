package E1_two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E9_OptimalPartitionOfString {

    public static int partitionString(String s) {
        int n=s.length();
        if(n==0){
            return 0;
        }
        int[] lastSeen=new int[26];
        Arrays.fill(lastSeen, -1);
//        int low=0, high=0;
        int rs=1;
        int low=0;

        //abacaba
        //ssssss
        for(int i=0;i<n;i++){
            int c=s.charAt(i)-'a';
            if(lastSeen[c]!=-1&&lastSeen[c]>=low){
                rs++;
                low=i;
            }
            lastSeen[c]=i;
        }
        return rs;
    }

    public static void main(String[] args) {
        String s = "abacaba";
//        String s = "ssssss";
        //- Với case : sssssss
        //+ Ta luôn tính trước 1 part ==> Vì ban đầu kiểu gì ta cũng lấy ít nhất result =1
        //  + Nếu chia được ==> rs++
        //
        System.out.println(partitionString(s));
        //#Reference
        //395. Longest Substring with At Least K Repeating Characters
        //915. Partition Array into Disjoint Intervals
    }
}
