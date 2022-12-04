package interviews.heap;

import java.util.*;

public class E1_RelativeRanks {

    public static String[] findRelativeRanks(int[] score) {
        HashMap<Integer, Integer> valueToPosition=new HashMap<>();
        List<Integer> sortList=new ArrayList<>();

        for (int j : score) {
            sortList.add(j);
        }
        Collections.sort(sortList, (integer, t1) -> t1-integer);
        for(int i=0;i<sortList.size();i++){
            valueToPosition.put(sortList.get(i), i);
        }

        String[] rs=new String[score.length];
        for(int i=0;i<score.length;i++){
            int pos=valueToPosition.get(score[i]);

            if(pos==0){
                rs[i]="Gold Medal";
            }else if(pos==1){
                rs[i]="Silver Medal";
            }else if(pos==2){
                rs[i]="Bronze Medal";
            }else{
                rs[i]=""+ (pos+1);
            }
        }
        return rs;
    }

    public static String[] findRelativeRanksOptimize(int[] score) {
        HashMap<Integer, Integer> valueToPosition=new HashMap<>();
        PriorityQueue<Integer> sortList=new PriorityQueue<>((integer, t1) -> t1-integer);

        for (int j : score) {
            sortList.add(j);
        }
        int index=0;
        while (!sortList.isEmpty()){
            valueToPosition.put(sortList.poll(), index++);
        }

        String[] rs=new String[score.length];
        for(int i=0;i<score.length;i++){
            int pos=valueToPosition.get(score[i]);

            if(pos==0){
                rs[i]="Gold Medal";
            }else if(pos==1){
                rs[i]="Silver Medal";
            }else if(pos==2){
                rs[i]="Bronze Medal";
            }else{
                rs[i]=""+ (pos+1);
            }
        }
        return rs;
    }

    public static void println(String[] rs){
        for(int i=0;i<rs.length;i++){
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{5,4,3,2,1};
        int[] arr=new int[]{10,3,8,9,4};
        String[] rs=findRelativeRanks(arr);
        String[] rs1=findRelativeRanksOptimize(arr);
        println(rs);
        println(rs1);
    }
}
