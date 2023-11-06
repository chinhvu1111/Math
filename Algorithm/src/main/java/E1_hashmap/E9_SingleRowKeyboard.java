package E1_hashmap;

public class E9_SingleRowKeyboard {

    public static int calculateTime(String keyboard, String word) {
        int[] charToPost=new int[26];
        int prevIndex=0;
        int m=word.length();
        int rs=0;

        for(int i=0;i<26;i++){
            charToPost[keyboard.charAt(i)-'a']=i;
        }
        for(int i=0;i<m;i++){
            rs+=Math.abs(charToPost[word.charAt(i)-'a']-prevIndex);
            prevIndex=charToPost[word.charAt(i)-'a'];
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25).
        //- Initially, your finger is at index 0.
        //- The time taken to move your finger from index i to index j is |i - j|.
        //- You want to type a string word.
        //* Write a function to calculate
        // - how much time it takes to type it (with one finger).
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //keyboard.length == 26
        //keyboard contains each English lowercase letter exactly once in some order.
        //1 <= word.length <= 104
        //word[i] is an English lowercase letter.
        //==> character trong keyboard là distinct ==> bên trên "English lowercase letter exactly once in some order"
        //- lower case ==> 26 size
        //
        //- Brainstorm
        //- Ở cho string keyboard ==> Bàn phím
        //- ta sẽ dùng int[] để lưu vị trí của pos[key]=index
        //- Sau đó traverse word để lấy thông tin ==> Cần phải lưu lại prevIndex
        //- len(word)==1: ta chỉ cần return key của word là được
        //==> Init PrevIndex = 0 for all cases
        //1.1, Optimization
        //1.2, Complexity:
        //- N is the length of keyboard
        //- N is the length of word
        //- Space: O(26)
        //- Time : O(n+m)
        //
        //#Reference:
        //953. Verifying an Alien Dictionary
        //1816. Truncate Sentence
        //2150. Find All Lonely Numbers in the Array
        //
//        String keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba";
        String keyboard = "1", word = "1";
        System.out.println(calculateTime(keyboard, word));
    }
}
