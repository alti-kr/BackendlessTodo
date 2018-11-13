package com.gmail.sergii_tymofieiev.backendlesstodo.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class DateUtils {
    public enum DateFormatCustom {
        ddMMyyyy_HHmmss("dd.MM.yyyy HH:mm:ss"),
        ddMMyyyy_HHmm("dd.MM.yyyy HH:mm"),
        ddMMyyyy("dd.MM.yyyy"),
        ddMMyy("dd.MM.yy"),
        ddMMyyyyHHmmss("yyyyMMddHHmmss"),
        ddMMyyyyLineFeedHHmmss("dd.MM.yyyy\nHH:mm:ss"),
        yyyy_MINUS_MM_MINUS_dd_HHmmss("yyyy-MM-dd_HH:mm:ss"),
        yyyy_MINUS_MM_MINUS_dd_HH_MINUS_mm_MINUS_ss("yyyy-MM-dd_HH-mm-ss"),
        HHmmss("HH:mm:ss"),
        yy("yy"),
        MM("MM"),
        dd("dd"),
        HH("HH"),
        mm("mm"),
        ss("ss"),
        MMddyyyy_HHmmss("MM/dd/yyyy HH:mm:ss"),
        MMMddyyyy("MMM dd yyyy"),
        yyyy_MINUS_MM_MINUS_dd("yyyy-MM-dd"),
        yyyy_MINUS_MM_MINUS_dd_Z("yyyy-MM-dd'T'HH:mm:ssZ");

        String formatString;

        @Override
        public String toString() {
            return formatString;
        }

        private DateFormatCustom(String tFString) {
            this.formatString = tFString;
        }
    }

    ;

    public static String getFormattedDateAsString(DateFormatCustom dateFormatCustom, Long tDate) {
        try {
            return new SimpleDateFormat(dateFormatCustom.formatString).format(new Date(tDate));
        } catch (Exception e) {
            return "";
        }
    }
}
