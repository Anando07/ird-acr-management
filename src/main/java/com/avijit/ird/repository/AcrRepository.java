package com.avijit.ird.repository;

import com.avijit.ird.domain.ACR;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 5:59 PM
 * @project InternalResourcesDivision
 */
public interface AcrRepository extends JpaRepository<ACR, Long> {

    Page<ACR> findByYearOrderByCreatedOnDesc(String year, Pageable page);

    List<ACR> findByYearOrderByCreatedOnDesc(String year);

    @Query(value = "SELECT * from acr WHERE year != :year",nativeQuery = true)
    List<ACR>acrOfOldYear(@Param("year") String year);

    List<ACR> findByGovtIdOrderByCreatedOnDesc(String govtId);

    List<ACR> findByGovtIdAndYearOrderByYearDesc(String govtId, String year);

    List<ACR> findByGovtIdOrderByYearDesc(String govtId);

    List<ACR> findAllByDepartmentIdOrderByYearDesc(Long deptId);

    List<ACR> findAllByYearAndDepartmentIdOrderByGovtId(String year, Long deptId);

}
