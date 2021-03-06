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
        //B??i n??y t?? duy nh?? say:
        //1, B??i n??y t??m t???t c??? c??c chu???i s??? kh??c nhau
        //<-> Ch??? kh??c nhau v??? s??? l???n xu???t hi???n c???a m???i s??? trong d??y.
        //1.1, V???i c??ch l??m ki???u n??y ta c?? th??? ??p d???ng t?? duy v???:
        //+ Hashing : L??u s??? l???n xu???t hi???n c???a m???i s??? trong 1 array[char] --> Cast ra String + hash
        //==> Check ??i???u ki???n xu???t hi???n
        //--> L??m nh?? th??? n??y ==> V??? c?? b???n s??? b??? time limit --> M???i l???n return l???i result s??? ph???i for(n)
        //1.2, V??i b??i n??y ta c?? 1 t?? duy m???i v??? c??ch nh??m c??c s??? v???i nhau nh?? sau:
        //VD: {100,200,4,12}
        //S??? l?????ng chu???i k???t h???p s??? ???????c chia l??m 2 lo???i ch??nh:
        //+ Chu???i c?? k?? t??? arr[i]
        //--> Ch??? n??y c?? th??? chia ra th??nh m chu???i (1 --> m) l?? s??? l???n xu???t hi???n k?? t??? arr[i] trong chu???i ????
        //+ Chu???i kh??ng c?? k?? t??? arr[i]
        //--> V???y th?? ????n gi???n ta s??? b??? qua index=i --> next (i+1).
        //Solution: Ta s??? pass index v??o
        //+ for(index<=i<n) { method (i); }
//        int arr[]=new int[]{2,3,5};
        //Case 1: Data tr??? ra qu?? l???n
        //200/4 --> ????? s??u c???a tree qu?? l???n + m???i l???n tr??? l???i result (loop(O(n))
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
