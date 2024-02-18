package E1_binary_search_topic;

public class E21_MaximumAverageSubarrayII {

    public static double[] checkValidWrong(int[] nums, int mid, int k){
        double sum=0;
        int n=nums.length;

        for(int i=0;i<k&&i<n;i++){
            sum+=nums[i]-mid;
        }
        double curRs=Integer.MIN_VALUE;

        if(sum>=0){
            curRs=(sum+mid*k)/k;
        }
//        System.out.println(curRs);
        int left=0;

        for(int i=k;i<n;i++){
            //Keep length
            double val1=sum+nums[i]-nums[left];
            //Length++
            double val2=sum+nums[i]-mid;

            if(val1>=val2){
                left++;
            }
            sum=Math.max(val1, val2);
            if(sum>=0){
                int range=i-left+1;
//                System.out.printf("i: %s, left: %s, sum: %s\n", i, left, sum);
                curRs=Math.max(curRs, (sum+mid*range)/range);
            }else if(nums[i]-mid>=0){
                sum=nums[i]-mid;
                left=i;
            }else{
                sum=0;
            }
            System.out.printf("Val1 : %s, val2: %s, left=%s, nums[left]: %s, i=%s, nums[i]: %s, curRs: %s\n",
                    val1, val2, left, nums[left], i, nums[i], curRs);
            System.out.println(curRs);
        }
        if(curRs!=Integer.MIN_VALUE){
            return new double[]{1, curRs};
        }
        return new double[]{0, curRs};
    }

    public static double[] checkValid(int[] nums, int mid, int k){
        double sumRight=0;
        double sumLeft=0;
        int n=nums.length;
        double minLeft=0;
        int left=-1;
        double curRs=Integer.MIN_VALUE;

        for(int i=0;i<k&&i<n;i++){
            sumRight+=nums[i]-mid;
        }
        if(sumRight>=0){
            curRs=(sumRight+mid*k)/k;
        }
//        System.out.println(curRs);

        for(int i=k;i<n;i++){
            sumLeft+=nums[i-k]-mid;
            if(minLeft>=sumLeft){
                minLeft=sumLeft;
                left=i-k;
            }
            sumRight+=nums[i]-mid;
//            if(maxRight<sumRight){
//                maxRight=sumRight;
//                right=i;
//            }
//            System.out.printf("Sum right: %s\n",sumRight);
//            System.out.printf("minLeft: %s, sumRight: %s\n", minLeft, sumRight);
            if(sumRight-minLeft>=0){
                int range=i-left;
                curRs=Math.max(curRs, (sumRight-minLeft+mid*range)/range);
            }
        }
        if(curRs>=0){
            return new double[]{1, curRs};
        }
        return new double[]{0, curRs};
    }

    public static double solution(int[] nums, int k){
        int n=nums.length;
        if(n==1){
            return nums[0];
        }
        int low=Integer.MAX_VALUE, high=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            low=Math.min(low, nums[i]);
            high=Math.max(high, nums[i]);
        }
        double rs=-1;

