/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E1_leetcode_medium_dynamic;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author chinhvu
 */
public class E28_NonoverlappingIntervals {

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
                    //X không làm gì cả có thể bỏ đi
                    //Vì luôn luôn X<=Y nên ta chỉ cần so sánh Y
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

        //Bài này ta tư duy như sau:
        //Có các điều ta cần quan tâm như sau:
        //1, Để xác định được công thức truy hồi:...
        //--> Ta sẽ xét sample cặp 3 phần tử và các case có thể xảy ra
        //Mặc dù ta có những quy lụât như sau:
        //Ta có thể: 
        //- Bỏ 1 cạnh (a,b) để nhận (c,d)
        //- Bỏ 2 cạnh (a,b)(c,d) để nhận (e,f)
        //--> Mặc dù chia thế này nhưng vẫn có thể còn trường hợp bao ngoài
        //--> Tức là nó phụ thuộc vào nhiều hơn 3 mẫu
        //**CHÚ Ý: Dấu hiệu những bài như thế này là phải --> Biễn một dãy 1 phần tử ko có quy luật ==> Có quy luật cụ thể
        //2, Ở đây ta sẽ sort phần tử theo ()X : (X,Y) tăng dần
        //--> Lúc đó ta chỉ xét quy luật của Y
        //VD: (1,5), (3,8), (7,9), (8,10)
        //rs=2
        //Ta phải bỏ (3,8),(7,9) / bỏ (3,8), (8,10)
        //Về cơ bản khi có nhiều trường hợp có kết quả: count giống nhau --> Chọn trường hợp nào cũng được
        //Nhưng để tối ưu nhất:
        //3, Trong tất cả các kết quả giống nhau ta sẽ chỉ chọn 1 kết quả với (y) nhỏ nhất: (x,y)
        //Vì nếu càng giảm y thì khả năng tập hợp các (phần tử trước) giao các (phần tử sau) "càng giảm"
        //- Ta đang sắp xếp nên (x1>=x) && (y1>=x1)
        //Đề bài là: Đếm những phần tử cần bỏ đi
        //Nó giao chỉ (x1<y) rs++;
        //Nếu phần tử (x1,y1) : (y>=y1) --> Tốt nhất ta nên thay thế (x,y) <=> (x1,,y1) thỏa mãn 2 tiêu chí:
        //- Y1 ngắn hơn Y --> Giảm tỉ lệ giao với các phần tử sau
        //- Vì ta đang xét với dãy không có phần tử giao nhau trước đó:
        //Các phần tử không giao (x,y) --> đều có (yk <=x)
        //** --> Ta hoàn toàn có thể thay phần tử (x,y) bằng phần tử (x1,y1)
        
        //Ngược lại thỏa mãn --> Ta chỉ update (previousY), vì (x<=previousY)
        //Giao hay không --> Quyết định bởi (previousY)
        
        //Kinh nghiệm:
        //- Lấy sample phần tử >=3 --> Sắp xếp lại theo quy luật
        //- Muốn thay thế phần tử thì -->? (Phần tử ta thay) phải có vai trò (tưởng tự / tốt hơn về sau) --> Phần tử hiện tại
        //Des: Vai trò ở đây là vai trò ảnh hưởng đến kq bài toán (Vai trò trong quy luật của dãy số)
        //VD: Nếu là MIN --> Phần tử ta thay thế phải (x1,y1) cho kết quả MIN hơn (VD: Giao ít hơn --> rs nhỏ hơn)
        //---> So với dùng (x,y)
        //VD: MAX tương tự
        System.out.println(eraseOverlapIntervals(intervals));
    }
}
