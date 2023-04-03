package spaland.zincomplete.giftbox.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseGetUserGiftbox {
    private Long id;
    private Long userId;
    private Long productId;

}