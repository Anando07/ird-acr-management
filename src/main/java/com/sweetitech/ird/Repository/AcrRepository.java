package com.sweetitech.ird.Repository;

import com.sweetitech.ird.Domain.ACR;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 5:59 PM
 * @project InternalResourcesDivision
 */
public interface AcrRepository extends JpaRepository<ACR, Long> {

}
