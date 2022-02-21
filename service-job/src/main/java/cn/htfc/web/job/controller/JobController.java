package cn.htfc.web.job.controller;

import cn.htfc.web.job.service.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

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
    public String add(String jobName) throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        String        randomId      = null;
        Random        random        = new Random();
        while (true) {
            for (int i = 0; i < 8; i++) {
                stringBuilder.append(String.valueOf(random.nextInt(9) + 1));
            }
            randomId = stringBuilder.toString();
            //如果跟数据库重复
            Map<String, Object> map = iJobService.query(randomId);
            if ((Long) (map.getOrDefault("cnt", 0)) == 0) {
                break;
            }
            stringBuilder.setLength(0);
        }
        String type = String.valueOf(new Random().nextInt(2));
        iJobService.add(randomId, jobName, type);
        return randomId;
    }


    @GetMapping("/zhhtJob/query")
    public String query(String jobId) throws InterruptedException {
        log.info("query job");
        Map<String, Object> ans = iJobService.query(jobId);
        System.out.println(ans);
        return String.valueOf(ans.getOrDefault("cnt", 0));
    }

}
