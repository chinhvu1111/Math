package E1_hashmap;

import java.util.HashMap;

public class E12_CountingElements {

    public static int countElements(int[] arr) {
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for(int e: arr){
            mapCount.put(e, mapCount.getOrDefault(e, 0)+1);
        }
        int rs=0;

        for(int e:arr){
            if(mapCount.containsKey(e+1)){
                rs++;
            }
        }
        return rs;
    }

    public int countElementsArr(int[] arr) {
        int[] mapCount=new int[1002];

        for(int e: arr){
            mapCount[e]++;
        }
        int rs=0;

        for(int e:arr){
            if(mapCount[e+1]!=0){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr.
        // If there are duplicates in arr, count them separately.
        //* count số chữ số x mà (x+1) có trong array ==> nếu giống nhau thì tính riêng rẽ.
        //- Không phải dạng hiệu trừ count đi ==> Mà chỉ đơn giản là có exists hay không là được.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= arr.length <= 1000
        //0 <= arr[i] <= 1000
        //
        //- Brainstorm
        //-
        //#Reference:
        //2536. Increment Submatrices by One
        //747. Largest Number At Least Twice of Others
        //661. Image Smoother
    }
}
