package ontice.repository;

import ontice.pojo.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NoticeRepository extends JpaRepository<Notice,Integer>, JpaSpecificationExecutor<Notice> {
    
}
