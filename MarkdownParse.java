//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            // if no more complete links with all 4 components, exit loop
            if (openBracket == -1 || closeBracket == -1 || 
                openParen == -1 || closeParen == -1) {
                    break;
                }

            // skip over the image links
            if (openBracket != 0 && markdown.charAt(openBracket - 1) == '!') {
                currentIndex = closeParen + 1;
                continue;
            }

            // skip over brackets and parentheses that do not directly touch
            if (closeBracket != openParen - 1) {
                currentIndex = closeParen + 1;
                continue;
            }

            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }

        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        MarkdownParse parser = new MarkdownParse();
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = parser.getLinks(content);
        System.out.println("These are the links: ");
	System.out.println(links);
    }
}
