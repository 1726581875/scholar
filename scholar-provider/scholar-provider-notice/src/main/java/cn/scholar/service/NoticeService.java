package cn.scholar.service;

import cn.scholar.common.constant.MyConstant;
import cn.scholar.common.message.enums.CommonEnum;
import cn.scholar.common.message.exception.CommonException;
import cn.scholar.repository.NoticeRepository;
import cn.scholar.pojo.Notice;
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
    @Autowired
    private MessageFactory messageFactory;

    public List<Notice> findAll(){
        return noticeRepository.findAll();
    }

    public Notice insert(Notice notice){
        return  noticeRepository.save(notice);
    }

    public void deleteNotice(Integer noticeId){
        noticeRepository.deleteById(noticeId);
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

      //lambda版简写
        Specification<Notice> spec = (root ,query ,cb) ->{
             return cb.equal(root.get("acceptId"), acceptId);
            };
        //调用分页查询方法
        return noticeRepository.findAll(spec,pageable);
    }




    /**
     * 批量删除消息
     * @param noticeIds
     */
    public void deleteNotice(List<Integer> noticeIds){
       List<Notice> noticeList = noticeRepository.findAllById(noticeIds);
       if(noticeList != null) {
           noticeRepository.deleteInBatch(noticeList);
       }
    }


    /**
     * 批量插入
     * @param sendId
     * @param courseId
     * @return
     */
    public  List<Notice> insertAll(Integer sendId , Integer courseId,String message){
        List<Notice> workMessage = messageFactory.getHomeWorkMessage(sendId, courseId,message);
        return noticeRepository.saveAll(workMessage);
    }

    public int insertAllNotice(List<Notice> noticeList){
        List<Notice> notices = noticeRepository.saveAll(noticeList);
        return notices.size();
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




    /**
     * 查找某个用户未读消息的数量
     * @param userId
     * @return
     */
    public int findUnReadNoticeAmount(Integer userId){
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


    public Page<Notice> findNoticeByUserIdAndFlag(Integer userId,Integer flag, Integer page){
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
