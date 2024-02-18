package E1_heap_priority_queue;

import javafx.util.Pair;

import java.util.*;

public class E7_RearrangeStringKDistanceApart {

    public static String rearrangeStringWrong(String s, int k) {
        HashMap<Character, Integer> charToCount=new HashMap<>();
        int n=s.length();

        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            if(!charToCount.containsKey(c)){
                charToCount.put(c, 1);
            }else{
                charToCount.put(c, charToCount.getOrDefault(c, 0)+1);
            }
        }
        PriorityQueue<Pair<Character, Integer>> sortByCount=new PriorityQueue<>((a, b) -> (!Objects.equals(b.getValue(), a.getValue()) ?b.getValue()-a.getValue():a.getKey()-b.getKey()));
        List<Pair<Character, Integer>> sortChar=new ArrayList<>();

        for(Map.Entry<Character, Integer> entry: charToCount.entrySet()){
            sortByCount.add(new Pair<>(entry.getKey(), entry.getValue()));
            sortChar.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        sortChar.sort((a, b) -> (!Objects.equals(b.getValue(), a.getValue()) ?b.getValue()-a.getValue():a.getKey()-b.getKey()));

        StringBuilder rs=new StringBuilder();
        HashMap<Character, Integer> recentPosOfChar=new HashMap<>();

        for(int i=0;i<n;i++){
            while(!sortByCount.isEmpty()&&!charToCount.containsKey(sortByCount.peek().getKey())){
                sortByCount.poll();
            }
            char c=sortByCount.peek().getKey();
            if(!recentPosOfChar.containsKey(c)){
                recentPosOfChar.put(c, i);

                int oldCount=charToCount.get(c);
                if(oldCount==1){
                    charToCount.remove(c);
                }else{
                    charToCount.put(c, charToCount.get(c)-1);
                }
                rs.append(c);
            }else{
                int prevIndex=recentPosOfChar.get(c);
                System.out.printf("prevIndex: %s, curIndex: %s, char: %s, str: %s\n", prevIndex, i, c, rs);

                if(i-prevIndex>=k){
                    rs.append(c);
                    recentPosOfChar.put(c, i);

                    int oldCount=charToCount.get(c);
                    if(oldCount==1){
                        charToCount.remove(c);
                    }else{
                        charToCount.put(c, charToCount.get(c)-1);
                    }
                }else{
                    for(int j=0;j<sortChar.size();j++){
                        if(sortChar.get(j).getKey()==c){
                            continue;
                        }
                        while(j<sortChar.size()
                                &&(recentPosOfChar.containsKey(sortChar.get(j).getKey())&&i-recentPosOfChar.get(sortChar.get(j).getKey())<k
                                ||!charToCount.containsKey(sortChar.get(j).getKey()))){
                            j++;
                        }
                        if(j<sortChar.size()){
                            int oldCount=charToCount.get(sortChar.get(j).getKey());

                            if(oldCount==1){
                                charToCount.remove(sortChar.get(j).getKey());
                            }else{
                                charToCount.put(sortChar.get(j).getKey(), charToCount.get(sortChar.get(j).getKey())-1);
                            }
                            recentPosOfChar.put(sortChar.get(j).getKey(), i);
                            rs.append(sortChar.get(j).getKey());
                            break;
                        }
                    }
                }
            }
            System.out.println(rs);
        }
//        System.out.println(rs.toString());
        return rs.length()!=s.length()?"":rs.toString();
    }

    public static class Node<T, V>{
        T x;
        V y;
        public Node(T x, V y){
            this.x=x;
            this.y=y;
        }
    }

    public static String rearrangeString(String s, int k) {
        HashMap<Character, Integer> charToCount=new HashMap<>();
        int n=s.length();

        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            if(!charToCount.containsKey(c)){
                charToCount.put(c, 1);
            }else{
                charToCount.put(c, charToCount.getOrDefault(c, 0)+1);
            }
        }
        PriorityQueue<Node<Character, Integer>> sortByCount=new PriorityQueue<>((a, b) -> (!Objects.equals(b.y, a.y) ?b.y-a.y:a.x-b.x));

