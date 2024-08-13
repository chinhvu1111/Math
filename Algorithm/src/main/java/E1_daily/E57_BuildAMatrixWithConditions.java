package E1_daily;

import java.util.*;

public class E57_BuildAMatrixWithConditions {

    public static int[][] buildMatrixWrong(int k, int[][] rowConditions, int[][] colConditions) {
        int n=rowConditions.length;
        int m=colConditions.length;
        int[] inDegree=new int[k+1];
        List<List<Integer>> adjRow=new ArrayList<>();

        for(int i=1;i<=k;i++){
            adjRow.add(new ArrayList<>());
        }
        for(int[] r: rowConditions){
            //a -> b
            //
            inDegree[r[1]]++;
            adjRow.get(r[0]).add(r[1]);
        }
        Queue<Integer> nodes=new LinkedList<>();

        for(int i=1;i<=k;i++){
            if(inDegree[i]==0){
                nodes.add(i);
            }
        }
        while (!nodes.isEmpty()){
            int size=nodes.size();

            for(int i=0;i<size;i++){
                Integer curNode = nodes.poll();

            }
        }

        return null;
    }

    public static int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] rowOrder=getOrder(k, rowConditions);
//        for (int i=1;i<=k;i++) {
//            System.out.printf("%s ",rowOrder[i]);
//        }
//        System.out.println();
        if(rowOrder.length==0){
            return new int[0][0];
        }
        int[] colOrder=getOrder(k, colConditions);
