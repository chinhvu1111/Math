package E1_stack;

import java.util.*;

public class E4_FindPermutation {

    public static int[] findPermutation(String s) {
        int n=s.length();
        if(n==0){
            return new int[]{};
        }
        Deque<Integer> queue=new LinkedList<>();
        int[] rs=new int[n+1];
        queue.add(0);

        for(int i=0;i<n;i++){
            char c=s.charAt(i);

            if(c=='I'){
                queue.addFirst(i+1);
            }else{
                List<Integer> curList=new LinkedList<>();
                while(!queue.isEmpty()&&queue.getLast()!=i){
                    int curIndex=queue.removeLast();
                    curList.add(curIndex);
                }
                Collections.reverse(curList);
                queue.addLast(i+1);
                queue.addAll(curList);
            }
//            System.out.printf("Add: %s, queue: %s\n",c, queue);
        }
        //[6, 4, 5, 3, 0, 1, 2]
        System.out.println(queue);
        while(!queue.isEmpty()){
            rs[queue.removeFirst()]=n+1;
            n--;
        }
        for(int i:rs){
            System.out.printf("%s, ", i);
        }
        System.out.println();
        return rs;
    }

    public static int[] findPermutationRefactor(String s) {
        int n=s.length();
        if(n==0){
            return new int[]{};
        }
        Deque<Integer> queue=new LinkedList<>();
        int[] rs=new int[n+1];
        queue.add(0);
        List<Integer> orderList=new ArrayList<>();

        for(int i=0;i<n;i++){
            char c=s.charAt(i);

            if(c=='I'){
                queue.addFirst(i+1);
            }else{
                while(!queue.isEmpty()&&queue.getLast()!=i){
                    int curIndex=queue.removeLast();
                    orderList.add(curIndex);
                }
//                Collections.reverse(curList);
                queue.addLast(i+1);
            }
//            System.out.printf("Add: %s, queue: %s\n",c, queue);
        }
        //[6, 4, 5, 3, 0, 1, 2]
//        System.out.println(queue);
//        System.out.println(orderList);
        while(!queue.isEmpty()){
            rs[queue.removeFirst()]=n+1;
            n--;
        }
        Collections.reverse(orderList);
        for(int e:orderList){
            rs[e]=n+1;
            n--;
        }
//        for(int i:rs){
//            System.out.printf("%s, ", i);
//        }
//        System.out.println();
        return rs;
    }

    public static int[] findPermutationOptimization(String s) {
        int n=s.length();
        if(n==0){
            return new int[]{};
        }
        Stack<Integer> stack=new Stack<>();
        int[] rs=new int[n+1];
        stack.add(1);
        int init=1;
        int index=0;

        for(int i=0;i<n;i++){
            char c=s.charAt(i);

            if(c=='I'){
                while(!stack.isEmpty()){
                    rs[index++]=stack.pop();
                }
                stack.add(++init);
            }else{
                stack.add(++init);
            }
//            System.out.printf("Add: %s, queue: %s\n",c, queue);
        }
//        System.out.println(stack);
        while (!stack.isEmpty()){
            rs[index++]=stack.pop();
        }
//        for(int i:rs){
//            System.out.printf("%s, ", i);
//        }
//        System.out.println();
        return rs;
    }

    public static int[] findPermutationOptimizationReverseArray(String s) {
        int n=s.length();
        if(n==0){
            return new int[]{};
        }
        int[] rs=new int[n+1];
        for(int i=0;i<=n;i++){
            rs[i]=i+1;
        }
//        int init=1;
        int low=0, high=0;

        for(int i=0;i<n;i++){
            char c=s.charAt(i);

            if(c=='I'){
                reverse(rs, low, high);
                high++;
                low=high;
            }else{
                high++;
            }
//            System.out.printf("Add: %s, queue: %s\n",c, queue);
        }
        reverse(rs, low, high);
        for(int i:rs){
            System.out.printf("%s, ", i);
        }
        System.out.println();
        return rs;
    }

    public static void reverse(int[] arr, int low, int high){
        while(low<=high){
            int temp=arr[low];
            arr[low]=arr[high];
            arr[high]=temp;
            low++;
            high--;
        }
    }

