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
import top.springdatajpa.zujijpa.Specifications;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    private MessageFactory messageFactory;

    public List<Notice> findAll(){
        return noticeRepository.findAll();
    }

    /**
     * 根据用户id查它的所有id
     * @param acceptId
     * @param page
     * @return
     */
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
      return  noticeRepository.save(notice);
    }


    public void deleteNotice(Integer noticeId){
        noticeRepository.deleteById(noticeId);
    }

    /**
     * 批量插入
     * @param sendId
     * @param courseId
     * @return
     */
    public  List<Notice> insertAll(Integer sendId , Integer courseId){
        List<Notice> workMessage = messageFactory.getHomeWorkMessage(sendId, courseId);
        return noticeRepository.saveAll(workMessage);
    }

    /**
     * 设置通知为已同意
     * @param noticeId
     * @return
     */
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


    public int insertAllNotice(List<Notice> noticeList){
        List<Notice> notices = noticeRepository.saveAll(noticeList);
        return notices.size();
    }

    /**
     * 查找某个用户消息的数量
     * @param userId
     * @return
     */
    public int findNoticeAmountByUserId(Integer userId){
        List<Notice> noticeList = noticeRepository.findAll(
                Example.of(new Notice()
                        .setAcceptId(userId).setNoticeFlag(0)));
        return noticeList.size();
    }

    /**
     * 把消息设置成已读
     * @param noticeId
     */
    public void readMsgById(Integer noticeId) {
        Optional<Notice> optionalNotice = noticeRepository.findById(noticeId);
        if(optionalNotice.isPresent()){
            Notice notice = optionalNotice.get();
            notice.setNoticeFlag(1);
            noticeRepository.save(notice);
        }

    }


    public Page<Notice> findNoticeByCondition(Integer userId,Integer flag, Integer page){
        //构造页面条件 ,第几页,每页大小
        Pageable pageable = PageRequest.of(page -1, MyConstant.PAGE_SIZE, Sort.Direction.DESC ,"createTime");

        //构造条件
        Specification<Notice> spec = Specifications.where(e -> {
            if(flag != null) e.eq("noticeFlag",flag);
            e.eq("acceptId",userId);
        });

        //调用分页查询方法
        return noticeRepository.findAll(spec,pageable);
    }


}
