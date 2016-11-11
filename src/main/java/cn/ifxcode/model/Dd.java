package cn.ifxcode.model;

import java.util.List;

public class Dd {
    private Integer id;

    private String name;

    private String ddesc;
    
    private List<Ddl> ddls;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDdesc() {
        return ddesc;
    }

    public void setDdesc(String ddesc) {
        this.ddesc = ddesc == null ? null : ddesc.trim();
    }

	public List<Ddl> getDdls() {
		return ddls;
	}

	public void setDdls(List<Ddl> ddls) {
		this.ddls = ddls;
	}
}