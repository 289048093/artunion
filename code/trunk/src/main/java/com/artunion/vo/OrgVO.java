/**
 * 
 */
package com.artunion.vo;

/**
 * 机构
 * 
 * @author LiZhao
 * 
 */
public class OrgVO extends UserVO {

    /**
     * 
     */
    private static final long serialVersionUID = -901212537209979896L;

    /**
     * 权限号
     */
    private String authNum;
    /**
     * 机构类型（0：画室，1：文化机构）
     */
    private Byte orgCat;

    public Byte getOrgCat() {
        return orgCat;
    }

    public void setOrgCat(Byte orgCat) {
        this.orgCat = orgCat;
    }

    public String getAuthNum() {
        return authNum;
    }

    public void setAuthNum(String authNum) {
        this.authNum = authNum;
    }

}
