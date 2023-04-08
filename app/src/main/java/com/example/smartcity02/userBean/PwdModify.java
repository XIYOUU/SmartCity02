package com.example.smartcity02.userBean;

public class PwdModify {
    private String newPassword;
    private String oldPassword;

    public PwdModify(String newPassword, String oldPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public PwdModify() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public String toString() {
        return "PwdModify{" +
                "newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }
}
