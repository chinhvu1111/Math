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
        //Bài này tư duy như sau:
        //Cách 1:
        //1,
        //1.1 Ở đây ta dùng prefix để tính số * cho đến vị trí "|"
        //+ prefixSum để dùng để trừ 2 vị trị [i] và [j] cho nhau
        //1.2, *(*|***|*)*
        //Khoảng cách giữa 2 dấu ngoặc là 3 *
        //Lúc này ta sẽ chia ra 2 kiểu prefix:
        //+ left nearest
        //+ right nearest
        //VD:
        //                  *(*|***|*)*
        //+ left nearest:   22 233335 : Tức là nó sẽ lấy cột gần nhất right
        //+ right nearest:  0(02222 : Tức là nó sẽ lấy cột gần nhất left
        //** CHÚ Ý: Với right, left thì các value tại "|":
        //+ Luôn là giá trị thực tế thật (Số * ở left) của "|"
        //1.3, Với tư duy trên thì số lượng * nằm giữa | | giữa 2 vị trí bất kỳ = rightSum[i] - leftSum[i]
        //
        //1.4, CHÚ Ý:
        //+ Với các giá trị ở 2 đầu:
        //VD:
        //+ Case 1: (**)|**|(***)
        //--> Case không có | ở 2 đầu
        //+ Case 2: **|**|***|
        //--> Case có | ở 2 đầu
        //** Kinh nghiệm:
        //Các cases đầu phải được tính riêng:
        //- Với nearest left :
        //Các giá trị ngoài | : |(****) sẽ được tính như nearest right
        //--> Tính theo | bên trái.
        //--> Ta sẽ bỏ qua các giá trị right ==> Cho đến khi gặp "|"
        //+ Với nearest left : initLeftCount=-1
        //-> Vì gặp "|" : initLeftCount++;
        //* SUMARIZE: array sum left sẽ lệch về phía right
        //
        //- Với nearest right :
        //Các giá trị ngoài | : |(****) sẽ được tính dựa trên giá trị của "|" left như bình thường
        //--> Tính theo | bên trái.
        //--> Ta sẽ bỏ qua các giá trị right ==> Cho đến khi gặp "|"
        //+ Với nearest left : initLeftCount=0 --> Vì ta lệch về phía left
        //-> Vì gặp "|" : initLeftCount++;
        //* SUMARIZE: array sum left sẽ lệch về phía left

        System.out.println();
    }
}
