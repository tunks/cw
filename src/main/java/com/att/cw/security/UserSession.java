package com.att.cw.security;

import java.io.Serializable;

public class UserSession implements Serializable {
    private static final long serialVersionUID = -8147900899599444974L;

    private String emailID;
    private String token;

    public UserSession(String emailID, String jwt) {
        this.emailID = emailID;
        this.token = jwt;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emailID == null) ? 0 : emailID.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserSession other = (UserSession) obj;
        if (emailID == null) {
            if (other.emailID != null) {
                return false;
            }
        } else if (!emailID.equals(other.emailID)) {
            return false;
        }
        if (token == null) {
            if (other.token != null) {
                return false;
            }
        } else if (!token.equals(other.token)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Session [emailID=" + emailID + ", token=" + token + "]";
    }

}
