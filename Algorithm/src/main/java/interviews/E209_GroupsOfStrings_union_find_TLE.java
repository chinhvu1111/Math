package interviews;

import java.util.HashMap;
import java.util.Objects;

public class E209_GroupsOfStrings_union_find_TLE {

    public static boolean isSameGroupWrongSubject(String s, String s1) {
        int n = s.length();
        int m = s1.length();
        if (Math.abs(n - m) >= 2) {
            return false;
        }
        int countDiff = 0;
        int startS = 0;
        int startS1 = 0;
        String newS = s;
        String newS1 = s1;

        if (n < m) {
            String temp = newS;
            newS = newS1;
            newS1 = temp;
        }

        while (startS < n && startS1 < m) {
            if (newS.charAt(startS) != newS1.charAt(startS1)) {
                if (n != m) {
                    startS++;
                } else {
                    startS++;
                    startS1++;
                }
                countDiff++;
            } else {
                startS++;
                startS1++;
            }
        }

        if (countDiff == 1) {
            return true;
        } else return countDiff <= 1;
    }

    public static boolean isSameGroup(String s, String s1, HashMap<String, Integer> hashMapWords) {
        Integer bitmaskS = hashMapWords.get(s);
        Integer bitmaskS1 = hashMapWords.get(s1);

        if (Math.abs(s.length() - s1.length()) >= 2) {
            return false;
        }
        //Equal case
        if (Objects.equals(bitmaskS, bitmaskS1)) {
            return true;
        }
        int xorBitmask = bitmaskS ^ bitmaskS1;

        //Delete and replace case
//        if(((bitmaskS^bitmaskS1)%2)==0){
//            return true;
//        }
        int countBit1 = 0;

        //Replace case
        while (xorBitmask != 0) {
            int currentBit;
            currentBit = xorBitmask % 2;
//            System.out.printf("%s ", currentBit);
            xorBitmask = xorBitmask >> 1;
            if (currentBit == 1) {
                countBit1++;
                if (countBit1 == 3) {
                    return false;
                }
            }
        }
//        System.out.println();
        return true;
    }

    public static int flipBitK(int k, int currentMask) {
        return 1;
    }

    public static int getBitK(int k, int currentMask) {
        return (currentMask >> k) & 1;
    }

    public static int findParent(int[] parent, int u) {
        while (parent[u] != u) {
            u = parent[u];
        }
        return u;
    }

    public static void union(int[] size, int[] height, int[] parent, int u, int v) {
        int parentU = findParent(parent, u);
        parent[u] = parentU;
        int parentV = findParent(parent, v);
        parent[v] = parentV;

        if (parentU == parentV) {
            return;
        }
        numberGroupsInit--;
        if (height[parentU] > height[parentV]) {
            height[parentU] = height[parentV] + 1;
            size[parentU] += size[parentV];
            maxNumberOfGroup = Math.max(maxNumberOfGroup, size[parentU]);
            parent[parentV] = parentU;
//            System.out.printf("%s is parent of %s\n", parentU, parentV);
        } else {
            height[parentV] = height[parentU] + 1;
            parent[parentU] = parentV;
            size[parentV] += size[parentU];
            maxNumberOfGroup = Math.max(maxNumberOfGroup, size[parentV]);
//            System.out.printf("%s is parent of %s\n", parentV, parentU);
        }
    }

    public static int numberGroupsInit;
    public static int maxNumberOfGroup;

