package org.asciidoctor;

import org.junit.Test;

import java.io.File;

import static org.asciidoctor.OptionsBuilder.options;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WhenDocumentContainsDitaaDiagram {

    private Asciidoctor asciidoctor = Asciidoctor.Factory.create();

    @Test
    public void png_should_be_rendered_for_diagram() {

        File inputFile = new File("build/resources/test/sample.adoc");
        File outputFile1 = new File(inputFile.getParentFile(), "asciidoctor-diagram-process.png");
        File outputFile2 = new File(inputFile.getParentFile(), ".asciidoctor/diagram/asciidoctor-diagram-process.png.cache");
        asciidoctor.requireLibrary("asciidoctor-diagram");
        asciidoctor.convertFile(inputFile,
            options()
                .backend("html5")
                .toDir(new File("build"))
                .get());
        assertThat(outputFile1.exists(), is(true));
        assertThat(outputFile2.exists(), is(true));
        outputFile1.delete();
        outputFile2.delete();
    }
}
