package org.asciidoctor;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenDocumentContainsQrcode {

    private final Asciidoctor asciidoctor = Asciidoctor.Factory.create();

    @Test
    public void png_should_be_rendered_for_qrcode() {
        File sourceDir = new File("build/resources/test");
        File inputFile = new File(sourceDir, "sampleqrcode.adoc");

        File outputDiagram = new File(sourceDir, "testqrcode.png");
        File outputDiagramCache = new File(sourceDir, ".asciidoctor/diagram/testqrcode.png.cache");

        asciidoctor.requireLibrary("asciidoctor-diagram");
        asciidoctor.convertFile(inputFile,
                Options.builder()
                        .backend("html5")
                        .toFile(new File(sourceDir, "sampleqrcode.html"))
                        .build());

        assertThat(outputDiagram).exists();
        assertThat(outputDiagramCache).exists();
    }
}
