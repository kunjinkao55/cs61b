package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    private static GuitarString[] keyboards = new GuitarString[37];
    private static double PLUS = 770.0 / 36;
    static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static double begin = 110.0;
    public static void init() {
        for (int i = 0; i < 37; i += 1) {
            begin += PLUS;
            char name = keyboard.charAt(i);
            keyboards[i] = new GuitarString(begin);
            keyboards[i].rename(name);
        }
    }
    public static void search(char n) {
        for (int i = 0; i < 37; i++) {
            if (keyboards[i].getname() == n) {
                keyboards[i].pluck();
            }
        }
    }
    public static void main(String[] args) {
        init();
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                search(key);
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < 37; i++) {
                sample += keyboards[i].sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < 37; i++) {
                keyboards[i].tic();
            }
        }
    }
}

