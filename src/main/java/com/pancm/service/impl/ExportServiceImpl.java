package com.pancm.service.impl;

import com.pancm.dao.UserDao;
import com.pancm.model.ExcelBean;
import com.pancm.pojo.User;
import com.pancm.service.ExportService;
import com.pancm.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

}
