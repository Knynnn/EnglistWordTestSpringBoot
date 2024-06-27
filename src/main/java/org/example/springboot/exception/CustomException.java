package org.example.springboot.exception;

public class CustomException extends RuntimeException{
    private String msg;

    public CustomException(String msg) {
        this.msg = msg;
    }

    public String getMSg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
