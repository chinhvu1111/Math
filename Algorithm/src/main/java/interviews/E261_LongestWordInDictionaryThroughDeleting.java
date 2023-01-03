package interviews;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class E261_LongestWordInDictionaryThroughDeleting {

    public static String findLongestWord(String s, List<String> dictionary) {
        int m=s.length();
        TreeSet<String> rsList=new TreeSet<>();
        int rsLength=0;

        for (String value : dictionary) {
            int index = 0;
            int l = value.length();

            if (l > m) {
                continue;
            }
            for (int j = 0; j < m&&index<l; j++) {
                if (value.charAt(index) == s.charAt(j)) {
                    index++;
                }
            }
            if (rsLength <= l && index == l) {
                if (rsLength == l) {
                    rsList.add(value);
                } else {
                    rsList.clear();
                    rsList.add(value);
                }
                rsLength = l;
            }
        }
        Iterator<String> iter = rsList.iterator();
        if(iter.hasNext()){
            return iter.next();
        }
        return "";
    }

    public static void main(String[] args) {
        //#Reference
        //525. Contiguous Array
        //2281. Sum of Total Strength of Wizards
        //161. One Edit Distance
        //2423. Remove Letter To Equalize Frequency
    }
}
