package com.opkcloud.service.impl;

import com.opkcloud.dao.UserDao;
import com.opkcloud.model.ExcelBean;
import com.opkcloud.pojo.User;
import com.opkcloud.service.ExportService;
import com.opkcloud.util.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private UserDao userDao;

    @Override
    public void importExcelInfo(InputStream in, MultipartFile file, String salaryDate, Integer adminId) throws Exception {
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
        List<User> userList = new ArrayList<User>();
        //遍历listob数据，把数据放到List中
        for (int i = 0; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);
            User user = new User();
            //设置编号
//            salarymanage.setSerial(SerialUtil.salarySerial());
            //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载
            user.setId(adminId);
            user.setName((String) ob.get(1));
            user.setAge(Integer.valueOf((String) ob.get(2)));
//            salarymanage.setAdminId(adminId);
//            salarymanage.setCompany(String.valueOf(ob.get(1)));
//            salarymanage.setNumber(String.valueOf(ob.get(2)));
//            salarymanage.setName(String.valueOf(ob.get(3)));
//            salarymanage.setSex(String.valueOf(ob.get(4)));
//            salarymanage.setCardName(String.valueOf(ob.get(5)));
//            salarymanage.setBankCard(String.valueOf(ob.get(6)));
//            salarymanage.setBank(String.valueOf(ob.get(7)));
//            //object类型转Double类型
//            salarymanage.setMoney(Double.parseDouble(ob.get(8).toString()));
//            salarymanage.setRemark(String.valueOf(ob.get(9)));
//            salarymanage.setSalaryDate(salaryDate);
            userList.add(user);
        }
        //批量插入
        userDao.insertInfoBatch(userList);
    }

    @Override
    public XSSFWorkbook exportExcelInfo(String salaryDate) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
        //根据条件查询数据，把数据装载到一个list中
        List<User> list = userDao.findAll();
        for(int i=0;i<list.size();i++){
            //查询财务名字
            int adminId = list.get(i).getId();
            String userName = userDao.findUserById(adminId);
            list.get(i).setName(userName);
            list.get(i).setId(i+1);
        }
        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook=null;
        //设置标题栏
        excel.add(new ExcelBean("Id","id",0));
        excel.add(new ExcelBean("姓名","name",0));
        excel.add(new ExcelBean("年龄","age",0));
//        excel.add(new ExcelBean("姓名","name",0));
//        excel.add(new ExcelBean("性别","sex",0));
//        excel.add(new ExcelBean("开户名","cardName",0));
//        excel.add(new ExcelBean("银行卡号","bankCard",0));
//        excel.add(new ExcelBean("开户行","bank",0));
//        excel.add(new ExcelBean("金额","money",0));
//        excel.add(new ExcelBean("备注","remark",0));
        map.put(0, excel);
        String sheetName = salaryDate + "月份收入";
        //调用ExcelUtil的方法
        xssfWorkbook = ExcelUtil.createExcelFile(User.class, list, map, sheetName);
        return xssfWorkbook;
    }

    @Override
    public void downLoadZip(HttpServletResponse response) {
        List<User> list = userDao.findAll();
//        HttpServletResponse response;
        ServletOutputStream out = null;
        OutputStream os = null;

        File zip = new File("比对数据.zip");
        SXSSFWorkbook web = null;
        try {
            List<String> fileNames = new ArrayList<>();
            for (int l = 0, n = list.size() % 3 == 0 ? list.size() / 3 : (list.size() / 3) + 1; l < n; l++) {
                web = new SXSSFWorkbook();
                Sheet sheet = web.createSheet();
                CellStyle cellStyle = web.createCellStyle();
                cellStyle.setWrapText(true);//自动换行
                cellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
                cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中

                Font font = web.createFont();
                font.setFontHeightInPoints((short) 14);
                font.setFontName("仿宋_GB2312");
                cellStyle.setFont(font);

                Row row;
                Cell cell = null;
                row = sheet.createRow(0);
                setTitle(row, cell);
                String file = "比对数据" + (l + 1) + ".xlsx";
                fileNames.add(file);

                FileOutputStream o = new FileOutputStream(file);
                int k = 0;
                for (int i = 0, min = (list.size() - l * 3) > 3 ? 3 : (list.size() - l * 3); i < min; i++) {
                    row = sheet.createRow(i + 1 + k);
                    User user = list.get(3 * l + i);
                    createValue(row, cell, user);
                    if (i % 10 == 0) {
                        ((SXSSFSheet) sheet).flushRows();
                    }
                }
                o.flush();
                web.write(o);
                o.close();
            }
            out = response.getOutputStream();
            response.reset();
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("比对人员数据").getBytes(), "ISO8859-1") + ".zip");
            zipFile(fileNames, zip);
            FileInputStream inStream = new FileInputStream(zip);

            byte[] buf = new byte[1024];
            int readLength;
            while ((readLength = inStream.read(buf)) != -1) {
                out.write(buf, 0, readLength);
            }
            inStream.close();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setTitle(Row row, Cell cell) {
        cell = row.createCell(0);
        cell.setCellValue("Id");
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell = row.createCell(2);
        cell.setCellValue("年龄");
    }

    private void createValue(Row row, Cell cell, User user) {
        setValue(0, user.getId() + "", row, cell);
        setValue(1, user.getName(), row, cell);
        setValue(2, user.getAge() + "", row, cell);
    }

    private void setValue(int code, String value, Row row, Cell cell) {
        if (value.equals(""))
            return;
        cell = row.createCell(code);
        cell.setCellValue(value);
    }

    private void zipFile(List<String> fileNames, File zipFile) {
        byte[] buf = new byte[1024];
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFile));
            for (String fileName : fileNames) {
                File file = new File(fileName);
                FileInputStream in = new FileInputStream(file);
                out.putNextEntry(new ZipEntry(file.getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
