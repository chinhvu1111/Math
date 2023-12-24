package E1_binary_search_topic;

public class E16_FindTheIndexOfTheLargeInteger {

    static interface ArrayReader {
      // Compares the sum of arr[l..r] with the sum of arr[x..y] 
              // return 1 if sum(arr[l..r]) > sum(arr[x..y])
              // return 0 if sum(arr[l..r]) == sum(arr[x..y])
              // return -1 if sum(arr[l..r]) < sum(arr[x..y])
              public int compareSub(int l, int r, int x, int y);
 
              // Returns the length of the array
              public int length();
  }

    public static int getIndex(ArrayReader reader) {
        int n=reader.length();
        int low=0, high=n-1;

        while(low<high){
            int mid=low+(high-low)/2;
            // System.out.printf("Low: %s, high: %s, mid: %s\n", low, high, mid);
            if((high-low)%2==1){
                int val=reader.compareSub(low, mid, mid+1, high);
                if(val==1){
                    high=mid;
                }else{
                    low=mid+1;
                }
            }else{
                //low=0, high=1
                //mid=0
                int val=reader.compareSub(low, mid-1, mid+1, high);
                if(val==0){
                    return mid;
                }else if(val==-1){
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
        }
        return low;
    }

    public static int getIndexOptimization(ArrayReader reader) {
        int n=reader.length();
        int low=0, high=n-1;

        while(low<high){
            int mid=high-(high-low)/2;
            // System.out.printf("Low: %s, high: %s, mid: %s\n", low, high, mid);
            int val=reader.compareSub(low, mid-1, mid, high);
//            if(){
//
//            }
        }
        return low;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given arr:
        // + All elements are same except for one element which are greater than the remaining elements.
        //- Given the function:
        // + int compareSub(int l, int r, int x, int y): where 0 <= l, r, x, y < ArrayReader.length(), l <= r and x <= y.
        // The function compares the sum of sub-array arr[l..r] with the sum of the sub-array arr[x..y] and returns:
        //  + 1 if arr[l]+arr[l+1]+...+arr[r] > arr[x]+arr[x+1]+...+arr[y].
        //  + 0 if arr[l]+arr[l+1]+...+arr[r] == arr[x]+arr[x+1]+...+arr[y].
        //  + -1 if arr[l]+arr[l+1]+...+arr[r] < arr[x]+arr[x+1]+...+arr[y].
        // + int length():
        //- You are allowed to call compareSub() 20 times at most. You can assume both functions work in O(1) time.
        //* Return the index of the array arr which has the largest integer
        //  + Tức là return element lớn hơn các phần tử còn lại.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //2 <= arr.length <= 5 * 10^5
        //1 <= arr[i] <= 100
        //
        //- Brainstorm
        //- Bài này ta có thể dùng binary search vì:
        //+ Log2(5*10^5)~19
        //Ex:
        //arr=[7,7,7,7,10,7,7,7]
        //[0, 7] => mid=3
        //+ (high-low)%2==1:
        //  + [0,3][4,7]
        //  + (0, 3(mid)), (4(mid+1),6)
        //   + ==1 ==> high=mid
        //   + ==-1 ==> low=mid+1
        //+ (high-low)%2==0:
        //arr=[7,7,7,7,10,7,7]
        //  + mid= (0 + 6)/2=3
        //  + (0, 3(mid)-1), (4(mid+1),6)
        //   + ==0 ==> return mid
        //   + ==1 ==> high=mid
        //   + ==-1 ==> low=mid+1
        //arr=[7,10]
        //mid=0
        //
        //1.1, Optimization
        //- Liệu có summarize được code lại không?
        //Ex:
        //arr=[7,7,7,7,(10),7,7,7], high=7
        //low=0, high=7 ==> mid = high-(high-low)/2 = 7-(7-0)/2 = 4
        //Ex:
        //arr=[7,7,7,(7),10,7,7], high=6
        //low=0, high=6 ==> mid = high-(high-low)/2 = 6-(6-0)/2 = 3
        //Ex:
        //arr=[10,7], high=1
        //low=0, high=2 ==> mid = 1 - (1-0)/2 = 1
        //==> Lúc này chọn CÔNG THỨC tính mid thì chọn : mid= high-(high-low)/2 hợp lý nếu xét (n even/ odd) ==> WRONG
        //
        //
        //1.2, Complexity
        //- Space complexity: O(1)
        //- Time complexity: O(log(N))
        //
        System.out.println((int)5.8);
        //#Reference:
        //702. Search in a Sorted Array of Unknown Size
    }
}
