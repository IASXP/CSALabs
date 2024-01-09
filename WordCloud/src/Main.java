import java.io.IOException;

public class Main {
    static WordCloud wordCloud;

    public static void main(String[] args) {
        try {
            wordCloud = new WordCloud("dream.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        wordCloud.printInfo();

    }
}