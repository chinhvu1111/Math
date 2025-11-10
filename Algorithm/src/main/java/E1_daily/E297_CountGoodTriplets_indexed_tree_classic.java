package E1_daily;

public class E297_CountGoodTriplets_indexed_tree_classic {

    public static int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n=arr.length;

//        for (int i = 0; i < n; i++) {
//            for (int j = i+1; j < n; j++) {
//
//            }
//        }
        int rs=0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    int x=Math.abs(arr[i]-arr[j]);
                    int y=Math.abs(arr[j]-arr[k]);
                    int z=Math.abs(arr[i]-arr[k]);
                    if(x<=a&&y<=b&&z<=c){
                        rs++;
                    }
                }
            }
        }
        return rs;
    }

    public static int countGoodTripletsRefactor(int[] arr, int a, int b, int c) {
        int n=arr.length;
        int rs=0;
        int[] sum=new int[1001];
        //
        for (int j = 0; j < n; j++) {
            for (int k = j+1; k < n; k++) {
                if(Math.abs(arr[j]-arr[k])<=b){
                    int lj=arr[j]-a, rj=arr[j]+a;
                    int lk=arr[k]-c, rk=arr[k]+c;
                    int l=Math.max(0, Math.max(lj, lk)), r=Math.min(1000, Math.min(rj, rk));

                    if(l<=r){
                        if(l==0){
                            rs+=sum[r];
                        }else{
                            rs+=sum[r]-sum[l-1];
                        }
                    }
                }
            }
            for (int k = arr[j]; k <=1000; k++) {
                sum[k]++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (an array of integers arr), and (three integers a, b and c).
        //- You need to find (the number of good triplets).
        //- A triplet (arr[i], arr[j], arr[k]) is good if the following conditions are true:
        //  + 0 <= i < j < k < arr.length
        //  + |arr[i] - arr[j]| <= a
        //  + |arr[j] - arr[k]| <= b
        //  + |arr[i] - arr[k]| <= c
        //  Where |x| denotes the absolute value of x.
        //* Return (the number of good triplets).
        //
        //Example 1:
        //
        //Input: arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
        //Output: 4
        //Explanation: There are 4 good triplets: [(3,0,1), (3,0,1), (3,1,1), (0,1,1)].
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //3 <= arr.length <= 100
        //0 <= arr[i] <= 1000
        //0 <= a, b, c <= 1000
        //  + length <= 100 => Time: O(n^3)
        //
        //* Brainstorm:
        //Ex:
        //Input: arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
        //Output: 4
        //
        //- Using brute force
        //
        //- How to solve this in O(n^2)?
        //+ (i),(j),(k)
        //- x,y,(z)
        //- |x-z|<=c
        //  <=> (x-z)<=c
        //      + x>=z
        //      + z>=x-c
        //  <=> (z-x)<=c
        //      + z>x
        //      + z<=x+c
        //
        //- |y-z|<=b
        //  <=> (y-z)<=b
        //      + y>=z
        //      + z>=y-b
        //  <=> (z-y)<=b
        //      + y<z
        //      + z<=y+b
        //- We have 4 cases:
        //  + (z>=x-c) && (z>=y-b) & x>=z && y>=z
        //  + (z>=x-c) && (z<=y+b) & x>=z && y<z
        //  + (z<=x+c) && (z>=y-b) & z>x && y>=z
        //  + (z<=x+c) && (z<=y+b) & z>x && y<z
        //==> Complicated
        //- Between [l,r]
        //  + Count how many element between this range
        //  + i < j
        //** The perspective of (discretization) or (binary indexed trees),
        //Ex:
        //arr =
        //[1,3,6,9,10]
        //[1,1,1,1,1] (j=0)
        //[1,2,2,2,2] (j=1)
        //[1,2,3,3,3] (j=2)* ==> Nếu (j=2) các (giá trị biến đổi) sẽ (chỉ đến 6)
        //[1,2,3,4,4]
        //[1,2,3,4,5] (j==4) ==> last one
        //- j=2
        //[3,9]
        //x = [9]-[2]
        //x = [3]-[1] = 2
        //* Main point:
        //- J is 0-indexed
        //* Formula:
        //  + count = sum[r]-sum[l-1]
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(1) ==> O(max)
        //- Time: O(n^3) ==> O(n^2)
        //
        int[] arr = {3,0,1,1,9,7};
        int a = 7, b = 2, c = 3;
        System.out.println(countGoodTriplets(arr, a, b, c));
        System.out.println(countGoodTripletsRefactor(arr, a, b, c));
    }
}