//        for (int i=1;i<=k;i++) {
//            System.out.printf("%s ",colOrder[i]);
//        }
        if(colOrder.length==0){
            return new int[0][0];
        }
        //Time: O(k^2)
        //Space: O(k^2)
        int[][] rs=new int[k][k];
        //Time: O(k)
        for(int i=1;i<=k;i++){
            rs[rowOrder[i]][colOrder[i]]=i;
        }
        return rs;
    }

    public static int[] getOrder(int k, int[][] condition){
        //Space: O(k+n)
        List<List<Integer>> adjRow=new ArrayList<>();
        //Space: O(k)
        int[] inDegree=new int[k+1];

        //Time: O(k)
        for(int i=0;i<=k;i++){
            adjRow.add(new ArrayList<>());
        }
        //Time: O(n)
        for(int[] r: condition){
            //a -> b
            //
            inDegree[r[1]]++;
//            System.out.println(r[0]);
            adjRow.get(r[0]).add(r[1]);
        }
        //Space: O(k)
        Queue<Integer> nodes=new LinkedList<>();

        //Time: O(k)
        for(int i=1;i<=k;i++){
            if(inDegree[i]==0){
                nodes.add(i);
            }
        }
        //Time: O(k)
        int[] orderIndex=new int[k+1];
        int index=0;

        //Time: O(k)
        while (!nodes.isEmpty()){
            Integer curNode=nodes.poll();
            orderIndex[curNode]=index;
            index++;
            k--;

            //Time: O(n)
            for(int e: adjRow.get(curNode)){
                inDegree[e]--;
                if(inDegree[e]==0){
                    nodes.add(e);
                }
            }
        }
        if(k!=0){
            return new int[0];
        }
        return orderIndex;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a positive integer k. You are also given:
        //  + a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
        //  + a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
        //- The (two arrays) contain (integers) from (1 to k).
        //
        //- You have to build (a k x k matrix) that contains each of the numbers from (1 to k) ("exactly once").
        //  + The remaining cells should have the value 0.
        //- The matrix should also satisfy the following conditions:
        //  + The number (abovei) should appear in (a row) that is strictly above the row at
        //  which the number (belowi) appears for (all) i from 0 to n - 1.
        //      + Vì value là ("exactly one") (above-i) sẽ "above" (belowi) follow the row order.
        //  + The number (lefti) should appear in a column that is strictly left of the column at
        //  which the number (righti) appears for (all) i from 0 to m - 1.
        //      + Vì value là ("exactly one") (left-i) sẽ "above" (right-i) follow the column order.
        //* Return (any matrix) that satisfies the conditions.
        // If no answer exists, return an empty matrix.
        //- Điền những số có trong condition thôi
        //  + Khi thoả mãn condition rồi --> Các vị trí khác == 0 hết.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //2 <= k <= 400
        //1 <= rowConditions.length, colConditions.length <= 10^4
        //rowConditions[i].length == colConditions[i].length == 2
        //1 <= abovei, belowi, lefti, righti <= k
        //abovei != belowi
        //lefti != righti
        //
        //- Brainstorm
        //- Vì nó build dựa trên node val --> Theo layer:
        //  + Row
        //  + Col
        //Ex:
        //k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
        //rs:
        //3 0 0
        //0 0 1
        //0 2 0
        //- Ở đây rowConditions = [[1,2],[3,2]]
        //  + 3 và 1 đều above 2
        //      + Nhưng ta không xác định được vị trí của 3 đối với 1
        //1 0 0
        //0 0 3
        //0 2 0
        //==> Không thoả mãn điều kiện colCondition:
        //  + 2 trước 1
        //
        //0 0 1
        //3 0 0
        //0 2 0
        //==> Với những node không rõ thứ tự:
        //Ex:
        //3 vs 1 => thằng nào nằm trên ==> Điền gì cũng được.
        //
        //   3       1
        //     \   /
        //       2
        //
        //- Main points:
        //  + row oder và col order không ảnh hưởng đến nhau.
        //
        //- Có cách nào build graph xác định được thứ tự không?
        //  + Ta có thể fill auto với những node không xác định được order theo (col/row)
        //- Fill value ntn?
        //Ex:
        //- Follow theo row:
        // 5 (0)
        //  \
        //   3 (1)   1 ==> cái này sẽ điền ntn?
        //     \   /
        //       2 (2)
        //==> Ở đây sẽ chọn nối (1 -> 5)
        //  + Để nó thành graph dạng 1 đường ==> Fill được value theo order được
        //
        // 1
        //  \
        //   2
        //   3
        //     \
        //      2
        //=> Nối thành:
        // 1
        //  \
        //    3
        //     \
        //       2
        //- Traverse theo topological:
        //  + Theo layer
        //  + 1 -> 2 sẽ được đến trước
        //  + add(3,2) : parent(2) = 3 ==> 3 sẽ được nối với 1
        //      + parent(1) = 3
        //      + parent(2) = 3
        //
        //Ex:
        //      1        3
        //        \       \
        //          2       4
        //==> Tách biệt ntn thì điền ntn
        //
        //- Với tư duy điền như bên trên thì khá khó control:
        //  + Khá nhiều cases đặc biệt khi cố fill in value vào.
        //** KINH NGHIỆM:
        //  + Khi traverse theo topological sort
        //  ==> Việc đánh thứ tự sẽ được tự quyết định bằng việc add từng element vào list
        //  + Index default sẽ chính là index của element trong list đó.
        //      + Hoặc là array với (size==k): index++ cũng được.
        //
        //- Topological sort:
        //  + Ta sẽ traverse dựa trên degree của các adj nodes (Các điểm kề)
        //      + InDegree[i]==0:
        //          + Add to queue.
        //- Test cases:
        //  + Condition không thể thoả mãn --> return empty
        //Ex:
        //1 trước 3, 3 trước 2, 2 trước 1
        //==> Conflict condition (2 trước 1 và 1 trước 2)
        //  + Điều kiện này sẽ luôn return all of 0 --> Vì không có node vào (InDegree[i]==0)
        //** KINH NGHIỆM:
        //- Trong topological sort:
        //- Có thể đi cyclic:
        //  + Không có đủ node
        //  ==> 1 số node không được xét
        //--> số node có !=k
        //  ==> return empty[0]
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- n is size of row conditions
        //- m is size of row conditions
        //- Space: O(k^2+k+n+m)
        //- Time: O(k^2+k+n+m)
        //
        int k = 3;
        int[][] rowConditions = {{1,2},{3,2}}, colConditions = {{2,1},{3,2}};
        int[][] rs=buildMatrix(k, rowConditions, colConditions);

        for (int i=0;i<rs.length;i++){
            for(int j=0;j<rs[i].length;j++){
                System.out.printf("%s ", rs[i][j]);
            }
            System.out.println();
        }
    }
}
