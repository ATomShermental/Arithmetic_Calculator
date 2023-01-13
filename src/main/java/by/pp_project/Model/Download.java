package by.pp_project.Model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Download {
    private boolean inputZip;
    private boolean inputDec;
    private String outputType;
    private boolean outputZip;
    private boolean outputEnc;
}
