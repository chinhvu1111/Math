package contest;

public class E193_FindTheKthCharacterInStringGameI {

    public static char kthCharacter(int k) {
        StringBuilder initStr=new StringBuilder("a");
        while (initStr.length()<k){
            int size= initStr.length();
            for(int j=0;j<size;j++){
                initStr.append((char)(initStr.charAt(j)+1%'z'));
            }
        }
//        System.out.println(initStr);
        return initStr.charAt(k-1);
    }

    public static void main(String[] args) {
        //* Requirement
        //- Alice and Bob are playing a game. Initially, Alice has a string word = "a".
        //You are given a positive integer k.
        //Now Bob will ask Alice to perform the following operation forever:
        //Generate a new string by changing each character in word to its next character in the English alphabet, and append it to the original word.
        //For example, performing the operation on "c" generates "cd" and performing the operation on "zb" generates "zbac".
        //Return the value of the kth character in word, after enough operations have been done for word to have at least k characters.
        //Note that the character 'z' can be changed to 'a' in the operation.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= k <= 500
        //
        //** Brainstorm
        System.out.println(kthCharacter(5));
        System.out.println(kthCharacter(10));
    }
}
