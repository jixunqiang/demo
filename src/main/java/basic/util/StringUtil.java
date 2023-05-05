package basic.util;

import com.aliyun.oss.common.utils.DateUtil;

import java.util.UUID;

public class StringUtil {

    /**
     * 生成指定长度的随机字符串
     *
     * @param length
     * @return
     */
    public String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
