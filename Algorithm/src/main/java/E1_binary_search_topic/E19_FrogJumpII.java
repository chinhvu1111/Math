package E1_binary_search_topic;

import java.util.HashSet;

public class E19_FrogJumpII {

    public static int maxJump(int[] nums) {
        int n=nums.length;
        int rs=Integer.MIN_VALUE;
        int i=1;
        int prevIndex1=0;
        int prevIndex2=0;

        //Ex:
        //[0,2,(5),6,7]
        //[0,2,(5),6,(7),8]
        while(i<n){
            if(i%2==0||i==n-1) {
                rs = Math.max(rs, nums[i] - nums[prevIndex1]);
                prevIndex1 = i;
            }
            if(i%2==1||i==n-1){
                rs=Math.max(rs, nums[i]-nums[prevIndex2]);
                prevIndex2=i;
            }
            i+=1;
        }

        return rs;
    }

    public static int maxJumpOnePass(int[] stones) {
        int n=stones.length;
        int rs=stones[1]-stones[0];

        for(int i=2;i<n;i++){
            rs=Math.max(rs, stones[i]-stones[i-2]);
        }
        return rs;
    }

    public static int maxJumpBinarySearch(int[] stones) {
        int n=stones.length;
        int rs=0;
        int low=stones[0];
        int high=stones[n-1];

        while(low<=high){
            int mid=low+(high-low)/2;
            if(isPossible(mid, stones)){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static boolean isPossible(int cost, int[] stones){
        HashSet<Integer> visited=new HashSet<>();
        int last=0;
        for(int i=1;i<stones.length;i++){
            if(stones[i]-stones[last]>cost){
                if(i-last>1){
                    visited.add(i-1);
                    last=i-1;
                    i--;
                }else{
                    return false;
                }
            }
        }
        last=stones.length-1;
        for(int i=stones.length-2;i>=0;i--){
            if(!visited.contains(i)){
                if(stones[last]-stones[i]>cost){
                    return false;
                }else{
                    last=i;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- You are given a 0-indexed integer (array stones) (sorted in strictly increasing order) representing the positions of stones in a river.
        //- A frog, initially on the first stone, wants to travel to the last stone and then return to the first stone.
        // However, it can jump to any stone (at most once).
        //- The (length of a jump) is the (absolute difference) between the (position of the stone) the frog is currently on and
        // the (position of the stone) to which the frog jumps.
        //- More formally, if the frog is at stones[i] and is jumping to stones[j], the length of the jump is |stones[i] - stones[j]|.
        //- The cost of a path is (the maximum length of a jump) among (all jumps in the path).
        //* Return (the minimum cost of a path) for the frog.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //2 <= stones.length <= 10^5
        //0 <= stones[i] <= 10^9
        //stones[0] == 0
        //stones is sorted in a strictly increasing order.
        //
        //- Brainstorm
        //Input: stones = [0,2,5,6,7]
        //Output: 5
        //- Tìm jump min length jump để jump từ (0 -> n-1 ->0)
        //- Mỗi lần run ta sẽ run max(range|stone[i]-stone[j]|)
        //  + Do dãy số tăng dần --> Càng sang right ==> Diff càng tăng
        //
        //- Ta có thể jump đến mỗi stone[i] nhiều nhất 1 lần
        //  + Tức là không cần jump đến cũng được ==> Nhưng thường sẽ jupm trung gian vì jump directly thì length rất lớn
        //- Có trường hợp nào ta nên skip nodes không?
        //Ex:
        //Input: stones = [0,1,2,3,6,7]
        //+ Giả sử khi ta skip thì ta sẽ phải đi từ (big_node -> small_node)
        //  ==> Kết quả sẽ tệ đi
        //==> Ta nên đi đủ các nodes
        //- Vấn đề ở đây là ta sẽ chọn sao cho ta có thể back lại (first node) ==> Vẫn keep (MIN LENGTH)
        //Ex:
        //stones=[0,1,2,3]
        //==> Ta sẽ skip 1 vì 1 để ta có thể come back
        //rs= 0 -> 2 -> 3 -> 1 -> 0 = 2
        //
        //- Path cần đi là:
        //==> 0 -> 3 -> 0
        //- Vì cần đường back lại min nhất ==> Ta sẽ chọn đường sao cho đi ít nhất
        //==> Chọn đường sole nhau để có thể đi min nhất ==> ý tưởng tốt
        //
        //- Liệu sole có đủ không
        //Ex:
        //stones=[1,2,6,8,10]
        //1 -> 6 -> 10 -> 8 -> 2 -> 1 ==> 5
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- N is the length of array
        //- Space : O(1)
        //- Time : O(N)
        //
        //2. One pass
        //2.1, Idea
        //- Liệu sole thì ta có thể làm 1 cách short được không?
        //- Thay vì i+=2 => sau đó tìm cách tính sole thì ta sẽ làm đơn giản:
        //  + i=i+1 => Sau đó tính max(rs, stone[i]-stones[i-2])
        // Vì i++ ==> Nó sẽ đi qua tất cả các i (lẻ và chẵn), nếu ta (i-2) thì ta cũng sẽ thu được kết quả tương tự.
        //- Vậy nếu với cases:
        //  + N lẻ:
        //  Ex: {0,1,2}
        //  Max({0,2},{1,2},{0,1}} : Vì {0,2} lúc nào cũng lớn nhất nên ta bỏ qua tính {0,1},{1,2} được.
        //  + N chẵn:
        //  Ex: {0,9,10,12}
        //  Max sẽ được tính tương tự
        //- Vì n có thể <2 => Ta cần init rs= stones[1]-stones[0]
        //
        //3. Binary search
        //3.1, Idea
        //- Ở đây ta sẽ tìm minJump theo range ntn?
        //- Cho minCost=1, maxCost=n
        //  + Tìm cost trong range (minCost, maxCost) sao cho Frog can jump (first -> last -> first)
        //  + Với mỗi cost ta sẽ check xem cái cost đó có (possible or not)
        //Ex:
        //cost = 5 cần check
        //[0],2,5,6,7
        //- Từ first=0 ta có 2 lựa chọn đến (2/5)
        //- Ta sẽ chứng minh rằng nếu đi từ first ta đi longest 5 thì sẽ "LUÔN" cho optimal cost.
        //Ex:
        //0 -> 5 -> 7 => 2 => 0 : cost 5
        //0 -> 2 -> 6 -> 7 => 5 => 0 : cost=5
        //+ 2 solution trên đều cho cost =5
        //- Ta sẽ luôn jump với step MAX nhất có thể <= (cost=5) (Ở mỗi steps)
        //- Ta sẽ làm 2 loops cho 2 directions:
        //  + Forward
        //  + Backward
        //==> Tìm cost nhỏ nhất có thể là được.
        //3.2, Optimization
        //
        //3.3, Complexity
        //- k = stones[n-1]-stone[0]
        //- Space : O(K)
        //- Time : O(K*LOG(K))
        //
//        int[] stones={1,2,6,8,10};
        int[] stones={0,2,5,6,7};
        System.out.println(maxJump(stones));
        System.out.println(maxJumpOnePass(stones));
        System.out.println(maxJumpBinarySearch(stones));
        //#70. Climbing Stairs
        //
    }
}
