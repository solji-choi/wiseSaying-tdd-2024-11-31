package com.ll.util;

import java.io.File;
import java.io.FileFilter;

public class JsonFileFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        if (pathname.getName().endsWith(".json")) {
            return true;
        } else {
            return false;
        }
    }
}
