package spaland.api.shipping.vo;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEditUserShippingAddress {

    private String zipCode;
    private String address;
    private String detailAddress;
    private Boolean isUse;
    private String shippingPhone;
}
