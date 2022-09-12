package interviews;

import java.util.*;

public class E150_TaskScheduler {

    static class Node{
        int x;
        int currentPosition=-1;
        char c;

        public Node(int x, char c) {
            this.x = x;
            this.c = c;
        }
    }

    public static int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> hashMap=new HashMap<>();
        int length=tasks.length;
        PriorityQueue<Node> queue= new PriorityQueue<>((node, t1) -> t1.x - node.x);

        for(int i=0;i<length;i++){
            if(!hashMap.containsKey(tasks[i])){
                hashMap.put(tasks[i], 1);
            }else{
                hashMap.put(tasks[i], hashMap.get(tasks[i])+1);
            }
        }
        Set<Map.Entry<Character, Integer>> entries = hashMap.entrySet();

        for(Map.Entry<Character, Integer> entry: entries){
            Node node=new Node(entry.getValue(), entry.getKey());
            queue.add(node);
        }
        List<Character> rs=new ArrayList<>();
        int index=0;

        while (!queue.isEmpty()){
            List<Node> temp=new ArrayList<>();

            while (!queue.isEmpty()){
                Node currentNode=queue.poll();

                if(currentNode.currentPosition!=-1&&(index-currentNode.currentPosition<n+1)){
//                    System.out.printf("%s %s %s %s, ", index, currentNode.c, currentNode.currentPosition, currentNode.x);
                    temp.add(currentNode);
                    continue;
                }
                currentNode.x--;
                currentNode.currentPosition=index;
//                System.out.printf("%s %s %s, ", currentNode.c, currentNode.currentPosition, currentNode.x);

                if(currentNode.x>0){
                    temp.add(currentNode);
                }
                rs.add(currentNode.c);
                break;
            }
            index++;
            queue.addAll(temp);
        }

