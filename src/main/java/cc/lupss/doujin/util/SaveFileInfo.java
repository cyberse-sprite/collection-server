package cc.lupss.doujin.util;

import lombok.Data;

@Data
public class SaveFileInfo {
    String infoName;
    String fileExt;
    String folder;
    int code;
    String message;
    String zipPath;
    String imagePath;
}
