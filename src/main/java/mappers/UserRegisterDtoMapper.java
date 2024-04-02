package mappers;

import models.dto.UserRegisterDTO;
import models.entity.User;

public class UserRegisterDtoMapper {

    public static User map(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setUsername(userRegisterDTO.username);
        user.setName(userRegisterDTO.name);
        user.setSurnames(userRegisterDTO.surnames);
        user.setEmail(userRegisterDTO.email);
        user.setBirth_date(userRegisterDTO.birth_date);
        user.setPassword(userRegisterDTO.password);
        return user;
    }
}
