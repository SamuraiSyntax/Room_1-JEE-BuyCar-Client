package org.eclipse.dto;

public class ErrorDto {
	private int code;
	private String message;
	private String path;
	public ErrorDto() {
	}
	public ErrorDto(int code, String message, String pathString) {
		this.code = code;
		this.message = message;
		this.path = pathString;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String pathString) {
		this.path = pathString;
	}

}
