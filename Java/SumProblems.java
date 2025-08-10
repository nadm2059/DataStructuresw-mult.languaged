import java.util.*;

public class SumProblems {

    // Two Sum: return indices of two numbers that add to target
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();  // Map to store number and its index
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];          // Complement to find
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};  // Return indices if complement found
            }
            map.put(nums[i], i);                        // Store number and index
        }
        return new int[]{};                            // Return empty if no pair found
    }

    // Three Sum: return list of unique triplets that sum to 0
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();  // Result list for triplets
        Arrays.sort(nums);                            // Sort array for two-pointer technique
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;  // Skip duplicates

            int left = i + 1, right = nums.length - 1;      // Two pointers

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right]; // Calculate sum

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right])); // Add valid triplet

                    // Skip duplicates on left and right
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;      // Need larger sum
                } else {
                    right--;     // Need smaller sum
                }
            }
        }
        return res;           // Return list of triplets
    }

    // Four Sum: return list of unique quadruplets that sum to target
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();  // Result list for quadruplets
        Arrays.sort(nums);                            // Sort array for two-pointer technique

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;  // Skip duplicates for i

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;  // Skip duplicates for j

                int left = j + 1, right = nums.length - 1;         // Two pointers

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right]; // Calculate sum using long to avoid overflow

                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right])); // Add valid quadruplet

                        // Skip duplicates on left and right
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;    // Need larger sum
                    } else {
                        right--;   // Need smaller sum
                    }
                }
            }
        }
        return res;   // Return list of quadruplets
    }

    // Main method to test the sum problem methods with line-by-line comments
    public static void main(String[] args) {
        // Create test input array
        int[] nums = {1, 0, -1, 0, -2, 2};

        // Set target for Two Sum test
        int targetTwoSum = 0;

        // Call twoSum and store result indices
        int[] twoSumResult = twoSum(nums, targetTwoSum);

        // Print header for Two Sum test output
        System.out.print("Two Sum indices for target " + targetTwoSum + ": ");

        // Check if a valid pair was found and print indices
        if (twoSumResult.length == 2) {
            System.out.println(Arrays.toString(twoSumResult));
        } else {
            // No valid pair found case
            System.out.println("No valid pair found");
        }

        // Call threeSum and get list of triplets summing to zero
        List<List<Integer>> threeSumResult = threeSum(nums);

        // Print header for Three Sum output
        System.out.println("Three Sum triplets that sum to 0:");

        // Iterate and print each triplet found
        for (List<Integer> triplet : threeSumResult) {
            System.out.println(triplet);
        }

        // Set target for Four Sum test
        int targetFourSum = 0;

        // Call fourSum and get list of quadruplets summing to target
        List<List<Integer>> fourSumResult = fourSum(nums, targetFourSum);

        // Print header for Four Sum output
        System.out.println("Four Sum quadruplets that sum to " + targetFourSum + ":");

        // Iterate and print each quadruplet found
        for (List<Integer> quadruplet : fourSumResult) {
            System.out.println(quadruplet);
        }
    }
}
