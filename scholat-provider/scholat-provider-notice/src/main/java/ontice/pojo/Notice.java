package ontice.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Integer noticeId;

    private Integer sendId;

    private String acceptId;

    private String noticeContent;

    private String refuseUrl;

    private String acceptUrl;

    private Integer noticeType;

    private Integer noticeFlag;

    private Date createTime;


}
