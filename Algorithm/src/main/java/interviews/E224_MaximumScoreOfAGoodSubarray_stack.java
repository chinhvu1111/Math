package interviews;

import java.util.Stack;

public class E224_MaximumScoreOfAGoodSubarray_stack {

    public static int maximumScore(int[] nums, int k) {
        int n=nums.length;
        int[] left=new int[n];
        int[] right=new int[n];

        for(int i=0;i<n;i++){
            left[i]=i;
            right[i]=i;
        }
//        leftMin[0]=nums[0];
//        rightMin[0]=nums[n-1];

        Stack<Integer> stack=new Stack<>();
        stack.add(0);

        for(int i=1;i<n;i++){
            while (!stack.isEmpty()&&nums[stack.peek()]>=nums[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                left[i]=stack.peek();
            }else{
                left[i]=-1;
            }
            stack.add(i);
        }
        stack=new Stack<>();
        stack.add(n-1);
        for(int i=n-2;i>=0;i--){
            while (!stack.isEmpty()&&nums[stack.peek()]>=nums[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                right[i]=stack.peek();
            }else{
                right[i]=n;
            }
            stack.add(i);
//            System.out.printf("%s ", i);
//            System.out.println(stack);
        }
//        println(left);
//        println(right);
        int rs=0;

        for(int i=0;i<n;i++){
            int currentValue=nums[i];
            int indexMinLeft=left[i]+1;
            int indexMinRight=right[i]-1;

            if(left[i]==i){
                indexMinLeft=i;
            }
            if(right[i]==i){
                indexMinRight=i;
            }

            if(indexMinLeft<=k&&indexMinRight>=k){
                rs=Math.max(rs, currentValue*(indexMinRight-indexMinLeft+1));
//                System.out.printf("%s %s %s value %s, ", indexMinLeft, indexMinRight,
//                        currentValue, currentValue * (indexMinRight-indexMinLeft+1));
//                System.out.println();
            }

//            int j;
//            for(j=i-1;j>=0;j--){
//                if(nums[j]<currentValue){
//                    break;
//                }
//                indexMinLeft=j;
//            }
//            for(j=i+1;j<n;j++){
//                if(nums[j]<currentValue){
//                    break;
//                }
//                indexMinRight=j;
//            }
//            if(indexMinLeft<=k&&indexMinRight>=k){
//                rs=Math.max(rs, currentValue * (indexMinRight-indexMinLeft+1));
////                System.out.printf("%s %s %s value %s, ", indexMinLeft, indexMinRight,
////                        currentValue, currentValue * (indexMinRight-indexMinLeft+1));
////                System.out.println();
//            }
        }
        return rs;
    }

    public static int maximumScoreRefactor(int[] nums, int k) {
        int n=nums.length;
        int[] left=new int[n];
        int[] right=new int[n];

        for(int i=0;i<n;i++){
            left[i]=i;
            right[i]=i;
        }
//        leftMin[0]=nums[0];
//        rightMin[0]=nums[n-1];

        Stack<Integer> stack=new Stack<>();
        stack.add(0);

        for(int i=1;i<n;i++){
            while (!stack.isEmpty()&&nums[stack.peek()]>=nums[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                left[i]=stack.peek();
            }else{
                left[i]=-1;
            }
            stack.add(i);
        }
        stack=new Stack<>();
        stack.add(n-1);
        int rs=0;
        for(int i=n-1;i>=0;i--){
            if(i!=n-1){
                while (!stack.isEmpty()&&nums[stack.peek()]>=nums[i]){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    right[i]=stack.peek();
                }else{
                    right[i]=n;
                }
                stack.add(i);
            }
            int currentValue=nums[i];
            int indexMinLeft=left[i]+1;
            int indexMinRight=right[i]-1;

            if(left[i]==i){
                indexMinLeft=i;
            }
            if(right[i]==i){
                indexMinRight=i;
            }

            if(indexMinLeft<=k&&indexMinRight>=k){
                rs=Math.max(rs, currentValue*(indexMinRight-indexMinLeft+1));
//                System.out.printf("%s %s %s value %s, ", indexMinLeft, indexMinRight,
//                        currentValue, currentValue * (indexMinRight-indexMinLeft+1));
//                System.out.println();
            }
//            System.out.printf("%s ", i);
//            System.out.println(stack);
        }
//        println(left);
//        println(right);
        return rs;
    }

    public static void println(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.printf("%s, ",arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{1,4,3,7,4,5};
//        int k=3;
        int[] arr=new int[]{5,5,4,5,4,1,1,1};
        int k=0;
//        int[] arr=new int[]{1};
//        int k=0;
//        int[] arr=new int[]{};
//        int k=0;
        System.out.println(maximumScore(arr, k));
        System.out.println(maximumScoreRefactor(arr, k));
        //
        //** Đề bài:
        //- Tính maximum score của array
        //- CT: score = min(nums[i], nums[i+1],...,nums[j]) * (i+j-1)
        //
        //** Bài này tư duy như sau:
        //1,
        //min( elements[]) * (i-j+1)
        //1.1, Nếu add thêm element
        //
        //min(1,4,3,7,4,5) = 1
        //--> Mình cần remove 1
        //==> Min nó đang nhỏ --> cần remove element để lấy min khác
        //Có 2 cách remove:
        //- Remove bình thường
        //- Remove + add thêm
        //
        //1.2,
        //- remove bình thường khi:
        //min * (i-j+1) < rs
        //2 * 4 <> 3*3
        //x * y <> (x+1) * (y-1)
        //x * y <> x*y + y - x
        //
        //VD: 12 * 3 > 13 * 2
        //==> Remove + add thêm
        //
        //-
        //(1),4,(3),7,4,(0)
        //+ min(1,4,3,7)=1
        //+ min(4,3,7,4)=3 (good 3>1)
        //(4,3,7,4) + add thêm 0 < 1 ==> group này sẽ tệ hơn so với xét cả 1
        //
        //- Add thêm element x vào:
        //+ x < min() : thêm vào sẽ tệ đi --> Bắt buộc thêm ==> (Nên k remove)
        //+ x > min() :
        //VD:
        //1(min),4,(3),7,4,0 : 3 > 1 (min)
        //1,4,3(min),(7),4,0 : 7 > 3 (min)
        //
        //
        //(1),4,(3),7,4,(0)
        //+ min(1,4,3,7)=1
        //+ min(4,3,7,4)=3 (good 3>1)
        //
        //1.3, Mỗi thằng có thể sẽ là min của all elements xung quanh nó --> Ta chỉ cần tìm range (i,j) max(j-i) của mỗi thằng là được.
        //
        //1.4,
        //k
        //i<=k<=j
        //Case 1:
        //i<=x <=k <=j
        //Case 2:
        //i<=k<=x <=j
        //Case 3"
        //i<=k<=j <=x
        //
        //- Có case đúng đến một mức độ nào đó miễn là <=k, >=k
        //
        //
        //1.5, Bài toán tìm phần tử left + furthest < nums[i] (min --> check số mà < min) ==> Break
        //1,4,(3),7,4,5
        //a,b,(c),e
        //- x < (c), c < e ==> chỉ cần quan tâm x
        //- x < (c), c > e ==> lấy c.
        //
        //VD:
        //1,4,(3),7,4,5
        //stack (3) : 1,3
        //stack (7) : 7> 3 ==> get i
        //stack (4) : 1,3,7 --> pop 7 + get index(3) : 1,3,4
        //
    }
}
