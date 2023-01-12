package Services;

import Model.FileType;
import org.springframework.web.multipart.MultipartFile;

public interface ParseService {
     byte[] parse(FileType fileType, String outputFiletype) throws Exception;


}
