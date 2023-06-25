package E1_Graph;

import java.util.*;

public class E12_WordsWithinTwoEditsOfDictionary {
    public static List<String> twoEditWords(String[] queries, String[] dictionary) {
        int n=queries.length;
        if(n==0){
            return new ArrayList<>();
        }
        Set<String> targetString = new HashSet<>();

        for(String target:dictionary){
            char[] targetArr=target.toCharArray();
            int l=targetArr.length;

            for(int j=0;j<l;j++){
                char tempInit=targetArr[j];
                targetArr[j]='*';
                targetString.add(String.valueOf(targetArr));
                for(int h=j+1;h<l;h++){
                    char temp=targetArr[h];
                    targetArr[h]='*';
                    targetString.add(String.valueOf(targetArr));
                    targetArr[h]=temp;
                }
                targetArr[j]=tempInit;
            }
        }
//        System.out.println(targetString);
        int m=queries[0].length();
        char[][] querieArr=new char[m][n];
        List<String> result=new ArrayList<>();

        for(String query: queries){
            char[] currentQuerieArr=query.toCharArray();
            int l=querieArr.length;
            boolean isFind=false;

            for(int j=0;j<l&&!isFind;j++){
                char tempInit=currentQuerieArr[j];
                currentQuerieArr[j]='*';
                String currentPattern=String.valueOf(currentQuerieArr);
                if(targetString.contains(currentPattern)){
//                        System.out.printf("%s %s\n", currentPattern, query);
                    result.add(query);
                    isFind=true;
                    continue;
                }
                for(int h=j+1;h<l;h++){
                    char temp=currentQuerieArr[h];
                    currentQuerieArr[h]='*';
                    currentPattern=String.valueOf(currentQuerieArr);
//                    System.out.println(currentPattern);
                    if(targetString.contains(currentPattern)){
//                        System.out.printf("%s %s\n", currentPattern, query);
                        result.add(query);
                        isFind=true;
                        break;
                    }
                    currentQuerieArr[h]=temp;
                }
                currentQuerieArr[j]=tempInit;
            }
        }
        return result;
    }

    public static List<String> twoEditWordsRefactor(String[] queries, String[] dictionary) {
        int n=queries.length;
        if(n==0){
            return new ArrayList<>();
        }
        Set<String> targetString = new HashSet<>();

        for(String target:dictionary){
            int l=target.length();

            for(int j=0;j<l;j++){
                String oneEditString=target.substring(0, j)+"*"+target.substring(j+1);
                targetString.add(oneEditString);

                for(int h=j+1;h<l;h++){
                    String currentString=oneEditString.substring(0, h)+"*"+oneEditString.substring(h+1);
                    targetString.add(currentString);
                }
            }
        }
//        System.out.println(targetString);
        List<String> result=new ArrayList<>();

        for(String query: queries){
            int l=query.length();

            if(targetString.contains(query)){
                result.add(query);
                continue;
            }
            boolean isFind=false;

            for(int j=0;j<l&&!isFind;j++){
                String currentPattern=query.substring(0, j)+"*"+query.substring(j+1);
                if(targetString.contains(currentPattern)){
//                        System.out.printf("%s %s\n", currentPattern, query);
                    result.add(query);
                    isFind=true;
                    continue;
                }
//                System.out.println(currentPattern);
                for(int h=j+1;h<l;h++){
                    String s=currentPattern.substring(0, h)+"*"+currentPattern.substring(h+1);
//                    System.out.println(s);
//                    System.out.println(currentPattern);
                    if(targetString.contains(s)){
//                        System.out.printf("%s %s\n", currentPattern, query);
                        result.add(query);
                        isFind=true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Liệt kê ra danh sách các words có thể biến đối --> Thành 1 word nào đó nằm trong set words còn lại
        //- Chỉ được phép biến đổi tối đa 2 edit trên 1 word.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint:
        //+ 1 <= queries.length, dictionary.length <= 100
        //+ n == queries[i].length == dictionary[j].length
        //+ 1 <= n <= 100
        //--> Giới hạn từ khá lớn <=100 : Tức là nếu sửa 2 characters thì sẽ mất O(n^2) để các cases cùng nhau.
        //
        //- Set để cache lại các string cần check
        //- For O(n^3) để check thử
        //
        //- Special cases:
        //- chỉ thay đổi 1 lần
        //
//        String[] queries = {"word","note","ants","wood"}, dictionary = {"wood","joke","moat"};
        String[] queries = {"a", "b", "c"}, dictionary = {"a"};
        System.out.println(twoEditWordsRefactor(queries, dictionary));
        //#Reference:
        //1. Two Sum
        //1960. Maximum Product of the Length of Two Palindromic Substrings
        //2151. Maximum Good People Based on Statements
        //1632. Rank Transform of a Matrix
    }
}
