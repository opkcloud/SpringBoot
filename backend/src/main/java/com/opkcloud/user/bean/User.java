package com.opkcloud.user.bean;

import com.opkcloud.util.logs.RecordAnnon;
import lombok.Data;

/**
 * 
* Title: User
* Description:用户pojo类
* Version:1.0.0  
* @author opkcloud
* @date 2017年9月26日
 */
@Data
public class User {
	 /** 编号 */
	 @RecordAnnon(value = "编号")
	 private int id;
	 /** 姓名 */
	 @RecordAnnon(value = "姓名")
	 private String name;
	 
	 /** 年龄 */
	 @RecordAnnon(value = "年龄")
	 private int age;

}
