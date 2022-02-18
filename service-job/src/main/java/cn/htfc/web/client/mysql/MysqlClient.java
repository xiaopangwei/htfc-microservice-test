package cn.htfc.web.client.mysql;

import cn.htfc.web.client.JobClient;

import java.util.Map;

/**
 * @Author chenjiahan | chenjiahan@corp.netease.com | 2019/3/25
 **/
public class MysqlClient implements JobClient {

    private JobService service;

    public MysqlClient(JobService service) {
        this.service = service;
    }


    @Override
    public void addJob(String jobId, String jobName, String jobType) {
        service.addJob(jobId, jobName, jobType);
    }

    @Override
    public Map<String, Object> queryJobById(String jobId) {
        return service.queryJobById(jobId);
    }
}
