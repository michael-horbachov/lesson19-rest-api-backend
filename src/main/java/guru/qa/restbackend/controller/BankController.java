package guru.qa.restbackend.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import guru.qa.restbackend.domain.LoginInfo;
import guru.qa.restbackend.domain.UserInfo;
import guru.qa.restbackend.exception.InvalidUserNameException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    private Map<String, UserInfo> users = Map.of(
            "Dima", UserInfo.builder().userName("Dima").build(),
            "Olga", UserInfo.builder().userName("Olga").build(),
            "Ivan", UserInfo.builder().userName("Ivan").build()
    );

    @PostMapping("user/login")
    @ApiOperation("Authorization")
    public UserInfo doLogin(@RequestBody LoginInfo loginInfo) {
        if (loginInfo.getUserName().equals("Dima")) {
            return UserInfo.builder()
                           .loginDate(new Date())
                           .userName(loginInfo.getUserName())
                           .build();
        } else {
            throw new InvalidUserNameException();
        }
    }

    @GetMapping("user/getAll")
    @ApiOperation("Get all users")
    public List<UserInfo> getAllUsersInfo() {

//        List<UserInfo> result = new ArrayList<>();
//        for (Map.Entry<String, UserInfo> entry : users.entrySet()) {
//            result.add(entry.getValue());
//        }
//        return result;

        return users.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
    }
}