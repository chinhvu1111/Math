package E1_bitmanipulation;

import java.util.Arrays;

public class E6_1_MaximumCompatibilityScoreSum {

    public static int rs=0;

    public static void solution(int index, int score, boolean[] visitedMentors, int[][] dp, int n){
        if(index==n){
            rs=Math.max(rs, score);
            return;
        }
        //n cách chọn
        for(int i=0;i<n;i++){
            if(visitedMentors[i]){
                continue;
            }
            visitedMentors[i]=true;
            solution(index+1, score+dp[index][i], visitedMentors, dp, n);
            visitedMentors[i]=false;
        }
    }

    public static int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int n=students.length;
        int[][] dp=new int[n][n];
        rs=-1;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int[] curStudent=students[i];
                int[] curMentor=mentors[j];
                int curComp=0;

                for(int l=0;l<curStudent.length;l++){
                    if(curStudent[l]==curMentor[l]){
                        curComp++;
                    }
                }
                dp[i][j]=curComp;
            }
        }
        boolean[] visited=new boolean[n];
        solution(0, 0, visited, dp, n);
        return rs;
    }

    public static int getBit(int[] arr){
        //1,0,1,0 ==> 1010
        //- Đi từ left qua right xong đẩy dần bit qua left thôi
        int rs=0;
        for(int e:arr){
            rs=rs|e;
            rs=rs<<1;
        }
        rs=rs>>1;
        return rs;
    }

    public static int solutionBitMask(
            int indexStudent, int visitedMentors,
            int[] mentorMemo, int n, int[][] students, int[][] mentors){
        if(indexStudent==n){
            return 0;
        }
        if(mentorMemo[visitedMentors]!=-1){
            return mentorMemo[visitedMentors];
        }
        int rs=0;
        for(int i=0;i<n;i++){
            if(((visitedMentors>>i)&1)==0){
//                System.out.printf("std index: %s, mentor index: %s\n", indexStudent, i);
                int nextVal = solutionBitMask(
                        indexStudent+1,
                        visitedMentors|(1<<i),
                        mentorMemo, n, students, mentors);
                int mentorBit = getBit(mentors[i]);
                int curScore=Integer.bitCount(getBit(students[indexStudent]) ^ (~mentorBit&((1<<mentors[i].length)-1)));
//                System.out.printf("Current score: %s\n", curScore);
                rs=Math.max(rs, nextVal+curScore);
            }
        }
        return mentorMemo[visitedMentors]=rs;
    }

    public static int maxCompatibilitySumBitMask(int[][] students, int[][] mentors) {
        int n=students.length;
        int size=(1<<n)-1;
        int[] mentorVisited=new int[size+1];
        Arrays.fill(mentorVisited, -1);
        return solutionBitMask(0, 0, mentorVisited, n, students, mentors);
    }

    public static int sumNumberBit(int a, int b){
        //2=10
        //2=10
        //
        //3: 011
        //5: 101
        //
        //3: 011
        //7: 111
        while(b!=0){
            int carry=a&b;
            System.out.printf("a:%s, b:%s, carry: %s\n", a, b, carry);
            a=a^b;
            b=carry<<1;
        }
        return a;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is a survey that consists of n questions where each question's answer is either 0 (no) or 1 (yes).
        //- The survey was given to m students numbered from (0 to m - 1) and m mentors numbered from (0 to m - 1).
        //- The answers of the students are represented by a 2D integer array students where students[i] is an integer array
        // that contains the answers of the ith student (0-indexed).
        //- The answers of the mentors are represented by a 2D integer array mentors where mentors[j] is an integer array
        // that contains the answers of the jth mentor (0-indexed).
        //- (Each student) will be assigned to (one mentor), and (each mentor) will have (one student) assigned to them.
        //- The (compatibility score of a student-mentor pair) is the number of answers
        // that are the (same) for (both the student and the mentor).
        //For example, if the student's answers were [1, 0, 1] and the mentor's answers were [0, 0, 1],
        // then their (compatibility score) is 2 because only the (second) and the (third) answers are the same.
        //- You are tasked with finding the (optimal student-mentor pairings)
        // to "maximize" the (sum of the compatibility scores).
        //- Given students and mentors,
        //* return (the maximum compatibility score sum) that can be achieved.
        //- Đại loại là chọn các cặp (student-mentor) sao cho
        //  + Sum (compatibility score) mà maximal
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //m == students.length == mentors.length
        //n == students[i].length == mentors[j].length
        //1 <= m, n <= 8
        //students[i][k] is either 0 or 1.
        //mentors[j][k] is either 0 or 1.
        //=> m, n không lớn
        //
        //- Brainstorm
        //Ex
        //Input: students = [[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]]
        //Output: 8
        //Explanation: We assign students to mentors in the following way:
        //- student 0 to mentor 2 with a compatibility score of 3.
        //- student 1 to mentor 0 with a compatibility score of 2.
        //- student 2 to mentor 1 with a compatibility score of 3.
        //The compatibility score sum is 3 + 2 + 3 = 8.
        //
        //- Compatibility score ta có thể tìm bằng AND các số ==> bit count là được.
        //- Cái này có thể brute force + AND operation được không?
        //- Ta tính dp[i][j] trước để brute force nhanh hơn.
        //
        //- Lấy student làm mốc ==> Index = (0 -> n-1) để chọn tương ứng
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(n^2) : Do lưu dp[][]
        //- Time: O(n!)
        //- Vẽ tree với n=5
        //                n=5
        //             /        \   \   \   \
        //           0           1   2   3   4 : n lựa chọn
        //          /     \       \   \
        //        1          2     3   4 : Mỗi lựa chọn có (n-1) lựa chọn khác
        //       / \  \    / \  \
        //      2   3  4  1  3  4
        //==> n!
        //
        //2. Bit Mask
        //2.0,
        //* Brainstorm
        //- Ở đây ta có thể thay thê visited[i]=true bằng cách gán bit(i)==1
        //  ==> Thay vì dùng mảng thì ta dùng bitmask ==> space giảm từ O(n) -> O(1)
        //  ==> Có thể dùng bitmask để lưu vị trí đã visited[]
        //
        //- Ngoài ra bài này ta có thể lưu lại:
        //  + Trace danh sách các mentors đã assigned
        //  ==> Thay vì return void --> return int + memo theo hinh dạng mentors đã visited.
        //
        //- Tìm số bit giống nhau, function:
        //Ex:
        //0101 : 5
        //1101 : 13
        //
        //0101 xor ~(0010) (Xor với đảo bit)
        //=> rs = 3
        //- Giải thích đoạn đảo bit:
        //- Số âm biểu diễn trong bit ntn?
        //- Sum 2 số ta có thể dùng phép xor + carry(a&b) (Có nhớ)
        //  + Xor là phép keep kết quả (1 ^ 0) = 1
        //  + And là phép keep kết quả (1 or 1) =1 ==> Sẽ được carry (nhớ)
        //      + qua (1 lần << 1) => Tính tiếp thì phải dịch sang left thôi
        //        kết quả and 1 lần ==> Lấy gán b=new rs ==> Tính tiếp.
        //- Cách dùng xor là cách phức tạp, ta có thể tính bình thường như sau:
        //
        // 0101
        //+
        // 1101
        //=
        //10010 ==> Cái này chỉ cần cộng bình thường thôi
        // + 1+1=0 --> nhớ 1...
        //
        //- CT:
        //  + - 32 + mentors[i].length
        //==> Khá khó hiểu.
        //
        //* Biểu diễn số âm ntn?
        //* Sign and Magnitude
        //- Positive và negative giống nhau ==> Chỉ khá signed bit
        //  + Biểu diễn phép tính ra 0 phức tạp với từng computer.
        //binary  decimal
        //  0000        0
        //  0010        2
        //  1010       -2
        //  1101       -5
        //  1000       -0 (wut?)
        //Ex
        //carry:  0 0 1 0 0
        //_________________
        //          0 0 1 0     (2)
        //      +   1 0 1 0     (-2)
        //      ___________
        //          1 1 0 0     (-4) <- we really want this to be 0!
        //
        //* Two complement:
        //- Negative number sẽ là flip của positive number + 1 biến flip ngoài size.
        //  + Biểu diễn được phép tính ra 0
        //- Dựa trên size của số:
        //- Size=4 ==> chọn bit left most có hệ số âm
        //1110 = (1 * -2^3) + (1 * 2^2) + (1 * 2^1) + (0 * 2^0) = -8 + 4 + 2 + 0 = -2
        //==> 1110 <=> -2
        //** Ý là -2 ==> map ra 1110
        // ** (KHÔNG CÓ map binary -> decimal NGƯỢC LẠI / Nếu muốn map ngược thì cần size của số)
        //  + Vì 1110 có 2 giá trị âm dương mà ==> Ta cần size để biết được sign.
        //
        //binary  decimal(unsigned)   decimal(two's complement)
        //  0000                 0                            0
        //  0001                 1                            1
        //  0010                 2                            2
        //  0011                 3                            3
        //  0100                 4                            4
        //  0101                 5                            5
        //  0110                 6                            6
        //  0111                 7                            7
        //  1000                 8                           -8
        //  1001                 9                           -7
        //  1010                10                           -6
        //  1011                11                           -5
        //  1100                12                           -4
        //  1101                13                           -3
        //  1110                14                           -2
        //  1111                15                           -1
        //- Có size để bỏ qua carry:
        //carry:  1 1 1 0 0
        //_________________
        //          0 0 1 0     (2)
        //      +   1 1 1 0     (-2)
        //      ___________
        //          0 0 0 0     (0) <- ahhhh much better
        //        ^ remember we ignore this carry bit
        //
        //* Formula:
        // -x = ~x + 1
        //Example: x = 5 (assume 4-bit numbers)
        //x      = 0101
        //~x     = 1010
        //~x + 1 = 1011
        //
        //1011 = (1 * -2^3) + (0 * 2^2) + (1 * 2^1) + (1 * 2^0) = -8 + 2 + 1 = -5
        //
        //- Flip bit ntn để không bị số âm
        //Ex:
        //101 =5
        //~101 =1|010 = -8 + 2 = -6
        //- Cần lấy 010
        //* CT:
        //  + 1|010 & 0|111 = ~x & ((1<<n) -1
        //
        //2.1, Optimization
        //2.2, Complexity
        //m: size of answer
        //- Space: O(2^m)
        //- Time : O(n!)
//        int[][] students = {{1,1,0},{1,0,1},{0,0,1}}, mentors = {{1,0,0},{0,0,1},{1,1,0}};
        int[][] students = {{0,0},{0,0},{0,0}}, mentors = {{1,1},{1,1},{1,1}};
        System.out.println(maxCompatibilitySum(students, mentors));
        System.out.println(maxCompatibilitySumBitMask(students, mentors));
        System.out.println(getBit(new int[]{1,0,1}));
        //Test flit bit
        //~110
        //= -7
        //~11
        //=-4
        System.out.println(~6);
        //Test sum of the two number by using bit:
//        int a=2;
//        int b=2;
//        int a=3; //011
//        int b=5; //101
        int a=3; //011
        int b=7; //111
        System.out.println("Sum 2 number using bit method");
        System.out.println(sumNumberBit(a, b));
        //#Reference:
        //461. Hamming Distance
        //174. Dungeon Game
        //2500. Delete Greatest Value in Each Row
        //1559. Detect Cycles in 2D Grid
        //1478. Allocate Mailboxes
        //2225. Find Players With Zero or One Losses
    }
}
