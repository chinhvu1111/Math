package interviews;

import java.util.*;

public class E76_PlatesBetweenCandles {

    public static int[] platesBetweenCandles(String s, int[][] queries) {
        int n=s.length();
        int[] leftSum =new int[n];
        int[] rightSum =new int[n];
        List<Integer> prefixSum=new ArrayList<>();
        int count=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='*'){
                count++;
            }else{
                prefixSum.add(count);
            }
        }

        int startHigh=n-1;
        int startLow=0;

        while (s.charAt(startHigh)!='|'&&prefixSum.size()!=0){
            leftSum[startHigh]=prefixSum.get(prefixSum.size()-1);
            rightSum[startHigh]=prefixSum.get(prefixSum.size()-1);
            startHigh--;
        }
        while (s.charAt(startLow)!='|'&&prefixSum.size()!=0){
            leftSum[startLow]=prefixSum.get(0);
            startLow++;
        }

        int lCount=-1;
        int rCount=0;

//        if(s.charAt(s.length()-1)=='|'){
////            lCount=0;
////            rCount=0;
//        }

        for(int i=startHigh;i>=startLow;i--){
            if(s.charAt(i)=='|'){
                lCount++;
            }
            if(prefixSum.size()-1-lCount>=0&&prefixSum.size()!=0){
                leftSum[i]=prefixSum.get(prefixSum.size()-1-lCount);
            }
            if(prefixSum.size()-1-rCount>=0&&prefixSum.size()!=0){
                rightSum[i]=prefixSum.get(prefixSum.size()-1-rCount);
            }
            if(s.charAt(i)=='|'){
                rCount++;
            }
        }
        int rsArr[]=new int[queries.length];

        for(int i=0;i<queries.length;i++){
            int left=queries[i][0];
            int right=queries[i][1];

            if(rightSum[right]-leftSum[left]>0){
                rsArr[i]=rightSum[right]-leftSum[left];
            }else{
                rsArr[i]=0;
            }
        }
        return rsArr;
    }

    public static int[] platesBetweenCandlesOptimize(String s, int[][] queries) {
        int n=s.length();
        int[] leftSum =new int[n];
        int[] rightSum =new int[n];
        Deque<Integer> prefixSum=new LinkedList<>();
        int count=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='*'){
                count++;
            }else{
                prefixSum.add(count);
            }
        }

        int startHigh=n-1;
        int startLow=0;

        while (s.charAt(startHigh)!='|'&&prefixSum.size()!=0){
            int value=prefixSum.pollLast();
            leftSum[startHigh]=value;
            rightSum[startHigh]=value;
            startHigh--;
        }
        while (s.charAt(startLow)!='|'&&prefixSum.size()!=0){
            int value=prefixSum.pollFirst();

            leftSum[startLow]=value;
            startLow++;
        }

        int lCount=-1;
        int rCount=0;

//        if(s.charAt(s.length()-1)=='|'){
////            lCount=0;
////            rCount=0;
//        }

        for(int i=startHigh;i>=startLow;i--){
            if(s.charAt(i)=='|'){
                lCount++;
                prefixSum.pollLast();
            }
            if(prefixSum.size()-1-lCount>=0&&prefixSum.size()!=0){
                leftSum[i]=prefixSum.peekLast();
            }
            if(prefixSum.size()-1-rCount>=0&&prefixSum.size()!=0){
                rightSum[i]=prefixSum.peekLast();
            }
            if(s.charAt(i)=='|'){
                rCount++;
                prefixSum.pollLast();
            }
        }
        int rsArr[]=new int[queries.length];

        for(int i=0;i<queries.length;i++){
            int left=queries[i][0];
            int right=queries[i][1];

            if(rightSum[right]-leftSum[left]>0){
                rsArr[i]=rightSum[right]-leftSum[left];
            }else{
                rsArr[i]=0;
            }
        }
        return rsArr;
    }

    public static void main(String[] args) {
//        String s="***|**|*****|**||**|*";
//        int queries[][]=new int[][]{{1,17},{4,5},{14,17},{5,11},{15,16}};
        String s="**|**|***|";
        int queries[][]=new int[][]{{2,5},{5,9}};
//        String s="***";
//        int queries[][]=new int[][]{{2,2}};
        int rs[]=platesBetweenCandles(s, queries);
        int rs1[]=platesBetweenCandlesOptimize(s, queries);
        //B??i n??y t?? duy nh?? sau:
        //C??ch 1:
        //1,
        //1.1 ??? ????y ta d??ng prefix ????? t??nh s??? * cho ?????n v??? tr?? "|"
        //+ prefixSum ????? d??ng ????? tr??? 2 v??? tr??? [i] v?? [j] cho nhau
        //1.2, *(*|***|*)*
        //Kho???ng c??ch gi???a 2 d???u ngo???c l?? 3 *
        //L??c n??y ta s??? chia ra 2 ki???u prefix:
        //+ left nearest
        //+ right nearest
        //VD:
        //                  *(*|***|*)*
        //+ left nearest:   22 233335 : T???c l?? n?? s??? l???y c???t g???n nh???t right
        //+ right nearest:  0(02222 : T???c l?? n?? s??? l???y c???t g???n nh???t left
        //** CH?? ??: V???i right, left th?? c??c value t???i "|":
        //+ Lu??n l?? gi?? tr??? th???c t??? th???t (S??? * ??? left) c???a "|"
        //1.3, V???i t?? duy tr??n th?? s??? l?????ng * n???m gi???a | | gi???a 2 v??? tr?? b???t k??? = rightSum[i] - leftSum[i]
        //
        //1.4, CH?? ??:
        //+ V???i c??c gi?? tr??? ??? 2 ?????u:
        //VD:
        //+ Case 1: (**)|**|(***)
        //--> Case kh??ng c?? | ??? 2 ?????u
        //+ Case 2: **|**|***|
        //--> Case c?? | ??? 2 ?????u
        //** Kinh nghi???m:
        //C??c cases ?????u ph???i ???????c t??nh ri??ng:
        //- V???i nearest left :
        //C??c gi?? tr??? ngo??i | : |(****) s??? ???????c t??nh nh?? nearest right
        //--> T??nh theo | b??n tr??i.
        //--> Ta s??? b??? qua c??c gi?? tr??? right ==> Cho ?????n khi g???p "|"
        //+ V???i nearest left : initLeftCount=-1
        //-> V?? g???p "|" : initLeftCount++;
        //* SUMARIZE: array sum left s??? l???ch v??? ph??a right
        //
        //- V???i nearest right :
        //C??c gi?? tr??? ngo??i | : |(****) s??? ???????c t??nh d???a tr??n gi?? tr??? c???a "|" left nh?? b??nh th?????ng
        //--> T??nh theo | b??n tr??i.
        //--> Ta s??? b??? qua c??c gi?? tr??? right ==> Cho ?????n khi g???p "|"
        //+ V???i nearest left : initLeftCount=0 --> V?? ta l???ch v??? ph??a left
        //-> V?? g???p "|" : initLeftCount++;
        //* SUMARIZE: array sum left s??? l???ch v??? ph??a left

        System.out.println();
    }
}
