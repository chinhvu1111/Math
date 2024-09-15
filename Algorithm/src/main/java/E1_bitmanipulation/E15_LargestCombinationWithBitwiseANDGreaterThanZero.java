package E1_bitmanipulation;

public class E15_LargestCombinationWithBitwiseANDGreaterThanZero {

    public static int largestCombination(int[] candidates) {
        int max=0;
        int n=candidates.length;

        for(int e: candidates){
            max=Math.max(max, e);
        }
        int numBit=0;

        while(max!=0){
            numBit++;
            max=max/2;
        }
        int rs=0;

        for(int i=0;i<numBit;i++){
            int curNumWithNonEmptyBit = 1<<i;
//            System.out.printf("Bit:%s, num:%s\n", i, curNumWithNonEmptyBit);
            int count=0;
            for(int j=0;j<n;j++){
                if((candidates[j]&curNumWithNonEmptyBit)!=0){
                    count++;
                }
            }
            rs=Math.max(rs, count);
        }
        return rs;
    }

    public static int largestCombinationRefer(int[] candidates) {
        int[] ones = new int[24];
        for (int candidate : candidates){
            int index = 0;
            while (candidate != 0){
                if((candidate & 1) == 1){
                    ones[index] += 1;
                }
                candidate = candidate >> 1;
                index++;
            }
        }
        int maxAns = 0;
        for (int i = 0; i < ones.length; i++) {
            maxAns = Math.max(maxAns, ones[i]);
        }
        return maxAns;
    }

    public static void main(String[] args) {
        //* Requirement
        //- The bitwise AND of an array nums is (the bitwise AND of all integers) in nums.
        //  + For example, for nums = [1, 5, 3], the bitwise AND is equal to 1 & 5 & 3 = 1.
        //  Also, for nums = [7], the bitwise AND is 7.
        //- You are given (an array of positive integers candidates).
        //- Evaluate the bitwise AND of (every combination) of (numbers of candidates).
        //- Each number in candidates may only be (used once) in (each combination).
        //* Return (the size of (the largest combination) of candidates) with a bitwise AND greater than 0.
        //  + Tức là loại đi những combinations có (AND == 0)
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= candidates.length <= 10^5
        //1 <= candidates[i] <= 10^7
        //  + Length khá lớn 10^5: Time: O(n)
        //
        //- Brainstorm
        //- 2 số AND với nhau ==0 khi nào?
        //Ex:
        //100
        //and
        //010
        //==0
        //4 & 2 =0
        //- AND ALL == 0 khi nào
        //  + Rule này hơi khó
        //
        //- Tập hợp AND !=0:
        //  + Chúng có chung ít nhất 1 bit !=0
        //- Ta sẽ tìm số lớn nhất:
        //  + Lấy size số bit 1 của số đó
        //- Xét mọi case với (size bit == n):
        //  + (Bit tại index=0 -> n == 1)
        //      + Ta sẽ check max length các số có chung cùng 1 bit là được.
        //- Vấn đề là có thể bị trùng không?
        //  + Combination a có thể chung 2 bit tại (i và j)
        //      + Nếu cộng dồn 2 cases này thì sẽ bị duplicated
        //* SAI ĐỀ BÀI:
        //  + Cái ta cần làm tìm max size của combination
        //- 1 combination nếu chung 2 bits == 1:
        //  + Thì chỉ cần xét 1 bits là được
        //      + "Chung": all of number đều có bit(i) == 1
        //  ==> Nếu mở rộng thêm (number=x):
        //      + Nếu không có bit(i)==1 ==> Sẽ AND ra ==0 nên không được.
        //  + Phải chung bit.
        //
        //1.1, Optimization
        //- Có 1 cách khác tương tự:
        //- Ta có thể loop mỗi number:
        //  + count số lượng 1 của mỗi vị trí bit bằng cách:
        //      + Loop toàn bộ các bit trong number
        //  + Lưu vào array
        //- Sau đó loop lấy max
        //
        //1.2, Complexity
        //- M is the number of bit of the max number
        //- Space: O(1)
        //- Time: O(n*m) = O(n*log2(max))
        //
        //#Reference:
        //756. Pyramid Transition Matrix
        //757. Set Intersection Size At Least Two
        //2416. Sum of Prefix Scores of Strings
        int[] candidates = {16,17,71,62,12,24,14};
        System.out.println(largestCombination(candidates));
        System.out.println(largestCombinationRefer(candidates));
    }
}
