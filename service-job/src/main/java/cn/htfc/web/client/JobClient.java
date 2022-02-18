package cn.htfc.web.client;

import java.util.Map;

/**
 * <p>Description: </p>
 * <p>Company: Harbin Institute of Technology</p>
 *
 * @author weihuang
 * @date 2022/2/17
 * @time 3:12 PM
 */
public interface JobClient {
    void addJob(String jobId, String jobName, String jobType);

    Map<String, Object> queryJobById(String jobId);
}
