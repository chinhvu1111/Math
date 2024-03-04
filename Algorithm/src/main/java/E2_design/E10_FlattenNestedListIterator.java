package E2_design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class E10_FlattenNestedListIterator {

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public static class NestedIteratorWrong{

        List<NestedInteger> nestedList;
        Stack<Iterator<NestedInteger>> curIterStack;
        Iterator<NestedInteger> curIter;
        List<NestedInteger> curList;
        Stack<List<NestedInteger>> curListStack;
        public NestedIteratorWrong(List<NestedInteger> nestedList) {
            this.nestedList=nestedList;
            curIterStack=new Stack<>();
            curListStack=new Stack<>();
            this.curIter =nestedList.iterator();
            this.curList=nestedList;
//            curIterStack.add(this.curStack);
        }

        public int getElement(){
            if(this.curIter.hasNext()){
                NestedInteger curElement=this.curIter.next();
                if(curElement.isInteger()){
                    return curElement.getInteger();
                }else{
                    curIterStack.add(this.curIter);
                    this.curIter =curElement.getList().iterator();
                    this.curListStack.add(curElement.getList());
                    return getElement();
                }
            }else if(!curIterStack.isEmpty()){
                this.curIter =curIterStack.pop();
                return getElement();
            }
            return -1;
        }

        public Integer next() {
            return getElement();
        }

        public boolean checkExist(List<NestedInteger> list){
            if(list.isEmpty()){
                return false;
            }
            System.out.println(list.size());
            for(NestedInteger e: list){
                if(e.isInteger()){
                    System.out.println("Loop int: "+e.getInteger());
                    return true;
                }else if(checkExist(e.getList())){
                    System.out.println("Loop: "+e.getList().size());
                    return true;
                }
            }
            return false;
        }

        public boolean exist(){
            return checkExist(this.curList);
        }

        public boolean hasNext() {
            return exist();
        }
    }

    public static class NestedIterator{

        public List<Integer> curList;
        public int pos;
        public NestedIterator(List<NestedInteger> nestedList) {
//            curIterStack.add(this.curStack);
            curList=new ArrayList<>();
            flatten(nestedList);
            pos=0;
        }

        public void flatten(List<NestedInteger> nestedList){
            for(NestedInteger e: nestedList){
                if(e.isInteger()){
                    curList.add(e.getInteger());
                }else{
                    flatten(e.getList());
                }
            }
        }

        public Integer next() {
            return curList.get(pos++);
        }

        public boolean hasNext() {
            return pos < curList.size();
        }
    }

    public static class NestedIntegerClass {
        Integer num=null;
        List<NestedIntegerClass> numList=null;
        public NestedIntegerClass(Integer num, List<NestedIntegerClass> numList){
            this.numList=new ArrayList<>();
            this.num=num;
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger(){
            return num!=null;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger(){
            return num;
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedIntegerClass> getList(){
            return numList;
        }
    }

    public static boolean checkExist(List<NestedIntegerClass> list){
        if(list.isEmpty()){
            return false;
        }
        System.out.println(list.size());
        for(NestedIntegerClass e: list){
            if(e.isInteger()){
                System.out.println("Loop int: "+e.getInteger());
                return true;
            }else if(checkExist(e.getList())){
                System.out.println("Loop: "+e.getList().size());
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        // Requirement
        //- Flatten list
        //- Each element is either an integer or a list whose elements may also be integers or other lists.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nestedList.length <= 500
        //The values of the integers in the nested list is in the range [-106, 106].
        //
        //- Brainstorm
        //Ex:
        //nestedList = [1,[4,[6]]]
        //
        //- Ban đầu mình dùng stack để lưu iterator và list để lưu thông tin current elements
        //==> Không cần thiết.
        //- Next() : mình đang làm thao tác change current iterator
        //- HasNext() cần phải được call nhiều lần --> Mà không thay đổi current iterator
        //==> Nhưng ở đây mình đang thay đổi --> Sai.
        //
        //- Idea:
        //- Ta sẽ luôn flatten ngay từ đầu + add element vào list thay vì dùng iterator
        //- Lưu lại position scan ==> Có thể check được.
        //
        //1.2, Optimization
        //1.3, Complexity:
        //- Space:
        //- Time:
//        List<NestedInteger> nestedList=new ArrayList<>();
//        nestedList.add(1);
//        NestedIterator nestedIterator=new NestedIterator();
        List<NestedIntegerClass> list=new ArrayList<>();
        List<NestedIntegerClass> subList=new ArrayList<>();
        subList.add(new NestedIntegerClass(1, null));
        subList.add(new NestedIntegerClass(1, null));
        NestedIntegerClass nestedIntegerClass=new NestedIntegerClass(null,subList);
        list.add(nestedIntegerClass);
        list.add(new NestedIntegerClass(1, null));
        list.add(nestedIntegerClass);
        System.out.println(checkExist(list));
//        System.out.println(list);
//        System.out.println(list.iterator().hasNext());
        //- Không rõ tại sao cái submit bị timeout
        //#Reference:
        //51. Flatten 2D Vector
        //385. Mini Parser
        //565. Array Nesting
        //
    }
}
