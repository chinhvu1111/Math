/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/**
 *
 * @author chinhvu
 */
public class Abreviation {

    public static String resultNo = "NO";
    public static String resultYes = "YES";

    public static String abbreviation(String a, String b) {
        // Write your code here
         int n=a.length();
         int m=b.length();

         boolean dp[][]=new boolean[m+1][n+1];

         for(int i=0;i<=m;i++){
             int count=0;

             for(int j=0;j<=n;j++){
                 
                 boolean isLowerChar=false;
                 if(j>0){
                     isLowerChar=a.charAt(j-1)>='a'
                         &&a.charAt(j-1)<='z';
                 }else{
                     isLowerChar=(j==0);
                 }
                 if(isLowerChar&&i==0){
                     dp[i][j]=true;
                     continue;
                 }
                 if(i==0||j==0){
                     continue;
                 }
//                 int value=b.charAt(i-1)-a.charAt(j-1);
                 String s=String.valueOf(b.charAt(i-1));
                 String s1=String.valueOf(a.charAt(j-1));
                 String s2=s1.toUpperCase();

                 if(s1.equals(s)){
                     if(!dp[i][j]){
                         dp[i][j]=dp[i-1][j-1];
                     }
                     count++;
//                     dp[i][j]=dp[i-1][j-1];
                 }else if(s.equals(s2)){
                     if(!dp[i][j]){
                         dp[i][j]=dp[i-1][j-1]|dp[i][j-1];
                     }
                     count++;
                 }
                 else if(count>=1&&isLowerChar){
                     dp[i][j]=dp[i][j-1];
                 }
             }
         }

//         int c=0;
//         boolean mark[]=new boolean[m+1];
//
//         for(int i=1;i<=n;i++){
//             for(int j=1;j<=m;j++){
//                 if(!mark[j]&&dp[j][i]&&a.charAt(i-1)==b.charAt(j-1)){
//                     mark[j]=true;
//                     continue;
//                 }
//                 if(dp[j][i]){
//                     c++;
//                 }
//             }
//         }
//        System.out.println(c);
        int numberUpper=0;
        for(int i=0;i<n;i++){
            if(a.charAt(i)>='A'&&a.charAt(i)<='Z'){
                numberUpper++;
            }
        }
        if(dp[m][n]&&numberUpper<=b.length()){
            return resultYes;
        }
        return resultNo;
//        System.out.println('A'-'a');
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/home/chinhvu/NetBeansProjects/Algorithm/src/algorithm/input"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String a = bufferedReader.readLine();

                String b = bufferedReader.readLine();

                String result = abbreviation(a, b);
                System.out.println(result);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
