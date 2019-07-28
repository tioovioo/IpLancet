package com.lancet.iplancet;

import com.lancet.iplancet.dao.MedicalRecordMapper;
import com.lancet.iplancet.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author  yongjia.guo
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IpLancetApplicationTests {

	@Autowired
	private MedicalRecordMapper medicalRecordDao;

	@Test
	public void contextLoads() {



	}

	//主键回显.
	@Test
	public void  mybatisTest(){
		User user = new User();
		user.setUserName("AlphaGo");
		user.setUserEmail("alphago.gmail.com");
		user.setBankId(10086);
		user.setTelephoneNumber(7391723);
		user.setUserRight(1);
		//Mehtod01:  useGeneratedKeys属性生效条件: 主键自增+针对insert 语句生效;
		//Dao接口如果使用@Param("user"),则 keyProperty=user.userId,
		//           未使用则 keyProperty=userId
		Integer num = medicalRecordDao.insertToUser(user);
		log.info("num: {}",num);
		log.info("userId: {}",user.getUserId());


	}

	@Test
	public void mybatisTest02(){
		User user = new User();
		user.setUserName("AlphaGo02");
		user.setUserEmail("alphago.gmail.com");
		user.setBankId(10086);
		user.setTelephoneNumber(7391723);
		user.setUserRight(1);

		//Method02: 使用Oracle 这样不支持自增主键列的数据库,则Method01不生效.
		Integer num = medicalRecordDao.insertToUserForOracle(user);
		log.info("num: {}",num);
		log.info("userId: {}",user.getUserId());
	}



}
