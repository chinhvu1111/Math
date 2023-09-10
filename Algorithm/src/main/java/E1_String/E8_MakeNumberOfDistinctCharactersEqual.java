package E1_String;

public class E8_MakeNumberOfDistinctCharactersEqual {

    public static boolean isItPossible(String word1, String word2) {
        //Space : O(26)
        int[] word1Count=new int[26];
        int[] word2Count=new int[26];
        int count1=0;
        int count2=0;
        int n=word1.length();
        int m=word2.length();

        //Time : O(n)
        for(int i=0;i<n;i++){
            if(word1Count[word1.charAt(i)-'a']==0){
                count1++;
            }
            word1Count[word1.charAt(i)-'a']++;
        }
        //Time : O(m)
        for(int i=0;i<m;i++){
            if(word2Count[word2.charAt(i)-'a']==0){
                count2++;
            }
            word2Count[word2.charAt(i)-'a']++;
        }
        if(Math.abs(count1-count2)>2){
            return false;
        }
        if(count1<count2){
            int[] temp=word1Count;
            word1Count=word2Count;
            word2Count=temp;
            int temp1=count1;
            count1=count2;
            count2=temp1;
        }

        if(count1==count2){
            //Time : O(26*26)
            for(int i=0;i<26;i++){
                if(word1Count[i]==1){
                    for(int j=0;j<26;j++){
                        //ab
                        //cd
                        //
                        if(i!=j&&word2Count[j]==1&&word1Count[j]==0&&word2Count[i]==0){
                            return true;
                        }
                        //a
                        //bb
                        if(i==j&&word2Count[i]>=1){
                            return true;
                        }
                    }
                }else if(word1Count[i]>1){
                    for(int j=0;j<26;j++){
                        //aacd
                        //bbef
                        //
                        //aadd
                        //cdc
                        //
                        //+ count[d]>1
                        //+
                        //
                        //aa
                        //bb
                        //+ count1[a]=2
                        //+ count2[a]=0
                        //+ count1[b]=0
                        //+ count2[b]=2
                        //
                        //ff
                        //f
                        //
                        if(i!=j&&word1Count[j]>=1&&word2Count[i]>=1){
                            return true;
                        }
                        if(i!=j&&word2Count[j]>1&&word1Count[j]==0&&word2Count[i]==0){
                            return true;
                        }
                        if(i==j&&word2Count[j]>=1){
                            return true;
                        }
                    }
                }
            }
        }
//        for(int i=0;i<26;i++){
//            System.out.printf("%s, ", word1Count[i]);
//        }
//        System.out.println();
//        for(int i=0;i<26;i++){
//            System.out.printf("%s, ", word2Count[i]);
//        }
        System.out.printf("Count1: %s, count2: %s, Count: %s\n", count1, count2, count1-count2);
        if(count1-count2==1){
            //Time : O(26^2)
            for(int i=0;i<26;i++){
                if(word1Count[i]==1){
//                    System.out.printf("Index: %s\n",i);
                    for(int j=0;j<26;j++){
                        //Ex:
                        //aa
                        //ab
                        //word1=ab
                        //word2=aa
                        //--> false
                        //word1=ab, count=2
                        //word2=aab, count=2
                        //+ count1[i]==1
                        //+ count2[i]>=1
                        //+ i!=j
                        //+ count1[j]>=1
                        //+ count2[j]>1
                        //
                        //abcc : count=3
                        //aab : count=2
                        //a<->b
                        //abcc ==> aacc : count=2
                        //aab ==> abb : count=2
                        //
                        //bccd : count=3
                        //aab : count=2
                        //+ count1[d]=1
                        //+ count1[b]=1
                        //+ count2[d]=0
                        //+ count2[b]=1
                        //
                        //--> d <-> b
                        //bccb : count=2
                        //aad : count=2
                        //
//                        System.out.printf("%s %s, word2Count[i]: %s, word2Count[j]: %s, word1Count[j]: %s\n", i, j, word2Count[i], word2Count[j], word1Count[j]);
                        if(i!=j&&word2Count[i]>=1&&word2Count[j]>1&&word1Count[j]>=1){
//                            System.out.printf("%s %s, Result: %s\n", i, j, true);
                            return true;
                        }
                        if(i!=j&&word2Count[i]==0&&word2Count[j]==1&&word1Count[j]>=1){
//                            System.out.printf("%s %s, Result: %s\n", i, j, true);
                            return true;
                        }
                    }
                }else if(word1Count[i]>1){
                    //aaccbb : count=3 +0 count=3
                    //cca : count=2 +1 count=3
                    //--> b <-> c
                    //- count1: i
                    //- count2 : j
                    //- count1[i]>1
                    //- count2[i]==0
                    //- count1[j]>=1
                    //- count2[j]>1
                    for(int j=0;j<26;j++){
                        if(i!=j&&word2Count[i]==0&&word1Count[j]>=1&&word2Count[j]>1){
                            return true;
                        }
                    }
                }
            }
        }
        if(count1-count2==2){
            //Time : O(26^2)
            for(int i=0;i<26;i++){
                if(word1Count[i]==1){
                    for(int j=0;j<26;j++){
                        //Ex:
                        //aabc
                        //aa
                        //b<->a
                        //+ count1[b]==1
                        //+ b!=a
                        //+ count2[b]==0
                        //+ count1[a]>=1
                        //
                        //bcd : count=3 -1= 2
                        //aa : count=1 +1= 2
                        //--> return false
                        //
                        //bca : count=3 -1= 2
                        //aa : count=1 +1= 2
                        //-> b <-> a
                        //- count1 : b (i)
                        //- count2 : a (j)
                        //+ count1[i]==1
                        //+ count2[i]==0
                        //+ count1[j]>=1
                        //+ count2[j]>=1
                        if(word2Count[i]==0&&word1Count[j]>=1&&word2Count[j]>1){
//                            System.out.printf("%s %s, Result: %s\n", i, j, true);
                            return true;
                        }
                    }
                }
//                else if(word1Count[i]>1){
//                    for(int j=0;j<26;j++){
//                        //aa
//                        //bcd
//                        //- ba
//                        //- acd
//                        //+ count1[a]=2
//                        //+ count2[a]=0
//                        //+ count2[b]=1
//                        //+ count1[b]=0
//                        if(i!=j&&word2Count[i]==0&&word2Count[j]==1&&word1Count[j]==0){
//                            return true;
//                        }
//                    }
//                }
            }
        }
        return false;
    }

