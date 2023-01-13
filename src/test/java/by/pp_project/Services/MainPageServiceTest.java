package by.pp_project.Services;

import by.pp_project.Model.Download;
import by.pp_project.Model.FileType;
import by.pp_project.zip.ZIP;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;

class MainPageServiceTest {

    @Test
    void create() throws Exception {
        MainPageService service = new MainPageService();

        //mock the parseService
        ParseService parseService = Mockito.mock(ParseService.class);
        service.parseService = parseService;

        //mock the zip
        ZIP zip = Mockito.mock(ZIP.class);
        service.zip = zip;

        //create a mocked multipartFile
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());

        Download download = new Download();
        download.setInputZip(true);
        download.setInputDec(false);
        download.setOutputZip(false);
        download.setOutputEnc(false);
        download.setOutputType("plain");

        //define the mocked parse service behavior
        byte[] parsedData = "parsed data".getBytes();
        Mockito.when(parseService.parse(Mockito.any(FileType.class), Mockito.anyString())).thenReturn(parsedData);
        FileType unzippedFileType = new FileType("test.txt", "unzipped data".getBytes());
        Mockito.when(zip.allUnzip(Mockito.any(FileType.class))).thenReturn(unzippedFileType);

        //call the create method
        ResponseEntity<byte[]> response = service.create(download, multipartFile);

        //verify the parseService and zip methods were called
        Mockito.verify(parseService).parse(Mockito.any(FileType.class), Mockito.anyString());
        Mockito.verify(zip).allUnzip(Mockito.any(FileType.class));

        //verify the response body
        assertArrayEquals(parsedData, response.getBody());

        //verify the response headers
        assertEquals("attachment; filename=results.txt", response.getHeaders().get("Content-Disposition").get(0));
        assertEquals("application/octet-stream", response.getHeaders().get("Content-Type").get(0));


    }
}