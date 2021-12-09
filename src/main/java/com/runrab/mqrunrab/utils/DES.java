package com.runrab.mqrunrab.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**加密工具
 * @author o
 */
public class DES {
    private static final String key = "5211314DES";
    private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
    public static String encryptDES(String encryptString, String encryptKey)
            throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return Base64.encode(encryptedData);
    }
    //解密
    public static byte[] decrypt(byte[] arrB, String encryptKey) throws Exception {
    	IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
    	Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    	cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		return cipher.doFinal(arrB);
	}

    /**
	 * 解密字符串
	 * @param strIn 需解密的字符串
	 * @return 解密后的字符串,异常返回空
	 */
	public synchronized static String decryptMy(String strIn, String encryptKey){
		try {
			return new String(decrypt(strIn.getBytes(), encryptKey));
		} catch (Exception ex) {
			return null;
		}
	}

	public static String decryptDES(String decryptString, String decryptKey)
            throws Exception {
        byte[] byteMi = Base64.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData);
    }
	//解密用例
    public static void main(String[] args) throws Exception {
        //加密参数为:  phone=
        //解密
        String planText = "nUonxlxXm66vhd2CVHbmoSVrJZO01mNz";
        String decryptDES = decryptDES(planText, key);
        System.out.println(decryptDES);
    }
}