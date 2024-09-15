package E1_Tree;

import java.util.*;

public class E44_PathSumIV {

    public static int pathSumStupid(int[] nums) {
        int n=nums.length;
        int rs=0;
        Map<Integer, int[]> prevNode=new HashMap<>();
//        Queue<Integer> nodes=new LinkedList<>();
//        nodes.add(nums[0]);
        prevNode.put(1, new int[]{nums[0], nums[0]%10});

        for(int i=1;i<n;i++){
            int curDepth=nums[i]/100;
            int j=i;
            Map<Integer, int[]> curNodes=new HashMap<>();
            HashSet<Integer> visited=new HashSet<>();
            while (j<n&&nums[j]/100==curDepth){
                //Viết thế này để đỡ check vào while hay chưa
                i=j;
                int curPos = (nums[j]/10%10+1)/2;
                visited.add(curPos);
                int[] prevRoot = prevNode.get(curPos);
//                System.out.printf("add: %s, curNode: %s\n", prevNode.get(curPos-1)%10, nums[j]%10);
                curNodes.put(nums[j]/10%10, new int[]{nums[j], nums[j]%10+prevRoot[1]});
                j++;
            }
//            System.out.println(prevNode);
//            System.out.printf("Rs: %s\n", rs);
            for(Map.Entry<Integer, int[]> e: prevNode.entrySet()){
                if(!visited.contains(e.getKey())){
                    rs+=e.getValue()[1];
                }
            }
            prevNode=curNodes;
        }
        for(Map.Entry<Integer, int[]> e: prevNode.entrySet()){
            rs+=e.getValue()[1];
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- If (the depth of a tree is smaller than 5), then this tree can be represented by (an array of three-digit integers).
        //- For (each integer) in this array:
        //- (The hundreds digit) represents (the depth d) of this node where:
        //  + 1 <= d <= 4.
        //- (The tens digit) represents (the position p) of (this node) in the level it belongs to where
        //  + 1 <= p <= 8.
        //  + The position is the same as that in (a full binary tree).
        //  ==> Tức là full binary tree
        //      + Số node tăng theo 2^
        //      ==> 2^3 = 8
        //- The units digit represents the value v of this node where:
        //  + 0 <= v <= 9.
        //- Given (an array of ascending three-digit integers nums) representing (a binary tree) with (a depth smaller than 5),
        //  + Mỗi value thể hiện cho 1 nodes trong tree
        //      + Nó chứa thông tin (depth, pos, value)
        //* return (the sum of all paths) from (the root) towards (the leaves).
        //- It is guaranteed that the given array represents a valid connected binary tree.
        //- Binary tree ("tự do")
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= nums.length <= 15
        //  + Length của nums không quá lớn
        //110 <= nums[i] <= 489
        //nums represents a valid binary tree with depth less than 5.
        //nums is sorted in ascending order.
        //  + Sort theo order, có thể:
        //      + depth tăng dần
        //      + pos tăng dần
        //      + value tăng dần
        //
        //- Brainstorm
        //- Biểu diễn ntn cũng là 1 cách biểu diễn binary tree
        //- Bài này ta lấy depth xét tăng dần lên là được
        //- Level traversal là được.
        //- Dùng queue để xử lý.
        //Ex:
        //      3
        //    /   \
        //   4    6
        //  /    /  \
        // 5    1    7
        //- Ở đây làm sao biết được:
        //  + 6 sẽ cộng 2 lần mà không phải 4
        //** The position is the same as that in a full binary tree.
        //- Tức là:
        //  + 1 sẽ có pos 3 thay vì 2
        //  ==> pos = 3 (prev pos=(3+1)/2)
        //      + Sẽ tính theo node = 6
        //- Cần lưu lại list node trước đó:
        //  + Để tính duplicated value.
        //
//        int[] nums = {113,215,221};
//        int[] nums = {111,217,221,315,415};
        //         1
        //       /   \
        //     7      1
        //   /
        //  5
        // /
        //5
        //+ (1+7+5+5) + (1+1) = 20
        //==> Ra 19 sai
        //- Nếu lưu lại prevNodes, bị case:
        //  + Thiếu node layer tiếp ==> Previous node sẽ không được add value.
        //  Ex:
        //  5 chỉ add 7 thôi.
        //- Ngoài ra pos của nó không hẳn là index của element trong array:
        //  + 1 số element bị khuyết.
        //- Ở đây để nhanh thì dùng hashset để mark.
        //
        //- Hoặc là dùng queue poll dần:
        //  + expected pos </> cái đang có thì poll đi.
        //  ==:
        //      + Tiếp tục.
        //
        //          3
        //        /  \
        //     n      9
        //   /  \    /  \
        //  n    n n     9
        //             /   \
        //           0      5
        //(3+9+9+5) + (3+9+9) = 26 + 21 = 46
        //wrong rs=35
        //
        //- Ngu vl:
        //  + Chỉ cần traverse + value từ root ==> next node là được.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //1196. How Many Apples Can You Put into the Basket
        //1593. Split a String Into the Max Number of Unique Substrings
        //2241. Design an ATM Machine
        int[] nums = {113,229,349,470,485};
        System.out.println(pathSumStupid(nums));
    }
}
