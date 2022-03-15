package com.EZALLC.securityapp;

public class Todo {
    private String success;
    private int qid;
    public Todo(String success, int qid) {
        this.success = success;
        this.qid = qid;
    }
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "qid=" + qid +
                ", success=" + success;
    }


}
