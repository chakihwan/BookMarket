package kr.ac.kopo.kihwan.bookmarket.repository;

import kr.ac.kopo.kihwan.bookmarket.domain.Order;

public interface OrderRepository {
//    주문목록 저장
    long saveOrder(Order order);
}
