package E1_leetcode_medium_dynamic;

import java.util.HashMap;

public class E130_MinimumScoreTriangulationOfPolygon {

    public static HashMap<Pair, Integer> memo;
    public static int solution(int x, int y, int[] values){
        if(x==y-2){
            return values[x]*values[y]*values[x+1];
        }
        if(x>=y-1){
            return 0;
        }
        int curRs=Integer.MAX_VALUE;
        Pair curKey = new Pair(x,y);
        if(memo.containsKey(curKey)){
            return memo.get(curKey);
        }
        for(int i=x+1;i<y;i++){
            int left = solution(x, i, values);
            int right = solution(i, y, values);
            int curVal = left + right + values[x]*values[y]*values[i];
            curRs=Math.min(curRs, curVal);
        }
        memo.put(curKey, curRs);
        return curRs;
    }

    public static int minScoreTriangulation(int[] values) {
        int n=values.length;
        memo=new HashMap<>();
        return solution(0, n-1, values);
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Cho 1 triangulation (Đa giác) được biểu diễn bằng array nums theo clockwise order
        //- Chia triangulation thành n-1 triangles, với mỗi triangle:
        //  + value = product of the values of its vertices.
        //  + Total score = sum all of the scores of (n-1) trianles.
        //* Return the (smallest possible total score) that you can achieve with (some triangulation) of the polygon.
        //* Đa giác lồi là một đa giác đơn trong đó không có đoạn thẳng nối giữa hai điểm trên đường biên (đi ra ngoài) đa giác.
        //  + Luôn nằm trên 1 nữa mặt phẳng khi kéo dài 1 edge nào đó.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //n == values.length
        //3 <= n <= 50
        //1 <= values[i] <= 100
        //
        //- Brainstorm
        //Ex:
        //Input: values = [1,3,1,4,1,5]
        //Output: 13
        //Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
        //       1 ---- 4
        //     / |    \  \
        //   3   |        1
        //     \ |   /  /
        //      1 ---- 5
        //- Questions:
        //  + Làm sao chia thành n-2 triangles được?
        //- Liệu n đỉnh có thể tính theo n-1 đỉnh được không?
        //  ==> Cái này hơi khó
        //- Ta sẽ xét từng vertex xem nên kết hợp vertex mới như thế nào so với các vertices cũ.
        //Ex:
        //Input: values = [1,3,1,4,1,5]
        //   3  ---  1
        //     \   /
        //      1
        //- Vertex = 4
        //==> 4 bắt buộc nằm khác phía với các vertices đã có.
        //   3  ---  1 --- 4
        //     \   /
        //      1
        // + 4 sẽ được tính dựa trên:
        //  + 2 điểm trước đó: 3,1 ==> IDEA này chưa rõ ràng
        // + 4 có thể tính gộp vào:
        //  + (3,1) ==> + Thêm phần đa giác trước 3
        //  + Nối tiếp với (1,3) ==> + thêm phần đa giác trước 1
        //- Giả sử dp[i] là sum phần đa giác max nhất cho đến value 1 ==> Quy về index
        //  + Ta sẽ tính bình thường cho đến last node ==> Ta cần nối vào first nữa.
        //---> Idea trên có 1 lỗ hổng
        //- Thêm 1 vertex ta sẽ có thêm nhiều cách nối
        //Ex:
        //   1 ---- 4
        // /          \
        //3            (1)
        // \
        //   1
        //+ Case 1
        //   1 ---- 4
        // /     \    |
        //3 -------- (1)
        // \
        //   1
        //-> Cộng thêm 3*1*1 + 1*4*1 = 7
        //+ Case 2
        //   1 ------ 4
        // /    /     |
        //3 -------- (1)
        // \
        //   1
        //-> Cộng thêm 3*1*4 + 3*4*1 = 24
        //
        //- Liệu nếu chia thành sum của các triangulation khác nhau + tính dần dần theo nhau thì sao?
        //* Hint là dp[0][n-1] là kết quả
        //- Questions:
        //  + Nếu có triangle được tạo từ 2 điểm (0,n-1) thì sao?
        //  ==> Nếu chia ntn thì sẽ không xét được các triangle kết hợp với (0,n-1)
        //  --> Ta sẽ chia theo vertex
        //- Triangle được tạo ra có thể kết hợp từ (0,n-1) với (i): (1 -> n-2)
        //  + A[1:k] và A[k+1:n-1]
        //  + Tức là luôn cộng thêm triangle kết hợp từ (0,n-1, k) + Nhưng cộng thêm 2 phần đa giác (left/ right) nữa
        //--> Có thể làm top down (memoziation)
        //Ex
        //   1 ------ 4
        // /          |
        //3 --------- 1
        // \         /
        // (1 ---- 5) ==> (1 -- 5 sẽ kết hợp với lần lượt all vertices)
        //
        //
        //
//        int[] values = {1,3,1,4,1,5};
        int[] values = {3,7,4,5};
        System.out.println(minScoreTriangulation(values));
        //#Reference:
        //2439. Minimize Maximum of Array
        //2788. Split Strings by Separator
        //757. Set Intersection Size At Least Two
    }
}
