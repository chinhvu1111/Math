package contest;

import javafx.util.Pair;

import java.util.*;

public class E236_MaximizeAmountAfterTwoDaysOfConversions {

    public static HashMap<String, Double> getMaxValue(int n, List<List<String>> pairs1, double[] rates1, String init, double val){
        HashMap<String, Double> convertValue=new HashMap<>();
        Queue<Pair<String, Double>> queue=new LinkedList<>();
        HashSet<String> visited=new HashSet<>();
        queue.add(new Pair<>(init, val));
//        visited.add(init);

        while (!queue.isEmpty()){
            Pair<String, Double> curNode=queue.poll();
            visited.add(curNode.getKey());
            convertValue.put(curNode.getKey(), curNode.getValue());
            for(int i=0;i<n;i++){
                if(!visited.contains(pairs1.get(i).get(1))&&curNode.getKey().equals(pairs1.get(i).get(0))){
                    queue.add(new Pair<>(pairs1.get(i).get(1), curNode.getValue()*rates1[i]));
                }
                if(!visited.contains(pairs1.get(i).get(0))&&curNode.getKey().equals(pairs1.get(i).get(1))){
                    queue.add(new Pair<>(pairs1.get(i).get(0), curNode.getValue()/rates1[i]));
                }
            }
        }
        return convertValue;
    }

    public static double maxAmount(
            String initialCurrency, List<List<String>> pairs1,
            double[] rates1, List<List<String>> pairs2, double[] rates2) {

        int n=pairs1.size();
        HashMap<String, Double> mapValue1 = getMaxValue(n, pairs1, rates1, initialCurrency, 1);
        double rs=0;

        for(Map.Entry<String, Double> e: mapValue1.entrySet()){
            HashMap<String, Double> mapValue2 = getMaxValue(rates2.length, pairs2, rates2, e.getKey(), e.getValue());
//            System.out.printf("Init: %s\n", e);
//            System.out.println(mapValue2);
            if(mapValue2.containsKey(initialCurrency)){
                rs=Math.max(rs, mapValue2.get(initialCurrency));
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string initialCurrency, and you start with (1.0 of initialCurrency).
        //- You are also (given four arrays) with currency pairs (strings) and rates (real numbers):
        //  + pairs1[i] = [startCurrencyi, targetCurrencyi] denotes that you can convert from (startCurrencyi to targetCurrencyi)
        //  at a rate of rates1[i] on day 1.
        //  + pairs2[i] = [startCurrencyi, targetCurrencyi] denotes that you can convert from (startCurrencyi to targetCurrencyi)
        //  at a rate of rates2[i] on day 2.
        //- Also, (each targetCurrency) can be converted back to (its corresponding startCurrency) at a rate of (1 / rate).
        //- You can perform any number of conversions, including zero, using rates1 on day 1,
        // followed by any number of additional conversions, including zero, using rates2 on day 2.
        //* Return (the maximum amount of "initialCurrency") you can have after performing (any number of conversions) on both days (in order).
        //  ==> Lấy về (same "initialCurrency")
        //
        //- Note: Conversion rates are valid, and there will be no contradictions in the rates for either day.
        // The rates for the days are independent of each other.
        //
        //Example 1:
        //
        //Input:
        // initialCurrency = "EUR",
        // pairs1 = [["EUR","USD"],["USD","JPY"]],
        //  + rates1 = [2.0,3.0],
        // pairs2 = [["JPY","USD"],["USD","CHF"],["CHF","EUR"]],
        //  + rates2 = [4.0,5.0,6.0]
        //
        //Output: 720.00000
        //
        //Explanation:
        //To get the maximum amount of EUR, starting with 1.0 EUR:
        //
        //On Day 1:
        //Convert EUR to USD to get 2.0 USD.
        //  + 1 -> 2
        //Convert USD to JPY to get 6.0 JPY.
        //  + 1 -> 3
        //On Day 2:
        //Convert JPY to USD to get 24.0 USD.
        //Convert USD to CHF to get 120.0 CHF.
        //Finally, convert CHF to EUR to get 720.0 EUR.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= initialCurrency.length <= 3
        //initialCurrency consists only of uppercase English letters.
        //1 <= n == pairs1.length <= 10
        //1 <= m == pairs2.length <= 10
        //pairs1[i] == [startCurrencyi, targetCurrencyi]
        //pairs2[i] == [startCurrencyi, targetCurrencyi]
        //1 <= startCurrencyi.length, targetCurrencyi.length <= 3
        //startCurrencyi and targetCurrencyi consist only of uppercase English letters.
        //rates1.length == n
        //rates2.length == m
        //1.0 <= rates1[i], rates2[i] <= 10.0
        //  + rates[i] >= 1
        //  ==> multiply that (always be increased).
        //The input is generated such that there are no contradictions or cycles in the conversion graphs for either day.
        //
        //- Brainstorm
        //- EURO -> [USD,...]
        //- USD -> [JPY,...]
        //- We need to perform all of transition?
        //  + No
        //- We can perform the transition because:
        //  + On the first day, the price is better than the second day
        //      + The second day, we can convert back to the (initialCurrency)
        //- The calculation on the same day:
        //  + This is straight forward
        //
        //
        String initialCurrency = "EUR";
        String[][] pairs1 = {{"EUR","USD"},{"USD","JPY"}};
        double[] rates1 = {2.0,3.0};
        String[][] pairs2 = {{"JPY","USD"},{"USD","CHF"},{"CHF","EUR"}};
        double[] rates2 = {4.0,5.0,6.0};
        List<List<String>> pairs1List=new ArrayList<>();
        for(String[] e: pairs1){
            pairs1List.add(Arrays.asList(e));
        }
        List<List<String>> pairs2List=new ArrayList<>();
        for(String[] e: pairs2){
            pairs2List.add(Arrays.asList(e));
        }
        System.out.println(maxAmount(initialCurrency, pairs1List, rates1, pairs2List, rates2));
    }
}
