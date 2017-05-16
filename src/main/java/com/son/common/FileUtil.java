package com.son.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by kiost on 2017-04-24.
 */
public class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public List<FileVO> saveAllFiles(List<MultipartFile> upfiles) {
        String filePath = "f:\\fileupload\\";
        List<FileVO> fileList = new ArrayList<FileVO>();

        for (MultipartFile uploadFile : upfiles) {
            if (uploadFile.getSize() == 0)
                continue;

            String newName = getNewName();

            saveFile(uploadFile, filePath, "/" + newName.substring(0, 4) + "/" + newName);

            FileVO fileVo = new FileVO();
            fileVo.setFileName(uploadFile.getOriginalFilename());
            fileVo.setRealName(newName);
            fileVo.setFileSize(uploadFile.getSize());
            fileList.add(fileVo);
        }

        return fileList;
    }

    public void makeBasePath(String path) {
        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();
    }

    public String saveFile(MultipartFile file, String basePath, String fileName) {
        if (file == null || file.getName().equals("") || file.getSize() < 1) {
            return null;
        }
        makeBasePath(basePath);
        String serverFullPath = basePath + fileName;

        File file1 = new File(serverFullPath);

        try {
            file.transferTo(file1);
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }

        return serverFullPath;
    }

    public String getNewName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        return sdf.format(new Date()) + new Random().nextInt(10);
    }

    public String getFileExtension(String fileName) {
        Integer mid = fileName.lastIndexOf('.');
        return fileName.substring(mid, fileName.length());
    }

    public String getRealPath(String path, String fileName) {
        return path + fileName.substring(0, 4) + "/";
    }
}
