package org.asciidoctor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenDocumentWantsDataUris {

    private Asciidoctor asciidoctor = Asciidoctor.Factory.create();

    @Test
    public void png_should_be_rendered_for_diagram() throws IOException {
        File sourceDir = new File("build/resources/test");
        File inputFile = new File(sourceDir, "data-uri.adoc");

        File expectedOutput = new File(sourceDir, "data-uri.html");
        File expectedDiagram = new File(sourceDir, "data-uri-test.png");
        File expectedDiagramCache = new File(sourceDir, ".asciidoctor/diagram/data-uri-test.png.cache");

        asciidoctor.requireLibrary("asciidoctor-diagram");
        asciidoctor.convertFile(inputFile,
                Options.builder()
                        .backend("html5")
                        .mkDirs(true)
                        .toFile(expectedOutput)
                        .safe(SafeMode.SERVER)
                        .build());

        assertThat(expectedOutput).isNotEmpty();
        assertThat(expectedDiagram).exists();
        assertThat(expectedDiagramCache).exists();

        final Document doc = Jsoup.parse(readString(expectedOutput));
        Elements images = doc.getElementsByTag("img");
        assertThat(images).hasSize(1);
        assertThat(images.get(0).attr("src")).startsWith("data:image/png;base64,");
    }

    private String readString(File outputFile) throws IOException {
        return new String(Files.readAllBytes(outputFile.toPath()), StandardCharsets.UTF_8);
    }
}
