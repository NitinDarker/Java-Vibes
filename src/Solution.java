// https://leetcode.com/problems/sum-of-all-subset-xor-totals/description/?envType=daily-question&envId=2025-04-05
class Solution {
    void dfsHelper(int ind, int xorSum, int[] finalSum, int[] nums) {
        if (ind == nums.length) {
            finalSum[0] += xorSum;
            return;
        }
        dfsHelper(ind+1, xorSum^nums[ind], finalSum, nums); // Take
        dfsHelper(ind+1, xorSum, finalSum, nums); // Not Take
    }

    public int subsetXORSum(int[] nums) {
        int[] finalSum = {0};
        dfsHelper(0, 0, finalSum, nums);
        return finalSum[0];
    }
}