        for(Map.Entry<Character, Integer> entry: charToCount.entrySet()){
            sortByCount.add(new Node<>(entry.getKey(), entry.getValue()));
        }
        HashMap<Character, Integer> recentPosOfChar=new HashMap<>();
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<n;i++){
            List<Node<Character, Integer>> list=new ArrayList<>();

            //If this char was put into the
            int size=sortByCount.size();
            Character curChar=null;

            for(int j=0;j<size;j++){
                Node<Character, Integer> curNode=sortByCount.poll();

                if(!recentPosOfChar.containsKey(curNode.x)||i-recentPosOfChar.get(curNode.x)>=k){
                    curChar=curNode.x;
                    recentPosOfChar.put(curChar, i);
                    rs.append(curChar);
                    curNode.y--;
                    if(curNode.y!=0){
                        list.add(curNode);
                    }
                    break;
                }else {
                    list.add(curNode);
                }
            }
//            System.out.println(curChar);
            sortByCount.addAll(list);
        }
        return rs.length()!=s.length()?"":rs.toString();
    }

    public static void main(String[] args) {
        // Requirement
        //- Given a string s and an integer k,
        //* Rearrange s such that the same characters are at least distance k from each other.
        // If it is not possible to rearrange the string, return an empty string "".
        //- Sắp xếp lại sao cho các ký tự giống nhau --> cách nhau ít nhất k
        //Ex
        //Input: s = "aabbcc", k = 3
        //Output: "abcabc"
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 3 * 10^5
        //s consists of only lowercase English letters.
        //0 <= k <= s.length
        //==> Lower case ==> 26 được
        //Length: <= 3*10^5
        //--> Ta có thể làm trong O(N) ==> O(N*log(N))
        //
        //- Brainstorm
        //- count càng lớn ==> khoảng cách cách nhau càng nhỏ (Nếu sort lại)
        //Ex:
        //Input: s = "aaadbbcc", k = 2
        //Output: "abacabcd"
        //abacabcd
        //count[a]=3
        //count[d]=1
        //count[b]=2
        //count[c]=2
        //
        //a, count lớn nhất ==> đặt trước
        //b -> c -> d
        //- Đặt như thế nào?
        //a : a| |a| |a| | | |
        //b : a|b|a|b|a| | | |
        //c : a|b|a|b|a|c| |c|
        //d : a|b|a|b|a|c|d|c|
        //- Khi nào thì biết nên fill gì?
        //  + Giữa khoảng a,...,a
        //  + Nhận biết trùng với a thì điền lại a
        //- a,b,a
        //- Ta sẽ lưu lại index gần nhất của character về phía left
        //  + HashMap<Char, pos>
        //- Lưu số character còn lại:
        //  + HashMap<Char, count>
        //==> Nếu count còn và char gần nhất thoả mãn thì chọn.
        //- Vấn đê là nếu không điền được "a" (ưu tiên lớn nhất)
        //  + Điền ưu tiên sau ntn?
        //Ex:
        //
//        String s="aaadbbcc";
//        int k=2;
//        String s="aabbcc";
//        int k=3;
//        String s="aaabc";
//        int k=3;
//        String s="aabbccdd";
        //abadbdc
//        int k=2;
//        String s="aabbcc";
//        int k=2;
        //Result=abcabc
        //- Nếu ta đặt bình thường thì sẽ không ra được result:
        //==> Case này thì sẽ chạy sai : result=ababc
        //- aabbcc
        //Result= abcabc
        //count[a]=2
        //count[b]=2
        //count[c]=2
        //Các step:
        //+ add(a) : a, count[a]=1, [b,c,a]
        //+ add(b) : ab, count[b]=1, [c,a,b]
        //+ add(c) : abc, count[c]=1,[a,b,c]
        //
        //s="aaadbbcc", k=2
        //count[a]=3
        //count[b]=2
        //count[c]=2
        //count[d]=1
        //sort=[a,b,c,d]
        //+ add(a) : a, count[a]=2, [a,b,c,d]
        //+ Vì a vừa sử dụng xong --> ta không thể sử dụng 'a':
        //  + Mình sẽ traverse qua all node để tìm phần tủ thoả mãn nhất
        //+ add(b) : ab; count[b]=1, [a,c,b,d]
        //+ add(a) : aba; count[a]=1, [c,a,b,d]
        //+ add(c) : abac; count[c]=1, [a,b,c,d]
        //
        String s="aaabc";
        int k=3;
        System.out.println(rearrangeString(s, k));
        //#Reference:
        //767. Reorganize String
        //2182. Construct String With Repeat Limit
    }
}