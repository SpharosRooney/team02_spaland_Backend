package spaland.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spaland.exception.CustomException;
import spaland.products.model.Product;
import spaland.products.model.ProductCategoryList;
import spaland.products.repository.*;
import spaland.products.vo.RequestCategoryList;
import spaland.products.vo.ResponseProduct;

import java.util.ArrayList;
import java.util.List;

import static spaland.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductCategoryListServiceImple implements IProductCategoryListService {

    private final IProductCategoryListRepository iProductCategoryListRepository;
    private final IProductRepository iProductRepository;
    private final ICategoryLargeRepository iCategoryLargeRepository;
    private final ICategoryMiddleRepository iCategoryMiddleRepository;
    private final IProductOptionRepository iProductOptionRepository;
    private final IEventRepository iEventRepository;

    @Override
    public void addProductCategoryList(RequestCategoryList requestCategoryList) {

        iProductCategoryListRepository.save(
                ProductCategoryList.builder()
                        .categoryLarge(iCategoryLargeRepository.findById(requestCategoryList.getCategoryLargeId()).orElseThrow(() -> new CustomException(INVALID_CATEGORY)))
                        .categoryMiddle(iCategoryMiddleRepository.findById(requestCategoryList.getCategoryMiddleId()).orElseThrow(() -> new CustomException(INVALID_CATEGORY)))
                        .product(iProductRepository.findById(requestCategoryList.getProductId()).orElseThrow(() -> new CustomException(INVALID_PRODUCT)))
                        .productOption(iProductOptionRepository.findById(requestCategoryList.getProductOptionId()).orElseThrow(() -> new CustomException(INVALID_OPTION)))
                        .event(iEventRepository.findById(requestCategoryList.getEventId()).orElseThrow(() -> new CustomException(INVALID_EVENT)))
                        .build()
        );

    }

    @Override
    public List<ResponseProduct> getByCategoryLargeId(Integer categoryLargeId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByCategoryLargeId(categoryLargeId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
                productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(),ResponseProduct.class));
                });

        return responseProduct;
    }

    @Override
    public List<ResponseProduct> getByCategoryMiddleId(Integer categoryMiddleId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByCategoryMiddleId(categoryMiddleId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
        productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(),ResponseProduct.class));
                });

        return responseProduct;
    }

    @Override
    public List<ResponseProduct> getByProductOptionId(Integer productOptionId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByProductOptionId(productOptionId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
        productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(),ResponseProduct.class));
                });

        return responseProduct;
    }

    @Override
    public List<ResponseProduct> getByEventId(Integer eventId) {
        List<ProductCategoryList> productCategoryLists = iProductCategoryListRepository.findByEventId(eventId);
        List<ResponseProduct> responseProduct = new ArrayList<>();
        productCategoryLists.forEach(
                productCategoryList -> {
                    ModelMapper modelMapper = new ModelMapper();
                    responseProduct.add(modelMapper.map(productCategoryList.getProduct(),ResponseProduct.class));
                });

        return responseProduct;
    }

    @Override
    public List<Product> findAllByFilter(Specification<ProductCategoryList> spec) {
        List<ProductCategoryList> all = iProductCategoryListRepository.findAll(spec);
        List<Product> productList = new ArrayList<Product>();
        for (ProductCategoryList iter : all) {
            productList.add(iter.getProduct());
        }
        return productList;
    }
}
