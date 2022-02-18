package cn.htfc.web.job.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>Description: </p>
 * <p>Company: Harbin Institute of Technology</p>
 *
 * @author weihuang
 * @date 2022/2/18
 * @time 4:46 PM
 */
public interface JobMapper {
    Map<String, Object> queryById(String jobId);

    void addJob(@Param("jobId") String jobId, @Param("jobName") String jobName,@Param("jobType") String jobType);
}
