package interviews.bytedance;

import java.util.HashMap;
import java.util.HashSet;

public class E34_FrogJump {

    public static boolean canCross(int[] stones) {
        int n= stones.length;
        HashMap<Integer, HashSet<Integer>> valueToJumpStep=new HashMap<>();
        HashSet<Integer> hashSet=new HashSet<>();
        hashSet.add(1);
        valueToJumpStep.put(0, hashSet);

        for(int i=1;i<n;i++){
            HashSet<Integer> jumpSteps=new HashSet<>();

            for(int j=i-1;j>=0;j--){
                HashSet<Integer> currentJumpStep=valueToJumpStep.get(stones[j]);

                if(currentJumpStep==null){
                    continue;
                }
                if(currentJumpStep.contains(stones[i]-stones[j])){
                    jumpSteps.add(stones[i]-stones[j]);
                    jumpSteps.add(stones[i]-stones[j]+1);
                    jumpSteps.add(stones[i]-stones[j]-1);
                }
            }
            if(jumpSteps.size()!=0){
                valueToJumpStep.put(stones[i], jumpSteps);
            }
        }
        return valueToJumpStep.containsKey(stones[n-1]);
    }

    public static boolean canCrossRefactor(int[] stones) {
        int n= stones.length;
        HashMap<Integer, HashSet<Integer>> valueToJumpStep=new HashMap<>();

        for (int stone : stones) {
            valueToJumpStep.put(stone, new HashSet<>());
        }
        HashSet<Integer> hashSet=new HashSet<>();
        hashSet.add(1);
        valueToJumpStep.put(1, hashSet);
        valueToJumpStep.remove(0);


        for(int i=1;i<n;i++){
            HashSet<Integer> jumps=valueToJumpStep.get(stones[i]);

            for(Integer jump:jumps){
                int nextValue=stones[i]+jump;
                int nextValue1=stones[i]+jump+1;
                int nextValue2=stones[i]+jump-1;

                if(nextValue==stones[n-1]||nextValue1==stones[n-1]||nextValue2==stones[n-1]){
                    return true;
                }

                if(stones[i]!=nextValue&&valueToJumpStep.containsKey(nextValue)){
                    HashSet<Integer> currentHashset=valueToJumpStep.get(nextValue);
                    currentHashset.add(jump);
//                    valueToJumpStep.put(nextValue, currentHashset);
                }
                if(stones[i]!=nextValue1&&valueToJumpStep.containsKey(nextValue1)){
                    HashSet<Integer> currentHashset=valueToJumpStep.get(nextValue1);
                    currentHashset.add(jump+1);
//                    valueToJumpStep.put(nextValue1, currentHashset);
                }
                if(stones[i]!=nextValue2&&valueToJumpStep.containsKey(nextValue2)){
                    HashSet<Integer> currentHashset=valueToJumpStep.get(nextValue2);
                    currentHashset.add(jump-1);
//                    valueToJumpStep.put(nextValue2, currentHashset);
                }
            }
        }
        return valueToJumpStep.get(stones[n-1]).size()>0;
    }

    public static boolean canCrossRefactorRefer(int[] stones) {
        if (stones.length == 0) {
            return true;
        }

        HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>(stones.length);
        map.put(0, new HashSet<Integer>());
        map.get(0).add(1);
        for (int i = 1; i < stones.length; i++) {
            map.put(stones[i], new HashSet<Integer>() );
        }

        for (int i = 0; i < stones.length - 1; i++) {
            int stone = stones[i];
            for (int step : map.get(stone)) {
                int reach = step + stone;
                if (reach == stones[stones.length - 1]) {
                    return true;
                }
                HashSet<Integer> set = map.get(reach);
                if (set != null) {
                    set.add(step);
                    if (step - 1 > 0) set.add(step - 1);
                    set.add(step + 1);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        int[] stones=new int[]{0,1,3,5,6,8,12,17};
        int[] stones=new int[]{0,1,2,3,4,8,9,11};
        System.out.println(canCross(stones));
        System.out.println(canCrossRefactor(stones));
        //
        //** Đề bài:
        //- Trả lại kết quả xem liệu con ếch có thể đi dòng sông hay không.
        //- Giả sử frog nhảy đến vị trí ith bước nhảy trước đó là k thì chỉ có thể nhảy về phía trước 1 đoạn (k-1,k,k+1)
        //
        //** Bài này tư duy như sau::
        //1,
        //- Mỗi vị trí phụ thuộc vào bước nhảy --> Nó phụ thuộc vào value khá lơn 2^31
        //- Idea:
        //+ Mục tiêu sẽ là vị trí cuối cùng
        //+ Mỗi vị trí thứ i sẽ chỉ có thể đến được qua i-1 (+1 do tính từ 0) vị trí trước đó.
        //1.1, Ta sẽ kiểm tra xem vị trí hiện tại có thể đến được hay không
        //+ vì value rất lớn 2^31 --> không thể trừ đi được
        //==> For các giá trị cũ thôi / ta cần lưu số bước nhảy có thể của từng vị trí.
        //- Idea dùng hashmap để lưu dạng <value_i_th, hashset<Inteter> jump_values>>
        //+ Với mỗi vị trí sẽ có số bước nhảy có thể nhảy tại vị trí i_th:
        //1.2, Complexity:
        //- Time complexity : O(n*n)
        //- Space complexity : O(n*k)
        //1.3, Refactor + optimization :
        //- Với cases bên trên số lượng Stones cần quét là (i-1) quá nhiều
        //==> Ta sẽ thay đổi bằng cách tư duy xuôi
        //VD:
        //1 --> đến đc 2,3 ==> Ta sẽ Add value cho các điểm đó
        //--> Thay vì từ 2,3 traverse ngược lại.
        //
        //- Cách làm như sau:
        //+ 0 --> 1 chỉ là 1 case duy nhất
        //+ Ta sẽ đè vào danh sách các điểm sắp đến tại vị trí i th:
        //~ jump
        //~ jump + 1
        //~ jump - 1
        //
        //- Cách code khác:
        //+ Bên trên là vị trí hiện tại tính liệu có thể đi đến vị trí nào tiếp theo ==> Thêm jump vào vị trí có thể
        //đến được
        //==> Tư duy tương tự chỉ khác init value:
        //Cũ : 1= [1] ==> Sau đó chia ra (1-1,1+1,1) cộng lên,
        //Mới : 0=[1], 1=[1, 2] ==> Ta đã cho sẵn vào 1 rồi ==> nên ta chỉ cần loop + vẫn cần (jum-1,jum+1, jum)
        //==> Nhưng mà get(stone[I] + value) để thêm vào.
        //
        //** Chú ý:
        //HashSet<Integer> currentHashset=valueToJumpStep.get(
        //currentHashset.add(jump);
        //==> Làm như thế này thì value nó tự thay đổi rồi ==> Không cần put lại vào nữa (put thì sẽ chậm hơn)
        //
        //#Reference:
        //404. Sum of Left Leaves
        //1824. Minimum Sideway Jumps
        //2140. Solving Questions With Brainpower
        System.out.println(canCrossRefactorRefer(stones));
    }
}
