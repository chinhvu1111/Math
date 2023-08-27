package E1_two_pointers;

import java.awt.*;
import java.util.*;
import java.util.List;

public class E4_MostProfitAssigningWork {

    public static class Node{
        int diff;
        int profit;
        public Node(int diff, int profit){
            this.diff=diff;
            this.profit=profit;
        }
        public String toString() {
            return diff +" : "+profit;
        }
    }

    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        //Space : O(n)
        List<Node> diffProfits=new ArrayList<Node>();
        int n=worker.length;
        int m=profit.length;
        //Time : O(n*log(n))
        Arrays.sort(worker);

        for(int i=0;i<m;i++){
            diffProfits.add(new Node(difficulty[i], profit[i]));
        }
        //Time : O(n*log(n))
        diffProfits.sort(Comparator.comparingInt(o -> o.diff));

        //Prefix max
        int maxProfit=0;

        //Time : O(n)
        for (Node currentNode : diffProfits) {
            maxProfit = Math.max(maxProfit, currentNode.profit);
            currentNode.profit = maxProfit;
        }
        // System.out.println(diffProfits);
        int i=0, j=0;
        int curMaxProfit=0;
        //Space : O(n)
        //Time : O(n)
        int[] rs=new int[n];

        //Time : O(n+m)
        while(i<n||j<m){
            if(i<n){
                if(j<m&&worker[i]>=diffProfits.get(j).diff){
                    curMaxProfit=diffProfits.get(j).profit;
                    rs[i]=curMaxProfit;
                    // System.out.printf("Assign : index=%s -> profit %s\n", i, rs[i]);
                    j++;
                }else{
                    rs[i]=curMaxProfit;
                    i++;
                }
            }else{
                j++;
            }
        }
        int total=0;
        //Time : O(n)
        for(i=0;i<n;i++){
            total+=rs[i];
            // System.out.printf("%s, ", rs[i]);
        }
        return total;
    }

    public static int maxProfitAssignmentRefactor(int[] difficulty, int[] profit, int[] worker) {
        int N = difficulty.length;
        Point[] jobs = new Point[N];
        for (int i = 0; i < N; ++i)
            jobs[i] = new Point(difficulty[i], profit[i]);
        Arrays.sort(jobs, Comparator.comparingInt(a -> a.x));
        Arrays.sort(worker);

        int ans = 0, i = 0, best = 0;
        for (int skill: worker) {
            while (i < N && skill >= jobs[i].x)
                best = Math.max(best, jobs[i++].y);
            ans += best;
        }

        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given difficult, profit, worker
        //- difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
        //- worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty (at most) worker[j]).
        //- Every worker can be assigned (at most) one job --> Mỗi người chỉ được thực hiện tối đa 1 job
        //- but one job can be completed (multiple times) --> Mỗi job có thể thực hiện nhiều lần bởi nhiều người.
        //* Return maximum profit có thể có sau khi assigning the workers các jobs
        //Ex:
        //difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
        //100
        //Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.
        //--> Tập trung lấy thằng có profit lớn nhất có thể với work load đã cho
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //n == difficulty.length
        //n == profit.length
        //m == worker.length
        //1 <= n, m <= 10^4
        //1 <= difficulty[i], profit[i], worker[i] <= 10^5
        //--> sum (Long type)
        //
        //- Brainstorm : Sort + Binary search
        //Ex:
        //difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
        //- Với workload=5 (i=1) ==> Ta sẽ lấy profit nhiều nhất có thể với (difficult < 5)
        //- Vì profit chưa chắc đã tỉ lệ thuận với difficult --> Nên ta cần phải check rõ thông tin
        //Ex:
        //difficulty = [85,47,57], profit = [24,66,99], worker = [56,25,25]
        //Ex: work[i] =56
        //sort difficult = [47,57,85] --> getting index={0,1} ==> Sort theo profit để lấy cái có max profit lớn nhất.
        //sort profit = [99,66,26] <=> indexes [2,1,0]
        //
        //- Bài này có thể dùng 2 pointers được không
        //Ex:
        //difficulty = [85,47,57], profit = [24,66,99], worker = [56,50,25]
        //sort (worker) = [25,50,56]
        //sort (difficult) = [47,57,85]
        //(profit) map theo difficult = [66,99,24]
        //==> Gộp
        //Sort theo difficult = [ (47,66), (57,99), (85,24) ]
        //
        //- Ở đây ta có thể dùng thêm prefix sum (prefix max)
        //+ Sort theo difficult = [ (47,66), (57,99), (85,24) ]
        //+ Prefix max= [66,99,99]
        //==> Kết hợp thêm 2 pointers để xử lý 2 array theo dạng greedy.
        //
        //1.1, Optimization
        //- Refactor code
        //
        //1.2, Complexity:
        //- Space : O(n)
        //- Time : O(n*m+n*log(n))
        //
        int[] difficulty = {85,47,57}, profit = {24,66,99}, worker = {40,25,25};
        System.out.println(maxProfitAssignment(difficulty, profit, worker));
        System.out.println(maxProfitAssignmentRefactor(difficulty, profit, worker));
        //#Reference:
        //2071. Maximum Number of Tasks You Can Assign
        //2410. Maximum Matching of Players With Trainers
    }
}
