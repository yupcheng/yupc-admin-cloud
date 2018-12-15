package com.github.yupc.file.utils;

import com.github.yupc.file.config.FileProperties;
import com.github.yupc.file.support.Config;
import com.github.yupc.file.support.FileManager;
import com.github.yupc.file.support.FileModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yupc
 * @createTime 2018-01-23 22:14
 */
@Slf4j
public class UploadUtil {

    /**
     * 移动文件到fastDFS
     */
    public static FileModel remove2DFS(String namespace, String objectId,
                                       FileProperties fileProperties, String absolutePath,
                                       String relativePath, String
                                               fileName) {
        FileModel fastDFSFile = new FileModel(namespace, objectId, absolutePath, relativePath, fileName);
        if (fileProperties.getFastdfs().isEnabled() && fastDFSFile.getKey() != null) {
            FileManager.getInstance().upload(fileProperties.getRemoteUriPrefix(), fastDFSFile);
        } else {
            // 将 \ 替换成成 /
            String url = new StringBuilder(fileProperties.getRemoteUriPrefix())
                    .append(relativePath.replaceAll("\\\\", Config.SEPARATOR))
                    .append(Config.SEPARATOR)
                    .append(fileName).toString();
            fastDFSFile.setRemotePath(url);
        }
        return fastDFSFile;
    }


}
