package contest;

import javafx.util.Pair;

import java.util.*;

public class E60_MinimumDeletionsToMakeStringKSpecial {

    public static int wrongSolution(int[] countArr, TreeSet<Integer> treeSet,
                               HashMap<Integer, Integer> countVal, int k){
        if(treeSet.isEmpty()){
           return 0;
        }
        int curRs=Integer.MAX_VALUE;
        if(treeSet.first()-treeSet.last()>k){
            int first=treeSet.first();
            int last=treeSet.last();
            treeSet.remove(first);

            int countFirst=countVal.get(first);
            if(countFirst>1){
                countVal.put(first, countFirst-1);
            }else{
                countVal.remove(first);
            }
            curRs=Math.min(curRs, wrongSolution(countArr, treeSet, countVal, k));
            treeSet.add(first);
            countVal.put(first, countFirst);
            int countLast=countVal.get(last);
            if(countLast>1){
                countVal.put(last, countLast-1);
            }else{
                countVal.remove(last);
            }
            curRs=Math.min(curRs, wrongSolution(countArr, treeSet, countVal, k));
            treeSet.add(last);
            countVal.put(last, countLast);
        }
        return curRs;
    }

    public static int minimumDeletionsStupid(String word, int k) {
        int[] count=new int[26];
        int n=word.length();

        for(int i=0;i<n;i++){
            count[word.charAt(i)-'a']++;
        }
        List<Integer> countList=new ArrayList<>();

        for(int i=0;i<26;i++){
            if(count[i]!=0){
                countList.add(count[i]);
            }
        }
        Collections.sort(countList);
        int[] countArr=new int[countList.size()];
        HashMap<Integer, Integer> countVal=new HashMap<>();

        for(int i=0;i<countList.size();i++){
            countArr[i]=countList.get(i);
            countVal.put(countArr[i], countVal.getOrDefault(countArr[i], 0)+1);
        }
        int i=0, j=countList.size()-1;
        int countLeft=1;
        int countRight=1;
        boolean isDone=false;
        int rs=0;
        System.out.println(countList);

        while(i<j&&!isDone){
            int left=countList.get(i);
            int right=countList.get(j);
            boolean leftDone=false;
            boolean rightDone=false;

            if(right-left<=k){
                break;
            }
            int nextLeft=countList.get(i+1);
            int leftVal=Integer.MAX_VALUE;
            //1,2,7, k=3
            //1,6,7
            if(right-k<=nextLeft){
                leftVal=countLeft*(right-k-left+1);
                leftDone=true;
            }else{
                leftVal=countLeft*(nextLeft-left);
            }
            int nextRight=countList.get(j-1);
            int rightVal=Integer.MAX_VALUE;
//            System.out.printf("%s %s\n", left + k, nextRight);
            if(left+k>=nextRight){
                rightVal=countRight*(right-(left+k));
                rightDone=true;
            }else{
                rightVal=countRight*(right-nextRight);
            }
            if(leftVal<rightVal){
                rs+=leftVal;
                i++;
                countLeft++;
                isDone=leftDone;
            }else{
                rs+=rightVal;
                j--;
                countRight++;
                isDone=rightDone;
            }
            System.out.printf("Left: %s, Right: %s, rs: %s, leftVal: %s, rightVal : %s, isDone: %s\n", left, right, rs, leftVal, rightVal, isDone);
        }
        return rs;
    }

