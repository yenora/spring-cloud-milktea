package com.jandar.generator;

public class Config implements Data {
	
	private String doname;
	
	private String doch;
	
	private String packagepath;
	
	private String dist;
	
	private String clent;
	
	private Config(){}
	
	private Config(String doname, String doch, String packagepath, String dist) {
		this.doname = doname;
		this.doch = doch;
		this.packagepath = packagepath;
		this.dist = dist;
	}
	
	private Config(String doname, String doch, String packagepath, String dist, String client) {
		this.doname = doname;
		this.doch = doch;
		this.packagepath = packagepath;
		this.dist = dist;
		this.clent = client;
	}

	public static Config build(String doname, String doch, String packagepath, String dist){
		return new Config(doname, doch, packagepath, dist);
	}
	
	public static Config build(String doname, String doch, String packagepath, String dist, String client){
		return new Config(doname, doch, packagepath, dist, client);
	}
	
	public static void main(String[] args) {
		Config c = new Config();
		c.doname = "com.jandar.ipcp.temple.pojo.AccountFeeDO";
		System.out.println(c.getDOBasePackageName());
		System.out.println(c.getDOPackageName());
		System.out.println(c.getDOName());
		System.out.println(c.getDOSimpleName());
		System.out.println(c.getDOUrl());
		System.out.println(c.getPackage());
		System.out.println(c.getDOVarible());
	}

	@Override
	public String getDOBasePackageName() {
		return doname.substring(0, doname.lastIndexOf(".") - 5);
	}

	@Override
	public String getDOPackageName() {
		return doname;
	}

	@Override
	public String getDOName() {
		return doname.substring(doname.lastIndexOf(".") + 1);
	}

	@Override
	public String getDOSimpleName() {
		return getDOName().substring(0, getDOName().length() - 2);
	}

	@Override
	public String getDOUrl() {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < getDOSimpleName().length(); i++) {
			char c = getDOSimpleName().charAt(i); 
			if(!Character.isLowerCase(c)){
				sb.append("/");
			}
			sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}

	@Override
	public String getDOCH() {
		return doch;
	}

	@Override
	public String getPackage() {
		return packagepath;
	}

	@Override
	public String getDist() {
		if(dist.endsWith("/"))
			return dist.substring(0, dist.length() - 1);
		return new String(dist);
	}

	@Override
	public String getDOVarible() {
		return Character.toLowerCase(getDOSimpleName().charAt(0)) + getDOSimpleName().substring(1);
	}

	@Override
	public String getFeignClient() {
		return clent;
	}

}
