package com.vnsd.internal.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A OrganizationEmployee.
 */
@Entity
@Table(name = "organization_employee")
public class OrganizationEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "organizationEmployee")
    private Set<Organization> organizations = new HashSet<>();

    @OneToMany(mappedBy = "organizationEmployee")
    private Set<Person> people = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public OrganizationEmployee role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Organization> getOrganizations() {
        return organizations;
    }

    public OrganizationEmployee organizations(Set<Organization> organizations) {
        this.organizations = organizations;
        return this;
    }

    public OrganizationEmployee addOrganization(Organization organization) {
        this.organizations.add(organization);
        organization.setOrganizationEmployee(this);
        return this;
    }

    public OrganizationEmployee removeOrganization(Organization organization) {
        this.organizations.remove(organization);
        organization.setOrganizationEmployee(null);
        return this;
    }

    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public OrganizationEmployee people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public OrganizationEmployee addPerson(Person person) {
        this.people.add(person);
        person.setOrganizationEmployee(this);
        return this;
    }

    public OrganizationEmployee removePerson(Person person) {
        this.people.remove(person);
        person.setOrganizationEmployee(null);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganizationEmployee)) {
            return false;
        }
        return id != null && id.equals(((OrganizationEmployee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrganizationEmployee{" +
            "id=" + getId() +
            ", role='" + getRole() + "'" +
            "}";
    }
}
