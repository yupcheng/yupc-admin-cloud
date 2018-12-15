package com.github.yupc.file.rest.file.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yupc
 */
@Data
@AllArgsConstructor
public class FileListDTO implements Serializable {
    private List<FileDTO> list;
}
