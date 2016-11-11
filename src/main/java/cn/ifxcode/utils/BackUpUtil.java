package cn.ifxcode.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;
import java.util.UUID;

import org.apache.log4j.Logger;

/**
 * MySQL数据库的备份与恢复 缺陷：可能会被杀毒软件拦截
 * 
 * @author xxx
 * @version xxx
 */
public class BackUpUtil {
	
	private static Logger logger = Logger.getLogger(BackUpUtil.class);
	
	/** MySQL安装目录的Bin目录的绝对路径 */
	private static String mysqlBinPath = binPath();
	/** 访问MySQL数据库的ip */
	private static String host = hostIP();
	/** 访问MySQL数据库的端口 */
	private static String port = port();
	/** 访问MySQL数据库的用户名 */
	private static String username = username();
	/** 访问MySQL数据库的密码 */
	private static String password = password();
	/**  要备份的路径  */
	private static String backupDir = backupDir();
	/**  要备份的数据库名 */
	private static String dbName = dbName();
	
	/**
	 * 备份数据库
	 * 
	 * @param output 输出流
	 */
	public static boolean backup(OutputStream output) {
		StringBuilder command = new StringBuilder();
		command.append(mysqlBinPath).append("mysqldump -h ").append(host).append(" -P ")
				.append(port).append(" -u").append(username).append(" -p")
				.append(password).append(" --default-character-set=utf8 ").append(dbName);
		PrintWriter p = null;
		BufferedReader reader = null;
		try {
			p = new PrintWriter(new OutputStreamWriter(output, "utf8"));
			Process process = Runtime.getRuntime().exec(command.toString());
			InputStreamReader inputStreamReader = new InputStreamReader(
					process.getInputStream(), "utf8");
			reader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				p.println(line);
			}
			p.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (p != null) {
					p.close();
				}
				logger.info("备份完成");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("备份出错",e);
			}
		}
		return false;
	}
	
	/**
	 * 备份数据库，如果指定路径的文件不存在会自动生成
	 */
	@SuppressWarnings("resource")
	public static String backupDB() {
		try {
			File file = new File(backupDir + DateUtils.getCurrentDate());
			if (!file.exists() && !file.isDirectory()) {       
		        logger.info("//目录不存在，正在创建");  
		        file.mkdirs();    
		    } else {  
		    	logger.info("//目录存在");  
		    }
			String backname = backupDir + DateUtils.getCurrentDate() 
					+ "\\" + UUID.randomUUID() + ".sql";
			OutputStream out = new FileOutputStream(backname);
			if(backup(out)){
				return backname;
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 恢复数据库
	 * 
	 * @param input  输入流
	 * 
	 */
	public static boolean restore(InputStream input) {
		StringBuilder command = new StringBuilder();
		command.append(mysqlBinPath).append("mysql -h ").append(host).append(" -P ")
				.append(port).append(" -u").append(username).append(" -p")
				.append(password).append(" ").append(dbName);
		try {
			Process process = Runtime.getRuntime().exec(command.toString());
			OutputStream out = process.getOutputStream();
			String line = null;
			String outStr = null;
			StringBuffer sb = new StringBuffer("");
			BufferedReader br = new BufferedReader(new InputStreamReader(input,
					"utf8"));
			while ((line = br.readLine()) != null) {
				sb.append(line + "/r/n");
			}
			outStr = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
			writer.write(outStr);
			writer.flush();
			out.close();
			br.close();
			writer.close();
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 恢复数据库
	 * 
	 * @param dest 备份文件的路径
	 * @return 
	 */
	public static boolean restore(String dest) {
		try {
			InputStream input = new FileInputStream(dest);
			return restore(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ResourceBundle rb(){
		ResourceBundle rb=ResourceBundle.getBundle("backup");
		return rb;
	}
	
	public static String binPath(){
		return rb().getString("mysqlBinPath");
	}
	
	private static String hostIP() {
		return rb().getString("host");
	}
	
	private static String port() {
		return rb().getString("port");
	}
	
	public static String username(){
		return rb().getString("username");
	}
	
	public static String password(){
		return rb().getString("password");
	}
	
	private static String backupDir() {
		return rb().getString("backupDir");
	}
	
	private static String dbName() {
		return rb().getString("dbName");
	}
	
	public static void main(String[] args) {
		//System.out.println(backupDB());
		System.out.println(restore("F:\\backup\\ifxcode\\20160503\\27878abc-ec4f-41f5-86e2-83b0685d2191.sql"));
	}
}