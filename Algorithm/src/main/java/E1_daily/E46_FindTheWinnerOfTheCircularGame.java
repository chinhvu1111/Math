package E1_daily;

import java.util.*;

public class E46_FindTheWinnerOfTheCircularGame {

    public static int findTheWinner(int n, int k) {
        HashSet<Integer> visited=new HashSet<>();
        int i=0;
        int sum=0;

        //a -->[(tìm)b [removed] --> (tìm)c] [starting point]
        //  + Việc tìm chung 1 pattern
        while(visited.size()<n-1){
            //(5+1)%5 = 1
            int nextPos = i-1;
            int count=0;

            //Find the removed element
            while (count<k){
                nextPos=(nextPos+1)%n;
                if(!visited.contains(nextPos)){
                    count++;
                }
            }
//            System.out.printf("start: %s, i: %s, visited: %s, count: %s", i, nextPos, visited.contains(nextPos), count);
            visited.add(nextPos);
            sum+=nextPos;
            while (visited.contains(nextPos)){
                nextPos=(nextPos+1)%n;
            }
//            System.out.printf(", j: %s\n", nextPos);
//            System.out.println(nextPos);
////            break;
//            //5%5 ==0
//            //+ Muốn keep i=5 => 5
//            //  + i%6
//            //+ 5 => 1 : (5+1)%6==0
//            //  ==> Ta sẽ cần dịch 1 -> 0
//            //  + Giảm đi 1 unit
            i=nextPos;
//            System.out.println(visited);
        }
//        System.out.println(sum);
//        System.out.println(visited);
        return n*(n-1)/2-sum+1;
    }

    public static int findTheWinnerList(int n, int k) {
        List<Integer> circle=new ArrayList<>();

        for(int i=1;i<=n;i++){
            circle.add(i);
        }
        int startIndex=0;

        while(circle.size()>1){
            int removedIndex=(startIndex+k-1)%circle.size();
            circle.remove(removedIndex);
            startIndex=removedIndex;
        }
        return circle.get(0);
    }

    public static int findTheWinnerQueue(int n, int k) {
        Queue<Integer> circle=new LinkedList<>();

        for(int i=1;i<=n;i++){
            circle.add(i);
        }

        //Ex:
        //n = 5, k = 2
        //+ k=2
        //1 -> [2]
        //3 -> [4]
        //5 -> [1]
        //+ k=3
        //+ 1 -> 3 -> 5
        //1 -> [3]
        //4 -> [5] : Vẫn là 2
        while(circle.size()>1){
            for(int i=k-1;i>=0;i--){
                circle.add(circle.remove());
            }
            circle.remove();
        }
        return circle.peek();
    }

    public static int recursionSolution(int n, int k){
        if(n==1){
            return 0;
        }
        return (recursionSolution(n-1, k)+k)%n;
    }

    public static int findTheWinnerRecursion(int n, int k) {
        return recursionSolution(n, k)+1;
    }

