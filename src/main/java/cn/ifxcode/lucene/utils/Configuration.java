package cn.ifxcode.lucene.utils;

import java.io.File;
import java.util.ResourceBundle;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Configuration {

	//索引库的目录位置
	private static Directory directory;
	//分词器
	private static Analyzer analyzer;
	
	public static ResourceBundle rb(){
		ResourceBundle rb=ResourceBundle.getBundle("lucene");
		return rb;
	}
	
	public static String getLuceneIndexDir() {
		return rb().getString("lucene.indexDir");
	}
	
	static{
		try {
			/**索引库目录为F:/lucene/indexDir*/
			directory = FSDirectory.open(new File(getLuceneIndexDir()));
			/**词库分词*/
			analyzer = new IKAnalyzer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Directory getDirectory() {
		return directory;
	}
	public static Analyzer getAnalyzer() {
		return analyzer;
	}
}
