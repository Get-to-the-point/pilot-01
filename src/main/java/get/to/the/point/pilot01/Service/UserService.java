package get.to.the.point.pilot01.Service;

import get.to.the.point.pilot01.entity.User;
import get.to.the.point.pilot01.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Boolean addUser(User user) {
        try {
            this.userMapper.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public User getUser(String id) {
        return this.userMapper.getUserById(Long.parseLong(id));
    }
}
