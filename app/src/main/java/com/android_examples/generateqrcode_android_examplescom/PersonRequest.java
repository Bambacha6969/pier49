package com.android_examples.generateqrcode_android_examplescom;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by oxfam20 on 07/10/2017.
 */
public class PersonRequest {

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

    PersonRequest(String caseNo, String password) {
        this.caseNo = caseNo;
        this.password = password;
    }

}
