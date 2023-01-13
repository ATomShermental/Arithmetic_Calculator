package by.pp_project.Services;

import by.pp_project.Model.FileType;

public interface ParseService {
     byte[] parse(FileType fileType, String outputFiletype) throws Exception;


}
