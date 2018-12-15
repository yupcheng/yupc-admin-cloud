package com.github.yupc.file.repository.file.service.impl;

import com.github.yupc.base.dao.BaseDao;
import com.github.yupc.base.service.impl.BaseServiceImpl;
import com.github.yupc.file.entity.file.po.ZhFile;
import com.github.yupc.file.repository.file.dao.ZhFileMapper;
import com.github.yupc.file.repository.file.example.ZhFileExample;
import com.github.yupc.file.repository.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yupc
 * @createTime 2018-01-26 23:05
 */
@Service
public class FileServiceImpl extends BaseServiceImpl
        <Long, ZhFile, ZhFileExample> implements FileService {
    @Autowired
    private ZhFileMapper zhFileMapper;

    @Override
    protected BaseDao<Long, ZhFile, ZhFileExample> getDao() {
        return zhFileMapper;
    }

    @Override
    public Integer removeDirByAppIdAndId(String appId, Long id) {
        return  zhFileMapper.removeDirByAppIdAndId(appId, id);
    }
}
