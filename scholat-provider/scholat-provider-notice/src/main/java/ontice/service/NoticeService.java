package ontice.service;

import ontice.pojo.Notice;
import ontice.repository.NoticeRepository;
import org.scholat.common.constant.MyConstant;
import org.scholat.common.message.enums.CommonEnum;
import org.scholat.common.message.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> findAll(){
        return noticeRepository.findAll();
    }

    public Page<Notice> findAllByAcceptId(Integer acceptId,Integer page){
        //构造页面条件 ,第几页,每页大小
        Pageable pageable = PageRequest.of(page-1, MyConstant.PAGE_SIZE, Sort.Direction.DESC ,"createTime");

    //原版构造查询条件
//        Specification<Notice> spec = new Specification<Notice>() {
//            @Override
//            public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                Path<Object> path = root.get("acceptId");
//                return cb.equal(path, acceptId);
//            }
//        };

      //lambda版简写
        Specification<Notice> spec = (root ,query ,cb) ->{
             return cb.equal(root.get("acceptId"), acceptId);
            };

        //调用分页查询方法
        return noticeRepository.findAll(spec,pageable);
    }


    public Notice insert(Notice notice){

//         UserLogin user = new UserLogin();
//         user.setPassword(传来的密码);
//         user.setPhone(传来的手机号);
//        noticeRepository.findOne(Example.of(user));

      return  noticeRepository.save(notice);
    }


    public void deleteNotice(Integer noticeId){
        noticeRepository.deleteById(noticeId);
    }

    public  List<Notice> insertAll(List<Notice> list){
       return noticeRepository.saveAll(list);
    }


    public boolean setNoticeResult(int noticeId){
        Optional<Notice> optionalNotice = noticeRepository.findById(noticeId);
        if(!optionalNotice.isPresent()){
           throw new CommonException(CommonEnum.UNKONW_ERROR,"没有该通知");
        }
        Notice notice = optionalNotice.get();
        notice.setNoticeFlag(4);//设置成已同意
        Notice resultNotice = noticeRepository.save(notice);
        return resultNotice == null ? false : true;
    }


}
