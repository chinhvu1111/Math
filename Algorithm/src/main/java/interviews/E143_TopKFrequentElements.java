package interviews;

import java.util.*;

public class E143_TopKFrequentElements {

    public static Random random=new Random();

    public static int[] topKFrequent(int[] nums, int k) {
        int max=0;
        int n=nums.length;

        for(int i=0;i<n;i++){
            max=Math.max(max, nums[i]);
        }
        HashMap<Integer, Integer> countHashMap=new HashMap<>();

        for(int i=0;i<n;i++){
            if(!countHashMap.containsKey(nums[i])){
                countHashMap.put(nums[i], 1);
            }else{
                countHashMap.put(nums[i], countHashMap.get(nums[i])+1);
            }
            countHashMap.put(nums[i], countHashMap.getOrDefault(nums[i], 1));
//            System.out.printf("%s ", count[nums[i]]);
        }
//        System.out.println();
        int indexMid=findTopKFrequent(countHashMap, nums, 0, n-1, k);
//        System.out.println(indexMid);
        int[] result=new int[k];
        int index=0;

        for(int i=0;i<=indexMid;i++){
            if(countHashMap.containsKey(nums[i])&&countHashMap.get(nums[i])!=0){
                result[index++]=nums[i];
                countHashMap.remove(nums[i]);
            }
        }

        return result;
    }

    public static int findTopKFrequent(HashMap<Integer,Integer> countHashMap, int[] nums, int left, int right, int k){

        //Các số < currentIndex (> count[pivot])
        int swapIndex=left + random.nextInt(right-left+1);
        swap(nums, right, swapIndex);

        int pivot=right;
        int currentIndex=left;

        //arr   : 6(left),2,3,1,8,2,0(right)
        //VD:
        //input : 1,1,1,2,2,3
        //count (index) : 1(2), 2(1), 3(1)
        //count > currentIndex
        //- Steps
        //VD:
        // 1(current)(i),1,3,1,2,[2] (pivot)(right)
        // 1(i),1,3(current),1,2,[2] (pivot)(right)
        //*** 3 (current có count < count[2] ) ==> traverse tiếp count[value=1]
        //===> current =2 (value=3) ---> Thay thế nó bằng thằng có count[i==1]> count[pivot=2]
        for(int i=left;i<right;i++){
            if(countHashMap.get(nums[i])>=countHashMap.get(nums[pivot])){
//                System.out.printf("%s %s %s %s, ", i, nums[i], currentIndex, nums[currentIndex]);
//                System.out.println();
                swap(nums, currentIndex++, i);
            }
        }
//        System.out.printf("%s %s,", currentIndex, pivot);
        swap(nums, currentIndex, pivot);

        int numberElemenCountLessThan=0;
        HashSet visited =new HashSet();

        //### Đoạn này nên tính đến currentIndex
        for(int i=left;i<=currentIndex;i++){
            if(!visited.contains(nums[i])){
                numberElemenCountLessThan++;
                visited.add(nums[i]);
            }
        }

        if(numberElemenCountLessThan==k){
//            System.out.println(currentIndex);
            return currentIndex;
        }
        int index=0;
//        System.out.printf("%s %s, ",currentIndex, numberElemenCountLessThan);

        //2,1,5(currentIndex==2),6(k==3),9
        if(numberElemenCountLessThan<k){
//            System.out.println("++++++");
            //1,3(currentIndex),5,6
            index=findTopKFrequent(countHashMap, nums,currentIndex+1, right, k-numberElemenCountLessThan);
        }else{
//            System.out.println("------");
            index=findTopKFrequent(countHashMap, nums, left, currentIndex-1, k);
        }
        return index;
    }

    public static void swap(int[] nums, int i, int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{1,1,3,1,2,2};
//        int k=2;
//        int[] arr=new int[]{1,1,1,2,2,3};
//        int k=2;
        int[] arr=new int[]{1,1,3};
        int k=2;
//        int[] arr=new int[]{1,1};
//        int k=1;
//        int[] arr=new int[]{1,1};
//        int k=0;
//        int arr[]=new int[]{5,3,1,1,1,3,73,1};
//        int k=2;

        int[] result=topKFrequent(arr, k);
        Arrays.stream(result).forEach(System.out::println);

        //
        //** Đề bài:
        //- Lấy ra danh sách k số có số lượng count lớn nhất
        //
        //Cách 1: Dùng quick select
        //
        //** Bài này tư duy như sau:
        //0, Bài này cần chú ý những điều như sau:
        //0.1, CurrentIndex ==> Nên xét (currentIndex==k)
        //===> Mục đích để dựng điều kiện dễ hơn
        //====== Pattern
        //if(current==k)
        //Condition(current<k)
        //else {}
        //====== Pattern
        //0.2,Các index xuất phát từ bên trong recursion --> luôn start=0
        //
        //1, Tư tưởng
        //VD:
        //input : 1,1,1,2,2,3
        //count (index) : 1(2), 2(1), 3(1)
        //- k==2 : lấy ra [1,2]
        //- Steps
        //VD:
        // 1(current)(i),1,3,1,2,[2] (pivot)(right)
        // 1(i),1,3(current),1,2,[2] (pivot)(right)
        //1.1, *** Key của tư duy là thay thế nums[i] với phần tử nums[currentIndex] mà trước đó count[currentIndex] <count[right]
        //====> Vì (i tăng liên tục), (currentIndex) chỉ tăng khi xảy ra swap <=> ( count[nums[i]]> count[nums[pivot]] )
        //VD:
        // 3 (current có count < count[2] ) ==> traverse tiếp count[value=1]
        //===> current =2 (value=3)
        // ---> Thay thế nó bằng thằng có count[i==1]> count[pivot=2]
        //
        //1.2,
        //Điều kiện so sánh với số lượng (k) tứ là các phần tử unique có count > count[right]
        //- Ta xác định được currentIndex
        //- Ta sẽ xét các phần tử (i<currentIndex) <=> có count < count[currentIndex (pivot sau khi swap]
        //** Đếm số lượng các phần tử distict = hasmap + so sánh với (k)
        //1.3,
        //- if(number<k) : (currentIndex+1, k-number)
        //- if(number>k) : (currentIndex-1, k)
        //
        //Cách 2:
        //
    }
}
