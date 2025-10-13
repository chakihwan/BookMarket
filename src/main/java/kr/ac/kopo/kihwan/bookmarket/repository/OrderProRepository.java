package kr.ac.kopo.kihwan.bookmarket.repository;

import kr.ac.kopo.kihwan.bookmarket.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProRepository extends JpaRepository<Order, Long> {
}
