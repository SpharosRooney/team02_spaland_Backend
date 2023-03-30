package spaland.users.service;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import spaland.users.model.User;
import spaland.users.vo.*;

public interface IUserService {

    User singup(SignupRequest signupRequest);
    ResponseUser getUser(Long id);
    LoginResponse login(LoginRequest loginRequest);
    LogoutResponse logout(LogoutRequest logoutRequest);

    boolean checkDuplicateId(String userId);
}
