package com.example.atdemo;

import java.util.regex.Pattern;

/**
 * @anthor: gaotengfei
 * @time: 2017/3/10
 * @tel: 18511913443
 * @desc:
 */

public class CharUtil {
//    public static void main(String[] args) {
//        String[] strArr = new String[] { "www.micmiu.com",
//                "!@#$%^&amp;*()_+{}[]|\"'?/:;&lt;&gt;,.", "！￥……（）——：；“”‘’《》，。？、", "不要啊",
//                "やめて", "韩佳人", "한가인" };
//        for (String str : strArr) {
//            System.out.println("===========&gt; 测试字符串：" + str);
//            System.out.println("正则判断：" + isChineseByREG(str) + " -- "
//                    + isChineseByName(str));
//            System.out.println("Unicode判断结果 ：" + isChinese(str));
//            System.out.println("详细判断列表：");
//            char[] ch = str.toCharArray();
//            for (int i = 0; i &lt; ch.length; i++) {
//                char c = ch[i];
//                System.out.println(c + " --&gt; " + (isChinese(c) ? "是" : "否"));
//            }
//        }
//
//    }

    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    // 完整的判断中文汉字和符号
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

}
