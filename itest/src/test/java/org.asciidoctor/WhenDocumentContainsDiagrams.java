package org.asciidoctor;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenDocumentContainsDiagrams {

    private final Asciidoctor asciidoctor = Asciidoctor.Factory.create();

    @Test
    public void png_should_be_rendered_for_diagram() {

        File sourceDir = new File("build/resources/test");

        File sourceDocument = new File(sourceDir, "sample.adoc");
        File expectedDiagram = new File(sourceDir, "asciidoctor-diagram-process.png");
        File expectedDiagramCache = new File(sourceDir, ".asciidoctor/diagram/asciidoctor-diagram-process.png.cache");

        File expectedPlantumlDiagram = new File(sourceDir, "plantuml-test.svg");
        File expectedPlantumlDiagramCache = new File(sourceDir, ".asciidoctor/diagram/plantuml-test.svg.cache");

        File expectedJSyntraxDiagram = new File(sourceDir, "jsyntrax-test.svg");
        File expectedJSyntraxDiagramCache = new File(sourceDir, ".asciidoctor/diagram/jsyntrax-test.svg.cache");

        asciidoctor.requireLibrary("asciidoctor-diagram");
        asciidoctor.convertFile(sourceDocument, Options.builder().backend("html5").build());

        assertThat(expectedDiagram).isNotEmpty();
        assertThat(expectedDiagramCache).isNotEmpty();

        assertThat(expectedPlantumlDiagram).isNotEmpty();
        assertThat(expectedPlantumlDiagramCache).isNotEmpty();

        assertThat(expectedJSyntraxDiagram).isNotEmpty();
        assertThat(expectedJSyntraxDiagramCache).isNotEmpty();
    }
}
