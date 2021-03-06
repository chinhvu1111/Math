package interviews;

import java.util.*;
import java.util.stream.Collectors;

public class E74_FindKClosestElements {

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int insertIndex=searchElementInsertLinear(arr, x);
        List<Integer> rs=new ArrayList<>();

        if(insertIndex!=-1){
            rs.add(arr[insertIndex]);
        }
        int left=insertIndex-1;
        int right=insertIndex+1;
        int n=arr.length;
//        int currentRs=Integer.MAX_VALUE;
//        int currentValue=Integer.MAX_VALUE;
        int count=1;

        while((left>=0||right<n)&&count<k){
            int valueLeft=left!=-1&&left<n?Math.abs(arr[left]-x):Integer.MAX_VALUE;
            int valueRight=right!=-1&&right<n?Math.abs(arr[right]-x):Integer.MAX_VALUE;

            if(valueLeft!=Integer.MAX_VALUE&&valueLeft==valueRight){
                if(arr[left]<arr[right]){
                    rs.add(arr[left]);
                    left--;
                }else{
                    rs.add(arr[right]);
                    right++;
                }
            }else if(valueLeft>valueRight){
                rs.add(arr[right]);
                right++;
            }else{
                rs.add(arr[left]);
                left--;
            }
            count++;
        }

        Collections.sort(rs);
        return rs;
    }

    public static int searchElementInsertLinear(int arr[], int target){
        int rsIndex=-1;
        int rsValue=Integer.MAX_VALUE;
        int currentValue=Integer.MAX_VALUE;

        for(int i=0;i<arr.length;i++){
            int value=Math.abs(arr[i]-target);

            if(value<rsValue
                    ||(rsValue==Math.abs(arr[i]-target))&&arr[i]<currentValue){
                rsValue=value;
                rsIndex=i;
                currentValue=arr[i];
            }else if(currentValue!=arr[i]){
                break;
            }
        }
        return rsIndex;
    }

    public static List<Integer> findClosestElementsOptimize(int[] arr, int k, int x) {
        List<Integer> rs=new LinkedList<>();
        int low=0;
        int high=arr.length-1;

        while (high-low>=k){
            int distLow=Math.abs(arr[low]-x);
            int distHigh=Math.abs(arr[high]-x);

            if(distHigh>=distLow){
                high--;
            }else {
                low++;
            }
        }
        for(int i=low;i<=high;i++){
            rs.add(arr[i]);
        }
        return rs;
    }

//    public static int searchElementInsert(int arr[], int low, int high, int target){
//        int mid=low + (high-low)/2;
//        int index=-1;
//
//        if(low>=high-1){
//            if(low!=high){
//                if(Math.abs(low-target)<Math.abs(high-target)){
//                    return low;
//                }else{
//                    return high;
//                }
//            }
//            return low;
//        }
//
//        if(arr[mid]>target){
//            index=searchElementInsert(arr, low, mid-1, target);
//        }else{
//            index=searchElementInsert(arr, mid, high, target);
//        }
//        return index;
//    }

    public static List<Integer> findClosestElementsOptimize1(int[] A, int k, int x) {
        int left = 0, right = A.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - A[mid] > A[mid + k] - x)
                left = mid + 1;
            else
                right = mid;
        }
        return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,2,3,4,5};
//        int arr[]=new int[]{1,2};
        int arr[]=new int[]{1,2,5,5,6,6,7,7,8,9};
        //Case 1:
        //Search theo binary (v???i vi???c t??m index searchInsert) s??? b??? l???i case n??y v???i vi???c ch???n (1,10)
        //+ Solution : M???t l?? ph???i ?????i ph??p check condtition <> Chuy???n qua linear search d??? h??n.
