class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long res = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    long value = (long) ((long) nums[i] - (long) nums[j]) * (long) nums[k];
                    res = Long.max(res, value);
                }
            }
        }
        if (res < 0) return 0;
        return res;
    }
}