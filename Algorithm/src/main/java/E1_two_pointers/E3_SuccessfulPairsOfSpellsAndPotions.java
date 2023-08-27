package E1_two_pointers;

import java.util.*;

public class E3_SuccessfulPairsOfSpellsAndPotions {

    public static class Node{
        long value;
        int index;
        public Node(int index, long value){
            this.index=index;
            this.value=value;
        }
        public String toString() {
            return index +" : "+value;
        }
    }

    public static int[] successfulPairsMethod1(int[] spells, int[] potions, long success) {
        int n=spells.length;
        int m=potions.length;
        //Space : O(n)
        //Time : O(n)
        List<Node> sortedList=new ArrayList<>();
        for(int i=0;i<n;i++){
            sortedList.add(new Node(i, spells[i]));
        }
        //Time : O(n*log(n))
        sortedList.sort((o1, o2) -> (int) (o1.value - o2.value));
        // System.out.printf("%s\n", sortedList);
        //Space : O(n)
        //Time : O(n)
        Integer[] potionsInt=new Integer[m];
        // Arrays.setAll(potionsInt, i -> potions[i]);
        for(int i=0;i<m;i++){
            potionsInt[i]=potions[i];
        }
        //Time : O(n*log(n))
        Arrays.sort(potionsInt, (o1, o2) -> o2-o1);

        int maxSuccess=0;
        int i=0,j=0;
        int[] result=new int[n];

        //Time : O(n+m)
        while(i<n||j<m){
            if(i<n){

                if(j<m&&sortedList.get(i).value*potionsInt[j]>=success){
                    result[sortedList.get(i).index]=j+1;
                    maxSuccess=j+1;
                    // System.out.printf("Assign %s : index i=%s, j=%s, value=%s\n", sortedList.get(i).index, i, j, sortedList.get(i).value);
                    j++;
                }else{
                    result[sortedList.get(i).index]=maxSuccess;
                    // result[sortedList.get(i).index]=maxSuccess;
                    i++;
                }
            }else{
                // if(i<n){
                //    result[sortedList.get(i).index]=maxSuccess;
                // }
                break;
            }
            // System.out.printf("index i=%s, j=%s\n", i, j);
            // if(i<n){
            //     System.out.printf("Current index %s, index i=%s, j=%s, value=%s\n", sortedList.get(i).index, i, j, sortedList.get(i).value);
            // }
        }

        return result;
    }

    public static void println(int[] input){
        int n=input.length;

        for (int j : input) {
            System.out.printf("%s,", j);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given spells and potions (Bùa chú và thuốc độc)
        //- Spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
        //- Given success, a spell and potion pair is successful nếu product của chúng >= success
        //* return array of pairs (length=n) where pair[i] is the number of potions that will form a successful pair with (ith) spell
        //Ex:
        //spells = [5,1,3], potions = [1,2,3,4,5], success = 7
        //i=0: value=5 ==> 5*[1,2,3,4,5]= [5,10,15,20,25]
        //+ the numbers >=7 : {10,15,20,25}, length=4 ==> result[0]=4
        //* result=[4,0,3]
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //n == spells.length
        //m == potions.length
        //1 <= n, m <= 10^5
        //1 <= spells[i], potions[i] <= 10^5
        //1 <= success <= 10^10
        //+ Các số khá lớn nên không thể (* tất cả) + tìm length mỗi cases được
        //+ Không thể loop O(n^2) được vì là 10^5
        //+ 10^9 : Larger number --> Long type --> Tránh debug mấy tiếng để fix lỗi này.
        //
        //- Brainstorm
        //spells = [5,1,3],
        //potions = [1,2,3,4,5], success = 7
        //+ sort spell = [1,3,5]
        //+ sort potions = [5,4,3,2,1]
        //i=0, j=0
        //Chạy lần lượt nếu 1* array mà đã lớn hơn rồi --> Thằng đằng sau length chỉ cộng thêm thôi.
        //
        //i=0, j=0 : 1*5<=7 ==> i++;
        //i=1,j=0 : 3*5>=7 ==> j++, rs++
        //i=1,j=1 : 3*4>=7 ==> j++, rs++
        //i=1,j=2: 3*3>=7 ==> j++, rs++
        //i=1,j=3 : 3*2<7 ==> i++
        //i=2(value=5 dùng lại rs)...
        //
        //- Bound index:
        //- i==k: thì số đã >=success mất rồi
        //
        //- Case 1:
        //Ex:
        //spells = [12,(33),51],
        //potions = [5,(4),3,2,1], success = 1452
        //+ Giả sử đến 33*4 --> Thoả mãn rồi : j++ --> n-1
        //+ Giả sử đến 33*4 --> Thoả mãn rồi : j++ --> n-1
        //- Case 2:
        //Ex:
        //spells = [12,33,(51)],
        //potions = [5,(4),3,2,1], success = 1452
        //+ i==n-1
        //
        //Case 3:
        //- Xảy ra khi : x>i thoả mãn + j hết ngưỡng
        //==> Những số đằng sau sẽ luôn = all
        //+ i<n && j==m-1
        //
        //- Special case:
        //Spells=[15,8,19]
        //potions=[38,36,23]
        //+ Sort spell = [8,15,19]
        //+ Sort potions = [38,36,23]
        //+ success=328
        //
        //i=0 --> reach -1 (First break)
        //i=1 --> j=2 ==> 3
        //i=2 --> j=2 ==> 3
        //
        //1.1, Optimization
        //- Refactor logic lại
        //
        //1.2, Complexity
        //- Space :O(n)
        //- Time : O(m+n*log(n))
        //
        //* Method-2:
        //- Sort + binary search
        //2.
        //Ex:
        //- spells = {5,1,3}, potions = {1,5,3,2,4};
        //- success=7
        //+ Sort potions = [1,2,3,4,5]
        //Với mỗi spells[i]
        //+ i=0: value=5 ==> (7/5)=1.4
        //Search 1.4 ==> (2>1.4) --> return i=2
        //==> Current result = m-i = 5-2=3 (Tính từ vị trí đó đến cuối)
        //
        int[] spells = {5,1,3}, potions = {1,2,3,4,5};
        int success = 7;
        int[] rs=successfulPairsMethod1(spells, potions, success);

        println(rs);
        //#Reference
        //826. Most Profit Assigning Work
        //2410. Maximum Matching of Players With Trainers
    }
}
