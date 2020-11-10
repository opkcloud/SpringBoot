package com.opkcloud.util;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils {

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";


    public static Map<String, String> createKeys(int keySize){
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try{
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        }catch(NoSuchAlgorithmException e){
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64String(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) throws IOException {
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        out.close();
        return resultDatas;
    }

    public static void main (String[] args) throws Exception {

//        Map<String, String> keyMap = RSAUtils.createKeys(1024);
//        String  publicKey = keyMap.get("publicKey");
//        String  privateKey = keyMap.get("privateKey");
//        System.out.println("公钥: \n\r" + publicKey);
//        System.out.println();
//        System.out.println("私钥： \n\r" + privateKey);
//        System.out.println();


        //MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnzW+pRXGaeT6qyZnv1EKQUc+Pzkoogh3E+J0BBOEu10GLACREB/JZQaO6CO0vRkhrJ2eNFF4tCBIMfVqI0e2/Ax8Gx0QUwM/k9g8M4HMvMpQiZ+IRBEsaQhcxIjaiW3YQ9a6aeDtpNif8iLvSkIhblBstUysNB4NtN2qJWJ6J9wIDAQAB
        //MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJsa4pt9ejmOsipukYlJ5Hkc1dJil3/ihDB0d0lRotLm4H9Fmg75ttAVohk7tFPQMRZJcXNnqU5/VOUHjtVS8rgk24GZy7MiBhumUkwzDLH3qfPEmm+FRVIMgY5Tjqv6UP5+3PN/xQI/urNyCuXsuZHIiwOb6l55wUmHKpOyj7hJAgMBAAECgYBz2e3+xAdiLHJpfTLZf8bWq7IONbQBQnkIFEXxDrf2nZp7lcpPezeQT0hHXq//llbD1dGWqIvuZO9NLosCTbWaqD8nVl5ltXec/WBQRgBHS1HziTb4jKG/yny4xy03+7kygaIuRr5WDadJgxX7oXrk6GF0wCMfv9W9xHpGzHDpzQJBANyMWPRmmstfdzuD9Jm/xATpIl5QygNBRbuQm1X0BM3NEaWRSd2jtTb9EHp20Lfhl+6fVhj0rPo6ejfvLFpLlXcCQQC0CYWiC0Y0LiBPL7CkxDJpoPA3qX4pWtVFqyGLz4emQcymeuHaa2mOnVTtYhU9mHAmu5sCzQcT1sDdJpUt9ZA/AkBoyzcpSeebYs0gtl5u/7OAMdZmwjR6v3UG3nswqDXwI9SSuypB6huc+TnvNq2N8d9jPsqGKRxX+ZlBIqAMd4WZAkA2eaMbsvmdFE/mZfG3CT6Pw4ir7vcjoD5kBDH0Uekjn0lDHm90XYflBSoLYeA3cQaOUbnHkS4RDKvyvpfo/iKVAkA9DkEp4pYDdQEY7hBE7a2byfPCasK+HoIHgDMiAnlotHXUwzTWCKbzHsxKc5zvnBv0ZaG6tRs/OfkIhwII1xsn

//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnzW+pRXGaeT6qyZnv1EKQUc+Pzkoogh3E+J0BBOEu10GLACREB/" +
//         "JZQaO6CO0vRkhrJ2eNFF4tCBIMfVqI0e2/Ax8Gx0QUwM/k9g8M4HMvMpQiZ+IRBEsaQhcxIjaiW3YQ9a6aeDtpNif8iLvSkIhblBstUysNB4NtN2qJWJ6J9wIDAQAB";
//        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKfNb6lFcZp5PqrJme/UQpBRz4/OSiiCHcT4nQEE4S7XQYsAJEQH8llBo7oI7S9GSGsnZ40UXi0IEgx9WojR7b8DHwbHRBTAz+T2Dwzgcy8ylCJn4hEESxpCFzEiNqJbdhD1rpp4O2k2J/yIu9KQiFuUGy1TKw0Hg203aolYnon3AgMBAAECgYAOWo5gpmYPVqu7pD8cusVu3vCoVncVe/vb64BSo/43KYabw0lW4G8kn27H7ReM4bA0Kky7TrC3nWcd1mM2LO7vMnm1NEpVVOasHkqAagim7GQ7mKkA01NFRv7YoZ2NLlaY5tNYzYImWI7y15vjHg9PtXkfiwKiudTeqXk551kqIQJBAM/EtAdujPrUXq5OvhDgQ/6Wa7HMs5Z/NFyrQ/cSfmfTPuO3ZrzOv7U3I/gNGg/DTxA7sywImGSqj6zQrDUv3SkCQQDOwaIGRW2BTTgOpCVSflNdIOBom42i8lIcZaRD+YtAdIz1jOj8CwlG+l8pQ0hZ70EV9s+5QW3D1gL4qN7Y8vIfAkB6AcPKXXPyU8WpzFm2pUuodAKX7WJEbTx9cqE9tKGALL/Qj0GIfEx9iPzZTDBSs1l5aeVkZUPAUZx/WibWMN5JAkAckeUxuKZJF8KHQ2dk3s/mLoTT76rCuXlfMyxfuDvlzDUuOSp4qY2+oRpFOWbmBomX32MU1lW0mf4PHkas8+VBAkBxjuYTsI6tiqDBO9duTh8Z93KMw4i+0QL2xy5ut71V1y+pGkOOPDeDHbk0iRyWvD/q2h6VhRuCsAkw9X7w8cUe";
//
//        System.out.println("公钥加密——私钥解密");
//        String str = "123456";
//        System.out.println("\r明文：\r\n" + str);
//        System.out.println();
//        System.out.println("\r明文大小：\r\n" + str.getBytes().length);
//        System.out.println();
//        String encodedData = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(publicKey));
//        System.out.println("密文：\r\n" + encodedData);
//        System.out.println();
//        String decodedData = RSAUtils.privateDecrypt(encodedData, RSAUtils.getPrivateKey(privateKey));
//        System.out.println("解密后文字: \r\n" + decodedData);


    }
}
