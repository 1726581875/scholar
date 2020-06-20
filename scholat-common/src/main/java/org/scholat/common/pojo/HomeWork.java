package org.scholat.common.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yrk
 * @date 2020/6/12 - 14:34
 * 作业类：
 */
@Data
@Accessors(chain = true)
public class HomeWork implements Serializable {

    private int workId;

    private int taskId;

    private int userId;

    private String workPath;

    private Date createTime;

}
