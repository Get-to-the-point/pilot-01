package get.to.the.point.pilot01.mapper;

import get.to.the.point.pilot01.controller.UserUpdateDto;
import get.to.the.point.pilot01.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    // 직접 SQL을 작성하여 사용자 정보를 조회
    @Select("SELECT * FROM users WHERE is_active = true")

    // UserMapper.xml 파일에 SQL 정보가 있음
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByEmail(String email);
    void insertUser(User user);
    void updateUser(Long id, UserUpdateDto user);
    void deleteUser(Long id);
}
