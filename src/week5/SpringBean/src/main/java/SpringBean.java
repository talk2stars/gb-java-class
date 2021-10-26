import model.Animal;
import model.Human;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBean {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Human human = (Human) context.getBean("human");
        System.out.println(human.toString());

        human.act();

    }
}
