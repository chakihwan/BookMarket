package kr.ac.kopo.kihwan.bookmarket.repository;

import kr.ac.kopo.kihwan.bookmarket.domain.Cart;

public interface CartRepository {
    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
    void delete(String cartId); //카트안에 전체 항목 일괄 삭제
}
