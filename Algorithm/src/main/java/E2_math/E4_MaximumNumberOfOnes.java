package E2_math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class E4_MaximumNumberOfOnes {
    public static int maximumNumberOfOnesReference(int width, int height, int sideLength, int maxOnes) {
        int widthCnt = width / sideLength;
        int heightCnt = height / sideLength;
        int widthRemain = width % sideLength;
        int heightRemain = height % sideLength;

        List<Integer> posCnt = new ArrayList<>();

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if (i < widthRemain && j < heightRemain) {
                    posCnt.add((widthCnt + 1) * (heightCnt + 1));
                } else if (i < widthRemain) {
                    posCnt.add((widthCnt + 1) * heightCnt);
                } else if (j < heightRemain) {
                    posCnt.add(widthCnt * (heightCnt + 1));
                } else {
                    posCnt.add(widthCnt * heightCnt);
                }
            }
        }
        System.out.println(posCnt);
        Collections.sort(posCnt);
        int result = 0;
        for (int i = 0; i < maxOnes; i++) {
            result += posCnt.get(posCnt.size() - 1 - i);
        }
        return result;
    }

    public static int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int[][] grid=new int[sideLength][sideLength];

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                grid[i%sideLength][j%sideLength]++;
            }
        }
        PriorityQueue<Integer> queue=new PriorityQueue<>();

        for(int i=0;i<sideLength;i++){
            for(int j=0;j<sideLength;j++){
                if(queue.size()<maxOnes){
                    queue.add(grid[i][j]);
                }else if(!queue.isEmpty()&&queue.peek()<grid[i][j]){
                    queue.poll();
                    queue.add(grid[i][j]);
                }
            }
        }
        int rs=0;
        while (!queue.isEmpty()){
            rs+=queue.poll();
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- Given matrix M (width, height) và any sub square matrix of M --> Đều có số lượng 1 <= maxOnes
        //* Return số lượng số 1 max nhất mà M có thể có.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= width, height <= 100
        //1 <= sideLength <= width, height
        //0 <= maxOnes <= sideLength * sideLength
        //
        //- Brainstorm
        //Ex:
        //Input: width = 3, height = 3, sideLength = 2, maxOnes = 2
        //Output: 6
        //Explanation:
        //[1,0,1]
        //[1,0,1]
        //[1,0,1]
        //Ex:
        //Input: width = 4, height = 4, sideLength = 2, maxOnes = 2
        //[1,0,1,0]
        //[1,0,1,0]
        //[1,0,1,0]
        //[1,0,1,0]
        //Có thể:
        //[1,0,1,0]
        //[1,0,1,0]
        //[1,0,1,0]
        //[0,1,0,1]
        //
        //[1,0,1,0]
        //[0,1,0,1]
        //[1,0,1,0]
        //[0,1,0,1]
        //- Mỗi square có max nhất là maxOne chữ số 1
        //- Tính chất:
        //+ Nếu ta điền cho 1 square matrix
        // --> Nó có thể sẽ được dùng lại bởi 4 direction + số lượng = (sizeLenth-1) ở mỗi direction
        //* Có vẻ nếu ta điền để any submax có <= maxOnes thì ta sẽ ra được số lượng chữ số 1 cần
        //
        //Ex:
        //Input: width = 4, height = 4, sideLength = 3, maxOnes = 2
        //Cách 1:
        //[1,0,0,0]
        //[0,1,0,0]
        //[0,0,0,1]
        //[1,0,0,0]
        //Cách 2:
        //[1,1,0,0]
        //[0,0,0,0]
        //[0,0,0,1]
        //[1,1,0,0]
        //Cách 3:
        //[1,0,0,1]
        //[1,0,0,1]
        //[0,0,0,0]
        //[1,0,0,1]
        //- Cách 2 tốt hơn vì điền matrix 1 chỉ impact đến 1 matrix
        //==> Không phải điền sao cũng được.
        //
        //Ex:
        //Input: width = 4, height = 4, sideLength = 3, maxOnes = 3
        //
        //[1,0,0,1]
        //[1,0,0,1]
        //[1,0,0,1]
        //[1,0,0,1]
        //
        //Ex:
        //Input: width = 6, height = 6, sideLength = 3, maxOnes = 3
        //[1,0,0,1,0,0]
        //[1,0,0,1,0,0]
        //[1,0,0,1,0,0]
        //[1,0,0,1,0,0]
        //[1,0,0,1,0,0]
        //[1,0,0,1,0,0]
        //rs=12
        //
        //Ex:
        //Input: width = 6, height = 6, sideLength = 5, maxOnes = 3
        //[1,0,0,0,0,1]
        //[1,0,0,0,0,1]
        //[1,0,0,0,0,1]
        //[0,0,0,0,0,0]
        //[0,0,0,0,0,0]
        //[1,0,0,0,0,1]
        //rs=8
        //Ex:
        //Input: width = 6, height = 6, sideLength = 5, maxOnes = 7
        //[1,1,0,0,0,1]
        //[1,1,0,0,0,1]
        //[1,0,0,0,0,1]
        //[1,0,0,0,0,1]
        //[1,0,0,0,0,1]
        //[1,0,0,0,1,1]
        //rs=15 ==> expect 16
        //+ width traverse: cứ side length=5 ==> Ta sẽ điền
        //
        //Ex:
        //Input: width = 6, height = 6, sideLength = 5, maxOnes = 7
        //[1,2,3,4,0,0]
        //[0,0,0,0,0,0]
        //[0,0,0,0,0,0]
        //[0,0,0,0,0,0]
        //[0,0,0,0,0,0]
        //[0,0,0,0,0,0]
        //
        //1.1, Optimization
        //- Phần này giới hạn size của heap là được.
        //
        //1.2, Complexity
        //- Space : O(slideLength^2)
        //- Time : O(w*h+2*slide*log(2*slide))
        //
        maximumNumberOfOnesReference(6,6,5,7);
        System.out.println(maximumNumberOfOnes(6,6,5,7));
        //#Reference:
        //2530. Maximal Score After Applying K Operations
        //786. K-th Smallest Prime Fraction
        //2522. Partition String Into Substrings With Values at Most K
    }
}
