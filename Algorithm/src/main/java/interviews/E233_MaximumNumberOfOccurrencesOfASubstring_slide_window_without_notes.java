package interviews;

import java.util.HashMap;
import java.util.HashSet;

public class E233_MaximumNumberOfOccurrencesOfASubstring_slide_window_without_notes {

    public static int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int start=0;
        int end=0;
        int n=s.length();
        HashMap<Character, Integer> countChars=new HashMap<>();
        HashMap<String, Integer> countStr=new HashMap<>();
        int rsCount=0;

        for(int length=minSize;length<=maxSize;length++){
            for(int i=0;i<n;i++){
                char currentChar=s.charAt(i);
                if(!countChars.containsKey(currentChar)){
                    countChars.put(currentChar, 1);
                }else{
                    countChars.put(currentChar, countChars.get(currentChar)+1);
                }
//                System.out.println(countChars);
                end++;
//                System.out.printf("%s %s\n", i, length);


                while (end<=n&&end>=start&&end-start>length){
                    char c=s.charAt(start);

                    if(countChars.get(c)==1){
                        countChars.remove(c);
                    }else{
                        countChars.put(c, countChars.get(c)-1);
                    }
                    start++;
                }
                if(end<=n&&end>=start&&countChars.size()<=maxLetters&&end-start>=minSize&&end-start<=maxSize){
//                    System.out.printf("Correction: %s start: %s, end %s\n",countChars, start, end);
                    String currentRsStr=s.substring(start, end);
//                    System.out.println(currentRsStr);

                    if(!countStr.containsKey(currentRsStr)){
                        rsCount=Math.max(rsCount, 1);
                        countStr.put(currentRsStr, 1);
                    }else{
                        int newCount=countStr.get(currentRsStr)+1;
                        rsCount=Math.max(newCount, rsCount);
                        countStr.put(currentRsStr, newCount);
                    }
                }
            }
        }


        return rsCount;
    }

    public static void main(String[] args) {
//        String s = "aababcaab";
//        int maxLetters = 2, minSize = 3, maxSize = 4;
        String s = "aaaa";
        int maxLetters = 1, minSize = 3, maxSize = 3;
        System.out.println(maxFreq(s, maxLetters, minSize, maxSize));
    }
}