//        int arr[]=new int[]{1,1,1,10,10,10};
//        int k=4;
//        int x=3;
//        int k=4;
//        int x=-1;
//        int k=1;
//        int x=9;
        int k=7;
        int x=7;
        System.out.println(findClosestElements(arr, k, x));
        //B??i n??y t?? duy nh?? sau:
        //C??ch 1:
        //1, ??? ????y t??m ??i???m g???n nh???t c???a (i) th???a m??n c??c ??i???u ki???n:
        //+ |a - x| < |b - x|
        //+ |a - x| == |b - x| and a < b
        //1.1, ??? ????y ta ???? SAI khi ??p d???ng binary search vi t??m index b??? sai ??? case:
        //int arr[]=new int[]{1,1,1,10,10,10};
        //--> ??? ????y n???u ta search(9) : return 2
        //==> M???c d?? rule ta ??ang ??p d???ng l?? |a-x| v?? |b-x|
        //+ Search theo binary (v???i vi???c t??m index searchInsert) s??? b??? l???i case n??y v???i vi???c ch???n (1,10)
        //+ Solution : M???t l?? ph???i ?????i ph??p check condtition <> Chuy???n qua linear search d??? h??n.
        //
        //1.2, Sau khi search xong th?? ta s??? ??i sang 2 ph??a (left/ right) c???a index
        //--> L???y l???n l?????t c??c ph???n t??? add v??a rs.
        //C??ch 2:
        //1, Thay v?? ta t??m index sau ???? ??i ra 2 ph??a left, right
        // + sort sau khi add v??o list.
        //2, Ta s??? lo???i b??? t???t c??? c??c ph???n t??? t??? h??n ??? 2 ph??a (low v?? high) cho ?????n khi
        //s??? l?????ng ph???n t??? ??? gi???a (low - high) l?? k
        //** KINH NGHI???M T?? DUY: Ph???n ngh???ch t??m k ph???n t??? t??? pivot ??i ra 2 b??n left v?? right
        //Thay v?? c??? t??m index + sau ???? l???y k ph???n t??? ??? left, right
        //----> Lo???i b??? c??c ph???n t??? BAD ==> Nh???n ???????c k ph???n t??? t???t nh???t.
        //3,
        //if(distHigh>=distLow){
        //                high--;
        //--> V?? ta ??u ti??n ph???n t??? less than ==> X??a high (X??a ??i ph???n t??? l???n h??n).
        System.out.println(findClosestElementsOptimize(arr, k, x));
        //C??ch 3:
        //1, B??i n??y ta ??ang t??m k ph???n t??? closest:
        //1.1, T?? duy ch??nh ??? ????y l?? chuy???n th??nh d???ng (slice window):
        //+ V?? d??y s??? ??? ????y c?? t??nh ch???t (LI??N TI???P NHAU + T??NG D???N)
        //VD:
        //A[mid] --> A[mid + k]
        //** Coi x l?? s??? c???n t??m sao cho c??c elements g???n X nh???t:
        //case 1: x - A[mid] < A[mid + k] - x, need to move window go left
        //-------x----A[mid]-----------------A[mid + k]----------
        //
        //case 2: x - A[mid] < A[mid + k] - x, need to move window go left again
        //-------A[mid]----x-----------------A[mid + k]----------
        //
        //case 3: x - A[mid] > A[mid + k] - x, need to move window go right
        //-------A[mid]------------------x---A[mid + k]----------
        //
        //case 4: x - A[mid] > A[mid + k] - x, need to move window go right
        //-------A[mid]---------------------A[mid + k]----x------
        //Question:
        //+ V???i c??c t?? duy n??y --> L??m th??? n??o x??c ?????nh ???????c slice window (k elements) ngay t??? ?????u.
        //==> Kh??ng ph???i slice window ngay t??? ?????u...
        //Answer:
        //+ Ta s??? ch???n ra n-k ph???n t??? li??n ti???p nhau --> ????? t??nh mid =left + (righ-left)/2 + k (KH??NG B??? QU?? ARRAY)
        //** KINH NGHI???M :
        // T?? duy ??? ????y l?? ki???m tra m???i quan h??? gi???a (slice window) v?? (x)
        //==> T??m v??? tr?? (mid) sao cho (mid) th???a m??n ??i???u ki???n (x n???m gi???a arr[mid] v?? arr[mid +k]
        //T???ng qu??t:
        //+ x - A[mid] < A[mid + k] - x :
        //
        //+ x - A[mid] > A[mid + k] - x :--> D???ch slice window sang right.
        //
        System.out.println(findClosestElementsOptimize1(arr, k, x));
        //** X??? l?? n???t v???n ????? t?? duy ph???n n??y????

    }
}
