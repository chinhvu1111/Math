package E1_weekly;

import java.util.Arrays;
import java.util.Comparator;

public class E3_PutBoxesIntoTheWarehouseII_classic {
    public static int search(int left, int right, int val, int[][] warehouse, int n){
        //Find>=val
        //
        int index=-1;
        int low=0, high=n-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            System.out.printf("Mid val: %s, original index=%s, val: %s\n", warehouse[mid][0], warehouse[mid][1], val);

            if(warehouse[mid][0]>=val){
                int curIndexMid = warehouse[mid][1];
                while(mid>=0){
                    if(curIndexMid<left){
                        index=curIndexMid;
                    }
                    mid--;
                }
                if(curIndexMid<left||curIndexMid>right){
                    index=curIndexMid;
                }
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return index;
    }

    public static int maxBoxesInWarehouseReference(int[] boxes, int[] warehouse) {
        int n = warehouse.length;
        int minHeight = Integer.MAX_VALUE;
        int[] effectiveHeights = new int[n];

        // Preprocess the height of the warehouse rooms to
        // get usable heights from the left end
        for (int i = 0; i < n; ++i) {
            minHeight = Math.min(minHeight, warehouse[i]);
            effectiveHeights[i] = minHeight;
            System.out.printf("effectiveHeights[i]:%s, i=%s\n", effectiveHeights[i], i);
        }
        System.out.println();

        minHeight = Integer.MAX_VALUE;
        // Update the effective heights considering the right end
        for (int i = n - 1; i >= 0; --i) {
            minHeight = Math.min(minHeight, warehouse[i]);
            effectiveHeights[i] = Math.max(effectiveHeights[i], minHeight);
            System.out.printf("effectiveHeights[i]:%s, i=%s\n", effectiveHeights[i], i);
        }

        // Sort the effective heights of the warehouse rooms
        Arrays.sort(effectiveHeights);
        // Sort the boxes by height
        Arrays.sort(boxes);

        int count = 0;
        int boxIndex = 0;
        // Try to place each box in the warehouse from
        // the smallest room to the largest
        for (int i = 0; i < n; ++i) {
            if (
                    boxIndex < boxes.length &&
                            boxes[boxIndex] <= effectiveHeights[i]
            ) {
                // Place the box and move to the next one
                ++count;
                ++boxIndex;
            }
        }

        return count;
    }

    public static int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        int n=warehouse.length;
        int m=boxes.length;
        int[][] sortWarehouse= new int[n][2];
        for(int i=0;i<n;i++){
            sortWarehouse[i][0]=warehouse[i];
            sortWarehouse[i][1]=i;
        }
        Arrays.sort(sortWarehouse, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        Arrays.sort(boxes);
        int rs=0;
//        int left=n, right=-1;
//        int rs=0;
//
//        for(int i=0;i<m;i++){
//            int curIndex=search(left, right, boxes[i], sortWarehouse, n);
//            System.out.printf("%s %s\n", boxes[i], curIndex);
//            if(curIndex!=-1){
//                left=Math.min(left, curIndex);
//            }
//            right=Math.max(right, curIndex);
//            System.out.printf("Left: %s, right: %s\n", left, right);
//            System.out.println();
//            if(curIndex!=-1){
//                rs++;
//            }
//        }
        int left=search(n, -1, boxes[0], sortWarehouse, n);
        int right=left;
        if(left!=-1){
            rs++;
        }
        for(int i=1;i<m;i++){
            int leftVal=(left<1)?Integer.MAX_VALUE:warehouse[left-1];
            int rightVal=right>=n-1?Integer.MAX_VALUE:warehouse[right+1];
            System.out.printf("leftVal: %s, rightVal: %s, val: %s\n", leftVal, rightVal, boxes[i]);
            if(leftVal>=boxes[i]){
                left--;
                rs++;
            }else if(rightVal>=boxes[i]){
                right++;
                rs++;
            }else{
                while (right<n&&sortWarehouse[right][0]<boxes[i]){
                    right++;
                }
                if(right!=n){
                    rs++;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (two arrays of positive integers), (boxes) and (warehouse),
        // representing (the heights of some boxes) of (unit width) and (the heights of n rooms) in a warehouse respectively.
        // (The warehouse's rooms) are labeled from (0 to n - 1) from left to right where warehouse[i] (0-indexed) is (the height of the "ith" room).
        //* NOTE:
        //  - Warehouse value is distinct
        //- Boxes are put into the warehouse by the following rules:
        //  + Boxes cannot be stacked.
        //  + You can (rearrange the insertion order) of the boxes.
        //  + Boxes can be pushed into the warehouse from either side (left or right)
        //  + If the height of some room in the warehouse is (less than) (the height of a box),
        //  then that box and all other boxes behind it will be stopped before that room.
        //* Return (the maximum number of boxes) you can put into the warehouse.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //n == warehouse.length
        //1 <= boxes.length, warehouse.length <= 10^5
        //1 <= boxes[i], warehouse[i] <= 10^9
        //+ Time: O(n), O(k*n)
        //
        //- Brainstorm
        //
        //Input: boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
        //Output: 4
        //- Sẽ có case push box vào > height của 1 warehouse nào đó ==> Stop lại ngay đó.
        //- Insert sao cũng được.
        //
        //- Insert tăng dần được không?
        //  + Phải insert tăng dần mới được
        //- Làm sao mỗi lần insert box ==> Đoán được ngay vị trí có thể insert?
        //- Nếu insert box có height =x, có thể:
        //  + Có warehouse<x chẳn ở left và right
        //  + Có warehouse>=x nên ta có thể chèn trực tiếp vào đó (Không có th nào chắn)
        //
        //- Mỗi lần search --> Tìm warehouse có (height>x) + index nằm ngoài (left, right) index
        //- Nếu cùng val ==> Lấy index nào
        //==> Không có vì warehouse height là unique
        //- Search idea sẽ không đúng vì:
        //  + Nếu search theo (index< left || index> right)
        //      + mid_val >= val :
        //          + high=mid-1: thiếu các value >= val nhưng có index<mid
        //          + high=mid+1: thiếu các value >= val nhưng có index>mid
        //
        //- Idea là sort cả 2 + 2 pointers là được.
        //Ex:
        //1,2,4,5
        //wh = [2,1,4,5]
        //+ 1 sẽ (điền vào 1): 1,2,4,5
        //  + Chứ (không điền vào 4) ==> Điền vào 4 sẽ chỉ điền được: 1,2,4
        //Ex:
        //1,2,4,5
        //wh = [3,1,[2],4,5]
        //- Ta thấy rằng ở [2] thì ta nên điền theo hướng [2],4,5 thay vì là 3,1,[2]
        //  + Vì min left < min right ==> tại 2 ta sẽ chọn được hướng theo (min = 2) thay vì (min = 1)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(log(n))
        //- Time : O(n*log(n) + m*log(m))
        //
        int[] boxes = {1,2,2,3,4}, warehouse = {3,4,1,2};
        System.out.println(maxBoxesInWarehouseReference(boxes, warehouse));
        System.out.println(maxBoxesInWarehouse(boxes, warehouse));
    }
}
