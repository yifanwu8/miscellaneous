package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 *For example:
 *Given "25525511135",
 *
 *return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * Created by Yifan.Wu on 7/11/2017.
 */
public class RestoreIpAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, res, new ArrayList<>(4), 0);
        return res;
    }

    public void dfs(String s, List<String> ipList, List<Integer> ip, int pos ) {
        // return condition
        if (ip.size() == 4 && pos == s.length()) {
            // TODO: join ip by dot and copy to ipList
            ipList.add(ip.stream().map(e -> e.toString()).collect(Collectors.joining(".")));
            return;
        }
        if (ip.size() == 4 || pos == s.length()) {
            return;
        }

        for (int end = pos + 1; end <= pos + 3 && end <= s.length(); end++) {
            if (s.charAt(pos) == '0' && end > pos + 1) {
                break;
            }
            int ipInt = Integer.parseInt(s.substring(pos, end));
            if (ipInt <= 255) {
                ip.add(ipInt);
                dfs(s, ipList, ip, end);
                ip.remove(ip.size() - 1);
            }

        }
    }
}
