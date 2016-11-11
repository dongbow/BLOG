package cn.ifxcode.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Resource {
    private Integer resid;

    private String resname;

    private String resurl;

    private String resattr;

    private String ressign;

    private String resico;

    private String resdesc;

    private String resstatus;

    private Integer pid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rescreatetime;

    public Integer getResid() {
        return resid;
    }

    public void setResid(Integer resid) {
        this.resid = resid;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname == null ? null : resname.trim();
    }

    public String getResurl() {
        return resurl;
    }

    public void setResurl(String resurl) {
        this.resurl = resurl == null ? null : resurl.trim();
    }

    public String getResattr() {
        return resattr;
    }

    public void setResattr(String resattr) {
        this.resattr = resattr == null ? null : resattr.trim();
    }

    public String getRessign() {
        return ressign;
    }

    public void setRessign(String ressign) {
        this.ressign = ressign == null ? null : ressign.trim();
    }

    public String getResico() {
        return resico;
    }

    public void setResico(String resico) {
        this.resico = resico == null ? null : resico.trim();
    }

    public String getResdesc() {
        return resdesc;
    }

    public void setResdesc(String resdesc) {
        this.resdesc = resdesc == null ? null : resdesc.trim();
    }

    public String getResstatus() {
        return resstatus;
    }

    public void setResstatus(String resstatus) {
        this.resstatus = resstatus == null ? null : resstatus.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getRescreatetime() {
        return rescreatetime;
    }

    public void setRescreatetime(Date rescreatetime) {
        this.rescreatetime = rescreatetime;
    }
}