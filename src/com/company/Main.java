package com.company;
import javax.annotation.processing.SupportedSourceVersion;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.StreamSupport;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("text.txt");
            Scanner reader = new Scanner(file);
            ArrayList<String> words = new ArrayList<>();

            while (reader.hasNextLine()) {
                String text = reader.nextLine();
                String line = text.replaceAll("[^ a-zA-Z0-9]","");
                line = line.replaceAll(" {2,}", " ");
                String[] arr = line.split(" ");
                cutWords(arr);
                words.addAll(Arrays.asList(arr));
            }
            reader.close();

            /*for(String word : words )
            {
                System.out.println(word);
            }*/

            String result = func(words);
            System.out.print(result);
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено");
            e.printStackTrace();
        }
    }

    private static void cutWords(String[] words)
    {
        for(int i = 0; i < words.length; ++i)
        {
            if(words[i].length() > 30) {
                words[i].substring(0, 30);
            }
            //System.out.print(words[i] + ' ');
        }
    }

    private static String func(ArrayList<String> words)
    {
        String result = null;
        TreeMap<String, Integer> tree = new TreeMap<String, Integer>();
        for(String s : words)
        {
            if(tree.containsKey(s)) tree.put(s, tree.get(s) + 1);
            else tree.put(s, 1);
        }

        /*for(String s: tree.keySet())
        {
            System.out.print(s + ' ' + tree.get(s) + '\n');
        }*/

        int max = -1, n;
        for(String s : tree.keySet())
        {
            n = tree.get(s);
            if(n >= max) { result = s; max = n; }
        }
        return result;
    }
}