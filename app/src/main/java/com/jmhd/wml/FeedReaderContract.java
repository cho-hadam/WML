package com.jmhd.wml;

import android.provider.BaseColumns;

public class FeedReaderContract {
    //DB참고
    //https://m.blog.naver.com/PostView.nhn?blogId=pyj721aa&logNo=221291256735&proxyReferer=https:%2F%2Fwww.google.com%2F
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "diary";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_PICTURE = "picture";

    }

}
