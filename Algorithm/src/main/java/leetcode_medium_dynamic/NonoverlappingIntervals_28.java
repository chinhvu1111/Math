/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author chinhvu
 */
public class NonoverlappingIntervals_28 {

    public static void sortArr(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] t, int[] t1) {
                return t[0]-t1[0];
            }
        });
    }

    public static boolean checkIntersection(
            int x,
            int y,
            int x1,
            int y1) {
        if (x <= x1 && y <= x1) {
            return true;
        }
        return false;
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
//        int dp[][] = new int[n][2];
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(dp[i], 0);
//        }

        sortArr(intervals);
//        dp[0][1] = 1;
        int previousY = intervals[0][1];
//        int previousX = intervals[0][0];
        int rs = 0;

        for (int i = 1; i < n; i++) {

//            if (checkIntersection(
//                    intervals[i - 1][0],
//                    intervals[i - 1][1],
//                    intervals[i][0],
//                    intervals[i][1])) {
//                if (intervals[i][0] <= previousY) {
//                    rs++;
//                    previousY = intervals[i][1];
//                }
////                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][0]);
////                dp[i][1] = Math.min(dp[i-1][1], dp[i-1][0])+1;
//            } else {
////                dp[i][1] = dp[i-1][0] + 1;
////                dp[i][0] = dp[i-1][1];
//            }
            if (intervals[i][0] < previousY) {
                rs++;
                if(previousY>=intervals[i][1]){
                    previousY=intervals[i][1];
                    //X kh??ng l??m g?? c??? c?? th??? b??? ??i
                    //V?? lu??n lu??n X<=Y n??n ta ch??? c???n so s??nh Y
//                    previousX=intervals[i][0];
                }
            } else {
                previousY = intervals[i][1];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[][] intervals = {{1, 2}, {2, 3},{3,4},{1,3}};
//        int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};
//        int[][] intervals = {{1,5},{3,8},{7,9},{8,10}};
//        int[][] intervals = {{1, 100}, {11, 22}, {1, 11}, {2, 12}};
        int[][] intervals = {{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}};

        //B??i n??y ta t?? duy nh?? sau:
        //C?? c??c ??i???u ta c???n quan t??m nh?? sau:
        //1, ????? x??c ?????nh ???????c c??ng th???c truy h???i:...
        //--> Ta s??? x??t sample c???p 3 ph???n t??? v?? c??c case c?? th??? x???y ra
        //M???c d?? ta c?? nh???ng quy l?????t nh?? sau:
        //Ta c?? th???: 
        //- B??? 1 c???nh (a,b) ????? nh???n (c,d)
        //- B??? 2 c???nh (a,b)(c,d) ????? nh???n (e,f)
        //--> M???c d?? chia th??? n??y nh??ng v???n c?? th??? c??n tr?????ng h???p bao ngo??i
        //--> T???c l?? n?? ph??? thu???c v??o nhi???u h??n 3 m???u
        //**CH?? ??: D???u hi???u nh???ng b??i nh?? th??? n??y l?? ph???i --> Bi???n m???t d??y 1 ph???n t??? ko c?? quy lu???t ==> C?? quy lu???t c??? th???
        //2, ??? ????y ta s??? sort ph???n t??? theo ()X : (X,Y) t??ng d???n
        //--> L??c ???? ta ch??? x??t quy lu???t c???a Y
        //VD: (1,5), (3,8), (7,9), (8,10)
        //rs=2
        //Ta ph???i b??? (3,8),(7,9) / b??? (3,8), (8,10)
        //V??? c?? b???n khi c?? nhi???u tr?????ng h???p c?? k???t qu???: count gi???ng nhau --> Ch???n tr?????ng h???p n??o c??ng ???????c
        //Nh??ng ????? t???i ??u nh???t:
        //3, Trong t???t c??? c??c k???t qu??? gi???ng nhau ta s??? ch??? ch???n 1 k???t qu??? v???i (y) nh??? nh???t: (x,y)
        //V?? n???u c??ng gi???m y th?? kh??? n??ng t???p h???p c??c (ph???n t??? tr?????c) giao c??c (ph???n t??? sau) "c??ng gi???m"
        //- Ta ??ang s???p x???p n??n (x1>=x) && (y1>=x1)
        //????? b??i l??: ?????m nh???ng ph???n t??? c???n b??? ??i
        //N?? giao ch??? (x1<y) rs++;
        //N???u ph???n t??? (x1,y1) : (y>=y1) --> T???t nh???t ta n??n thay th??? (x,y) <=> (x1,,y1) th???a m??n 2 ti??u ch??:
        //- Y1 ng???n h??n Y --> Gi???m t??? l??? giao v???i c??c ph???n t??? sau
        //- V?? ta ??ang x??t v???i d??y kh??ng c?? ph???n t??? giao nhau tr?????c ????:
        //C??c ph???n t??? kh??ng giao (x,y) --> ?????u c?? (yk <=x)
        //** --> Ta ho??n to??n c?? th??? thay ph???n t??? (x,y) b???ng ph???n t??? (x1,y1)
        
        //Ng?????c l???i th???a m??n --> Ta ch??? update (previousY), v?? (x<=previousY)
        //Giao hay kh??ng --> Quy???t ?????nh b???i (previousY)
        
        //Kinh nghi???m:
        //- L???y sample ph???n t??? >=3 --> S???p x???p l???i theo quy lu???t
        //- Mu???n thay th??? ph???n t??? th?? -->? (Ph???n t??? ta thay) ph???i c?? vai tr?? (t?????ng t??? / t???t h??n v??? sau) --> Ph???n t??? hi???n t???i
        //Des: Vai tr?? ??? ????y l?? vai tr?? ???nh h?????ng ?????n kq b??i to??n (Vai tr?? trong quy lu???t c???a d??y s???)
        //VD: N???u l?? MIN --> Ph???n t??? ta thay th??? ph???i (x1,y1) cho k???t qu??? MIN h??n (VD: Giao ??t h??n --> rs nh??? h??n)
        //---> So v???i d??ng (x,y)
        //VD: MAX t????ng t???
        System.out.println(eraseOverlapIntervals(intervals));
    }
}
