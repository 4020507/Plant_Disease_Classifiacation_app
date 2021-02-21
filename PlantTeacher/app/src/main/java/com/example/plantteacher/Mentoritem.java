package com.example.plantteacher;

import java.util.Arrays;

public class Mentoritem {
    String title;

    public Mentoritem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Mentoritem{" +
                "title='" + title + '\'' +
                '}';
    }
}
