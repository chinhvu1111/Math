package E1_heap_priority_queue;

import java.util.*;

public class E4_HighFive {

    public static int[][] highFive(int[][] items) {
        //Space : O(n)
        TreeMap<Integer, PriorityQueue<Integer>> idToScores=new TreeMap<>();

        //Time : O(n)
        for(int[] e: items){
            int id=e[0];
            int score=e[1];
            PriorityQueue<Integer> queues=idToScores.get(id);

            if(!idToScores.containsKey(id)){
                queues=new PriorityQueue<>();
                idToScores.put(id, queues);
            }
            if(queues.size()==5){
                if(queues.peek()<score){
                    //Time : O(log(N))
                    queues.poll();
                }else{
                    continue;
                }
            }
            //Time : O(log(N))
            queues.add(score);
        }
        int[][] rs=new int[idToScores.size()][2];
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> treeSet = idToScores.entrySet();
        int i=0;

        //Time : O(k)
        for(Map.Entry<Integer, PriorityQueue<Integer>> e:treeSet){
            rs[i][0]=e.getKey();
            int averageScore=0;

            //Time : O(N)
            while(!e.getValue().isEmpty()){
                averageScore+=e.getValue().poll();
            }
            rs[i][1]=averageScore/5;
            i++;
        }
        return rs;
    }

    public static int[][] highFiveSort(int[][] items) {
        //Space : O(n)
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o2[1]-o1[1];
            }
        });
        List<int[]> list=new ArrayList<>();
        int n=items.length;
        int i=0;

        while(i<n){
            int id=items[i][0];
            int curAvg=0;
            int j;

            for(j=i;j<n&&j-i<=4&&items[j][0]==id;j++){
                curAvg+=items[j][1];
            }
            while(j<n&&items[j][0]==id){
                j++;
            }
//            System.out.printf("i: %s, j: %s, curAvg: %s\n", i, j, curAvg);
            list.add(new int[]{id, curAvg/5});
            i=j;
        }
        int[][] rs=new int[list.size()][2];

        for(i=0;i<list.size();i++){
            rs[i][0]=list.get(i)[0];
            rs[i][1]=list.get(i)[1];
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- (A student's top five average) is calculated by taking the sum of their top five scores and dividing it by 5 using integer division.
        //- Given a list of the scores of different students, items, where items[i] = [IDi, scorei]
        // represents (one score) from a student with (IDi), calculate each student's (top five average).
        //* Return the answer as an array of pairs result, where result[j] = [IDj, topFiveAveragej]
        // represents the (student with IDj) and their (top five average).
        //- Sort result by IDj in (increasing order).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= items.length <= 1000
        //items[i].length == 2
        //1 <= IDi <= 1000
        //0 <= score-i <= 100
        //
        //- Brainstorm
        //- Về cơ bản bài toán:
        //- sort (Id, sort(scores) for each student)
        //
        //- Sort key + map to score ==> TreeMap
        //- Value : PriorityQueue --> It could be duplicated
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- N is the number of entry in the input array
        //- Space: O(N)
        //- Time: O(N*log(N))
        //
        //2. Sort theo id trước --> Sort theo value
        //2.0, Idea
        //- Sort theo kiểu này --> Tính dần dần được
        //2.1, Optimization
        //2.2, Complexity
        //- Space : O(N)
        //- Time : O(N*Log(N))
        //
//        int[][] arr={{1,91},{1,92},{2,93},{2,97},{1,60},{2,77},{1,65},{1,87},{1,100},{2,100},{2,76}};
//        int[][] rs=highFive(arr);
        int[][] arr={{1,91},{1,92},{2,93},{2,97},{1,60},{2,77},{1,65},{1,87},{1,100},{2,100},{2,76}};
        int[][] rs=highFiveSort(arr);

        for (int[] r : rs) {
            System.out.printf("%s %s\n", r[0], r[1]);
        }
        //#Reference:
        //2660. Determine the Winner of a Bowling Game
        //
    }
}
