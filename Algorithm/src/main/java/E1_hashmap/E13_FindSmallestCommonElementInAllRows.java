package E1_hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class E13_FindSmallestCommonElementInAllRows {

    public static int smallestCommonElement(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;
        //Space : O(n*m*4)
        HashMap<Integer, HashSet<Integer>> mapValToIndexRow=new HashMap<>();

        //Time : O(n*m)
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                HashSet<Integer> rowIndexes=mapValToIndexRow.get(mat[i][j]);

                if(rowIndexes==null){
                    rowIndexes=new HashSet<>();
                }
                rowIndexes.add(i);
                mapValToIndexRow.put(mat[i][j], rowIndexes);
            }
        }
        int rs=Integer.MAX_VALUE;

        //Time : O(n*m)
        for(int key: mapValToIndexRow.keySet()){
            if(mapValToIndexRow.get(key).size()==n){
                rs=Math.min(rs, key);
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static int smallestCommonElementOptimization(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;
        //Space : O(n*m*4)
        HashMap<Integer, HashSet<Integer>> mapValToIndexRow=new HashMap<>();

        //Time : O(n*m)
        int rs=Integer.MAX_VALUE;
        for(int i=0;i<m;i++){
            boolean isValidLoop=false;

            for(int j=0;j<n;j++){
                HashSet<Integer> rowIndexes=mapValToIndexRow.get(mat[j][i]);

                if(rowIndexes==null){
                    rowIndexes=new HashSet<>();
                }
                rowIndexes.add(j);
                mapValToIndexRow.put(mat[j][i], rowIndexes);
                if(rowIndexes.size()==n){
                    isValidLoop=true;
                    rs=Math.min(rs, mat[j][i]);
                }
            }
            if(isValidLoop){
                break;
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static int smallestCommonElementShortenOptimization(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;
        //Space : O(n*m*4)
        HashMap<Integer, HashSet<Integer>> mapValToIndexRow=new HashMap<>();
        //Time : O(n*m)
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                HashSet<Integer> rowIndexes=mapValToIndexRow.get(mat[j][i]);

                if(rowIndexes==null){
                    rowIndexes=new HashSet<>();
                }
                rowIndexes.add(j);
                mapValToIndexRow.put(mat[j][i], rowIndexes);
                if(rowIndexes.size()==n){
                    return mat[j][i];
                }
            }
        }
        return -1;
    }

    public static int smallestCommonElementBinarySearch(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;

        //Time : O(m)
        for(int i=0;i<m;i++){
            int value=mat[0][i];
            boolean found=true;

            //Time : O(n)
            for(int j=1;j<n&&found;j++){
                //Time : O(log(m))
                found= Arrays.binarySearch(mat[j], value)>=0;
            }
            if(found){
                return value;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an m x n matrix mat where every row is sorted in (strictly increasing order)
        //* return (the smallest common element) in (all rows).
        //If there is no common element, return -1.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //m == mat.length
        //n == mat[i].length
        //1 <= m, n <= 500
        //1 <= mat[i][j] <= 10^4
        //mat[i] is sorted in strictly increasing order.
        //--> m, n không quá lớn
        //--> Ta có thể giải quyết trong vòng O(n*m) -> O(n+m)
        //
        //- Brainstorm
        //Ex:
        //mat =
        // [
        // [1,2,3,4,5],  : 0
        // [2,4,5,8,10], : 1
        // [3,5,7,9,11], : 2
        // [1,3,5,7,9]   : 3
        // ]
        // 3 :[0,1,2,3]
        //- Mỗi value sẽ thuộc row nào đó ==> add vào hashset index (index là số thứ tự của row)
        //- Nếu number of distinct rows ==4 : Ta sẽ ghi nhận kết quả
        //  + rs=Math.min(rs, value)
        //
        //1.1, Optimization:
        //- Mình chưa dùng đến việc row lúc nào cũng tăng dần.
        //==> Chỗ này mình sẽ scan theo columns
        //==> Nếu đến index của columns nào mà có giá trị set(row)==n ==> ta sẽ lấy cho đến khi hết loop tại column đó
        //Ex:
        //- Liệu có case nào mà (new index column) > previous index ==> return value nhỏ hơn không
        //==> Không vì giá trị mới chắc chắn là new area mới có ==> new value >= prev min value ở vùng column trước đó
        //==> return ngay khi hết loop được.
        //
        //1.2, Complexity:
        //- Space : O(n*m)
        //- Time : O(n*m*log(m))
        //
        //2.
        //2.0, Binary search
        //- Idea
        //- Ta sẽ chọn ra 1 row : index=0
        //- Mỗi element thuộc row (index=0) search đối chiếu với các rows còn lại:
        //  + search với (n-1) rows còn lại nếu có element hiện tại xuất hiện trong all rows ==> ta sẽ lấy luôn nó
        //  Lấy luôn vì kiểu gì rs cũng thuộc row (index=0) : ta sẽ chọn từ (min -> max), tìm được element thoả mãn thì dừng.
        //- Search ta sẽ dùng binary search (Vì elements trong row là strictly increase)
        //
        //2.1, Optimization
        //
        //2.2, Complexity
        //- Space:
        //- Time
        //
        //#Reference:
        //2248. Intersection of Multiple Arrays
        int[][] arr={{1,2,3,4,5},{2,4,5,8,10},{3,5,7,9,11},{1,3,5,7,9}};
        System.out.println(smallestCommonElement(arr));
        System.out.println(smallestCommonElementOptimization(arr));
        System.out.println(smallestCommonElementShortenOptimization(arr));
        System.out.println(smallestCommonElementBinarySearch(arr));
    }
}
