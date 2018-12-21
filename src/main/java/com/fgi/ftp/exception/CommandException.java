package com.fgi.ftp.exception;

/**
 * @author 黄世增
 */

public class CommandException extends Exception {

    private final int code;
    private final String text;

    public CommandException(int code, String text) {
        super(code + " " + text);
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    @Override
    public String getMessage() {
        return code + " " + text;
    }
}
