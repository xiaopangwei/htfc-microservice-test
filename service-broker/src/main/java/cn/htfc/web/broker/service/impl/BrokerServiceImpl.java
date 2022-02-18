package cn.htfc.web.broker.service.impl;

import cn.htfc.web.broker.mapper.BrokerMapper;
import cn.htfc.web.broker.service.IBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 * <p>Company: Harbin Institute of Technology</p>
 *
 * @author weihuang
 * @date 2022/2/17
 * @tim#mysqle 3:29 PM
 */
@Service
public class BrokerServiceImpl implements IBrokerService {
    @Autowired
    private BrokerMapper brokerMapper;


    @Override
    public void add(String serviceId, String custId, String jobId, String custName, String custMobile) {
        brokerMapper.addBroker(jobId, serviceId, custId, custName, custMobile);
    }
}
