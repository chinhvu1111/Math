package interviews.bytedance;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public class E29_ValidateStackSequences {

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> pushElements=new LinkedList<>();
        Deque<Integer> popElements=new LinkedList<>();
        int index=0;

        for (int j : popped) {
            popElements.addLast(j);
        }

        while (index<pushed.length){
            if(!popElements.isEmpty()&&popElements.getFirst()==pushed[index]){
                popElements.removeFirst();
            }else if(!popElements.isEmpty()&&!pushElements.isEmpty()&& Objects.equals(pushElements.getLast(), popElements.getFirst())){
                popElements.removeFirst();
                pushElements.removeLast();
                continue;
            }else{
                pushElements.addLast(pushed[index]);
            }
            index++;
        }
        while (!pushElements.isEmpty()&&!popElements.isEmpty()&& Objects.equals(pushElements.getLast(), popElements.getFirst())){
            pushElements.removeLast();
            popElements.removeFirst();
        }

        return pushElements.isEmpty();
    }

    public static boolean validateStackSequencesOptimization(int[] pushed, int[] popped) {
        Stack<Integer> st=new Stack<>();
        int index=0;

        for(int val:pushed){
            st.push(val);
            while (!st.isEmpty()&&st.peek()==popped[index]){
                st.pop();
                index++;
            }
        }
        return st.isEmpty();
    }

    public static boolean validateStackSequencesO1(int[] pushed, int[] popped) {
        int i=0;
        int j=0;

        for(int val:pushed){
            pushed[i++]=val;

            while (i>0&&pushed[i-1]==popped[j]){
                i--;
                j++;
            }
        }
        return i==0;
    }

    public static void main(String[] args) {
//        int []pushed = {1,2,3,4,5};
//        int[] popped = {4,5,3,2,1};
//        int []pushed = {1,2,3,0};
//        int[] popped = {2,1,3,0};
        int []pushed = {48,70,284,568,870,142,32};
        int[] popped = {48,70,284,870,142,32,568};
        System.out.println(validateStackSequences(pushed, popped));
        //** Đề bài:
        //- Tức là đầu vào cho 2 danh sách push và pop
        //+ Act từng operation sao cho kết quả cuối cùng trả lại empty.
        //==> Nếu có thể return true <> false.
        //- Pop chính là hoán vị của push
        //- Các số là duy nhất
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Làm thế nào để có thể tạo ra empty Stack
        //- Chỉ có thể push và pop có cùng thứ tự.
        //VD: 1,2,3 ==> Cần 3,2,1/ 2,1,3
        //+ Tức là tuỳ thuộc vào vị trí ta chọn để tách ra số nào trước số nào đứng sau.
        //
        //- Thử các cases có thể xảy ra:
        //Case 1:
        //Push : 1,2,3,(4),5
        //Pop : (4),5,3,2,1
        //+ 5 được pop sau 4, push sau 4
        //+ 3,2,1 được pop sau 4, push trước 4.
        //
        //Case 2:
        //Push : 1,2,3,4,5,6
        //Pop : 4,6,5,3,2,1
        //
        //- Quan Sát 2 cases ta thấy:
        //+ Pop thì ta bắt buộc phải pop từ đầu đến cuối.
        //+ 4 --> 6 ==> push sẽ còn là : 1,2,3,5 (6 đã pop rồi)
        //+ check last push = first của pop hay không sau đó pop tiếp ==> Cho đến khi empty.
        //+ Nếu phần tử hiện tại == first của stackPop thì ta chỉ remote stackPop.
        //
        //1.2, Cách làm như sau:
        //Index (push), index1 (pop)
        //+ Check từng phần tử Của push xem có == pop(index1) --> stackPop.removeFirst()
        //+ Không bằng push vào pushElements: index++
        //+ <> pop nó đí index++ + stackPush.removeLast(), stackPop.removeFirst()
        //+ Tiếp tục như thế nếu đến cuối stackPush empty() ==> true <> false.
        //
        //Case này sai do dùng == operator, cần thay bằng Objects.equal
        //int []pushed = {48,70,284,568,870,142,32};
        //int[] popped = {48,70,284,870,142,32,568};
        //48,70,284 ==> pop all
        //568,870 ==> pop 870 : 568
        //568,142 ==> pop 142 : 568
        //568,32 ==> pop 32 : 568
        //568 ==> pop 568 : empty
        //
        //1.3, Complexity:
        //- Time complexity : O(n)
        //- Space : O(n)
        //+ n là số phần tử của push/pop array.
        //1.4, Tối ưu:
        //- Ta có thể dùng 1 stack là pushed stack --> Sau đó push dần + check với popped array để có thể pop dần các phần tử trong push stack ra
        //+ Kết quả trả lại true khi pushed stack empty
        //+ Time complexity : O(n)
        //+ Space complextity : O(n) ==> Hơn bên trên ở chỗ chỉ cần tạo 1 stack.
        //
        //- Ngoài ra ta có thể custom một chút với pushed stack --> Có thể modify array thay thế cho việc tạo ra pushed stack + dùng index.
        //+ Kết quả trả lại khi i==0
        //+ Time complexity : O(n)
        //+ Space complextity : O(1)
        System.out.println(validateStackSequencesOptimization(pushed, popped));
        System.out.println(validateStackSequencesO1(pushed, popped));
        //#Reference
        //947. Most Stones Removed with Same Row or Column
        //2411. Smallest Subarrays With Maximum Bitwise OR
        //256. Paint House
        //1627. Graph Connectivity With Threshold
    }
}