    public static int[] groupStringsUnionFind(String[] words) {
        int n = words.length;
        int[] parent = new int[n];
        int[] height = new int[n];
        int[] size = new int[n];
        numberGroupsInit = n;
        maxNumberOfGroup = 0;
        if (n != 0) {
            maxNumberOfGroup = 1;
        }
        HashMap<String, Integer> hashMapWords = new HashMap<>();

        for (String s : words) {
            int lS = s.length();
            int mask = 0;

            for (int i = 0; i < lS; i++) {
                mask |= 1 << s.charAt(i) - 'a';
            }
            hashMapWords.put(s, mask);
        }
        //+ case add
//        System.out.println(isSameGroup("a", "ab", hashMapWords));
        //+ case false
//        System.out.println(isSameGroup("ab", "cde", hashMapWords));
        //+ case replace
//        System.out.println(isSameGroup("cbe", "cde", hashMapWords));
        //+ case delete
//        System.out.println(isSameGroup("cea", "ca"));
//        System.out.println(isSameGroup("abe", "eba"));
        //+ Sai test case bỏ 1 ký tự trên cả 2 string
//        System.out.println(isSameGroup("dac", "dca", hashMapWords));
//        System.out.println(isSameGroup("ca", "bc", hashMapWords));
//        System.out.println(isSameGroup("ghnv", "tenv", hashMapWords));
//        System.out.println(isSameGroup("cae", "ea", hashMapWords));
//        System.out.println(isSameGroup("e", "ea", hashMapWords));

        for (int i = 0; i < n; i++) {
            height[i] = 1;
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSameGroup(words[i], words[j], hashMapWords)) {
                    System.out.printf("%s %s is same group\n", words[i], words[j]);
                    union(size, height, parent, i, j);
                }
            }
        }
