package org.mddarr.dakobedreports.auth.models;

import java.util.Date;

/**
 * Created by fan.jin on 2016-10-17.
 */
public class UserTokenState {
    private String token;
    private Date expires;
    public String name;
    public UserTokenState() {
        this.token = null;
        this.expires = null;
        this.name = null;
    }
    public UserTokenState(String token, Date expires, String name) {
        this.token = token;
        this.expires = expires;
        this.name = name;
    }
    public String getName() {return name; }
    public void setName(String name) {
        this.name = name;
    }
    public String gettoken() {
        return token;
    }
    public void settoken(String token) {
        this.token = token;
    }
    public Date getExpires() {
        return expires;
    }
    public void setExpires(Date expires_in) {
        this.expires = expires_in;
    }
}