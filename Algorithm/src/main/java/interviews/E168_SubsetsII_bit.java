package interviews;

import java.util.*;

public class E168_SubsetsII_bit {

    /*
    Cách giải sai do thiếu cases
     */
    public static List<List<Integer>> subsetsWithDupWrong(int[] nums) {
        List<Integer> numsUnques = new ArrayList<>();
        boolean[] visited = new boolean[11];
        int[] count = new int[11];

        for (Integer i : nums) {
            if (!visited[i]) {
                numsUnques.add(i);
                visited[i] = true;
            }
            count[i]++;
        }
        int length = numsUnques.size();
        List<List<Integer>> rs = new ArrayList<>();
//        System.out.println(1<<(length));


        return rs;
    }

    public static List<List<Integer>> subsetsWithDupSlow(int[] nums) {
        int length = nums.length;
        ArrayList rs;
        HashSet<List<Integer>> rsSet = new HashSet<>();
//        System.out.println(1<<(length));

        for (int i = 0; i <= (1 << (length)) - 1; i++) {
            int currentBit = i;
            int currentCount = 0;
            List<Integer> list = new ArrayList<>();

//            System.out.printf("%s,", i);
            while (currentBit != 0) {
                if ((currentBit & 1) == 1) {
                    list.add(nums[length - currentCount - 1]);
//                    System.out.printf("%s %s %s, ", i, currentCount, length-currentCount-1);
                }
                currentBit = currentBit >> 1;
                currentCount++;
            }
            Collections.sort(list);
//            System.out.printf("%s %s,", i, list);
//            System.out.printf("%s\n", currentBit);
            rsSet.add(list);
        }
//        System.out.println(rsSet.size());
        rs = new ArrayList(rsSet);
        return rs;
    }

    /*
    - Cách này sẽ sort ngay từ đầu --> Tăng tốc độ, tránh khi trước khi thêm vào list phải sort (Nhiều lần loop).
     */
    public static List<List<Integer>> subsetsWithDupOptimizeSort(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        ArrayList rs;
//        System.out.println(1<<(length));
        HashSet<List<Integer>> rsSet = new HashSet<>();

        for (int i = 0; i <= (1 << (length)) - 1; i++) {
            int currentBit = i;
            int currentCount = 0;
            List<Integer> list = new ArrayList<>();

//            System.out.printf("%s,", i);
            while (currentBit != 0) {
                if ((currentBit & 1) == 1) {
                    list.add(nums[length - currentCount - 1]);
//                    System.out.printf("%s %s %s, ", i, currentCount, length-currentCount-1);
                }
                currentBit = currentBit >> 1;
                currentCount++;
            }
            rsSet.add(list);
//            System.out.printf("%s %s,", i, list);
//            System.out.printf("%s\n", currentBit);
        }
//        System.out.println(rsSet.size());
        rs = new ArrayList(rsSet);
        return rs;
    }

    /*
    Phần này mình sẽ dùng phương pháp đệ quy để xử lý --> Chưa refactor
     */
    public static List<List<Integer>> subsetsWithDupRecursive(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> rs = new ArrayList<>();
        subsetRecursion(new ArrayList<>(), rs, 0, nums, false);
        System.out.println(rs);
        return rs;
    }

    public static void subsetRecursion(
            List<Integer> list, List<List<Integer>> currentList, Integer index, int[] nums, boolean beforeFlag) {
        if (index == nums.length) {
            currentList.add(new ArrayList(list));
            return;
        }
        if (index >= 1 && nums[index] == nums[index - 1]) {
            if (beforeFlag) {
                list.add(nums[index]);
                subsetRecursion(list, currentList, index + 1, nums, true);
                list.remove(list.size() - 1);
            }
            subsetRecursion(list, currentList, index + 1, nums, false);
        } else {
            list.add(nums[index]);
            subsetRecursion(list, currentList, index + 1, nums, true);
            list.remove(list.size() - 1);
            subsetRecursion(list, currentList, index + 1, nums, false);
        }
    }

    public static List<List<Integer>> subsetsWithDupRecursiveRefactor(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> rs = new ArrayList<>();
        subsetRecursionRefactor(new ArrayList<>(), rs, 0, nums, false);
        System.out.println(rs);
        return rs;
    }