        //Time = O(n*log(max-min))
        while(low<=high){
            int mid=low+(high-low)/2;
            double[] curRs=checkValid(nums, mid, k);
//            System.out.printf("Mid: %s, cur: %s\n",mid, curRs[1]);
            if(curRs[0]==1){
                rs=curRs[1];
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static double findMaxAverage(int[] nums, int k) {
        return solution(nums, k);
    }

    public static void main(String[] args) {
        // Đề bài:
        //- You are given an integer array nums consisting of n elements, and an integer k.
        //* Find (a contiguous subarray) whose (length is greater than or equal to k) that has (the maximum average value) and return this value.
        //-> Tức là tìm average sum lớn nhất của các array có size>=k
        //- Any answer with a calculation error less than 10-5 will be accepted.
        //Ex:
        //Input: nums = [1,12,-5,-6,50,3], k = 4
        //Output: 12.75000
        //Explanation:
        //- When the length is 4, averages are [0.5, 12.75, 10.5] and the maximum average is 12.75
        //- When the length is 5, averages are [10.4, 10.8] and the maximum average is 10.8
        //- When the length is 6, averages are [9.16667] and the maximum average is 9.16667
        //The maximum average is when we choose a subarray of length 4 (i.e., the sub array [12, -5, -6, 50]) which has the max average 12.75, so we return 12.75
        //Note that we do not consider the subarrays of length < 4.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //n == nums.length
        //1 <= k <= n <= 10^4
        //-10^4 <= nums[i] <= 10^4
        //==> K khá lớn nên không thể tạo array 2 dimensions được.
        //
        //- Brainstorm
        //Ex:
        //Input: nums = [1,12,-5,-6,50,3], k = 4
        //Output: 12.75000
        //result = (12-5-6+50)=51/4
        //- Cách đơn giản là ta tính max sum của từng length>=k
        //  + Time = O(N^2) ==> Time limit exceed
        //Ex:
        //[1,12,-5,-6,50,3]
        //[1,13,8,2,52,55]
        //- Ta hoàn toàn có thể tính trung bình của (i,j) : average sum của các element từ (i -> j)
        // + (55)/6
        //- Length càng nhỏ thì càng tốt ==> Chia ra lớn hơn
        //  + Nếu tính dựa trên length của array thì phải đi qua all legnth -> Time = O(log(n^2))
        //  ==> Ta sẽ tính dựa trên low, high
        //  + low = (sum_all/n)
        //  + high = max(value)
        //- Value cần tìm là gì?
        //- Giả sử value cần tìm là average sum:
        //  + Value càng lớn càng tốt
        //- Check value valid ntn?
        //  + Valid khi nó là average sum của 1 subarray nào đó trong
        //  + value*(k --> n-1) : Khá khó để có thể suy được
        //
        //Ex:
        //[1,12,-5,-6,50,3]
        //[1,13,8,2,52,55]
        //Max:
        //[1,13,13,13,52,55] ==> Dùng idea max có dc k? Khá khó
        //- Nếu tính riêng theo từng k ==> chỉ cần
        // + curSum= prefix_sum-prefix_sum(i-k-1)
        //- Tìm length liệu có được không?
        //Ex:
        //[40,10,-2,40]
        //k=1 : max_sum=40
        //k=2 : max_sum=50
        //k=3 : max_sum=48
        //k=4 : max_sum=88
        //40 -> 50 -> 48 -> 88
        //mid=k=2
        //Vì: 2,50,-2,100 ==> sum=[2,52,50,150] ==> Khó mà có thể chỉ kết luận dựa trên length
        //- Tìm length hợp lý khá khó
        //
        //- Chỉ có thể đi theo hướng mid là average value
        //- Idea như thế nào?
        //- Mid thoả mãn tức là tồn tại:
        //  + length=j để sum(a1,a2,...,ak)/j >= mid
        //  ==> return k
        //  + Với (i, j), ta ưu tiên i càng nhỏ càng tốt + (j-i)>=k
        //- Bài toán con là kiểm tra xem:
        //  + Có sum subarray nào mà >=mid + length>=k hay không
        //Ex:
        //[1,12,-5,-6,50,3]
        //prefixSum=[1,13,8,2,52,55]
        //+ mid=12, k=4
        //==> a1+a2+...+ai >= mid * j (j>=k)
        //<=> (a1-mid)+(a2-mid)+...+(ai-mid)>=0 (Các giá trị có thể bù cho nhau)
        //==> Ta sẽ prefix sum bằng cách trừ cho mid
        //[1,12,-5,-6,50,3]
        //- Check tồn tại sum_sub_array>=0 là được
        //- Đã dễ hơn rất nhiều so với việ tính average bằng cách chia rồi
        //->mid=12
        //element-mid=[-11,0,-17,-18,38,-9]
        //==> slide window được k?
        // + (0,3): -11,0,-17,-18= -46
        // + (1,4): 0,-17,-18,38 = 3 ==> return true
        //
        //- Nếu không tìm được (average sum subarray >=mid)
        //  <=> tức là với mọi subarray với length>=k
        //- Không tìm được:
        //  + Giảm mid để tìm
        //- Tìm được:
        //  + Tăng mid để tìm
        //** KEY Idea là :
        //+ Nếu average sum >= mid với length>=k "không tìm được"
        //  + Ta giảm mid để tìm thêm
        //+ Nếu average sum >= mid với length>=k "tìm được":
        //  + Có thể tăng mid ==> Ta đang muốn tìm max mà
        //
        //1,12,-5,-6,50,3
        //+ (1+12+-5+-6) - 12*4 = -46
        //+ (12+-5+-6+50) - 12*4 = 3
        //  = -46 + +50-1 = 3
//        int[] nums=new int[]{1,12,-5,-6,50,3};
//        int k=4;
//        int[] nums=new int[]{1};
//        int k=1;
//        int[] nums=new int[]{1,0,1,4,2};
        //(1+0+1+4)/4 = 1.5
        //(0+1+4+2)/4 = 1.75
//        int k=4;
//        int[] nums=new int[]{8,0,1,7,8,6,5,5,6,7};
        //(8+0+1+7+8)/5=4.8
        //(0+1+7+8+6)/5 = 4.4
        //(8+0+1+7+8+6)/6 = 5
        //(8+0+1+7+8+6+5)/7 = 5
        //... ==> 8 là lớn nhất ở left nên nếu code ntn thì lúc nào nó cũng tăng i + ko replace first.
        //(8+0+1+7+8+6+5+5+6+7)/10 = 5.3
        //==> Tìm sai >=value thì cũng lỗi.
        //Expected result:
        //- before rs=(7+8+6+5+5)/5 = 6.2
        //- (7+8+6+5+5+6+7)/7 = 6.28571428571 (3 -> 9)
        //
        //- Cách trên có vẻ không được => prefix sum?
        //- [8,0,1,7,8,6,5,5,6,7], k=5
        // [3,-5,-4, (2, 3,1,0,0),1,2]
        // [3,-2,-6,-4,-1,0,0,0,1,3]
        // [3,-2,[-6],-4,-1,0,0,0,1,[3]]
        //+ (max nhất bên right) - (min nhất bên left)
        //+ max ==> Tìm bên left với (i-index-k+1>=0)
        //** Ta sẽ luôn luôn xét:
        //max(i) và min(i-k) ==> Thì ta sẽ lấy được kết quả mong muốn
        //==> BỎ "MAX" vì "WRONG"
        //- Min thì sẽ được giới hạn theo (index = i-k)
        //- Max thì "KHÔNG DÙNG KHÁI NIỆM MAX" chỉ lấy luôn current sum + luôn update vì:
        //  + max(index=i) chỉ được map với min(index-k) ==> Ta sẽ lấy luôn current sum
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(1)
        //- Time : O(n*log2(max-min))
        //
//        int k=5;
        int[] nums=new int[]{8,9,3,1,8,3,0,6,9,2};
        int k=8;
        //(8+9+3+1+8+3+0+6)/8 =4.75
        //(3+1+8+3+0+6+9+2)/8 = 4
        //(9+3+1+8+3+0+6+9)/8 = 4.875
        //(8+9+3+1+8+3+0+6+9)/8 = 5.875
        //
        //- Special case:
        //-
        //
//        System.out.println(checkValid(nums, 12, k)[0]);
//        System.out.println(checkValid(nums, 1, k)[0]);
//        System.out.println(checkValid(nums, 5, k)[0]);
//        System.out.println(checkValid(nums, 5, k)[1]);
//        System.out.println(checkValid(nums, 5, k)[1]);
        System.out.println(findMaxAverage(nums, k));
        //#Reference:
        //1770. Maximum Score from Performing Multiplication Operations
        //2250. Count Number of Rectangles Containing Each Point
        //2012. Sum of Beauty in the Array
    }
}
