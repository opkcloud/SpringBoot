package com.pancm.web;

import com.pancm.service.ExportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class ExportController {

    /**
     * 在SSM下基于POI实现Excel表的导入/导出教程
     * 页面参考: https://www.jianshu.com/p/198497e08e00
     *          https://blog.csdn.net/dirkwlk/article/details/81909663
     * 后台接口参考: https://blog.csdn.net/hsf15768615284/article/details/73136029
     */

    @Autowired
    private ExportService exportService;

    @ApiOperation(value = "Excel表的导入", httpMethod = "GET", notes = "Excel表的导入")
    @RequestMapping("/import")
    public String impotr(HttpServletRequest request, Model model) throws Exception {
        int adminId = 1;
        //获取上传的文件
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        MultipartFile file = multipart.getFile("file");
        String month = request.getParameter("month");
        InputStream in = file.getInputStream();
        //数据导入
        exportService.importExcelInfo(in,file,month,adminId);
        in.close();
        return "01";
    }

    @ApiOperation(value = "Excel表的导出", httpMethod = "GET", notes = "Excel表的导出")
    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException {
        String salaryDate = request.getParameter("salaryDate");
        if(salaryDate!=""){
            response.reset(); //清除buffer缓存
            Map<String,Object> map=new HashMap<String,Object>();
            // 指定下载的文件名，浏览器都会使用本地编码，即GBK，浏览器收到这个文件名后，用ISO-8859-1来解码，然后用GBK来显示
            // 所以我们用GBK解码，ISO-8859-1来编码，在浏览器那边会反过来执行。
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String(salaryDate.getBytes("GBK"),"ISO-8859-1"));
            // 设置文件名
            response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            XSSFWorkbook workbook=null;
            //导出Excel对象
//            workbook = salaryService.exportExcelInfo(salaryDate);
            workbook = exportService.exportExcelInfo(salaryDate);
            OutputStream output;
            try {
                output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                bufferedOutPut.flush();
                workbook.write(bufferedOutPut);
                bufferedOutPut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            File file = new File("111.xlsx");
            File zip = new File("数据.zip");
            try {
                ZipOutputStream zOut = new ZipOutputStream(new FileOutputStream(zip));
                zOut.putNextEntry(new ZipEntry(file.getName()));
                FileInputStream in = new FileInputStream(file);
                int len = 0;
                // 读取文件的内容,打包到zip文件
                while ((len = in.read(buffer)) > 0) {
                    zOut.write(buffer, 0, len);
                }
                zOut.closeEntry();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
