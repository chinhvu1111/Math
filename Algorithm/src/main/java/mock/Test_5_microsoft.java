package mock;

import java.util.ArrayList;
import java.util.List;

public class Test_5_microsoft {
    //1.
    //1.1, Idea
    //- Dữ kiện:
    //+ 2 array được sắp xếp tăng dần của a --> ta có 2 array (a,b), (c,d) ==> Ta không có mối quan hệ của a với c...
    //+ Các segment trong cùng 1 array sẽ không overlap với nhau
    //
    //- Bài này có thể:
    //+ Loop O(n^2)
    //+ Two pointers.
    //1.2, Tư duy như sau:
    //VD: p=0, p1=0
    //{{0,2},{5,10},{13,23},{24,25}}
    //{{1,5},{8,12},{15,24},{25,26}}
    //A1 <> B1
    //- Lấy đoạn giao
    //- Nếu B1 dài hơn --> p++
    //--> Cứ so sánh tiếp đến khi kết thúc array
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int first=0;
        int second=0;
        int n=firstList.length;
        int m=secondList.length;
        List<int[]> resultList=new ArrayList<>();

        while (first<n&&second<m){
            int[] currentFirst=firstList[first];
            int[] currentSecond=secondList[second];
            int[] currentRs=getCommon(currentFirst, currentSecond);

            //Cần khử các cases không giao nhau
            if(currentRs!=null){
                if(currentRs[1]==currentSecond[1]){
                    second++;
                }
                if(currentRs[1]==currentFirst[1]){
                    first++;
                }
                resultList.add(currentRs);
            }else{
                if(currentFirst[0]>currentSecond[1]){
                    second++;
                }else if(currentFirst[1]<currentSecond[0]){
                    first++;
                }
            }
        }
        int[][] rsArr=new int[resultList.size()][2];
        for(int i=0;i<resultList.size();i++){
//            System.out.printf("%s %s\n", e[0], e[1]);
            int[] currentValue=resultList.get(i);
            rsArr[i][0]=currentValue[0];
            rsArr[i][1]=currentValue[1];
        }

        return rsArr;
    }

    public static int[] getCommon(int[]a, int[]b){
        if(a[0]>b[1]){
            return null;
        }
        if(a[1]<b[0]){
            return null;
        }
        int start=Math.max(a[0], b[0]);
        int end=Math.min(a[1], b[1]);
        return new int[]{start, end};
    }

    public static void main(String[] args) {
        int[][] firstList = {{0,2},{5,10},{13,23},{24,25}},
                secondList = {{1,5},{8,12},{15,24},{25,26}};
        intervalIntersection(firstList, secondList);
    }
}
