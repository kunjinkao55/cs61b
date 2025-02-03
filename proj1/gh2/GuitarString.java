package gh2;

import deque.*;

import java.util.Arrays;


//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    private char name;
    /* Buffer for storing sound data. */
    public char getname() {
        return name;
    }
    public void rename(char newname) {
        name = newname;
    }
    private ArrayDeque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayDeque<Double>();
        buffer.resize((int) Math.round(SR / frequency));
        Arrays.fill(buffer.items(), 0.0);
        buffer.changesize((int) Math.round(SR / frequency));
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        for (int i = 0; i < (buffer.items()).length; i++) {
            double r = Math.random() - 0.5;
            (buffer.items())[i] = r;
        }
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {

        double oldhead = buffer.removeFirst();
        double newhead = buffer.get(0);
        double newdouble = (oldhead + newhead) * 0.5 * DECAY;
        buffer.addLast(newdouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.get(0);
    }
}

