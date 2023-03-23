package spaland.giftCard.model;

import jakarta.persistence.*;
import lombok.*;
import spaland.users.model.User;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class UserGiftCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private GiftCard giftCard;
}