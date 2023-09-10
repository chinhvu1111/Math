package E1_Array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class E9_FindAndReplaceInString {

    public static String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int k=indices.length;
        HashMap<Integer, List<String[]>> indexToString=new HashMap<>();
        for(int i=0;i<k;i++){
            List<String[]> list=indexToString.get(indices[i]);
            if(list==null){
                list=new LinkedList<>();
            }
            list.add(new String[]{sources[i], targets[i]});
            indexToString.put(indices[i], list);
        }
        // System.out.printf("%s\n", indexToString);
        int n=s.length();
        StringBuilder str=new StringBuilder();

        for(int j=0;j<n;j++){
            // System.out.printf("%s %s\n", j, indexToString.get(j));
            if(indexToString.containsKey(j)){
                int tempJ=j;
                List<String[]> listStr=indexToString.get(j);
                boolean isRelace=false;
                StringBuilder temp = new StringBuilder();

                for(int h=0;h<listStr.size();h++){
                    j=tempJ;
                    String currentSource=listStr.get(h)[0];
                    String currentTarget=listStr.get(h)[1];
                    temp=new StringBuilder();
                    int index=0;

                    while(j<n&&index<currentSource.length()){
                        if(s.charAt(j)!=currentSource.charAt(index)){
                            temp.append(s.charAt(j));
                            break;
                        }
                        temp.append(s.charAt(j));
                        j++;
                        index++;
                    }
                    if(index==currentSource.length()){
                        str.append(currentTarget);
                        j--;
                        isRelace=true;
                        break;
                    }
                }
                if(!isRelace){
                    System.out.printf("Append %s ,j=%s\n",temp, j);
                    str.append(temp);
                }
            }else{
                // System.out.printf("%s\n", s.charAt(j));
                str.append(s.charAt(j));
            }
        }
        return str.toString();
    }
    public static void main(String[] args) {
        //** Requirement
        //- Given string s, indices, sources, and targets
        //- k length of operations
        //- indices[i] : vị trí của source[i] trong s
        //- target[i] : Nếu vị trí của source[i] trong s mà đúng là indices[i] --> replace
        //- Việc replacement sẽ không overlap lẫn nhau
        //* Return string s sau khi replace all.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 1000
        //k == indices.length == sources.length == targets.length
        //1 <= k <= 100
        //0 <= indexes[i] < s.length
        //1 <= sources[i].length, targets[i].length <= 50
        //s consists of only lowercase English letters.
        //sources[i] and targets[i] consist of only lowercase English letters.
        //+ k<=100 is small
        //- source[i] <= 50 : small
        //- length(s) <=1000 : small
        //
        //- Brainstorm
        //- source[i] là vị trí cần thay thế
        //- target[i] là string thay thế tương ứng
        //--> HashMap<Integer, String>
        //- Khi reach đến integer thuộc hashmap --> Ta sẽ làm 1 bước là check và thay thế
        //
        //#Reference:
        //2497. Maximum Star Sum of a Graph
        //243. Shortest Word Distance
        //2505. Bitwise OR of All Subsequence Sums
        String s="abcde";
        int[] indices=new int[]{2,2};
        String[] sources=new String[]{"cdef","bc"};
        String[] targets=new String[]{"f","fe"};
        System.out.println(findReplaceString(s, indices, sources, targets));
        //#Reference:
        //327. Count of Range Sum
        //406. Queue Reconstruction by Height
        //493. Reverse Pairs
    }
}