    public static int findTheWinnerDynamicProgramming(int n, int k) {
        int ans=0;

        for(int i=2;i<=n;i++){
            ans=(ans+k)%i;
        }
        return ans+1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are n friends that are playing a game.
        // The friends are sitting in a circle and are numbered from (1 to n) in clockwise order.
        //- More formally, moving clockwise from (the ith friend) brings you to the (i+1)th friend for 1 <= i < n,
        // and moving clockwise from the nth friend brings you to the 1st friend.
        //
        //The rules of the game are as follows:
        //- Start at the (1st friend).
        //- Count the next (k friends) in the clockwise direction (including the friend) you (started at).
        // The counting wraps around the circle and may (count) some friends (more than once).
        //- The (last friend) you counted (leaves) the circle and (loses the game).
        //- If there is still more than one friend in the circle,
        // go back to step 2 starting from the friend immediately clockwise of the friend who just lost and repeat.
        //- Else, the last friend in the circle wins the game.
        //- Given the number of friends, n, and an integer k,
        //* Return (the winner) of the game.
        //
        //Input: n = 5, k = 2
        //Output: 3
        //Explanation: Here are the steps of the game:
        //1) Start at friend 1.
        //2) Count 2 friends clockwise, which are friends 1 and 2.
        //3) Friend 2 leaves the circle. Next start is friend 3.
        //4) Count 2 friends clockwise, which are friends 3 and 4.
        //5) Friend 4 leaves the circle. Next start is friend 5.
        //6) Count 2 friends clockwise, which are friends 5 and 1.
        //7) Friend 1 leaves the circle. Next start is friend 3.
        //8) Count 2 friends clockwise, which are friends 3 and 5.
        //9) Friend 5 leaves the circle. Only friend 3 is left, so they are the winner.
        //+ count k friends liên tiếp ==> Thằng cuối out of ring
        //==> Next 1 lần
        //+ Lặp lại cho đến khi còn duy nhất 1 thằng ==> Winner.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= k <= n <= 500
        //+ N không lớn ==> O(n^3)
        //
        //- Brainstorm
        //- Đếm k lần:
        //+ i => (i+k)
        //- Số node trong ring có thể:
        //  + odd
        //  + even
        //- Qua mỗi lần đi qua hết clockwise:
        //  + Số steps jump +=1
        //Ex:
        //n = 5, k = 2
        //+ k=2
        //1 -> [2]
        //3 -> [4]
        //5 -> [1]
        //+ k=3
        //+ 1 -> 3 -> 5
        //1 -> [3]
        //4 -> [5] : Vẫn là 2
        //==> Giả thuyết tăng k theo từng turn WRONG
        //
        //a -->[(tìm)b [removed] --> (tìm)c] [starting point]
        //  + Việc tìm chung 1 pattern
        //- Chia ra 2 whiles để tìm points
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- K is the number of turns = size of window
        //  + Each turn:
        //      + Get (n/k) nodes
        //- Time: O(n)
        //- Space: O(n*k)
        //
        //2, LinkedList
        //2.0,
        //- Dùng list ==> remove bằng node cho dễ
        //  + Mỗi lần loop cái list rồi tính theo index của list thôi
        //=> easier + faster hơn là dùng visited
        //  + --> Không phải traverse lại nhiều lần
        //
        //* Kinh nghiệm:
        //- Với các dạng sát nhau + remove elements
        //  ==> Cứ dùng list cho dễ.
        //
        //3, Queue
        //* Bản chất queue:
        //- Bản chất của queue sẽ giống như RING nếu ta:
        //  + Add lại phần tử remove() từ first ==> last
        //- Ta chỉ cần đi k-2 lần remove của queue để tìm phần tử cần remove
        //  ==> Remove nó + lặp lại cho đến khi (size==1) là được
        //
        //4, Recursion
        //- n, k
        //- Dấu hiệu như sau:
        //  + Mỗi lần xoá 1 phần tử n-1
        //  + Ta shift index đi k unit
        //* Kinh nghiệm để (%n):
        //- Tính index từ (i==0)
        //
        //- From this process, there are two key insights:
        //  + The problem, initially dealing with a circle of n friends, now reduces to a subproblem with n-1 friends
        //  + In the new subproblem, friend indices shift by (-k).
        //  For instance, friend 3 moves from index 3 to index 1 in the new circle.
        //
        //- Let's define f(n,k) as function that returns (the index of the winning friend) with a game of n friends
        // and a step size of k.
        // In our example above, f(4,2) would yield the final answer.
        //
        //* Formula:
        // (n,k) = ((n-1,k) + k)%n
        //- Init:
        //  + n=1:
        //     + index=0: Vì chỉ có duy nhất 1 node
        //
        //4.1, Optimization
        //- Reduce space --> O(1)
        //- Dynamic programming
        //- i==2 :
        //  + mới tính từ (i==1) (length của n)
        //  + i==1: tính từ (length==0) ==> Không cần.
        //
        int n = 5, k = 2;
        System.out.println(findTheWinner(n, k));
        System.out.println(findTheWinnerList(n, k));
        System.out.println(findTheWinnerQueue(n, k));
        System.out.println(findTheWinnerRecursion(n, k));
        System.out.println(findTheWinnerDynamicProgramming(n, k));
        //
        //#Reference:
        //406. Queue Reconstruction by Height
        //910. Smallest Range II
        //453. Minimum Moves to Equal Array Elements
        //3181. Maximum Total Reward Using Operations II
        //1649. Create Sorted Array through Instructions
        //1476. Subrectangle Queries
        //2598. Smallest Missing Non-negative Integer After Operations
        //1243. Array Transformation
        //3082. Find the Sum of the Power of All Subsequences
    }
}
