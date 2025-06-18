package kr.ac.kopo.kihwan.bookmarket.service;

import kr.ac.kopo.kihwan.bookmarket.domain.Cart;

public interface CartService {
    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
}
