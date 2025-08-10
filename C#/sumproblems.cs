using System;
using System.Collections.Generic;

public class SumProblems {
    public int[] TwoSum(int[] nums, int target) {
        Dictionary<int, int> map = new();
        for (int i = 0; i < nums.Length; i++) {
            int diff = target - nums[i];
            if (map.ContainsKey(diff))
                return new int[] { map[diff], i };
            map[nums[i]] = i;
        }
        return new int[0];
    }

    public IList<IList<int>> ThreeSum(int[] nums) { 
        Array.Sort(nums);
        List<IList<int>> res = new();
        for (int i = 0; i < nums.Length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.Length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.Add(new List<int> { nums[i], nums[left], nums[right] });
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++; right--;
                } else if (sum < 0) left++;
                else right--;
            }
        }
        return res;
    }

    public static void Main(string[] args) {
        SumProblems sp = new SumProblems();

        // Test TwoSum
        int[] nums1 = { 2, 7, 11, 15 };
        int target = 9;
        int[] resultTwoSum = sp.TwoSum(nums1, target);
        Console.WriteLine("TwoSum result:");
        if (resultTwoSum.Length > 0)
            Console.WriteLine($"Indices: {resultTwoSum[0]}, {resultTwoSum[1]}");
        else
            Console.WriteLine("No two sum solution found.");

        // Test ThreeSum
        int[] nums2 = { -1, 0, 1, 2, -1, -4 };
        var resultThreeSum = sp.ThreeSum(nums2);
        Console.WriteLine("\nThreeSum result:");
        foreach (var triplet in resultThreeSum) {
            Console.WriteLine(string.Join(", ", triplet));
        }
    }
}
