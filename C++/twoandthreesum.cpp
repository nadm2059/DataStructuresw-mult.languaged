#include <unordered_map>
vector<int> twoSum(vector<int>& nums, int target) {
    unordered_map<int, int> map;  // Map value -> index
    for (int i = 0; i < nums.size(); ++i) {
        int complement = target - nums[i];  // Needed value
        if (map.count(complement))  // If found
            return {map[complement], i};
        map[nums[i]] = i;  // Add current value to map
    }
    return {};
}

#include <vector>
#include <algorithm>
using namespace std;

vector<vector<int>> threeSum(vector<int>& nums) {
    sort(nums.begin(), nums.end());  // Sort array
    vector<vector<int>> res;
    for (int i = 0; i < nums.size(); ++i) {
        if (i > 0 && nums[i] == nums[i-1]) continue;  // Skip duplicates
        int l = i+1, r = nums.size()-1;
        while (l < r) {
            int sum = nums[i] + nums[l] + nums[r];
            if (sum == 0) {
                res.push_back({nums[i], nums[l], nums[r]});
                while (l < r && nums[l] == nums[l+1]) ++l;  // Skip duplicates
                while (l < r && nums[r] == nums[r-1]) --r;
                ++l; --r;
            } else if (sum < 0) ++l;
            else --r;
        }
    }
    return res;
}
int main() {
    vector<int> nums = {2, 7, 11, 15};
    auto res = twoSum(nums, 9);
    cout << "Two Sum: " << res[0] << ", " << res[1] << endl;

    vector<int> nums2 = {-1, 0, 1, 2, -1, -4};
    auto triplets = threeSum(nums2);
    cout << "Three Sum triplets:" << endl;
    for (auto& triplet : triplets) {
        for (int x : triplet) cout << x << " ";
        cout << endl;
    }

    return 0;
}