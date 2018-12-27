package com.sweetitech.ird.repository;

import com.sweetitech.ird.domain.ACR;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 5:59 PM
 * @project InternalResourcesDivision
 */
public interface AcrRepository extends JpaRepository<ACR, Long> {

    Page<ACR> findByYearOrderByCreatedOnDesc(String year, Pageable page);
}
