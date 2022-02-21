package cn.htfc.web.broker.controller;

import cn.htfc.web.broker.model.BrokerResponse;
import cn.htfc.web.broker.service.IBrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 * <p>Company: Harbin Institute of Technology</p>
 *
 * @author weihuang
 * @date 2022/2/17
 * @time 3:31 PM
 */
@RestController
public class BrokerController {

    private static Logger log = LoggerFactory.getLogger(BrokerController.class);

    @Autowired
    IBrokerService iBrokerService;

    @PostMapping("/zhhtCustBroker/insert")
    public BrokerResponse add(String jobId, String custName, String custNum, String custMobile) throws InterruptedException {
        log.info("start to add broker...jobId:[{}],custName:[{}],custNum:[{}],custMobile:[{}]", jobId, custName, custNum, custMobile);
        String serviceId = jobId + custNum;
        iBrokerService.add(serviceId, custNum, jobId, custName, custMobile);
        BrokerResponse response = new BrokerResponse();
        response.setCustNum(custNum);
        response.setJobId(jobId);
        response.setServiceId(serviceId);
        log.info("success to add broker...jobId:[{}],custName:[{}],custNum:[{}],custMobile:[{}]", jobId, custName, custNum, custMobile);
        return response;
    }

}
