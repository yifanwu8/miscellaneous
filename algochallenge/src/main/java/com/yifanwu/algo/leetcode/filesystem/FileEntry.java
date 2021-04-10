package com.yifanwu.algo.leetcode.filesystem;

import java.util.Collection;

/**
 * @author Yifan.Wu on 4/3/2021
 */
public interface FileEntry {

    Collection<FileEntry> list();
    long getCreateTime();
    long getModifiedTime();
    int getOwnerUid();
    int getGid();
    String getPermission();
    String getName();
}
