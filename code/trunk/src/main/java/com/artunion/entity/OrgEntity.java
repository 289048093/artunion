/**
 * 
 */
package com.artunion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 机构
 * 
 * @author LiZhao
 * 
 */
@Entity
@Table(name = "tb_org")
public class OrgEntity extends UserEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 2811974814366696168L;
    /**
     * 权限号
     */
    @Column(name = "AUTH_NUM_")
    private String authNum;
    /**
     * 机构类型
     */
    @Column(name = "ORG_TYPE_")
    private Byte orgType;

    public Byte getOrgType() {
        return orgType;
    }

    public void setOrgType(Byte orgType) {
        this.orgType = orgType;
    }

    public String getAuthNum() {
        return authNum;
    }

    public void setAuthNum(String authNum) {
        this.authNum = authNum;
    }

}
