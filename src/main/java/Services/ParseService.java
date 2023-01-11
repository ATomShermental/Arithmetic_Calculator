package Services;

import org.springframework.web.multipart.MultipartFile;

public interface ParseService {
    public byte[] parse(MultipartFile multipartFile, String outputFiletype) throws Exception;


}
