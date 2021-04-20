package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author Yifan.Wu on 4/8/2021
 */
public class FileSystem {
    private final Dir root = new Dir();

    public FileSystem() {
    }

    private Dir getOneToLastDir(List<String> pathList) {
        Dir dir = root;
        // travese down to the one to the last
        for (int i = 1; i < pathList.size() - 1; i++) {
            dir = dir.getDir(pathList.get(i));
            if (dir == null) throw new IllegalArgumentException();
        }
        return dir;
    }

    public List<String> ls(String path) {
        // corner case of "/"
        if ("/".equals(path)) return root.list();
        // index 0 is always empty "" and denoting root
        List<String> pathList = splitPath(path);
        Dir dir = getOneToLastDir(pathList);
        // the last file or dir
        FileEntry lastEntry = dir.getFileEntry(pathList.get(pathList.size() - 1));
        if (lastEntry == null) throw new IllegalArgumentException();
        return lastEntry.list();
    }

    public void mkdir(String path) {
        List<String> pathList = splitPath(path);
        Dir dir = root;
        for (int i = 1; i < pathList.size(); i++) {
            dir = dir.getOrCreateDir(pathList.get(i));
        }
    }

    public void addContentToFile(String filePath, String content) {
        List<String> pathList = splitPath(filePath);
        Dir dir = getOneToLastDir(pathList);

        File file = dir.getOrCreateFile(pathList.get(pathList.size() - 1));
        file.appendContent(content);
    }

    public String readContentFromFile(String filePath) {
        List<String> pathList = splitPath(filePath);
        Dir dir = getOneToLastDir(pathList);

        File file = dir.getFile(pathList.get(pathList.size() - 1));
        if (file == null) throw new IllegalArgumentException();
        return file.getContent();
    }

    private List<String> splitPath(String path) {
        if (path == null || path.isEmpty() || path.charAt(0) != '/') throw new IllegalArgumentException();
        return Arrays.asList(path.split("/"));
    }


    interface FileEntry {
        String getName();
        List<String> list();
    }

    private static abstract class FileEntryBase implements FileEntry {
        private final String name;

        FileEntryBase() {
            name = "";
        }

        public FileEntryBase(String name) {
            Objects.requireNonNull(name, "Name cannot be null");
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
    private static class Dir extends FileEntryBase {
        private final Map<String, FileEntry> entries = new TreeMap<>(); // guarantee lexicographical order

        Dir() {
        }

        public Dir(String name) {
            super(name);
            if ("".equals(name)) throw new IllegalArgumentException("Dir name cannot be empty");
        }

        @Override
        public List<String> list() {
            return new ArrayList<>(entries.keySet());
        }

        public Dir getDir(String name) {
            FileEntry fileEntry = entries.get(name);
            if (fileEntry instanceof Dir) {
                return (Dir) fileEntry;
            } else {
                return null;
            }
        }
        public Dir getOrCreateDir(String name) {
            FileEntry fileEntry = entries.get(name);
            if (fileEntry instanceof File) {
                throw new IllegalArgumentException();
            }
            if (fileEntry == null) {
                entries.put(name, new Dir(name));
            }
            return (Dir) entries.get(name);
        }
        // refactor reuse
        public File getOrCreateFile(String name) {
            FileEntry fileEntry = entries.get(name);
            if (fileEntry instanceof Dir) {
                throw new IllegalArgumentException();
            }
            if (fileEntry == null) {
                entries.put(name, new File(name));
            }
            return (File) entries.get(name);
        }
        // nullable return
        public File getFile(String name) {
            FileEntry fileEntry = entries.get(name);
            if (fileEntry instanceof File) {
                return (File) fileEntry;
            } else {
                return null;
            }
        }
        public FileEntry getFileEntry(String name) {
            return entries.get(name);
        }
    }

    private static class File extends FileEntryBase {
        private String content = "";

        public File(String name) {
            super(name);
            if ("".equals(name)) throw new IllegalArgumentException("File name cannot be empty");
        }

        @Override
        public List<String> list() {
            return Arrays.asList(getName());
        }

        public void appendContent(String content) {
            Objects.requireNonNull(content);
            this.content += content;
        }
        public String getContent() {
            return content;
        }
    }
}
