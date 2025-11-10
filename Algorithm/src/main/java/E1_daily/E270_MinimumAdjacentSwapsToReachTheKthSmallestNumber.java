package E1_daily;

import java.util.*;

public class E270_MinimumAdjacentSwapsToReachTheKthSmallestNumber {

    public static char[] getKSmallestWonderfulWrong(String num, int k){
        int n=num.length();
        char[] numArr=num.toCharArray();
        char[] sortArr=Arrays.copyOf(numArr, n);
        Arrays.sort(sortArr);
//        List<String> set=new ArrayList<>();
//        System.out.println(sortArr);
        //
        //2,3,5,6,10
        //10,5,6,3,2
        for(int i=0;i<k;i++){
            //4,3,2,1
            int l;
            for(l=n-1;l>=0;l--){
                if(sortArr[n-l-1]!=numArr[l]){
                    break;
                }
            }
//            System.out.println(l);
//            System.out.printf("%s %s\n",l, numArr[l]);
            for(int j=l-1;j>=0;j--){
                if(numArr[j]<numArr[l]){
//                    System.out.printf("swap(%s, %s)\n", numArr[j], numArr[l]);
//                    System.out.println("Before");
//                    System.out.println(numArr);
                    char temp=numArr[j];
                    numArr[j]=numArr[l];
                    numArr[l]=temp;
                    Arrays.sort(numArr, j+1, n);
//                    set.add(String.valueOf(numArr));
//                    System.out.printf("%s %s\n", i, Arrays.toString(numArr));
                    break;
                }
            }
//            System.out.println(numArr);
        }
//        System.out.println(set);
//        for (String s: set){
//            System.out.println(s);
//        }
//        System.out.println();
        return numArr;
    }

    public static char[] getKSmallestWonderful(String num, int k){
        int n=num.length();
        char[] numArr=num.toCharArray();
//        char[] sortArr=Arrays.copyOf(numArr, n);
//        Arrays.sort(sortArr);
//        List<String> set=new ArrayList<>();
//        System.out.println(sortArr);
        //
        //2,3,5,6,10
        //10,5,6,3,2
        //Time: O(k)
        for(int i=0;i<k;i++){
            TreeSet<int[]> sortedList=new TreeSet<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[0]!=o2[0]){
                        return o1[0]-o2[0];
                    }
                    return o1[1]-o2[1];
                }
            });
            //Time: O(n*log(n))
            sortedList.add(new int[]{numArr[n-1]-'0', n-1});
            int swapIndex=-1;
            int j;
            //4,3,2,1
            for(j=n-2;j>=0;j--){
                //1,3,6,10
                int[] elementSwap = sortedList.ceiling(new int[]{numArr[j]-'0'+1, -1});
                if(elementSwap!=null){
                    swapIndex=elementSwap[1];
                    break;
                }
                sortedList.add(new int[]{numArr[j]-'0', j});
            }
            char temp=numArr[j];
            numArr[j]=numArr[swapIndex];
            numArr[swapIndex]=temp;
            Arrays.sort(numArr, j+1, n);
//            System.out.println(numArr);
        }
