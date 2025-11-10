package E1_daily;

import java.util.ArrayList;
import java.util.List;

public class E268_SortTransformedArray_fx_func {

    public static int lowerBound(int[] nums, double key){
        int low=0, high=nums.length-1;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]<key){
                rs=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n=nums.length;
        int[] rs=new int[n];
        double pivot= ((double)-b/(double)(2*a));
//        System.out.println(pivot);
        int lowerBoundLeft = (-b>0)?(n-1):-1;
        //Đoạn này có thể để là:
        //  + lowerBoundLeft=lowerBound(nums, pivot);// Find lower bound thì luôn đúng
        if(a!=0){
            lowerBoundLeft=lowerBound(nums, pivot);
        }
//        System.out.println(lowerBoundLeft);
//        System.out.println(nums[lowerBoundLeft]);
        int index=0;
        List<Integer> firstIncrease=new ArrayList<>();
        List<Integer> increaseSecond=new ArrayList<>();

        if(a>=0){
            for(int i=lowerBoundLeft;i>=0;i--){
                firstIncrease.add(a*nums[i]*nums[i]+b*nums[i]+c);
            }
            for(int i=lowerBoundLeft+1;i<n;i++){
                increaseSecond.add(a*nums[i]*nums[i]+b*nums[i]+c);
            }
        }else{
            for(int i=0;i<=lowerBoundLeft;i++){
                firstIncrease.add(a*nums[i]*nums[i]+b*nums[i]+c);
            }
            for(int i=n-1;i>=lowerBoundLeft+1;i--){
                increaseSecond.add(a*nums[i]*nums[i]+b*nums[i]+c);
            }
        }
        int i=0,j=0;
        //i=1,j=1 ==> size=2
        while (i+j+1<=n){
            if(i<firstIncrease.size()&&j<increaseSecond.size()&&firstIncrease.get(i)>increaseSecond.get(j)){
                rs[index++]=increaseSecond.get(j);
                j++;
            }else if(i<firstIncrease.size()){
                rs[index++]=firstIncrease.get(i);
                i++;
            }else if(j<increaseSecond.size()){
                rs[index++]=increaseSecond.get(j);
                j++;
            }
        }

        return rs;
    }

    private static int transform(int x, int a, int b, int c) {
        // Return the transformed result for element 'x'
        return (a * x * x) + (b * x) + c;
    }

    public static int[] sortTransformedArrayRefer(int[] nums, int a, int b, int c) {
        int[] answer = new int[nums.length];
        int left = 0, right = nums.length - 1;
        int index = 0;

        if (a < 0) {
            // When 'downward parabola' we will put the edge element (smaller elements) first.
            while (left <= right) {
                int leftTransformedVal = transform(nums[left], a, b, c);
                int rightTransformedVal = transform(nums[right], a, b, c);
                if (leftTransformedVal < rightTransformedVal) {
                    answer[index++] = leftTransformedVal;
                    left++;
                } else {
                    answer[index++] = rightTransformedVal;
                    right--;
                }
            }
        } else {
            while (left <= right) {
                // When 'upward parabola' or a 'straight line'
                // we will put the edge element (bigger elements) first.
                int leftTransformedVal = transform(nums[left], a, b, c);
                int rightTransformedVal = transform(nums[right], a, b, c);
                if (leftTransformedVal > rightTransformedVal) {
                    answer[index++] = leftTransformedVal;
                    left++;
                } else {
                    answer[index++] = rightTransformedVal;
                    right--;
                }
            }
            // Reverse the decreasing 'answer' array.
            for (int i = 0; i < answer.length / 2; i++) {
                int temp = answer[i];
                answer[i] = answer[answer.length - i - 1];
                answer[answer.length - i - 1] = temp;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (a sorted integer array nums) and three integers a, b and c,
        // apply a quadratic function of the form f(x) = ax2 + bx + c to each element nums[i] in the array, and
        //* return (the array in a sorted order).
        //
        //Example 1:
        //
        //Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
        //Output: [3,9,15,33]
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 200
        //-100 <= nums[i], a, b, c <= 100
        //nums is sorted in ascending order.
        //
        //* Brainstorm:
        //a*x^2+b*x+c - (a*y^2+b*y+c)
        //= a*(x^2-y^2) + b(x-y)
        //= (x-y)*(a*(x+y)+b)
        //- (x-y)<0
        //- a*(x+y)+b
        //
        //- How to solve in O(n) time?
        //
        //- Parameter A in: A * x^2 + B * x + C dictates the shape of the parabola.
        //  + parabola: Parabol
        //- f(x) = A * x^2 + B * x + C
        //  + f'(x) = 2*A*x+B >=0
        //      ==> x >= -B/2*A
        //- A>0:
        //  + x < -B/2*A: Decrease (0, left)
        //  + x >= -B/2*A: Increase (left+1, right)
        //- A<0
        //  + x < -B/2*A: Increase (0, left)
        //  + x >= -B/2*A: Decrease (left+1, right)
        //* Refer:
        //https://vuihoc.vn/tin/thpt-bang-bien-thien-ham-so-bac-2-1393.html
        //
        //-  double pivot= ((double)-b/(double)(2*a));
        //  + -INF
        //  + INF
        //  ==> 2 cái này khác nhau:
        //      + INF: left = n-1
        //      + -INF: left = -1
        //
        //1.1, Cases
//        int[] nums = {-4,-2,2,4};
//        int a = 1, b = 3, c = 5;
//        int[] nums = {-4,-2,2,4};
//        int a = -1, b = 3, c = 5;
//        int[] nums = {
//                -99,-94,-90,-88,-84,-83,-79,-68,-58,-52,-52,-50,-47,-45,-35,-29,-5,
//                -1,9,12,13,25,27,33,36,38,40,46,47,49,57,57,61,63,73,75,79,97,98
//        };
//        int a = -2, b = 44, c = -56;
        int[] nums = {-4,-2,2,4};
        int a = 0, b = 3, c = 5;
//        int[] rs = sortTransformedArray(nums, a, b, c);
        int[] rs = sortTransformedArrayRefer(nums, a, b, c);
        //Output: [-24014,-21864,-20216,-19416,-17864,-17486,-16014,-12296,-9336,-7752,-7752,-7256,-6542,-6086,-4046,-3014,-326,-102,178,-14952,-14606,-9062,-8006,-7502,-5222,-4814,-4046,-4046,-2702,-2406,-2264,-1496,-1272,-1064,-782,-326,-206,178,184]
        //Expected: [-24014,-21864,-20216,-19416,-17864,-17486,-16014,-14952,-14606,-12296,-9336,-9062,-8006,-7752,-7752,-7502,-7256,-6542,-6086,-5222,-4814,-4046,-4046,-4046,-3014,-2702,-2406,-2264,-1496,-1272,-1064,-782,-326,-326,-206,-102,178,178,184]
        //
        //1.2, Optimization
        //- Thực ra không cần binary searc tìm pivot
        //==> Chỉ cần left, right
        //- Dựa trên (a>0/a<0) để quyết định
        //  + left++/ right--
        //==> Chọn từng element là được (From small to big)
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n)
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
    }
}
