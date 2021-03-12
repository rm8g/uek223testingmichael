package ch.noser.uek223.domain.user;

import ch.noser.uek223.domain.user.dto.UserDTOMinimal;
import ch.noser.uek223.domain.user.dto.UserDTONew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserWeb {

    private final UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserWeb(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping({"/", ""})
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserDTOMinimal> save(@RequestBody UserDTONew user) {
        return new ResponseEntity<UserDTOMinimal>(userMapper.toDTO(userService.create(userMapper.userDTONewToUser(user))), HttpStatus.CREATED);
    }

}
