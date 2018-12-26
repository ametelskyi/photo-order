package somes.selfImproving.hr.heap;

/**
 * Created by anme on 26.04.17.
 */
public class Some {
    public static void main(String[] args) {

        String[] s = new String []{
                "G",
                "G#",
                "A",
                "A#",
                "B",
                "C",
                "C#",
                "D",
                "D#",
                "E",
                "F",
                "F#",
                "G",
                "G#",
                "A",
                "A#",
                "B",
                "C",
                "C#",
                "D",
                "D#",
                "E",
                "F"};

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[s.length-i-1]);
        }
    }
}
