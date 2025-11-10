package E1_daily;

import java.util.HashSet;

public class E341_UsingARobotToPrintTheLexicographicallySmallestString {

    public static String robotWithString(String s) {
        int n=s.length();
        boolean[] exists=new boolean[26];

        for (int i = 0; i < n; i++) {
            exists[s.charAt(i)-'a']=true;
        }
        int[] minCharSuffix=new int[n];
        int minChar=Integer.MAX_VALUE;

        for (int i = n-1; i >=0; i--) {
            minChar=Math.min(minChar, s.charAt(i)-'a');
            minCharSuffix[i]=minChar;
        }
        int j=0;
        StringBuilder rs=new StringBuilder();
        boolean[] visited=new boolean[n];

        for(int i=0;i<26;i++){
            if(!exists[i]) {
                continue;
            }
            int k=-1;
            //bba
            int prevJ=j;
            for(;j<n;j++){
                if(s.charAt(j)-'a'==i&&!visited[j]){
                    rs.append(s.charAt(j));
                    k=j;
                    visited[j]=true;
                }
            }
            if(k!=-1){
                j=k;
            }else{
                j=prevJ;
            }
            int l=j-1;
            //Duplicate char
            //Make sure this char < less than all character on the right
            //b(a)c
            //b<c ==> append(b) immediately
            if(j < n && l>=0 && (j+1==n|| s.charAt(l)-'a' <= minCharSuffix[j+1])){
                while(l < n && l >= 0 && !visited[l] && (j+1==n||s.charAt(l)-'a' <= minCharSuffix[j+1])) {
                    rs.append(s.charAt(l));
                    visited[l] = true;
                    l--;
                }
            }
            while (j>=0&&j<n&&visited[j]){
                j--;
            }
        }
        for(int i=n-1;i>=0;i--){
            if(!visited[i]){
                rs.append(s.charAt(i));
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a string s) and (a robot) that currently holds (an empty string t).
        //- Apply one of the following operations until s and t are both empty:
        //  + Remove the first character of a string s and give it to the robot.
        //      The robot will append this character to (the string t).
        //  + Remove the last character of a string t and give it to the robot.
        //      The robot will write this character (on paper).
        //* Return (the lexicographically smallest string) that can be written on the paper.
        //
        //Example 1:
        //
        //Input: s = "zza"
        //Output: "azz"
        //Explanation: Let p denote the written string.
        //Initially p="", s="zza", t="".
        //Perform first operation three times p="", s="", t="zza". ==> Copy s to t
        //Perform second operation three times p="azz", s="", t="". ==> Reverse substring of t
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= s.length <= 10^5
        //s consists of only English lowercase letters.
        //  + s.length <= 10^5 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //Ex:
        //s = abc
        //t:
        //p:
        //  + p1(a,b):
        //      + s=c
        //      + t=ab
        //      + p
        //  + p2(a):
        //      + s=c
        //      + t=a
        //      + p=b
        //  + p1(c):
        //      + s=
        //      + t=ac
        //      + p=b
        //  + p2(c):
        //      + s=
        //      + t=
        //      + p=bca
        //
        //- We need to remove (any character) at the middle and then appending them to (the last) of the paper
        //  + When we add them to (the t string)
        //  ==> That mean we move this character to the "FIRST" of (the rest chars)
        //- We choose the smallest character
        //Ex:
        //s=bcd(a)dec(a)bc
        //==> aabc|ceddcb
        //  + We need to add all of "a" characters
        //
        //Ex:
        //s=bcd(a)dec(a)(b)c(b)ed
        //
        //- Start from (a -> z)
        //
        //1.1, Case
        //
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(1)
//        String s = "bdda";
//        String s = "bac";
//        String s = "bydizfve";
        //Case này bị:
        //ofn ==> Thay vì add thêm p
        //  + ta sẽ add(o->h) (Back lại)
        String s = "vzhofnpo";
        //Wrong:  fnopohzv
        //Result: fnohopzv
        //
        System.out.println(robotWithString(s));
    }
}
