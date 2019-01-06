package com.sweetitech.ird.repository;

import com.sweetitech.ird.domain.AcrFileRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/27/18 at 10:58 AM
 * @project ird
 */
public interface AcrFileRelationRepository extends JpaRepository<AcrFileRelation, Long> {

    List<AcrFileRelation> findByAcr_Id(Long acrId);

}
