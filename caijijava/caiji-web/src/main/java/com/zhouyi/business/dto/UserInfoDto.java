package com.zhouyi.business.dto;

/**
 * @author 李秸康
 * @ClassNmae: UserInfoDto
 * @Description: 用户信息查询Dto
 * @date 2019/7/8 8:21
 * @Version 1.0
 **/
public class UserInfoDto {

    private String userCode;
    private String userName;
    private String userIdcardno;
    private String userPoliceno;
    private String userUnitCode;
    private String userUnitName;
    private String userPhone;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIdcardno() {
        return userIdcardno;
    }

    public void setUserIdcardno(String userIdcardno) {
        this.userIdcardno = userIdcardno;
    }

    public String getUserPoliceno() {
        return userPoliceno;
    }

    public void setUserPoliceno(String userPoliceno) {
        this.userPoliceno = userPoliceno;
    }

    public String getUserUnitCode() {
        return userUnitCode;
    }

    public void setUserUnitCode(String userUnitCode) {
        this.userUnitCode = userUnitCode;
    }

    public String getUserUnitName() {
        return userUnitName;
    }

    public void setUserUnitName(String userUnitName) {
        this.userUnitName = userUnitName;
    }
}
