package interviews;

import java.util.*;

public class E64_LetterCombinationsOfAPhoneNumber {

    public static List<String> rs;
    public static String[] map;

    public static void execute(int index, String digits, String str){

        if(index>digits.length()-1){
            rs.add(str);
            return;
        }
//        System.out.println("==="+index);
//        System.out.println(digits+" "+(digits.charAt(index)-'0'));
        String currentString=map[digits.charAt(index)-'0'];

        for(int i=0;i<currentString.length();i++){
            execute(index+1, digits, str+currentString.charAt(i));
        }
    }

    public static List<String> letterCombinations(String digits) {
        map=new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        rs=new ArrayList<>();
        if("".equals(digits)){
            return rs;
        }
        execute(0, digits, "");
        return rs;
    }

    public static List<String> letterCombinationsOptimized(String digits) {
        map=new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> rs=new ArrayList<>();
        if("".equals(digits)){
            return rs;
        }
        Queue<String> temp=new LinkedList<>();
        temp.add("");

        while (!temp.isEmpty()){
            String currentStr=temp.poll();

            if(currentStr.length()==digits.length()){
                rs.add(currentStr);
            }else{
                String nextStr=map[digits.charAt(currentStr.length())-'0'];

                for(int i=0;i<nextStr.length();i++){
                    temp.add(currentStr+nextStr.charAt(i));
                }
            }
        }
        return rs;
    }

    public List<String> letterCombinationsOptimized1(String digits) {
        Queue<String> cache = new LinkedList<>();
        Map<Character, List<String>> map = new HashMap<>();
        populateMap(map);

        //Traverse size of input string (input numbers)
        for(char curr : digits.toCharArray()){
            List<String> mappedChars = map.get(curr);

            //If our Queue is empty (first time only), add each letter associated with the first number
            if(cache.isEmpty()){
                for(String str : mappedChars){
                    cache.add(str);
                }
            } else {
                //Size of cache - each combination up until this point
                int size = cache.size();
                for(int i = 0; i < size; i++){
                    //Poll the Queue for the head of the list
                    String currStr = cache.poll();
                    //Add back the head of the queue with newly appended letter
                    for(String str : mappedChars){
                        cache.add(currStr + str);
                    }
                }
            }
        }

        return new ArrayList(cache);
    }

    private void populateMap(Map<Character, List<String>> map){
        map.put('2', Arrays.asList("a", "b", "c"));
        map.put('3', Arrays.asList("d", "e", "f"));
        map.put('4', Arrays.asList("g", "h", "i"));
        map.put('5', Arrays.asList("j", "k", "l"));
        map.put('6', Arrays.asList("m", "n", "o"));
        map.put('7', Arrays.asList("p", "q", "r", "s"));
        map.put('8', Arrays.asList("t", "u", "v"));
        map.put('9', Arrays.asList("w", "x", "y", "z"));
    }

    public static void main(String[] args) {
        String digits="23";
//        String digits="";
        List<String> rs=letterCombinations(digits);
        System.out.println(rs);
        System.out.println(letterCombinationsOptimized(digits));
        //B??i n??y ta c?? nh???ng c??ch t?? duy nh?? sau:
        //C??ch 1:
        //1, C??ch n??y d??ng recursion t?? duy ch???n c??ch b??nh th?????ng v???i:
        //+ index+1
        //+ return list khi t??m ???????c k???t qu???
        //2, C??ch th??? 2 d??ng queue:
        //2.1, H???u h???t c??c b??i ????? quy ch???n tr?????ng h???p (index++ t??? t??ng) --> C?? th??? chuy???n v??? queue
        //2.2, C??ch t?? duy nh?? sau:
        //VD: digits="23" bao g???m ("abc", "def")
        //2.3, C??c b?????c nh?? sau:
        //Add all l???n c??c ph???n t??? c???a "abc"
        //* index=0
        //a
        //b
        //c
        //* index=1
        //Sau ???? s??? pop(a)
        //+ index= length( a )
        //L???y a gh??p v???i c??c ph???n t??? c???a chu???i th??? 2
        //b
        //c
        //ad
        //ae
        //af
        //---> C??? th??? l???n l?????t ta s??? l???y ???????c h???t c??c tr?????ng h???p c?? th???.
    }
}
