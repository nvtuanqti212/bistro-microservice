package com.bistrocheese.orderservice.service.impl;


import com.bistrocheese.orderservice.client.UserFeignClient;
import com.bistrocheese.orderservice.constant.APIStatus;
import com.bistrocheese.orderservice.dto.request.order.OrderRequest;
import com.bistrocheese.orderservice.exception.CustomException;
import com.bistrocheese.orderservice.model.*;
import com.bistrocheese.orderservice.repository.OrderLineRepository;
import com.bistrocheese.orderservice.repository.OrderRepository;
import com.bistrocheese.orderservice.repository.OrderTableRepository;
import com.bistrocheese.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderTableRepository orderTableRepository;
//    private final WebClient webClient;
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final UserFeignClient userFeignClient;

//    @Override
//    public List<OrderResponse> getOrders() {
//        var staff = getStaff();
//        List<OrderResponse> orderResponseList = new ArrayList<>();
//
//        List<OrderTable> orderTableList = orderTableRepository.findAll();
//
//        //method get all orders
//        getAllOrders(orderResponseList, orderTableList, staff);
//        return orderResponseList;
//    }

    @Override
    public void createOrder(OrderRequest orderRequest, String staffId) {

//        Boolean isValidStaffId = webClient.get()
//                .uri("http://userservice/api/v1/users/" + staffId + "/orders")
//                .retrieve()
//                .bodyToMono(Boolean.class)
//                .block();
        User user = userFeignClient.getUser(staffId);

//        if (isValidStaffId == null || !isValidStaffId) {
//            throw new CustomException(APIStatus.ORDER_NOT_FOUND);
//        }
        if (user == null) {
            throw new CustomException(APIStatus.ORDER_NOT_FOUND);
        }

        logger.info("order created by: {}", user.toString());

        OrderTable orderTable = orderTableRepository.findById(orderRequest.getTableId()).orElseThrow(
                () -> new CustomException(APIStatus.ORDER_TABLE_NOT_FOUND)
        );
        if (!orderTable.getTableStatus().equals(TableStatus.EMPTY)) {
            throw new CustomException(APIStatus.ORDER_TABLE_NOT_EMPTY);
        }
        orderTableRepository.updateTableStatusById(TableStatus.OCCUPIED, orderTable.getId());
        Order newOrder = Order.builder()
                .staffId(user.getId())
                .orderTable(orderTable)
                .status(OrderStatus.PENDING)
                .build();
        logger.info("order: {}", newOrder.toString());

        orderRepository.save(newOrder);
    }



//    @Override
//    public void deleteOrder(UUID orderId) {
//        Order order = orderRepository.findById(orderId).orElseThrow(
//                () -> new CustomException(APIStatus.ORDER_NOT_FOUND)
//        );
//
//        List<OrderLine> orderLineList = orderLineRepository.findByOrder_Id(orderId);
//
//        //add food quantity back to inventory
//
//        orderLineList.stream().map(OrderLine::getId).forEach(orderLineRepository::deleteById);
//        orderRepository.deleteById(orderId);
//        orderTableRepository.updateTableStatusById(TableStatus.EMPTY, order.getOrderTable().getId());
//    }

}
