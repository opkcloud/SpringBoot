package com.pancm.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public interface ExportService {

    public void importExcelInfo(InputStream in, MultipartFile file, String salaryDate, Integer adminId) throws Exception;
    public XSSFWorkbook exportExcelInfo(String salaryDate) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;

    public void downLoadZip(HttpServletResponse response);

}
