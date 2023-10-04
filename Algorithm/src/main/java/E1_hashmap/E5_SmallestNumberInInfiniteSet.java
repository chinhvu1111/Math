package E1_hashmap;

import java.util.TreeSet;

public class E5_SmallestNumberInInfiniteSet {
    public TreeSet<Integer> currentSet;
    public int min=1;

    public E5_SmallestNumberInInfiniteSet() {
        currentSet=new TreeSet();
    }

    public int popSmallest() {
        if(currentSet.isEmpty()){
            return min++;
        }
        if(currentSet.first()<min){
            return currentSet.pollFirst();
        }
        return min++;
    }

    public void addBack(int num) {
        if(min<=num){
            return;
        }
        currentSet.add(num);
    }
    public static void main(String[] args) {
        //** Requirement
        //- Cho một set bao gồm all các positive integer : 1,2,3,4...
        //* Implement infinite set bao gồm các thao tác:
        //+ pop smallest integer from set + return
        //+ add new element num back to infinite set if it is not already in the infinite set.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //
        //- Brainstorm
        //+ 1 <= num <= 1000
        //+ At most 1000 calls will be made in total to popSmallest and addBack.
        //
        //- Idea
        //- Ở đây ta có thể có cases:
        //+ pop() : remove 5 ==> set còn 6,7,8,9
        //+ add(3) : add 1 ==> set : 3,6,7,8,9
        //+ add(4) : add 3 ==> set : 2,3,4,6,7,8,9
        //
        //- Idea
        //- min variable --> all number 1 --> n
        //- set : list of element will be added
        //- Nếu add thêm phần tử thì ta sẽ add thêm vào set
        //- Nếu pop phần tử đi thì ta có thể so sánh (min của variable và min của set) thì có thể xảy ra cases
        //+ Pop phần tử max ==> Không có trong đề bài
        //+ Pop phần tử min : Ta sẽ so sánh min của variable và min của set ==> nếu min cái nào smaller:
        //  + min++
        //  + minset.pollFirst()
        //+ Pop phần tử ở giữa ==> Không có trong đề bài
        //Ex:
        //+ pop() : remove 5 ==> min=6, max=9, set=none
        //+ add(3) : add 1 ==> set=3, min=6, max=9
        //+ add(4) : add 3 ==> set=3,4, min=6, max=9
        //
        //1.1, Optimization
        //- Ta có thể bỏ max đi vì bài này dường như không cần cận trên (max)
        //
        //1.2, Complexity:
        //- N is the number of element that we want to add
        //- M is the number of element that we want to remove
        //- Space : O(n)
        //- Time : O((n+m) * log(n))
        //
        //#Reference:
        //41. First Missing Positive
    }
}
