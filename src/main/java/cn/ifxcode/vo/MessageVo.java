package cn.ifxcode.vo;

import cn.ifxcode.model.Message;

public class MessageVo extends Message {

	private String send_nickname;
	private String receive_nickname;
	
	public String getSend_nickname() {
		return send_nickname;
	}
	public void setSend_nickname(String send_nickname) {
		this.send_nickname = send_nickname;
	}
	public String getReceive_nickname() {
		return receive_nickname;
	}
	public void setReceive_nickname(String receive_nickname) {
		this.receive_nickname = receive_nickname;
	}

}
