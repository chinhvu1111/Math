package E1_hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class E3_EqualRowAndColumnPairs {

    public static int equalPairs(int[][] grid) {
        int n=grid.length;
        //Space : O(n)
        String[] hashcodesRow=new String[n];
        String[] hashcodesColumn=new String[n];

        //Time : O(n^2)
        for(int i=0;i<n;i++){
            hashcodesRow[i]=Arrays.toString(grid[i]);
        }
        //Space : O(n^2)
        int[][] copyGrid=new int[n][n];

        //Time : O(n^2)
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                copyGrid[j][i]=grid[i][j];
            }
        }
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                System.out.printf("%s, ", copyGrid[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();
        //Time : O(n^2)
        for(int i=0;i<n;i++){
            hashcodesColumn[i]=Arrays.toString(copyGrid[i]);
        }
        //Space : O(n)
        HashMap<String, Integer> mapCount1=new HashMap<>();
        HashMap<String, Integer> mapCount2=new HashMap<>();

        for(int i=0;i<n;i++){
            mapCount1.put(hashcodesRow[i], mapCount1.getOrDefault(hashcodesRow[i], 0)+1);
            mapCount2.put(hashcodesColumn[i], mapCount2.getOrDefault(hashcodesColumn[i], 0)+1);
        }
        System.out.println(mapCount1);
        System.out.println(mapCount2);
        int rs=0;

        //Time : O(n)
        for(int i=0;i<n;i++){
            if(mapCount2.containsKey(hashcodesRow[i])){
                rs+=mapCount2.get(hashcodesRow[i]);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Đếm số pair mà row == column
        //+ Để 1 row-i == column-j thì chúng phải bằng nhau lần lượt + đúng order
        //* return số lượng pair có trong matrix
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //n == grid.length == grid[i].length
        //1 <= n <= 200
        //1 <= grid[i][j] <= 10^5
        //- n khá nhỏ
        //- value của từng grid[i][j] là khá lớn
        //
        //- Brainstorm
        //- Vấn đề ta cần xử lý:
        //+ Cách check 2 arrays giống nhau
        //--> Ta sẽ dùng Arrays.toString(arr[][]) --> Để tính hashmap của 1 array
        //* Note:
        //- Dùng Arrays.hashCode() : Khá tệ vì hàm này có 1 số trường hợp array khác nhau nhưng return cùng Integer
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space : O(n^2)
        //- Time : O(n^2)
        //
        //2.
        //2.0, Dùng Trie để lưu thông tin
        //
        //#Reference:
        //2500. Delete Greatest Value in Each Row
        HashSet<Integer> hashSet=new HashSet<>();
        hashSet.add(Arrays.hashCode(new int[]{1,2,3}));
        System.out.println(hashSet.contains(Arrays.hashCode(new int[]{1,2,3})));
//        int[][] nums={
//                {3,1,2,2},
//                {1,4,4,4},
//                {2,4,2,2},
//                {2,5,2,2}};
        int[][] nums={
                {2, 1},
                {3, 32}};
        System.out.println(equalPairs(nums));
        System.out.println(Arrays.hashCode(new int[]{2,3}));
        System.out.println(Arrays.hashCode(new int[]{1,32}));
        System.out.println(Arrays.hashCode(new int[]{2,1}));
        System.out.println(Arrays.hashCode(new int[]{3,32}));
    }
}
