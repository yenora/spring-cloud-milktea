package com.jandar.generator;

public interface Data {
	
	/**
	 * @return com.jandar.ipcp.temple
	 */
	String getDOBasePackageName();
	/**
	 * @return accountFee
	 */
	String getDOVarible();
	/**
	 * @return com.jandar.ipcp.temple.pojo.AccountFeeDO
	 */
	String getDOPackageName();
	/**
	 * 
	 * @return AccountFeeDO
	 */
	String getDOName();
	/**
	 * 
	 * @return AccountFee
	 */
	String getDOSimpleName();
	/**
	 * 
	 * @return account/fee
	 */
	String getDOUrl();
	/**
	 * 
	 * @return 账户金额
	 */
	String getDOCH();
	/**
	 * 
	 * @return com.example.iccp.milktea.service
	 */
	String getPackage();
	/**
	 * 
	 * @return C:/document/FTP/iccp-milktea-service
	 */
	String getDist();
	/**
	 * 
	 * @return ICCP-REST
	 */
	String getFeignClient();
}
