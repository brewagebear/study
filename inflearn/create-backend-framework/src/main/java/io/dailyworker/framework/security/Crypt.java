package io.dailyworker.framework.security;

public interface Crypt {
    public byte[] encrypt(byte[] plainByte) throws Exception;
    public byte[] decrypt(byte[] encryptedByte) throws Exception;
}
