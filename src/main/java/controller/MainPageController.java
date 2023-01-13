package controller;

import Implementations.ParseServiceImplementation;
import Model.Download;
import Model.FileResponseBuilder;
import Model.FileType;
import Services.ParseService;
import DESImplementation.DES;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zip.ZIP;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;


@RequiredArgsConstructor
@RestController
@RequestMapping("/arithmetic")
public class MainPageController {

    private final ParseService parseService = new ParseServiceImplementation();
    private final ZIP zip = new ZIP();



    @PostMapping(path = "/calculate", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<byte[]> create(@RequestPart("options") Download download, @RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
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
