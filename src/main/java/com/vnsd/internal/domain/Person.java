package com.vnsd.internal.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @NotNull
    @Column(name = "permalink", nullable = false)
    private String permalink;

    @NotNull
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotNull
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "alsoknownas")
    private String alsoknownas;

    @Column(name = "bio")
    private String bio;

    @Column(name = "profileimageid")
    private Integer profileimageid;

    @Column(name = "roleinvestor")
    private Boolean roleinvestor;

    @Column(name = "bornon")
    private Instant bornon;

    @Column(name = "bornontrustcode")
    private Integer bornontrustcode;

    @Column(name = "diedon")
    private Instant diedon;

    @Column(name = "diedontrustcode")
    private Integer diedontrustcode;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @Column(name = "permalinkaliases")
    private String permalinkaliases;

    @Column(name = "gender")
    private String gender;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "primaryaffiliationid")
    private Integer primaryaffiliationid;

    @Column(name = "primarylocationid")
    private Integer primarylocationid;

    @Column(name = "primaryimageid")
    private Integer primaryimageid;

    @Column(name = "title")
    private String title;

    @Column(name = "homepageurl")
    private String homepageurl;

    @Column(name = "facebookurl")
    private String facebookurl;

    @Column(name = "twitterurl")
    private String twitterurl;

    @Column(name = "linkedinurl")
    private String linkedinurl;

    @Column(name = "cityname")
    private String cityname;

    @Column(name = "regionname")
    private String regionname;

    @Column(name = "countrycode")
    private String countrycode;

    @ManyToOne
    @JsonIgnoreProperties("people")
    private Organization organization;

    @ManyToOne
    @JsonIgnoreProperties("people")
    private OrganizationEmployee organizationEmployee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public Person uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPermalink() {
        return permalink;
    }

    public Person permalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getFirstname() {
        return firstname;
    }

    public Person firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Person lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAlsoknownas() {
        return alsoknownas;
    }

    public Person alsoknownas(String alsoknownas) {
        this.alsoknownas = alsoknownas;
        return this;
    }

    public void setAlsoknownas(String alsoknownas) {
        this.alsoknownas = alsoknownas;
    }

    public String getBio() {
        return bio;
    }

    public Person bio(String bio) {
        this.bio = bio;
        return this;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getProfileimageid() {
        return profileimageid;
    }

    public Person profileimageid(Integer profileimageid) {
        this.profileimageid = profileimageid;
        return this;
    }

    public void setProfileimageid(Integer profileimageid) {
        this.profileimageid = profileimageid;
    }

    public Boolean isRoleinvestor() {
        return roleinvestor;
    }

    public Person roleinvestor(Boolean roleinvestor) {
        this.roleinvestor = roleinvestor;
        return this;
    }

    public void setRoleinvestor(Boolean roleinvestor) {
        this.roleinvestor = roleinvestor;
    }

    public Instant getBornon() {
        return bornon;
    }

    public Person bornon(Instant bornon) {
        this.bornon = bornon;
        return this;
    }

    public void setBornon(Instant bornon) {
        this.bornon = bornon;
    }

    public Integer getBornontrustcode() {
        return bornontrustcode;
    }

    public Person bornontrustcode(Integer bornontrustcode) {
        this.bornontrustcode = bornontrustcode;
        return this;
    }

    public void setBornontrustcode(Integer bornontrustcode) {
        this.bornontrustcode = bornontrustcode;
    }

    public Instant getDiedon() {
        return diedon;
    }

    public Person diedon(Instant diedon) {
        this.diedon = diedon;
        return this;
    }

    public void setDiedon(Instant diedon) {
        this.diedon = diedon;
    }

    public Integer getDiedontrustcode() {
        return diedontrustcode;
    }

    public Person diedontrustcode(Integer diedontrustcode) {
        this.diedontrustcode = diedontrustcode;
        return this;
    }

    public void setDiedontrustcode(Integer diedontrustcode) {
        this.diedontrustcode = diedontrustcode;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Person createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Person updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public String getPermalinkaliases() {
        return permalinkaliases;
    }

    public Person permalinkaliases(String permalinkaliases) {
        this.permalinkaliases = permalinkaliases;
        return this;
    }

    public void setPermalinkaliases(String permalinkaliases) {
        this.permalinkaliases = permalinkaliases;
    }

    public String getGender() {
        return gender;
    }

    public Person gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getRank() {
        return rank;
    }

    public Person rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPrimaryaffiliationid() {
        return primaryaffiliationid;
    }

    public Person primaryaffiliationid(Integer primaryaffiliationid) {
        this.primaryaffiliationid = primaryaffiliationid;
        return this;
    }

    public void setPrimaryaffiliationid(Integer primaryaffiliationid) {
        this.primaryaffiliationid = primaryaffiliationid;
    }

    public Integer getPrimarylocationid() {
        return primarylocationid;
    }

    public Person primarylocationid(Integer primarylocationid) {
        this.primarylocationid = primarylocationid;
        return this;
    }

    public void setPrimarylocationid(Integer primarylocationid) {
        this.primarylocationid = primarylocationid;
    }

    public Integer getPrimaryimageid() {
        return primaryimageid;
    }

    public Person primaryimageid(Integer primaryimageid) {
        this.primaryimageid = primaryimageid;
        return this;
    }

    public void setPrimaryimageid(Integer primaryimageid) {
        this.primaryimageid = primaryimageid;
    }

    public String getTitle() {
        return title;
    }

    public Person title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHomepageurl() {
        return homepageurl;
    }

    public Person homepageurl(String homepageurl) {
        this.homepageurl = homepageurl;
        return this;
    }

    public void setHomepageurl(String homepageurl) {
        this.homepageurl = homepageurl;
    }

    public String getFacebookurl() {
        return facebookurl;
    }

    public Person facebookurl(String facebookurl) {
        this.facebookurl = facebookurl;
        return this;
    }

    public void setFacebookurl(String facebookurl) {
        this.facebookurl = facebookurl;
    }

    public String getTwitterurl() {
        return twitterurl;
    }

    public Person twitterurl(String twitterurl) {
        this.twitterurl = twitterurl;
        return this;
    }

    public void setTwitterurl(String twitterurl) {
        this.twitterurl = twitterurl;
    }

    public String getLinkedinurl() {
        return linkedinurl;
    }

    public Person linkedinurl(String linkedinurl) {
        this.linkedinurl = linkedinurl;
        return this;
    }

    public void setLinkedinurl(String linkedinurl) {
        this.linkedinurl = linkedinurl;
    }

    public String getCityname() {
        return cityname;
    }

    public Person cityname(String cityname) {
        this.cityname = cityname;
        return this;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getRegionname() {
        return regionname;
    }

    public Person regionname(String regionname) {
        this.regionname = regionname;
        return this;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public Person countrycode(String countrycode) {
        this.countrycode = countrycode;
        return this;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Person organization(Organization organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public OrganizationEmployee getOrganizationEmployee() {
        return organizationEmployee;
    }

    public Person organizationEmployee(OrganizationEmployee organizationEmployee) {
        this.organizationEmployee = organizationEmployee;
        return this;
    }

    public void setOrganizationEmployee(OrganizationEmployee organizationEmployee) {
        this.organizationEmployee = organizationEmployee;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        return id != null && id.equals(((Person) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", permalink='" + getPermalink() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", alsoknownas='" + getAlsoknownas() + "'" +
            ", bio='" + getBio() + "'" +
            ", profileimageid=" + getProfileimageid() +
            ", roleinvestor='" + isRoleinvestor() + "'" +
            ", bornon='" + getBornon() + "'" +
            ", bornontrustcode=" + getBornontrustcode() +
            ", diedon='" + getDiedon() + "'" +
            ", diedontrustcode=" + getDiedontrustcode() +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            ", permalinkaliases='" + getPermalinkaliases() + "'" +
            ", gender='" + getGender() + "'" +
            ", rank=" + getRank() +
            ", primaryaffiliationid=" + getPrimaryaffiliationid() +
            ", primarylocationid=" + getPrimarylocationid() +
            ", primaryimageid=" + getPrimaryimageid() +
            ", title='" + getTitle() + "'" +
            ", homepageurl='" + getHomepageurl() + "'" +
            ", facebookurl='" + getFacebookurl() + "'" +
            ", twitterurl='" + getTwitterurl() + "'" +
            ", linkedinurl='" + getLinkedinurl() + "'" +
            ", cityname='" + getCityname() + "'" +
            ", regionname='" + getRegionname() + "'" +
            ", countrycode='" + getCountrycode() + "'" +
            "}";
    }
}
