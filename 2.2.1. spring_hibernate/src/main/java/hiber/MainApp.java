package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Соколов", "Никита", "socol@mail.ru");
      user1.setCar(new Car(user1,"VAZ", 1111));

      User user2 = new User("Георгий", "Борисов", "bjrya@mail.ru");
      user2.setCar(new Car(user2,"TOYOTA", 2222));

      User user3 = new User("Мария", "Громова", "MARIA@mail.ru");
      user3.setCar(new Car(user3,"BMW", 3333));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
      }
      try {
         System.out.println(userService.getUserBySeriesAndModel("VAZ", 1111));
      } catch (Exception e) {
         System.out.println("Нету пользователя с такой машиной");
      }
      context.close();
   }
}
