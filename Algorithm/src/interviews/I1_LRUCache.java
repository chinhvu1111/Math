package interviews;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

class LRUCache{
    private LinkedList<Integer> usage=null;
    private int cache[]=null;
    private int capacity=0;
    private int currentLength=0;

    public LRUCache(int capacity) {
        usage=new LinkedList();
        cache=new int[10000];
        Arrays.fill(cache, -1);
        this.capacity=capacity;
    }

    public int get(Integer key) {
        if(key>=10000||cache[key]==-1){
            return -1;
        }
        if(cache[key]!=-1){
            usage.remove(key);
        }
        usage.add(key);
        return cache[key];
    }

    public void put(int key, int value) {
        if(currentLength+1>capacity){
            Integer e=usage.pop();
            cache[e]=-1;
        }else {
            this.currentLength++;
        }
        cache[key]=value;
        usage.add(key);
    }

    public int[] getCache() {
        return cache;
    }

    public int getCapacity() {
        return capacity;
    }
}

public class I1_LRUCache {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}
