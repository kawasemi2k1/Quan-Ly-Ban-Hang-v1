/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author ADMIN
 */
public class ValidateData {
    public boolean kiemTraEmail(String email) {
        String dinhDangEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean ktEmail = email.matches(dinhDangEmail);
        return ktEmail;
        // String dinhDangEmail nó giống như là format chuẩn cho email mình nhập vào, nếu sai cái
        // dịnh dạng này thì là lỗi.
        // Hàm matches dùng để xác định xem chuỗi email của mình có khớp với định dạng mình đã quy 
        // định trước hay không. hàm này trả về true , false nên mình khởi tạo 1 biến ktEmail rồi gán
        // cho nó. false thì báo lỗi.
    }
    public boolean kiemTraTen(String ten){
        for (int i = 0; i < 10; i++) {
            if(ten.contains(i+"")){
                return false;
            }
        }
        return true;
    }
    public String kiemTraSDT(String sdt){
        sdt = sdt.trim();
        sdt = sdt.replace(".", "");
        sdt = sdt.replace(",", "");
        sdt = sdt.replace(" ", "");
        sdt = sdt.replaceAll("\\s+", "");
        try{
            if(!sdt.startsWith(0+"")){
                return "Số điện thoại phải bắt đầu bằng số 0";
                
            } else if(sdt.length() != 10 && sdt.length() != 11){
                return "Số điện thoại phải có 10 hoặc 11 chữ số";
            }
            long sdt_long = Long.parseLong(sdt);
        } catch(NumberFormatException ex){
            return "Số điện thoại không được chứa chữ cái";
        } catch(Exception ex){
            return "Sai dinh dang" + ex.getMessage();
        }
        return "";
    }
    
    public String md5(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
}
    //
    public static void PBKDF2(String pass) 
   throws NoSuchAlgorithmException, InvalidKeySpecException
    {
    String generatedSecuredPasswordHash 
        = generateStorngPasswordHash(pass);
    System.out.println(generatedSecuredPasswordHash);
    }
    private static String generateStorngPasswordHash(String password) 
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
    
    
    
}


