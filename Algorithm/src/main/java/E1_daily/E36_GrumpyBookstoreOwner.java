package E1_daily;

public class E36_GrumpyBookstoreOwner {

    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n=customers.length;
        int numSatisfiedCustomer=0;
        int left=0,right=0;
        int sum=0;
        int curNumSatisfiedCustomer=0;
        int curNumUnSatisfiedCustomer=0;

        for(int i=0;i<n;i++){
            if(grumpy[i]==0){
                numSatisfiedCustomer+=customers[i];
            }
            sum+=customers[i];
            if(minutes==i+1){
                right=i;
                curNumSatisfiedCustomer=numSatisfiedCustomer;
                curNumUnSatisfiedCustomer=sum-curNumSatisfiedCustomer;
            }
        }
        if(minutes>=n){
            return sum;
        }
        int rs=0;
        rs=Math.max(rs, numSatisfiedCustomer+curNumUnSatisfiedCustomer);
//        System.out.println(rs);
        right++;

        while(right<n){
            if(grumpy[left]==1){
                curNumUnSatisfiedCustomer-=customers[left];
            }
            left++;
            if(grumpy[right]==1){
                curNumUnSatisfiedCustomer+=customers[right];
            }
            right++;
            rs=Math.max(rs, numSatisfiedCustomer+curNumUnSatisfiedCustomer);
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is a bookstore owner that has a store open for n minutes.
        // Every minute, some number of customers enter the store. You are given an integer array customers of length n
        // where customers[i] is the number of the customer that enters the store at the start of the (ith minute)
        // and all those customers leave after the end of that minute.
        //- On some minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1
        // if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.
        //- When (the bookstore owner is grumpy), (the customers of that minute) are not satisfied, otherwise, they are satisfied.
        //- (The bookstore owner) knows a secret technique to keep themselves (not grumpy) for "minutes" (consecutive minutes), but can "only" (use it once).
        //* Return (the maximum number of customers) that can be (satisfied) throughout the day.
        //* Nói chung là:
        //- Nếu owner không grumpy thì customers đó không satisfied
        //- Point chính ở đây là chọn consecutive minutes ntn? ==> Số lượng customer lớn nhất.
        //
        //Example 1:
        //
        //Input:
        // customers =  [1,0,1,2,1,1,7,5],
        // grumpy =     [0,1,0,1,0,1,0,1],
        // minutes = 3
        //Output: 16
        //Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
        //The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //n == customers.length == grumpy.length
        //1 <= minutes <= n <= 2 * 10^4
        //0 <= customers[i] <= 1000
        //grumpy[i] is either 0 or 1.
        //+ Length của customer không quá lớn
        //+ Minutes: 10^4 -> O(k*n)/ O(n)
        //
        //- Brainstorm
        //- Point chính ở đây là chọn consecutive minutes ntn? ==> Số lượng customer lớn nhất.
        //- Dùng slide window --> trải left,right ==> left++, right++
        //  + Với grumpy[left]==1 : unsatisfiedNum-=customer[left]
        //  + Với grumpy[right]==1 : unsatisfiedNum+=customer[right]
        //  ==> rs=max(rs, unsatisfiedNum + total satisfied num customer)
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        int[] customers =   {1,0,1,2,1,1,7,5};
        int[] grumpy =      {0,1,0,1,0,1,0,1};
        int minutes = 3;
        System.out.println(maxSatisfied(customers, grumpy, minutes));
        //#Reference:
        //2143. Choose Numbers From Two Arrays in Range
        //1151. Minimum Swaps to Group All 1's Together
        //2155. All Divisions With the Highest Score of a Binary Array
    }
}
