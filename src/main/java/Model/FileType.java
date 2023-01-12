package Model;

import lombok.Data;

@Data
public class FileType {
    String filename;
    byte[] data;

    public FileType(String filename, byte[] data){
        this.filename = filename;
        this.data = data;
    }
}
