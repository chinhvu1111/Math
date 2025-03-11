package E1_daily;

public class E237_NeighboringBitwiseXOR {

    public static boolean doesValidArrayExistWrong(int[] derived) {
        int n= derived.length;
        if(n==1){
            return derived[0]==0;
        }

        for(int i=1;i<n;i++){
            if(i!=n-1){
                if((derived[i]^derived[i-1])!=0){
                    return false;
                }
            }else{
                if(n==2){
                    return derived[i]==derived[i-1];
                }else if(n==3){
                    return derived[i]==0;
                }else if(n==4){
                    return derived[i] == (derived[1]^derived[n-2]);
                }else if(n==5){
                    return derived[i]==0;
                }else{
                    return derived[i]== (derived[2]^derived[n-3]);
                }
            }
        }
        return true;
    }

    public static boolean doesValidArrayExist(int[] derived) {
        int n= derived.length;
        if(n==1){
            return derived[0]==0;
        }
        int[] org = new int[n+1];

        //org[i] xor org[i+1] = derived[i]
        for(int i=0;i<n;i++){
            org[i+1] = derived[i]^org[i];
        }
        boolean firstElementZero = org[n] == org[0];
        org[0]=1;
        for(int i=0;i<n;i++){
            org[i+1] = derived[i]^org[i];
        }
        boolean firstElementOne = org[n] == org[0];
        return firstElementOne||firstElementZero;
    }

    public static boolean doesValidArrayExistOptimization(int[] derived) {
        int n= derived.length;
        int xorVal=0;
        for (int i = 0; i < n; i++) {
            xorVal=xorVal^derived[i];
        }
        return xorVal==0;
    }

    public static boolean doesValidArrayExistOptimization1(int[] derived) {
        int sum = 0;
        for (int num : derived) {
            sum += num;
        }
        return sum % 2 == 0;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A 0-indexed array derived with length n is derived by computing the bitwise XOR (⊕) of adjacent values
        // in a binary array original of length n.
        //- Specifically, for each index i in the range [0, n - 1]:
        //  + If i = n - 1, then derived[i] = (original[i] ⊕ original[0]).
        //  + Otherwise, derived[i] = (original[i] ⊕ original[i + 1]).
        //- Given an array derived, your task is to determine whether there exists (a valid binary array original) that could have (formed derived).
        //* Return true if such an array exists or false otherwise.
        //- (A binary array) is an array containing only 0's and 1's
        //
        //Example 1:
        //
        //Input: derived = [1,1,0]
        //Output: true
        //Explanation: A valid original array that gives derived is [0,1,0].
        //derived[0] = original[0] ⊕ original[1] = 0 ⊕ 1 = 1
        //derived[1] = original[1] ⊕ original[2] = 1 ⊕ 0 = 1
        //derived[2] = original[2] ⊕ original[0] = 0 ⊕ 0 = 0
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == derived.length
        //1 <= n <= 10^5
        //The values in derived are either 0's or 1's
        //  + n<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //Ex:
        //
        //derived = [a,b,c]
        //arr = [e,f,g]
        //e xor f = a
        //f xor g = b
        //g xor e = c
        //- e == g
        //- How to check using the revert logic:
        //
        //- From derived how to check the relationship between them
        //Ex:
        //derived = [a,b,c]
        //arr = [e,f,g]
        //e xor f = a
        //f xor g = b
        //g xor e = c
        //- How to check e == g:
        //  ==> a xor b == 0
        //  + e == g
        //
        //- For index=n-1:
        //Ex:
        //derived = [a,b,c,d,e,f,g]
        //arr =     [h,k,i,l,m,n,o]
        //h xor k = a
        //k xor i = b
        //i xor l = c
        //l xor m = d
        //m xor n = e
        //n xor o = f
        //o xor h = g
        //- Check b:
        //+ h == i
        //  ==> (a xor b) == 0
        //- Check c: k == l
        //  ==> (b xor c) == 0
        //
        //- Check g:
        //  g = a xor g
        //  + g == e
        //  + a == c
        //  ==> g = e xor c
        //  Check:
        //      + derived[n-1] == derived[n-3] xor derived[2]
        //
        //- Size = 2
        //- derived = [1,1]
        //derived = [a,b]
        //arr     = [c,d]
        //c = a xor b
        //d = a xor b
        //  + derived[0] == derived[1]
        //- Size = 3
        //derived = [a,b,c]
        //arr     = [c,d,e]
        //a = c xor d
        //b = d xor e
        //c = a xor c
        //  + c==0
        //
        //- Size = 4
        //derived = [a,b,c,d]
        //arr     = [d,e,f,g]
        //a = d xor e
        //b = e xor f
        //c = f xor g
        //d = a xor d
        //  + a == c
        //  + d == b
        //  + d = derived[1] xor derived[n-2]
        //
        //- Size = 5
        //derived = [a,b,c,d,e1]
        //arr     = [e,f,g,h,i]
        //a = f xor e
        //b = f xor g
        //c = g xor h
        //d = h xor i
        //e1 = e xor i
        //  e1 = g xor g
        //  ==> e1 == 0
        //
        //- Size = 6
        //derived = [(a),b,[c],[d],e,(f)]
        //arr     = [g,h,i,k,l,m]
        //  + derived[i] = derived[2] xor derived[n-3]
        //
//        int[] derived = {1,1,0};
//        int[] derived = {1,1};
        int[] derived = {0,1,1};
        //0,1,0
        //0 xor 1 = 1
        //1 xor 1 = 0
        System.out.println(doesValidArrayExistWrong(derived));
        //
        //* Sai đề
        //- (A binary array) is an array containing only 0's and 1's
        //
        //Ex:
        //- Size = 5
        //derived = [a,b,c,d,e1]
        //arr     = [e,f,g,h,i]
        //a = f xor e
        //b = f xor g
        //c = g xor h
        //d = h xor i
        //e1 = e xor i
        //xor[1,1,1] = 1
        //xor[1,0,1] = 0
        //xor [1,1,0] = 0
        //
        //nums =    [1,0,0,1,1,0]
        //derived  =[1,0,1,0,1,1]
        //
        //* Main point:
        //- We can fill the first element as 0 or 1
        //==> Base on that we can check whether the created original array is (valid or not)
        //
        //- For the last one:
        //  - Calculate org[n+1] = derived[n-1]^org[n-1]
        //Ex:
        //derived = [1,1,0]
        //  + org = [0,1,0,0]
        //  - Calculate:
        //      + org[n] = derived[n-1]^org[n-1]
        //      => derived[n-1] = org[n]^org[n-1]
        //      We also have:
        //      + derived[n-1] = org[0]^org[n-1]
        //  ==> Check (org[n]==org[0])
        //
        //1.1, Optimization
        //* Solution 2:
        //- Each element appear twice:
        //  + Cumulative XOR
        //
        //* Solution 1:
        //- Số chẵn + lẻ ==> Cũng thể hiện phép tính XOR
        //  + Lẻ ==> Thừa 1 số
        //  + Chẵn ==> Các số xuất hiện chẵn lần
        //
        //1.2, Complexity
        //- Space: O(n) => O(1)
        //- Time: O(n)
        //
        System.out.println(doesValidArrayExist(derived));
        System.out.println(doesValidArrayExistOptimization(derived));
        System.out.println(doesValidArrayExistOptimization1(derived));
        //
    }
}
