package com.softwareTesting.online_converter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    @Query("SELECT e.exchangeRate FROM exchange_rate e WHERE e.fromCurrencyName = :fromCurrencyName AND e.toCurrencyName = :toCurrencyName")
    Optional<Double> findExchangeRateByFromAndToCurrencyNames(@Param("fromCurrencyName") String fromCurrencyName, @Param("toCurrencyName") String toCurrencyName);
}

