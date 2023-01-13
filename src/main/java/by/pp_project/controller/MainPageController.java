package by.pp_project.controller;


import by.pp_project.Model.Download;
import by.pp_project.Services.MainPageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@AllArgsConstructor
@RestController
@RequestMapping("/arithmetic")
public class MainPageController {
    @Autowired
    MainPageService mainPageService;



    @PostMapping(path = "/calculate", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<byte[]> create(@RequestPart("options") Download download, @RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
        return mainPageService.create(download, multipartFile);
    }
}