        return index;
    }

    public static int leastIntervalOptimize(char[] tasks, int n) {
        if(n==0) return tasks.length;
        int[] count =new int[27];
        int length=tasks.length;

        for(int i=0;i<length;i++){
            count[tasks[i]-'A']++;
        }
        ArrayList<Node> integers=new ArrayList();

        for(int i=0;i<=26;i++){
            if(count[i]!=0){
                Node currentNode=new Node(count[i], (char) (i+'A'));
                integers.add(currentNode);
//                System.out.printf("%s, ", count[i]);
            }
        }
        integers.sort((integer, t1) -> t1.x - integer.x);
        int lengthList=integers.size();
        int rs=0;
        int cycle=n;

        for(int i=0;i<lengthList;i++){
            System.out.printf("%s %s, ", i, integers.get(i).x);

            if(integers.get(i).x>0){
                integers.get(i).x--;
                cycle--;
                if(cycle<0){
                    cycle=n;
                    i=i-n-1;
                    System.out.println();
                    System.out.println(i);
                }
                rs++;
            }else{
                n++;
            }
        }

        return rs;
    }

    public static void main(String[] args) {
//        char[] s=new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'};
//        char[] s=new char[]{'A','A','A','B','B','B'};
//        char[] s=new char[]{'A','A','A','B','B','B'};
//        char[] s=new char[]{'A'};
//        char[] s=new char[]{'A','A','A'};
        char[] s=new char[]{'A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'};
        int n=2;
        System.out.println(leastInterval(s, n));
        System.out.println(leastIntervalOptimize(s, n));
        //Bài này tư duy như sau:
        //1, Xác định thuật toán làm bài này/ tư tưởng.
        //Any order
        //chỉ quan tâm đến khác đôi một
        //
        //Thử 1 case đơn giản:
        //
        //"A","A","A","B","C","D"
        //
        //A B (?) có 2 lựa chọn:
        //+ A B idle
        //-->
        //+ A B C A B D A
        //
        //1.1, case đặc biệt làm sai kết quả
        //AABCB
        //A B C A B = 5
        //A B idle A B C
        //--> Chưa tối ưu chô idle --> Mục tiêu điền càng nhiều càng tốt
        //
        //A C B A idle B = 6
        //--> Chọn B/ C cần phải chọn đúng.
        //
        //- Thư case num(A) > num(B)
        //AAABBCD
        //
        //A B C A B D A --> 7
        //B A C B A D i A --> 8
        //--> Có vẻ như điền A trước đang có lợi
        //
        //- Case không có CD
        //AAABB
        //
        //A B i A B i A : 7
        //idle = 2
        //B A i B A i i A : 8
        //idle = 3
        //Tìm ilde min nhất:
        //
        //- Tổng quát:
        //A (i) i A (i) i A
        //B (i) i B (i) i i (i)
        //--> Giữa (A và A) chỉ có thể đặt nhiều nhất 2 elements.
        //Chứng minh là nếu điền số count1 < count2 trước ==> Kết quả sẽ tệ đi.
        //
        //1 + 3 + 3 = 7
        //1 + 3 + 1 + 3 = 8
        //
        //Cách tính như sau:
        //xếp all A với count(A) max trước --> Luôn luôn sẽ cho min nhất --> Sắp xếp tiếp các điểm còn lại vào.
        //
        //VD: Case số lớn hơn
        //AAAA BBB CC DD
        //A B C A B C A B D A (i) D : 12
        //--> nếu muốn bỏ (i) ta phải thay số (B) đằng trước ==> Tối ưu rồi.
        //
        //AAAA BBB CCC DDD EE FF
        //A B C A B C A B C A (D) E F D E F D
        //
        //Case đề bài
        //"A","A","A","A","A","A","B","C","D","E","F","G"
        //
        //AAAAAA B C D E F G
        //
        //A B C A D E A F G A i i A i i A
        //
        //2, Implement nhử thế nào?
        //- Cách suy nghĩ 1 ta sẽ điền theo thứ tự ban đầu + không sắp xếp lại (resort)
        //(A) B C (A) B C (A) B C D E i D
        //13
        //- Cách suy nghĩ 2, sắp xếp lại liên tục:
        //1,
        //A i i i i i i
        //AABBBCCCDDE
        //2,
        //A B i i i i i
        //AABBCCCDDE
        //3,
        //A B C i i i i
        //AABBCCDDE
        //4,
        //A B C D i i i
        //AABBCCDE
        //5,
        //A B C D A i i
        //ABBCCDE
        //6,
        //A B C D A B i
        //ABCCDE
        //7,
        //A B C D A B C
        //ABCDE
        //8,
        //A B C D A B C A
        //BCDE
        //9,
        //A B C D A B C A B
        //CDE
        //10,
        //A B C D A B C A B E
        //CD
        //11,
        //A B C D A B C A B E C
        //D
        //12,
        //A B C D A B C A B E C D
        //
        //==> Cách 1 sai.
        //
        //AAABBBCCCDDE
        //3 3 3 2 1
        //1,
        //(2) 3 3 2 1
        //2,
        //2 (2) 2 2 1
        //3,
        //2 2 (2) 2 1
        //4,
        //2 2 2 (1) 1
        //5,
        //2 2 (1) 1 1
        //6,
        //2 2 (1) 1 1
        //7,
        //2 (1) 1 1 1
        //8,
        //(1) 1 1 1 1
        //9,
        //(0) 1 1 1 1
        //
        //
        //AAAAAAAABBBBCCDE
        //84211
        //
        //1,
        //(7)4211
        //2,
        //7(4)211
        //3,
        //74(2)11
        //i=2 --> 2-n = (0)
        //VD: Case khác:
        //44211
        //1,
        //(3)4211
        //2,
        //3(3)211
        //3,
        //3(2)211
        //4,
        //32(1)11
        //5,
        //(2)2111
        //--> comback (index=0)
        //==> Vẫn đúng (3>=3)
        //6,
        //2(1)111
        //7,
        //21(0)11
        //8,
        //(1)1011
        //9,
        //1(0)011
        //10,
        //100(0)1
        //10,
        //10000
        //11,
        //10,
        //00000
    }
}
