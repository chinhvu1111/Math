package E1_trie_topic;

import java.util.*;

public class E6_DesignSearchAutocompleteSystem {

    public static class Node{
        int frequency;
        String s;
        public Node(int frequency, String s){
            this.frequency=frequency;
            this.s=s;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "frequency=" + frequency +
                    ", s='" + s + '\'' +
                    '}';
        }
    }

    public class Trie{
        char c;
        HashMap<Character, Trie> child;
        TreeSet<Node> sortResult;
        public Trie(){
            child = new HashMap<>();
            sortResult = new TreeSet<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if(o1.frequency!=o2.frequency){
                        return o1.frequency-o2.frequency;
                    }
                    return o2.s.compareTo(o1.s);
                }
            });
        }

        @Override
        public String toString() {
            return c+" "+child+ " " + sortResult + "\n";
        }
    }
    Trie root;
    StringBuilder bufferStr;
    Trie pointer;
    HashMap<String, Integer> mapCount;

//    public void addNewChar(char c, Trie node, String word, int frequency){
//        Trie child=node.child.get(c);
//        if(child==null){
//            child=new Trie();
//            node.child.put(c, child);
//            if(node.sortResult.size()<3){
//                node.sortResult.add(new Node(frequency, word));
//            }else if(frequency>node.sortResult.first().frequency){
//                if(word.compareTo(node.sortResult.first().s)<0){
//                    node.sortResult.pollFirst();
//                    node.sortResult.add(new Node(frequency, word));
//                }else if(word.compareTo(node.sortResult.first().s)==0){
//                    Node oldNode=node.sortResult.pollFirst();
//                    oldNode.frequency++;
//                    node.sortResult.add(oldNode);
//                }
//            }
//        }
//        node=child;
//    }

    public Trie addWord(Trie initNode, String word, int frequency){
        Trie node=initNode;
        int oldCount=0;

        if(mapCount.containsKey(word)){
            oldCount=mapCount.get(word);
        }
        bufferStr=new StringBuilder();
        //chinhvu
        //
        frequency+=oldCount;
        //Time : O(m)
        for(char c : word.toCharArray()){
            Trie child=node.child.get(c);
//            System.out.printf("%s %s\n", c, child);

            if(child==null){
                child=new Trie();
                child.sortResult.add(new Node(frequency, word));
                node.child.put(c, child);
            }
            //Có thể nhiều words >3 --> Dẫn đến sai số
            Node oldNode= new Node(oldCount, word);
            //Time : Log(3)
            if(child.sortResult.contains(oldNode)){
                Node newNode=new Node(frequency, word);
//                System.out.printf("Init: %s\n",node.sortResult.size());
                child.sortResult.remove(oldNode);
//                System.out.printf("Before: %s\n",node.sortResult.size());
                child.sortResult.add(newNode);
//                System.out.printf("After: %s\n",node.sortResult.size());
            }else if(child.sortResult.size()<3){
                Node newNode=new Node(frequency, word);
                child.sortResult.add(newNode);
            }else if(frequency>child.sortResult.first().frequency){
                child.sortResult.pollFirst();
                Node newNode=new Node(frequency, word);
                child.sortResult.add(newNode);
            }else if(frequency==child.sortResult.first().frequency){
                if(word.compareTo(child.sortResult.first().s)<0){
                    child.sortResult.pollFirst();
                    child.sortResult.add(new Node(frequency, word));
//                    System.out.printf("Poll: %s, add: %s\n", poll.s, word);
                }else if(word.compareTo(child.sortResult.first().s)==0){
                    oldNode=child.sortResult.pollFirst();
                    oldNode.frequency++;
                    child.sortResult.add(oldNode);
                }
            }
            node=child;
        }
        return node;
    }

    public E6_DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        root=new Trie();
        pointer=root;
        mapCount=new HashMap<>();
        int n=sentences.length;

        for(int i=0;i<n;i++){
            addWord(root, sentences[i], times[i]);
            mapCount.put(sentences[i], mapCount.getOrDefault(sentences[i], 0)+times[i]);
        }
    }

    public List<String> input(char c) {
        Trie lastNode = null;

        if(c=='#'){
            String str= bufferStr.toString();
            addWord(root, str, 1);
            if(mapCount.containsKey(str)){
                mapCount.put(str, mapCount.getOrDefault(str, 0)+1);
            }else{
                mapCount.put(str, 1);
            }
            pointer=root;
            bufferStr=new StringBuilder();
            return new ArrayList<>();
        }else{
            bufferStr.append(c);
            lastNode=pointer.child.get(c);
            if(lastNode==null){
                Trie child=new Trie();
                pointer.child.put(c, child);
                lastNode=child;
            }
        }
//        Trie child=lastNode.child.get(c);
        List<String> currentRs=new ArrayList<>();

        for (Node node : lastNode.sortResult) {
            currentRs.add(node.s);
        }
        //                Trie child=new Trie();
//                lastNode.child.put(c, child);
        pointer=lastNode;
        Collections.reverse(currentRs);
        return currentRs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Sentence là 1 loạt các character kết thúc bởi #
        //* For each input character except '#', return the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed.
        //- The hot degree for a sentence
        // + Defined as the number of times a user typed the exactly same sentence before.
        //- Nếu có chung số lần giống nhau --> Sort string theo ascii từ (min -> max)
        //Ex:
        //Explanation
        //AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]);
        //- obj.input("i"); // return ["i love you", "island", "i love leetcode"].
        // There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree.
        // Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman".
        // Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
        //- obj.input(" "); // return ["i love you", "i love leetcode"]. There are only two sentences that have prefix "i ".
        //- obj.input("a"); // return []. There are no sentences that have prefix "i a".
        //- obj.input("#"); // return []. The user finished the input, the sentence ("i a") should be saved as a historical sentence in system. And the following input will be counted as a new search.
        //
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //n == sentences.length
        //n == times.length
        //1 <= n <= 100
        //1 <= sentences[i].length <= 100 ==> Số cũng không lớn
        //1 <= times[i] <= 50 ==> Số cũng nhỏ
        //c is a lowercase English letter, a hash '#', or space ' '.
        //Each tested sentence will be a sequence of characters c that end with the character '#'.
        //Each tested sentence will have a length in the range [1, 200].
        //The words in each input sentence are separated by single spaces.
        //At most 5000 calls will be made to input.
        //
        //- Brainstorm
        //1.
        //1.1, Idea
        //- Các words sẽ được structured thành 1 trie
        //- Các word end with # sẽ được save vào history
        //- Add word : nếu word đã exists --> Increase frequency lên
        //Ex:
        //["AutocompleteSystem", "input", "input", "input", "input"]
        //[[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
        // root -i-> node1 -" "-> node2
        // i : treeSet : [(5, str(i=0)),(3, str(i=2)),(2, str(i=3)),(2, str(i=4))]
        //
        //- Vì count của word cũ sẽ được cộng dồn nếu + word đó được dùng tiếp
        //==> Nên ta sẽ cần phải lưu thông tin các word đó vào map count để khi cần thì ta sẽ fill trong sorted list vào
        //
        //- Phân biệt "i a" và "i a#"
        //+ # thì luôn return empty list
        //+ i a thì sẽ chưa có word mới ==> word mới chỉ được add khi có #
        //
        //- Lỗi thêm case liên quan đến frequency nữa.
        //==> Old frequency + 1 để ra frequency mới rồi mới so sánh được.
        //
        //- Nếu các word mà out top 3 mà nếu được access đến thì có return lại không?
        //Ex:
        //["a","b","c","d","e"],[5,5,5,2,1]
        //input : d
        //input : #
        //==> Vẫn đúng
        //- Bị trường hợp nếu không tìm thấy lúc append thì pointer next chưa đúng
        //Ex:
        //[[["a","b","ca","fe","e"],[5,5,5,2,1]],["d"],["c"],["#"],["e"],["#"]]
        //+ Result:
        //[null,[],["ca"],[],["e"],[]]
        //+ Expect:
        //[null,[],[],[],["e"],[]]
        //
        //1.2, Special cases:
        //Ex:
        //[[["abc","abbc","a"],[3,3,3]],
        //["b"] : []
        //["c"] : []
        //["#"] : []
        //["b"] : [bc]
        //["c"] : [bc]
        //["#"] : []
        //["a"] : ["a","abbc","abc"] != ["abbc","abc"]
        //["b"] : ["abbc","abc"]
        //["c"] : ["abc"]
        //["#"] : []
        //["a"] : ["abc","a","abbc"] != ["abc","abbc","abc"]
        //["b"] : ["abc","abbc"] != ["abc","abbc","abc"]
        //["c"] : ["abc"]
        //["#"]] : []
        //
        System.out.println("New testcase");
//        String[] s={"i love you","island","iroman","i love leetcode"};
//        int[] times={5,3,2,2};
//        String[] s={"abc","abbc","a"};
//        int[] times={3,3,3};
        String[] s={"a","b","ca","fe","e"};
        int[] times={5,5,5,2,1};
//        E6_DesignSearchAutocompleteSystem e=new E6_DesignSearchAutocompleteSystem(s, times);
//        System.out.println(e.input('i'));
//        System.out.println(e.input(' '));
//        System.out.println(e.input('a'));
//        System.out.println(e.input('#'));
//        e=new E6_DesignSearchAutocompleteSystem(s, times);
//        System.out.println(e.input('i'));
//        System.out.println(e.input(' '));
//        System.out.println(e.input('a'));
//        System.out.println(e.input('#'));
//        System.out.println(e.input('i'));
//        System.out.println(e.input(' '));
//        System.out.println(e.input('a'));
//        System.out.println(e.input('#'));
//        System.out.println(e.input('i'));
//        System.out.println(e.input(' '));
//        System.out.println(e.input('a'));
//        System.out.println(e.input('#'));
        //
//        E6_DesignSearchAutocompleteSystem e=new E6_DesignSearchAutocompleteSystem(s, times);
//        System.out.println(e.input('b'));
//        System.out.println(e.input('c'));
//        System.out.println(e.input('#'));
//        System.out.println(e.input('b'));
//        System.out.println(e.input('c'));
//        System.out.println(e.input('#'));
//        System.out.println(e.input('a'));
//        System.out.println(e.input('b'));
//        System.out.println(e.input('c'));
//        System.out.println(e.input('#'));
//        System.out.println(e.input('a'));
//        System.out.println(e.input('b'));
//        System.out.println(e.input('c'));
//        System.out.println(e.input('#'));
        //e.root.child.get('i').child.get(' ').child.get('a')
        //[[["i love you","island","iroman","i love leetcode"],
        // [5,3,2,2]],["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"]]
        //
        //["i"] : ["i love you","i love leetcode"]
        //[" "] : []
        //["a"] : []
        //["#"] : []
        E6_DesignSearchAutocompleteSystem e=new E6_DesignSearchAutocompleteSystem(s, times);
        System.out.println(e.input('d')); //Expect: []
        System.out.println(e.input('c')); //Expect: []
        System.out.println(e.input('#')); //Expect: []
        System.out.println(e.input('e')); //Expect: [e]
        System.out.println(e.input('#')); //Expect: []
        System.out.println("=============================");
        //For testing
        String s1="i love leetcode";
        String s2="iroman";
        System.out.println(s1.compareTo(s2));
        TreeSet<Node> sortResult = new TreeSet<>((o1, o2) -> {
            if (o1.frequency != o2.frequency) {
                return o1.frequency - o2.frequency;
            }
            return o2.s.compareTo(o1.s);
        });
        sortResult.add(new Node(1, s1));
        sortResult.add(new Node(1, s2));
        System.out.println(sortResult);
        //
        //1.3, Optimization
        //- Sẽ không thể gộp đoạn insert word + đoạn lấy thông tin top 3 vì:
        //  + Chỉ khi insert word thì ta update all word trên đường đi
        //      + Lúc insert ta cần insert trên all node trên traversed path
        //  + Các result trả lại khi traverse sẽ sẽ là top 3 cũ --> Không được updated
        //
        //1.4, Complexity
        //- m : the max length of all of the words
        //- n : the number of word
        //- Time : O(m)
        //- Space : O(n*3)
        //#Reference:
        //1967. Number of Strings That Appear as Substrings in Word
        //2933. High-Access Employees
        //537. Complex Number Multiplication
    }
}
