package com.vnsd.internal.repository;

import com.vnsd.internal.domain.OrganizationEmployee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrganizationEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizationEmployeeRepository extends JpaRepository<OrganizationEmployee, Long> {

}