    /*
    - Ở đây mình đẩy phần beforeFlag=false lên đầu tiên --> Vì nó không cần thay đổi (list) ==> Đặt trước hay đặt sau đều được
    ==> Nên nhớ rằng list --> Vẫn phải remove element (Mặc dù không qua đoạn flag=false)
    ==> Do list --> Sẽ ảnh hưởng đến parent recursion.
    - Ở đây mình refactor lại 1 chút --> Cách đảo dấu của (beforeFlag) đi ---> Tránh lặp code đoạn (flag=true)
     */
    public static void subsetRecursionRefactor(
            List<Integer> list, List<List<Integer>> currentList, Integer index, int[] nums, boolean beforeFlag) {
        if (index == nums.length) {
            currentList.add(new ArrayList(list));
            return;
        }
        subsetRecursion(list, currentList, index + 1, nums, false);

        if (index >= 1 && !beforeFlag && nums[index] == nums[index - 1]) {
            return;
        }
        list.add(nums[index]);
        subsetRecursion(list, currentList, index + 1, nums, true);
        list.remove(list.size() - 1);
    }

    public static List<List<Integer>> subsetsWithDupChooseAllElements(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), nums, 0);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> ls, int[] nums, int pos) {
        res.add(new ArrayList<>(ls));
        for (int i = pos; i < nums.length; i++) {
            if (i > pos && nums[i] == nums[i - 1]) continue;
            ls.add(nums[i]);
            helper(res, ls, nums, i + 1);
            ls.remove(ls.size() - 1);
        }
    }

    public static List<List<Integer>> subsetsWithDupInterative(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int currentResultSize = 0;
        System.out.println();

        for (int i = 0; i < nums.length; i++) {
            int startIndex = 0;

            if (i >= 1 && nums[i] == nums[i - 1]) {
                startIndex = currentResultSize;
            }

            currentResultSize = res.size();

            while (startIndex < currentResultSize) {
                List<Integer> list = new ArrayList<>(res.get(startIndex));
                list.add(nums[i]);
                System.out.println(list);
                res.add(list);
                startIndex++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //Case tổng quát hơn chút khi tồn tại : [2,2], [2,2,3]
        int arr[] = new int[]{1, 2, 2, 3};
        //Case cơ bản để có tồn tại [2,2]
//        int arr[]=new int[]{1,2,2};
//        int[] arr =new int[]{4,4,4,1,4};
//        int arr[]=new int[]{1};
//        int arr[]=new int[]{};
        HashMap<List<Integer>, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        map.put(list, 1);
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        System.out.println(1 << 0);
        //==> Điều này chứng tỏ list bên trên : các elements trong list bắt buộc phải giống thứ tự nhau.
        System.out.println(map.get(list1));
        System.out.println(subsetsWithDupSlow(arr));
        System.out.println(subsetsWithDupOptimizeSort(arr));
        System.out.println(subsetsWithDupRecursive(arr));
        System.out.println(subsetsWithDupRecursiveRefactor(arr));
        System.out.println(subsetsWithDupChooseAllElements(arr));
        //
        //** Đề bài
        //- Liệt kê danh sách các dãy con (Không liên tiếp) có thể của array.
        //- Các dãy con cần unique:
        //
        //Input: nums = [1,2,2]
        //Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
        //==> 123 <=> 132
        //
        //** Bài này tư duy như sau:
        //0, Các tư duy sai lầm, tip
        //0.1, Mặc dù áp dụng tư duy về việc unique data ==> Là đúng
        //--> Nhưng không phải đúng trong mọi trường hợp.
        //VD:
        //1,2,2,3
        //==> Nếu ta làm sạch Data --> 1,2,3
        //==> Nếu chỉ lấy tập con trên 1 tập hợp ntn --> Thiếu {2,2}, {1,2,2},...
        //
        //0.2,
        //- Nếu làm sạch Data + xét các case các elements giống nhau ==> Chưa đủ
        //VD: {2,2,2,2} --> {2,2,2,2},{2,2,2},{2,2} ==> Chưa đủ khi còn thiếu các cases
        //{2,2,2,1},{2,2,2,3} : Các case kết hợp với các số khác.
        //
        //0.3, 1<<length
        //VD: 1<<0 = 1
        //Nếu muốn lấy theo format (1111)
        //1111 = 10000 - 1
        //==> 1111= (1<<4) -1
        //
        //0.4, Các loại Object cùng DataType --> Không phải loại nào cũng có thể hash + lấy data được
        //VD:
        //- List ==> Vẫn có thể hash được nhưng phải thảo mãn điều kiện:
        //+ Các elements trong list lúc put + get --> Là phải giống nhau
        //
        //- PriorityQueue không áp dụng hashMap được
        //
        //0.5, Kinh nghiệm log nhiều cách ra màn hình
        //- Log --> Cần prefix + \n (tùy)
        //
        //Cách 1: HashSet
        //1, Bài này tư duy như sau:
        //1.1, Tư duy ở đây là áp dụng HashSet cho List<Integer>
        //--> Vì để apply List<Integer> cần đúng Order --> ta đã có 2 cách để sắp xếp elements:
        //+ Sắp xếp từ đầu
        //+ Sắp xếp list trước khi add và HashSet.
        //
        //Cách 2: Recursion
        //1,
        //1.1, Ta sẽ sắp xếp Array --> Để đảm bảo các phần tử giống nhau đứng cạnh nhau:
        //VD: 1,2,2,3,4
        //==> Phục vụ múc đích kiểm tra phần tử đằng trước đã chọn ntn (Chỉ các phần tử giống nhau mới cần quan tâm cái này)
        //1.2,
        //Quy luật như sau:
        //VD:
        //2,2,2,2
        //- 1,(0),0 : Với flag đằng trước là 0 (false) --> đằng sau phải chọn 0 (false)
        //VD : Các case {2,3} : 2 chỉ có duy nhất sẽ được cover.
        //- 1,1,1   : Với flag đằng trước là 1 (true) --> Đằng sau có thể chọn (1/0 (true/ false)
        //VD: Các cases {2,2,2} (all 2),{2,2} (1 phần 2) đều phải được cover.
        //
        //1.3, Phần tích
        //1 :
        //1,3 :
        //1,2,3 :
        //1,2,2,3
        //1,2,2,2,3
        //1,2,2,2,2,3
        //- Các case 1 phần (2) : VD {2,2,3,4} --> Là kết quả của việc chọn (false) kéo theo false nhiều lần (Không được phép true nữa)
        // + chọn (true) ==> Theo sau đó là (true/false)
        //+ 1 theo sau đó là 1 --> đủ tất cả case (all --> 1 phần) VD: 1111(4)/ 111 (3) : là hệ quả của việc 1->1 liên tiếp
        //+ 1 theo sau đó là 0 --> là việc tiếp theo không chọn nữa (Cấm chọn) ==> Bổ nghĩ cho việc (1 theo sau đó là 1)
        //- Các case all elements (2, 2, 2, 2) : VD {2,2,2,2} --> Là kết quả của việc chọn (true) all elements.
        //
        //Cách 2.1: Đệ quy chọn elements:
        //==> loop all elements --> Để chọn phần tử nào cần điền. ==> Với cách này sẽ dùng continue (Thay vì return)
        //--- Nó cũng cover hết case như trên.
        //
        //Cách 3:
        //1,
        //1.1,
        //- Tư duy chính ở đây là tư duy dạng thêm phần từ lần lượt vào các list cũ.
        //==> Chỉ thêm vào các list có phần tử 2 ==> Số lượng phần tử 2 lúc đó có level = current level -1
        //==> Tức là các phần tử 2 đó chính tương đuơng vòng recursive trước đó (Vòng mà đã thêm phần tử tương tự lần thứ m-1).
        //
        //VD:
        // 1,2,2,3
        //Steps:
        //- []
        //- Thêm 1:
        //1
        //- Thêm 2:
        //2
        //1,2
        //- Thêm 2 (ta thấy rằng 2 giống phần từ trước đó --> Tức là ta chỉ thêm vào phần Thêm 2 trước đó):
        //2,2
        //1,2,2
        //1.2,
        //Time complexity:
        //- The outer loop takes O(N) time
        //- The inner loop takes 2,4,8...2^N (Số phần tử result tăng theo cấp số mũ 2)
        //- The inner loop, making a copy of L takes at most O(N) time.
        //* Total runtime = T(N) = N* (2+4+ 2^N) = N * 2^N
        //
        //- Xem phân tích độ phức tạp ở đây:
        //https://leetcode.com/problems/subsets-ii/discuss/1549662/Java-or-TC%3A-O(N*2N)-or-SC%3A-O(UniqueNums)-or-Space-Optimized-Iterative-and-Backtracking-solutions
        // * Space Optimized Iterative Solution
        // *
        // * S(n) = (0 × (n C 0) + 1 × (n C 1) + 2 × (n C 2) + … + n × (n C n))
        // * Note that (n C k) = (n C n-k). Therefore:
        // * S(n) = 0 × (n C n) + 1 × (n C n-1) + 2 × (n C n-2) + … + n × (n C 0)
        // * If we add these two together, we get
        // * 2S(n) = n × (n C 0) + n × (n C 1) + … + n × (n C n)
        // *       = n × (n C 0 + n C 1 + … + n C n)
        // * As per binomial theorem, (n C 0 + n C 1 + … + n C n) = 2^n, so
        // * 2*S(n) = n * 2^n => S(n) = n * 2^(n-1)
        // *
        // * Time Complexity: O(S(N) + n C 0) = O(N * 2^(N-1) + 1) = O(N * 2^N)
        // *
        // * Space Complexity: O(Unique Nums) (Excluding the result space)
        // *
        // * N = Length of input nums array
        //
        //# Reference
        //- Find Array Given Subset Sums
        System.out.printf("%s %s\n", "Interative method: ", subsetsWithDupInterative(arr));
    }
}
