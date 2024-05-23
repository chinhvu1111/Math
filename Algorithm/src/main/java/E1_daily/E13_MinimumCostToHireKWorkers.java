package E1_daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class E13_MinimumCostToHireKWorkers {

    public static double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        List<int[]> sortP=new ArrayList<>();
        int n=quality.length;

        for(int i=0;i<n;i++){
            sortP.add(new int[]{wage[i], quality[i]});
        }
        Collections.sort(sortP, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (int) ((double)o1[0]/(double)o1[1]-((double)o2[0]/(double)o2[1]));
            }
        });
        double rs=0;
        double curMaxRate=0;
        double curMaxWage=0;
        //Ex:
        //worker 1 : wage: 10, quality : 20 => rate = 1/2
        //worker 2: wage : 100, quality : 30
        //
        for (int i = 0; i < n; i++) {
            System.out.printf("%s %s\n", sortP.get(i)[0], sortP.get(i)[1]);
        }
        for(int i=0;i<k;i++){
            double curCost=sortP.get(i)[0];
            double curQuality=sortP.get(i)[1];
            if(curMaxRate!=0){
                curCost=Math.max(curCost, curMaxRate*(curCost/curMaxWage));
            }
            if(curMaxRate<=curCost/curQuality){
                curMaxRate=curCost/curQuality;
                curMaxWage=curCost;
            }
            rs+=curCost;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of (the ith worker)
        // and wage[i] is the (minimum wage expectation) for the ith worker.
        //We want to (hire exactly k workers) to form a paid group.
        //- To hire a group of k workers, we must pay them according to the following rules:
        //  + Every worker in the paid group must be paid (at least) their (minimum wage expectation).
        //  + In the group, each worker's pay must be directly proportional to their quality.
        // This means if (a worker’s quality) is (double that of another worker in the group),
        // then they must be (paid twice) as much as the (other worker).
        //- Given the integer k,
        //* Return (the least amount of money) needed to form (a paid group satisfying) the above conditions.
        // Answers within (10^-5) of the actual answer will be accepted.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //n == quality.length == wage.length
        //1 <= k <= n <= 10^4
        //1 <= quality[i], wage[i] <= 10^4
        //==> O(n*k)
        //
        //- Brainstorm
        //Ex:
        //Input: quality = [10,20,5], wage = [70,50,30], k = 2
        //Output: 105.00000
        //Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
        //
        //- Phần này cần chú ý đến việc quality của workers trong paid group cần tỉ lệ vs nhau.
        //- Cần ưu tiên chọn những workers có tỉ lệ p/p tốt
        //  + Ex:
        //  quality = [10,20,5], wage = [70,50,30]
        //  => sort : quality = [5,10,20], wage = [30,70,50]
        //- Nếu chọn workers với p/p min --> max:
        //  + Về sau ta sẽ có cost min hơn do : rate sẽ tăng dần
        //      + Cost của th đằng sau chỉ có thể: (rate*x (Cái này đằng trước rate sẽ min nên không lo), the rate)
        //
        //- Phần thứ 2 là đoạn caching rate:
        //  + Mình sẽ cần cache max rate ==> Sau đó sẽ tính nhân lên.
        //
        //- Idea này không đúng vì:
        //  + Về sau nếu thuê thằng rate to ==> Thằng đằng trước tị ngay (SAI)
        //
        int[] quality = {10,20,5}, wage = {70,50,30};
        int k = 2;
        System.out.println(mincostToHireWorkers(quality, wage, k));
    }
}