//        for(int i=0;i<n;i++){
//            int parentOfINode=findParent(parent,i);
//            int currentValue=hashMap.getOrDefault(parentOfINode, 0)+1;
//
//            hashMap.put(parentOfINode, currentValue);
//            maxNumberOfGroup=Math.max(maxNumberOfGroup, currentValue);
//        }
        return new int[]{numberGroupsInit, maxNumberOfGroup};
    }

    public static void main(String[] args) {
//        String[] words=new String[]{"a","b","ab","cde"};
//        String[] words=new String[]{"a","ab","abc"};
//        String[] words=new String[]{"ghnv","uip","tenv","hvepx","e","ktc","byjdt","ulm","cae","ea"};
        String[] words = new String[]{"umeihvaq",
                "ezflcmsur", "ynikwecaxgtrdbu", "u", "q", "gwrv", "ftcuw", "ocdgslxprzivbja", "zqrktuepxs", "cpqolvnwxz", "geqis", "xgfdazthbrolci", "vwnrjqzsoepa", "udzckgenvbsty", "lpqcw", "nekpvchqfgdo", "iapjhxvdrmwetz", "gw", "waxokchnmifsruj", "vqp", "vbpkij", "ufjvbstzh", "swiu", "knslbdcahfrox", "ctofplkhednmv", "g", "zk", "idretzjbpl", "pxqdauys", "mfgrqaktbzpv", "vdtq", "wyxjrcie", "kl", "jpcdzmli", "oth", "yumdawhfbskcjo", "rvfksqhu", "swemnvjpg", "rnl", "zgd", "rmzdbcsqht", "ure", "qlusoaxprtebn", "zkbmvtpya", "jszxuwevfidkm", "smlft", "cpwugmbzfsqr", "cblkjevhp", "iyfnozaulex", "qvlok", "wsgm", "du", "awyplckj", "aey", "ycsjqnt", "vtoqzsyx", "ejqixsmrdhlofyp", "kvlmurbzjg", "lysdahgpwmrcn", "af", "jkezhdu", "etjzqiyghdnovm", "ycwdfnluoke", "kwshbx", "pyvaznljqwes", "xakinu", "e", "zjexfgvhtabwcy", "thuvwlnjkbxym", "jorzeslpidmhubq", "wnr", "qzdv", "qeovrbmwzgpdh", "jkioenptaygfubh", "bvndzxijope", "cudizhjntbes", "rnhzitpqoexwb", "ihezcmfqouyl", "q", "mwtsdjqn", "hrmc", "hxaocbyikluvqsf", "d", "vgwjzuaondbcm", "ibqxltf", "rzyhguptmesqo", "ruwgy", "jvprwhtzuf", "aupngodjexkiw", "yhijelwpvtsrbqc", "gtick", "koilywcfbs", "elv", "dehxzlitskq", "ptvbkql", "msfxyjahlzo", "oslxzfwrpmtyh", "gypuchkwa", "rsqij", "tw", "igbcylqfhtmjkr", "nryhzjgi", "pw", "bnfairow", "xjzrf", "olxfypjtmrncuv", "ifhue", "akcvofuyzwbj", "tvhxfeuiykpwbsz", "wnrztclfpm", "ozvypnfwrqg", "cwkgr", "gjyzrucplbsfe", "pdtzmfoy", "wehd", "bnvqhcmg", "uyw", "sgynxljqbf", "tvxbq", "wcmguioelbdrkvx", "okvtyexuj", "hjbc", "uidcswzm", "jemtkvshizaub", "rmb", "jpgnqdemzcxa", "dmalekhiyj", "akocedu", "rlpqufcv", "r", "lohgs", "xapnorj", "cdb", "icopdtzxy", "xcrflvojqgpkwt", "elv", "rp", "yv", "u", "atdxqeilhkg", "olfvmrgkb", "rplxskabvtqmhw", "n", "rldswkyoujmfxpn", "rvgejzdusoya", "hvoft", "wskgmjchz", "luagnzkj", "ywe", "i", "wcqtsk", "umpvywknjbxacsd", "ynavjpcrgq", "jyftmklci", "xfol", "zh", "kut", "zvawyielscotkn", "p", "wykpqdjoz", "uabtpxkvq", "uabtifwhrvxc", "sdcamqup", "srghwfptloxvke", "sfdywtx", "tuohnxzjqmac", "pwxjyhdurnfz", "axgfcuqtiyhjz", "rwqpyh", "bmoznqavicdgp", "jcu", "vnkc", "jpb", "nvfqyahjkul", "radpctwixygb", "pvjmk", "s", "dzyqjbwucne", "mgh", "ivc", "eaqc", "yjimsadtcwbgk", "lo", "ayirlsfevtwpnd", "wcsk", "xlvejy", "kcjrqf", "a", "ixsdga", "vk", "cqxyfotziwrvl", "zmxboiewhfdjlnr", "kdpwngf", "zyretijxpw", "ncw", "ljw", "mrxeciy", "aqwcofnjypsgi", "byuvhj", "ukidyqzhxgowmc", "cpqsmu", "auwmcrpdisbzokg", "pxgwmvfq", "azgljrsyeqwxfic", "xmlgpdrzwqe", "emgdcqntjpwrf", "hrwq", "zmjkx", "npabcide", "dvlfxnt", "kilqsvmborf", "lvsxjnbimhpzfow", "sqcym", "tcjmkwq", "yugkwdzvmteon", "pq", "nklmb", "azqcnodkimtxve", "ovpcfe", "uqkcwjimbvdyx", "xvdazh", "xk"};
//        String[] words=new String[]{"a", "b"};
//        String[] words=new String[]{"qamp","am","khdrn"};
        //
        //** Đề bài
        //- cho một array các letters
        //- Return lại {số group max nhất, size của letter max nhất của group all group là bn}
        //**: Chú ý 1 điều --> Ở đây là set of letters ==> Không cần order (Chỉ xét đến danh sách các ký tự mà thôi)
        //VD:
        //(s1 : cae) và (s2: ea) vẫn là 1 group --> chỉ cần xóa c đi trong s1 là được
        //==> {c,a,e}
        //Example:
        //Input: words = ["a","b","ab","cde"]
        //Output: [2,3]
        //--> Có 2 group {a,b,ab} {cde}
        //+ Max size of group = 3 : {a,b,ab}
        //case add
//        System.out.println(isSameGroup("a", "ab"));
        //case false
//        System.out.println(isSameGroup("ab", "cde"));
        //case replace
//        System.out.println(isSameGroup("cbe", "cde"));
        //,1 Sai case này với phép so sánh cùng group
        //VD: cea, ca ==> có thể remove e là xong
        //case delete
//        System.out.println(isSameGroup("cea", "ca"));
//        System.out.println(isSameGroup("abe", "eba"));

        //Sai test case bỏ 1 ký tự trên cả 2 string
//        System.out.println(isSameGroup("dac", "dca"));
//        System.out.println(isSameGroup("ca", "bc"));
        int[] result = groupStringsUnionFind(words);
        System.out.println(result[0] + " " + result[1] + "\n");
        //1000
//        System.out.println(getBitK(3, 8));
    }
}

