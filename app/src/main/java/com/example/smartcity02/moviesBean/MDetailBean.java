package com.example.smartcity02.moviesBean;

public class MDetailBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":14,"name":"最可爱的人","category":"7","cover":"/prod-api/profile/upload/image/2021/05/08/6d0a97ab-d902-40ce-8b54-df4c969c3137.jpg","playType":"2","score":5,"duration":84,"playDate":"2021-05-01","likeNum":69792,"favoriteNum":97849,"language":"中文","introduction":"<p><span style=\"color: rgb(51, 51, 51);\">1950 年，朝鲜战争爆发，战火烧到鸭绿江两岸，严重威胁到了新中国的安全。无数优秀青年，为保卫来之不易的幸福家园、保卫新中国，参加了中国人民志愿军。杨根思、邱少云、黄继光、王海、张桃芳...... 他们是普通的志愿军战士，在那场保家卫国的战争中，他们无畏强敌、英勇战斗，谱写了壮丽的生命之歌，他们是最可爱的人！<\/span><\/p>","other":null,"recommend":"Y"}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 14
         * name : 最可爱的人
         * category : 7
         * cover : /prod-api/profile/upload/image/2021/05/08/6d0a97ab-d902-40ce-8b54-df4c969c3137.jpg
         * playType : 2
         * score : 5
         * duration : 84
         * playDate : 2021-05-01
         * likeNum : 69792
         * favoriteNum : 97849
         * language : 中文
         * introduction : <p><span style="color: rgb(51, 51, 51);">1950 年，朝鲜战争爆发，战火烧到鸭绿江两岸，严重威胁到了新中国的安全。无数优秀青年，为保卫来之不易的幸福家园、保卫新中国，参加了中国人民志愿军。杨根思、邱少云、黄继光、王海、张桃芳...... 他们是普通的志愿军战士，在那场保家卫国的战争中，他们无畏强敌、英勇战斗，谱写了壮丽的生命之歌，他们是最可爱的人！</span></p>
         * other : null
         * recommend : Y
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String name;
        private String category;
        private String cover;
        private String playType;
        private int score;
        private int duration;
        private String playDate;
        private int likeNum;
        private int favoriteNum;
        private String language;
        private String introduction;
        private Object other;
        private String recommend;

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

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getPlayType() {
            return playType;
        }

        public void setPlayType(String playType) {
            this.playType = playType;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getPlayDate() {
            return playDate;
        }

        public void setPlayDate(String playDate) {
            this.playDate = playDate;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getFavoriteNum() {
            return favoriteNum;
        }

        public void setFavoriteNum(int favoriteNum) {
            this.favoriteNum = favoriteNum;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public Object getOther() {
            return other;
        }

        public void setOther(Object other) {
            this.other = other;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public static class ParamsBean {
        }
    }
}
