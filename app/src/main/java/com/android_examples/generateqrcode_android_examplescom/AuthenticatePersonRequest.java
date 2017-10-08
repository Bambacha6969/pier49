package com.android_examples.generateqrcode_android_examplescom;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Used to authenticate a person against the API
 */
public class AuthenticatePersonRequest {

    @JsonProperty("case_no")
    String caseNo;

    String password;

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    AuthenticatePersonRequest(String caseNo, String password) {
        this.caseNo = caseNo;
        this.password = password;
    }

}
