package com.example.smartcity02.TOBean;

import java.util.List;

public class TOSellerListBean2 {

    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":null,"createTime":"2021-05-08 15:07:16","updateBy":null,"updateTime":"2021-05-12 12:42:19","remark":null,"params":{},"id":4,"name":"田玉麟黄焖鸡","address":"大连甘井子区凌水镇新新园18号楼8号公建","introduction":"田玉麟黄焖鸡成立于2015年，是大连销量遥遥领先的黄焖鸡连锁品牌","themeId":1,"score":4.7,"saleQuantity":668,"deliveryTime":30,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/6cf580bd-9418-43a7-8fd4-05798463616d.jpg","avgCost":20,"other":null,"recommend":"Y","distance":510,"saleNum3hour":15},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 23:24:46","updateBy":null,"updateTime":"2021-05-12 12:42:35","remark":null,"params":{},"id":10,"name":"大先生小碗菜","address":"辽宁省大连市沙河口区文萃街","introduction":"用心做好饭","themeId":1,"score":4.8,"saleQuantity":2807,"deliveryTime":30,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/f6b0c53a-a481-4c8a-8775-dd3c1f2287e7.jpg","avgCost":20,"other":null,"recommend":"N","distance":1400,"saleNum3hour":46},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 23:45:52","updateBy":null,"updateTime":"2021-05-09 02:29:54","remark":null,"params":{},"id":11,"name":"鹿岛の日式咖喱蛋包饭","address":"辽宁省大连市甘井子区新新园114号","introduction":"大连蛋包饭第一名","themeId":1,"score":5,"saleQuantity":1887,"deliveryTime":32,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/081fc1fb-8797-44c0-87e9-815398f0b62c.jpg","avgCost":20,"other":null,"recommend":"N","distance":2000,"saleNum3hour":28}]
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
         * createTime : 2021-05-08 15:07:16
         * updateBy : null
         * updateTime : 2021-05-12 12:42:19
         * remark : null
         * params : {}
         * id : 4
         * name : 田玉麟黄焖鸡
         * address : 大连甘井子区凌水镇新新园18号楼8号公建
         * introduction : 田玉麟黄焖鸡成立于2015年，是大连销量遥遥领先的黄焖鸡连锁品牌
         * themeId : 1
         * score : 4.7
         * saleQuantity : 668
         * deliveryTime : 30
         * imgUrl : /prod-api/profile/upload/image/2021/05/08/6cf580bd-9418-43a7-8fd4-05798463616d.jpg
         * avgCost : 20.0
         * other : null
         * recommend : Y
         * distance : 510.0
         * saleNum3hour : 15
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String name;
        private String address;
        private String introduction;
        private int themeId;
        private double score;
        private int saleQuantity;
        private int deliveryTime;
        private String imgUrl;
        private double avgCost;
        private Object other;
        private String recommend;
        private double distance;
        private int saleNum3hour;

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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public int getThemeId() {
            return themeId;
        }

        public void setThemeId(int themeId) {
            this.themeId = themeId;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(int saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public int getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(int deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public double getAvgCost() {
            return avgCost;
        }

        public void setAvgCost(double avgCost) {
            this.avgCost = avgCost;
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

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getSaleNum3hour() {
            return saleNum3hour;
        }

        public void setSaleNum3hour(int saleNum3hour) {
            this.saleNum3hour = saleNum3hour;
        }

        public static class ParamsBean {
        }
    }
}
