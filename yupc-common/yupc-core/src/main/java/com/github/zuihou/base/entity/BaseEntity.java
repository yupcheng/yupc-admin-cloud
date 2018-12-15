package com.github.yupc.base.entity;

import java.io.Serializable;

/**
 * @author yupc
 * @createTime 2017-12-08 17:34
 */
public abstract class BaseEntity<I extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract I getId();

    public abstract void setId(I id);
}
