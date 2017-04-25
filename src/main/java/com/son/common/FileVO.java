package com.son.common;

/**
 * Created by kiost on 2017-04-24.
 */
public class FileVO {
    private Integer fileNo;
    private String parentPK;
    private String fileName;
    private String realName;
    private long fileSize;

    public String size2String() {
        Integer unit = 1024;
        if (fileSize < unit) {
            return String.format("(%d B)", fileSize);
        }
        int exp = (int) (Math.log(fileSize) / Math.log(unit));

        return String.format("(%.0f %s)", fileSize / Math.pow(unit, exp), "KMGTPE".charAt(exp - 1));
    }

    public Integer getFileNo() {
        return fileNo;
    }

    public void setFileNo(Integer fileNo) {
        this.fileNo = fileNo;
    }

    public String getParentPK() {
        return parentPK;
    }

    public void setParentPK(String parentPK) {
        this.parentPK = parentPK;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
