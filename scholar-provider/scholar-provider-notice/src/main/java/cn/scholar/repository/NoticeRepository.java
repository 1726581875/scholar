package cn.scholar.repository;

import cn.scholar.pojo.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NoticeRepository extends JpaRepository<Notice,Integer>, JpaSpecificationExecutor<Notice> {
    
}
