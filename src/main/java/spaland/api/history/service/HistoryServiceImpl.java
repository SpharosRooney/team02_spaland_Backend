package spaland.api.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spaland.Response.Message;
import spaland.api.history.vo.RequestHistory;
import spaland.exception.CustomException;
import spaland.api.history.dto.ResponseHistoryDTO;
import spaland.api.history.dto.ResponseHistoryDetailDTO;
import spaland.api.history.model.History;
import spaland.api.history.repository.IHistoryRepository;
import spaland.api.products.model.Product;
import spaland.api.products.repository.IProductRepository;
import spaland.api.shipping.model.UserShippingAddress;
import spaland.api.shipping.repository.IUserShippingAddressRepository;
import spaland.api.users.model.User;
import spaland.api.users.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements IHistoryService{

    private final IHistoryRepository iHistoryRepository;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final IUserShippingAddressRepository iUserShippingAddressRepository;

    @Override
    public ResponseEntity<Message> addHistory(RequestHistory requestHistory, String userId) {
        User user = iUserRepository.findByUserId(userId).orElseThrow(()->new CustomException(INVALID_ACCESS));

        iHistoryRepository.save(History.builder()
                .user(iUserRepository.findById(user.getId()).orElseThrow(()->new CustomException(INVALID_MEMBER_USER)))
                .product(iProductRepository.findById(requestHistory.getProductId()).orElseThrow(()->new CustomException(INVALID_PRODUCT)))
                .userShippingAddress(iUserShippingAddressRepository.findById(requestHistory.getUserShippingAddressId()).orElseThrow(() -> new CustomException(INVALID_MEMBER_SHIPPING)))
                .historyNum("asd")
                .currentState(0L)
                .amount(requestHistory.getAmount())
                .paymentType(requestHistory.getPaymentType())
                .build());

        Message message = new Message();
        message.setMessage("주문 성공");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> getHistory(Integer historyId, String userId) {
        iHistoryRepository.findById(historyId).orElseThrow(() -> new CustomException(INVALID_MEMBER_HISTORY));
        History history = iHistoryRepository.findById(historyId).get();
        Product product = iHistoryRepository.findById(historyId).get().getProduct();
        UserShippingAddress usa = iHistoryRepository.findById(historyId).get().getUserShippingAddress();

        ResponseHistoryDetailDTO responseHistoryDTO = ResponseHistoryDetailDTO.builder()
                .productId(product.getId())
                .productName(product.getName())
                .productPrice(product.getPrice())
                .productTitleImg(product.getTitleImg())
                .zipCode(usa.getZipCode())
                .address(usa.getAddress())
                .detailAddress(usa.getDetailAddress())
                .shippingPhone(usa.getShippingPhone())
                .currentState(history.getCurrentState())
                .paymentType(history.getPaymentType())
                .amount(history.getAmount())
                .totalPrice(history.getAmount() * history.getAmount())
                .build();

        Message message = new Message();
        message.setMessage("장바구니 상세 조회 성공");
        message.setData(responseHistoryDTO);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> findAll(String userId) {
        User user = iUserRepository.findByUserId(userId).orElseThrow(()->new CustomException(INVALID_ACCESS));
        List<History> historyList = iHistoryRepository.findAllByUser(user);
        List<ResponseHistoryDTO> responseHistoryDTOList = new ArrayList<>();

        historyList.forEach(
                history -> {
                    responseHistoryDTOList.add(
                            ResponseHistoryDTO.builder()
                                    .productId(history.getProduct().getId())
                                    .productName(history.getProduct().getName())
                                    .productTitleImg(history.getProduct().getTitleImg())
                                    .amount(history.getAmount())
                                    .totalPrice(history.getAmount() * history.getAmount())
                                    .currentState(history.getCurrentState())
                                    .build()
                    );
                }
        );

        Message message = new Message();
        message.setData(responseHistoryDTOList);
        message.setMessage("장바구니 전체 조회 성공");
        
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
