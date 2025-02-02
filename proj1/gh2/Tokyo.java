package gh2;

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
public class Tokyo {
    public static void main(String[] args) {
        try {
            InputStream source = new ByteArrayInputStream(Base64.getDecoder().decode(Tokyo));
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

    private static final String Tokyo =
            "H4sIAAAAAAAAA+1cW28bxxVexZIYuSTQSDZswW2TRgkUWKooi5V3Q63EpUhTFBeU1jJTFwgMJUgEtGCRmn6J0hdJ1kML9IX/oK/pW"
                    +"4A+twiSP9J/4n5zLrOzpKQ4vrSyqweC2pkzl3Odc74dqt353eee5417I94bI/9udx518TTrPbk0lvz+0y/+6D357ejIG"
                    +"9ff9J7cvTQx++3xJ3gexfNf/+U9KXpMHv8J5EvfH393/P3j7/6O78f4/pt8/0Pav5bnf8r31+/c8p6M/mSj/U7yh0+/2"
                    +"nuEx28975vLkfdNzvn+Ch/s75s9832Ab3xyn3P/x2h74B1/0g8Tr1/Bp4HPPD5+cvTZYRh5hxV8GlEBBEf3D+fx4Ed5d"
                    +"BRA3UNnDh09PB/0V2SGGj5z+Cwnjz86XJEZavjM4bMcTaHj6NegLuJhAgSj/bXk6ObhWnQZjUG2YRUNq2gwuyvjs8i7K"
                    +"vSr6Kiio4bvGr4b+G7gu5XMHJaxzmJ09bCF5zY218YmW/huYZNtTLIpm1zFp2Q2uYoBJWxsNTm6C2rvcJN2PImOAo2o0"
                    +"iweVsz1t2l073CbaA76CZ7reE7wXEc/C29/iG6HhLsrIpw83CG5Do4/6Md43qA1VYyQRYzODdrAZRAEeB61Ivjh55+L6"
                    +"G9IO+tphSbPjjGb5g083ia9rdDAceKCNzFlldwkhfSsfpvgvpF0IfsC2rtkNU1M3CS9FNE+QTqJhbtycnQNk06AqIZde"
                    + "FCcGbiBAROYKMYEvwLhDQzMo3+kv06LYtA6rTaBzgQa3EDnBHb1AdiZwW6aUGYeux7p38aAkLSMQbcxKAThMlYrYSUz"
                    +"EBZoLR8WP2U6oB2vHyRHH4v5X2XTD6zZm17mOiDz5+eQnlM3aNCUPcx20F9K9qwnNDDPEsbV1cnqZBAFEqkxGJgzPpfg"
                    +"BD34QI6MqQpx+uSEHom2ir8jiDaCOCv4rpDTMH0FRhaSC0ySf9ZICD1SUUgqYrU1iCZnXWRTNp26Rk884+hLaYB7bBIL"
                    +"RuFH18iemWQSjTmycR26I9bA/ePUH4vV1Ehm4D0AH3UIJyaFmlmnaFAd7YGYkHkOZIJlsVvWF1x3WWy0goFl7MZ4VpmE"
                    +"htmNxHw2NH2AIstQgC+KrImBUFxRO2bhFfofomNdFiwagg/RsS4LFkGwag3Z6y8kxxvEKxuyd7gQTWMJmraQ/lE1f1Rt"
                    +"9LyPBT4QcdzB3PPYgNEYvM+wbMThs+QNAx7ZbUWFxxbILK7IVizfRlLG7HzZbxVTVYXQh0SN4JawRFX6/WGhsZgXDWO+"
                    +"MFUh0UxbgkUycNP3SNzeBK196vOTt9Bmwv4sjfWjiOW8KEyUlInSSQ3Mjde/xRLzaJ1bllKld4OIWYKzkGBrWIKu1fky"
                    +"ODW4R1aw1iJftkBFBovGGlPJ5qnxuSUbCrclPcCbwh41mD/W0NtypbsGipYjYVCugeJ9os767CwIj0KSUslsP5VjnqhD"
                    +"G/lgNaHECh8W01A/LJMs99DBrr4sO0LnA2qMbRzC/Gskgul083YOuNQaLc/R6H1yL0umUvBdKUgIeCgSIOnnhXvqaaL1"
                    +"BpFlub5juP7FD3O97HK9LFwvmC0sPBPXs2Kts6TCnDzvOmxP2r7YLBNTwoI/2sLDnJxCseRfvmQwmmVoP/ch0PsmyKen"
                    +"/SRJwx3hS07D/WY2PprmDCfbcIA5POzIgCaJz2SU2NOOjGpS3PKQCRUo+2lqQEtsZ7pk0zotCLadGdRr2+4MbWeGtmy4"
                    +"5nqxDx20JZnS2MDZFnPBSVFP8p4c0afJV57kt2XPBUy6Zc8EXlFPUMfLecWGsyITcaZUFpmWRTuLsmJD7GvRWXHJXXFJ"
                    +"VtR01tr+LPn1npPJ8qpMiMA4S34+LYTQdxylOe+sBIGsCofU92JV1xPN3ScHDMR4U+2JA9ozPLYq5LO8AypddliFPRLsi"
                    +"hnYQSNvJU8q68igbFDuCMWiyEGnHtZVj/SzZCqaNpLPHWf6JXzaSELNElviyGbiLcq5uo6qutZdn0dFF152Hr1s3NHcV"
                    +"EZjotqsxlzVZjzP9bqMWyYDbrl9kjoHVckeN+xt1TO9rfojvK086G1lGUgqMx2stn2walQ0TgWDuuOwurrilOPicDxuy"
                    +"Rlnk55OlCY9ZrKKFfbjDvFSia6A010R9nWntlTCnpSYOTJZVUpgjrgqlgxOSsMrSZpeMi7SFZmPU90VYM9ac6xQTVPQA"
                    +"v+BrT1W6AD1KD8wGeccYyXBIE4yQ/nmXHRVOjylVtykKAQTFj8JNRUKJbsOT8VQiqZQFvwkVQbjKF1Ryjj6DZP7jnt0ae" +
                    "5VVcSqKIHTlgy28vYpuMqu5DOTgptozrMvOElX+sct3hJYrIXLRB0XkIl4tnxkvCUnKkrn80+Yz41XqqZSsvScMMwMabYEn" +
                    "b1KMMwHIJrBJM0BGEYr/QwcU7M6L5EvDUIzHQeWyUAyPgaZ6DdtB54AzRiCgjnlPrbIpB+9SSdcqN4Y/khvbDjeKC70QH" +
                    "Aa9kZ2owLhNULwGeETjNXk4Hy7QjRJ+AsTMWajg4cxG9ZDRZx30WI3HAsVt+FqkNdpCAdlwULaMq4ttA1yQ9b76S5YE9Y" +
                    "2Fb9Rf1P8RmsOxW/U384dfqP1tNnMe3qwKEhh2HwvSnGcNTnbZxIHy1mTM34mYjwnEKM2XvFuIpiOyaEU13k3ekpcJ4fFv" +
                    "sTcIyKq3muF6wRyBJQEhSi8YFzHSK9LIO6ZEuw4Lt6hJKB76GA5D9A4/orgOWdI9AXgOa3E5q15dIyc7MevG57TSrX9rFy" +
                    "fiOc8B9cXeM4rW2mG+uYxPKPSDE+oNMOBSjO8wHP8ZNtUmFdeIp6TURdXjlNUMYZSMZ4F6/Ab7RwdDymiYw6cAHOZoqh4g" +
                    "e5coDvnHN35FL731gnozmm+90LQHYuldmTj/wOUh85Nk4EdjYFcYZ5JKozjJIV4YnFVVWVbltgiEcbizmMQRgw+x7CVGsT5J" +
                    "rYyRgIKbHssbdzPbaoj7UvnYFU93TwMEO3b0LOF0MP4UB57G7HKEiDpM1FKgeRUxYRN0uwYBsbETBulNk9wBQyP2aRiWQTSh" +
                    "EC4f9wKbFH6GqLDBtFxe1P+rhLC4BGMsS7V7brAHKrnqtThCiCFVPF6VKAVtUYwhZkWYwuUIPeoItSgHCaMl4S0ydH+7WSGI" +
                    "uFCdPXwNp6XSbtGyz0yWROYS/QNk71FTpiXEmGfJvOl+p83CU8oW5uXfK7kbJ+zuBzdD/EpxI/LccHWN6+5aUUCs1a7fJUH" +
                    "nVrtmppiTiJlNdFc0xDJlalqpDmnIZyyNXN9kNAsVXcI3aJ4qCDWAi3zwoO52yMic8i1JZKzkgqqnAcC1I1bBW1LOKzL+bSg" +
                    "4WiT9mTiAV2bMarJwy45QLStA7KDtTO2GYtNYiB7ZZ5SYMX4mgljdoFWeBr96lLpxUlaEqo1K+qj1hxIX5oEp8ChJsHqdi6N" +
                    "wrIWs3XBuNguuCuEabxx3UsnbDgThu5kDfKLgiYx5urXZczKiGxTYDvWmXSabISVNQHpMYEoLSVgpTHBdqqwm1j3Mk6dAAfN" +
                    "aP+uabiLhh3Hp3ZkhMRWHqFAZds0tKlhRjTG6KbRmIKTsauxmmgsFo010sPsJphnXlllEwJZkmfdxKl1GRspwrMmsIdRe9puJ" +
                    "A4wt2N14lnwLbEEMdg05hWDZgwDY9CNYQs9Cml1B8irO0Ce+lM5A+g5oH0ZBlCCRErYlanlfXyXqaY36F6AwJHe0hoG2wMLtF" +
                    "eTowp88j1xtYKNfTsSIwKN8oGYTlky1AYdwSkhB0CRR4P0wuDpPRGYJbgnwlICPWMD94wNnqYjhWIYg+b21P+03ZjGPcfv7g34n" +
                    "PbpKXRyf8+28zMihp6L3JHyas9G5VUdT3ceZnbNNUJHnDWUw7lD8YoP51AO5y3XFbeexhWbjis2yQocVzTWvwFz2CCzEIuPrStq" +
                    "ufAFzbSFPW9RgOHAsuUkOluy96aNzk6iWBe1cShkT2hSojAGT+HMI5HMxDrmtjCnjqnMBS5jgeOx9uYq86BJy4REDYegTcwE2LS" +
                    "JMwaLYDPm0kOj6bQM6JJFt0XDdzHuLsYlzgKJxKtQmQ7lrPJl8paUKcadGSfqEqbeivhuJGG6DTo2CzaGMaB+vCHxaxqjjouCr" +
                    "l/H0OkMusRQLqhTYMlgRxjB2O51zM9gEmNGOMwZSJI/FM6PTUDgtOMWWVop+fMlC+Fz3m12cMvu0xZpa8YjeLMGEivYOVvKhZn" +
                    "iv7Lx01CvlI8XjnotGctagmUtwUKWKCtEwzIaND7jtPjQOcFvUlTPzpMbeub3jGJXNQzgotIWlIWBW99adfak/+CU/qEJnCXSo" +
                    "vLooeIA952qMv/64wAX2NuzYG8c6QIH/D5Bgy8Ze3PuUoXR2XepUtDt4i7VhZe9NmjbS71LZb1N0TZW334G6c6ibQ6xVvgrMmB" +
                    "lAGazRT3nxood2KLeetR9AQ+OHlpfu3Cn8+9O5/HQGnajixdGF0fY6+Nz5/UIszBeUW7MfGLi+aVD/S2Mr29Vnv7M2nN8jV2pP" +
                    "HAi/cijy1VX+qIofPYXRc514LZzHdh46VZGK1y/NTPviwTPbDvIc6BCetbrwJkLiHodWCFH6XzoAJ+IZ9ybd4BPF/CUdw5znIz" +
                    "brNzgoNctMKLOYROEFItOkwRjJhbKYlNR4y4OvLMRfHIhstcOO47V/+xc3/y9l1wBZ/edW7/Qxj3YbzMZMR7xG7nDm6eEqJm4d" +
                    "3jBd5MQbUmI2CXycgFXMWR59cLgSoHzKf7Fel6AD77NdSdxXowZUZkbXXd0BEucRwy/iJFri06KFjtvZt6HBMzvzXOO1U//v1o" +
                    "z359Njbn7g8bsHr5v086q9KqRjdkfMGZ/wJiDAWMOHOMrO0ZbFqM17StJipavRIx6qzHzxThXGSnKvprwjWAzbpX2nBOj3pcrc" +
                    "z25LXcguf2umPekZPQ5x7z3Bxz3QE7IXcfYJ+WgVKe2Bn9NvHPCmC9fNxekKyfW/1OHwlp+hjIzR0709FOHwpYsZ63G2syuJk5" +
                    "y1mrsJoOrWZwuPWz1uiq3P8q0qwdppIodxcVDEW3gVWPN+WmITqivFvWfTAzCi7HzOjIeeK9ZO+kVZE1eP+oCocOJ264nssuJv" +
                    "tY3C7H+9i2S3xhyrBTJbzh5jP4njDji/4axJY5pzIw1xya2KWbGmtu3CWnVVhJsonrFP3Zej+jtfn0f2xpUZ2tApSlBz+l/NNT" +
                    "vqrd1iornsVlNLswt53mr5q4kGYwDayJd1fDPyXDBxreK824kddKuDXXp2wtMwC8CCnLZwPXYafHQrtw5KMg9A7mNUY/4NqyRP" +
                    "/vptOi2S6FmXfqrevehGunN/F1R+LRVeFm21ZR7IwN4Ql6CTnpJwebE2Y6ejU6D13t0oL3eG6ToRJ4a3P9zo/pK/81NLpMY1EV" +
                    "f7sGm9zpe+4PtPGdp6bUNJ0vTqxhNfRf8qmdpbavD1MjdOyRmt/NOHNayj//D0El0jz86gXDKqed6Ts2m5RcGKR7iVvcctNCZV" +
                    "vbpv3hyCYuJ/nwwpStG8qLOrd71ZxJp4W54p5eR0849imuiiF/KfQr9dVRPfhWlP1R0YQEz8V9GSDmNyP0vL/6TovcfjAuGn79LAAA=";

}
