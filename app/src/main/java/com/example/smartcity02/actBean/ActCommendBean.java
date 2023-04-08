package com.example.smartcity02.actBean;

public class ActCommendBean {

    /**
     * activityId : 1
     * content : 我喜欢这个活动
     */

    private Integer activityId;
    private String content;

    public int getActivityId() {
        return activityId;
    }

    public ActCommendBean(Integer activityId, String content) {
        this.activityId = activityId;
        this.content = content;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
