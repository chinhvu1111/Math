package interviews;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class E24_MergeIntervals {

    public static List<List<Integer>> mergeList(List<List<Integer>> intervals) {
        int n=intervals.size();
        Collections.sort(intervals, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0)-o2.get(0);
            }
        });
        int x=-1;
        int y=-1;
        if(n>=1){
            x=intervals.get(0).get(0);
            y=intervals.get(0).get(1);
        }
        List<List<Integer>> rs=new ArrayList<>();
        if(n==0){
            return rs;
        }
        if(n==1){
            List<Integer> integerList=new ArrayList<>();
            integerList.add(x);
            integerList.add(y);
            return rs;
        }
//        boolean isMerge=false;

        for(int i=1;i<n;i++){
            int nextX=intervals.get(i).get(0);
            int nextY=intervals.get(i).get(1);

            if(y>=nextX){
//                isMerge=true;
                x=Math.min(x, nextX);
                y=Math.max(y, nextY);
            }else{
                List<Integer> integerList=new ArrayList<>();
                integerList.add(x);
                integerList.add(y);
                rs.add(integerList);
//                isMerge=false;
                x=nextX;
                y=nextY;
            }
//            rs.add(new int[]{x, y});
        }
        List<Integer> integerList=new ArrayList<>();
        integerList.add(x);
        integerList.add(y);
        rs.add(integerList);
        return rs;
    }

    public static int[][] merge(int[][] intervals) {
        int n=intervals.length;
//        int m=intervals[0].length;
//        Integer arr[][]=new Integer[n][m];
//
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                arr[i][j]=intervals[i][j];
//            }
//        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int x=-1;
        int y=-1;
        if(n>=1){
            x=intervals[0][0];
            y=intervals[0][1];
        }
        List<int[]> rs=new ArrayList<>();
        if(n==0){
            return new int[][]{};
        }
        if(n==1){
            return new int[][]{{x,y}};
        }
//        boolean isMerge=false;

        for(int i=1;i<n;i++){
            int nextX=intervals[i][0];
            int nextY=intervals[i][1];

            if(y>=nextX){
//                isMerge=true;
                x=Math.min(x, nextX);
                y=Math.max(y, nextY);
            }else{
                rs.add(new int[]{x, y});
//                isMerge=false;
                x=nextX;
                y=nextY;
            }
//            rs.add(new int[]{x, y});
        }
        rs.add(new int[]{x, y});
        return rs.toArray(new int[rs.size()][]);
    }

    public static void main(String[] args) {
//        int intervals[][]=new int[][]{{1,3},{2,6},{8,10},{15,18}};
//        int intervals[][]=new int[][]{{1,4},{4,5}};
//        int intervals[][]=new int[][]{{1,4}};
//        int intervals[][]=new int[][]{{1,4},{5,6}};
//        int intervals[][]=new int[][]{{1,4},{0,0}};
//        int intervals[][]=new int[][]{{1,4},{0,2},{3,5}};
        int intervals[][]=new int[][]{{6,9},{2,3},{9,11},{1,5},{14,18}};
//        int intervals[][]=new int[][]{};
        //Ta tư duy như sau:
        //1, Ở đây để merge được ta cần sort array theo column X
        //1.1, Sau đó ta sẽ merge lần lượt
        //2, Ta cần phải merge theo các quy tắc sau đây:
        //2.1, Khi (x,y) và (x_next, y_next)
        //- Chúng không giao nhau (Joint) --> Ta add (x, y) vào list vì nó là element độc lập (independent).
        //- Chúng giao nhau (Disjoint) --> Do nó có thể giao nhau với các elements sau đó nữa
        //===> Khi không merge được thì mới add vào result list.
        //3, Implementation:
        //3.1, Khi disjoint --> Luôn add(x,y)
        //3.2, Bên ngoài loop --> add(x,y) : Chỉ add final element.
        //4, Sort : Có thể Arrays.sort trực tiếp.
        merge(intervals);
    }
}
