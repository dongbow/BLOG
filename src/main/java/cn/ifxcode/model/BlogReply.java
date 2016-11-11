package cn.ifxcode.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BlogReply {
    private Integer bg_rid;

    private String content;

    private String isdelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    
    private User user;
    
    private Integer user_id;

    private Integer parent_id;

    private Integer blogtopic_id;
    
    private BlogReply blogReply;
    
    private BlogTopic blogTopic;
    
    private List<BlogReply> blogReplies;

    public Integer getBg_rid() {
        return bg_rid;
    }

    public void setBg_rid(Integer bg_rid) {
        this.bg_rid = bg_rid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BlogReply getBlogReply() {
		return blogReply;
	}

	public void setBlogReply(BlogReply blogReply) {
		this.blogReply = blogReply;
	}

	public List<BlogReply> getBlogReplies() {
		return blogReplies;
	}

	public void setBlogReplies(List<BlogReply> blogReplies) {
		this.blogReplies = blogReplies;
	}

	public BlogTopic getBlogTopic() {
		return blogTopic;
	}

	public void setBlogTopic(BlogTopic blogTopic) {
		this.blogTopic = blogTopic;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getBlogtopic_id() {
		return blogtopic_id;
	}

	public void setBlogtopic_id(Integer blogtopic_id) {
		this.blogtopic_id = blogtopic_id;
	}

}