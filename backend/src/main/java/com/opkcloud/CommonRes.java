package com.opkcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * 通用数据响应结构
 *
 * @author zhousir
 * @since 1.0.0-RELEASE
 *
 */
@Slf4j
@Component
public class CommonRes<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 响应码 */
	private String code;

	/** 响应信息 */
	private String Msg;

	/** 数据体 */
	private T data;

	/*@Autowired
	private I18NHelper i18NHelperx;

	private static I18NHelper i18NHelper;
	@PostConstruct
	public void init(){
		i18NHelper = i18NHelperx;
	}*/

	public CommonRes() {
	}


	private CommonRes(String code, String msg, T data) {
		this.code = code;
//		this.Msg = StringUtil.isNullOrEmpty(msg) ? i18NHelper.getMessage(code,null) : msg;
		this.data= data;
	}

	private CommonRes(Coder coder, T data) {
		this.code = coder.getId();
		this.data= data;
		/*try {
			this.Msg = i18NHelper.getMessage(coder.getId(),null);
		} catch(Exception e) {
			log.error("Fail to get message", coder.getId());
			this.Msg = coder.getDefaultMsg();
		}*/
	}

	private CommonRes(Coder coder, T data, String[] args) {
		this.code = coder.getId();
		this.data= data;
//		this.Msg = i18NHelper.getMessage(coder.getId(),null, args);
	}

	/**
	 * 构造一个CommonRes
	 *
	 * @param coder Coder实例
	 * @return
	 */
	public static <T> CommonRes<T> makeRes(Coder coder){
		return new CommonRes<T>(coder,null);
	}


	/**
	 * 构造一个CommonRes
	 *
	 * @param coder Coder实例
	 * @param data 数据体
	 * @return
	 */
	public static <T> CommonRes<T> makeRes(Coder coder, T data){
		return new CommonRes<T>(coder, data);
	}

	/**
	 * 构造一个CommonRes，传入自定义参数
	 *
	 * @param coder Coder实例
	 * @param data 数据体
	 * @return
	 */
	public static <T> CommonRes<T> makeRes(Coder coder, String[] args, T data){
		return new CommonRes<T>(coder, data, args);
	}

	/**
	 * 构造一个CommonRes
	 * @param code 自定义响应码
	 * @param msg 自定义响应信息
	 * @return
	 */
	public static <T> CommonRes<T> makeRes(String code, String msg){
		return new CommonRes<T>(code, msg, null);
	}


	/**
	 * 构造一个CommonRes
	 * @param code 自定义响应码
	 * @param msg 自定义响应信息
	 * @param data 数据体
	 * @return
	 */
	public static <T> CommonRes<T> makeRes(String code, String msg, T data){
		return new CommonRes<T>(code, msg, data);
	}

	//	public static <T> CommonRes<T> fail(String msg){
//		String temp = StringUtil.isNullOrEmpty(msg)?ResCoder.DATA_EXIST.desc(): msg;
//		return new CommonRes<T>(ResCoder.FAIL.id(),temp,null);
//	}
	public static <T> CommonRes<T> fail(){
		return new CommonRes<T>(ResCoder.FAIL,null);
	}
	public static <T> CommonRes<T> fail(String code){
		return new CommonRes<T>(code,"",null);
	}
	public static <T> CommonRes<T> fail(Coder coder){
		return new CommonRes<T>(coder,null);
	}
	/*public static <T> CommonRes<T> fail(Coder coder, String msg){
		String temp = StringUtil.isNullOrEmpty(msg)?ResCoder.DATA_EXIST.getDefaultMsg(): msg;
		return new CommonRes<T>(coder.getId(),temp,null);
	}

	public static <T> CommonRes<T> fail(Coder coder, String msg, T data){
		String temp = StringUtil.isNullOrEmpty(msg)?ResCoder.DATA_EXIST.getDefaultMsg(): msg;
		return new CommonRes<T>(coder.getId(),temp,data);
	}*/

	public static <T> CommonRes<T> unAuth(){
		return new CommonRes<T>(ResCoder.UN_AUTH,null);
	}
	public static <T> CommonRes<T> unLogin(){
		return new CommonRes<T>(ResCoder.UN_LOGIN,null);
	}
	/*public static <T> CommonRes<T> exist(String msg){
		String temp = StringUtil.isNullOrEmpty(msg)?ResCoder.DATA_EXIST.getDefaultMsg(): msg;
		return new CommonRes<T>(ResCoder.DATA_EXIST.getId(), temp, null);
	}
	public static <T> CommonRes<T> notExist(String msg){
		String temp = StringUtil.isNullOrEmpty(msg)?ResCoder.DATA_EXIST.getDefaultMsg(): msg;
		return new CommonRes<T>(ResCoder.DATA_NOT_EXIST.getId(), temp, null);
	}*/

	public static <T> CommonRes<T> paramErr(){
		return new CommonRes<T>(ResCoder.PARAM_ERROR, null);
	}

	public static <T> CommonRes<T> systemErr(){
		return new CommonRes<T>(ResCoder.PARAM_ERROR, null);
	}
	public static <T> CommonRes<T> handling(){
		return new CommonRes<T>(ResCoder.HANDLE, null);
	}
	public static <T> CommonRes<T> success(){
		return new CommonRes<T>(ResCoder.SUCCESS, null);
	}

	public static <T> CommonRes<T> success(T data){
		return new CommonRes<T>(ResCoder.SUCCESS, data);
	}

	/**
	 * 判断当前code是否与传参对象的id值,匹配
	 *
	 * @param coder 响应码实例对象
	 * @return
	 */
	public boolean isEquals(Coder coder) {
		return this.code.equalsIgnoreCase(coder.getId());
	}


	/**
	 * 判断当前code是否与传参对象的id值,不匹配
	 *
	 * @param coder 响应码实例对象
	 * @return
	 */
	public boolean isNotEquals(Coder coder) {
		return !isEquals(coder);
	}


	/**
	 * 重写后的equals,可直接对比两个CommonRes的属性是否一样
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		CommonRes<T> pr = null;
		try{
			pr = (CommonRes<T>) o;
		} catch(Exception e) {
		}
		if (pr == null) {
			return false;
		}
		return this.code == pr.code && eq(this.Msg, pr.Msg) && eq(this.data, pr.data);
	}
	private static <T> boolean eq(T o1, T o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
