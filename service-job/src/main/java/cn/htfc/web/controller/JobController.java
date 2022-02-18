package cn.htfc.web.controller;

import cn.htfc.web.service.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>Description: </p>
 * <p>Company: Harbin Institute of Technology</p>
 *
 * @author weihuang
 * @date 2022/2/17
 * @time 3:31 PM
 */
@RestController
public class JobController {

    private static Logger log = LoggerFactory.getLogger(JobController.class);

    @Autowired
    IJobService iJobService;

    @PostMapping("/zhhtJob/insert")
    public String add(String jobId, String jobName, String type) throws InterruptedException {
        iJobService.add(jobId, jobName, type);
        return jobId;
    }


    @GetMapping("/zhhtJob/query")
    public String query(String jobId) throws InterruptedException {
        Map<String, Object> ans = iJobService.query(jobId);
        return (String) ans.getOrDefault("count", "0");
    }

}
