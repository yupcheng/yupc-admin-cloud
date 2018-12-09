package com.github.zuihou.admim;


import com.alibaba.druid.filter.config.ConfigTools;

/**
 * Created by yupcheng on 2018/12/9.
 */
public class ConfigApplicationTest {

    public static void main(String[] args) throws Exception {
        System.out.println(ConfigTools.encrypt("root"));
    }
}