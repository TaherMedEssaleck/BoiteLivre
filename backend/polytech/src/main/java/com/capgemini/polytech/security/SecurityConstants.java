package com.capgemini.polytech.security;


public class SecurityConstants {
    public static final long JWT_EXPIRATION = 60000*60*24*7;
    public static final long JWT_REFRESH_EXPIRATION = 60000*60*24*30;
    public static final String JWT_SECRET = "secret";
}
