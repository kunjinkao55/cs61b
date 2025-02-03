
package gh2;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

/**
 * This code does some stuff. Run it (with sound on!) to find out what stuff it does!
 * Requires completion of CS 61B Homework 1.
 *
 * @author Eli Lipsitz
*/
public class TOKYO {
    @Test
    public void main() {
        try {
            InputStream source = new ByteArrayInputStream(Base64.getDecoder().decode(TOKYO));
            source = new GZIPInputStream(source);
            GuitarPlayer player = new GuitarPlayer(source);
            player.play();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // You can also do this:
        // GuitarPlayer player = new GuitarPlayer(new java.io.File("path/to/music.mid"));
        // player.play();
    }

    private static final String TOKYO = "midi to base64 and paste here";
}