    public static int minimumDeletions(String word, int k) {
        int[] count=new int[26];
        int n=word.length();

        for(int i=0;i<n;i++){
            count[word.charAt(i)-'a']++;
        }
        List<Integer> countList=new ArrayList<>();

        for(int i=0;i<26;i++){
            if(count[i]!=0){
                countList.add(count[i]);
            }
        }
        if(countList.size()==1){
            return 0;
        }
        Collections.sort(countList);
        int[] countArr=new int[countList.size()];
        TreeSet<Pair<Integer, Integer>> setSort=new TreeSet<>((a, b) ->{
            return a.getKey()-b.getKey();
        });

        for(int i=0;i<countList.size();i++){
            countArr[i]=countList.get(i);
            Pair<Integer, Integer> curPair = new Pair(countArr[i], i);
            setSort.add(curPair);
        }
        int size=countList.size();
        int last=countList.get(size-1);
        if(last<=k){
            return 0;
        }
        int counter=1;
        int[] rightRs=new int[size];
        int[] rightDeleted=new int[size];
        int finalRs=Integer.MAX_VALUE;
        int right=last;

        System.out.println(countList);
        for(int i=size-2;i>=0;i--){
            rightRs[i]=counter*(countList.get(i+1)-countList.get(i)) + rightRs[i+1];
            counter++;
        }
        for(int i=size-2;i>=0;i--){
            //(right-k), [i], last
            if(countList.get(i)+k<=right){
                Pair<Integer, Integer> curPair=new Pair<>(countList.get(i)+k, -1);
                curPair=setSort.higher(curPair);
                int curCount=size-curPair.getValue();
                int curVal = curCount * (curPair.getKey() - countList.get(i) - k) + rightRs[curPair.getValue()];
                right=countList.get(i)+k;
                rightDeleted[i]=curVal;
            }else{
                rightDeleted[i]=rightDeleted[i+1];
            }
            counter++;
        }
//        finalRs=Math.min(rs, finalRs);
//        System.out.println(rs);
        int deleteLeft=0;

        for(int i=0;i<size-1;i++){
            deleteLeft+=countList.get(i);
            if(rightDeleted[i+1]!=Integer.MAX_VALUE){
                finalRs=Math.min(finalRs, deleteLeft+rightDeleted[i+1]);
            }
        }
        //Case mà không cần delete
        finalRs=Math.min(finalRs, rightDeleted[0]);
//        System.out.println(finalRs);
        return finalRs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string (word) and an integer (k).
        //- We consider word to be k-special if |freq(word[i]) - freq(word[j])| <= k
        //for all indices i and j in the string.
        //- Here, freq(x) denotes the frequency of the character x in word,
        //and |y| denotes the absolute value of y.
        //* Return the (minimum number of characters) you need to (delete) to make word k-special.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= word.length <= 10^5
        //0 <= k <= 10^5
        //word consists only of lowercase English letters.
        //--> Time : O(n)/ O(n*log(n))
        //- K khá lớn : Không thể mỗi recursion - 1 được
        //
        //- Brainstorm
        //Ex:
        //word = "dabdcbdcdcd", k = 2
        //count[a]=1
        //count[b]=2
        //count[c]=3
        //count[d]=5
        //--> 5 - 1 = 4
        // ==> Cần giảm 2 ở 5 / tăng 1 lên 2
        //
        //Ex:
        //k = 2
        //count[a]=1
        //count[b]=2
        //count[c]=5
        //count[d]=7
        //- Chỉ giảm right
        //1,5,7
        //-> Giảm 7 đi 4 : count=4
        //1,5,3 => 1,3,5
        //-> Giảm 5 đi 2 : count=6
        //=> 1,3,3
        //+ rs = 6
        //
        //- Chỉ tăng left
        //1,5,7
        //-> Tăng 1 lên 4 : count=4
        //5,5,7
        //+ rs = 4
        //- Với case này thì nếu xoá left và right:
        //1,5,7
        //-> 7 - 2 = 5
        //1,5,5
        //-> 1 + 2 = 3
        //3,5,5
        //+ rs=4
        //- Tăng left khi ít phần tử sát left
        //- Giảm right khi ít phần tử sát right
        //
        //Ex:
        //k = 2
        //count[a]=1
        //count[b]=2
        //count[c]=5
        //count[d]=7
        //1,2,5,7
        //-> 1+1, 7-2 => count=3
        //2,2,5,5
        //- Lowercase nên chỉ có thể traverse recursively theo character được.
        //Ex:
        //1,2,5,7, k=2
        //- Nếu chỉ xét 1 đôi (1,7):
        //  + Tăng 1 or giảm 7 thì coi là như nhau
        //- Xét dạng 3 elements:
        //1,3,7, k=2
        //- Kết quả tối ưu nhất khi:
        //  + 1 tăng và 7 giảm sao cho xét đến 3 thì ta không cần move gì hết
        //1 -> 3 : count+=2
        //7 --> 5 : count+=2
        //-> count=4
        //- Cùng số lượng move của 1 và 7 ta sẽ move sao cho 3 cũng sẽ move ít nhất
        //+ 1 --> 5 : count+=4
        //-> 3,5,7 : Ta cần phải move tiếp 3
        //+ 3 --> 5 => 5,5,7 => count+=2
        //-> count=6 (Chưa tối ưu)
        //* Ta thấy rằng nếu tăng 1 lên >=3 để <> với 7
        //  + Thì để đúng thì ta cần phải tăng cả 3 nữa
        // + 1 -> 3 : count+=2 : 3,3,7
        // + Nếu tăng 3 -> 5 ==> ta phải tăng 2 lần 2*2 cho 2 số
        //  -> Giảm 7 -> 5 : count+=2
        //Ex:
        //1,2,5,7, k=2
        //- Cách 1 : Apply idea bên trên
        //+ 1 -> 2 : count+=1
        // + 2,2,5,7
        //+ 7 -> 5 : count+=2
        // + 2,2,5,5
        //+ 2 -> 3 : count+=2
        //-> count = 5
        //Ex: Tổng quát
        //a,b,c
        //+ b gần a
        //  Ex: 1,2,7, k=3
        //  + Dù a tăng lên > b, c-a>=k ==> a cần tăng > b (kéo theo b)
        //  7 -> 4 : count+=3
        //  + a tăng đến <b ==> đã thoả mãn c-a<=k ==> Cái này thì b luôn đúng
        //+ b gần c
        //  Ex:
        //  1,6,7, k=3
        //  1 -> 4 : count+=3
        //  + Ưu tiên tăng thằng cách thằng tiếp theo xa hơn
        //Ex:
        //1,2,5,7, k=3 (Để làm sao để 2 -> 5 vừa đủ 3)
        //- 1 -> 2: 2,2,5,7
        //  + count+=1
        //- 2,2,5,7
        //  2 options:
        //  + (2,2) -> 4 (min của (max-k=7-3) hiện tại và next num) : count+=2*2
        //  + 7 -> 5 : count += 2
        //Ex:
        //1,2,5,7
        //** NGU VÃI:
        //- Ở đây là DELETE --> Chỉ có thể xoá ==> Chỉ có thể giảm right
        //==> left không cần giảm vì giảm chỉ làm kết quả tệ đi.
        //
        //[1, 4, 6], k=1
        //[0, 0, 0]
        //-> 6 -> 5
        //[1, 4, 1]
        //- Bài toán trở thành:
        //  + Với mỗi (i) là min ==> nếu xoá (n-1) --> i để all subtraction <= k
        //  ==> right[i] sẽ lưu kết quả
        //  + left(i) sẽ là value nếu xoá từ (0 -> i)
        //
        //[1, 2, 3, 5], k=2
        // 5 -> 3 : (3+k)>=5
        //  + right = 5
        // 5 -> 2 : (2 + k < right)
        //  + right= 2 + k = 4
        //==> Ta thấy 2 + k có thể nằm ở index nào đó trong khoảng (2, 5)
        //  + Nếu ta muốn tìm thì chỉ có thể dùng binary search
        //[1, 2, 3, 5], k=2
        //[0, 0, 0, 0]: count=1
        //[0, 0, 2, 0]: count=2
        //[0, 4, 2, 0]: count=3
        //[7, 4, 2, 0]: count=7
        //
//        String word = "dabdcbdcdcd";
//        int k = 2;
//        String word = "aabcaba";
//        int k = 0;
        //
//        String word = "aaabaaa";
//        int k = 2;
//        String word = "inn";
//        int k = 3;
//        String word = "ahahnhahhah";
//        int k = 1;
//        String word = "a";
//        int k = 0;
//        String word = "qqb";
//        int k = 1;
        String word = "vvnowvov";
        int k = 2;
//        System.out.println(minimumDeletionsStupid(word, k));
        System.out.println(minimumDeletions(word, k));
    }
}
