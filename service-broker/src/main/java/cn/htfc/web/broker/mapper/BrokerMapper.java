package cn.htfc.web.broker.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * <p>Description: </p>
 * <p>Company: Harbin Institute of Technology</p>
 *
 * @author weihuang
 * @date 2022/2/18
 * @time 4:46 PM
 */
public interface BrokerMapper {


    void addBroker(@Param("jobId") String jobId, @Param("serviceId") String serviceId,
                   @Param("custNum") String custNum, @Param("custName") String custName,
                   @Param("custMobile") String custMobile);
}
