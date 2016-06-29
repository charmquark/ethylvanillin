package com.invironz.ethylvanillin.lib;

/**
 * Created by charmquark on 6/9/2016.
 */
public final class EVNames {

    public static final String BUILD = "GRADLE:BUILD";
    public static final String MOD_ID = "ethylvanillin";
    public static final String MOD_NAME = "Ethylvanillin";
    public static final String PKG = "com.invironz." + MOD_ID + ".";
    public static final String VERSION = "GRADLE:VERSION-" + BUILD;

    public static final String PREFIX = MOD_ID.toLowerCase() + ":";

    public static final String PROXY_PKG = EVNames.PKG + "proxy.";
    public static final String PROXY_COMMON = PROXY_PKG + "CommonProxy";
    public static final String PROXY_CLIENT = PROXY_PKG + "ClientProxy";
    public static final String PROXY_SERVER = PROXY_COMMON;

    public static final String OVEN = "oven";
    public static final String QUERN = "quern";
    public static final String WHEAT_BREAD = "wheatBread";
    public static final String WHEAT_DOUGH = "wheatDough";
    public static final String WHEAT_FLOUR = "wheatFlour";
}
