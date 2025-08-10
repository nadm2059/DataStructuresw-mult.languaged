# Two Sum
def two_sum(nums, target):
    seen = {}
    for i, num in enumerate(nums):
        if target - num in seen:
            return [seen[target - num], i]
        seen[num] = i
    return []

# Three Sum
def three_sum(nums):
    nums.sort()
    res = []
    for i, a in enumerate(nums):
        if i > 0 and a == nums[i - 1]: continue
        l, r = i + 1, len(nums) - 1
        while l < r:
            s = a + nums[l] + nums[r]
            if s == 0:
                res.append([a, nums[l], nums[r]])
                l += 1
                r -= 1
                while l < r and nums[l] == nums[l - 1]: l += 1
                while l < r and nums[r] == nums[r + 1]: r -= 1
            elif s < 0:
                l += 1
            else:
                r -= 1
    return res

# Four Sum
def four_sum(nums, target):
    nums.sort()
    res = []
    n = len(nums)
    for i in range(n):
        if i > 0 and nums[i] == nums[i-1]: continue
        for j in range(i+1, n):
            if j > i+1 and nums[j] == nums[j-1]: continue
            l, r = j+1, n-1
            while l < r:
                s = nums[i] + nums[j] + nums[l] + nums[r]
                if s == target:
                    res.append([nums[i], nums[j], nums[l], nums[r]])
                    l += 1
                    r -= 1
                    while l < r and nums[l] == nums[l-1]: l += 1
                    while l < r and nums[r] == nums[r+1]: r -= 1
                elif s < target:
                    l += 1
                else:
                    r -= 1
    return res


def main():
    print("Two Sum:")
    print(two_sum([2, 7, 11, 15], 9))  # [0, 1]

    print("Three Sum:")
    print(three_sum([-1, 0, 1, 2, -1, -4]))  # [[-1, -1, 2], [-1, 0, 1]]

    print("Four Sum:")
    print(four_sum([1, 0, -1, 0, -2, 2], 0))  # Multiple combinations

if __name__ == "__main__":
    main()
