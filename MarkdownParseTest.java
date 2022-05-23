import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParseTest {

    private Path fileName1;
    private String content1;
    private ArrayList<String> links1;

    private Path fileName2;
    private String content2;
    private ArrayList<String> links2;

    private Path fileName3;
    private String content3;
    private ArrayList<String> links3;

    private Path fileName4;
    private String content4;
    private ArrayList<String> links4;

    private MarkdownParse parser = new MarkdownParse();

    @Before
    public void setUp() throws IOException {
        fileName1 = Path.of("test-file.md");
        content1 = Files.readString(fileName1);
        links1 = new ArrayList<String>();

        fileName2 = Path.of("code-breaker.md");
        content2 = Files.readString(fileName2);
        links2 = new ArrayList<String>();

        fileName3 = Path.of("third-breaker.md");
        content3 = Files.readString(fileName3);
        links3 = new ArrayList<String>();

        fileName4 = Path.of("fourth-breaker.md");
        content4 = Files.readString(fileName4);
        links4 = new ArrayList<String>();
    }

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testTestFile() {
        links1.add("https://something.com");
        links1.add("some-thing.html");
        assertEquals(links1, parser.getLinks(content1));
    }

    @Test
    public void testCodeBreaker() {
        links2.add("https://en.wikipedia.org/wiki/Trollface");
        assertEquals(links2, parser.getLinks(content2));
    }

    @Test
    public void testThirdBreaker() {
        links3.add("thisshouldbetheonlyreallink.com");
        assertEquals(links3, parser.getLinks(content3));
    }

    @Test
    public void testFourthBreaker() {
        assertEquals(links4, parser.getLinks(content4));
    }

    @Test
    public void testSnippetOne() throws IOException {
        ArrayList<String> links5 = new ArrayList<String>();
        links5.add("`google.com");
        links5.add("google.com");
        links5.add("ucsd.edu");
        assertEquals(links5, parser.getLinks(Files.readString(Path.of("snippet1.md"))));
    }

    @Test
    public void testSnippetTwo() throws IOException {
        ArrayList<String> links6 = new ArrayList<String>();
        links6.add("a.com");
        links6.add("a.com(())");
        links6.add("example.com");
        assertEquals(links6, parser.getLinks(Files.readString(Path.of("snippet2.md"))));
    }

    @Test
    public void testSnippetThree() throws IOException {
        ArrayList<String> links7 = new ArrayList<String>();
        links7.add("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
        assertEquals(links7, parser.getLinks(Files.readString(Path.of("snippet3.md"))));
    }
}

// javac -cp ".;lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar" MarkdownParseTest.java
// java -cp ".;lib/junit-4.12.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore MarkdownParseTest
