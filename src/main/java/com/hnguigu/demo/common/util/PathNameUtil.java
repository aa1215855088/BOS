package com.hnguigu.demo.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @program: BOS
 * @description:
 * @author: 徐子楼
 * @create: 2018-10-08 19:08
 **/
public class PathNameUtil {

    /**
     * 通过当前时间合成文件名
     *
     * @return
     */
    public static String exportPathName() {
        return "管理分区" + getDate()+".xls";
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = format.format(new Date());
        return date;
    }
}
