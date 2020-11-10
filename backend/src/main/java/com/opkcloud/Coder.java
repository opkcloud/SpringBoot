package com.opkcloud;


/**
 * 响应码对象
 *
 * @author zhousir
 * @since 1.0.0-RELEASE
 *
 */

public class Coder {
	private String id;
	private String defaultMsg;
	private String enMsg;
	private String zhMsg;
	private String twMsg;

	public Coder(String id, String defaultMsg) {
		this.id = id;
		this.defaultMsg = defaultMsg;
	}

	public Coder(String id, String defaultMsg, String enMsg, String zhMsg, String twMsg) {
		this.id = id;
		this.defaultMsg = defaultMsg;
		this.enMsg = enMsg;
		this.zhMsg = zhMsg;
		this.twMsg = twMsg;
	}

	/**
	 * 获取响应码的ID
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 获取响应码的默认描述
	 * @return
	 */
	public String getDefaultMsg() {
		return defaultMsg;
	}

	/**
	 * 获取响应码的英文描述
	 * @return
	 */
	public String getEnMsg() {
		return enMsg;
	}

	/**
	 * 获取响应码的中文描述
	 * @return
	 */
	public String getZhMsg() {
		return zhMsg;
	}

	/**
	 * 获取响应码的繁体字描述
	 * @return
	 */
	public String getTwMsg() {
		return twMsg;
	}
}
