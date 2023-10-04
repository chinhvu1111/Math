package E1_binary_search_topic;

public class E13_FindAPeakElementII {

    public static int getIndexWithMaxValue(int col, int[][] mat, int n){
        int index=-1;
        int currentMax=-1;

        for(int i=0;i<n;i++){
            if(currentMax<mat[i][col]){
                currentMax=mat[i][col];
                index=i;
            }
        }
        return index;
    }

    public static int[] findPeakGrid(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;

        int low=0, high=m-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            int indexMaxValue=getIndexWithMaxValue(mid, mat, n);
            int left=mid>0?mat[indexMaxValue][mid-1]:-1;
            int right=mid<m-1?mat[indexMaxValue][mid+1]:-1;

//            System.out.printf("indexMaxValue: %s, mid: %s, left: %s, right: %s, n: %s\n", indexMaxValue, mid, left, right, n);
            if(mat[indexMaxValue][mid]>left&&mat[indexMaxValue][mid]>right){
                return new int[]{indexMaxValue, mid};
            }else if(mat[indexMaxValue][mid]<left){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Cho 1 array tìm 1 node mà thoả mãn lớn hơn các element xung quanh nó
        //+ Array không có 2 node nào cạnh nhau mà có giá trị bằng nhau
        //- Phải xử lý trong O(n*log(m))
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //m == mat.length
        //n == mat[i].length
        //1 <= m, n <= 500
        //1 <= mat[i][j] <= 10^5
        //--> Bài này giới hạn không lớn ==> Nhưng vẫn cần làm less time
        //
        //- Brainstorm
        //Ex:
        //-1, -1, -1, -1, -1
        //-1, 10, 20, 15, -1
        //-1, 21, 30, 14, -1
        //-1, 7, 16, 32, -1
        //-1, -1, -1, -1, -1
        //
        //- Special cases:
        // 1|  2 |5 | 22 |30|20|70|20
        //-1|(30)|10|[20]|15|50|40|-1
        // 3| 4  |8 | 25 |30|60|70|20
        //
        // 1|2 |5 |22|30|20|70|20
        //-1|30|10|20|15|50|40|-1
        // 3|4 |8 |25|30|60|70|20
        //
        //- Nếu làm như bài array having 1 dimension thì nó sẽ không cover được case mà ta có thể tìm được node ở left và right thoả mãn:
        //+ left < arr[i] > right
        // Nhưng chỉ 1 direction having this condition:
        //+ bottom < arr[i] > top
        //--> Ta không thể break nhỏ các case ra để thành các array theo chiều ngang được.
        //
        //- Chia array thành 3 phần:
        //+ Central column
        //+ Left column
        //+ Right column
        //
        //- Central column:
        //+ Ta sẽ xét all nodes trong central column mà thoả mãn điều kiện > neighbors (Top and bottom)
        //+ Các cột bên left and right khá độc lập
        //--> Câu chuyện là ta chia ra 2 cases để có thể chọn left hoặc chọn right?
        //
        // 1|  2 |5  | 28 |30|20|70|20
        //-1|(30)|10 | 20 |15|50|40|-1
        // 3| 4  |8  |[33]|43|60|70|20
        // 6| 9  |33 | 23 |31|15|81|26
        //
        //* Reference solution:
        //- Ở đây ta sẽ tìm max của all row at (ith column)
        //-
        //1|2| 3 |2|3
        //3|4|(5)|6|10
        //7|2| 1 |8|9
        //- Ở đây thì thấy rằng ta sẽ tìm max nhất từ left - right
        // 5 -> 8 -> 10
        //* Mục đích của ta là sẽ tìm max của all columns thứ ith ==> Nếu có columns thứ (i-1)th / (i+1)th > max của column hiện tại
        //==> Ta sẽ move về hướng đó
        //--> max1(all of columns) < max2(all of columns) ... ==> Thế nên đến cuối ta cùng lắm sẽ lấy được max ở last column.
        //
        //- mat[index][mid]> left && mat[index][mid]> right: return new int[]{index, mid}
        //- left> mat[index][mid] : high=mid-1
        //- right> mat[index][mid] : low=mid+1
        //
        //1.1, Optimization
        //- Ở đây ta sẽ chọn n và m số nào to hơn thì để vào trong log(x)
        //==> Time sẽ được tối ưu.
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(n*log(m))
        //
        //- Idea là sẽ traverse central column + all rows mỗi column đó.
//        int[][] mat={{10,20,15},{21,30,14},{7,16,32}};
//        int[][] mat={{1,4},{3,2}};
//        int[][] mat={{1,4}};
        int[][] mat={{1}};
        int[] rs=findPeakGrid(mat);
        System.out.printf("%s %s\n", rs[0], rs[1]);
        //#Reference:
        //2018. Check if Word Can Be Placed In Crossword
        //1874. Minimize Product Sum of Two Arrays
        //2282. Number of People That Can Be Seen in a Grid
    }
}
