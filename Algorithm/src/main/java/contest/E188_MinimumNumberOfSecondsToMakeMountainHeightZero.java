package contest;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class E188_MinimumNumberOfSecondsToMakeMountainHeightZero {

    public static long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        //Space: O(n)
        PriorityQueue<Pair<Long, Integer>> sortWorkerTimes=new PriorityQueue<>(new Comparator<Pair<Long, Integer>>() {
            @Override
            public int compare(Pair<Long, Integer> o1, Pair<Long, Integer> o2) {
                return (int) (o1.getKey() - o2.getKey());
            }
        });

        for(int i=0;i<workerTimes.length;i++){
            sortWorkerTimes.add(new Pair<>((long)workerTimes[i], i));
        }
//        Pair<Long, Integer> firstE=sortWorkerTimes.poll();
//        long time=firstE.getKey();
//        mountainHeight--;
//        sortWorkerTimes.add(new Pair<>(time*2, firstE.getValue()));
        //Mỗi loop turn cần giảm height đi 1
        long time=0;
        //Space: O(n)
        long[] count=new long[workerTimes.length];
        Arrays.fill(count, 1);
        //Time: O(n*log(n))
        while(mountainHeight>0){
            Pair<Long, Integer> minWorkTime=sortWorkerTimes.poll();
            time=Math.max(time, minWorkTime.getKey());
            count[minWorkTime.getValue()]++;
            sortWorkerTimes.add(new Pair<>(minWorkTime.getKey()+workerTimes[minWorkTime.getValue()]*count[minWorkTime.getValue()], minWorkTime.getValue()));
            mountainHeight--;
//            long count=1;
            //- this code handle:
            //  + If we have processed for time
            while (time>=sortWorkerTimes.peek().getKey()&&mountainHeight>0){
                Pair<Long, Integer> minE = sortWorkerTimes.poll();
                count[minE.getValue()]++;
                sortWorkerTimes.add(new Pair<>(minE.getKey()+workerTimes[minE.getValue()]*count[minE.getValue()], minE.getValue()));
                mountainHeight--;
            }
//            System.out.println(sortWorkerTimes);
//            System.out.printf("Time: %s, curTime:%s\n", time, minWorkTime);
        }
        return time;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (an integer mountainHeight) denoting (the height of a mountain).
        //- You are also given an integer (array workerTimes) representing (the work time) of workers (in seconds).
        //- The workers work simultaneously to reduce (the height of the mountain).
        //  + For worker i:
        //      + To decrease the mountain's height by x, it takes
        //          + workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x seconds. For example:
        //          ==> Càng làm càng mệt
        //      + To reduce the height of the mountain by 1, it takes workerTimes[i] seconds.
        //      + To reduce the height of the mountain by 2, it takes workerTimes[i] + workerTimes[i] * 2 seconds, and so on.
        //* Return an integer representing (the minimum number of seconds) required for the workers to make (the height of the mountain 0).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= mountainHeight <= 10^5
        //1 <= workerTimes.length <= 10^4
        //1 <= workerTimes[i] <= 10^6
        //  + workerTimes khá lớn: Time: O(n*k)
        //  + workerTimes[i] lớn ==> Long
        //
        //** Brainstorm
        //Example 1:
        //
        //Input: mountainHeight = 4, workerTimes = [2,1,1]
        //Output: 3
        //
        //Explanation:
        //One way the height of the mountain can be reduced to 0 is:
        //  + Worker 0 reduces the height by 1, taking workerTimes[0] = 2 seconds.
        //  + Worker 1 reduces the height by 2, taking workerTimes[1] + workerTimes[1] * 2 = 3 seconds.
        //  + Worker 2 reduces the height by 1, taking workerTimes[2] = 1 second.
        //Since they work simultaneously, the minimum time needed is
        //  + max(2, 3, 1) = 3 seconds.
        //- Chia sao cho tối ưu:
        //  + Để time cùng hoàn thành min
        //  ==> min chính là thời gian max nhất để worker slowest complete
        //- Nên dùng hết toàn bộ các workers.
        //  ==> Tối ưu nhất
        //- Bản chất là mỗi thằng workers sẽ thực hiện lần lượt 1 unit 1:
        //  + time của worker[i] sẽ là:
        //      + time = workerTimes[0]
        //  ==> Lần tới thực hiện sẽ là thằng có worertime[i]*k ít nhất
        //  ==> Ta sẽ đi dần dần
        //
        //- Greedy được không?
        //  +
        //mountainHeight = 4, workerTimes = [2,1,1]
        //h=1:
        //  + Chọn i=1:
        //      + mountainHeight = 4 - 1 = 3
        //      + time = 1
        //- workers time sẽ tăng lên:
        //+ update : workerTimes = [2,2,1]
        //  + Chọn i=2:
        //      + mountainHeight = 3 - 1 - 1 = 1 (Do time = 2 có thể đi 2 lần)
        //      + time = 2
        //+ update : workerTimes = [2,2,2]
        //  + Chọn i=0:
        //      + mountainHeight = 1 - 1 = 0 (Vì time = 3 rồi)
        //      + time = 3
        //- priority queue cho workers
        //  + Update time liên tục
        //==> final time chính là rs
        //
        //==> WRONG
        //- Chỉ cần loop từng second là được
        //  + second++
        //==> Với second = x
        //  + Ta có thể cut được bao height == (số lượng element >= x)
        //Ex:
        //mountainHeight = 4, workerTimes = [2,1,1]
        //second=3:
        //  + workTime = 1:
        //      + cut: 2 unit
        //  + workTime = 2:
        //      + cut: 1 unit
        //  ==> 2*2 + 1 = 5>= mountainHeight
        //  ==> Time = 3
        //Ex:
        //- Time/workTime = 3/1 = 3 = n*(n+1)/2
        //==> Time khá lớn nếu loop (TLE)
        //- Thay vì loop:
        //- Chọn số time thì sao:
        //  + x : (low,high)
        //      + Sau đó loop từng element:
        //          + sum(height)
        //      ==> Loop để tìm được height với mỗi workTimes[i] khá khó vì cần giải:
        //      + Pt bậc 2
        //      + Hoặc là cộng dần w+2*w+...*k*w == x
        //
        //- Giảm từng height có vẻ ổn
        //- Khó khăn:
        //+ Trong lúc chọn solution không biết chọn time hay height
        //  + Time thì khó hơn
        //  + Height is easier
        //- Trong loop không biết init loop ntn cho hợp lý:
        //  + Cần count để tăng dần hệ số:
        //      + Key là current value ==> next: key + count[i]*key
        //  + Ở đây cần tìm thằng có min time nhất ==> Sau đó mới xét tiếp được
        //      + Sau khi xét 1 workers ==> Update time mới
        //          + Ta cần xét thêm tất cả các workers có thể làm trong thời gian đó nữa.
        //          ==> Lúc này không cần update time.
        //      + Back lại beginning of the loop:
        //          + Ta cần tăng time ==> Lúc đó sẽ xét worker mới (Có workerTime min) ==> Kể cả nhưng thằng được update.
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(n*log(n))
        //
        int mountainHeight = 4;
        int[] workerTimes = {2,1,1};
        System.out.println(minNumberOfSeconds(mountainHeight, workerTimes));
    }
}
