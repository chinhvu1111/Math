package interviews;

import java.util.*;

public class E65_CombinationSum {

//    public static char[]frequency;
//    public static Set<String> setMap;
    public static List<List<Integer>> rs;
//    public static Map<Integer, List<List<Integer>>> mapSum;

    public static void subCombinatioSum(
            int[] candidates, List<Integer> list, int index, int currentSum){

        if(currentSum<=0){
            if(currentSum==0){
                rs.add(new ArrayList<Integer>(list));
            }
            return;
        }
//        List<List<Integer>> lists=mapSum.get(currentSum);

//        if(lists!=null){
//            for(List<Integer> integers: lists){
//                List<Integer> integerList=new LinkedList<>();
//                integerList.addAll(list);
//                integerList.addAll(integers);
//                rs.add(integerList);
//            }
//            return;
//        }

        for(int i=index;i<candidates.length;i++){
//            if(candidates[i]>=150){
//                continue;
//            }
            if(candidates[i]>currentSum){
                break;
            }
//            frequency[candidates[i]]++;
            list.add(candidates[i]);
            subCombinatioSum(candidates, list, i,currentSum-candidates[i]);
//            mapSum.put(currentSum, new LinkedList<>(rs));
//            frequency[candidates[i]]--;
            list.remove(list.size()-1);
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
//        frequency=new char[201];
//        setMap=new HashSet<>();
        rs=new LinkedList<>();
        Arrays.sort(candidates);
//        mapSum=new HashMap<>();

        subCombinatioSum(candidates, new LinkedList<>(), 0, target);
        return rs;
    }

    public static List<List<Integer>> list=new ArrayList<List<Integer>>();

    public static void go(int pos,int target,List ll,int[] candidates){
        int i;
        for(i=pos;i<candidates.length;i++){
            int leftover=target-candidates[i];
            if(leftover>0){
                List<Integer> newlist=new ArrayList<Integer>(ll);
                newlist.add(candidates[i]);
                go(i,leftover,newlist,candidates);
            }else if(leftover==0){
                List<Integer> newlist=new ArrayList<Integer>(ll);
                newlist.add(candidates[i]);
                list.add(newlist);
            }
        }
    }

    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        go(0,target,new ArrayList<Integer>(),candidates);
        return list;
    }

    public static void main(String[] args) {
        //
        //** Đề bài:
        //- Tìm các tập có sum <=target
        //- Không giới hạn số lần của mỗi phần tử
        //- Các chuỗi khác nhau
        //Input: candidates = [2,3,6,7], target = 7
        //Output: [[2,2,3],[7]]
        //
        //Bài này tư duy như say:
        //1, Bài này tìm tất cả các chuỗi số khác nhau
        //<-> Chỉ khác nhau về số lần xuất hiện của mỗi số trong dãy.
        //1.1, Với cách làm kiểu này ta có thể áp dụng tư duy về:
        //+ Hashing : Lưu số lần xuất hiện của mỗi số trong 1 array[char] --> Cast ra String + hash
        //==> Check điểu kiện xuất hiện
        //--> Làm như thế này ==> Về cơ bản sẽ bị time limit --> Mỗi lần return lại result sẽ phải for(n)
        //1.2, Vói bài này ta có 1 tư duy mới về cách nhóm các số với nhau như sau:
        //VD: {100,200,4,12}
        //Số lượng chuỗi kết hợp sẽ được chia làm 2 loại chính:
        //+ Chuỗi có ký tự arr[i]
        //--> Chỗ này có thể chia ra thành m chuỗi (1 --> m) là số lần xuất hiện ký tự arr[i] trong chuỗi đó
        //+ Chuỗi không có ký tự arr[i]
        //--> Vậy thì đơn giản ta sẽ bỏ qua index=i --> next (i+1).
        //Solution: Ta sẽ pass index vào
        //+ for(index<=i<n) { method (i); }
//        int arr[]=new int[]{2,3,5};
        //Case 1: Data trả ra quá lớn
        //200/4 --> Độ sâu của tree quá lớn + mỗi lần trả lại result (loop(O(n))
        //==> Slow.
//        int arr[]=new int[]{100, 200, 4, 12};
        int arr[]=new int[]{10,1,2,7,6,1,5};
//        combinationSum(arr, 7);
//        combinationSum(arr, 400);
//        combinationSum1(arr, 400);
        combinationSum1(arr, 8);
        System.out.println("");
    }
}
