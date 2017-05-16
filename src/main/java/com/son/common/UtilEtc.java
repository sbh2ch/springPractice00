package com.son.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kiost on 2017-04-24.
 */
public class UtilEtc {
    private static final String CHARSET = "UTF-8";
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilEtc.class);

    private UtilEtc() {
        /* private constructor */
    }

    public static String getShortString(String str, Integer len) {
        try {
            if (str.getBytes(CHARSET).length > len) {
                str = strCut(str, len) + "...";
            }
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }

        return str;
    }

    private static String strCut(String str, Integer len) {
        String rval = str;
        int oooF = 0;
        int oooL = 0;
        int rrrF = 0;
        int rrrL = 0;
        int nlengthPrev = 0;

        try {
            byte[] bytes = rval.getBytes(CHARSET);

            int jcount = 0;
            if (nlengthPrev > 0) {
                while (jcount < bytes.length) {
                    if ((bytes[jcount] & 0x80) != 0) {
                        oooF += 2;
                        rrrF += 3;
                        if (oooF + 2 > nlengthPrev) {
                            break;
                        }
                        jcount += 3;
                    } else {
                        if (oooF + 1 > nlengthPrev) {
                            break;
                        }
                        ++oooF;
                        ++rrrF;
                        ++jcount;
                    }
                }
            }
            jcount = rrrF;
            while (jcount < bytes.length) {
                if ((bytes[jcount] & 0x80) != 0) {
                    if (oooL + 2 > len) {
                        break;
                    }
                    oooL += 2;
                    rrrL += 3;
                    jcount += 3;
                } else {
                    if (oooL + 1 > len) {
                        break;
                    }
                    ++oooL;
                    ++rrrL;
                    ++jcount;
                }
            }
            rval = new String(bytes, rrrF, rrrL, CHARSET);
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }

        return rval;
    }
}
