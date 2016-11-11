package cn.ifxcode.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BlogSign {
    private Integer bsid;

    private String bsname;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    
    private Integer blog_id;
    
    private BlogTopic blogTopic;


    public Integer getBsid() {
        return bsid;
    }

    public void setBsid(Integer bsid) {
        this.bsid = bsid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public BlogTopic getBlogTopic() {
		return blogTopic;
	}

	public void setBlogTopic(BlogTopic blogTopic) {
		this.blogTopic = blogTopic;
	}

	public String getBsname() {
		return bsname;
	}

	public void setBsname(String bsname) {
		this.bsname = bsname;
	}

	public Integer getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Integer blog_id) {
		this.blog_id = blog_id;
	}

}