package cn.ifxcode.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookReply {
    private Integer id;

    private String content;

    private Integer replycount;

    private String isdelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    
    private Integer user_id;
    
    private Integer parent_id;

    private User user;
    
    private BookReply parentReply;
    
    private List<BookReply> childBookreplies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getReplycount() {
        return replycount;
    }

    public void setReplycount(Integer replycount) {
        this.replycount = replycount;
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

	public BookReply getParentReply() {
		return parentReply;
	}

	public void setParentReply(BookReply parentReply) {
		this.parentReply = parentReply;
	}

	public List<BookReply> getChildBookreplies() {
		return childBookreplies;
	}

	public void setChildBookreplies(List<BookReply> childBookreplies) {
		this.childBookreplies = childBookreplies;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

}