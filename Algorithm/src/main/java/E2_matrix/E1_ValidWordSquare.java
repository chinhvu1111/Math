package E2_matrix;

import java.util.Arrays;
import java.util.List;

public class E1_ValidWordSquare {

    public static boolean validWordSquare(List<String> words) {
        int m=words.size();
        int maxLength=0;

        for(String word:words){
            maxLength=Math.max(maxLength, word.length());
        }
        //Ex:
        //[
        // "abcd",
        // "bnrt",
        // "crmy",
        // "dtye"
        //]
        for(int i=0;i<maxLength;i++){
            StringBuilder curRs=new StringBuilder();

            for(int j=0;j<m;j++){
                if(i<words.get(j).length()){
                    curRs.append(words.get(j).charAt(i));
                }
            }
//            System.out.printf("index:%s, s: %s, s1: %s\n", i, curRs, words.get(i));
            if(!curRs.toString().equals(words.get(i))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an array of strings words
        //* Return true if it forms a (valid word square).
        //- Tức là row (ith) = column (ith) nếu ghép thành string.
        //+ Các (string có length khác nhau)
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= words.length <= 500
        //1 <= words[i].length <= 500
        //words[i] consists of only lowercase English letters.
        //
        //- Brainstorm
        //
        String[] s={"abcd","bnrt","crmy","dtye"};
        System.out.println(validWordSquare(Arrays.asList(s)));
        //#Reference:
        //425. Word Squares
        //766. Toeplitz Matrix
    }
}
