package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {

    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {

        /* create guitar strings, for concert q to the last key */
        GuitarString[] strings = new GuitarString[keyboard.length()];

        for (int i = 0; i < keyboard.length(); i++) {
            double newCONCERT = calculateFrequency(i);
            strings[i] = new GuitarString(newCONCERT);
        }

        /* playing */
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int keyIdx = keyboard.indexOf(key);

                if(keyIdx >= 0){
                    strings[keyIdx].pluck();
                }

            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for(GuitarString string : strings){
                sample += string.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(GuitarString string : strings){
                string.tic();
            }
        }
    }

    /**
     * The ith character of the string keyboard
     * corresponds to a frequency of 440⋅2(i−24)/12
     */
    private static double calculateFrequency(int i) {
        return 440 * Math.pow(2, (i - 24) / 12);
    }
}
