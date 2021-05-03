package com.exercise.fileupload.file;

import java.util.regex.Pattern;

class TagInvalidCharsFinder {

    private static final Pattern invalidCharsPattern = Pattern.compile("[\\-+\\s]");

    public boolean find(String tag) {
        return invalidCharsPattern.matcher(tag).find();
    }
}
