package cn.htfc.web.service;

import java.util.Map;

/**
 * <p>Description: </p>
 * <p>Company: Harbin Institute of Technology</p>
 *
 * @author weihuang
 * @date 2022/2/17
 * @time 3:28 PM
 */
public interface IJobService {
    void add(String jobId,String jobNmae,String jobType);

    Map<String,Object> query(String jobId);
}
