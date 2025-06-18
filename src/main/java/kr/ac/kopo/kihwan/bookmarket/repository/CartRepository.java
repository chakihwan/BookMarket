package kr.ac.kopo.kihwan.bookmarket.repository;

import kr.ac.kopo.kihwan.bookmarket.domain.Cart;

public interface CartRepository {
    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
    void delete(String cartId); //전체 카트 삭제
}