    public static void main(String[] args) {
        //* Requirement
        //- Permutation of perm là 1 tập hợp n số intergers trong khoảng (1 -> n)
        //  + N số này sinh ra từ string có (n-1) characters
        //+ s[i] == 'I' if perm[i] < perm[i + 1], and
        //+ s[i] == 'D' if perm[i] > perm[i + 1].
        //- Given string s, reconstruct the lexicographically (smallest) permutation perm
        //* return it
        //Ex:
        //s= I
        //i==0: perm[0]<perm[1]
        //+ length=1 => n=2
        //==> rs=[1,2]
        //
        //** Idea
        //* Method 1:
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 10^5
        //s[i] is either 'I' or 'D'.
        //--> Ta có thể giải time = O(n) ==> O(log(n))
        //
        //- Brainstorm
        //Ex:
        //s="DI"
        //i=0: perm[0]>perm[1]
        //i=1: perm[1]<perm[2]
        //==> perm[0]> perm[1] < perm[2]
        //rs=[2,1,3]
        //
        //- Tức là bài cho ta predicate --> Ta dựng array phù hợp
        //Ex:
        // a>b<c>d<e>f<g>h
        // number in [1,n]
        //==> Build array
        //
        //- Return smallest --> Hướng điền smallest đầu tiên.
        //  + Hoặc là điền bừa --> Xong (tất cả các số) cùng trừ đi min là được
        //+ Tác number khác nhau -> permutation (Hoán vị của [1,n])
        //
        //Ex:
        //n=7
        // a>b<c>d<e>f<g>h
        // 0 1 2 3 4 5 6 7
        //==> Sort lại xong fill value được không
        //a>b<c>d<e>f<g>h
        //Fill bừa
        //a>1<c>2<e>3<g>4
        //+ Do 2,3,4 điền rồi => Chỉ có thể điền a=5 ==> Không smallest (SAI)
        //Ex:
        //+ Ta thử fill ưu tiên min với các chars đầu
        //n=7
        // a>b<c>d<e>f<g>h
        // 2>1<3>? (d ta cũng không fill dc value)
        //
        // a>b<c>d<e>f<g>h
        // g>h>e>f>c>d>a>b
        //==> Ta có thể suy ra 1 theo 1 rule nào đó như trên được không?
        //==> Coi như ta suy ra 1 cách sort để return smallest array.
        //Ex:
        // a>b<c>d<e>f<g>h
        //+ add(a) : stack=a
        //+ a>b ==> Remove(a) + add(b) + add(a): stack= a,b
        // b<c ==> add c
        //  + Số sau ta sẽ để lớn hơn vì độ ưu tiên thấp hơn các số trước đó (Về sau thì càng lớn)
        //  + add first : stack= c,a,b
        //c>d
        //* Ta thấy rằng:
        // + Ta sẽ dùng element vừa add vào để compare
        //+ remove first() + add(d) + add again: stack= d,c,a,b
        //+ e>d:
        //  add(e): stack= e,d,c,a,b
        //+ f<e:
        //=>....
//        String s="DI";
//        String s="I";
//        String s="DDIIDI";
        String s="DIDDIDIDII";
        // 0 > 1 > 2 < 3 < 4 > 5 < 6
        //- Stack : Apply new rule, left -> right giảm dần value
        //+ add(0) : stack= 0
        //+ 0>1, addLast(1) : stack= 0, 1
        //+ 1>2, addLast(2) : stack= 0, 1, 2
        //+ 2<3, addFirst(3) : stack= 3, 0, 1, 2
        //+ 3<4, addFirst(4) : stack= 4, 3, 0, 1, 2
        //+ 4>5, pop(4), addFirst(5) : stack= 4, 5, 3, 0, 1, 2
        //+ 5<6, addFirst(6) :
        //  + Điền 6 để số đằng sau không đổi
        // 6,4,5,3,0,1,2
        //- Kết luận:
        //+ Nếu (số sau > số trước)('D') ==> Add vào first
        //+ Nếu (số sau < số trước)('I'):
        //  + Check số trước position:
        //      + Last : addLast
        //      + Other : Thì đặc số đó ngay sau index ta cần tìm.
        //
        //- Làm như trên sẽ bị timeout đoạn other --> Cần pop và add lại liên tục.
        //- Refactor:
        // 0 > 1 > 2 < 3 < 4 > 5 < 6
        //- Stack : Apply new rule, left -> right giảm dần value
        //+ add(0) : stack= 0, size=1
        //+ 0>1, addLast(1) : stack= 0, 1
        //  + Pop all : add to order_list => size=2
        //+ 1>2, addLast(2) : stack= 0, 1, 2 => size=3
        //  + Pop all : add to order_list
        //+ 2<3, addFirst(3) : stack= 3, 0, 1, 2
        //  + Pop + add(2) to order list, 3 to queue
        //+ 3<4, addFirst(4) : stack= 4, 3, 0, 1, 2
        //+ 4>5, pop(4), addFirst(5) : stack= 4, 5, 3, 0, 1, 2
        //+ 5<6, addFirst(6) :
        //* Skill caching 1 phần của queue:
        //- Ta sẽ cache 1 phần không thay đổi của queue
        //  + Ở đây ta sẽ không reverse luôn --> Đợi đến cuối mới reverse.
        //
        //1.1, Optimization
        //Ex:
        //IIDDIIID
        // 0 < 1 < 2 > 3 > 4 < 5 < 6 < 7 > 8
        //stack ={1}
        //i=0: I < --> pop(1) + add(2): stack={2}, rs[0]=1 (pop)
        //i=1: I < --> pop(2) + add(3): stack={3}, rs[1]=2 (pop)
        //i=2: D > --> add(4) : stack={3,4}
        //i=3: D (3>4) --> add(5) : stack={3,4,5}
        //i=4: I (4>5) --> pop all() add(6) : stack={6} ==> pop ra thì ta thu được {5,4,3} chính là value của rs={1,2,5,4,3,6}
        //- Tức là theo chiều > > ==> Tăng dần thì ta tăng dần value là được ==> Sau khi pop ngược ta assign lần lượt left --> right (value tăng dần)
        //  + Tăng dần sẽ thể hiện được dấu >.
        //- Chiều < : thì ta chỉ cần pop ra + tăng dần là được.
        //  + Vì value tăng dần --> Giá trị trước sẽ < later value ==> Chọn xong 1 số thì nó là tối ưu nhất rồi ==> pop ra không cần nữa.
        //
        //- Ở đây thậm chí ta còn có thể tối ưu --> Space = O(1)
        //  + Nếu ta dùng reverse array.
        //  + Gán rs[i]=i+1 trước
        //  + Sau đó nếu D ==> high++
        //  + Nếu gặp I ==> Reverse(rs, low, high)
        //  + high++, low=high là được
        //  + Sau đó cần reverse bên ngoài (rs, low, high) nữa để ra kết quả.
        //
        //1.2, Complexity:
        //+ N is the length of the String s
        //- Space: O(N)
        //- Time : O(N)

        findPermutation(s);
        findPermutationRefactor(s);
        findPermutationOptimization(s);
        findPermutationOptimizationReverseArray(s);
        //#Reference:
        //2434. Using a Robot to Print the Lexicographically Smallest String
    }
}
