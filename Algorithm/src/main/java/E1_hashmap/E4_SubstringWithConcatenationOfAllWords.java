package E1_hashmap;

import java.util.*;

public class E4_SubstringWithConcatenationOfAllWords {

    public static List<Integer> findSubstring(String s, String[] words) {
        if(words.length==0){
            return new LinkedList<>();
        }
        HashMap<String, Integer> mapCount=new HashMap<>();
        int n=s.length();
        HashMap<String, Integer> mapCurrentWindow=new HashMap<>();
        Deque<String> queueWords=new LinkedList<>();
        for(String word:words){
            mapCount.put(word, mapCount.getOrDefault(word, 0)+1);
        }
        List<Integer> rs=new ArrayList<>();
        int lengthWord=words[0].length();

        for(int j=0;j<lengthWord;j++){
            for(int i=j;i+lengthWord<=n;i+=lengthWord){
                String currentStr=s.substring(i, i+lengthWord);
                System.out.println(currentStr);

                if(!mapCount.containsKey(currentStr)){
                    mapCurrentWindow.clear();
                    queueWords.clear();
                }else{
                    if(mapCurrentWindow.containsKey(currentStr)){
                        while(!queueWords.isEmpty()&&mapCurrentWindow.containsKey(currentStr)&&mapCurrentWindow.get(currentStr)>=mapCount.get(currentStr)){
                            String deletedWord=queueWords.removeFirst();
//                        System.out.printf("Remove: %s\n", deletedWord);
                            if(mapCurrentWindow.get(deletedWord)==1){
                                mapCurrentWindow.remove(deletedWord);
                            }else{
                                mapCurrentWindow.put(deletedWord, mapCurrentWindow.get(deletedWord)-1);
                            }
                        }
                    }
                    queueWords.addLast(currentStr);
                    if(queueWords.size()==words.length){
                        System.out.printf("Result: %s, %s\n", queueWords, i-(words.length-1)*lengthWord);
                        rs.add(i-(words.length-1)*lengthWord);
                    }
                    mapCurrentWindow.put(currentStr, mapCurrentWindow.getOrDefault(currentStr, 0)+1);
                }
                System.out.println(mapCurrentWindow);
                System.out.println(queueWords);
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //* return index xuất hiện của mỗi permutation words có trong string s
        //
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 10^4
        //1 <= words.length <= 5000
        //1 <= words[i].length <= 30
        //s and words[i] consist of lowercase English letters.
        //+ The length of words không quá lớn (5000)
        //+ The length of string s khá lớn (10^4)
        //+ words[i].length : 30 * 5000 = 150000 (Khá lớn)
        //
        //- Brainstorm
        //- Nếu lưu hashmap của all string của words ==> Khá lớn
        //+ max= 30! cases
        //
        //- Ở đây ta có thể lưu chỉ hashmap của từng words được không
        //Ex:
        //barfoofoobarthefoobarman, words = ["bar","foo","the"]
        //+ substring(i, 30) ==> 10^4*30 vẫn được
        //+ words có thể không distinct
        //+ words : {"bar":1, "foo":1, "the":1}
        //- Ở đây ta sẽ dùng slide window để xử lý bài này
        //- Khi gặp 1 string:
        //+ Thuộc trong words : Update hashmap
        //  + Nếu value của currentHashMap : > count có trong words : remove các phần từ ở first cho đến khi count của hashMap hiện tại == count
        //  + == count : thì put thêm
        //+ Không thuộc trong words:
        //  + Ta sẽ remove toàn bộ hashmap và update tiếp.
        //
        //#Reference:
        //2311. Longest Binary Subsequence Less Than or Equal to K
        //115. Distinct Subsequences
        //2453. Destroy Sequential Targets
        //
//        String s = "barfoofoobarthefoobarman";
//        String[] words = {"bar","foo","the"};
//        String s = "barfoothefoobarman";
//        String[] words = {"foo","bar"};
//        String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
//        String[] words = {"fooo","barr","wing","ding","wing"};
        String s = "ababababab";
        String[] words = {"ababa","babab"};
        System.out.println(findSubstring(s, words));
    }
}
