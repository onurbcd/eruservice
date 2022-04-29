package com.onurbcd.eruservice.service.helper;

public interface Cryptoable {

    String encrypt(String plainText);

    String decrypt(String encodedCipherText);
}
