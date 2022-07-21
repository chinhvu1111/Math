package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class E90_FindAllDuplicatesInAnArray {

    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> rs=new LinkedList<>();
//        HashSet<Integer> s=new HashSet<>();
        boolean[] s=new boolean[10001];

        for(int i=0;i<nums.length;i++){
            if(s[nums[i]]){
                rs.add(nums[i]);
            }else{
                s[nums[i]]=true;
            }
        }

        return rs;
    }

    public static List<Integer> findDuplicatesOptimize(int[] nums) {
        List<Integer> rs=new ArrayList<>();
//        HashSet<Integer> s=new HashSet<>();

        for(int i=0;i<nums.length;i++){
            int value=Math.abs(nums[i]);

            if(nums[value-1]<0){
                rs.add(value);
            }else {
                nums[value-1]*=-1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{4,3,2,7,8,2,3,1};
        System.out.println(findDuplicates(arr));
        System.out.println(findDuplicatesOptimize(arr));
        //1, Ý tưởng tối ưu ở đây vẫn sẽ là thay đổi array gốc
        //1.1, Ở đây ta vì ( 1 <= nums[i] <=n)
        //--> Ta sẽ tận dụng đặc điểm của nó ví dụ:
        //- Traverse đến nums[i] : Ta cần lưu tracing của nums[i], ta có những cách sau:
        //+ Lưu dựa trên 1 array --> Có thể được tạo ra với số phần tử = max =n
        //+ Dùng hashSet
        //+ Ta có thể dùng chính mảng đầu vào để lưu trace:
        //VD:
        //value =nums[i] (value>0)
        //--> Ta sẽ lưu vết lại bằng cách thay đổi giá trị của array ứng với
        //+ (index = value-1) (Vì array có n phần tử nên cần -1)
        //--> Ở đây ta chỉ quan tâm các số xuất hiện (nhiều hơn 1 lần)
        //--> Chỉ cần thay đổi 1 bậc : Ta chỉ cần thay đổi dấu của nums[value-1] ==> Để xác định rằng nó đã thay đổi.
        //Question: Làm thế nào để biết được 1 số đó thay đổi hay không?
        //+ Vì số đó luôn dương --> Ta chỉ cần kiểm tra dấy của nums[value-1] để xác định nó có tồn tại hay chưa!
        //--> Nếu chưa tồn tại --> Ta sẽ thay đổi dấu <=> *-1.
        //2, LinkedList --> Có thể sẽ chậm hơn ArrayList nếu kích thước list nhỏ
        //Vì arrayList --> Gán phần tử dựa trên Array ---> Nhanh hơn so với LinkedList (Nối điểm sẽ chạm hơn)
    }
}