//        System.out.println(set);
//        for (String s: set){
//            System.out.println(s);
//        }
//        System.out.println();
        return numArr;
    }

    public static int getMinSwaps(String num, int k) {
        int n=num.length();
        char[] targetString = getKSmallestWonderful(num, k);
//        char[] targetString = Arrays.copyOf(num.toCharArray(), n);
//        for (int i = 0; i < k; i++) {
//            nextPermutation(targetString);
//            System.out.println(String.valueOf(targetString));
//        }
//        List<Integer>[] numToindices=new List[10];
//        char[] valToTargetNum=new char[10];
//
//        for(int i=0;i<n;i++){
//            valToTargetNum[num.charAt(i)-'0']=targetString[i];
//        }
//        char[] orgNum= num.toCharArray();

        //5489355142 => 5489355421
        //Ex:
        //3,4,5,1
        //=>
        //1,4,3,5
        //3 <=> 1
        //  + 1,3,4,5
        //  ==> Go right 1 unit
        //+ left=0, right=1
        //rs+=2
        //
        //- Use list
        //- Init: list = [1]
        //- swap(1,3)
        //  + add(3): 1,3
        //==> Use list to (remove + add)
        //
        List<Character> listNum=new ArrayList<>();
        for(int i=0;i<n;i++){
            listNum.add(num.charAt(i));
        }
//        System.out.println(listNum);
//        System.out.println(targetString);
        int rs=0;
        for(int i=0;i<n;i++){
            char c= listNum.get(i);
            if(c==targetString[i]){
//                System.out.println(i);
                continue;
            }
            int j;
            for(j=i+1;j<n;j++){
                rs++;
                if(listNum.get(j)==targetString[i]){
                    break;
                }
            }
            if(j<n){
//                System.out.printf("Swap(%s, %s), %s %s\n", i, j, num.charAt(i), targetString[j]);
//                System.out.printf("Before: %s\n", listNum);
                listNum.remove(j);
                listNum.add(i, targetString[i]);
//                System.out.printf("After: %s\n", listNum);
            }
//            System.out.println(listNum);
        }
        return rs;
    }

    public static int getMinSwapsRefer(String num, int k) {
        char[] arr = num.toCharArray();
        for (int i = 0; i < k; i++) {
            nextPermutation(arr);
        }
//        System.out.println(arr);
        char[] ori = num.toCharArray();
        return CountSteps(ori, arr, arr.length);
    }

    public static void nextPermutation(char[] nums) {
        if (nums.length == 0) return;
        int len = nums.length;
        for (int i = len - 1; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) {
                reverse(nums, i);
                for (int j = i; j < len; j++) {
                    if (nums[j] > nums[i - 1]) {
                        swap(nums, i - 1, j);
                        return;
                    }
                }
            }
        }
        reverse(nums, 0);
    }

    public static void reverse(char[] nums, int i) {
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static int CountSteps(char []s1, char[] s2, int size) {
        int i = 0, j = 0;
        int count = 0;

        while (i < size) {
            j = i;

            while (s1[j] != s2[i]) {
                j += 1;
            }
            while (i < j) {
                swap(s1, j, j - 1);
                j -= 1;
                count++;
            }
            i++;
        }
        return count;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string num, representing (a large integer), and an integer k.
        //- We call (some integer wonderful):
        //  + if it is (a permutation of the digits) in num and is ("greater") in value than num.
        //- There can be many wonderful integers.
        //- However, we only care about (the smallest-valued ones).
        //
        //For example, when num = "5489355142":
        //The 1st smallest wonderful integer is "5489355214".
        //The 2nd smallest wonderful integer is "5489355241".
        //The 3rd smallest wonderful integer is "5489355412".
        //The 4th smallest wonderful integer is "5489355421".
        //* Return (the minimum number of adjacent digit swaps) that needs to be applied to num to reach (the kth smallest wonderful integer).
        //
        //- The tests are generated in such a way that (kth smallest wonderful integer) exists.
        //
        //Example 1:
        //
        //Input: num = "5489355142", k = 4
        //Output: 2
        //Explanation: The 4th smallest wonderful number is "5489355421". To get this number:
        //- Swap index 7 with index 8: "5489355142" -> "5489355412"
        //- Swap index 8 with index 9: "5489355412" -> "5489355421"
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= num.length <= 1000
        //1 <= k <= 1000
        //num only consists of digits.
        //  + Length <= 1000 ==> Time: O(n^2)
        //
        //* Brainstorm:
        //- Get kth smallest wonderful num
        //- BFS to find the shortest path
        //
        //Ex:
        //5489355142
        //5489355214
        //
        //123
        //132
        //213
        //231
        //312
        //321
        //
        //- In the left, We find the (first number) is smaller than (the last digit)
        //  + Sort all chars in the right
        //Ex:
        //142
        //214
        //241
        //412
        //421
        //==> 1 is smallest
        //
        //- Control (last char) by using index is increased (every loop turn).
        //- How to swap x to y by (adjacent digit swaps)
        //Ex:
        //3,4,5,1
        //=>
        //1,3,4,5
        //Making array to:
        //  + 3 <=> 1: right:3
        //  + 4 <=> 3: left:1
        //  + 5 <=> 4: left:1
        //  + 1 <=> 5: left:1
        //
        //- BFS is too big to traverse
        //
        //3,4,5,1
        //=>
        //3,4,1,5
        //=>
        //3,1,4,5
        //=>
        //1,3,4,5
        //==> When we swap (1,3)
        //==> we move all numbers (3,4,5) to the right (1 unit)
        //- 3 <=> 1: Right(3)
        //
        //Ex:
        //3,4,5,1
        //=>
        //1,4,3,5
        //
        //- Making array to:
        //  + 3 <=> 1: right:3
        //  + 4: 0
        //  + 5 <=> 3: left:2
        //  + 1 <=> 5: left:1
        //Ex:
        //3,4,5,1
        //=>
        //1,3,4,5
        //rs+=2
        //* Main point:
        //- Go from left to right:
        //  + Do element by element
        //  ==> Best way
        //
        //
//        String s= "5489355142";
//        int k=4;
//        String s= "059";
//        int k=5;
        //059
        //095
        //509
        //590
        //905
        //950
        //- swap(059, 950)
        //0,5,9 => 9,0,5: rs+=2
        //9,0,5 => 9,5,0: rs+=1
        //
        //s= 5489355142 to 5489355421
        //
//        String s= "11112";
        //target = 21111
        //target = 21111
//        int k=4;
//        String s= "99499";
//        int k=1;
        //max= 99994
        //99949
        //Fix: ==> swap nums[l]>nums[j]
        //
        //948653 => 948653
        //953468
        //953486
        //953648
        //953684
        //954368
        //954386
        //
        String s= "948635";
        //Target= 986435
        //Expected = 985436 <=> 57 [9, 8, 5, 4, 3, 6] (Wrong)
        //55 [9, 8, 5, 3, 4, 6]
        //56 [9, 8, 5, 3, 6, 4]
        //57 [9, 8, 5, 4, 3, 6]
        int k=64;
        //
        //948653
        //953468
        //953486
        //953648
        //953684
        //......
        //954368
        //954386
        //
        //<=>
        //948653
        //953468
        //953486
        //953648
        //953684
        //953846 ==> Lack
        //953864 ==> Lack
        //
        //953684 ==> 953846
        //- Solution:
        //  ==> For(n^2) to find (the best pair)
        //  ==> TLE
        //- Thay vì thế:
        //953684
        //* Suffix max:
        //- max[i]: Là max val từ [i+1,n-1]
        //  + if element[i] < max[i] ==> Swap được
        //- Nếu nhiều th cùng val thì sao?
        //  + 9536845444
        //  ==> Nên swap với thằng nào cũng được
        //  ==> Vì về sau còn sort
        //
        //Ex:
        //986[3]5(4)
        //=>
        //  + 3<5, 3<4 ==> Chọn 4 (Nên idea bên trên chưa đúng lắm)
        //  ==> Chọn thằng >3 ==> Nhưng min (VÌ KHÔNG QUAN TRỌNG INDEX ==> Có thể)
        //  ==> Binary search
        //
        //  + (3,4) vs (6,8)
        //  ==> Choose (6,8)
        //  + Không replace(4,3) ==> Quá sớm
        //
        //1.2, Optimization
        //-
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(k*n*log(n)+n^2) ==> O(n^2)
        //
//        System.out.println(getKSmallestWonderful(s, k));
        System.out.println(getMinSwaps(s, k));
        System.out.println(getMinSwapsRefer(s, k));
    }
}
