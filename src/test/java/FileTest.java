import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author chanwook
 */
public class FileTest {

    /**
     * 경로에 있는 폴더를 삭제하고 다시 그 폴더를 생성하고, 마지막으로 파일 생성까지..
     *
     * @throws Exception
     */
    @Test
    public void testToFilesClass() throws Exception {
        // create sample file
        // TODO 좀더 간결하게 표현할 수 있는 방법은?
        final Path dir = Files.createDirectory(Paths.get("src/test/resources/file/wtree"));
        Files.createFile(Paths.get("/file/wtree/a.txt"));
        Files.createFile(Paths.get("/file/wtree/b.txt"));
        Files.createFile(Paths.get("/file/wtree/c.txt"));

    }
}
