package E1_daily;

import java.util.HashMap;

public class E118_CheckIfArrayPairsAreDivisibleByk {

    public static boolean canArrange(int[] arr, int k) {
        int n=arr.length;

        for(int i=0;i<n;i++){
            arr[i]=arr[i]%k;
        }
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for(int e: arr){
            if(mapCount.containsKey(k-e)){
                int curCount = mapCount.get(k-e);
                if(curCount==1){
                    mapCount.remove(k-e);
                }else{
                    mapCount.put(k-e, curCount-1);
                }
            }else if(mapCount.containsKey((-1)*k-e)){
                int curCount = mapCount.get((-1)*k-e);
                if(curCount==1){
                    mapCount.remove((-1)*k-e);
                }else{
                    mapCount.put((-1)*k-e, curCount-1);
                }
            }else if(mapCount.containsKey(-e)){
                int curCount = mapCount.get(-e);
                if(curCount==1){
                    mapCount.remove(-e);
                }else{
                    mapCount.put(-e, curCount-1);
                }
            } else{
                mapCount.put(e, mapCount.getOrDefault(e, 0)+1);
            }
        }
//        System.out.println(mapCount);
        return mapCount.isEmpty()||mapCount.size()==1&&mapCount.keySet().iterator().next()==0;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (an array of integers arr) of (even length n) and (an integer k).
        //- We want to divide the array into exactly (n / 2 pairs) such that (the sum of each pair) is (divisible by k).
        //* Return true If you can find a way to do that or false otherwise.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //arr.length == n
        //1 <= n <= 10^5
        //n is even.
        //-10^9 <= arr[i] <= 10^9
        //1 <= k <= 10^5
        //  + N khá lớn ==> Time: O(n)
        //  + arr[i] cũng khá lớn ==> Khó thể loop để check (divisible by k) được
        //  + arr[i] may be negative value ==> Cẩn thận case này
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
        //Output: true
        //Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
        //
        //- Check có thể tạo n pair ntn:
        //Ex:
        //+ (1+3)%4==0
        //+ (4+8)%4==0
        //  (1+4)%4==1 ==> Swap value cho nhau không còn đúng nữa
        //Ex:
        //1,3,7,5
        //(1+7) and (3+5)
        //or
        //(1+3) and (7+5)
        //+ (a+b)%k ==0
        //+ (a+c)%k ==0
        //  + (b-c)%k ==0
        //  ==> d+c = d+b-k
        //      + Giả sử (d+b)%k==0:
        //      ==> (d+b-k)%k==0
        //  ==> Thế nên a chọn (b or c) đều không ảnh hưởng đến kết quả của số khác.
        //
        //- Bài này có thể greedy được:
        //  + 1 số cứ chọn 1 số mà nó có thể chia hết cho k là được
        //
        //Example 1:
        //Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
        //sorted arr = [1,2,3,4,5,6,7,8,9,10]
        //- Làm sao để check xem có thể chia được không?
        //- Để làm giảm đi số arr[i]:
        //  + Áp dụng tính chất:
        //      + (a+b+k)%k==0 <=> (a+b)%k==0
        //- Ta sẽ %k tất cả các số trong arr:
        //arr = [1,2,3,4,5,10,6,7,8,9], k=5
        //<=>
        //arr = [1,2,3,4,0,0,1,2,3,4]
        //  + Lúc đó sum sẽ chỉ là 5 mà thôi
        //
        //- Map count:
        //  ==> Loop qua all key
        //+ Put new element nếu chưa tồn tại sum
        //  + Remove (count của pair) nếu tồn tại sum rồi
        //
        int[] arr = {-1,1,-2,2,-3,3,-4,4};
        int k = 3;
        //[-1,-2],[1,2],[-3,3],[-4,4]
        //- Negative value
        //
//        int[] arr = {1,2,3,4,5,10,6,7,8,9};
//        int k = 5;
        System.out.println(canArrange(arr, k));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the number of elements in the array
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //2183. Count Array Pairs Divisible by K
        //2344. Minimum Deletions to Make Array Divisible
        //
    }
}
