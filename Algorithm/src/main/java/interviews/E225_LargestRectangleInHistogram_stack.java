package interviews;

import java.util.Deque;
import java.util.LinkedList;

public class E225_LargestRectangleInHistogram_stack {

    public static int largestRectangleArea(int[] heights) {
        int n=heights.length;
        Deque<Integer> lessThanFurthest=new LinkedList<>();
        int rs=0;
        int[] left=new int[n];
        int[] right=new int[n];

        for(int i=0;i<n;i++){
            //6,2,3
            //- 6,2
            int currentLength=1;
            while (!lessThanFurthest.isEmpty()&&heights[lessThanFurthest.getLast()]>=heights[i]){
                currentLength+=left[lessThanFurthest.removeLast()];
            }
            lessThanFurthest.add(i);
            left[i]=currentLength;
        }
        lessThanFurthest=new LinkedList<>();
        for(int i=n-1;i>=0;i--){
            //6,2,3
            //- 6,2
            int currentLength=1;
            while (!lessThanFurthest.isEmpty()&&heights[lessThanFurthest.getLast()]>=heights[i]){
                currentLength+=right[lessThanFurthest.removeLast()];
            }
            lessThanFurthest.add(i);
            right[i]=currentLength;
        }
        for(int i=0;i<n;i++){
            rs=Math.max(rs, (left[i]+right[i]-1)*heights[i]);
        }
        return rs;
    }

    public static int largestRectangleAreaRefactor(int[] heights) {
        if(heights==null|| heights.length==0){
            return 0;
        }
        int n=heights.length;
        int[] lessFromLeft=new int[n];
        int[] lessFromRight=new int[n];

        lessFromLeft[0]=-1;
        lessFromRight[n-1]=n;

        for(int i=1;i<n;i++){
            int p=i-1;

            while (p>=0&&heights[p]>=heights[i]){
                p=lessFromLeft[p];
            }
            lessFromLeft[i]=p;
        }
        for(int i=n-2;i>=0;i--){
            int p=i+1;

            while (p<=n-1&&heights[p]>=heights[i]){
                p=lessFromRight[p];
            }
            lessFromRight[i]=p;
        }
        int maxArea=0;

        for(int i=0;i<n;i++){
            maxArea=Math.max(maxArea, (lessFromRight[i]-lessFromLeft[i]-1)*heights[i]);
        }

        return maxArea;
    }

    public static void main(String[] args) {
//        int[] height=new int[]{2,1,5,6,2,3};
        int[] height=new int[]{4,2,0,3,2,4,3,4};
//        int[] height=new int[]{1,1};
        System.out.println(largestRectangleArea(height));
        //
        //** Đề bài:
        //- Bài này tìm diện tích hình chữ nhật lớn nhất
        //VD: 2,1,5,6,2,3
        //result = 10
        //+ (5,6) --> Tạo được hình chữ nhật có width=5 vì 5 nhỏ nhất
        //+ (2,1,5,6,2,3) --> Tạo được hình chữ nhật có width=1 (Vì 1 nhỏ nhất)
        //==> 5*2 > 1*6 ==> result=5
        //
        //** Bài này tư duy như sau
        //1,
        //1.1,
        //- Bài này sẽ chia ra tư duy:
        //+ Giả sử với mỗi (i : 0--> n-1), ta coi nó như là 1 lựa chọn để coi như nó là height ta chọn để tính
        //diện tích
        //--> Thì ta sẽ có n case
        //==> Nếu ta for chạy lan sang 2 bên --> O(n^2) slow
        //
        //1.2,
        //- Cần làm rõ các case và cách xử lý:
        //VD:
        //4,2,0,3,[1],(2),4,3,4
        //Duyệt từ right —> left
        //- 2<4 : Không tính theo 4 được do (4) có thể >3 —> Length nhỏ
        //- Ta không thể remote phần tử phía sau vì —> về sau có thể 1 là min nhất ==> Nó có thể tính theo length == all phần tử phía trước
        //VD: [2],(4),8,9,3
        //+ 2 không tính theo 4 được, 4 được tính theo 8,9
        //+ 2 còn có thể tính trên 3 nữa
        //==> 2 sẽ được tính bằng
        //[2]= [4]+ [3]
        //—> Ta có thể remove (8,9) >4 ra
        //
        //VD:
        //4,2,0,3,[1],(2),4,3,4
        //
        //- 4,2 -> remove 4
        //+ [2]=2
        //- 0 : remove 2
        //[0]=[2]+1 (Chỉ tính theo những phần tử còn trong Stack)
        //- 3>0 —> Ta không cần tính 3 nữa
        //+ [3]=1
        //0,3
        //- 0,3,1 ==>
        //+[1]=[3]
        //==> Chỉ remote 3 thôi
        //+ 0, 1
        //- 2 ==> Ta sẽ add thêm 2 vào giữ nguyên 0,1 (Để các số sau có thể nhỏ hơn 2 số này)
        //+ 0,1,2
        //
        //* Quy luật:
        //- a nếu add b:
        //+ a > b==> các phần tử sau c --> Chỉ có thể tạo ra height là b
        //==> Có thể remove a + add(b)
        //+ a<b : Vì các phần tử sau b là c có thể tính theo cả a
        //VD: 2,3,(1) --> 1 có thể tính theo 2 ==> không remov 2
        //+ Các phần tử sẽ remove last và value == all phần tử bên trong queue (value < element in queue)
        //left[i] =1 : Mỗi phần tử height =1 (Ban đầu)
        //CT: left[i] + = left[height[last]]
        //
        //1.3, For lại để tính diện tích lớn nhất
        //CT: (left[i]+right[i]-1) * height[i].
        //
        //1.4, Optimization
        //- Thay vì sử dụng stack để remove + các giá trị cũ lại
        //+ Ta sẽ sử dụng array để jump (Nhảy cóc giá trị)
        //- p=lessFromLeft[i] : Tức là index bên trái gần nhất sao cho (heights[p] <= heights[i])
        //+ lessFromLeft[n] : Kích thước giống heights
        //- Ta sẽ thực hiện nhảy cóc để tránh phải traverse all
        //+ [3],4,5,[3],6,7,[4],(3)
        //- Ví dụ để tìm điểm left của (3) + (xa nhất) mà thoả mãn heights[p]>= heights[i]
        //+ Thứ tự nhảy: 3 --> 4 (i-1) --> p==3 --> p==0
        //
        //- Sau đó kết quả sẽ bằng (lessFromLeft[i]-lessFromRight-1)* heights[i].
        //
        //1.5,
        //- Time complexity : O(N)
        //- Space complexity: O(N)
        System.out.println(largestRectangleAreaRefactor(height));
    }
}
