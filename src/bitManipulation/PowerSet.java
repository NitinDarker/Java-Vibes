package bitManipulation;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int comb = 0; // Bitwise combinations
        int limit = 1 << nums.length;

        while (comb < limit) {
            int currComb = comb;
            List<Integer> subset = new ArrayList<>();
            for (int num : nums) {
                int unit = currComb & 1;
                if (unit == 1) subset.add(num);
                currComb = currComb >> 1;
            }
            comb += 1;
            res.add(subset);
        }
        return res;
    }
}
