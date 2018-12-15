package com.github.yupc.file.repository.file.service;

import com.github.yupc.base.service.BaseService;
import com.github.yupc.file.entity.file.po.ZhFile;
import com.github.yupc.file.repository.file.example.ZhFileExample;

/**
 * @author yupc
 * @createTime 2018-01-26 23:05
 */
public interface FileService extends BaseService<Long, ZhFile, ZhFileExample> {
    /**
     * 根据appId和 tree_path 删除文件夹
     *
     * @param appId
     * @param id
     */
    Integer removeDirByAppIdAndId(String appId, Long id);

}
