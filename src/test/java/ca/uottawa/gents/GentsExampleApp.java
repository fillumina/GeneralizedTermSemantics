package ca.uottawa.gents;

import ca.uottawa.gents.relatedness.LoadForRelatedness;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Francesco Illuminati <fillumina@gmail.com>
 */
public class GentsExampleApp {

    public static void main(String[] args) throws IOException {
        LoadForRelatedness loader;
        try (BufferedReader labels = getBufferedReaderFromResource(GentsExampleApp.class,
                "/data/sampleMatrix_n/sampleMatrix_n.rlabel");
                BufferedReader matrix = getBufferedReaderFromResource(GentsExampleApp.class,
                        "/data/sampleMatrix_n/matrix_crs.mat")) {

            loader = new LoadForRelatedness(labels, matrix);
        }

        System.out.println("Distance between \"boy\" and \"boy\": " + 
                loader.distance("boy", "boy"));
        System.out.println("Distance between \"boy\" and \"girl\": " + 
                loader.distance("boy", "girl"));
        
        show(loader, "girl", "boy");
        show(loader, "adult", "boy");
        show(loader, "child", "boy");
        show(loader, "kid", "boy");
        show(loader, "kid", "child");
        show(loader, "baby", "child");
        show(loader, "baby", "infant");

        System.out.println("List of the top 10 closest words to \"geometry\":");
        System.out.println(loader.getWordArrayString(loader.getClosestWords(
                "geometry", 10)));
    }

    public static void show(LoadForRelatedness loader, String word1, String word2) {
        System.out.println("Distance between \"" + word1 + "\" and \"" + 
                word2 + "\": " +
                loader.distance(word1, word2));
    }

    public static BufferedReader getBufferedReaderFromResource(
            Class<GentsExampleApp> clazz, String resource) {
        final InputStream inputStream = clazz.getResourceAsStream(resource);
        final InputStreamReader reader = new InputStreamReader(inputStream);
        return new BufferedReader(reader);
    }

}
