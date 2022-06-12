package com.imooc;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 21:12
 */
public class LoginContext {

    private static ThreadLocal<String> loginUserId = new ThreadLocal<>();

    public static void initContext(String userId) {
        loginUserId.set(userId);
    }

    public static String getUserId() {
        return loginUserId.get();
    }

    public static void destroy() {
        loginUserId.remove();
    }

}
