package cn.scholar.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Accessors(chain = true)
public class Notice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer noticeId;

    private Integer sendId;

    private Integer acceptId;

    private String noticeContent;

    private String refuseUrl;

    private String acceptUrl;

    private Integer noticeType;

    private Integer noticeFlag;

    private Date createTime;


}
