package E1_daily;

import java.util.Comparator;
import java.util.PriorityQueue;

public class E198_MaximumAveragePassRatio {

    public static double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> maxHeap=new PriorityQueue<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                double diff1=(o1[1]-o1[0])/(o1[1]*o1[1]+o1[1]);
                double diff2=(o2[1]-o2[0])/(o2[1]*o2[1]+o2[1]);
                return Double.compare(diff2, diff1);
            }
        });
        int n=classes.length;
        for(int i=0;i<n;i++){
            maxHeap.add(new double[]{classes[i][0], classes[i][1]});
        }
        double rs=0;

        for(int i=1;i<=extraStudents;i++){
            double[] maxRatioElement = maxHeap.poll();
            maxRatioElement[0]++;
            maxRatioElement[1]++;
            maxHeap.add(new double[]{maxRatioElement[0], maxRatioElement[1]});
        }
        while (!maxHeap.isEmpty()){
            double[] maxRatioElement = maxHeap.poll();
            rs+=maxRatioElement[0]/maxRatioElement[1];
        }
        return rs/n;
    }

    public static double maxAverageRatioRefer(int[][] classes, int extraStudents) {
        // Lambda to calculate the gain of adding an extra student
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>((a, b) ->
                Double.compare(b[0], a[0])
        );

        for (int[] singleClass : classes) {
            int passes = singleClass[0];
            int totalStudents = singleClass[1];
            double gain = calculateGain(passes, totalStudents);
            maxHeap.offer(new double[] { gain, passes, totalStudents });
        }

        // Distribute extra students
        while (extraStudents-- > 0) {
            double[] current = maxHeap.poll();
            double currentGain = current[0];
            int passes = (int) current[1];
            int totalStudents = (int) current[2];
            maxHeap.offer(
                    new double[] {
                            calculateGain(passes + 1, totalStudents + 1),
                            passes + 1,
                            totalStudents + 1,
                    }
            );
        }

        // Calculate the final average pass ratio
        double totalPassRatio = 0;
        while (!maxHeap.isEmpty()) {
            double[] current = maxHeap.poll();
            int passes = (int) current[1];
            int totalStudents = (int) current[2];
            totalPassRatio += (double) passes / totalStudents;
        }

        return totalPassRatio / classes.length;
    }

    private static double calculateGain(int passes, int totalStudents) {
        return (
                (double) (passes + 1) / (totalStudents + 1) -
                        (double) passes / totalStudents
        );
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is a school that has classes of students and each class will be having (a final exam).
        //- You are given a 2D integer array classes, where classes[i] = [pass-i, total-i].
        //- You know beforehand that in the (ith class),
        //  + there are (total-i total students), but only (pass-i number of students) will pass the exam.
        //- You are also given (an integer extraStudents). There are (another extraStudents brilliant students)
        // that are guaranteed to (pass the exam of any class) they are assigned to.
        //- You want to assign (each of the extraStudents) students to a class in a way
        //  + that maximizes (the average pass ratio) across all (the classes).
        //- The (pass ratio) of a class is equal to (the number of students of the class
        // that will pass the exam) divided by (the total number of students of the class).
        //  + (passed_student_num/student_num_in_class)
        //- The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.
        //  + sum_ratio/class_num
        //* Return (the maximum possible average) pass ratio after (assigning the extraStudents students).
        // Answers within 10^-5 of the actual answer will be accepted.
        //
        //Example 1:
        //
        //Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
        //Output: 0.78333
        //Explanation: You can assign the two extra students to the first class.
        //The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= classes.length <= 10^5
        //classes[i].length == 2
        //1 <= passi <= totali <= 10^5
        //1 <= extraStudents <= 10^5
        //  + Length<=10^5 ==> Time: O(n)/O(n*log(n))
        //
        //- Brainstorm
        //- a/b
        //  + (a+x/b+x)=1+(a-b)/(b+x)
        //  a+x   x+b+(a-b)
        //  --- = ---------
        //  b+x     b+x
        // a+x    a     x(b-a)    b-a
        // --- - --- =  ------ = ---------- (x increases)
        // b+x    b     b(b+x)    (b^2)/x + b
        //  b-a
        //= --- = 1 - a/b
        //   b
        //- Each time:
        //  + We add 1 student
        //  ==> Add to (the class) having (the max diff ratio)
        //- diff=
        //  b-a
        //--------
        // (b^2)+b
        //
        //1.1, Optimization
        //** Kinh nghiệm:
        //- In max/ min heap, we have two ways to create the rule:
        //  + For each sort, we calculate the value to compare in the comparator
        //  + Create new value in element and compare them in simpler way
        //
        //1.2, Complexity
        //- N is the number of classes
        //- M is the number of extra students
        //- Space: O(N)
        //- Time: O((N+M)*log(N))
        //
        //#Reference:
        //1870. Minimum Speed to Arrive on Time
        //2408. Design SQL
        //2357. Make Array Zero by Subtracting Equal Amounts
        int[][] classes = {{1,2},{3,5},{2,2}};
        int extraStudents = 2;
        System.out.println(maxAverageRatio(classes, extraStudents));
        System.out.println(maxAverageRatioRefer(classes, extraStudents));
    }
}
