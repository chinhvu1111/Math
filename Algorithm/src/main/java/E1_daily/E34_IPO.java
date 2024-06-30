package E1_daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class E34_IPO {

    public static int findCapitalIndex(int i, int j, int currentCapital, int[][] valMapCapital){
        int low=i, high=j;
        int rs=-1;

        while(low<=high){
            int mid= low+(high-low)/2;
            if(valMapCapital[mid][1]>currentCapital){
                high=mid-1;
            }else{
                rs=mid;
                low=mid+1;
            }
        }
        return rs;
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n= profits.length;
        //Space: O(n)
        //Time: O(n)
        int[][] valMapCapital =new int[n][2];

        //Time: O(n)
        for(int i=0;i<n;i++){
            valMapCapital[i][0]=profits[i];
            valMapCapital[i][1]=capital[i];
        }
        //Time: O(n*log(n))
        Arrays.sort(valMapCapital, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        //Space: O(n)
        PriorityQueue<Integer> maxGain=new PriorityQueue<>((o1, o2) -> o2-o1);
        int prevIndex=-1;
        int curCapital=w;
        //Đoạn này sẽ cần xét min
        int minTurn=Math.min(k, n);

        //Time: O(min(k,n))
        for(int i=0;i<minTurn;i++){
            //Time: O(log(n))
            int capitalIndex=findCapitalIndex(prevIndex+1, n-1, curCapital, valMapCapital);
            //Không tìm thấy được capital nào thoả mãn ==> Tức là lấy cái cũ trong maxHeap thôi
            //- Cần giữ nguyên prevIndex
//            if(capitalIndex==-1){
//                break;
//            }

            //Total in time : O(n)
            for(int j=prevIndex+1;j<=capitalIndex;j++){
                //Time: O(log(n))
                maxGain.add(valMapCapital[j][0]);
            }
            if(!maxGain.isEmpty()){
                int curVal = maxGain.poll();
                if(curVal<0){
                    break;
                }
                curCapital+=curVal;
            }
            if(capitalIndex!=-1){
                prevIndex=capitalIndex;
            }
        }

        return curCapital;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Suppose LeetCode will start its IPO soon.
        //- In order to sell (a good price of its shares) to (Venture Capital),
        //- LeetCode would like to work on some projects to increase (its capital) before (the IPO).
        //- Since it has limited resources, it can (only finish "at most" (k distinct projects)) before the IPO.
        //- Help LeetCode design the best way to maximize its total capital after finishing (at most k distinct projects).
        //- You are given (n projects) where (the ith project) has (a pure profit profits[i]) and (a minimum capital of capital[i]) is needed to start it.
        //- Initially, you have (w capital).
        // + When you "finish" (a project), you will obtain (its pure profit) and (the profit) will be added to (your total capital).
        //* Pick a list of (at most k distinct projects) from given projects to (maximize) your (final capital), and return (the final maximized capital).
        //- The answer is guaranteed to fit in a 32-bit signed integer.
        //
        //+ capital (n) Vốn
        //
        //Example 1:
        //Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
        //Output: 4
        //Explanation: Since your initial capital is 0, you can only start the project indexed 0.
        //After finishing it you will obtain profit 1 and your capital becomes 1.
        //With capital 1, you can either start the project indexed 1 or the project indexed 2.
        //Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
        //Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= k <= 10^5
        //0 <= w <= 10^9
        //n == profits.length
        //n == capital.length
        //1 <= n <= 10^5
        //0 <= profits[i] <= 10^4
        //0 <= capital[i] <= 10^9
        //+ N khá lớn --> Time : O(n*k) / O(n*log(n))
        //+ capital <= 10^9 khá lớn
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: k = 2, w = 0
        //profits = [1,2,3]
        //capital = [0,1,1]
        //- Phần gain được là:
        //gain    = [1,1,2]
        //==> sort decrementally
        //gain    = [2,1,1]
        //==> Ta sẽ ưu tiên thằng lớn nhất
        //
        //- Vì việc chọn phụ thuộc vào current capital
        //  + Có thể gain lớn ==> Capital lớn:
        //      + Không dủ capital : Lấy thằng có capital ít hơn
        //      + Đủ capital: Lấy luôn
        //- Nhu cầu là search capital >= x sao cho:
        //  + Gain là max nhất.
        //  ==> Search theo capital nữa
        //- Càng về sau thì capital càng lớn:
        //  + Mở rộng ra với các capital lớn hơn.
        //
        //==> Sort theo capital trước
        //- Tìm capital index ==> add tất cả những thằng lớn hơn (index đó) vào priorityQueue
        //  + Sort theo gain.
        //
        //- Special cases:
        //- Không tìm thấy được capital nào thoả mãn ==> Tức là lấy cái cũ trong maxHeap thôi
        //  + Cần giữ nguyên prevIndex ==> Nếu không thì nó sẽ add thừa element vào max heap
        //      + -1 --> n-1 : từ (-1 -> prevIndex cũ sẽ được add duplicate)
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O((n+k)*log(n))
        //
        //#Reference:
        //2813. Maximum Elegance of a K-Length Subsequence
        //
        int k = 2, w = 0;
        int[] profits = {1,2,3}, capital = {0,1,1};
        System.out.println(findMaximizedCapital(k, w, profits, capital));
        //
    }
}