    public static boolean isItPossibleOptimization(String word1, String word2) {
        //Space : O(26)
        int[] word1Count=new int[26];
        int[] word2Count=new int[26];
        int count1=0;
        int count2=0;
        int n=word1.length();
        int m=word2.length();

        //Time : O(n)
        for(int i=0;i<n;i++){
            if(word1Count[word1.charAt(i)-'a']==0){
                count1++;
            }
            word1Count[word1.charAt(i)-'a']++;
        }
        //Time : O(m)
        for(int i=0;i<m;i++){
            if(word2Count[word2.charAt(i)-'a']==0){
                count2++;
            }
            word2Count[word2.charAt(i)-'a']++;
        }
        if(Math.abs(count1-count2)>2){
            return false;
        }
        if(count1<count2){
            int[] temp=word1Count;
            word1Count=word2Count;
            word2Count=temp;
            int temp1=count1;
            count1=count2;
            count2=temp1;
        }

        if(count1==count2){
            //Time : O(26*26)
            for(int i=0;i<26;i++){
                if(word1Count[i]==1){
                    for(int j=0;j<26;j++){
                        if(i!=j&&word2Count[j]==1&&word1Count[j]==0&&word2Count[i]==0){
                            return true;
                        }
                        if(i==j&&word2Count[i]>=1){
                            return true;
                        }
                    }
                }else if(word1Count[i]>1){
                    for(int j=0;j<26;j++){
                        if(i!=j&&word1Count[j]>=1&&word2Count[i]>=1){
                            return true;
                        }
                        if(i!=j&&word2Count[j]>1&&word1Count[j]==0&&word2Count[i]==0){
                            return true;
                        }
                        if(i==j&&word2Count[j]>=1){
                            return true;
                        }
                    }
                }
            }
        }

        if(count1-count2==1){
            //Time : O(26^2)
            for(int i=0;i<26;i++){
                if(word1Count[i]==1){
                    for(int j=0;j<26;j++){
                        if(i!=j&&word2Count[i]>=1&&word2Count[j]>1&&word1Count[j]>=1){
                            return true;
                        }
                        if(i!=j&&word2Count[i]==0&&word2Count[j]==1&&word1Count[j]>=1){
                            return true;
                        }
                    }
                }else if(word1Count[i]>1){
                    for(int j=0;j<26;j++){
                        if(i!=j&&word2Count[i]==0&&word1Count[j]>=1&&word2Count[j]>1){
                            return true;
                        }
                    }
                }
            }
        }
        if(count1-count2==2){
            //Time : O(26^2)
            for(int i=0;i<26;i++){
                if(word1Count[i]==1){
                    for(int j=0;j<26;j++){
                        if(word2Count[i]==0&&word1Count[j]>=1&&word2Count[j]>1){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isItPossibleRefactor(String word1, String word2) {
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for(int i = 0; i < word1.length(); i++){
            char ch = word1.charAt(i);
            int idx = ch - 'a';
            arr1[idx] = arr1[idx] + 1;     // count the frequency of character in word1
        }
        for(int i = 0; i < word2.length(); i++){
            char ch = word2.charAt(i);
            int idx = ch - 'a';
            arr2[idx] = arr2[idx] + 1;  // count the frequency of character in word2
        }


        for(int i = 0; i < 26; i++){
            for(int j = 0; j < 26; j++){
                if(arr1[i] > 0 && arr2[j] > 0){                  // both indices should have frequency greater than 0 for swapping
                    arr1[j] = arr1[j] + 1;                        // take the one character of  word2 from arr2 and put into arr1
                    arr1[i] = arr1[i] - 1;                          // basically we are swapping here
                    arr2[i] = arr2[i] + 1;
                    arr2[j] = arr2[j] - 1;
                    int temp1 = 0;
                    int temp2 = 0;
                    for(int k = 0; k < 26; k++){
                        if(arr1[k] != 0){                                // here checking number of different characters
                            if(arr1[k] > 0)
                                temp1++;
                        }
                        if(arr2[k] != 0){
                            if(arr2[k] > 0)
                                temp2++;
                        }

                    }
                    if(temp1 == temp2){                // if number of distinct character equal then return true
                        return true;
                    }
                    arr1[j] = arr1[j] - 1;                          // in postorder we again put the same value of arr1 and arr2 because one swapping allowed only
                    arr1[i] = arr1[i] + 1;
                    arr2[i] = arr2[i] - 1;
                    arr2[j] = arr2[j] + 1;
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        //* Requirement
        //- Distinc char là số lượng character khác nhau ở mỗi string
        //* Return true nếu ta có thể thực hiện operation word1[i] with word2[j] và số lượng distinct giữa 2 string bằng nhau.
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //1 <= word1.length, word2.length <= 105
        //word1 and word2 consist of only lowercase English letters.
        //
        //- Brainstorm
        //- Nếu khoảng cách giữa 2 count distinct của 2 string >2 ==> Return false
        //- Để giảm count distint đi 1 đơn vị
        //
        //Count-1 - count-2==1
        //+ Giảm count-1 đi 1 đơn vị
        //+ Tăng count-2 lên 1 đơn vị
        //Ex : abb, cc
        //String-1:
        // ['a']=1
        // ['b']=2
        //String-2:
        // ['c']=1
        //
        //- Để 2 string có distinc bằng nhau ta có thể làm chỉ khi:
        //Ex:
        // "abcc", word2 = "aab"
        //- count-1 - count-2==1
        //String-1:
        //[a]=1
        //[b]=1
        //[c]=2
        //String-2:
        //[a]=2
        //[b]=1
        //- Dể giảm count của string-1:
        //==> Ta chỉ có thể giảm character có count=1 ==> Nhưng phải thuộc chuỗi kia (count[]>=1) +
        // (character lấy lại từ string-2 cũng phải thuộc chuỗi string-1 + khác chuỗi đang cần swap)
        //
        //- count-1 - count-2==2
        // "abccd", word2 = "aab"
        //String-1:
        //[a]=1
        //[b]=1
        //[c]=2
        //[d]=1
        //String-2:
        //[a]=2
        //[b]=1
        //- Dể giảm count của string-1 đi 1 và tăng string-2 lên 1:
        //==> Ta chỉ có thể giảm character có count=1 ==> Nhưng phải không thuộc chuỗi kia (count[]==0)
        //
        //- Bài này có rất nhiều cases:
        //- count2-count1==1
        //- Case 1:
        //+ count1=3 -1 = 2
        //+ count2=2 +0 = 2
        //- Case 2:
        //+ count1=3 +0 = 3
        //+ count2=2 +1 = 3
        //
        //- count2-count1==2
        //- Case 1:
        //+ count1=4 -1= 3
        //+ count2=2 +1= 3
        //==> Ở đây replace sẽ không cần điều kiện (i!=j) nữa --> Vì với điều kiện count đã có thì nó đã cover rồi.
        //
        //- count2-count1=0
        //- Case 1:
        //+ Replace không gây ra xáo trộn count của 2 string
        //Ex:
        //s=aaaaabb, count=2 +0 =2
        //t=aabb, count=2 +0 =2
        //s=bc, count=2 +0 =2
        //t=de, count=2 +0 =2
        //+ Replace gây ra xáo trộn count của 2 string mỗi cái (tăng lên/ giảm đi) 1
        //Ex:
        //s=bc, count=2 -1=1
        //t=cb, count=2 -1=1
        //
        //* Experience:
        //- Giả định testcase cần define sao cho giống code --> Để có thể copy ngay được thay vì dùng 1 variable khác có thể
        //gây nhầm lẫn
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space : O(1)
        //- Time : O(n+m)
        //
        //* Method-2:
        //2.0, Idea
        //- Ở đây độ phức tạp sẽ là O(26^3)
        //- Ta vẫn dùng count method --> Nhưng sẽ thêm 1 vòng for bên trong để thử swap các vị trí theo vòng lặp:
        //i : 0 -> 25
        //j : 0 -> 25
        //arr1[j] = arr1[j] + 1;
        //arr1[i] = arr1[i] - 1;
        //arr2[i] = arr2[i] + 1;
        //arr2[j] = arr2[j] - 1;
        //- Sau đó sẽ đếm xem count có giống nhau không ==> return true
        //- reset các giá trị thay đổi về giá trị cũ
        //
        //2.1, Optimization
        //2.2, Complexity:
        //- N is the length of String s
        //- M is the length of String t
        //- Space : O(26)
        //- Time : O(n+m)
        //
        //#Reference:
        //299. Bulls and Cows
        //1247. Minimum Swaps to Make Strings Equal
        //1941. Check if All Characters Have Equal Number of Occurrences
//        String s="ac";
//        String t="b";
//        String s="abcc";
//        String t="aab";
//        String s="abcde";
//        String t="fghij";
//        String s="aa";
//        String t="ab";
//        String s="a";
//        String t="bb";
//        String s="aa";
//        String t="bb";
//        String s="aa";
//        String t="bcd";
//        String s="aab";
//        String t="bccd";
//        String s="wilfuzpxqserkdcvbgajtyhon";
//        String t="rlmyvwvucqxsjodbelmgjkabnxegihuwats";
//        String s="fsaxghtafrelticalluhcmmkfesgsatkarduxttodjeqsajsiodfcktkqdbkubqedbrcnttfpghbkdtqfafhhciejqofaoecklfnccscdebobhpbghrdjtecsqnjdh";
//        String t="oitqsiubarfrimlpkskroohjiufsdhpcbpbqbrttsboganhsqqftfnucrsblounncdidtbruemlblqooiokqicpsgrbehikttskmhqqfcphqkhbojdphmtfsuufnrirfpikqdftr";
//        String s="aaccbb";
//        String t="cca";
//        String s="ff";
//        String t="f";
        String s="abc";
        String t="a";
        System.out.println(isItPossible(s, t));
        System.out.println(isItPossibleOptimization(s, t));
    }
}
