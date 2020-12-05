import Bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {

    public static void main(String[] args) {
//        Person person=new Person();
//        person.setName("zhangsan");
//        person.setAge(27);
//        person.setAddress("hangzhou");
//        System.out.println(person);

        ApplicationContext context=new ClassPathXmlApplicationContext("app.xml");
        Person person = context.getBean("person",Person.class);
        System.out.println(person);
    }
}
