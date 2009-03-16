package com.tddinaction.parameterized;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FileDrivenTest {

    private final File dataFile;

    public FileDrivenTest(File dataFile) {
        this.dataFile = dataFile;
    }

    @Parameters
    public static Collection<Object[]> parameters() throws Exception {
        File[] testfiles = findTestDataFromFileSystem();
        Collection<Object[]> params = new ArrayList<Object[]>();
        for (int i = 0; i < testfiles.length; i++) {
            params.add(new Object[] { testfiles[i] });
        }
        return params;
    }

    private static File[] findTestDataFromFileSystem() {
        File packageDir = new File(("./src/test/java/" + FileDrivenTest.class
                .getPackage().getName().replace('.', '/')));
        File[] testfiles = packageDir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });
        return testfiles;
    }

    @Test
    public void testSomethingWithTheFile() {
        System.out.println("Testing with " + dataFile.getName());
    }
}