package com.android_examples.generateqrcode_android_examplescom;

/**
 * Created by oxfam20 on 07/10/2017.
 */
public class GetPersonNonceResponse {

    /**
     * Cryptographic secret value which identifies a person
     */
    public String nonce;

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}
