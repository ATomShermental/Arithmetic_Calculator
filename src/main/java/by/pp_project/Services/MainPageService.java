package by.pp_project.Services;

import by.pp_project.DESImplementation.DES;
import by.pp_project.Implementations.ParseServiceImplementation;
import by.pp_project.Model.Download;
import by.pp_project.Model.FileResponseBuilder;
import by.pp_project.Model.FileType;
import by.pp_project.zip.ZIP;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
@Service
public class MainPageService {

    ParseService parseService = new ParseServiceImplementation();
    ZIP zip = new ZIP();

    public ResponseEntity<byte[]> create(Download download, MultipartFile multipartFile) throws Exception {
        FileType fileType = new FileType(multipartFile.getOriginalFilename(),multipartFile.getBytes());
        if(download.isInputZip() && !download.isInputDec()){
            fileType = zip.allUnzip(fileType);
        }

        else if(!download.isInputZip() && download.isInputDec()){
            fileType.data = DES.decrypt(fileType.getData(),"12345678".getBytes());
        }

        else if(download.isInputZip() && download.isInputDec()){
            fileType = zip.allUnzip(fileType);
            fileType.data = DES.decrypt(fileType.getData(),"12345678".getBytes());

        }
        fileType.data = parseService.parse(fileType, download.getOutputType());

        if(download.isOutputZip() && !download.isOutputEnc()){
            fileType.filename = "results";
            switch (download.getOutputType()){
                case "xml":
                    fileType.filename += ".xml";
                    break;
                case "json":
                    fileType.filename += ".json";
                    break;
                case "plain":
                    fileType.filename += ".txt";
            }
            fileType.data = zip.zip(fileType.getFilename(), fileType.getData());
        }

        else if(!download.isOutputZip() && download.isOutputEnc()){
            fileType.data = DES.encrypt(fileType.getData(),"12345678".getBytes());
        }

        else if(download.isOutputZip() && download.isOutputEnc()){
            fileType.data = DES.encrypt(fileType.getData(),"12345678".getBytes());
            fileType.data = zip.zip(fileType.getFilename(), fileType.getData());
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(fileType.data);
        String buffer = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1);
        fileType.data = buffer.getBytes();
        byteArrayOutputStream.close();
        return FileResponseBuilder.createResponse(fileType.getFilename(), fileType.getData());

    }
}
