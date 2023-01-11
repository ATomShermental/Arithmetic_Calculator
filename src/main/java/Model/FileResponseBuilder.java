package Model;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class FileResponseBuilder {
    private static final MediaType DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_OCTET_STREAM;

    private static final Map<String, MediaType> EXTENSION_MEDIA_TYPE_MAP = ImmutableMap.<String, MediaType>builder()
            .put("pdf", MediaType.APPLICATION_PDF)
            .put("zip", MediaType.APPLICATION_OCTET_STREAM)
            .build();

    public static ResponseEntity<byte[]> createResponse(String fileName, byte[] fileContent) {
        String extension = FilenameUtils.getExtension(fileName);
        String escapedFileName = fileName.replaceAll("\\s+", "");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(EXTENSION_MEDIA_TYPE_MAP.getOrDefault(extension, DEFAULT_MEDIA_TYPE));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + escapedFileName);
        header.setContentLength(fileContent.length);
        return new ResponseEntity<>(fileContent, header, HttpStatus.OK);
    }
}
