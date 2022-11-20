package leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class E77_WordBreakII {

    public static List<String> wordBreak(String s, List<String> wordDict) {
        int n=s.length();
        List<String>[] listS=new ArrayList[n];

        for(int i=0;i<n;i++){
            listS[i]=new ArrayList<>();
        }
        HashSet<String> hashSetStr = new HashSet<>(wordDict);

        for(int i=0;i<n;i++){
            for(int j=i-1;j>=-1;j--){
                if( ((j==-1)||(listS[j].size()!=0))){
                    String remainString=s.substring(j+1, i+1);

                    if(!hashSetStr.contains(remainString)){
                        continue;
                    }
                    if(j!=-1){
                        for(String str: listS[j]){
                            listS[i].add(str+" "+remainString);
                        }
                    }else{
                        listS[i].add(remainString);
                    }
                }
            }
        }

        return listS[n-1];
    }

    public static void main(String[] args) {
//        String s="pineapplepenapple";
//        String[] wordDict=new String[]{"apple","pen","applepen","pine","pineapple"};

        String s="catsanddog";
        String[] wordDict=new String[]{"cat","cats","and","sand","dog"};

        System.out.println(wordBreak(s, Arrays.asList(wordDict)));
    }

}
