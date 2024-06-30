package E1_counting_sort;

import java.util.Arrays;

public class E1_HIndex {

    public static int hIndex(int[] citations) {
        int n=citations.length;
        Arrays.sort(citations);
        int max1=0;

        for(int i=n-1;i>=0;i--){
            if(citations[i]<=n-i){
                max1= citations[i];
                break;
            }
        }
        //citations=[100]
        //
        for(int i=0;i<n;i++){
            if(n-i<=citations[i]){
                max1=Math.max(n-i, max1);
                break;
            }
        }
        return max1;
    }

    public static int hIndexCountingSort(int[] citations) {
        int n=citations.length;
        //Space: O(n)
        int[] countingSort=new int[n+1];

        //Time : O(m)
        for(int c: citations){
            countingSort[Math.min(c, n)]++;
        }
        int k=n;

        //Time: O(n)
        for(int i=countingSort[n];k>i;i+=countingSort[k]){
            k--;
        }
        return k;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given an array of integers citations where citations[i] is (the number of citations a researcher)
        // received for their (ith paper),
        //* Return the researcher's h-index.
        //- According to (the definition of h-index) on Wikipedia:
        // + The h-index is defined as (the ("maximum") value of h) such
        // that (the given researcher) has published (at least) (h papers) that have (each) been cited ("at least") (h times).
        //  + Vì là at least:
        //  Ex:
        //  citations = [100]
        //  => rs=1 ==> h=1 ==> 100 thì là at least 100
        //* Tìm max(h) sao cho:
        //  + Reseacher đó có (h papers) và mỗi paper được cited ít nhất (h times).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //n == citations.length
        //1 <= n <= 5000
        //0 <= citations[i] <= 1000
        //- Số lượng cities không nhiều ==> O(N^3) được.
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: citations = [3,0,6,1,5]
        //Output: 3
        //=> sort: 0,1,3,5,6
        //- Đếm từ cuối lên đầu ==> nếu có (min == prefixsum là được)
        //
        //Example 2:
        //Input: citations = [1,3,1]
        //Output: 1
        //=> sort : 1,1,3
        //
        //Example 3:
        //* at least nghĩa là ciations:
        //  + Không cần phải >= n-i
        //  + citations[i] <= n-i : Left --> right
        //      + Ta sẽ lấy n-i ==> break:
        //citations =
        //[100]
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Before optimizing:
        //+ Space: O(log(n))
        //+ Time: O(n*log(n))
        //
        //2. Counting sort
        //2.0,
        //- Vì k không bao giờ lớn hơn được n:
        //  ==> Ta sẽ chuyển tất cả thành min(n, elements[i])
        //Ex:
        //citations = {3,0,6,1,5}
        //+ sort:
        //citations = [0,1,3,5,6]
        //=> Convert min:
        //+ citations = [0,1,3,5,5]
        //+ countingSort[] = {0,1,0,1,0,2}
        //- Lấy H max:
        //  + count từ (right -> left) để tính số lượng elements
        //  + Nếu count<k: Tức là không đủ k để có thể lấy
        //      + Ex:
        //      countingSort[] = {0,1,0,1,0,2}
        //      {0,1,0,(1),0,2} : tại index=3 sẽ lấy
        //      + Tại index=3 sẽ là giá trị min nhất của (1,0,2) <=> (3,4,5)
        //      ==> Nếu count<k thì ta sẽ không lấy.
        //          + Vì ta muôn 3 phần tử để có min = x ==> Không đủ phần tử sẽ không thoả mãn.
        //
        //  + Nếu (count >= k) => break
        //      + Nếu ta có 3 phần tử mà min của nó at least là k ==> Lấy k cũng được.
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n)
        //- Time: O(n+m)
        //
        //#Reference:
        //275. H-Index II
        int[] citations = {3,0,6,1,5};
        System.out.println(hIndex(citations));
        System.out.println(hIndexCountingSort(citations));
    }
}
