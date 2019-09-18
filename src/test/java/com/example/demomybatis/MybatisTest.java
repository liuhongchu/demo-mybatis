package com.example.demomybatis;
import com.example.demomybatis.system.entity.SystemUser;
import com.example.demomybatis.system.mapper.TestUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.io.InputStream;
import static org.apache.ibatis.io.Resources.getResourceAsStream;

//spring boot 项目测试类必须加的注解
@RunWith(SpringRunner.class)
public class MybatisTest {

    //添加测试注解
    @Test
    //自定义一个方法
    public void contextLoads() {
        try {
            // 定义自己的mybatis配置文件
            String resource = "mybatis-config.xml";
            //加载配置文件到InputStream中
            InputStream inputStream = getResourceAsStream(resource);
            // 创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 获取SqlSession
            SqlSession session = sqlSessionFactory.openSession();
            //获取Mapper
            TestUserMapper userMapper = session.getMapper(TestUserMapper.class);
            //创建一个对象，set 一个值
            SystemUser systmemUser = new SystemUser();
            systmemUser.setUserId((long) 1);
            //通过查询 传入一个对象接受，查询id等于1 的 数据
            systmemUser = userMapper.selectById(systmemUser);
            //打印一下
            System.out.println("id：" + systmemUser.getUserId() + "名字：" + systmemUser.getUserName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
