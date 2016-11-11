package cn.ifxcode.lucene.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;

import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.result.Result;

public class LuceneIndexUtil {

	private static int fragmentSize = 255;

	public static Result initIndex(List<BlogTopic> blogTopics) {
		Result result = new Result();
		List<Document> document = BlogTopicDocument.manyBlogTopicToDocument(blogTopics);
		try{
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, Configuration.getAnalyzer());
			IndexWriter writer = new IndexWriter(Configuration.getDirectory(), config);
			writer.addDocuments(document);
			writer.close();
			result.setMsg("初始化创建索引成功");
		} catch(Exception e) {
			e.printStackTrace();
			result.setMsg("初始化创建索引失败");
		}
		return result;
	}
	
	public static List<BlogTopic> searchIndex(String keyword) {
		List<BlogTopic> blogTopics = new ArrayList<BlogTopic>();
		try{
			DirectoryReader reader = DirectoryReader.open(Configuration.getDirectory());
			IndexSearcher indexSearcher = new IndexSearcher(reader);

			Query query = null;
			if(StringUtils.isNotBlank(keyword)){
				QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_44, 
						new String[]{"title","content"}, Configuration.getAnalyzer());
				query = queryParser.parse(keyword);
			}
			
			TopDocs topDocs = indexSearcher.search(query, 100);
			//表示返回的结果集
			ScoreDoc [] scoreDocs = topDocs.scoreDocs;

			//html页面高亮显示的格式化，默认是<b></b>
			Formatter formatter = new SimpleHTMLFormatter("<font color='red'><b>","</b></font>");
			//执行查询条件，因为高亮的值就是查询条件
			Scorer scorer = new QueryScorer(query);
			Highlighter highlighter = new Highlighter(formatter,scorer);
			//设置文字摘要大小
			highlighter.setTextFragmenter(new SimpleFragmenter(fragmentSize));
			
			if(scoreDocs != null && scoreDocs.length > 0){
				for (ScoreDoc scoreDoc : scoreDocs) {
					System.out.println("相关度得分："+scoreDoc.score);//默认得分高的数据在前面
					//获取查询结果的文档的惟一编号，使用编号，获取对应的数据
					Document document = indexSearcher.doc(scoreDoc.doc);
					
					//获取文字的高亮，一次只能获取一个字段高亮的结果，如果获取不到，返回null值
					//索引库字段title 取完整值
					//索引库字段content
					String content = highlighter.getBestFragment(Configuration.getAnalyzer(), "content", document.get("content"));
//					if(StringUtils.isBlank(content)){
//						content = document.get("content");
//						if(content != null){
//							fragmentSize = content.length() > 255 ? 255 : content.length();
//							content = content.substring(0,fragmentSize);
//						}
//					}
					//将高亮后的结果放置到document中去
					//document.getField("content").setValue(content);

					BlogTopic blogTopic = BlogTopicDocument.documentToBlogTopic(document,content);
					blogTopics.add(blogTopic);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return blogTopics;
	}
	
	public static void addIndex(BlogTopic blogTopic) {
		Document document = BlogTopicDocument.oneBlogTopicToDocument(blogTopic);
		try{
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, Configuration.getAnalyzer());
			IndexWriter writer = new IndexWriter(Configuration.getDirectory(), config);
			writer.addDocument(document);
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
