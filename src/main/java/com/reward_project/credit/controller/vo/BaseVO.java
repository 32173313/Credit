package com.reward_project.credit.controller.vo;

public class BaseVO {
    private int code;

    private long time;

    private boolean success;

    private String errorMsg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static BaseVO buildBaseVo(int code, long time, boolean success, String errorMsg) {
        BaseVO baseVO = new BaseVO();
        baseVO.setCode(code);
        baseVO.setTime(time);
        baseVO.setSuccess(success);
        baseVO.setErrorMsg(errorMsg);
        return baseVO;
    }

    public BaseVO(int code, long time, boolean success, String errorMsg) {
        this.code = code;
        this.time = time;
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public BaseVO() {
    }

    @Override
    public String toString() {
        return "BaseVO{" +
                "code=" + code +
                ", time=" + time +
                ", success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
