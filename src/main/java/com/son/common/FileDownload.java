package com.son.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;


/**
 * Created by kiost on 2017-04-28.
 */
@Controller
public class FileDownload {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownload.class);

    @RequestMapping("/fileDownload")
    public void fileDownload(HttpServletRequest req, HttpServletResponse res) {
        String path = "f:\\fileupload\\";
        String fileName = req.getParameter("fileName");
        String downName = req.getParameter("downName");
        String realPath = "";
        System.out.println(fileName);

        if (fileName == null || "".equals(fileName)) {
            fileName = downName;
        }
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }

        realPath = path + downName.substring(0, 4) + "\\" + downName;
        File file1 = new File(realPath);

        if (!file1.exists()) {
            return;
        }
        res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        try {
            OutputStream os = res.getOutputStream();
            FileInputStream fis = new FileInputStream(realPath);

            int ncount;
            byte[] bytes = new byte[512];

            while ((ncount = fis.read(bytes)) != -1) {
                os.write(bytes, 0, ncount);
            }
            fis.close();
            os.close();
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
        }
    }
}
