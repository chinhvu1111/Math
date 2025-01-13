package E1_daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E227_SubdomainVisitCount {

    public static List<String> subdomainVisits(String[] cpdomains) {
        int n= cpdomains.length;
        HashMap<String, Integer> domainToCount=new HashMap<>();

        for(int i=0;i<n;i++){
            String curDomain=cpdomains[i];
            int m=curDomain.length();
            StringBuilder curRs=new StringBuilder();
            StringBuilder numberStr=new StringBuilder();

            for (int j = 0; j < m; j++) {
                if(curDomain.charAt(j)==' '){
                    break;
                }
                numberStr.append(curDomain.charAt(j));
            }
            int number=Integer.parseInt(numberStr.toString());
            for(int j=m-1;j>=0;j--){
                if(curDomain.charAt(j)=='.'||curDomain.charAt(j)==' '){
                    domainToCount.put(curRs.toString(), domainToCount.getOrDefault(curRs.toString(), 0)+number);
                }
                if(curDomain.charAt(j)==' '){
                    break;
                }
                curRs.insert(0, curDomain.charAt(j));
            }
        }
        List<String> rs=new ArrayList<>();

        for(Map.Entry<String, Integer> e: domainToCount.entrySet()){
            String curRs=e.getValue()+" "+e.getKey();
            rs.add(curRs);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A website domain "discuss.leetcode.com" consists of various subdomains.
        //- At (the top level), we have "com", at (the next level), we have "leetcode.com" and at (the lowest level), "discuss.leetcode.com".
        // When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" (implicitly).
        //- (A count-paired domain) is a domain that has (one of the two formats) ("rep d1.d2.d3" or "rep d1.d2")
        // where rep is the number of visits to the domain and (d1.d2.d3) is the domain itself.
        //  + For example, "9001 discuss.leetcode.com" is a count-paired domain that indicates that discuss.leetcode.com was visited 9001 times.
        //- Given (an array of count-paired domains cpdomains),
        //* return (an array of the count-paired domains) of (each subdomain) in the input.
        //- You may return the answer in any order.
        //
        //Example 1:
        //
        //Input: cpdomains = ["9001 discuss.leetcode.com"]
        //Output: ["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
        //Explanation: We only have one website domain: "discuss.leetcode.com".
        //As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= cpdomain.length <= 100
        //1 <= cpdomain[i].length <= 100
        //cpdomain[i] follows either the "repi d1i.d2i.d3i" format or the "repi d1i.d2i" format.
        //repi is an integer in the range [1, 104].
        //d1i, d2i, and d3i consist of lowercase English letters.
        //
        //- Brainstorm
        //
        //Example 2:
        //
        //Input: cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
        //Output: ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
        //Explanation: We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times.
        //For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
        //==> This example is in easy level
        //
        String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        System.out.println(subdomainVisits(cpdomains));
        //
        //#Reference:
        //2014. Longest Subsequence Repeated k Times
        //963. Minimum Area Rectangle II
        //2029. Stone Game IX
    }
}
