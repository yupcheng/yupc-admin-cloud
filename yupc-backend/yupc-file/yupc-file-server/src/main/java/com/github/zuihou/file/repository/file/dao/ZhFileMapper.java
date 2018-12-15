package com.github.yupc.file.repository.file.dao;

import org.apache.ibatis.annotations.Param;

public interface ZhFileMapper extends com.github.yupc.base.dao.BaseDao<Long, com.github.yupc.file.entity.file.po.ZhFile, com.github.yupc.file.repository.file.example.ZhFileExample> {

    Integer removeDirByAppIdAndId(@Param("appId") String appId, @Param("id") Long id);
}