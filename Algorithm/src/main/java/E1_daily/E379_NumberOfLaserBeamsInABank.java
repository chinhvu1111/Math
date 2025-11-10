package E1_daily;

import java.util.ArrayList;
import java.util.List;

public class E379_NumberOfLaserBeamsInABank {

    public static int numberOfBeams(String[] bank) {
        int n=bank.length;
        List<Integer> sumList=new ArrayList<>();

        for(int i=0;i<n;i++){
            String curBank=bank[i];
            int curSum=0;
            for(int j=0;j<curBank.length();j++){
                curSum+=curBank.charAt(j)-'0';
            }
            if(curSum!=0){
                sumList.add(curSum);
            }
        }
        int rs=0;
        for(int i=0;i<sumList.size()-1;i++){
            rs+=sumList.get(i)*sumList.get(i+1);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- (Anti-theft security devices) are activated inside (a bank).
        //- You are given a 0-indexed binary string array bank representing (the floor plan) of the bank,
        //- which is an m x n 2D matrix. bank[i] represents the ith row, consisting of '0's and '1's.
        //  + '0' means the cell is empty, while
        //  + '1' means the cell has (a security device).
        //- There is (one laser beam) between any two security devices if both conditions are met:
        //  + The two devices are located on two different rows:
        //      + r1 and r2, where r1 < r2.
        //  + For each row i where r1 < i < r2, there are no security devices in (the ith row).
        //- Laser beams are independent, i.e., one beam does not interfere nor join with another.
        //* Return the total number of laser beams in the bank.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == bank.length
        //n == bank[i].length
        //1 <= m, n <= 500
        //bank[i][j] is either '0' or '1'.
        //  + m,n<=500 ==> Time: O(n*m^2)
        //
        //* Brainstorm:
        //- For each device
        //==> We connect that to (the nearest device)
        //3,0,5,2 ==> prefix multiply of two of elements
        //
        //1.1, Case
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n*m)
        //- Space: O(n) -> O(1)
        //
        String[] bank = {"011001","000000","010100","001000"};
        System.out.println(numberOfBeams(bank));
    }
}
