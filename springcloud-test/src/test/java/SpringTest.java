import com.dys.spring.BeanConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

    @Test
    public void testIOC(){
        AnnotationConfigApplicationContext applicatoin = new AnnotationConfigApplicationContext(BeanConfig.class);
        System.out.println("容器创建完成... ...");
        String[] names = applicatoin.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        Object computer = applicatoin.getBean("user");
        System.out.println(computer);

        Object computer1 = applicatoin.getBean("user");
        System.out.println(computer == computer1);
    }
}
