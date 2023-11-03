package com.bistrocheese.orderservice.repository;

import com.bistrocheese.orderservice.model.OrderTable;
import com.bistrocheese.orderservice.model.TableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface OrderTableRepository extends JpaRepository<OrderTable, Long>{
        @Transactional
        @Modifying
        @Query("update OrderTable o set o.tableStatus = ?1 where o.id = ?2")
        void updateTableStatusById(TableStatus tableStatus, Long id);
}
