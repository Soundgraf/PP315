package pp315;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pp315.entity.User;

import java.util.List;

@Component
public class RestConnection {
    private final RestTemplate restTemplate;
    private String URL = "http://94.198.50.185:7081/api/users";
    private StringBuilder code = new StringBuilder();

    @Autowired
    public RestConnection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public StringBuilder getCode() {
        return code;
    }

    public List<String> getAllUsers(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.GET,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());  //allUsers JSON
        return responseEntity.getHeaders().get("Set-Cookie");
    }

    public void addUser(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.POST,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        code.append(responseEntity.getBody());
    }

    public void editUser(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.PUT,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        code.append(responseEntity.getBody());
    }

    public void deleteUser(HttpEntity<User> requestEntity,int id) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id,
                HttpMethod.DELETE,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        code.append(responseEntity.getBody());
    }
}