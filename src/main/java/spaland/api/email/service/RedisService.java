package spaland.api.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisService {

    private final int LIMIT_TIME = 3 * 60;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void createConfirmCodeByEmail(String email, String confirmCode) {

        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(email, confirmCode, Duration.ofSeconds(LIMIT_TIME));

    }

    public void createBlacklistToken(String accessToken, Long expiration) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);
    }

    public String getConfirmCodeByEmail(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    public String getEmailByRefreshToken(String refreshToken) {
        return redisTemplate.opsForValue().get(refreshToken);
    }

    public void removeConfirmCodeByEmail(String email) {
        redisTemplate.delete(email);
    }

    public boolean hasKey(String email) {
        return redisTemplate.hasKey(email);
    }


}
