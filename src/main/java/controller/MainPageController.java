package controller;

import Implementations.ParseServiceImplementation;
import Model.Download;
import Model.FileResponseBuilder;
import Services.ParseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@RestController
@RequestMapping("/arithmetic")
public class MainPageController {

    private final ParseService parseService = new ParseServiceImplementation();


    @PostMapping(path = "/calculate", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<byte[]> create(@RequestPart("options") Download download, @RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
        byte[] responseArray = parseService.parse(multipartFile, download.getOutputType());
        return FileResponseBuilder.createResponse(multipartFile.getOriginalFilename(), responseArray);

    }
}
