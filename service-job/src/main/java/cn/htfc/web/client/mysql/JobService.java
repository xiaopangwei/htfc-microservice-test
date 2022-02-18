package cn.htfc.web.client.mysql;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * <p>Description: </p>
 * <p>Company: Harbin Institute of Technology</p>
 *
 * @author weihuang
 * @date 2022/2/17
 * @time 3:13 PM
 */
public class JobService {

    private JdbcTemplate jdbcTemplate;

    public JobService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addJob(String jobId, String jobName, String jobType) {
        String pattern = "insert into ZHHT_JOB(job_id,job_name,job_tye) values('%s','%s','%s')";
        jdbcTemplate.execute(String.format(pattern, jobId, jobName, jobType));
    }


    public Map<String, Object> queryJobById(String jobId) {
        String              pattern = "select * from ZHHT_JOB where job_id='%s'";
        Map<String, Object> res     = jdbcTemplate.queryForMap(String.format(pattern, jobId));
//        System.out.println(res);
        return res;
    }

}
