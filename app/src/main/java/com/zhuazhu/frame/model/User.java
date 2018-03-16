package com.zhuazhu.frame.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 创建时间:2018-01-25 18:29<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2018-01-25 18:29<br/>
 * 描述:
 */

@Data
@Accessors(chain = true,fluent = true)
public class User {

    public String username;
    private String pwd;


//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPwd() {
//        return pwd;
//    }
//
//    public void setPwd(String pwd) {
//        this.pwd = pwd;
//    }
}
