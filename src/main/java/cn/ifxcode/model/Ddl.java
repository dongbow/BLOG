package cn.ifxcode.model;

public class Ddl {
    private Integer id;

    private String value;
    
    private int sign;

    private Dd dd;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

	public Dd getDd() {
		return dd;
	}

	public void setDd(Dd dd) {
		this.dd = dd;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

}