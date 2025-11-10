package E1_daily;

import java.util.*;

public class E280_ApplyOperationsToMaximizeScore {

    private static final int MOD = 1_000_000_007;

    public static boolean isPrimeOptimization(int number){
        if(number<=1){
            return false;
        }
        if(number<=3){
            return true;
        }
        if(number%2==0||number%3==0){
            return false;
        }
        for(int i=5;i*i<=number;i+=6){
            if(number%i==0||number%(i+2)==0){
                return false;
            }
        }
        return true;
    }

    public static long customPow(long key, int count, HashMap<Integer, Long> cache, long mod){
        if(count==1){
            cache.put(1, key);
            return key%mod;
        }
        if(cache.containsKey(count)){
            return cache.get(count);
        }
        //3 = 2*1
        //4=2*2
        long curVal= customPow(key, count/2, cache, mod)*customPow(key, (count+1)/2, cache, mod);
        curVal=curVal%mod;
        cache.put(count, curVal);
        return curVal;
    }

    public static int maximumScore(List<Integer> nums, int k) {
        int max=Integer.MIN_VALUE;
        int n=nums.size();
        for(int e: nums){
            max=Math.max(e, max);
        }
        List<Integer> primes=new ArrayList<>();
//        int maxPrime = (int) Math.sqrt(max);

        for(int i=1;i<=max;i++){
            if(isPrimeOptimization(i)){
                primes.add(i);
            }
        }
        int[] primeScore=new int[n];

        for(int i=0;i<n;i++){
            int curVal = nums.get(i);
            int countDistinct=0;
            for(int j=0;j<primes.size();j++){
                boolean isValid=false;
                if(curVal<primes.get(j)){
                    break;
                }
                while (curVal%primes.get(j)==0){
                    curVal=curVal/primes.get(j);
                    isValid=true;
                }
                if(isValid){
                    countDistinct++;
                }
            }
            primeScore[i]=countDistinct;
        }
//        for (int i = 0; i < primeScore.length; i++) {
//            System.out.printf("%s, ", primeScore[i]);
//        }
//        System.out.println();

        //Input: nums = [19,12,14,6,10,18], k = 3
        //- At index=i:
        //  + Count how many previous elements (elements[j]) such that:
        //      + element[j]<element[i] && primeScore[j]<primeScore[i]
        //- Motonoic stack:
        //  + element[stack.last]>element[i] || primeScore[j]>primeScore[i]
        //  ==> Clear stack
        //==> It is not correct
        //nums = [19,12,14,6,10,18]
        //  + [1,1,2,1,2,(3)]
        //  ==> 3 is not correct
        //[18] ==> need to recieve buffer from 10
        //
        //nums = [19,12,14,6,10,18]
        //19,12,14,6,10
        //==> 10>6 but 10<14 ==> we cannot remove 14
        //stack = [19]
        //stack = [19,12]
        //- add(14)
        // stack = [19,14]
        //- add(6)
        // stack = [19,14,6]
        //- add(10)
        // stack = [19,14,10]
        //- add(18)
        // stack = [19,18]
        //* Main point:
        //  + sum[18] = sum[10]+sum[14]
        //
        Stack<Integer> indicesPrefix=new Stack<>();
        Stack<Integer> indicesSuffix=new Stack<>();
        int[] countSizePrefix=new int[n];
        int[] countSizeSuffix=new int[n];
        int[] countSize=new int[n];
        for (int i = 0; i < n; i++) {
            int curCount=1;
            while (!indicesPrefix.isEmpty()&&
                    (nums.get(indicesPrefix.peek())<nums.get(i)&&primeScore[indicesPrefix.peek()]<primeScore[i])){
                curCount+=countSizePrefix[indicesPrefix.pop()];
            }
            indicesPrefix.add(i);
            countSizePrefix[i]=curCount;
//            System.out.printf("Index: %s, countSize: %s\n", i, countSize[i]);
        }
        for(int i=n-1;i>=0;i--){
            int curCount=1;
            while (!indicesSuffix.isEmpty()&&
                    (nums.get(indicesSuffix.peek())<nums.get(i)&&primeScore[indicesSuffix.peek()]<=primeScore[i])){
                curCount+=countSizeSuffix[indicesSuffix.pop()];
            }
            indicesSuffix.add(i);
            countSizeSuffix[i]=curCount;
//            System.out.printf("Index: %s, countSize: %s\n", i, countSizeSuffix[i]);
        }
        for (int i = 0; i < n; i++) {
            countSize[i]=countSizeSuffix[i]+countSizePrefix[i]-1;
            System.out.printf("Index: %s, countSize: %s\n", i, countSize[i]);
        }
        TreeSet<int[]> sortElement=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o2[0]-o1[0];
                }
                return o1[1]-o2[1];
            }
        });
        for(int i=0;i<n;i++){
            sortElement.add(new int[]{nums.get(i), i});
        }
        long rs=1;
        int remainingSize=k;
        long mod=1_000_000_007;
        for(int i=0;i<k&&remainingSize>0;i++){
            int[] maxElement = sortElement.pollFirst();
//            System.out.println(maxElement[0]);
            int count = Math.min(remainingSize, countSize[i]);
            remainingSize-=count;
//            double powVal = customPow(maxElement[0], count, new HashMap<>(), mod);
            long powVal = (long) Math.pow(maxElement[0], count)%mod;
            System.out.printf("Element: %s, count:%s, pow: %s\n", maxElement[0], count, powVal);
            rs= (rs * powVal)%mod;
            System.out.println(rs);
//            for (int j = 0; j < countSize[i]; j++) {
//                rs=(rs*maxElement[0])%mod;
//            }
//            System.out.println(rs);
        }

        return (int) rs;
    }

    public static int maximumScoreRefer(List<Integer> nums, int k) {
        int n = nums.size();
        int[] primeScores = new int[n];

        // Find the maximum element in nums to determine the range for prime generation
        int maxElement = Collections.max(nums);

        // Get all prime numbers up to maxElement using the Sieve of Eratosthenes
        List<Integer> primes = getPrimes(maxElement);

        // Calculate the prime score for each number in nums
        for (int index = 0; index < n; index++) {
            int num = nums.get(index);

            // Iterate over the generated primes to count unique prime factors
            for (int prime : primes) {
                if (prime * prime > num) break; // Stop early if prime^2 exceeds num
                if (num % prime != 0) continue; // Skip if the prime is not a factor

                primeScores[index]++; // Increment prime score for the factor
                while (num % prime == 0) num /= prime; // Remove all occurrences of this factor
            }

            // If num is still greater than 1, it is a prime number itself
            if (num > 1) primeScores[index]++;
        }

        // Initialize next and previous dominant index arrays
        int[] nextDominant = new int[n];
        int[] prevDominant = new int[n];
        Arrays.fill(nextDominant, n);
        Arrays.fill(prevDominant, -1);

        // Stack to store indices for a monotonic decreasing prime score
        Stack<Integer> decreasingPrimeScoreStack = new Stack<>();

        // Calculate the next and previous dominant indices for each number
        for (int index = 0; index < n; index++) {
            while (
                    !decreasingPrimeScoreStack.isEmpty() &&
                            primeScores[decreasingPrimeScoreStack.peek()] <
                                    primeScores[index]
            ) {
                int topIndex = decreasingPrimeScoreStack.pop();
                nextDominant[topIndex] = index;
            }

            if (!decreasingPrimeScoreStack.isEmpty()) {
                prevDominant[index] = decreasingPrimeScoreStack.peek();
            }

            decreasingPrimeScoreStack.push(index);
        }

        // Calculate the number of subarrays in which each element is dominant
        long[] numOfSubarrays = new long[n];
        for (int index = 0; index < n; index++) {
            numOfSubarrays[index] =
                    (long) (nextDominant[index] - index) *
                            (index - prevDominant[index]);
        }

        // Sort elements in decreasing order based on their values
        List<int[]> sortedArray = new ArrayList<>();
        for (int index = 0; index < n; index++) {
            sortedArray.add(new int[] { nums.get(index), index });
        }
        sortedArray.sort((a, b) -> Integer.compare(b[0], a[0])); // Sort in descending order

        long score = 1;
        int processingIndex = 0;

        // Process elements while there are operations left
        while (k > 0) {
            int[] element = sortedArray.get(processingIndex++);
            int num = element[0];
            int index = element[1];

            // Calculate the number of operations to apply on the current element
            long operations = Math.min(k, numOfSubarrays[index]);

            // Update the score by raising the element to the power of operations
            System.out.printf("num: %s, op:%s\n", num, operations);
            score = (score * power(num, operations)) % MOD;

            // Reduce the remaining operations count
            k -= operations;
        }

        return (int) score;
    }

    // Helper function to compute the power of a number modulo MOD
    private static long power(long base, long exponent) {
        long res = 1;

        while (exponent > 0) {
            if (exponent % 2 == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exponent /= 2;
        }

        return res;
    }

    // Function to generate prime numbers up to a given limit using the Sieve of Eratosthenes
    private static List<Integer> getPrimes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        List<Integer> primes = new ArrayList<>();

        for (int number = 2; number <= limit; number++) {
            if (!isPrime[number]) continue;

            primes.add(number);

            for (
                    long multiple = (long) number * number;
                    multiple <= limit;
                    multiple += number
            ) {
                isPrime[(int) multiple] = false;
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an array nums of n positive integers) and (an integer k).
        //- Initially, you start with (a score of 1).
        //- You have to maximize your score by applying the following operation at most (k times):
        //  + Choose any (non-empty subarray) nums[l, ..., r] that you (haven't chosen previously).
        //  + Choose an element x of nums[l, ..., r] with (the highest prime score).
        //      + If multiple such elements exist, choose the one with (the smallest index).
        //      + Multiply (your score by x).
        //- Here, nums[l, ..., r] denotes (the subarray of nums) (starting at index l and ending at the index r),
        // both ends being ("inclusive").
        //* (The prime score of an integer x) is equal to (the number of ("distinct") prime factors) of x.
        //  + For example, the prime score of 300 is 3 since 300 = 2 * 2 * 3 * 5 * 5.
        //* Return (the maximum possible score) after applying (["at most"] k operations).
        //
        //- Since the answer may be large, return it modulo 10^9 + 7.
        //
        //Example 1:
        //
        //Input: nums = [8,3,9,3,8], k = 2
        //Output: 81
        //Explanation: To get a score of 81, we can apply the following operations:
        //- Choose subarray nums[2, ..., 2]. nums[2] is (the only element) in this subarray.
        // Hence, we multiply the score by nums[2]. The score becomes 1 * 9 = 9.
        //- Choose subarray nums[2, ..., 3].
        // Both nums[2] and nums[3] have (a prime score of 1), but nums[2] has the smaller index.
        // Hence, we multiply the score by nums[2]. The score becomes 9 * 9 = 81.
        //It can be proven that 81 is the highest score one can obtain.
        //
        //** Idea
        //1. Median
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length == n <= 10^5
        //1 <= nums[i] <= 10^5
        //1 <= k <= min(n * (n + 1) / 2, 10^9)
        //  + Time: O(n*k)
        //
        //* Brainstorm:
        //
        //Example 2:
        //
        //Input: nums = [19,12,14,6,10,18], k = 3
        //Output: 4788
        //
        //- To solve this, we need to implement the function to (get the prime score).
        //  + x= a*b*c*...
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
//        Integer[] nums = {19,12,14,6,10,18};
//        int k = 3;
        //nums = {19,12,14,6,10,18}
        //primes = [1,2,2,2,2,2]
        //
        //8 = 2*2*2
//        Integer[] nums = {8,3,9,3,8};
//        int k = 2;
        Integer[] nums = {3289,2832,14858,22011};
        int k = 6;
        //size = [2,1,3,1]
        //Output: 256720975
        //- Choose range of [i,j] but we should choose max:
        // x = x*nums[i]
        //  + result = nums[i]*nums[j]*...
        //- Choose max ==> find all prefix of this max
        //  ==> Max heap + index
        //  + Index-- ==> To get all of subarrays
        //
        System.out.println(customPow(2, 3, new HashMap<>(), 1_000_000_007));
        System.out.println(customPow(2, 4, new HashMap<>(), 1_000_000_007));
        System.out.println(maximumScore(Arrays.asList(nums), k));
        System.out.println();
        System.out.println(maximumScoreRefer(Arrays.asList(nums), k));
    }
}
