package interviews;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class E214_SkyLine {

    public static List<Integer> skyLineStack(int[] nums){
        int n=nums.length;
        Deque<Integer> stack=new LinkedList<>();
        List<Integer> result=new ArrayList<>();

        for(int i=0;i<n;i++){
            while (!stack.isEmpty()&&nums[stack.peekLast()]<=nums[i]){
                stack.removeLast();
            }
            if(stack.isEmpty()){
                result.add(-1);
            }else{
                result.add(stack.peekLast());
            }
            stack.addLast(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{8, 3, 4, 3, 5, 9};
        System.out.println(skyLineStack(arr));
        //
        //** Đề bài
        //- Tìm phần tử của mỗi phần tử ở vị trí (i) như sau:
        //+ j trước (i), i gần j nhất
        //+ nums[i]>nums[j]
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Bình thường ta sẽ dùng phường pháp O(n^2) để implement:
        //- Khi mỗi phần tử (i) sẽ lặp đến khi break
        //==> Refactor bằng cách dùng while thì không cần break nữa --> dùng variable (j) thay thế
        //2,
        //2.1, Ta sẽ dùng phương pháp remove phần tử thửa để tránh các phần tử sau lặp lại phần tử trước
        //- Ta sẽ dùng stack implement
        //VD:
        // 8,(3),(4),[5]
        // 8,8,8,8
        //- Ở đây ta sẽ remove phần tử 3 đi để 5 không phải traverse lại phần tử 3 nữa
        //==> Chỉ traverse phần tử 4 + remove phần tử 4 mà thôi
        //+ 5 > 4 : Không cần traverse phần tử <4 ==> Cũng bỏ đi
        //+ 2 < 4 : 2 không cần traverse phần tử < 4 (Vì index của các phần tử sau đó không gần 2 bằng 4) ==> CŨng bỏ đi
        //2.2, Implement bằng stack
        //2.2,
        //- Time complexity :
        //+ Mặc dù ta thấy 2 đoạn loop nhưng độ phức tạp vẫn chỉ là O(N)
        //Vì mỗi phần tử được (add stack, remove từ stack) duy nhất mỗi operation 1 lần
        //==> Chỉ tối đa O(2*N) time --> O(N)
        //- Space complextity : O(N)
    }
}
