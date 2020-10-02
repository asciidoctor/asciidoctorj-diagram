package org.asciidoctor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.asciidoctor.OptionsBuilder.options;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class WhenDocumentWantsDataUris {

    private Asciidoctor asciidoctor = Asciidoctor.Factory.create();

    @Test
    public void png_should_be_rendered_for_diagram() throws IOException {
        File buildDir = new File("build/resources/test");

        File inputFile = new File(buildDir, "data-uri.adoc");
        File outputFile = new File(buildDir, "data-uri.html");
        File outputFile1 = new File(inputFile.getParentFile(), "data-uri-test.png");
        File outputFile2 = new File(inputFile.getParentFile(), ".asciidoctor/diagram/data-uri-test.png.cache");
        asciidoctor.requireLibrary("asciidoctor-diagram");
        asciidoctor.convertFile(inputFile,
            options().backend("html5")
                .toFile(outputFile)
                .safe(SafeMode.SERVER)
                .get());
        assertThat(outputFile1.exists(), is(true));
        assertThat(outputFile2.exists(), is(true));
        outputFile1.delete();
        outputFile2.delete();

        String html = new String(Files.readAllBytes(outputFile.toPath()), StandardCharsets.UTF_8);
        final Document doc = Jsoup.parse(html);
        System.out.println(doc);
        Elements images = doc.getElementsByTag("img");
        assertEquals(1, images.size());
        assertThat(images.get(0).attr("src"), startsWith("data:image/png;base64,"));

    }
}
