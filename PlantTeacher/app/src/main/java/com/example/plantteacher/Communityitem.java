package com.example.plantteacher;

import android.graphics.Bitmap;

import java.util.Arrays;

public class Communityitem {
    String title;
    String contents;
    byte[] array;

    public Communityitem(String title, String contents, byte[] array) {
        this.title = title;
        this.contents = contents;
        this.array = array;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public byte[] getArray() {
        return array;
    }

    public void setArray(byte[] array) {
        this.array = array;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Communityitem{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", array=" + Arrays.toString(array) +
                '}';
    }

}
