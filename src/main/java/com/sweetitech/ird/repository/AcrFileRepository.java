package com.sweetitech.ird.repository;

import com.sweetitech.ird.domain.AcrFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Avijit Barua
 * @created_on 12/26/18 at 1:02 PM
 * @project ird
 */
public interface AcrFileRepository extends JpaRepository<AcrFile, Long> {

    AcrFile findById (long id);

    AcrFile findByUrl(String url);

    AcrFile getFirstById(long id);
}
