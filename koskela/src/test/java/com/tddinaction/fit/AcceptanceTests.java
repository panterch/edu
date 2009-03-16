package com.tddinaction.fit;

import java.io.File;

import junit.framework.TestCase;
import fitlibrary.runner.FolderRunner;
import fitlibrary.runner.Report;

/**
 * @author lkoskela
 */
public class AcceptanceTests extends TestCase {

    public void testAcceptance() throws Exception {
        File targetDirectory = new File("target/storytests");
        targetDirectory.mkdirs();

        FolderRunner runner = new FolderRunner();
        Report report = runner.run(new File(
                "src/test/resources/storytests").getAbsolutePath(),
                targetDirectory.getAbsolutePath());
        System.out.println(report.getCounts());
    }
}
