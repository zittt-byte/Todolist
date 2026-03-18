package Todolist.Todo.src.main.java;

import java.util.Random;

/**
 *
 * @Kanin
 */



public class Greeting {
    private static final String[] word = new String[]{"Hello","Ahoy","Aloha","Hola","Ciao","Namaste","Shalom","Xin chào","Guten Tag"};
    public static String getWord() {
        Random generator = new Random();
        return word[generator.nextInt(word.length)];
    }
}
