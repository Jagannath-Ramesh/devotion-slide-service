package org.sathya.sai.devotion.slide.dataaccess;

/**
 * @author Jagannath Ramesh (jagannath-ramesh)
 */
public class test {

    static void add(String s[]) {
        s[0] = "hii";
    }

    public static void main(String args[]) {
      String s[] = {"hello"};
      add(s);
        System.out.println(s[0]);
    }
}
