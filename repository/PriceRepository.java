package com.example.binance.repository;

import com.example.binance.entity.Code;
import com.example.binance.entity.Price;
import com.example.binance.entity.PriceId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {
    List<Price> findAllByTimeLessThan(LocalDateTime time); // по времени до

    Optional<Price> findFirstByCodeOrderByTimeDesc(Code code); // самую последнюю цену , чтоб не плодить дубликаты

    void deleteAllByTimeLessThan(LocalDateTime time);

    List<Price>  findPriceByCode_Id(Long code_id);

    @Query(value="SELECT u.* FROM prices u where u.code_id = 12 ORDER BY u.time DESC;",
            nativeQuery=true)
    List<Price> findAllByCode_Id(Long code_id);

    List<Price> findAllBy(Pageable pagebale);
}
