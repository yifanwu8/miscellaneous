package com.yifanwu.algo.leetcode.filesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yifan.Wu on 4/3/2021
 */
public class FileSystem {
    private final Directory root;
    private Directory currentDir;

    public FileSystem() {
        root = new Directory("", true);
    }

    public void addContentToFile(String path,  String content) {
        File file = getOrCreateFile(path);
        file.appendContent(content);
    }

    private File getOrCreateFile(String path) {
        List<String> pathList = splitPath(path);
        Directory dir = root;
        for (int i = 1; i < pathList.size() - 1; i++) {
            // error handling
            Collection<FileEntry> fileEntries = dir.list();
            Directory nextDir = null;
            for (FileEntry fileEntryEach : fileEntries) {
                if (fileEntryEach.getName().equals(pathList.get(i))) { // find the name
                    if (fileEntryEach instanceof Directory) { // the name is a dir
                        nextDir = (Directory) fileEntryEach;
                        break;
                    } else { // name exists for a file; this is not a leaf
                        throw new IllegalStateException();
                    }
                }
            }
            dir = nextDir;
        }
        File file = null;
        Collection<FileEntry> fileEntries = dir.list();
        for (FileEntry fileEntryEach : fileEntries) {
            if (fileEntryEach.getName().equals(pathList.get(pathList.size() - 1))) { // find the name
                if (fileEntryEach instanceof File) { // the name is a dir
                    file = (File) fileEntryEach;
                    break;
                } else { // name exists for a dir
                    throw new IllegalStateException();
                }
            }
        }
        if (file == null) {
            file = new File(pathList.get(pathList.size() - 1), "");
        }
        dir.addFileEntry(file);
        return file;
    }

    public List<String> ls(String path) {
        List<String> pathList = splitPath(path);
        if (pathList.isEmpty()) {
            return root.list().stream().map(FileEntry::getName).collect(Collectors.toList());
        }
        FileEntry leaf = null;
        Directory dir = root;
        for (int i = 1; i < pathList.size(); i++) {
            Collection<FileEntry> fileEntries = dir.list();
            Directory nextDir = null;
            for (FileEntry fileEntryEach : fileEntries) {
                if (fileEntryEach.getName().equals(pathList.get(i))) { // find a match name
                    if (i == pathList.size() - 1) { // if it is leaf
                       leaf = fileEntryEach;
                       break;
                    } else if (fileEntryEach instanceof Directory){
                        nextDir = (Directory) fileEntryEach;
                        break;
                    } else {
                        // not the leaf and is a file
                        throw new IllegalStateException();
                    }
                }
            }
            // the dir does not contain the name
            if (nextDir == null && leaf == null) {
                throw new IllegalStateException();
            }
            dir = nextDir;
        }
        return leaf.list().stream().map(FileEntry::getName).collect(Collectors.toList());
    }

    public void mkdir(String path) {
        List<String> pathList = splitPath(path);
        Directory dir = root;
        for (int i = 1; i < pathList.size(); i++) {
            Collection<FileEntry> fileEntries = dir.list();
            Directory nextDir = null;
            for (FileEntry fileEntryEach : fileEntries) {
                if (fileEntryEach.getName().equals(pathList.get(i))) { // find a match name
                    if (fileEntryEach instanceof Directory) { // the name is a dir
                        nextDir = (Directory) fileEntryEach;
                        break;
                    } else { // name exists for a file
                        throw new IllegalStateException();
                    }
                }
            }
            if (nextDir == null) {
                nextDir = new Directory(pathList.get(i));
                dir.addFileEntry(nextDir);
            }
            dir = nextDir;
        }
    }

    private List<String> splitPath(String path) {
        // error handling
        if (path == null || path.isEmpty() || path.charAt(0) != '/')
            throw new IllegalArgumentException();
        return Arrays.asList(path.split("/"));
    }

    private class Directory extends FileBase {
        private final boolean isRoot;
        private final Collection<FileEntry> fileEntires = new ArrayList<>();

        public Directory(String name) {
            this(name, false);
        }

        public Directory(String name, boolean isRoot) {
            super(name);
            if (isRoot) name = "";
            this.isRoot = isRoot;
        }

        boolean addFileEntry(FileEntry fileEntry) {
            return fileEntires.add(fileEntry);
        }

        @Override
        public Collection<FileEntry> list() {
            return fileEntires;
        }
    }

    private class File extends FileBase {
        private String content;

        public File(String name, String  content) {
            super(name);
            this.content = content;
        }

        void appendContent(String content) {
            this.content = (this.content == null) ? content : this.content + content;
        }

        @Override
        public Collection<FileEntry> list() {
            return Collections.singletonList(this);
        }
    }

    private abstract class FileBase implements FileEntry {
        protected String name;
        protected final long createTime;
        protected long modifiedTime;
        protected int ownerUid = 001;
        protected int gid = 001;
        protected String permission = "rw-------";

        public FileBase(String name) {
            this.name = name;
            this.createTime = System.currentTimeMillis();
        }

        @Override
        public long getCreateTime() {
            return createTime;
        }

        @Override
        public long getModifiedTime() {
            return modifiedTime;
        }

        @Override
        public int getOwnerUid() {
            return ownerUid;
        }

        @Override
        public int getGid() {
            return gid;
        }

        @Override
        public String getPermission() {
            return permission;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        List<String> list = fileSystem.ls("/");
        fileSystem.mkdir("/a/b/c");
        list = fileSystem.ls("/");
        list = fileSystem.ls("/a/b");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
    }
}
