package interviews;

import java.util.*;

public class E235_PeekingIterator_design_treeset {

    class PeekingIterator implements Iterator<Integer> {
        int indexPeek =-1;
        public List<Integer> list=new ArrayList<>();

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            while (iterator.hasNext()){
                list.add(iterator.next());
            }
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return list.get(indexPeek+1);
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            return list.get(++indexPeek);
        }

        @Override
        public boolean hasNext() {
            return list.size()>indexPeek+2;
        }
    }

    class PeekingIteratorRefactor implements Iterator<Integer> {
        public Deque<Integer> list=new LinkedList<>();

        public PeekingIteratorRefactor(Iterator<Integer> iterator) {
            // initialize any member here.
            while (iterator.hasNext()){
                list.addLast(iterator.next());
            }
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return list.peekFirst();
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            return list.removeFirst();
        }

        @Override
        public boolean hasNext() {
            return list.size()>0;
        }
    }

    public static void main(String[] args) {

    }
}
