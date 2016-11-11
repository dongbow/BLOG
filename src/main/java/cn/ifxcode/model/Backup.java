package cn.ifxcode.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Backup {
    private Integer backid;

    private String backname;

    private String backdir;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    public Integer getBackid() {
        return backid;
    }

    public void setBackid(Integer backid) {
        this.backid = backid;
    }

    public String getBackname() {
        return backname;
    }

    public void setBackname(String backname) {
        this.backname = backname == null ? null : backname.trim();
    }

    public String getBackdir() {
        return backdir;
    }

    public void setBackdir(String backdir) {
        this.backdir = backdir == null ? null : backdir.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}