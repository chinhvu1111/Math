package contest;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class E162_CountTheNumberOfSubstringsWithDominantOnes {

    public static int numberOfSubstrings(String s) {
        int n=s.length();
//        int[] prefixSumZero=new int[n];
        int sumZero=0;
        int sumOne=0;
        TreeSet<double[]> set=new TreeSet<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                if(o1[3]!=o2[3]){
                    //Chỗ này cẩn thận
                    return o1[3]>o2[3]?1:-1;
                }
                return (int) (o1[2]-o2[2]);
            }
        });
        int rs=0;
        set.add(new double[]{0,0,-1,0});

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='0'){
                sumZero++;
            }else{
                sumOne++;
            }
            if(set.isEmpty()&&sumOne!=0){
                rs++;
            }
            Iterator<double[]> iterator=set.iterator();
//            System.out.printf("Index: %s\n", i);

            while (iterator.hasNext()){
                double[] curElement= iterator.next();
                int numZeroInRange= (int) (sumZero-curElement[0]);
                int numOne= (int) (i-curElement[2]-numZeroInRange);
                if(sumZero-curElement[0]<=Math.sqrt(numOne)){
//                    System.out.printf("With: index=%s\n", curElement[2]);
                    rs++;
                }else{
                    while (iterator.hasNext()){
                        double[] nextE= iterator.next();
//                        if(nextE[3]!=curElement[3]){
//                            break;
//                        }
                        numZeroInRange= (int) (sumZero-nextE[0]);
                        numOne= (int) (i-nextE[2]-numZeroInRange);
                        if(sumZero-nextE[0]<=Math.sqrt(numOne)){
//                            System.out.printf("With: index=%s\n", nextE[2]);
                            rs++;
                        }
                    }
                    break;
                }
            }
            set.add(new double[]{sumZero, sumOne, i, Math.sqrt(sumOne)-sumZero});
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (a binary string s).
        //* Return the number of substrings with (dominant ones).
        //- A string has (dominant ones) if (the number of ones) in the string
        // is (greater than or equal) to the square of (the number of zeros) in the string.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 4 * 10^4
        //s consists only of characters '0' and '1'.
        //+ Time: O(n*k) ==> Không thể xử lý trong O(n^2)
        //  ==> Chỉ có thể là Time = O(n*log(n))
        //
        //** Brainstorm
        //- Bài này không giải được trong O(n^2)
        //
        //Example 1:
        //Input: s = "00011"
        //Output: 5
        //- Thử cố định 1 đầu thì sao?
        //  + Sau đó tính số lượng chữ số 1/0 của từng substring.
        //- Số lượng chữ số 1 >= (số lượng số 0)^2
        //- Đếm số lượng string thoả mãn điều kiện:
        //Ex:
        //Input: s = "00011"
        //
        //Output: 5
        //
        //Explanation:
        //
        //The substrings with dominant ones are shown in the table below.
        //
        //i	j	s[i..j]	Number of Zeros	Number of Ones
        //3	3	1	    0	            1
        //4	4	1	    0	            1
        //2	3	01	    1	            1
        //3	4	11	    0	            2
        //2	4	011	    1	            2
        //- Các ký tự 0/1 cần phải liên tiếp nhau ==> 1 substring
        //
        //- Ta không thể xét toàn bộ substring:
        //  ==> Time limit
        //
        //- Tính chất:
        //  + Nếu s thoả mãn ==> s+'1' ==> sẽ thoả mãn
        //  + Nếu s không thoả mãn:
        //      + s+'1' ==> Cần check lại
        //          + (numOne++ >= numZero*numZero)
        //      + s+'0' ==> Không thoả mãn
        //- Tương tự thoả mãn + '0' vào (leading or trailing)
        //
        //Ex:
        //s= 10011000101
        //i=0: valid substr=1
        //i=1: valid substr=10
        //i=2: non
        //i=3:
        //  + s1=1001
        //  + 1(i=3,j=3),01(i=2,j=3)
        //i=4:
        //  + s1=10011
        //  + 1(i=4,j=4),11(i=3,j=4)
        //- Khi thêm 1 char ==> số lượng char từ (start -> end) tăng thêm 1
        //+ i=4:
        //  + Xét mọi j<4
        //      + j=0:
        //          + prefixSum_one[i=4] - prefixSum_one[j=-1] = 3
        //          + numZero = i-j - 3 = 4 +1 - 3 = 2
        //          + 3<2*2 ==> Không thoả mãn
        //- Bài này làm prefix sum được.
        //  + Vì số lượng chữ số 1 sẽ luôn có xu hướng tăng lên ==> Ta sẽ cần kết hợp là được
        //
        //- Càng ngày càng lớn ==> (Prefix sum lớn nhất) sẽ check kết hợp được với cái nhỏ nhất trở đi ==> Đến khi tìm được cái thoả mãn
        //==> Ở đây ta dùng binary search
        //  + Nhưng ở đây có trường hợp số lượng số 0 nhiều ==> Thành ra không thoã mãn điều kiện nếu chỉ count 1
        //  + Tức là nhiều số 1 nhất ==> Chưa chắc đã tốt
        //      + Vì có thể có nhiều chữ số 0
        //
        //- Ta thấy:
        //  + prefixOne[i] - prefix[j] >= numZero*numZero
        //  ==> Nên sort ntn?
        //
        //Ex:
        // s = 10011000(1)01
        //- Nếu ta sort theo numZero thì sao?
        //  + (10011100000)1111(1)
        //      + Ta sẽ phải kết hợp dần dần từ (right-> left)
        //      (10011100000) kết hợp với 1111(1)
        //      (1001110000) kết hợp với 01111(1)
        //      ==> Dịch cho đến khi:
        //          + count[1]<numZero*numZero
        //      ==> Nếu dịch ntn thì left có thể có rất nhiều chữ số 0 hoặc 1
        //          + Ta sẽ không biết dịch đến bao h
        //
        //- Thử tư duy khác đi:
        //  + numOne >= numZero^2
        //==> sqrt(numOne) ==> Tìm numZero thì sao
        //- Dùng prefix sum zero:
        //- Xét đến index=i:
        //  + sumOne = x
        //  ==> sumOne, Phù hợp với:
        //      + numZero = sqrt(sumOne)
        //  + Nhưng nếu tìm được
        //
        //- Trong 1 tập hợp thì có thể xảy ra:
        //  + 1 áp đảo 0
        //  + 0 áp đảo 1
        //- Nếu prefix_zero[i] kết hợp prefixZero[j] vừa đủ thoả mãn:
        //  ==> Không gì đảm bảo được prefix_zero[i] kết hợp với (k>j) prefix_zero[k] sẽ thoả mãn
        //  + Lúc đó (j->k) có thể cut xét rất nhiều 1 ==> Không thoả mãn
        //
        //- Ta sẽ sort theo:
        //  + Sqrt(sum_one) - zero
        //  ==> Tăng dần vì đang xét prefix_sum_zero[i] ==> trừ [Sqrt(sum_one) - zero] càng ít càng tốt
        //  + Từ index=i, ta sẽ tìm thằng (j) thoả mãn:
        //      + prefix_sum_zero[i]-prefix_sum_zero[j] <= sqrt(sum_one)
        //          + Nhưng list cũ [Sqrt(sum_one) - zero] ==> Càng lớn càng tốt
        //              ==> Index của nó chính là số lượng phần tử ta có thể kết hợp được
        //- Ta sẽ dùng treeSet:
        //  + Sort theo:
        //      + [Sqrt(sum_one) - zero]
        //      + index ==> Biết đâu trùng thì lấy thằng index to nhất
        //==> Cần chữa.
        //
        //1.1, Optimization
        //1.2, Complexity
        //
        //
//        String s = "00011";
        String s = "101101";
        System.out.println(numberOfSubstrings(s));
        //#Reference:
        //1377. Frog Position After T Seconds
        //2926. Maximum Balanced Subsequence Sum
        //1662. Check If Two String Arrays are Equivalent
        //
    }
}
