package org.asciidoctor;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenDocumentContainsDitaaDiagram {

    private Asciidoctor asciidoctor = Asciidoctor.Factory.create();

    @Test
    public void png_should_be_rendered_for_diagram() {

        File sourceDir = new File("build/resources/test");

        File sourceDocument = new File(sourceDir, "sample.adoc");
        File expectedDiagram = new File(sourceDir, "asciidoctor-diagram-process.png");
        File expectedDiagramCache = new File(sourceDir, ".asciidoctor/diagram/asciidoctor-diagram-process.png.cache");

        asciidoctor.requireLibrary("asciidoctor-diagram");
        asciidoctor.convertFile(sourceDocument, Options.builder().backend("html5").build());

        assertThat(expectedDiagram).isNotEmpty();
        assertThat(expectedDiagramCache).isNotEmpty();
    }
}
