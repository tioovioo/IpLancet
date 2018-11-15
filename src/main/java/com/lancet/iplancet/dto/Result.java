package com.lancet.iplancet.dto;

/**
 * @author ZhaoShihao
 * @version 1.0
 * @create 2017-06-01 11:45
 */

public class Result {
    // -2 鉴权异常 -1 系统异常 0 业务失败 1成功 2其他
    private Integer state;
    private Object data;

    public Result() {
    }

//    public Result(JSONObject object) {
//        this.state = object.getInteger("state");
//        this.data = object.get("data");
//    }

    public Result(Integer state) {
        this.state = state;
    }

    public Result(Integer state, Object data) {
        this.state = state;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "state=" + state +
                ", data=" + data +
                '}';
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
