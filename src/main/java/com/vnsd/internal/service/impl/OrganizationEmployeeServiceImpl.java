package com.vnsd.internal.service.impl;

import com.vnsd.internal.service.OrganizationEmployeeService;
import com.vnsd.internal.domain.OrganizationEmployee;
import com.vnsd.internal.repository.OrganizationEmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link OrganizationEmployee}.
 */
@Service
@Transactional
public class OrganizationEmployeeServiceImpl implements OrganizationEmployeeService {

    private final Logger log = LoggerFactory.getLogger(OrganizationEmployeeServiceImpl.class);

    private final OrganizationEmployeeRepository organizationEmployeeRepository;

    public OrganizationEmployeeServiceImpl(OrganizationEmployeeRepository organizationEmployeeRepository) {
        this.organizationEmployeeRepository = organizationEmployeeRepository;
    }

    /**
     * Save a organizationEmployee.
     *
     * @param organizationEmployee the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OrganizationEmployee save(OrganizationEmployee organizationEmployee) {
        log.debug("Request to save OrganizationEmployee : {}", organizationEmployee);
        return organizationEmployeeRepository.save(organizationEmployee);
    }

    /**
     * Get all the organizationEmployees.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationEmployee> findAll() {
        log.debug("Request to get all OrganizationEmployees");
        return organizationEmployeeRepository.findAll();
    }


    /**
     * Get one organizationEmployee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrganizationEmployee> findOne(Long id) {
        log.debug("Request to get OrganizationEmployee : {}", id);
        return organizationEmployeeRepository.findById(id);
    }

    /**
     * Delete the organizationEmployee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrganizationEmployee : {}", id);
        organizationEmployeeRepository.deleteById(id);
    }
}
