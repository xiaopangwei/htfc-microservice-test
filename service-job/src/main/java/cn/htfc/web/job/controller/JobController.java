package cn.htfc.web.job.controller;

import cn.htfc.web.job.service.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    RestTemplate restTemplateCustom;

    @Value("${broker.service.url}")
    String brokerServiceUrl;

    @PostMapping("/zhhtJob/insert")
    public String insert(String jobName) throws InterruptedException {
        log.info("start to insert job...[{}]", jobName);
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
        log.info("end to insert job...jobName:[{}],jobId:[{}]", jobName, randomId);
        return randomId;
    }


    @GetMapping("/zhhtJob/query")
    public String query(String jobId) throws InterruptedException {
        log.info("start to query job,jobId:[{}]", jobId);
        Map<String, Object> ans = iJobService.query(jobId);
        System.out.println(ans);
        String res = String.valueOf(ans.getOrDefault("cnt", 0));
        log.info("end to query job,jobId:[{}]", res);
        return res;
    }

    //测试微服务调用
    @PostMapping("/zhhtJob/add")
    public String add(String jobName, String custNum, String custName, String custMobile) {
        log.info("start to invoke job microservice...jobName:[{}],custNum:[{}],custName:[{}],custMobile:[{}]", jobName,custNum,custName,custMobile);
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
        log.info("success to invoke job microservice...{},jobId:[{}]", jobName, random);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //提交参数设置
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("custMobile", custMobile);
        map.add("custNum", custNum);
        map.add("custName", custName);
        map.add("jobId", randomId);

        // 组装请求体
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<MultiValueMap<String, String>>(map, headers);
        log.info("start to invoke broker microservice...jobName:[{}]", jobName);

        String resp = restTemplateCustom.postForObject(brokerServiceUrl + "/zhhtCustBroker/insert", request, String.class);
        log.info("success to invoke broker microservice...jobName:[{}],response:[{}]", jobName, resp);
        return resp;
    }

}
