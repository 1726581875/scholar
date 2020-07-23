package cn.scholar.studentHomeWork.service;

/**
 * @author yrk
 * @date 2020/6/15 - 15:27
 */
public interface IDownLoadService {
    /**
     * 从OSS上获取需要下载的文件url
     * @return
     */
    public String getFileUrl();

    /**
     *
     * @return
     */
    public String downLoadFormOSS();
}
