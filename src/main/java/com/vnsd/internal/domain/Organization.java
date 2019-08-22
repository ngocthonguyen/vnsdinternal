package com.vnsd.internal.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Organization.
 */
@Entity
@Table(name = "organization")
public class Organization implements Serializable {

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
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "alsoknownas")
    private String alsoknownas;

    @Column(name = "shortdescription")
    private String shortdescription;

    @Column(name = "description")
    private String description;

    @Column(name = "profileimageid")
    private Integer profileimageid;

    @Column(name = "primaryrole")
    private String primaryrole;

    @Column(name = "rolecompany")
    private Boolean rolecompany;

    @Column(name = "roleinvestor")
    private Boolean roleinvestor;

    @Column(name = "rolegroup")
    private Boolean rolegroup;

    @Column(name = "roleschool")
    private Boolean roleschool;

    @Column(name = "foundedon")
    private Instant foundedon;

    @Column(name = "foundedontrustcode")
    private Integer foundedontrustcode;

    @Column(name = "closedon")
    private Instant closedon;

    @Column(name = "closedontrustcode")
    private Integer closedontrustcode;

    @Column(name = "numemployeesmin")
    private Integer numemployeesmin;

    @Column(name = "numemployeesmax")
    private Integer numemployeesmax;

    @Column(name = "totalfundingusd")
    private Integer totalfundingusd;

    @Column(name = "totalfundingvnd")
    private Integer totalfundingvnd;

    @Column(name = "stockexchange")
    private String stockexchange;

    @Column(name = "stocksymbol")
    private String stocksymbol;

    @Column(name = "numberofinvestments")
    private Integer numberofinvestments;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @Column(name = "permalinkaliases")
    private String permalinkaliases;

    @Column(name = "investortype")
    private String investortype;

    @Column(name = "contactemail")
    private String contactemail;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "primaryimageid")
    private Integer primaryimageid;

    @Column(name = "ownedbyid")
    private Integer ownedbyid;

    @Column(name = "headquartersid")
    private Integer headquartersid;

    @Column(name = "acquiredbyid")
    private Integer acquiredbyid;

    @Column(name = "ipoid")
    private Integer ipoid;

    @Column(name = "domain")
    private String domain;

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

    @OneToMany(mappedBy = "organization")
    private Set<Person> owners = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("organizations")
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

    public Organization uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPermalink() {
        return permalink;
    }

    public Organization permalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getName() {
        return name;
    }

    public Organization name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlsoknownas() {
        return alsoknownas;
    }

    public Organization alsoknownas(String alsoknownas) {
        this.alsoknownas = alsoknownas;
        return this;
    }

    public void setAlsoknownas(String alsoknownas) {
        this.alsoknownas = alsoknownas;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public Organization shortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
        return this;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getDescription() {
        return description;
    }

    public Organization description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProfileimageid() {
        return profileimageid;
    }

    public Organization profileimageid(Integer profileimageid) {
        this.profileimageid = profileimageid;
        return this;
    }

    public void setProfileimageid(Integer profileimageid) {
        this.profileimageid = profileimageid;
    }

    public String getPrimaryrole() {
        return primaryrole;
    }

    public Organization primaryrole(String primaryrole) {
        this.primaryrole = primaryrole;
        return this;
    }

    public void setPrimaryrole(String primaryrole) {
        this.primaryrole = primaryrole;
    }

    public Boolean isRolecompany() {
        return rolecompany;
    }

    public Organization rolecompany(Boolean rolecompany) {
        this.rolecompany = rolecompany;
        return this;
    }

    public void setRolecompany(Boolean rolecompany) {
        this.rolecompany = rolecompany;
    }

    public Boolean isRoleinvestor() {
        return roleinvestor;
    }

    public Organization roleinvestor(Boolean roleinvestor) {
        this.roleinvestor = roleinvestor;
        return this;
    }

    public void setRoleinvestor(Boolean roleinvestor) {
        this.roleinvestor = roleinvestor;
    }

    public Boolean isRolegroup() {
        return rolegroup;
    }

    public Organization rolegroup(Boolean rolegroup) {
        this.rolegroup = rolegroup;
        return this;
    }

    public void setRolegroup(Boolean rolegroup) {
        this.rolegroup = rolegroup;
    }

    public Boolean isRoleschool() {
        return roleschool;
    }

    public Organization roleschool(Boolean roleschool) {
        this.roleschool = roleschool;
        return this;
    }

    public void setRoleschool(Boolean roleschool) {
        this.roleschool = roleschool;
    }

    public Instant getFoundedon() {
        return foundedon;
    }

    public Organization foundedon(Instant foundedon) {
        this.foundedon = foundedon;
        return this;
    }

    public void setFoundedon(Instant foundedon) {
        this.foundedon = foundedon;
    }

    public Integer getFoundedontrustcode() {
        return foundedontrustcode;
    }

    public Organization foundedontrustcode(Integer foundedontrustcode) {
        this.foundedontrustcode = foundedontrustcode;
        return this;
    }

    public void setFoundedontrustcode(Integer foundedontrustcode) {
        this.foundedontrustcode = foundedontrustcode;
    }

    public Instant getClosedon() {
        return closedon;
    }

    public Organization closedon(Instant closedon) {
        this.closedon = closedon;
        return this;
    }

    public void setClosedon(Instant closedon) {
        this.closedon = closedon;
    }

    public Integer getClosedontrustcode() {
        return closedontrustcode;
    }

    public Organization closedontrustcode(Integer closedontrustcode) {
        this.closedontrustcode = closedontrustcode;
        return this;
    }

    public void setClosedontrustcode(Integer closedontrustcode) {
        this.closedontrustcode = closedontrustcode;
    }

    public Integer getNumemployeesmin() {
        return numemployeesmin;
    }

    public Organization numemployeesmin(Integer numemployeesmin) {
        this.numemployeesmin = numemployeesmin;
        return this;
    }

    public void setNumemployeesmin(Integer numemployeesmin) {
        this.numemployeesmin = numemployeesmin;
    }

    public Integer getNumemployeesmax() {
        return numemployeesmax;
    }

    public Organization numemployeesmax(Integer numemployeesmax) {
        this.numemployeesmax = numemployeesmax;
        return this;
    }

    public void setNumemployeesmax(Integer numemployeesmax) {
        this.numemployeesmax = numemployeesmax;
    }

    public Integer getTotalfundingusd() {
        return totalfundingusd;
    }

    public Organization totalfundingusd(Integer totalfundingusd) {
        this.totalfundingusd = totalfundingusd;
        return this;
    }

    public void setTotalfundingusd(Integer totalfundingusd) {
        this.totalfundingusd = totalfundingusd;
    }

    public Integer getTotalfundingvnd() {
        return totalfundingvnd;
    }

    public Organization totalfundingvnd(Integer totalfundingvnd) {
        this.totalfundingvnd = totalfundingvnd;
        return this;
    }

    public void setTotalfundingvnd(Integer totalfundingvnd) {
        this.totalfundingvnd = totalfundingvnd;
    }

    public String getStockexchange() {
        return stockexchange;
    }

    public Organization stockexchange(String stockexchange) {
        this.stockexchange = stockexchange;
        return this;
    }

    public void setStockexchange(String stockexchange) {
        this.stockexchange = stockexchange;
    }

    public String getStocksymbol() {
        return stocksymbol;
    }

    public Organization stocksymbol(String stocksymbol) {
        this.stocksymbol = stocksymbol;
        return this;
    }

    public void setStocksymbol(String stocksymbol) {
        this.stocksymbol = stocksymbol;
    }

    public Integer getNumberofinvestments() {
        return numberofinvestments;
    }

    public Organization numberofinvestments(Integer numberofinvestments) {
        this.numberofinvestments = numberofinvestments;
        return this;
    }

    public void setNumberofinvestments(Integer numberofinvestments) {
        this.numberofinvestments = numberofinvestments;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Organization createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Organization updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public String getPermalinkaliases() {
        return permalinkaliases;
    }

    public Organization permalinkaliases(String permalinkaliases) {
        this.permalinkaliases = permalinkaliases;
        return this;
    }

    public void setPermalinkaliases(String permalinkaliases) {
        this.permalinkaliases = permalinkaliases;
    }

    public String getInvestortype() {
        return investortype;
    }

    public Organization investortype(String investortype) {
        this.investortype = investortype;
        return this;
    }

    public void setInvestortype(String investortype) {
        this.investortype = investortype;
    }

    public String getContactemail() {
        return contactemail;
    }

    public Organization contactemail(String contactemail) {
        this.contactemail = contactemail;
        return this;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public Organization phonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getRank() {
        return rank;
    }

    public Organization rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPrimaryimageid() {
        return primaryimageid;
    }

    public Organization primaryimageid(Integer primaryimageid) {
        this.primaryimageid = primaryimageid;
        return this;
    }

    public void setPrimaryimageid(Integer primaryimageid) {
        this.primaryimageid = primaryimageid;
    }

    public Integer getOwnedbyid() {
        return ownedbyid;
    }

    public Organization ownedbyid(Integer ownedbyid) {
        this.ownedbyid = ownedbyid;
        return this;
    }

    public void setOwnedbyid(Integer ownedbyid) {
        this.ownedbyid = ownedbyid;
    }

    public Integer getHeadquartersid() {
        return headquartersid;
    }

    public Organization headquartersid(Integer headquartersid) {
        this.headquartersid = headquartersid;
        return this;
    }

    public void setHeadquartersid(Integer headquartersid) {
        this.headquartersid = headquartersid;
    }

    public Integer getAcquiredbyid() {
        return acquiredbyid;
    }

    public Organization acquiredbyid(Integer acquiredbyid) {
        this.acquiredbyid = acquiredbyid;
        return this;
    }

    public void setAcquiredbyid(Integer acquiredbyid) {
        this.acquiredbyid = acquiredbyid;
    }

    public Integer getIpoid() {
        return ipoid;
    }

    public Organization ipoid(Integer ipoid) {
        this.ipoid = ipoid;
        return this;
    }

    public void setIpoid(Integer ipoid) {
        this.ipoid = ipoid;
    }

    public String getDomain() {
        return domain;
    }

    public Organization domain(String domain) {
        this.domain = domain;
        return this;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getHomepageurl() {
        return homepageurl;
    }

    public Organization homepageurl(String homepageurl) {
        this.homepageurl = homepageurl;
        return this;
    }

    public void setHomepageurl(String homepageurl) {
        this.homepageurl = homepageurl;
    }

    public String getFacebookurl() {
        return facebookurl;
    }

    public Organization facebookurl(String facebookurl) {
        this.facebookurl = facebookurl;
        return this;
    }

    public void setFacebookurl(String facebookurl) {
        this.facebookurl = facebookurl;
    }

    public String getTwitterurl() {
        return twitterurl;
    }

    public Organization twitterurl(String twitterurl) {
        this.twitterurl = twitterurl;
        return this;
    }

    public void setTwitterurl(String twitterurl) {
        this.twitterurl = twitterurl;
    }

    public String getLinkedinurl() {
        return linkedinurl;
    }

    public Organization linkedinurl(String linkedinurl) {
        this.linkedinurl = linkedinurl;
        return this;
    }

    public void setLinkedinurl(String linkedinurl) {
        this.linkedinurl = linkedinurl;
    }

    public String getCityname() {
        return cityname;
    }

    public Organization cityname(String cityname) {
        this.cityname = cityname;
        return this;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getRegionname() {
        return regionname;
    }

    public Organization regionname(String regionname) {
        this.regionname = regionname;
        return this;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public Organization countrycode(String countrycode) {
        this.countrycode = countrycode;
        return this;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public Set<Person> getOwners() {
        return owners;
    }

    public Organization owners(Set<Person> people) {
        this.owners = people;
        return this;
    }

    public Organization addOwner(Person person) {
        this.owners.add(person);
        person.setOrganization(this);
        return this;
    }

    public Organization removeOwner(Person person) {
        this.owners.remove(person);
        person.setOrganization(null);
        return this;
    }

    public void setOwners(Set<Person> people) {
        this.owners = people;
    }

    public OrganizationEmployee getOrganizationEmployee() {
        return organizationEmployee;
    }

    public Organization organizationEmployee(OrganizationEmployee organizationEmployee) {
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
        if (!(o instanceof Organization)) {
            return false;
        }
        return id != null && id.equals(((Organization) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Organization{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", permalink='" + getPermalink() + "'" +
            ", name='" + getName() + "'" +
            ", alsoknownas='" + getAlsoknownas() + "'" +
            ", shortdescription='" + getShortdescription() + "'" +
            ", description='" + getDescription() + "'" +
            ", profileimageid=" + getProfileimageid() +
            ", primaryrole='" + getPrimaryrole() + "'" +
            ", rolecompany='" + isRolecompany() + "'" +
            ", roleinvestor='" + isRoleinvestor() + "'" +
            ", rolegroup='" + isRolegroup() + "'" +
            ", roleschool='" + isRoleschool() + "'" +
            ", foundedon='" + getFoundedon() + "'" +
            ", foundedontrustcode=" + getFoundedontrustcode() +
            ", closedon='" + getClosedon() + "'" +
            ", closedontrustcode=" + getClosedontrustcode() +
            ", numemployeesmin=" + getNumemployeesmin() +
            ", numemployeesmax=" + getNumemployeesmax() +
            ", totalfundingusd=" + getTotalfundingusd() +
            ", totalfundingvnd=" + getTotalfundingvnd() +
            ", stockexchange='" + getStockexchange() + "'" +
            ", stocksymbol='" + getStocksymbol() + "'" +
            ", numberofinvestments=" + getNumberofinvestments() +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            ", permalinkaliases='" + getPermalinkaliases() + "'" +
            ", investortype='" + getInvestortype() + "'" +
            ", contactemail='" + getContactemail() + "'" +
            ", phonenumber='" + getPhonenumber() + "'" +
            ", rank=" + getRank() +
            ", primaryimageid=" + getPrimaryimageid() +
            ", ownedbyid=" + getOwnedbyid() +
            ", headquartersid=" + getHeadquartersid() +
            ", acquiredbyid=" + getAcquiredbyid() +
            ", ipoid=" + getIpoid() +
            ", domain='" + getDomain() + "'" +
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
