package E1_daily;

public class E161_DefuseTheBomb {

    public static int[] decrypt(int[] code, int k) {
        int n= code.length;
        int sumNextElement=0;
        int nextIndex=1;
        int positiveK = Math.abs(k);
        int count=positiveK;

        while(count>=1){
            sumNextElement+=code[nextIndex];
            nextIndex=(nextIndex+1)%n;
            count--;
        }
        int[] rs=new int[n];

        rs[0]=sumNextElement;
        for(int i=1;i<n;i++){
            sumNextElement-=code[i];
            //0,1,2
            sumNextElement+=code[(i+positiveK)%n];
            //0,3,[6],9,12,15
            //i=2, k=5
            //
            rs[i]=sumNextElement;
        }
        if(k<0){
            int[] rs1=new int[n];
            //0,3,1,4
            //k=2
            //rs[i+k+1]=rs[i]
            //
            for(int i=0;i<n;i++){
                rs1[(i+positiveK+1)%n]=rs[i];
            }
            rs=rs1;
        }
        return rs;
    }

    public static int[] decryptReference(int[] code, int k) {
        int n=code.length;
        int[] rs=new int[n];
        if(k==0){
            return rs;
        }
        int start=1, end=k, sum=0;
        if(k<0){
            //Start from the last (Right to left)
            //- k>=0
            //[start=0,...,end]...n-1
            //- k<0
            //...,[start,...end=n-1]
            //  + rs[i] = sum(start,end)
            //- Tức là k>=0, k<0:
            //  + Chỉ khác nhau là start range lúc đầu:
            //      + k>=0:
            //          + range(start,end): start=i
            //              + Coi như tiến trước (i==0) 1 step
            //      + k<0:
            //          + range(start,end): start,end đi vòng xuống cuối
            //              + Coi như lùi sau (i==0) 1 step
            start=code.length-Math.abs(k);
            end=code.length-1;
        }
        for(int i=start;i<=end;i++){
            sum+=code[i];
        }
        for(int i=0;i<code.length;i++){
            rs[i]=sum;
            sum-=code[(start)%n];
            sum+=code[(end+1)%n];
            start++;
            end++;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array code of length of n and a key k.
        //- To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.
        //  + If k > 0, replace the ith number with (the sum of the next k numbers).
        //  + If k < 0, replace the ith number with (the sum of the previous k numbers).
        //  + If k == 0, replace (the ith number with 0).
        //- As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].
        //- Given (the circular array code) and (an integer key k),
        //* Return the decrypted code to defuse the bomb!
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //n == code.length
        //1 <= n <= 100
        //1 <= code[i] <= 100
        //-(n - 1) <= k <= n - 1
        //
        //- Brainstorm
        //- Slide window
        //
        //- Sum of the k previous elements
        //- Sum of the k next elements
        //
        //- We will calculate the (k previous elements) for the first time
        //- We will calculate the (k next elements) for the first time
        //
        //- We see that |k| can calculate to the k<0 by using fomula:
        //  + nums = [0,3,1,4]
        //  + k=2
        //  + rs[i+k+1]=rs[i]
        //- We create the result by the |k| and then using the rs to calculate rs1
        //  + return rs1=rs
        //
        int[] code = {5,7,1,4};
        int k = 3;
//        int[] rs= decrypt(code, k);
        int[] rs= decryptReference(code, k);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,",rs[i]);
        }
        System.out.println();
        //
        //1.1, Optimization
        //Start from the last (Right to left)
        //- k>=0
        //[start=0,...,end]...n-1
        //- k<0
        //...,[start,...end=n-1]
        //  + rs[i] = sum(start,end)
        //- Tức là k>=0, k<0:
        //  + Chỉ khác nhau là start range lúc đầu:
        //      + k>=0:
        //          + range(start,end): start=i
        //              + Coi như tiến trước (i==0) 1 step
        //      + k<0:
        //          + range(start,end): start,end đi vòng xuống cuối
        //              + Coi như lùi sau (i==0) 1 step
        //==> Nhờ đó ta có thể làm cách shorter.
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //2515. Shortest Distance to Target String in a Circular Array
        //2516. Take K of Each Character From Left and Right
    }
}
