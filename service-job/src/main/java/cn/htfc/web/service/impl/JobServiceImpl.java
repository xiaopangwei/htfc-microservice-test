package cn.htfc.web.service.impl;

import cn.htfc.web.client.JobClient;
import cn.htfc.web.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>Description: </p>
 * <p>Company: Harbin Institute of Technology</p>
 *
 * @author weihuang
 * @date 2022/2/17
 * @time 3:29 PM
 */
@Service
public class JobServiceImpl implements IJobService {
    @Autowired
    private JobClient jobClient;

    @Override
    public void add(String jobId, String jobName, String jobType) {
        jobClient.addJob(jobId, jobName, jobType);
    }

    @Override
    public Map<String, Object> query(String jobId) {
        return jobClient.queryJobById(jobId);
    }
}
