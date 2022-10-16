package interviews;

import java.util.Arrays;

public class E140_SortColors {

    public static void sortColors(int[] nums) {
        int[] count = new int[10];
        int n = nums.length;

        //2,0,2,3,1,1,0
        //0(2), 1(2), 2(2), 3(1)
        for (int i = 0; i < n; i++) {
            count[nums[i]]++;
        }
        int index = 0;

        for (int i = 0; i < 10; i++) {
            int currentCount = 0;

            while (currentCount < count[i]) {
                nums[index] = i;
                index++;
                currentCount++;
            }
        }
//        System.out.println();
    }

    public static void sortColorsRefactor(int[] nums) {
        int n = nums.length;
        int max=0;

        for(int i=0;i<n;i++){
            max=Math.max(max, nums[i]);
        }
        //2, 0, 2, 1, 1, 0
        //0(2), 1(2), 2(2)
        //
        int[] count = new int[max+1];
        //2,
        //5,0,2,1,1,0
        //0(2),1(2),2(1), 5(1)
        //2.1, CT: c[i]=c[i]+c[i-1] (Không nên -1) vì có thể có ( c[i]==0 ) không tồn tại
        //** Các giá trị (count[i]==0)
        //Result : 2 (value=0),4 (value=1), 5 (value=2), 6 (value=6) : count[value=6] = index(6-1) đứng vị trí 6-1
        //* Thực ra:
        //- Result : 2 (value=0), 4 (value=1), 5 (value=2), 5 (value=3), 5(value=4), 5 (value=5),
        //... 6 (value=6) : count[value=6] = index(6-1) đứng vị trí 6-1
        //2.2, VD
        //numbers : 0,0,1,1,2,6
        //- (index của count) : value =0 : index = [0, c[0]-1]
        //- (index của count) : value =1 : index = [c[0], c[0]+ c[1]-1]
        //
        //2.3, nums[i] chính là index của count[index]
        //count[index=nums[i]] sẽ thể hiện index max nhất của nums[i]
        //- Tạo ra 1 result mới --> rs[n]
        //- Sau đó rs[count[nums[i]] : index max nhất hiện tại của nums[i] khi sort xong ] = nums[i]
        //Vì couns[ nums[i] ] là index max nhất --> VÌ CÓ KHẢ NĂNG GẶP LẠI nums[j]==nums[i]
        //==> Lúc đó ta sẽ fill index-- (Giảm dần) ==> Nên (count[nums[i]]--)
        for (int i = 0; i < n; i++) {
            count[nums[i]]++;
        }
        for(int i=1;i<=max;i++){
            count[i]=count[i]+count[i-1]-1;
        }
        int rs[]=new int[n];

        for(int i=0;i<n;i++){
            rs[count[nums[i]]]=nums[i];
            count[nums[i]]--;
        }
//        System.out.println();
    }

    public static void sortColorsOnePass(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int i = low;

        while (i <= high) {
            if (nums[i] == 0) {
                swap(nums, low, i);
                i++;
                low++;
            } else if (nums[i] == 2) {
                swap(nums, i, high);
                //chỗ này chưa chắc đã có thể bỏ qua được (i)
                //VD: 0,0,2(i),1,0,1(end)
                // swap --> 0,0,1(i),1,0(i'),2(end)
                //==>
//                i++;
                high--;
            } else {
                i++;
            }
        }
    }

