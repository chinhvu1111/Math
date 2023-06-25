package interviews.vng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class E2_CurrencyExchangeProblem {

    public static int solution(String value, String currencyChanges, String bankCurrencies){
        String currencyChangeArr[]=currencyChanges.split(",");
        HashMap<String, Integer> valueChanges=new HashMap<>();
        for(String s:currencyChangeArr){
            String[] sV=s.split(":");
            String nameBank=sV[0];
            Integer v=Integer.valueOf(sV[1]);
            valueChanges.put(nameBank, v);
        }
        List<Integer> values=new ArrayList<>();
        String bankCurrenciesArr[]=bankCurrencies.split(",");
        for(String s:bankCurrenciesArr){
            HashMap<Integer, Integer> mapCount=new HashMap<>();
            String s1[]=s.split(":");
            String nameBank=s1[0];
            int valueChange=valueChanges.get(nameBank);

            for(int i=1;i<s1.length;i++){
                Integer currentVal=Integer.parseInt(s1[i])*valueChange;
                Integer currentCount=mapCount.get(currentVal);
//                if(currentCount==null){
//                    values.add(currentVal);
//                    mapCount.put(currentVal, 1);
//                }else{
//                    values.add(currentVal*(currentCount+1));
//                    mapCount.put(currentVal, currentCount+1);
//                }
                values.add(currentVal*(currentCount+1));
            }
        }
        System.out.println(values);
        int valueMoney=Integer.parseInt(value);
        int n=values.size();
        int[][] dp=new int[values.size()+1][valueMoney+1];
        for(int i=0;i<n+1;i++){
            dp[i][0]=1;
        }
//        HashSet<Integer> hashSet=new HashSet<>();
        for(int i=1;i<n+1; i++)
        {
//            if(hashSet.contains(values.get(i-1))){
//                continue;
//            }
//            hashSet.add(values.get(i-1));
            for(int j=0;j<valueMoney+1; j++)
            {
                if(values.get(i-1) <= j)
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-values.get(i-1)];
                else dp[i][j]= dp[i-1][j];
//                System.out.printf("%s %s %s\n", i, j, dp[i][j]);
            }
        }
        System.out.printf("%s %s %s\n", n, valueMoney, dp[n][valueMoney]);
        return dp[n][valueMoney];
    }
    public static void main(String[] args) {
        //** Requirement
        //- Tính số cách chuyển đổi để có value money
        //VD:
        //Using 3 bank notes value 5 of C1, 1 bank note value 10 of C1 and 1 bank note value 50 of C2. Total value is (5 * 3 + 10) * 2 + 50 * 1 = 100
        // (5*3+10)*2+50*1=100
        //
        //** Idea
        //VD:
        //- Constraint:
        //+ C1:...., C2:...
        //+ Nhiều value thì sao : <10^4
        //+ Nhiều banks thì sao: <10^4
        //+ C1:2 : Tỉ lệ chuyển đổi
        //
        //- Quy hoạch động
        //+ dp[i] là số lượng cách có thể để có (i) value
        //dp[i] = dp[i-j] + dp[j]
        //
        //100
        // C1:2,C2:1,C3:3
        // C1:1:2:5:5:5:10,C2:20:50
        //-->
        //C1:2:4:10:10:10:20, C2:20:50
        //-->
        //C1:2:4:10(3):20, C2:20:50
        //- Có 3 số 10 --> Có cách nào tách ra dùng được không.
        //--> Chia theo index là được.
        String value="100";
        String s="C1:2,C2:1,C3:3";
        String s1="C1:1:2:5:5:5:10,C2:20:50";
        System.out.println(solution(value, s, s1));
    }
}
