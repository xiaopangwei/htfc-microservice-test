package cn.htfc.web.broker.service;

/**
 * <p>Description: </p>
 * <p>Company: Harbin Institute of Technology</p>
 *
 * @author weihuang
 * @date 2022/2/17
 * @time 3:28 PM
 */
public interface IBrokerService {
    void add(String serviceId, String custNum, String jobId,String custName,String custMobile);
}
