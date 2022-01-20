package org.asciidoctor;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenDocumentContainsDitaaDiagram {

    private Asciidoctor asciidoctor = Asciidoctor.Factory.create();

    @Test
    public void png_should_be_rendered_for_diagram() {
        File sourceDir = new File("build/resources/test");
        File inputFile = new File(sourceDir, "sample.adoc");

        File outputDiagram = new File(sourceDir, "asciidoctor-diagram-process.png");
        File outputDiagramCache = new File(sourceDir, ".asciidoctor/diagram/asciidoctor-diagram-process.png.cache");

        asciidoctor.requireLibrary("asciidoctor-diagram");
        asciidoctor.convertFile(inputFile,
                Options.builder()
                        .backend("html5")
                        .toFile(new File(sourceDir, "sample.html"))
                        .build());

        assertThat(outputDiagram).exists();
        assertThat(outputDiagramCache).exists();
    }
}
