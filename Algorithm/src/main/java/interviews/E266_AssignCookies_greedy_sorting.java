package interviews;

import java.util.Arrays;

public class E266_AssignCookies_greedy_sorting {

    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index=0, index1=0;

        while (index<g.length&&index1<s.length){
            if(g[index]<=s[index1]){
                index++;
                index1++;
            }else{
                index1++;
            }
        }
        return index;
    }

    public static int findContentChildrenRefactor(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count=0;
        for(int i=g.length-1,j=s.length-1; j>=0 && i>=0 ;i--)
        {
            if(s[j]>=g[i])
            {
                count++;
                j--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] g=new int[]{1,2,3};
//        int[] s=new int[]{1,1};
        int[] g=new int[]{10,9,8,7};//7,8,9,10
        int[] s=new int[]{5,6,7,8};//5,6,7,8
        System.out.println(findContentChildren(g,s));
        System.out.println(findContentChildrenRefactor(g,s));
        //#Reference:
        //456. 132 Pattern
        //1463. Cherry Pickup II
        //1423. Maximum Points You Can Obtain from Cards
        //2237. Count Positions on Street With Required Brightness
    }
}
