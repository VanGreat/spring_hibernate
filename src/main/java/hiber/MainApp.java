package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.CarService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      Car tesla = new Car("Tesla Model 3", 2017);
      Car lexus = new Car("Lexus LC 500", 2018);
      Car ferrari = new Car("Ferrari F8 Tributo", 2019);
      Car porsche = new Car("Porsche 911 Carrera 4S", 2020);


      User elon = new User("Elon", "Musk", "elonmusk@mail.us");
      elon.setCar(tesla);
      User eiji = new User("Eiji", "Toyoda", "eijitoyoda@mail.jp");
      eiji.setCar(lexus);
      User enzo = new User("Enzo", "Ferrari", "enzoferrari@mail.it");
      enzo.setCar(ferrari);
      User ferdinand = new User("Ferdinand", "Porsche", "ferdinandporsche@mail.de");
      ferdinand.setCar(porsche);

      UserService userService = context.getBean(UserService.class);

      userService.add(elon);
      userService.add(eiji);
      userService.add(enzo);
      userService.add(ferdinand);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      CarService carService = context.getBean(CarService.class);
      List<Car> cars = carService.listCars();
      for (Car car : cars) {
         System.out.println(carService.getUser(car));
      }

      context.close();
   }
}
