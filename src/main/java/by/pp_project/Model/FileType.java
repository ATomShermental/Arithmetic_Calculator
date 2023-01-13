package by.pp_project.Model;

import lombok.Data;

@Data
public class FileType {
    public String filename;
    public byte[] data;

    public FileType(String filename, byte[] data){
        this.filename = filename;
        this.data = data;
    }
}