    public static void swap(int nums[], int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(arr);
        Arrays.stream(arr).forEach(System.out::print);
        System.out.println();
        //
        //** Đề bài:
        //- Sắp xếp các số trong dãy (nums[i] is 0,1,2)
        //
        //** Bài này tư duy như sau
        //Cách 1:
        //- Dùng counting sort
        //1,
        //1,1, Đầu tiên ta dùng count[] --> Để tính số lượng các phần tử của từng số
        //VD:
        //2,0,2,1,1,0
        //0(2),1(2),2(2)
        //1.2, Sau đó gán lại các giá trị vào Array cũ --> Dựa trên số lượng các phần tử mỗi số
        //
        //Cách 2:
        //1, Two pointers --> Chỉ có thể sử dụng đặc biệt với bài này
        //1.1, Khi mà ta chỉ có 3 giá trị duy nhất (0,1,2)
        //1.2, Nếu có nhiều hơn 3 giá trị thì sao thì sẽ làm ntn?
        //Tư duy như sau
        //=====================================
        //1.3, Thứ tự (tăng/ giảm) start, i, end
        //- Key tư duy ở đây là gì?
        //
        //2 (start)(i),0,2,1,1,0 (end)
        //nums[start] <= nums[i], nums[i]>= nums[end]
        //-->
        //0,2,2,1,1,2 (end)
        //Vì còn có case:
        //2 (start)(i),0,2,1,1,1 (end)
        //
        //swap xong:
        //1 (start)(i),0,2,1,1,2 (end)
        //--> end++ --> nums[end] sẽ không phải lớn nhất, nums[start]=1 sẽ không phải nhỏ nhất
        //1.4, Xác định case nào thì chắc chắn sẽ (start++, end++)
        //2 (start)(i),0,2,1,1,1 (end)
        //--> swap (i, end)
        //1 (start)(i),0,2,1,1,2 (end)
        //+ Case nums[i]=0 --> swap cho (start)
        //+ Case nums[i]=2 --> swap cho (end)
        //+ Case nums[i]=1
        //
        //0<=1<=2
        //1<=1<=2
        //2>=1<=0,1/2
        //1.5, Sẽ ưu tiên swap các phần tử bên trái --> Thành 1 tập tăng dần bên trái
        //VD
        //1 (start),0(i),2,1,1,2 (end)
        //--> 0(start),1(i),2,1,1,2 (end)
        //VD
        //2,1,2,2,0,1
        //Expected:
        //0,1,1,2,2,2
        //Steps:
        //input:
        //2(start),1(i),2,2,0,1(end)
        //--> 1(start),2(i),2,2,0,1(end)
        //+ Ở đây 1 vẫn chưa MIN
        //===> Ở đây (start) cần HOLD --> Vì chưa thể bỏ qua (start=0, nums[start]==1)
        //
        //*Tư duy sai 1:
        //
        //4(start),2(i),6,3,9
        //--> 2(start),4(i),6,3,9
        //2(start),4,6(i),3,9
        //--> 2(start),4,6(i),3,9
        //2(start),4,6,3(i),9
        //
        //1.6, Tư duy sai:
        //*Tư duy sai 2:
        //
        //4(start), 2(i), 6, 3, 9, 0, 1
        //--> 2(start), 4(i), 6, 3, 9, 0, 1
        //2, 4(start), 6(i), 3, 9, 0, 1
        //--> 2, 4(start), 6(i), 3, 9, 0, 1
        //** ==> Chỗ này mình không dịch (start) vì (4<6 --> 3,2,1 thì sao) ==> Cần swap (3/2/2 với 4)
        //2,4(start),6,3(i),9, 0, 1
        //--> 2,3(start),6,4(i),9, 0, 1
        //** ==> Chỗ này sẽ bị sai với giá trị (0,1) phía sau --> Dịch (start) sẽ gây sai.
        //
        //*** Kinh nghiệm:
        //==> Nếu tư duy theo dạng này --> Thì phải gán lại (i)
        //==> Ntn thì ta sẽ chỉ có thể sắp xếp 1 cách bình thường ---> Nếu các số là số nhiều
        //
        //2, Solution:
        //2.1, Ta sẽ chia ra thành 3 pointers:
        //- 1 pointer số 0 (start)
        //- 1 pointer số 1 (i)
        //- 1 pointer số 2 (end)
        //==> Cứ swap lần lượt sao cho (0, 2) phân bổ đều ra 2 đầu, điều kiện như sau:
        //+ nums[i]=2 --> swap(i, high--)
        //+ nums[i]=1 --> swap(i, low+=)
        //+ nums[i]=0 --> non(i++)
        //2.2, tại sao swap(i, low+=) : Không tăng i
        //VD: 0,1(start),2(i),1,0,0(end)
        // swap --> 0,1(start),0(i),1,0,2(end)
        //==> Vì nums[i]==0 --> Vẫn cần swap cho (start) (nums[start]==1)
        //** Số nhỏ hơn 2 (<2) thì có (2 số 0,1), còn số <0 (thì không có số nào)
        //
        //Cách 1 (refactor)
        //3,
        //VD
        //5,0,2,1,1,0
        //0(2),1(2),2(1), 5(1)
        //3.1, CT: c[i]=c[i]+c[i-1] (Không nên -1) vì có thể có ( c[i]==0 ) không tồn tại
        //** Các giá trị (count[i]==0)
        //Result : 2 (value=0),4 (value=1), 5 (value=2), 6 (value=6) : count[value=6] = index(6-1) đứng vị trí 6-1
        //* Thực ra:
        //- Result : 2 (value=0), 4 (value=1), 5 (value=2), 5 (value=3), 5(value=4), 5 (value=5),
        //... 6 (value=6) : count[value=6] = index(6-1) đứng vị trí 6-1
        //3.2, VD
        //numbers : 0,0,1,1,2,6
        //- (index của count) : value =0 : index = [0, c[0]-1]
        //- (index của count) : value =1 : index = [c[0], c[0]+ c[1]-1]
        //
        //3.3, nums[i] chính là index của count[index]
        //count[index=nums[i]] sẽ thể hiện index max nhất của nums[i]
        //- Tạo ra 1 result mới --> rs[n]
        //- Sau đó rs[count[nums[i]] : index max nhất hiện tại của nums[i] khi sort xong ] = nums[i]
        //Vì couns[ nums[i] ] là index max nhất --> VÌ CÓ KHẢ NĂNG GẶP LẠI nums[j]==nums[i]
        //==> Lúc đó ta sẽ fill index-- (Giảm dần) ==> Nên (count[nums[i]]--)
        //
        //#reference:
        //- Sort List
        //- Wiggle Sort
        //- Wiggle Sort II
        //** 2 pointers
        sortColorsOnePass(arr);
        Arrays.stream(arr).forEach(System.out::print);
        //** counting sort
        sortColorsRefactor(arr);
    }
}
