package E1_Graph;

import java.util.Arrays;
import java.util.HashSet;

public class E10_MinimumGeneticMutation {
    public static char[] genChar={'A', 'C', 'G', 'T'};
    public static HashSet<String> bankGens;
    public static int rs;

    public static void solution(char[] startGenChar, char[] endGenChar, int index, int depth){
        String startStr=String.valueOf(startGenChar);
        String endStr=String.valueOf(endGenChar);
//        System.out.printf("%s %s\n", startStr, endStr);
        if(startStr.equals(endStr)){
            rs=Math.min(rs, depth);
            return;
        }
        if(index>=endStr.length()){
            return;
        }
        for (char currentChar : genChar) {
            char valueBeforeChanging = startGenChar[index];
            startGenChar[index] = currentChar;
            if (isValid(startGenChar, bankGens)) {
                System.out.println(String.valueOf(startGenChar));
//                    System.out.println("Success");
                int nextDepth = depth + 1;
                if (currentChar == valueBeforeChanging) {
                    nextDepth = depth;
                }
                solution(startGenChar, endGenChar, index + 1, nextDepth);
            }
            startGenChar[index] = valueBeforeChanging;
        }
    }

    public static boolean isValid(char[] intermediateValue, HashSet<String> bankGens){
        String currentString=String.valueOf(intermediateValue);
//        System.out.println("Inter: "+currentString);
        return bankGens.contains(currentString);
    }

    public static int minMutationWrong(String startGene, String endGene, String[] bank) {
        bankGens = new HashSet<>(Arrays.asList(bank));
        bankGens.add(startGene);
        bankGens.add(endGene);
        System.out.println(bankGens);
        char[] startGenChar=startGene.toCharArray();
        char[] endGenChar=endGene.toCharArray();
        rs=Integer.MAX_VALUE;
        solution(startGenChar, endGenChar, 0, 0);

        return rs;
    }

    public static boolean[] visited;
    public static int[][] diff;
    public static int result;

    public static void dfs(int index,
                           int endIndex, int currentDiff, int n){
        if(result<=currentDiff){
            return;
        }
        if(index==endIndex){
            result= currentDiff;
//            System.out.printf("%s %s\n", index, currentDiff);
            return;
        }
        for(int i=0;i<n;i++){
            if(!visited[i]){
                int nextDiff=currentDiff+diff[index][i];

                if(diff[index][i]!=1){
                    continue;
                }
//                System.out.println(i);
                visited[i]=true;
                dfs(i, endIndex, nextDiff, n);
                visited[i]=false;
            }
        }
    }

