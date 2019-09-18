package com.example.demomybatis.utils;

import com.example.demomybatis.exception.CustomException;
import com.example.demomybatis.system.entity.SystemUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @liuhongchun
 * @Date 2019/9/16  11:23
 * @des
 */
public class ShiroUtils {
    /**  加密算法 */
    public final static String hashAlgorithmName = "SHA-256";
    /**  循环次数 */
    public final static int hashIterations = 16;

    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SystemUser getSystemUser() {
        return (SystemUser)SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getSystemUser().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        /**
         * kaptcha等于null
         * 表示'验证码已失效'
         */
        if(kaptcha == null){
            return String.valueOf(false);
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }
}
