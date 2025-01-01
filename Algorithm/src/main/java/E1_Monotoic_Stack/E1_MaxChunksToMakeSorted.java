package E1_Monotoic_Stack;

public class E1_MaxChunksToMakeSorted {

    public static int maxChunksToSorted(int[] arr) {
        int n=arr.length;
        int max=arr[0];
        int end=0;
        int rs=0;

        for(int i=0;i<n;i++){
            if(max>arr[i]){
                end=i;
            }else{
                if(end==i-1){
                    rs++;
                }
                if(arr[i]==i){
                    rs++;
                    end=i;
                }
                max=arr[i];
            }
        }
//        rs++;
        return rs;
    }

    public static int maxChunksToSortedRefer(int[] arr) {
        int n=arr.length;
        int max=arr[0];
        int rs=0;

        if(max==0){
            rs++;
        }
        for(int i=1;i<n;i++){
            max=Math.max(arr[i], max);
            if(max==i){
                rs++;
            }
        }
//        rs++;
        return rs;
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- You are given an integer array arr of length n that represents (a permutation of the integers) in the range [0, n - 1].
        //We split arr into some (number of chunks) (i.e., partitions), and individually (sort each chunk).
        // After concatenating them, the result should equal (the sorted array).
        //* Return (the largest number of chunks) we can make to sort the array.
        //- Tức là chia làm sao cho việc sort all ==> sort chunks + concat cho cùng kết quả
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraints:
        //n == arr.length
        //1 <= n <= 10
        //0 <= arr[i] < n
        //All the elements of arr are unique.
        //==> N bé nên có thể viết recusive được
        //
        //- Brainstorm:
        //Input: arr = [4,3,2,1,0]
        //Output: 1
        //Explanation:
        //Splitting into two or more chunks will not return the required result.
        //For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
        //
        //Example 2:
        //Input: arr = [1,0,2,3,4]
        //Output: 4
        //- Việc chia chunk ntn
        // ==> Liên quan đến khoảng cách từ index(i) -> index của nó sau khi sort
        //Ex:
        //arr = [1,0,2,3,4]
        //+ index[0] = 1
        //sorted : 0,1,2,3,4
        //+ index[0] = 0 ==> Tức là move về 0
        //- Khoảng cách giữa 2 index sẽ tạo thành 1 chunk
        //  + Ta cần merge chunk để tìm ra số lượng chunk
        //
        //- Khi nào thì ta kết thúc 1 chunk
        //Ex:
        //3,2,1,5,4,0
        //+ 2<1 ==> 2<>1
        //+ 1<0 => 1<>0
        //+ ...
        //+ 1<0 => 1<>0 ==> chunk là đây.
        //==> Ta thấy nếu không swap
        //  <=> index đến index gần nhất là có thể chia được đều.
        //
        //Ex:
        //3,5,4,0,1,2
        //+ 3<>5 : ok
        //+ 3<>4 : ok
        //+ 3<>0 : swap(3,0)
        //0,5,4,3,1,2
        //+ 0<>1 : ok
        //+ 0<>2: ok
        //==> (3,5,4,0),1,2
        //sort each chunk:  (0,3,4,5),1,2: không đúng lắm
        //- Nếu xét thêm max thì sao:
        //Ex:
        //3,5,4,0,1,2
        //=> min=3, max=3
        //- (3,3)<>5 : ok
        //=> min=3, max=5
        //- (3,5)<>4 : 4 thuộc (3,5) ==> end++
        //=> min=3, max=5
        //- (3,5)<>0 : 0<=5 ==> end++
        //=> min=0, max=5
        //- (0,5)<>1 : 1<=5 or 1>=0 ==> end++
        //Ex:
        //4,3,2,1,0
        //
        //Ex:
        //0,2,1
        //rs: 2
        //Expectation: 1
        //- Mình cũng có thể lưu vị trí của từng (i) ==> ta có thể xác định vị trí đúng của nó khi sort all array bằng chính val của nó
        //+ ví dụ trên :
        //  + Nếu i không cần swap + arr[i]==i : Ta có thể chia được.
        // + 0(val)=0(pos) ==> rs++
        //
        //- The array is permuation of [0,n-1]
        //  + If we get max from nums[i] to nums[j]
        //      + max==i ==> We can split the array
        //      ==> We have (one more chunk) : rs++
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(n)
        //
        //#Reference:
        //768. Max Chunks To Make Sorted II
        //
//        int[] arr = {1,0,2,3,4};
        int[] arr = {4,3,2,1,0};
//        int[] arr = {0};
        System.out.println(maxChunksToSorted(arr));
        System.out.println(maxChunksToSortedRefer(arr));
    }
}
