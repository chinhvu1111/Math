package E1_Queue;

import java.util.*;
import java.util.function.BiConsumer;

public class E1_Dota2Senate {

    public static String predictPartyVictory(String senate) {
        int n=senate.length();
        TreeSet<Integer> treeR=new TreeSet<>();
        TreeSet<Integer> treeD=new TreeSet<>();

        for(int i=0;i<n;i++){
            if(senate.charAt(i)=='R'){
                treeR.add(i);
            }else{
                treeD.add(i);
            }
        }
//        if(queueR.size()!=queueD.size()){
//            if(queueR.size()>queueD.size()){
//                return "Radiant";
//            }else{
//                return "Dire";
//            }
//        }
        while(!treeR.isEmpty()&&!treeD.isEmpty()){
            TreeSet<Integer> tmpTreeR=new TreeSet<>();
            TreeSet<Integer> tmpTreeD=new TreeSet<>();
            //Ex:
            //RRDDDRRD
            //+ queue(R)=0,1,5,6
            //+ queue(D)=2,3,4,7
            for(int i=0;i<n;i++){
                System.out.printf("Current index: %s\n", i);
                System.out.println(treeR);
                System.out.println(treeD);
                if(!treeR.isEmpty()&&senate.charAt(i)=='R'){
                    Integer currentElement=treeR.first();
                    treeR.remove(currentElement);
                    tmpTreeR.add(currentElement);
                    Integer deletedElement=treeD.ceiling(currentElement);
                    if(deletedElement==null){
                        deletedElement=tmpTreeD.ceiling(currentElement);
                    }
                    if(deletedElement!=null){
                        System.out.printf("R - D, Current element: %s, deleted element: %s\n", currentElement, deletedElement);
                        treeD.remove(deletedElement);
                        tmpTreeD.remove(deletedElement);
                    }else if(!treeD.isEmpty()){
                        System.out.printf("D - R, Current element: %s, deleted element: %s\n", currentElement, treeD.first());
                        Integer e=treeD.first();
                        treeD.remove(e);
                        tmpTreeD.remove(e);
                    }else if(!tmpTreeD.isEmpty()){
                        System.out.printf("D - R, Current element: %s, deleted element: %s\n", currentElement, treeR.first());
                        Integer e=tmpTreeD.first();
                        tmpTreeD.remove(e);
                    }
//                    if(treeD.isEmpty()){
//                        return "Radiant";
//                    }
                }else if(!treeD.isEmpty()&&senate.charAt(i)=='D'){
                    Integer currentElement=treeD.first();
                    treeD.remove(currentElement);
                    tmpTreeD.add(currentElement);
                    Integer deletedElement=treeR.ceiling(currentElement);
                    if(deletedElement==null){
                        deletedElement=tmpTreeR.ceiling(currentElement);
                    }
                    if(deletedElement!=null){
                        System.out.printf("D - R, Current element: %s, deleted element: %s\n", currentElement, deletedElement);
                        treeR.remove(deletedElement);
                        tmpTreeR.remove(deletedElement);
                    }else if(!treeR.isEmpty()){
                        System.out.printf("D - R, Current element: %s, deleted element: %s\n", currentElement, treeR.first());
                        Integer e=treeR.first();
                        treeR.remove(e);
                        tmpTreeR.remove(e);
                    }else if(!tmpTreeR.isEmpty()){
                        System.out.printf("D - R, Current element: %s, deleted element: %s\n", currentElement, treeR.first());
                        Integer e=tmpTreeR.first();
                        tmpTreeR.remove(e);
                    }
//                    if(treeR.isEmpty()){
//                        return "Dire";
//                    }
                }
                System.out.println();
            }
            System.out.println("TMP");
            System.out.println(tmpTreeR);
            System.out.println(tmpTreeD);
            System.out.println();
            treeR.addAll(tmpTreeR);
            treeD.addAll(tmpTreeD);
        }
        if(treeR.isEmpty()){
            System.out.printf("%s\n", "exit");
            return "Dire";
        }
        return "Radiant";
    }

