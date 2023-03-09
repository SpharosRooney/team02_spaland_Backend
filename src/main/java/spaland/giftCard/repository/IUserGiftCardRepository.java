package spaland.giftCard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.giftCard.model.UserGiftCard;

import java.util.List;

public interface IUserGiftCardRepository extends JpaRepository<UserGiftCard, Long> {

    UserGiftCard save(UserGiftCard userGiftCard);

    UserGiftCard findByUserIdAndGiftCardId(Long userId, Long cardId);

    List<UserGiftCard> findAllByUserId(Long userId);
}