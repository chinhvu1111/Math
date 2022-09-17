package interviews;

import java.util.ArrayList;
import java.util.List;

public class E162_Subsets_bit {

    public static List<List<Integer>> subsets(int[] nums) {
        int n=nums.length;
        List<List<Integer>> rs=new ArrayList<>();

        for(int i=0;i<1<<n;i++){
            List<Integer> currentRs=new ArrayList<>();

            for(int j=0;j<n;j++){
                if(((i >> j)&1)==1){
                    currentRs.add(nums[j]);
                }
            }
            rs.add(currentRs);
        }
        return rs;
    }

    public static List<List<Integer>> subsetsRecursion(int[] nums) {
        int n=nums.length;
        List<List<Integer>> rs=new ArrayList<>();
        subRecursion(0, new ArrayList<>(), nums, n, rs);
        return rs;
    }

    public static void subRecursion(int index, List<Integer> list, int[]nums, int n, List<List<Integer>> rs){
        if(index==n){
            List<Integer> currentList = new ArrayList<>(list);
            rs.add(currentList);
            return;
        }
        list.add(nums[index]);
        subRecursion(index+1, list, nums, n, rs);
        list.remove(list.size()-1);
        subRecursion(index+1, list, nums, n, rs);
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,2,3};
        System.out.println(subsets(arr));
        //** Đề bài:
        //- Cho [1,2,3] liệt kê tât cả các subset của all elements trong array.
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Bài này ta có thể dùng phép dịch bits để liệt kê tất cả lần xuất hiện của các elements
        //VD:
        //1,2,3
        //+ 010 <=> {2}
        //+ 110 <=> {1,2}
        //1.1, Cover all case : số (1 <=x<= 111...111 (n chữ số 1)
        //1.2, Sau khi liệt kê tất cả các cases : ==> Ta chỉ cần add all cases là xong
        //- phần tử xuất hiện <=> (i >> j) & 1
        //
        //Cách 2:
        //1, Dùng phương pháp recursion để cover all cases
        //1.1, Chú ý : Dùng biến static
        //--> Liên code sẽ dùng chung object ==> Nếu dùng nhiều lần thì rs sẽ bị cache lại
        //--> Test case sau mặc dù không ra kết quả ===> Nhưng vẫn trả lại kết quả SAI (Do lưu lại từ test case trước)
        //==> Nên truyền vào đầu method(Trade off có thể bị stackOverFlow)
        System.out.println(subsetsRecursion(arr));
    }
}
