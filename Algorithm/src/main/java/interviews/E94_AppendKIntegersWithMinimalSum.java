package interviews;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class E94_AppendKIntegersWithMinimalSum {

    public static long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long rs=0;
        int n=nums.length;
        long valueLeft=0;
        int count=0;

        for(int i=0;i<n&&count<k;i++){
            if(nums[i]==valueLeft){
                continue;
            }
            long leftV=valueLeft*(valueLeft+1)/2;
            long rightV;

            if(nums[i]-valueLeft-1<k-count){
                count+=nums[i]-valueLeft-1;
                rightV= (long) (nums[i] - 1) *nums[i]/2;
            }else {
                rightV=(valueLeft+k-count)*(valueLeft+k-count+1)/2;
                count=k;
            }
            rs+=rightV-leftV;
            //
            valueLeft=nums[i];
        }
        if(count<k){
            long leftValue= (long) nums[n - 1] *(nums[n-1]+1)/2;
            long rightValue= (long) (nums[n - 1] + k - count) *(nums[n-1]+k-count+1)/2;
            rs+=rightValue-leftValue;
        }
        return rs;
    }

    public static long minimalKSumOptimize(int[] nums, int k) {
        Arrays.sort(nums);
        long ans = 0, lo = 1;
        for (int num : nums) {
            if (num > lo) {
                long hi = Math.min(num - 1, lo + k - 1);
                int cnt = (int)(hi - lo + 1);
                ans += (lo + hi) * cnt / 2;
                k -= cnt;
                if (k == 0) {
                    return ans;
                }
            }
            lo = num + 1;
        }
        if (k > 0) {
            ans += (lo + lo + k - 1) * k / 2;
        }
        return ans;
    }

    public static long minimalKSumMethod2(int[] nums, int k) {
        long ans = k * (k + 1L) / 2;
        TreeSet<Integer> unique = new TreeSet<>();
        for (int num : nums) {
            unique.add(num);
        }
        while (!unique.isEmpty()) {
            int first = unique.pollFirst();
            if (k >= first) {
                ans += ++k - first;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,4,25,10,25};
//        int arr[]=new int[]{5,6};
//        int k=2;
//        int k=6;
        //Case 1:
//        int arr[]=new int[]{96,44,99,25,61,84,88,18,19,33,60,86,52,19,32,47,35,50,94,17,29,98,22,21,72,100,40,84};
//        int k=35;
        int arr[]=new int[]{1,5,5,5,6,8,10};
        int k=5;
        System.out.println(minimalKSum(arr, k));
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Vì điền thêm unique số vào sao cho MIN SUM
        //--> Ta chỉ điền các số nhỏ nhất vào
        //Để xác định số đó nhở nhất hay không --> Ta cần sắp xếp Array trước
        //==> Để lấy các số đi tử min --> max.
        //VD:
        //3,5,8,10, k=5
        //Ta sẽ điền vào các số: 1,2,4,6,7.
        //1.1, Code như sau:
        //+ valueLeft=0 , compare với arr[i]
        //+ Sau đó valueLeft sẽ được update =arr[i]
        //- Có 2 trường hợp:
        //* k ở giữa (valueLeft và arr[i])
        //+ không đủ
        //+ Đủ
        //
        //* Đến cuối k vẫn quá lớn so với các số được thêm vào tử arr[]
        //+ --> Trường hợp này ta sẽ += k-count riêng.
        //
        //1.2, Còn tính sum :
        //CT:
        // m+m+1+...+ (n-1) = n(n+1)/2 - m(m+1)/2.
        //1.3, Dùng Tree add element liênt tục sẽ slower than Arrays.sort(all)
        //1.4, Vẫn là cách 1 chỉ khác ở chỗ:
        //CT: 3,4,5= (5+3) * (count=3) /2 = 12
        //--> Không cần dùng CT : n(n+1)/2 - m(m+1)/2.
        //Cách 2:
        //1. Add element vào tree sau đó cũng + như bình thường.
        System.out.println(minimalKSumOptimize(arr, k));
        System.out.println(minimalKSumMethod2(arr, k));
    }
}
