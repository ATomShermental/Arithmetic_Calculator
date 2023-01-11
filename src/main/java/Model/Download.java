package Model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class Download {
    private String inputZIP;
    private String inputDec;
    private String outputType;
    private String outputZIP;
    private String outputEnc;
}
