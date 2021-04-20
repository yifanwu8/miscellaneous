package com.yifanwu.algo.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Yifan.Wu on 4/19/2021
 */
public class FilesystemTest {

    private FileSystem fileSystem = new FileSystem();

    private void createCommons() {
        fileSystem.mkdir("/abc");
        fileSystem.mkdir("/xyz");

        fileSystem.mkdir("/abc/def");
        fileSystem.mkdir("/abc/ghi");


    }

    @Test
    public void mkdirTest() {
        createCommons();
        List<String> stringList = fileSystem.ls("/");
        Assert.assertEquals("abc", stringList.get(0));
        Assert.assertEquals("xyz", stringList.get(1));

        stringList = fileSystem.ls("/abc");
        Assert.assertEquals("def", stringList.get(0));
        Assert.assertEquals("ghi", stringList.get(1));

        stringList = fileSystem.ls("/xyz");
        Assert.assertEquals(0, stringList.size());
    }

    @Test
    public void fileTest() {
        createCommons();
        fileSystem.addContentToFile("/abc/def/new.txt", "a new file");
        Assert.assertEquals("a new file", fileSystem.readContentFromFile("/abc/def/new.txt"));
        fileSystem.addContentToFile("/abc/def/new.txt", "something else");
        Assert.assertEquals("a new file" + "something else",
                fileSystem.readContentFromFile("/abc/def/new.txt"));
    }

    @Test
    public void errorTest() {
        createCommons();
        try {
            fileSystem.ls("/aba");
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
        }
    }
}
