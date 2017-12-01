package com.lawyer.manage.exception;

/**
 * Created by wangqiang on 2017/6/6.
 */
public enum ErrorCode {

    NULL_OBJ("1001", "对象为空"),
    EXIST_DB("1002", "数据库记录已经存在"),
    FILE_ERROR("1003", "文件转换错误"),
    DEL_FILE_ERROR("1004", "删除文件失败"),
    UNKNOWN_ERROR("9999", "系统繁忙，请稍后再试....");


    private String code;
    private String desc;

    private ErrorCode(String code, String desc) {
        this.setCode(code);
        this.setDesc(desc);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "[" + this.code + "]" + this.desc;
    }


}
