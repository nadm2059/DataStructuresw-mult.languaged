// 5. Two Sum, Three Sum, Four Sum
function twoSum(nums, target) {
    const map = {};
    for (let i = 0; i < nums.length; i++) {
        let complement = target - nums[i];
        if (map[complement] !== undefined) return [map[complement], i];
        map[nums[i]] = i;
    }
    return [];
}

function threeSum(nums) {
    nums.sort((a, b) => a - b);
    const res = [];

    for (let i = 0; i < nums.length - 2; i++) {
        if (i > 0 && nums[i] === nums[i - 1]) continue;
        let left = i + 1, right = nums.length - 1;

        while (left < right) {
            let sum = nums[i] + nums[left] + nums[right];
            if (sum === 0) {
                res.push([nums[i], nums[left], nums[right]]);
                while (nums[left] === nums[left + 1]) left++;
                while (nums[right] === nums[right - 1]) right--;
                left++;
                right--;
            } else if (sum < 0) left++;
            else right--;
        }
    }
    return res;
}

function mainSum() {
    // Array for Two Sum problem and target
    const numsTwo = [2, 7, 11, 15];
    const target = 9;

    // Find indices of two numbers that add to target
    const twoSumResult = twoSum(numsTwo, target);
    console.log("Two Sum indices:", twoSumResult);

    // Array for Three Sum problem
    const numsThree = [-1, 0, 1, 2, -1, -4];

    // Find triplets that sum to zero
    const threeSumResult = threeSum(numsThree);
    console.log("Three Sum triplets:", threeSumResult);
}

mainSum();
