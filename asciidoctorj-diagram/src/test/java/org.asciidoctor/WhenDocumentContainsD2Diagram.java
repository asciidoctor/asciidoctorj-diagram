package org.asciidoctor;


import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenDocumentContainsD2Diagram {

    private final Asciidoctor asciidoctor = Asciidoctor.Factory.create();

    @Test
    public void svg() {
        File sourceDir = new File("build/resources/test");
        File inputFile = new File(sourceDir, "sampled2.adoc");

        File outputDiagram = new File(sourceDir, "testd2.svg");
        File outputDiagramCache = new File(sourceDir, ".asciidoctor/diagram/testd2.svg.cache");

        asciidoctor.requireLibrary("asciidoctor-diagram");
        asciidoctor.convertFile(inputFile,
                Options.builder()
                        .backend("html5")
                        .toFile(new File(sourceDir, "sampled2.html"))
                        .build());

        assertThat(outputDiagram).exists();
        assertThat(outputDiagramCache).exists();
    }
}
