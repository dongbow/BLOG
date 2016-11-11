package cn.ifxcode.lucene.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.utils.HtmlToStringUtil;

@SuppressWarnings("deprecation")
public class BlogTopicDocument {

	public static Document oneBlogTopicToDocument(BlogTopic blogTopic){
		Document doc = new Document();
		//int bid = NumericUtils.intToPrefixCoded(blogTopic.getBid(), 0, null);
		doc.add(new Field("bid", Integer.toString(blogTopic.getBid()), Store.YES, Index.NOT_ANALYZED));
		doc.add(new Field("title", blogTopic.getTitle(), Store.YES, Index.ANALYZED));
		doc.add(new Field("content", HtmlToStringUtil.forIndex(blogTopic.getContent()), Store.YES, Index.ANALYZED));
		return doc;
	}
	
	public static List<Document> manyBlogTopicToDocument(List<BlogTopic> blogTopics){
		List<Document> document = new ArrayList<Document>();
		for (BlogTopic blogTopic : blogTopics) {
			Document doc = new Document();
			//int bid = NumericUtils.intToPrefixCoded(blogTopic.getBid(), 0, null);
			doc.add(new Field("bid", Integer.toString(blogTopic.getBid()), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("title", blogTopic.getTitle(), Store.YES, Index.ANALYZED));
			doc.add(new Field("content", HtmlToStringUtil.forIndex(blogTopic.getContent()), Store.YES, Index.ANALYZED));
			document.add(doc);
		}
		return document;
	}
	
	public static BlogTopic documentToBlogTopic(Document document, String content){
		BlogTopic blogTopic = new BlogTopic();
		blogTopic.setBid(Integer.parseInt(document.get("bid")));
		blogTopic.setTitle(document.get("title"));
		blogTopic.setContent(content);
		return blogTopic;
	}
	
}
