package E2_design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E5_ZigzagIterator {

    public class ZigzagIterator {
        public int index1;
        public int index2;
        public boolean turn;
        public List<Integer> v1;
        public List<Integer> v2;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            index1=0;
            index2=0;
            turn=true;
            this.v1=v1;
            this.v2=v2;
        }

        public int next() {
            if(turn){
                turn=!turn;
                if(index1<v1.size()){
                    return v1.get(index1++);
                }else if(index2<v2.size()){
                    return v2.get(index2++);
                }
            }else{
                turn=!turn;
                if(index2<v2.size()){
                    return v2.get(index2++);
                }else if(index1<v1.size()){
                    return v1.get(index1++);
                }
            }
            return -1;
        }

        public boolean hasNext() {
            return index1<v1.size()||index2<v2.size();
        }
    }

    public static class ZigzagIteratorCyclic {
        public class Iterator{
            public int index;
            public List<Integer> curList;
            public Iterator(List<Integer> curList){
                index=0;
                this.curList=curList;
            }
            public boolean hashNext(){
                return index<curList.size();
            }

            public int next(){
                return curList.get(index++);
            }

            @Override
            public String toString() {
                return curList.toString()+", index: "+index;
            }
        }

        public Queue<Iterator> listIterators;

        public ZigzagIteratorCyclic(List<Integer> v1, List<Integer> v2) {
            listIterators=new LinkedList<>();
            if(v1.size()!=0){
                listIterators.add(new Iterator(v1));
            }
            if(v2.size()!=0){
                listIterators.add(new Iterator(v2));
            }
        }

        public int next() {
            Iterator curIter = listIterators.poll();
            while(!listIterators.isEmpty()&&curIter!=null&&!curIter.hashNext()){
                curIter=listIterators.poll();
            }
            if(curIter==null||!curIter.hashNext()){
                return -1;
            }
            int val=curIter.next();
            if(curIter.hashNext()){
                listIterators.add(curIter);
            }
            return val;
        }

        public boolean hasNext() {
            return !listIterators.isEmpty();
        }
    }

    public static void main(String[] args) {
        // Requirement
        //- Given two vectors of integers v1 and v2
        //* Implement an iterator to return their elements "alternately".
        //Ex:
        //Input: v1 = [1,2], v2 = [3,4,5,6]
        //Output: [1,3,2,4,5,6]
        //* Follow up:
        //- What if you are given k vectors? How well can your code be extended to such cases?
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //0 <= v1.length, v2.length <= 1000
        //1 <= v1.length + v2.length <= 2000
        //-2^31 <= v1[i], v2[i] <= 2^31 - 1
        //
        //- Brainstorm
        //- Nếu làm bình thường ta dùng 2pointers + if/else là được.
        //
        //- Nếu số vectors scale lên thì sao?
        //  + Từ zigzag nó trở thành cyclic
        //- Tức là giả sử vẫn là 2 vectors được passed to input
        //==> Làm sao có thể làm bài này theo cyclic được.
        //- Dùng Queue để lưu các (list vào cycle/ Interator của mỗi list vào)
        //==> Ở đây mình prefer using the interator as the instance : Vì dùng list thì không lưu được index vào instance trong Queue được.
        //- Xét đến e --> (remove (e) first) + (add(e) vào last)
        //- Build the iterator for each list:
        //+ Next() : own iterator()
        //+ HasNext(): check whether it can call next or not
        //
        //- Các method bên ngoài:
        //+ hasNext() : !list.isEmpty()
        //+ next() :
        // + Vì trước khi next ==> Nó sẽ check hasNext bên trên.
        // + Logic next() chỉ liên quan đến size() của queue
        // ==> Ta sẽ phải:
        //   + Check các list có next() được nữa không trước khi add vào queue
        //   + Ngay cả các list đầu vào v1, v2 cũng phải check empty trước khi add vào queue tránh trường hợp bị cases:
        //Ex:
        //v1=[1]
        //v2=[]
        //==> Call được next 2 lần vì có 2 element ==> Lần call thứ 2 sẽ sai vì:
        // + v2 không có element mà vẫn call do (queue is not empty ( do ta add đủ v1 và v2 vào queue)
        //
        //#Reference:
        //251. Flatten 2D Vector
        //341. Flatten Nested List Iterator
        List<Integer> v1=new ArrayList<>();
        v1.add(1);
        List<Integer> v2=new ArrayList<>();
        v2.add(1);
        ZigzagIteratorCyclic zigzagIteratorCyclic=new ZigzagIteratorCyclic(v1, v2);
        System.out.println(zigzagIteratorCyclic.next());
        System.out.println(zigzagIteratorCyclic.next());
    }
}