    public static String predictPartyVictorySlow(String senate) {
        int n=senate.length();
        int index=0;
        //Space : O(n)
        boolean[] visited=new boolean[n];
        int countD=0, countR=0;

        //Time : O(n)
        for(int i=0;i<n;i++){
            if(senate.charAt(i)=='R'){
                countR++;
            }else{
                countD++;
            }
        }
        System.out.printf("%s %s\n", countR, countD);
        //s=DRRDRDRD
        //s=D(R)RDRDRD
        //s=D(R)R(D)RDRD
        //s=D(R)R(D)R(D)RD
        //s=D(R)R(D)R(D)R(D)
        //Time : O(k)
        while(countR!=0&&countD!=0){
            index=0;
            //Time : O(n)
            for(int i=0;i<n;i++){
                if(visited[i]){
                    continue;
                }
                char c=senate.charAt(i);
                char findChar=(c=='R')?'D':'R';
                index=Math.max(i+1, index);
                index=index%n;

                //Time : O(n)
                while(index<n&&index!=i&&(senate.charAt(index)!=findChar||visited[index])){
                    index++;
                    index=index%n;
                }
                if(index!=i&&index<n){
                    if(c=='R'){
                        countD--;
                    }else{
                        countR--;
                    }
                }
                System.out.printf("Index: %s\n", index);
                if(index<n){
                    System.out.printf("i=%s, Current char:%s, Find: %s, index=%s\n", i, c, findChar, index);
                    visited[index]=true;
                    System.out.printf("Visited[%s]=true\n", index);
                }
                if(index==i){
                    if(c=='R'){
                        return "Radiant";
                    }else{
                        return "Dire";
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if(visited[i]){
                    System.out.printf("(%s)",senate.charAt(i));
                }else{
                    System.out.printf("%s",senate.charAt(i));
                }
            }
            System.out.println();
            System.out.printf("%s %s\n", countR, countD);
        }
        System.out.printf("%s %s\n", countR, countD);
        if(countD==0){
            return "Radiant";
        }
        return "Dire";
    }

    public static boolean ban(StringBuilder senateArr, char toBan, int startAt){
        boolean loopAround=false;

        while(true){
            if(startAt==0){
                loopAround=true;
            }
            if(senateArr.charAt(startAt)==toBan){
                senateArr.deleteCharAt(startAt);
                break;
            }
            startAt=startAt%(senateArr.length());
        }
        return loopAround;
    }

    public static String predictPartyVictoryRefactor(String senate) {
        int n=senate.length();
        //Space : O(n)
        int countD=0, countR=0;

        //Time : O(n)
        for(int i=0;i<n;i++){
            if(senate.charAt(i)=='R'){
                countR++;
            }else{
                countD++;
            }
        }
        int index=0;
        StringBuilder senateArray = new StringBuilder(senate);

        while(countR>0&&countD>0){
            if(senateArray.charAt(index)=='R'){
                //- Vì 'R'!='D' nên nếu search ntn ta sẽ không bao giờ có cases trừng index dù rotate
                //- Nếu nó ta tìm được index trước index, ta cần:
                //+ Remove character đó đi từ StringBuilder
                //+ Giảm index vì ta cần tính theo index mới (Array sau khi xoá phần tử đó đi)
                boolean bannedSenatorBefore=ban(senateArray, 'D', (index+1)%senateArray.length());
                if(bannedSenatorBefore){
                    index--;
                }
                countD--;
            }else{
                boolean bannedSenatorBefore=ban(senateArray, 'R', (index+1)%senateArray.length());
                if(bannedSenatorBefore){
                    index--;
                }
                countR--;
            }
            index=(index+1)%senateArray.length();
        }
        if(countR>0){
            return "Radiant";
        }else{
            return "Dire";
        }
    }

    public static String predictPartyVictoryBinarySearch(String senate) {

        int n=senate.length();
        //Space : O(n)
        List<Integer> rIndices=new ArrayList<>();
        List<Integer> dIndices=new ArrayList<>();
        boolean[] visited=new boolean[n];

        //Time : O(n)
        for(int i=0;i<n;i++){
            if(senate.charAt(i)=='R'){
                rIndices.add(i);
            }else{
                dIndices.add(i);
            }
        }
        BiConsumer<List<Integer>, Integer> ban=(indices, start) ->{
            int temp=Collections.binarySearch(indices, start);
            //Return:
            //- temp : index of the element having the value greater than key
            //-  (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the list
            //- size of indices if all element < key

            if(temp<0){
                temp=-temp-1;
                if(temp==indices.size()){
                    //- Ta cần xoá đầu tiên vì không có element (index) > current index
                    //==> Ta sẽ xoá ở đầu (Sẽ không sai vì indices sẽ được remove element liên tục ở 2 đầu)
                    //- Array theo dạng index nên (i : 0 --> n-1)
                    //+ rIndices và đIndices sẽ được sorted sẵn nếu theo order này
                    visited[indices.get(0)]=true;
                    indices.remove(0);
                }else{
                    //Chỗ này là tìm ceiling
                    visited[indices.get(temp)]=true;
                    indices.remove(temp);
                }
            }
        };

        int index=0;

        while(!rIndices.isEmpty()&&!dIndices.isEmpty()){
            if(!visited[index]){
                if(senate.charAt(index)=='R'){
                    ban.accept(dIndices, index);
                }else{
                    ban.accept(rIndices, index);
                }
            }
            index=(index+1)%n;
        }
        if(!rIndices.isEmpty()){
            return "Radiant";
        }
        return "Dire";
    }

    public static String predictPartyVictoryQueue(String senate) {
        int n=senate.length();
        //Space : O(n)
        Queue<Integer> rQueue=new LinkedList<>();
        Queue<Integer> dQueue=new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if(senate.charAt(i)=='R'){
                rQueue.add(i);
            }else{
                dQueue.add(i);
            }
        }
        while(!rQueue.isEmpty()&&!dQueue.isEmpty()){
            int rTurn=rQueue.poll();
            int dTurn=dQueue.poll();

            if(dTurn<rTurn){
                dQueue.add(dTurn+n);
            }else{
                rQueue.add(rTurn+n);
            }
        }
        if(!rQueue.isEmpty()){
            return "Radiant";
        }
        return "Dire";
    }

    public static String predictPartyVictoryQueueOptimization(String senate) {
        int n=senate.length();
        //Space : O(n)
        Queue<Character> queue=new LinkedList<>();
        int countD=0, countR=0;

        //Time : O(n)
        for(int i=0;i<n;i++){
            queue.add(senate.charAt(i));
            if(senate.charAt(i)=='R'){
                countR++;
            }else{
                countD++;
            }
        }
        int dFloatingBan=0, rFloatingBan=0;

        while(countR>0&&countD>0){
            char cur=queue.poll();

            if(cur=='D'){
                if(dFloatingBan>0){
                    dFloatingBan--;
                    countD--;
                }else{
                    rFloatingBan++;
                    queue.add('D');
                }
            }else{
                if(rFloatingBan>0){
                    rFloatingBan--;
                    countR--;
                }else{
                    dFloatingBan++;
                    queue.add('R');
                }
            }
        }
        if(countR!=0){
            return "Radiant";
        }
        return "Dire";
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Có 2 loại senator là :
        //+ Radiant
        //+ Dire
        //- Ở mỗi bước thì 1 senator có thể
        //+ Ban một senator's rights and the following rounds.ki
        //+ Nếu 1 senator found the rest of senator (have rights to vote) are (all from the same party) ==> Announce the victory.
        //- Suppose every senator is smart enough and will play the (best strategy) for his own party.
        //- The voting for this change is a round-based procedure.
        //+ Thủ tục sẽ bắt đầu từ first senator to last senator + (quay lại first)
        //
        //* Return the party will finally announce the victory
        //
        //Ex:
        //Input: senate = "RDD"
        //Output: "Dire"
        //Explanation:
        //The first senator comes from Radiant and he can just ban the next senator's right in round 1.
        //And the second senator can't exercise any rights anymore since his right has been banned.
        //And the third senator comes from Dire and he can ban the first senator's right in round 1.
        //And in round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //n == senate.length
        //1 <= n <= 10^4
        //senate[i] is either 'R' or 'D'.
        //--> Có thể xử lý trong (<=n*Log(n))
        //
        //- Brainstorm
        //Ex:
        //Input: senate = "RDD"
        //Output: "Dire"
        //- Mỗi bước 1 senator sẽ có quền (ban another senator) + (check victory)
        //- Ta thấy rằng nếu count của thằng nào lớn hơn --> Thằng đó win
        //+ Vì đến team còn lại không thể ban hết được
        //
        //Mỗi senator sẽ ưu tiên ban người bên phải sắp traverse để có thể senator đó tránh ban team mình
        //+ Để có thể traverse được số lượng R sớm nhất
        //Ex:
        //Input: senate = "RDDRRD"
        //+ step=1: R ban pos=1 (count(R)=3, count(D)=2)
        //+ step=2: R ban pos=1 (count(R)=3, count(D)=2)
        //+ step=3: R ban pos=1 (count(R)=2, count(D)=2)
        //+ step=4: R ban pos=1 (count(R)=2, count(D)=1)
        //+ step=5: R ban pos=1 (count(R)=2, count(D)=0) ==> return R
        //
        //- Ban người gần nhất bên right như thế nào
        //Ex:
        //RRRD
        //pos=0 --> D gần nhất có pos=3
        //** NOTE:
        //- Ý tưởng ở đây rất hay là dùng 2 queue để lưu index
        //- Nó sẽ thể hiện được order của từng R với D ==> WRONG vì không thể hiện được là peek node của queue thứ 2 có index > curren index
        //
        //Ex:
        //RRRDDD
        //queue(R) = 0,1,2
        //queue(D) = 3,4,5
        //
        //- Special cases:
        //S=DDRRR
        //+ Có vẻ như count không thể hiện gì vì:
        //+ Đến pos=2 ==> R chỉ còn 1 ==> Về cơ bản không thể ban được nếu so với D.
        //+ Bài này mấu chốt là ai ban trước --> Có khả năng thắng cao hơn.
        //Ex:
        //RRDDDRRD
        //RR(D)DDRRD
        //RR(D)(D)DRRD
        //RR(D)(D)D(R)RD
        //RR(D)(D)D(R)R(D)
        //
        //+ queue(R)=0,1,5,6
        //+ queue(D)=2,3,4,7
        //+ Chưa thể hiện được là tìm các node có index lớn hơn current index
        //
        //+ queue(R)=0,1,(5),6
        //+ queue(D)=2,3,4,(7)
        //- Nếu ta dùng treeset ==> Ta cần đánh dấu những điểm đã qua rồi ==> Không xét lại nữa.
        //+ Vấn đề là rotate
        //Ex:
        //RRDDDRRD
        //queueR = [6, 11, 13, 15]
        //queueD = [5, 10, 12, 14]
        //+ Khi index=6 xét xong ==> Nó cần phải được bỏ qua vì check tiếp thì ta sẽ check 11
        //+ Nhưng khi xét index=5 ==> Nó vẫn cần remove 6 đi chứ không phải remove 11 ==> Nếu ta search trong tree mà remove 11 ==> Bài toán sẽ sai
        //* Thực ra case này sẽ không bao giờ xảy ra vì khi xét đến current index ==> Nó sẽ là max index cho đến hiện tại ==> current index= 5 mà < index cũ = 6 là không thể.
        //--> Ta cần 1 queue thể hiện rằng:
        //+ Ta có thể đánh dấu peek là đã xét
        //+ Ta cũng có thể remove nó đi được khi so sánh với queue khác
        //
        //- Ta sẽ bị case là khi queue bị empty (do xét lần lượt) nhưng thực tế là element đó vân chưa bầu (Mà chỉ được traverse rồi thôi)
        //- Ta bị case:
        //Ex: DRRDRDRD
        //+ DRRD ==> 2 queue empty hết rồi ==> Trong loop đó nó sẽ empty + continue
        //+ Ta cần tìm cách để nó có thể traverse tiếp ==> Tránh rotate sai cách
        //
        //Ex:
        //DRRDRDRD
        //+ Vì phía right D gần R nhất
        //--> Traverse from right to left
        //+ index=0, queue(R) = empty queue(D)= D
        //+ index=1, queue(R) = R, queue(D) = empty
        //+ index=2, queue(R) = empty, queue(D) = D
        //+ index=3, queue(R) = R, queue(D) = empty
        //+ index=4, queue(R) = empty, queue(D) = D
        //- 1 senator có thể ban 1 senator khác mà trong trường hợp này nó không cần nằm trong queue ==> theo vòng tròn
        //+ senator ith sẽ ban senator (i-k)th + không nhất thiết ban senator (i+k)th
        //Ex:
        //DDDRRR
        //+ i=3 : queue(R)=R
        //+ i=2 : D(i==2) ban R(queue(last))=R
        //+ i=1 : D(i==2) ban R(queue(last))=R
        //+ i=0 : D(i==2) ban R(queue(last))=R
        //** Vì thử tự ban phải từ left --> right ==> Cái tư duy bên trên là để TÌM PHẦN TỪ "GẦN NHẤT" rìght của senator hiện tại
        //- Traverse from right to left
        //Ex:
        //DDDDRRR
        //+ i=3 : queue(R)=R
        //+ i=2 : D(i==2) ban R(queue(last))=R
        //+ i=1 : D(i==2) ban R(queue(last))=R
        //+ i=0 : D(i==2) ban R(queue(last))=R
        //** Vì thử tự ban phải từ left --> right ==> Cái tư duy bên trên là để TÌM PHẦN TỪ "GẦN NHẤT" rìght của senator hiện tại
        //
        //- Rotate case:
        //Ex:
        //RDRDDDDRRR
        //** Exp:
        //- Cần chú ý nếu dùng index rotate thì nếu gán index=i+1
        //==> Init cũng cần phải index=index%n
        //- Cần review detail 1 chút các cases ít nhất là coverage test.
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- N is the number of
        //- Space: O(n)
        //- Time : O(n^2*k)
        //
        //2.0 Refactor
        //- Idea
        //- Ta sẽ tạo ra StringBuilder có thể remove character được
        //Các thông tin cần lưu ý như sau:
        //- Ta sẽ tính count(R) và count(D) sau đó while trừ dần đi theo count(R) và count(D) cho đến khi chúng ==0 : break
        //- Vì 'R'!='D' nên nếu search ntn ta sẽ không bao giờ có cases trừng index dù rotate
        //- Nếu nó ta tìm được index trước index, ta cần:
        //+ Remove character đó đi từ StringBuilder
        //+ Giảm index vì ta cần tính theo index mới (Array sau khi xoá phần tử đó đi)
        //
        //- Index sẽ được % length của senateArr() thay vì n bình thường
        //==> Vì kích thước array thay đổi.
        //
        //
        //2.1, Optimization
        //- Ở đây ta sẽ dùng visited để speed up lên --> Không remove character nữa.
        //
        //2.2, Complexity:
        //- N is the length of string
        //- Space: O(n)
        //- Time : O(n^2*k)
        //
        //3 : Binary search
        //3.1, Idea
        //Return:
        //- temp : index of the element having the value greater than key
        //-  (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the list
        //- size of indices if all element < key
        //
        //- Ta cần xoá đầu tiên vì không có element (index) > current index
        //==> Ta sẽ xoá ở đầu (Sẽ không sai vì indices sẽ được remove element liên tục ở 2 đầu)
        //- Array theo dạng index nên (i : 0 --> n-1)
        //+ rIndices và đIndices sẽ được sorted sẵn nếu theo order này
        //
        //- Ở đây chủ yếu là tìm ceiling
        //
        //** EXP:
        //- Không cần dùng TreeSet để ceiling nữa ==> Ta sẽ dùng Collections.binarySearch() (List không distinct) để tìm + nhớ công thức
        //
        //4. Queue
        //4.1, Idea
        //* Exp:
        //- Với những bài toán rotate --> Nếu muốn xét tiếp rotate các phần tử ở first ta có 2 cách:
        //+ index=(index+1)%n
        //+ Add các phần từ ở đầu vào sau bằng cách index ==> (index+n)
        //- Không nên loop đi loop lại 1 array nhiều lần như cách 1 ==> Ta sẽ add vào cuối 1 cách khéo léo để không phải code quá complex.
        //
        //- Áp dụng tư duy add các phần tử đầu vào sau
        //+ Ta sẽ add các phần tử với index nhỏ vào cuối thay thế bởi (index+n)
        //Ex:
        //n=6
        //RRRDDD
        //queue(R) = 0,1,2
        //queue(D) = 3,4,5
        //
        //queue(R) = 1,2,6
        //queue(D) = 3,4,5
        //
        //+ 0<3 ==> Lấy 0 + vẫn cần 0 để xét tiếp nếu có senator nào cần ban thằng index=0 này
        //--> Ở đây ta sẽ làm 1 cách là (add index này) vào cuối + thay đổi value của nó thành (index+n)
        // 0 ==> 0+6 = 6 thì ta thấy rằng chỉ có thằng index_d=5 là có thể dùng thằng 6 này ==> Đúng vai trò rotate rồi
        //- Cứ làm như thế đến khi queue empty()
        //
        //4.1, Optimization
        //- Bài này có thể tối ưu xuống single queue
        //
        //4.2, Complexity:
        //- N is the length of string
        //- Space: O(n)
        //- Time : O(n)
        //
        //5, Single queue
        //5.1, Idea
        //- Ở đây ta sẽ không ban ngay mà sinh ra floating ban cho mỗi R và D
        //+ Chi khi gặp R và D ta sẽ ban (Kiểu dạng án treo)
        //- Nếu gặp R mà floating ban của R>0 ==> Ta sẽ ban R ngay
        //<> nếu floating ban ==0 --> Ta sẽ lấy character hiện tại đi ban senator khác.
        //- Vì dùng count nên kết quả sẽ luôn luôn rotate.
        //
        //5.1, Optimization
        //5.3,
        //- N is the length of string
        //- Space: O(n)
        //- Time : O(n)
//        String senator="DDRRR";
        //DD(R)RR
        //DD(R)RR
//        String senator="RRDDDRRD";
//        String senator="RD";
//        String senator="DRRDRDRD";
//        String senator="DRRDRDRDRDDRDRDR";
        //DRRDRDRDRDDRDRDR
        //D(R)RDRDRDRDDRDRDR
        //D(R)R(D)RDRDRDDRDRDR
        //D(R)R(D)R(D)RDRDDRDRDR
        //D(R)R(D)R(D)R(D)RDDRDRDR
        //D(R)R(D)R(D)R(D)R(D)DRDRDR
        //D(R)R(D)R(D)R(D)R(D)D(R)DRDR
        //D(R)R(D)R(D)R(D)R(D)D(R)D(R)DR
        //D(R)R(D)R(D)R(D)R(D)D(R)D(R)D(R)
        //D(R)(R)(D)R(D)R(D)R(D)D(R)D(R)D(R)
        //D(R)(R)(D)R(D)R(D)R(D)(D)(R)D(R)D(R)
        //D(R)(R)(D)(R)(D)R(D)R(D)(D)(R)D(R)D(R)
        //D(R)(R)(D)(R)(D)R(D)R(D)(D)(R)(D)(R)D(R)
        //D(R)(R)(D)(R)(D)(R)(D)R(D)(D)(R)(D)(R)D(R)
        //D(R)(R)(D)(R)(D)(R)(D)R(D)(D)(R)(D)(R)D(R)
//        String senator="DRRDRDRDRDDRDRDRD";
        String senator="DDRRR";
        //DD(R)RR
        //(D)D(R)(R)R
        //count(R)=8
        //count(D)=9
        //D - R
        //R - D
        //R - D
        //R - D
        //R - D
        //D - R
        //D - R
        //D - R
        //
        //D ==> Dire
//        System.out.println(predictPartyVictory(senator));
        System.out.println(predictPartyVictorySlow(senator));
        System.out.println(predictPartyVictoryRefactor(senator));
        System.out.println(predictPartyVictoryBinarySearch(senator));
        System.out.println(predictPartyVictoryQueue(senator));
        System.out.println(predictPartyVictoryQueueOptimization(senator));
    }
}
