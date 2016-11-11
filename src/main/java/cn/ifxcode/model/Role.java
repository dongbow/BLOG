package cn.ifxcode.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Role {
    private Integer rid;

    private String name;

    private String roledesc;
    
    private String role_status;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date role_createtime;
    
    private List<User> user;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
    }

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public String getRole_status() {
		return role_status;
	}

	public void setRole_status(String role_status) {
		this.role_status = role_status;
	}

	public Date getRole_createtime() {
		return role_createtime;
	}

	public void setRole_createtime(Date role_createtime) {
		this.role_createtime = role_createtime;
	}

}