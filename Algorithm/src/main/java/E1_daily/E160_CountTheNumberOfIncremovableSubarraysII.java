package E1_daily;

public class E160_CountTheNumberOfIncremovableSubarraysII {

    public static long incremovableSubarrayCountWrong(int[] nums) {
        int n=nums.length;
        int left=0;
        long rs=0;

        for(int i=1;i<n;i++){
            if(nums[i]>nums[i-1]){
                left=i;
            }else{
                break;
            }
        }
        int right=n-1;
        for(int i=n-2;i>=0;i--){
            if(nums[i]<nums[i+1]){
                right=i;
            }else{
                break;
            }
        }
//        System.out.println(right);
        for(int i=0;i<=left;i++){
            int tmpRight=right;
            while(tmpRight<n&&nums[i]>=nums[tmpRight]){
                tmpRight++;
            }
            if(tmpRight<n){
                rs+=n-tmpRight;
//                System.out.printf("Left: %s, right: %s\n", i, tmpRight);
            }
//            rs+=n-Math.min(i+1, tmpRight);
            if(tmpRight>left+1){
                rs++;
            }
//            rs++;
        }
        int tmpLeft=0;
        for(int i=right;i<n;i++){
            while(tmpLeft<left&&nums[tmpLeft]>=nums[i]){
                tmpLeft++;
            }
            if(tmpLeft<=left){
                rs+=tmpLeft+1;
//                System.out.printf("Left: %s, right: %s\n", i, tmpRight);
            }
//            rs+=Math.min(i-1, tmpLeft)+1;
            if(i>tmpLeft+1){
                rs++;
            }
        }
//        System.out.println(rs);
//        while (right<=left){
//            right++;
//        }
//        rs+=n-right;
//        System.out.println(n-right);
        return rs;
    }

