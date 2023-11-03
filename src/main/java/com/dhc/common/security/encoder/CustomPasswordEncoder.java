package com.dhc.common.security.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author donghongchen
 * @create 2023/11/1 14:26
 * @Description: 定制加解密
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Override
    public String encode(CharSequence rawPassword) {
        //md5加密
//        return Arrays.toString(DigestUtils.md5Digest(rawPassword.toString().getBytes()));
        //bc加密
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //md5解密
//        return encodedPassword.equals(Arrays.toString(DigestUtils.md5Digest(rawPassword.toString().getBytes())));
        //bc解密
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }

}
