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




    /*List<ACR> findByGovtIdAndYearOrderByYearDesc(String govtId, String year);*/

    @Query(value = "SELECT * FROM acr WHERE govt_id =:govtId AND is_deleted IS FALSE AND year =:year AND (acr_required_type IS TRUE OR acr_required_type IS NULL) ORDER BY year DESC", nativeQuery = true)
    List<ACR> findRequiredACRbyGovtIdAndYear(@Param("govtId")String govtId, @Param("year") String year);


    @Query(value = "SELECT * FROM acr where govt_id =:govtId ORDER BY year DESC,assigned_from DESC", nativeQuery = true)
    List<ACR> findByGovtIdOrderByYearDesc(@Param("govtId")String govtId);


    @Query(value = "SELECT * FROM acr WHERE govt_id =:govtId AND is_deleted IS FALSE AND (acr_required_type IS TRUE OR acr_required_type IS NULL) ORDER BY year DESC", nativeQuery = true)
    List<ACR> findAllRequiredACRbyGovtId(@Param("govtId") String govtId);




    List<ACR> findAllByGovtIdAndAcrRequiredTypeFalseAndIsDeletedFalseOrderByYearDesc(String govtId);

    List<ACR> findAllByDepartmentIdOrderByYearDesc(Long deptId);

    List<ACR> findAllByYearAndDepartmentIdOrderByGovtId(String year, Long deptId);

}
