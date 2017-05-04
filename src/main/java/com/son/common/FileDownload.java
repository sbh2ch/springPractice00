package com.son.common;

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
    @RequestMapping("/fileDownload")
    public void fileDownload(HttpServletRequest req, HttpServletResponse res) {
        String path = "d:\\fileupload\\";
        String fileName = req.getParameter("fileName");
        String downName = req.getParameter("downName");
        String realPath = "";

        if (fileName == null || "".equals(fileName)) {
            fileName = downName;
        }

        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        realPath = path + downName.substring(0, 4) + "\\" + downName;
        System.out.println("realPath : "+realPath);
        File file1 = new File(realPath);

        if (!file1.exists())
            return;

        res.setHeader("Content-Disposition", "attachment; file");

        try {
            OutputStream os = res.getOutputStream();
            FileInputStream fis = new FileInputStream(realPath);

            int ncount = 0;
            byte[] bytes = new byte[1024];

            while ((ncount = fis.read(bytes)) != -1) {
                os.write(bytes, 0, ncount);
            }

            fis.close();
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