    public static int minMutation(String startGene, String endGene, String[] bank) {
        int n=bank.length;
        diff=new int[n][n];
        visited=new boolean[n];
        result=Integer.MAX_VALUE;
        int endIndex=-1;

        for(int i=0;i<n;i++){
            if(bank[i].equals(endGene)){
                endIndex=i;
            }
            for(int j=0;j<n;j++){
                diff[i][j]=getDiffBwnTwoStrings(bank[i], bank[j]);
            }
        }
        if(endIndex==-1){
            return -1;
        }
        for(int i=0;i<n;i++){
            int currentDiff=getDiffBwnTwoStrings(startGene, bank[i]);

            if(currentDiff!=1){
                continue;
            }
            System.out.println(i);
            visited[i]=true;
            dfs(i, endIndex, currentDiff, n);
            visited[i]=false;
        }
        return result==Integer.MAX_VALUE?-1:result;
    }
    public static int getDiffBwnTwoStrings(String s, String s1){
        int numDiff=0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=s1.charAt(i)){
                numDiff++;
            }
        }
        return numDiff;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Gen được bao gồm các ký tự ACGT
        //- Ta có gen startGen và endGen
        //+ Tìm số lần biến đổi gen ít nhất để startGen --> endGen
        //+ Các gen trung gian phải nằm trong bank (Ngân hàng) --> Danh sách các gen hơp lệ
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint :
        //+ 0 <= bank.length <= 10
        //+ startGene.length == endGene.length == bank[i].length == 8
        //+ startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
        //
        //- Ý tưởng ở đây là dùng DFS để tìm đủ các case có thể xảy ra --> + độ sâu dần dần --> tìm min nhất trong các độ sâu là được.
        //- Kiểm tra intermediate value valid hay không --> Dùng hashset.
        //- Tư duy như sau:
        //                         AACC
        //                  /       \           \
        //              AACC        ACCC        AGCC --> Có 2 lựa chọn: (Giữ nguyên / changed)
        //+ Giữ nguyên : tăng index+1, depth giữ nguyên
        //+ Changed : Tăng index+1, depth +1
        //==> WRONG
        //- Hiểu sai để --> 1 mutate <= 1 character change
        //==> Có thể change nhiều character cùng lúc sao cho nó (thuộc) bank.
        //
        //- Bài này 1 lúc có thể thay đổi nhiều character --> Miễn là intermediate value thuộc (bank)
        //--> Thế nên cái ta cần làm là lưu lại sụ thay đổi giữa 2 string bất kỳ trong bank
        //+ O(n^2*length) time để check
        //- Sau đó ta sẽ dùng dfs để tìm thông tin cần
        //- Vì startGene và endGene thuộc bank --> Ta sẽ xác định được index của nó trong bank
        //+ diff[i][j] là khoảng cách giữa (2 string bất kỳ) mà không dùng phép biến đổi
        //+ Ta sẽ đi tìm depth từ (startIndex --> endIndex)
        //- Ta sẽ quay về bài toán BFS/DFS --> Với các node là các string trong bank
        //+ Ta có thể dùng visited lúc này được.
        //** NOTE: Start genge là gene sai --> không thuộc bank
        //--> Cần đọc kỹ đề hơn
        //+ Note that the starting point is assumed to be valid, so it might not be included in the bank.
        //- Ta sẽ cần 1 đoạn loop để tìm diff giữa startString và các bank --> Chọn lần 1
        //- DFS method:
        //+ Mỗi recursive ta có n cách chọn ==> Lúc đó diff sẽ được cộng thêm vào
        //+ Khi rs <= diff hiện tại --> Không cần xét tiếp ==> Cut nhánh.
        //
        //- Tối ưu thêm để không phải truyền bank vào + tính hashmap(String) quá nhiều
        //+ Chỉ các ký tự khác nhau mới xét --> Không thì bỏ đi
        //
        //- Đề bài vẫn chưa clear:
        //+ Mỗi step ở đây chỉ được reach 1 character --> Chỉ không phải all characters như ta đang nghĩ.
        //+ Start string không in bank
        //+ End string bắt buộc in bank
        //
        //1.1, Special testcases:
        //- -1 --> Cần phải check == MaxInteger --> return -1
        //- Nếu end string không trong bank --> return -1 luôn.
        //
        //1.2, Complexity:
        //- Time complexity:
        //+ cache time = O(n^2)
        //+ Giả sử ta vào được bank rồi:
        // Layer 1 : 1 node
        //Layer 2 : n-1 node
        // Layer 3 : (n-2)(n-1)
        //...
        // Layer n-1 : (n-1)*...*1
        //+ finding path time = 1+ (n-1) + (n-1)*(n-2) + ... + (n-1)*...*1
        // <= (n-1)!*n= (n!)
        // = O(n!) (Ta sẽ lấy cận trên lỏng)
        //+
        //+ time to find the path =
        //              1 (1)
        //          2,      3,      4,       5 (n-1)
        //     (3,4,5)   (2,4,5)  (2,3,5)  (2,3,4) ( (n-1)(n-2) = 12 )
        //- Space complexity :
        //+ visited: O(n^2)
        //+ Stack : n
        //--> Space = O(n^2)
        //
        //** Đã học thêm về complexity:
        //T(N):
        //+ =1 : n=1
        //+ 2* T(N/2) + N
        //Ex :
        //Code : for(n), T(N/2), T(N/2)
        //
        //                  N
        //              /        \
        //          N/2         N/2 = N
        //      /       \       ....
        //  N/4         N/4     N/4     N/4 = N
        //   |
        //  N/K
        //- K = LOG(2)(N) --> TIME= Nlog(N)
        //#Reference:
        //127. Word Ladder
//        String startGene = "AACCGGTT";
//        String endGene = "AACCGGTA";
//        String[] bank = {"AACCGGTA"};
//        String startGene = "AACCGGTT";
//        String endGene = "AAACGGTA";
        //AACCGGTT
        //-->
        //AACCGGTA
        //-->
        //AAACGGTA
        // end=
        //AAACGGTA
        //
//        String[] bank = new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"};
//        System.out.println(minMutationWrong(startGene, endGene, bank));
        String startGene = "AAAAAAAA";
        String endGene = "CCCCCCCC";
        String[] bank = {"AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC","AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA"};
        System.out.println(minMutation(startGene, endGene, bank));
    }
}
