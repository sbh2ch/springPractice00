package com.son;

import com.son.common.UtilEtc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by kiost on 2017-04-21.
 */
public class BoardVO {
    private String brdno;
    private String brdtitle;
    private String brdwriter;
    private String brdmemo;
    private String brddate;
    private String brdhit;
    private String brddeleteflag;
    private String filecnt;
    private String replycnt;
    private List<MultipartFile> uploadfile;

    public BoardVO() {
        /* default constructor */
    }

    public String getReplycnt() {
        return replycnt;
    }

    public void setReplycnt(String replycnt) {
        this.replycnt = replycnt;
    }

    public String getShortTitle(Integer len) {
        return UtilEtc.getShortString(brdtitle, len);
    }

    public String getBrdno() {
        return brdno;
    }

    public void setBrdno(String brdno) {
        this.brdno = brdno;
    }

    public String getBrdtitle() {
        return brdtitle;
    }

    public void setBrdtitle(String brdtitle) {
        this.brdtitle = brdtitle;
    }

    public String getBrdwriter() {
        return brdwriter;
    }

    public void setBrdwriter(String brdwriter) {
        this.brdwriter = brdwriter;
    }

    public String getBrdmemo() {
        return brdmemo.replaceAll("(?i)<script", "&lt;script");
    }

    public void setBrdmemo(String brdmemo) {
        this.brdmemo = brdmemo;
    }

    public String getBrddate() {
        return brddate;
    }

    public void setBrddate(String brddate) {
        this.brddate = brddate;
    }

    public String getBrdhit() {
        return brdhit;
    }

    public void setBrdhit(String brdhit) {
        this.brdhit = brdhit;
    }

    public String getBrddeleteflag() {
        return brddeleteflag;
    }

    public void setBrddeleteflag(String brddeleteflag) {
        this.brddeleteflag = brddeleteflag;
    }

    public String getFilecnt() {
        return filecnt;
    }

    public void setFilecnt(String filecnt) {
        this.filecnt = filecnt;
    }

    public List<MultipartFile> getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(List<MultipartFile> uploadfile) {
        this.uploadfile = uploadfile;
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "brdno='" + brdno + '\'' +
                ", brdtitle='" + brdtitle + '\'' +
                ", brdwriter='" + brdwriter + '\'' +
                ", brdmemo='" + brdmemo + '\'' +
                ", brddate='" + brddate + '\'' +
                ", brdhit='" + brdhit + '\'' +
                ", brddeleteflag='" + brddeleteflag + '\'' +
                ", filecnt='" + filecnt + '\'' +
                ", uploadfile=" + uploadfile +
                '}';
    }
}
