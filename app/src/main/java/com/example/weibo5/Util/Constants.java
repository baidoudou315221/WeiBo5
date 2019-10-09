package com.example.weibo5.Util;

public class Constants {
    public static String name = null;
    public static String gender = null;
    public static String location = null;
    public static String token = null;

    public static final String APP_KEY = "1546468873";
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    public static final String APP_SECRET = "8f6429813e2a9d6673f07d9235af482d";
    public static final String URL = "https://api.weibo.com/oauth2/authorize?client_id=" + APP_KEY + "&redirect_uri=" + REDIRECT_URL + "&response_type=code";
    //https://api.weibo.com/oauth2/authorize?client_id=1546468873&redirect_uri=https://api.weibo.com/oauth2/default.html&response_type=code
    public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";
}
