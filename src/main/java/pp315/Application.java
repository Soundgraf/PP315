package pp315;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import pp315.entity.User;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(Application.class, args);

        RestConnection restConnection = context.getBean("restConnection",
                RestConnection.class);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<String> cookies = restConnection.getAllUsers(new HttpEntity<>(httpHeaders));
        System.out.println(cookies);

        httpHeaders.set("Cookie", String.join(";", cookies));

        User newUser = new User(3L, "James", "Brown", (byte)30);

        HttpEntity<User> httpEntity = new HttpEntity<>(newUser, httpHeaders);

        restConnection.addUser(httpEntity);

        newUser.setName("Thomas");
        newUser.setLastName("Shelby");

        restConnection.editUser(httpEntity);

        restConnection.deleteUser(httpEntity, 3);

        System.out.println(restConnection.getCode());
    }
}