    public static long incremovableSubarrayCountReference(int[] nums) {
        int n = nums.length;
        int i=0;
        while(i+1<n&&nums[i]<nums[i+1]){
            i++;
        }
        if(i==n-1){
            return (long) n *(n+1)/2;
        }
        long rs=i+2;
        //Case init:
        //- Ta không dùng right:
        //  + rs+=i+2
        //  <=> toàn bộ (i+1, n) sẽ là chuỗi kết hợp với substring(0, 1->i+1)

        for (int j = n-1; j >=0 ; j--) {
            //1,2,3
            if(j<n-1&&nums[j]>=nums[j+1]){
                break;
            }
            //2,[6](i==1),5,4,3,7,[8](j)
            //- Bỏ qua idea liên quan đến remove xem có dễ hơn không
            //- remove all of substring(0,i<1(==6):
            //  + count = i+1
            //- remove(i+1,j):
            //  + i+=1
            //- remove subString(0,j)???
            //  + (2,6,5,4,3,7)[8]
            //- Hiểu sai:
            //- remove all of substring(0,i<1(==6):
            //==> WRONG
            //* TRUE IDEA:
            //- We remove (i+1,j) ==> Non rule
            //  + rs+=1
            //- We remove the combination of (i+1,j) + substring(k,i+1)
            //  + Nói tóm lại là kết hợp đoạn non-rule với (each suffix string trước đó)
            //      + Số case suffix string = i+1
            //      + Số case không combine = 1
            //  => rs+=i+2
            //- In the cases above, We also include the case substring(0, j)
            //  + Tức là include cả case prefix[0-j-1] rồi = combine bên trên.
            //
            while(i>=0&&nums[i]>=nums[j]){
                i--;
            }
            //nums[i]<nums[j]
            rs+=i+2;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given a 0-indexed array of positive integers nums.
        //- (A subarray of nums) is called incremovable if nums becomes (strictly increasing) on removing the subarray.
        //  + For example, the subarray [3, 4] is an incremovable subarray of [5, 3, 4, 6, 7] because
        //  removing this subarray changes the array [5, 3, 4, 6, 7] to [5, 6, 7] which is (strictly increasing).
        //* Return (the total number of incremovable subarrays) of nums.
        //- Note that an empty array is considered (strictly increasing).
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //
        //- Brainstorm
        //Ex:
        //nums = [1,2,3,10,4,2,3,5]
        //- Remove [1,2,3,(10,4,2,3),5]
        //  + rs = 1 + 3 + 1 + 3 = 8
        //- Remove [1,2,3,10,(4,2,3,5)]
        //  + rs = 8+1
        //- Remove [(1,2,3,10,4),2,3,5]
        //  + rs +...
        //- The number of case seem to be quite different the E159
        //Ex:
        //nums = [1,2,3,10,4,2,3,5]
        //nums = [(1,2,3,10),4,2,3,5]
        //nums = [1,2,3,10,4,(2,3,5)]
        //- For each (left):
        //  + We don't find the right:
        //      + rs+=1
        //  + We find the right such that:
        //      + nums[right]>nums[left]:
        //      Ex: [0,2,(3),4,5,9]
        //      n-1-index = 5-2 = 3
        //      ==> 4 cases ==> (n-index)
        //          + rs+= n-index
        //
        //Ex:
        //nums = [1,2,3,10,4,2,3,5]
        //nums = [(1,2,3,10),4,2,3,5]
        //nums = [1,2,3,10,4,(2,3,5)]
        //rs+=1 (Remove the whole array)
        //rs+=4 (For each i in left side, we remove all rights)
        //rs+=3+2+1
        //rs+=3 (For each i in right side, we remove all lefts)
        //=> rs = 14
        //
        //- If we remove range from (left==0, n-1)
        //  + It means we remove the whole array
        //- right may be <= left
        //  + Remove all elements in right side need to be ignored several cases
        //
        //
//        int[] nums = {1,2,3,10,4,2,3,5};
//        int[] nums = {1,2,3,4};
        //rs = [2],[2,3],[2,3,4], [3],[3,4],[4]
        //rs = [1],[1,2],[1,2,3],[1,2,3,4]
        //
        //expected = 10
        int[] nums = {6,5,7,8};
        //[6],5,[7],8
        //- We can remove
        //
        //- Check left site:
        //  + index=0: [5]
        //
        //- Check right site:
        //  + index=1(5): [6]
        //  + index=2(7): [6,5],[5]???
        //  + index=3(8): [6,5,7]
        //[6],5,4,3,[7],8 (left as pivot)
        //- We can remove [5,4,3],[(5,4,3),7],[(5,4,3),7,8]
        //
        //- Special cases:
        //2,6,5,7,[8],9
        //+ [6,5],[6,5,7],[6,7,8]
        //  ==> Ta thấy rằng khi xét right thì (5,7,8,9) nếu không chọn mốc cho last element thì sẽ bị flex nhiều cases ntn
        //* Chọn last element để count.
        //
        //2,[6],5,4,[3],7,8 (right as pivot)
        //- We can remove:
        //  + index =4(3): [6,5,4],[2,6,5,4]
        //  + index =5(7): [5,4,3],[6,5,4,3],[2,6,5,4,3]
        //  + index =6(8): [5,4,3,7],[6,5,4,3,7],[2,6,5,4,3,7]
        //
        //expected = 7
        //rs = [5],[5,7],[5,7,8]
        //rs = [6,5,7,8]
        //rs = [6],[6,5],[6,5,7]
        //rs = 1
        //rs+=3,2,1
        //
        //===> Cách này khá khó để có thể giải quyết được + in interview cũng khó
        //
        //* Case init:
        //- Ta không dùng right:
        //  + rs+=i+2
        //  <=> toàn bộ (i+1, n) sẽ là chuỗi kết hợp với substring(0, 1->i+1)
        //
        //2,[6](i==1),5,4,3,7,[8](j)
        //* Combination case:
        //- Bỏ qua idea liên quan đến remove xem có dễ hơn không
        //- remove all of substring(0,i<1(==6):
        //  + count = i+1
        //- remove(i+1,j):
        //  + i+=1
        //- remove subString(0,j)???
        //  + (2,6,5,4,3,7)[8]
        //- Hiểu sai:
        //- remove all of substring(0,i<1(==6):
        //==> WRONG
        //* TRUE IDEA:
        //- We remove (i+1,j) ==> Non rule
        //  + rs+=1
        //- We remove the combination of (i+1,j) + substring(k,i+1)
        //  + Nói tóm lại là kết hợp đoạn non-rule với (each suffix string trước đó)
        //      + Số case suffix string = i+1
        //      + Số case không combine = 1
        //  => rs+=i+2
        //- In the cases above, We also include the case substring(0, j)
        //  + Tức là include cả case prefix[0-j-1] rồi = combine bên trên.
        //
        //* Reference solution:
        //- Find the longest increasing prefix subarray. If the A is strict increasing,
        //- return n * (n + 1) / 2.
        //
        //- The check the all increasing suffix array one element by one element,
        //and find out the longest smaller increasing prefix by moving the pointer i.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
//        System.out.println(incremovableSubarrayCountWrong(nums));
        System.out.println(incremovableSubarrayCountReference(nums));
        //#Reference:
        //1996. The Number of Weak Characters in the Game
        //909. Snakes and Ladders
        //2778. Sum of Squares of Special Elements
    }
}
