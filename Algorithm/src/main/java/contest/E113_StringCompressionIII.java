package contest;

public class E113_StringCompressionIII {

    public static String compressedString(String word) {
        int n= word.length();
        StringBuilder rs=new StringBuilder();
        if(n==0){
            return "";
        }
        char c=word.charAt(0);
        int count=1;

        for(int j=1;j<n;j++){
            if(word.charAt(j)==c){
                count++;
                if(count==10){
                    count=1;
                    rs.append(9).append(c);
                }
            }else{
                rs.append(count).append(c);
                c=word.charAt(j);
                count=1;
            }
        }
        rs.append(count).append(c);
        return rs.toString();
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given a string word, compress it using the following algorithm:
        //Begin with an empty string comp. While word is not empty, use the following operation:
        //<ul>
        //	<li>Remove a maximum length prefix of <code>word</code> made of a <em>single character</em> <code>c</code> repeating <strong>at most</strong> 9 times.</li>
        //	<li>Append the length of the prefix followed by <code>c</code> to <code>comp</code>.</li>
        //</ul>
        //</li>
        //Return the string comp.
        //
        //- Tức là chỉ được phép ghi nhận max(length) = 9
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= word.length <= 2 * 10^5
        //word consists only of lowercase English letters.
        //=> Only 26 characters
        //
        //- Brainstorm
        //
        //
//        String word = "aaaaaaaaaaaaaabb";
//        String word = "abcde";
        String word = "a";
        System.out.println(compressedString(word));
    }
}
