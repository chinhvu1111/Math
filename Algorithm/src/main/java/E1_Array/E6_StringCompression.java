package E1_Array;

import java.util.ArrayList;
import java.util.List;

public class E6_StringCompression {

    public static int[] getNumberOfDigit(int x, char[] result,int index){
        int count=0;
        //126
        //12
        //1
        List<Character> list=new ArrayList<>();
        while(x!=0){
            list.add((char)(x%10 +'0'));
            x=x/10;
            count++;
        }
        int n=list.size();

        for(int i=n-1;i>=0;i--){
            result[index++]=list.get(i);
        }
        return new int[]{count, index};
    }

    public static int compress(char[] chars) {
        int low=0, high=low;
        int n=chars.length;
        int rs=0;
        char[] result=new char[n];
        int index=0;

        while(high<n){
            while(high+1<n&&chars[high+1]==chars[low]){
                high++;
            }
            // System.out.printf("Low: %s high: %s, char: %s\n", low, high, chars[high]);
            rs++;
            int value=high-low+1;
            result[index++]=chars[low];
            if(value!=1){
                int[] tempRs=getNumberOfDigit(value, result, index);
                // System.out.printf("Low: %s high: %s, char %s, %s %s %s\n", low, high, chars[high], value, chars[low], tempRs[0]);
                rs+=tempRs[0];
                index=tempRs[1];
            }
            high++;
            low=high;
        }
        for(int i=0;i<n;i++){
            // System.out.printf("%s, ", result[i]);
            chars[i]=result[i];
        }
        chars=result;
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //-
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //
        //#Reference:
        //283. Move Zeroes
        //38. Count and Say
        //271. Encode and Decode Strings
        //604. Design Compressed String Iterator
    }
}
