package com.example.smartcity02.actBean;

import java.util.List;

public class PinlunListBean {

    /**
     * total : 10
     * rows : [{"searchValue":null,"createBy":null,"createTime":"2021-12-31 14:34:37","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":823,"userId":1113546,"activityId":5,"content":"123","commentTime":"2021-12-31 14:34:37","activityName":"eBay跨境电商开店注册运营，流量转化爆款打造短视频课程","userName":"wangjh","nickName":"likeH","avatar":"../../static/logo.png"},{"searchValue":null,"createBy":null,"createTime":"2021-12-31 14:31:59","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":822,"userId":1113546,"activityId":5,"content":"111","commentTime":"2021-12-31 14:31:59","activityName":"eBay跨境电商开店注册运营，流量转化爆款打造短视频课程","userName":"wangjh","nickName":"likeH","avatar":"../../static/logo.png"},{"searchValue":null,"createBy":null,"createTime":"2021-12-13 20:08:29","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":545,"userId":1112444,"activityId":5,"content":"sadsadsadasd","commentTime":"2021-12-13 20:08:29","activityName":"eBay跨境电商开店注册运营，流量转化爆款打造短视频课程","userName":"lhj","nickName":"qqwhj","avatar":""},{"searchValue":null,"createBy":null,"createTime":"2021-12-01 10:39:54","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":383,"userId":1111412,"activityId":5,"content":"aaa","commentTime":"2021-12-01 10:39:54","activityName":"eBay跨境电商开店注册运营，流量转化爆款打造短视频课程","userName":"object","nickName":"llz","avatar":""},{"searchValue":null,"createBy":null,"createTime":"2021-12-01 10:39:47","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":382,"userId":1111412,"activityId":5,"content":"aaa","commentTime":"2021-12-01 10:39:47","activityName":"eBay跨境电商开店注册运营，流量转化爆款打造短视频课程","userName":"object","nickName":"llz","avatar":""},{"searchValue":null,"createBy":null,"createTime":"2021-11-21 17:01:02","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":304,"userId":1111167,"activityId":5,"content":"111","commentTime":"2021-11-21 17:01:02","activityName":"eBay跨境电商开店注册运营，流量转化爆款打造短视频课程","userName":"lc","nickName":"lc","avatar":"./static/logo.png"},{"searchValue":null,"createBy":null,"createTime":"2021-11-06 12:20:58","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":181,"userId":1111178,"activityId":5,"content":"123","commentTime":"2021-11-06 12:20:58","activityName":"eBay跨境电商开店注册运营，流量转化爆款打造短视频课程","userName":"cici","nickName":"cc","avatar":""},{"searchValue":null,"createBy":null,"createTime":"2021-11-04 16:01:40","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":145,"userId":1111545,"activityId":5,"content":"谢娜绿色","commentTime":"2021-11-04 16:01:40","activityName":"eBay跨境电商开店注册运营，流量转化爆款打造短视频课程","userName":"DaDavid","nickName":"小小伟","avatar":"/profile/2020/10/26/27e7fd58-0972-4dbf-941c-590624e6a886.png"},{"searchValue":null,"createBy":null,"createTime":"2021-11-02 15:17:15","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":105,"userId":1111560,"activityId":5,"content":"1235","commentTime":"2021-11-02 15:17:15","activityName":"eBay跨境电商开店注册运营，流量转化爆款打造短视频课程","userName":"110","nickName":"1231","avatar":"/profile/upload/2021/11/16/9bc634c7-0669-4d64-888c-3b0b311546b4.jpg"},{"searchValue":null,"createBy":null,"createTime":"2021-10-19 15:37:38","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":21,"userId":1111173,"activityId":5,"content":"1","commentTime":"2021-10-19 15:37:38","activityName":"eBay跨境电商开店注册运营，流量转化爆款打造短视频课程","userName":"ak","nickName":"ak","avatar":"/prod-api/profile/upload/2021/12/27/b776dde8-00e5-4d2b-8760-b4657d0e55a2.jpg"}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2021-12-31 14:34:37
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 823
         * userId : 1113546
         * activityId : 5
         * content : 123
         * commentTime : 2021-12-31 14:34:37
         * activityName : eBay跨境电商开店注册运营，流量转化爆款打造短视频课程
         * userName : wangjh
         * nickName : likeH
         * avatar : ../../static/logo.png
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private int userId;
        private int activityId;
        private String content;
        private String commentTime;
        private String activityName;
        private String userName;
        private String nickName;
        private String avatar;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(String commentTime) {
            this.commentTime = commentTime;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public static class ParamsBean {
        }
    }
}
