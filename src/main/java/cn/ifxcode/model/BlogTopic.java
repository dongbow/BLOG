package cn.ifxcode.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BlogTopic {
    private Integer bid;

    private String title;
    
    private String description;

    private Integer viewcount;

    private Integer replycount;

    private Integer praisecount;

    private Integer notpraisecount;

    private String ishome;

    private String isdelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private String createOrRepost;
    
    private Integer classify_id;

    private String content;
    
    private BlogClassify blogClassify;
    
    private List<BlogSign> blogSigns;
    
    private List<BlogReply> blogReplies;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    public Integer getReplycount() {
        return replycount;
    }

    public void setReplycount(Integer replycount) {
        this.replycount = replycount;
    }

    public Integer getPraisecount() {
        return praisecount;
    }

    public void setPraisecount(Integer praisecount) {
        this.praisecount = praisecount;
    }

    public Integer getNotpraisecount() {
        return notpraisecount;
    }

    public void setNotpraisecount(Integer notpraisecount) {
        this.notpraisecount = notpraisecount;
    }

    public String getIshome() {
        return ishome;
    }

    public void setIshome(String ishome) {
        this.ishome = ishome;
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public BlogClassify getBlogClassify() {
		return blogClassify;
	}

	public void setBlogClassify(BlogClassify blogClassify) {
		this.blogClassify = blogClassify;
	}

	public List<BlogSign> getBlogSigns() {
		return blogSigns;
	}

	public void setBlogSigns(List<BlogSign> blogSigns) {
		this.blogSigns = blogSigns;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateOrRepost() {
		return createOrRepost;
	}

	public void setCreateOrRepost(String createOrRepost) {
		this.createOrRepost = createOrRepost;
	}

	public List<BlogReply> getBlogReplies() {
		return blogReplies;
	}

	public void setBlogReplies(List<BlogReply> blogReplies) {
		this.blogReplies = blogReplies;
	}

	public Integer getClassify_id() {
		return classify_id;
	}

	public void setClassify_id(Integer classify_id) {
		this.classify_id = classify_id;
	}
}