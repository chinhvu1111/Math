package interviews.bytedance;

import java.util.Stack;

public class E21_AsteroidCollision {

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> asStack=new Stack<>();
        int n=asteroids.length;

        for (int currentValue : asteroids) {
            if (currentValue < 0) {
                while (!asStack.isEmpty() && asStack.peek() > 0 && asStack.peek() + currentValue < 0) {
                    asStack.pop();
                }
                if (!asStack.isEmpty() && asStack.peek() + currentValue == 0) {
                    asStack.pop();
                } else if (asStack.isEmpty() || (asStack.peek() + currentValue < 0)) {
                    asStack.add(currentValue);
                }
            } else {
                asStack.push(currentValue);
            }
        }
        int[] rs=new int[asStack.size()];

        for(int i=rs.length-1;i>=0;i--){
            rs[i]=asStack.pop();
        }
        return rs;
    }

    public static int[] asteroidCollisionRefactor(int[] asteroids) {
        Stack<Integer> asStack=new Stack<>();

        for (int i=0;i<asteroids.length;i++) {
            int currentValue=asteroids[i];

            if(currentValue>0 || asStack.isEmpty() || asStack.peek() <0) {
                asStack.push(currentValue);
            }else if(asStack.peek()<= -currentValue){
                //- Nếu < thì ta sẽ loop tiếp để bỏ các phần tử trước đó
                //<> thì ta next i (i++) bỏ qua phần tử âm do == nhau.
                if(asStack.pop()< -currentValue){
                    i--;
                }
            }
        }
        int[] rs=new int[asStack.size()];

        for(int i=rs.length-1;i>=0;i--){
            rs[i]=asStack.pop();
        }
        return rs;
    }

    public static void println(int[] arr){
        for (int j : arr) {
            System.out.printf("%s,", j);
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] asteroids = new int[]{5,10,-5};
//        int[] asteroids = new int[]{8,-8};
        int[] asteroids = new int[]{10,2,-5};
//        int[] asteroids = new int[]{-2,-1,1,2};
        int[] rs=asteroidCollision(asteroids);
        println(rs);
        //
        //** Đề bài:
        //[5,10,-5]
        //- Mỗi giá trị trong array thể hiện |a| chính là size của hành tinh đó
        //- a>0 : Move right
        //- a<0 : Move left
        //- Chúng move cùng vận tốc, nếu chúng move ngược chiều thì hành tinh nhỏ hơn sẽ nổ.
        //- Khác chiều thì không gặp nhau.
        //+ Vị trí trong mảng thể hiện việc vị trí của các Element trong array.
        //==> Return ra kết quả khi move đến hết.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //- Việc nổ sẽ phụ thuộc vào vị trí của các hành tinh
        //+ Nếu a>0 --> Nó nổ hay không sẽ phụ thuộc vào hành tinh bên phải gần nhất + nó <0
        //+ Nếu a<0 --> Nó nổ hay không sẽ phụ thuộc vào hành tinh bên trái gần nhất + nó >0
        //
        //- Stack idea:
        //+ Với arr[i]<0 ==> Chạy về phía bên trái, ta sẽ cho nó clear phía bên trái (chỉ clear các hành tinh >0)
        //đến khi không còn hành tinh nào >0 + currentValue (<0) [Tức là có thể phá huỷ được]
        //VD:
        //5,10,-5,-13
        //+ Ở đây 5 và 10 cùng hướng ==> 10 đi trước
        //s1:
        //5, 10
        //s2:
        //5, 10, -5
        //==> 5, 10
        //S3: -13
        //
        //1.2, Complexity :
        //- Time complexity : O(n)
        //- Space complexity : O(n)
        //
        //1.3, Refactor:
        //- Khi xét đến phần tử arr[i] --> Ta sẽ không lấy arr[i] while nữa
        //==> Ta sẽ dùng i-- ==> Để xét lặp lại phần tử arr[i] ==> Tư duy vẫn thế thôi.
        //
        println(asteroidCollisionRefactor(asteroids));
        //#Reference
        //736. Parse Lisp Expression
        //2126. Destroying Asteroids
        //2211. Count Collisions on a Road
    }
}
