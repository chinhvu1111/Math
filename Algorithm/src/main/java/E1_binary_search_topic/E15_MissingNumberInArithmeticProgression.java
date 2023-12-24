package E1_binary_search_topic;

public class E15_MissingNumberInArithmeticProgression {

    public static int missingNumber(int[] arr) {
        int n=arr.length;
        int low=0, high=n-1;
        //Range này lấy ntn cho đúng
        int range=Math.abs((arr[n-1]-arr[0])/n);
        if(range==0){
            return arr[0];
        }
//        System.out.println(range);

        while (low<high) {
            int mid=low+(high-low)/2;

            if(Math.abs(arr[mid]-arr[low])!=range*(mid-low)){
                if(Math.abs(arr[mid]-arr[mid-1])!=range){
                    return (arr[mid]+arr[mid-1])/2;
                }
                high=mid-1;
            }else{
                if(Math.abs(arr[mid+1]-arr[mid])!=range){
                    return (arr[mid+1]+arr[mid])/2;
                }
                low=mid+1;
            }
        }
        // System.out.printf("%s %s\n", arr[low], arr[high]);
        return (arr[high]+arr[low])/2;
    }

    public static int missingNumberOptimization(int[] arr) {
        int n=arr.length;
        int low=0, high=n-1;
        //Range này lấy ntn cho đúng
        int range=(arr[n-1]-arr[0])/n;
        if(range==0){
            return arr[0];
        }
//        System.out.println(range);

        while (low<high) {
            int mid=low+(high-low)/2;

            //!= ==> Tức là missing value ==> arr[low] > missing_value
            if(arr[mid]==arr[0]+range*mid){
                low=mid+1;
            }else{
                high=mid;
            }
        }
        // System.out.printf("%s %s\n", arr[low], arr[high]);
        return (arr[0]+range*low);
    }

    public static void main(String[] args) {
        // Đề bài:
        //- In some array arr, the values were in arithmetic progression: the values arr[i + 1] - arr[i] are all equal for every 0 <= i < arr.length - 1.
        //- A value from arr was removed that was not the first or last value in the array.
        //* Given arr, return the removed value.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //3 <= arr.length <= 1000
        //0 <= arr[i] <= 105
        //The given array is guaranteed to be a valid array.
        //
        //- Brainstorm
        //Ex
        //arr = [5,7,11,13]
        //rs= 9
        //Ex: 13-5=8/2=4 ==> 4 khoảng <=> 5 số
        //- Ta có thể xác định số missing value nằm left or right bằng cách tính số chữ số ta có:
        //Ex
        //arr = [5,7,11,13]
        //mid= (0 + 3)/2= 1
        //==> 0, 1 <=> (7-5)/range ==(i-j) ==> Missing value in right side : low=mid
        //<> high=mid
        //+ Mục tiêu tìm nums[i]-nums[i-1]!=range
        //
        //1.1, Optimization
        //- Đoạn code if else bên trên cơ bản hơi cồng kềnh --> reduce xuống
        //- Mình sẽ xét arr[mid]-arr[0] == range*mid thay vì so (mid với low)
        //  + Đôi khi 1 số bài toán không cần so mid với low/high ==> So với 0/ n-1
        //- Mục tiêu:
        //  + low : là index của element ngay AFTER(SAU) missing value <=> missing_value < arr[low]
        //  + Idea:
        //  + (!=) ==> Tức là missing value ==> arr[low] > missing_value
        //            if(arr[mid]==arr[0]+range*mid){ ==> Break khi (!=)
        //
        //1.2, Complexity:
        //- N is the length of array
        //- Space : O(1)
        //- Time : O(log(N))
//        int[] arr = {5,7,11,13};
//        int[] arr = {15,13,12};
//        int[] arr = {0,0,0};
        int[] arr = {1,2,3,5};
        System.out.println(missingNumber(arr));
        System.out.println(missingNumberOptimization(arr));
        //#Reference:
        //1994. The Number of Good Subsets
        //2864. Maximum Odd Binary Number
        //2125. Number of Laser Beams in a Bank
    }
}
