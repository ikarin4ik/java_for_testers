package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main (String[] args) {
        String [] langs = {"Java", "JavaScript", "C#", "Python"};

        List<String> languages = Arrays.asList("Java", "JavaScript", "PHP");

        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
