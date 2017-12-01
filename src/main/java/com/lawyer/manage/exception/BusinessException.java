package com.lawyer.manage.exception;

/**
 * Created by wangqiang on 2017/6/6.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;
    private String desc;

    public BusinessException(Object Obj) {
        super(Obj.toString());
        if (Obj instanceof ErrorCode) {
            this.code = ((ErrorCode) Obj).getCode();
            this.desc = ((ErrorCode) Obj).getDesc();
        }
    }


    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
