package cn.htfc.web.job.service.impl;

import cn.htfc.web.job.service.IJobService;
import cn.htfc.web.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * <p>Description: </p>
 * <p>Company: Harbin Institute of Technology</p>
 *
 * @author weihuang
 * @date 2022/2/17
 * @tim#mysqle 3:29 PM
 */
@Service
public class JobServiceImpl implements IJobService {
    @Autowired
    private JobMapper jobMapper;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void add(String jobId, String jobName, String jobType) {
        jobMapper.addJob(jobId, jobName, jobType);
    }

    @Override
    public Map<String, Object> query(String jobId) {
        return jobMapper.queryById(jobId);
    }
}
