package com.vnsd.internal.web.rest;

import com.vnsd.internal.VnsdinternalApp;
import com.vnsd.internal.domain.Organization;
import com.vnsd.internal.repository.OrganizationRepository;
import com.vnsd.internal.service.OrganizationService;
import com.vnsd.internal.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.vnsd.internal.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrganizationResource} REST controller.
 */
@SpringBootTest(classes = VnsdinternalApp.class)
public class OrganizationResourceIT {

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_PERMALINK = "AAAAAAAAAA";
    private static final String UPDATED_PERMALINK = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ALSOKNOWNAS = "AAAAAAAAAA";
    private static final String UPDATED_ALSOKNOWNAS = "BBBBBBBBBB";

    private static final String DEFAULT_SHORTDESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SHORTDESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROFILEIMAGEID = 1;
    private static final Integer UPDATED_PROFILEIMAGEID = 2;
    private static final Integer SMALLER_PROFILEIMAGEID = 1 - 1;

    private static final String DEFAULT_PRIMARYROLE = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARYROLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ROLECOMPANY = false;
    private static final Boolean UPDATED_ROLECOMPANY = true;

    private static final Boolean DEFAULT_ROLEINVESTOR = false;
    private static final Boolean UPDATED_ROLEINVESTOR = true;

    private static final Boolean DEFAULT_ROLEGROUP = false;
    private static final Boolean UPDATED_ROLEGROUP = true;

    private static final Boolean DEFAULT_ROLESCHOOL = false;
    private static final Boolean UPDATED_ROLESCHOOL = true;

    private static final Instant DEFAULT_FOUNDEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FOUNDEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_FOUNDEDON = Instant.ofEpochMilli(-1L);

    private static final Integer DEFAULT_FOUNDEDONTRUSTCODE = 1;
    private static final Integer UPDATED_FOUNDEDONTRUSTCODE = 2;
    private static final Integer SMALLER_FOUNDEDONTRUSTCODE = 1 - 1;

    private static final Instant DEFAULT_CLOSEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLOSEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_CLOSEDON = Instant.ofEpochMilli(-1L);

    private static final Integer DEFAULT_CLOSEDONTRUSTCODE = 1;
    private static final Integer UPDATED_CLOSEDONTRUSTCODE = 2;
    private static final Integer SMALLER_CLOSEDONTRUSTCODE = 1 - 1;

    private static final Integer DEFAULT_NUMEMPLOYEESMIN = 1;
    private static final Integer UPDATED_NUMEMPLOYEESMIN = 2;
    private static final Integer SMALLER_NUMEMPLOYEESMIN = 1 - 1;

    private static final Integer DEFAULT_NUMEMPLOYEESMAX = 1;
    private static final Integer UPDATED_NUMEMPLOYEESMAX = 2;
    private static final Integer SMALLER_NUMEMPLOYEESMAX = 1 - 1;

    private static final Integer DEFAULT_TOTALFUNDINGUSD = 1;
    private static final Integer UPDATED_TOTALFUNDINGUSD = 2;
    private static final Integer SMALLER_TOTALFUNDINGUSD = 1 - 1;

    private static final Integer DEFAULT_TOTALFUNDINGVND = 1;
    private static final Integer UPDATED_TOTALFUNDINGVND = 2;
    private static final Integer SMALLER_TOTALFUNDINGVND = 1 - 1;

    private static final String DEFAULT_STOCKEXCHANGE = "AAAAAAAAAA";
    private static final String UPDATED_STOCKEXCHANGE = "BBBBBBBBBB";

    private static final String DEFAULT_STOCKSYMBOL = "AAAAAAAAAA";
    private static final String UPDATED_STOCKSYMBOL = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBEROFINVESTMENTS = 1;
    private static final Integer UPDATED_NUMBEROFINVESTMENTS = 2;
    private static final Integer SMALLER_NUMBEROFINVESTMENTS = 1 - 1;

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_CREATEDAT = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_UPDATEDAT = Instant.ofEpochMilli(-1L);

    private static final String DEFAULT_PERMALINKALIASES = "AAAAAAAAAA";
    private static final String UPDATED_PERMALINKALIASES = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTORTYPE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTORTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTEMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTEMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONENUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;
    private static final Integer SMALLER_RANK = 1 - 1;

    private static final Integer DEFAULT_PRIMARYIMAGEID = 1;
    private static final Integer UPDATED_PRIMARYIMAGEID = 2;
    private static final Integer SMALLER_PRIMARYIMAGEID = 1 - 1;

    private static final Integer DEFAULT_OWNEDBYID = 1;
    private static final Integer UPDATED_OWNEDBYID = 2;
    private static final Integer SMALLER_OWNEDBYID = 1 - 1;

    private static final Integer DEFAULT_HEADQUARTERSID = 1;
    private static final Integer UPDATED_HEADQUARTERSID = 2;
    private static final Integer SMALLER_HEADQUARTERSID = 1 - 1;

    private static final Integer DEFAULT_ACQUIREDBYID = 1;
    private static final Integer UPDATED_ACQUIREDBYID = 2;
    private static final Integer SMALLER_ACQUIREDBYID = 1 - 1;

    private static final Integer DEFAULT_IPOID = 1;
    private static final Integer UPDATED_IPOID = 2;
    private static final Integer SMALLER_IPOID = 1 - 1;

    private static final String DEFAULT_DOMAIN = "AAAAAAAAAA";
    private static final String UPDATED_DOMAIN = "BBBBBBBBBB";

    private static final String DEFAULT_HOMEPAGEURL = "AAAAAAAAAA";
    private static final String UPDATED_HOMEPAGEURL = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOKURL = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOKURL = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTERURL = "AAAAAAAAAA";
    private static final String UPDATED_TWITTERURL = "BBBBBBBBBB";

    private static final String DEFAULT_LINKEDINURL = "AAAAAAAAAA";
    private static final String UPDATED_LINKEDINURL = "BBBBBBBBBB";

    private static final String DEFAULT_CITYNAME = "AAAAAAAAAA";
    private static final String UPDATED_CITYNAME = "BBBBBBBBBB";

    private static final String DEFAULT_REGIONNAME = "AAAAAAAAAA";
    private static final String UPDATED_REGIONNAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRYCODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRYCODE = "BBBBBBBBBB";

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restOrganizationMockMvc;

    private Organization organization;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrganizationResource organizationResource = new OrganizationResource(organizationService);
        this.restOrganizationMockMvc = MockMvcBuilders.standaloneSetup(organizationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organization createEntity(EntityManager em) {
        Organization organization = new Organization()
            .uuid(DEFAULT_UUID)
            .permalink(DEFAULT_PERMALINK)
            .name(DEFAULT_NAME)
            .alsoknownas(DEFAULT_ALSOKNOWNAS)
            .shortdescription(DEFAULT_SHORTDESCRIPTION)
            .description(DEFAULT_DESCRIPTION)
            .profileimageid(DEFAULT_PROFILEIMAGEID)
            .primaryrole(DEFAULT_PRIMARYROLE)
            .rolecompany(DEFAULT_ROLECOMPANY)
            .roleinvestor(DEFAULT_ROLEINVESTOR)
            .rolegroup(DEFAULT_ROLEGROUP)
            .roleschool(DEFAULT_ROLESCHOOL)
            .foundedon(DEFAULT_FOUNDEDON)
            .foundedontrustcode(DEFAULT_FOUNDEDONTRUSTCODE)
            .closedon(DEFAULT_CLOSEDON)
            .closedontrustcode(DEFAULT_CLOSEDONTRUSTCODE)
            .numemployeesmin(DEFAULT_NUMEMPLOYEESMIN)
            .numemployeesmax(DEFAULT_NUMEMPLOYEESMAX)
            .totalfundingusd(DEFAULT_TOTALFUNDINGUSD)
            .totalfundingvnd(DEFAULT_TOTALFUNDINGVND)
            .stockexchange(DEFAULT_STOCKEXCHANGE)
            .stocksymbol(DEFAULT_STOCKSYMBOL)
            .numberofinvestments(DEFAULT_NUMBEROFINVESTMENTS)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT)
            .permalinkaliases(DEFAULT_PERMALINKALIASES)
            .investortype(DEFAULT_INVESTORTYPE)
            .contactemail(DEFAULT_CONTACTEMAIL)
            .phonenumber(DEFAULT_PHONENUMBER)
            .rank(DEFAULT_RANK)
            .primaryimageid(DEFAULT_PRIMARYIMAGEID)
            .ownedbyid(DEFAULT_OWNEDBYID)
            .headquartersid(DEFAULT_HEADQUARTERSID)
            .acquiredbyid(DEFAULT_ACQUIREDBYID)
            .ipoid(DEFAULT_IPOID)
            .domain(DEFAULT_DOMAIN)
            .homepageurl(DEFAULT_HOMEPAGEURL)
            .facebookurl(DEFAULT_FACEBOOKURL)
            .twitterurl(DEFAULT_TWITTERURL)
            .linkedinurl(DEFAULT_LINKEDINURL)
            .cityname(DEFAULT_CITYNAME)
            .regionname(DEFAULT_REGIONNAME)
            .countrycode(DEFAULT_COUNTRYCODE);
        return organization;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organization createUpdatedEntity(EntityManager em) {
        Organization organization = new Organization()
            .uuid(UPDATED_UUID)
            .permalink(UPDATED_PERMALINK)
            .name(UPDATED_NAME)
            .alsoknownas(UPDATED_ALSOKNOWNAS)
            .shortdescription(UPDATED_SHORTDESCRIPTION)
            .description(UPDATED_DESCRIPTION)
            .profileimageid(UPDATED_PROFILEIMAGEID)
            .primaryrole(UPDATED_PRIMARYROLE)
            .rolecompany(UPDATED_ROLECOMPANY)
            .roleinvestor(UPDATED_ROLEINVESTOR)
            .rolegroup(UPDATED_ROLEGROUP)
            .roleschool(UPDATED_ROLESCHOOL)
            .foundedon(UPDATED_FOUNDEDON)
            .foundedontrustcode(UPDATED_FOUNDEDONTRUSTCODE)
            .closedon(UPDATED_CLOSEDON)
            .closedontrustcode(UPDATED_CLOSEDONTRUSTCODE)
            .numemployeesmin(UPDATED_NUMEMPLOYEESMIN)
            .numemployeesmax(UPDATED_NUMEMPLOYEESMAX)
            .totalfundingusd(UPDATED_TOTALFUNDINGUSD)
            .totalfundingvnd(UPDATED_TOTALFUNDINGVND)
            .stockexchange(UPDATED_STOCKEXCHANGE)
            .stocksymbol(UPDATED_STOCKSYMBOL)
            .numberofinvestments(UPDATED_NUMBEROFINVESTMENTS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT)
            .permalinkaliases(UPDATED_PERMALINKALIASES)
            .investortype(UPDATED_INVESTORTYPE)
            .contactemail(UPDATED_CONTACTEMAIL)
            .phonenumber(UPDATED_PHONENUMBER)
            .rank(UPDATED_RANK)
            .primaryimageid(UPDATED_PRIMARYIMAGEID)
            .ownedbyid(UPDATED_OWNEDBYID)
            .headquartersid(UPDATED_HEADQUARTERSID)
            .acquiredbyid(UPDATED_ACQUIREDBYID)
            .ipoid(UPDATED_IPOID)
            .domain(UPDATED_DOMAIN)
            .homepageurl(UPDATED_HOMEPAGEURL)
            .facebookurl(UPDATED_FACEBOOKURL)
            .twitterurl(UPDATED_TWITTERURL)
            .linkedinurl(UPDATED_LINKEDINURL)
            .cityname(UPDATED_CITYNAME)
            .regionname(UPDATED_REGIONNAME)
            .countrycode(UPDATED_COUNTRYCODE);
        return organization;
    }

    @BeforeEach
    public void initTest() {
        organization = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganization() throws Exception {
        int databaseSizeBeforeCreate = organizationRepository.findAll().size();

        // Create the Organization
        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isCreated());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeCreate + 1);
        Organization testOrganization = organizationList.get(organizationList.size() - 1);
        assertThat(testOrganization.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testOrganization.getPermalink()).isEqualTo(DEFAULT_PERMALINK);
        assertThat(testOrganization.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrganization.getAlsoknownas()).isEqualTo(DEFAULT_ALSOKNOWNAS);
        assertThat(testOrganization.getShortdescription()).isEqualTo(DEFAULT_SHORTDESCRIPTION);
        assertThat(testOrganization.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOrganization.getProfileimageid()).isEqualTo(DEFAULT_PROFILEIMAGEID);
        assertThat(testOrganization.getPrimaryrole()).isEqualTo(DEFAULT_PRIMARYROLE);
        assertThat(testOrganization.isRolecompany()).isEqualTo(DEFAULT_ROLECOMPANY);
        assertThat(testOrganization.isRoleinvestor()).isEqualTo(DEFAULT_ROLEINVESTOR);
        assertThat(testOrganization.isRolegroup()).isEqualTo(DEFAULT_ROLEGROUP);
        assertThat(testOrganization.isRoleschool()).isEqualTo(DEFAULT_ROLESCHOOL);
        assertThat(testOrganization.getFoundedon()).isEqualTo(DEFAULT_FOUNDEDON);
        assertThat(testOrganization.getFoundedontrustcode()).isEqualTo(DEFAULT_FOUNDEDONTRUSTCODE);
        assertThat(testOrganization.getClosedon()).isEqualTo(DEFAULT_CLOSEDON);
        assertThat(testOrganization.getClosedontrustcode()).isEqualTo(DEFAULT_CLOSEDONTRUSTCODE);
        assertThat(testOrganization.getNumemployeesmin()).isEqualTo(DEFAULT_NUMEMPLOYEESMIN);
        assertThat(testOrganization.getNumemployeesmax()).isEqualTo(DEFAULT_NUMEMPLOYEESMAX);
        assertThat(testOrganization.getTotalfundingusd()).isEqualTo(DEFAULT_TOTALFUNDINGUSD);
        assertThat(testOrganization.getTotalfundingvnd()).isEqualTo(DEFAULT_TOTALFUNDINGVND);
        assertThat(testOrganization.getStockexchange()).isEqualTo(DEFAULT_STOCKEXCHANGE);
        assertThat(testOrganization.getStocksymbol()).isEqualTo(DEFAULT_STOCKSYMBOL);
        assertThat(testOrganization.getNumberofinvestments()).isEqualTo(DEFAULT_NUMBEROFINVESTMENTS);
        assertThat(testOrganization.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testOrganization.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
        assertThat(testOrganization.getPermalinkaliases()).isEqualTo(DEFAULT_PERMALINKALIASES);
        assertThat(testOrganization.getInvestortype()).isEqualTo(DEFAULT_INVESTORTYPE);
        assertThat(testOrganization.getContactemail()).isEqualTo(DEFAULT_CONTACTEMAIL);
        assertThat(testOrganization.getPhonenumber()).isEqualTo(DEFAULT_PHONENUMBER);
        assertThat(testOrganization.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testOrganization.getPrimaryimageid()).isEqualTo(DEFAULT_PRIMARYIMAGEID);
        assertThat(testOrganization.getOwnedbyid()).isEqualTo(DEFAULT_OWNEDBYID);
        assertThat(testOrganization.getHeadquartersid()).isEqualTo(DEFAULT_HEADQUARTERSID);
        assertThat(testOrganization.getAcquiredbyid()).isEqualTo(DEFAULT_ACQUIREDBYID);
        assertThat(testOrganization.getIpoid()).isEqualTo(DEFAULT_IPOID);
        assertThat(testOrganization.getDomain()).isEqualTo(DEFAULT_DOMAIN);
        assertThat(testOrganization.getHomepageurl()).isEqualTo(DEFAULT_HOMEPAGEURL);
        assertThat(testOrganization.getFacebookurl()).isEqualTo(DEFAULT_FACEBOOKURL);
        assertThat(testOrganization.getTwitterurl()).isEqualTo(DEFAULT_TWITTERURL);
        assertThat(testOrganization.getLinkedinurl()).isEqualTo(DEFAULT_LINKEDINURL);
        assertThat(testOrganization.getCityname()).isEqualTo(DEFAULT_CITYNAME);
        assertThat(testOrganization.getRegionname()).isEqualTo(DEFAULT_REGIONNAME);
        assertThat(testOrganization.getCountrycode()).isEqualTo(DEFAULT_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void createOrganizationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organizationRepository.findAll().size();

        // Create the Organization with an existing ID
        organization.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationRepository.findAll().size();
        // set the field null
        organization.setUuid(null);

        // Create the Organization, which fails.

        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPermalinkIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationRepository.findAll().size();
        // set the field null
        organization.setPermalink(null);

        // Create the Organization, which fails.

        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizationRepository.findAll().size();
        // set the field null
        organization.setName(null);

        // Create the Organization, which fails.

        restOrganizationMockMvc.perform(post("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrganizations() throws Exception {
        // Initialize the database
        organizationRepository.saveAndFlush(organization);

        // Get all the organizationList
        restOrganizationMockMvc.perform(get("/api/organizations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organization.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].permalink").value(hasItem(DEFAULT_PERMALINK.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].alsoknownas").value(hasItem(DEFAULT_ALSOKNOWNAS.toString())))
            .andExpect(jsonPath("$.[*].shortdescription").value(hasItem(DEFAULT_SHORTDESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].profileimageid").value(hasItem(DEFAULT_PROFILEIMAGEID)))
            .andExpect(jsonPath("$.[*].primaryrole").value(hasItem(DEFAULT_PRIMARYROLE.toString())))
            .andExpect(jsonPath("$.[*].rolecompany").value(hasItem(DEFAULT_ROLECOMPANY.booleanValue())))
            .andExpect(jsonPath("$.[*].roleinvestor").value(hasItem(DEFAULT_ROLEINVESTOR.booleanValue())))
            .andExpect(jsonPath("$.[*].rolegroup").value(hasItem(DEFAULT_ROLEGROUP.booleanValue())))
            .andExpect(jsonPath("$.[*].roleschool").value(hasItem(DEFAULT_ROLESCHOOL.booleanValue())))
            .andExpect(jsonPath("$.[*].foundedon").value(hasItem(DEFAULT_FOUNDEDON.toString())))
            .andExpect(jsonPath("$.[*].foundedontrustcode").value(hasItem(DEFAULT_FOUNDEDONTRUSTCODE)))
            .andExpect(jsonPath("$.[*].closedon").value(hasItem(DEFAULT_CLOSEDON.toString())))
            .andExpect(jsonPath("$.[*].closedontrustcode").value(hasItem(DEFAULT_CLOSEDONTRUSTCODE)))
            .andExpect(jsonPath("$.[*].numemployeesmin").value(hasItem(DEFAULT_NUMEMPLOYEESMIN)))
            .andExpect(jsonPath("$.[*].numemployeesmax").value(hasItem(DEFAULT_NUMEMPLOYEESMAX)))
            .andExpect(jsonPath("$.[*].totalfundingusd").value(hasItem(DEFAULT_TOTALFUNDINGUSD)))
            .andExpect(jsonPath("$.[*].totalfundingvnd").value(hasItem(DEFAULT_TOTALFUNDINGVND)))
            .andExpect(jsonPath("$.[*].stockexchange").value(hasItem(DEFAULT_STOCKEXCHANGE.toString())))
            .andExpect(jsonPath("$.[*].stocksymbol").value(hasItem(DEFAULT_STOCKSYMBOL.toString())))
            .andExpect(jsonPath("$.[*].numberofinvestments").value(hasItem(DEFAULT_NUMBEROFINVESTMENTS)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())))
            .andExpect(jsonPath("$.[*].permalinkaliases").value(hasItem(DEFAULT_PERMALINKALIASES.toString())))
            .andExpect(jsonPath("$.[*].investortype").value(hasItem(DEFAULT_INVESTORTYPE.toString())))
            .andExpect(jsonPath("$.[*].contactemail").value(hasItem(DEFAULT_CONTACTEMAIL.toString())))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER.toString())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].primaryimageid").value(hasItem(DEFAULT_PRIMARYIMAGEID)))
            .andExpect(jsonPath("$.[*].ownedbyid").value(hasItem(DEFAULT_OWNEDBYID)))
            .andExpect(jsonPath("$.[*].headquartersid").value(hasItem(DEFAULT_HEADQUARTERSID)))
            .andExpect(jsonPath("$.[*].acquiredbyid").value(hasItem(DEFAULT_ACQUIREDBYID)))
            .andExpect(jsonPath("$.[*].ipoid").value(hasItem(DEFAULT_IPOID)))
            .andExpect(jsonPath("$.[*].domain").value(hasItem(DEFAULT_DOMAIN.toString())))
            .andExpect(jsonPath("$.[*].homepageurl").value(hasItem(DEFAULT_HOMEPAGEURL.toString())))
            .andExpect(jsonPath("$.[*].facebookurl").value(hasItem(DEFAULT_FACEBOOKURL.toString())))
            .andExpect(jsonPath("$.[*].twitterurl").value(hasItem(DEFAULT_TWITTERURL.toString())))
            .andExpect(jsonPath("$.[*].linkedinurl").value(hasItem(DEFAULT_LINKEDINURL.toString())))
            .andExpect(jsonPath("$.[*].cityname").value(hasItem(DEFAULT_CITYNAME.toString())))
            .andExpect(jsonPath("$.[*].regionname").value(hasItem(DEFAULT_REGIONNAME.toString())))
            .andExpect(jsonPath("$.[*].countrycode").value(hasItem(DEFAULT_COUNTRYCODE.toString())));
    }
    
    @Test
    @Transactional
    public void getOrganization() throws Exception {
        // Initialize the database
        organizationRepository.saveAndFlush(organization);

        // Get the organization
        restOrganizationMockMvc.perform(get("/api/organizations/{id}", organization.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(organization.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.permalink").value(DEFAULT_PERMALINK.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.alsoknownas").value(DEFAULT_ALSOKNOWNAS.toString()))
            .andExpect(jsonPath("$.shortdescription").value(DEFAULT_SHORTDESCRIPTION.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.profileimageid").value(DEFAULT_PROFILEIMAGEID))
            .andExpect(jsonPath("$.primaryrole").value(DEFAULT_PRIMARYROLE.toString()))
            .andExpect(jsonPath("$.rolecompany").value(DEFAULT_ROLECOMPANY.booleanValue()))
            .andExpect(jsonPath("$.roleinvestor").value(DEFAULT_ROLEINVESTOR.booleanValue()))
            .andExpect(jsonPath("$.rolegroup").value(DEFAULT_ROLEGROUP.booleanValue()))
            .andExpect(jsonPath("$.roleschool").value(DEFAULT_ROLESCHOOL.booleanValue()))
            .andExpect(jsonPath("$.foundedon").value(DEFAULT_FOUNDEDON.toString()))
            .andExpect(jsonPath("$.foundedontrustcode").value(DEFAULT_FOUNDEDONTRUSTCODE))
            .andExpect(jsonPath("$.closedon").value(DEFAULT_CLOSEDON.toString()))
            .andExpect(jsonPath("$.closedontrustcode").value(DEFAULT_CLOSEDONTRUSTCODE))
            .andExpect(jsonPath("$.numemployeesmin").value(DEFAULT_NUMEMPLOYEESMIN))
            .andExpect(jsonPath("$.numemployeesmax").value(DEFAULT_NUMEMPLOYEESMAX))
            .andExpect(jsonPath("$.totalfundingusd").value(DEFAULT_TOTALFUNDINGUSD))
            .andExpect(jsonPath("$.totalfundingvnd").value(DEFAULT_TOTALFUNDINGVND))
            .andExpect(jsonPath("$.stockexchange").value(DEFAULT_STOCKEXCHANGE.toString()))
            .andExpect(jsonPath("$.stocksymbol").value(DEFAULT_STOCKSYMBOL.toString()))
            .andExpect(jsonPath("$.numberofinvestments").value(DEFAULT_NUMBEROFINVESTMENTS))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()))
            .andExpect(jsonPath("$.permalinkaliases").value(DEFAULT_PERMALINKALIASES.toString()))
            .andExpect(jsonPath("$.investortype").value(DEFAULT_INVESTORTYPE.toString()))
            .andExpect(jsonPath("$.contactemail").value(DEFAULT_CONTACTEMAIL.toString()))
            .andExpect(jsonPath("$.phonenumber").value(DEFAULT_PHONENUMBER.toString()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.primaryimageid").value(DEFAULT_PRIMARYIMAGEID))
            .andExpect(jsonPath("$.ownedbyid").value(DEFAULT_OWNEDBYID))
            .andExpect(jsonPath("$.headquartersid").value(DEFAULT_HEADQUARTERSID))
            .andExpect(jsonPath("$.acquiredbyid").value(DEFAULT_ACQUIREDBYID))
            .andExpect(jsonPath("$.ipoid").value(DEFAULT_IPOID))
            .andExpect(jsonPath("$.domain").value(DEFAULT_DOMAIN.toString()))
            .andExpect(jsonPath("$.homepageurl").value(DEFAULT_HOMEPAGEURL.toString()))
            .andExpect(jsonPath("$.facebookurl").value(DEFAULT_FACEBOOKURL.toString()))
            .andExpect(jsonPath("$.twitterurl").value(DEFAULT_TWITTERURL.toString()))
            .andExpect(jsonPath("$.linkedinurl").value(DEFAULT_LINKEDINURL.toString()))
            .andExpect(jsonPath("$.cityname").value(DEFAULT_CITYNAME.toString()))
            .andExpect(jsonPath("$.regionname").value(DEFAULT_REGIONNAME.toString()))
            .andExpect(jsonPath("$.countrycode").value(DEFAULT_COUNTRYCODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrganization() throws Exception {
        // Get the organization
        restOrganizationMockMvc.perform(get("/api/organizations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganization() throws Exception {
        // Initialize the database
        organizationService.save(organization);

        int databaseSizeBeforeUpdate = organizationRepository.findAll().size();

        // Update the organization
        Organization updatedOrganization = organizationRepository.findById(organization.getId()).get();
        // Disconnect from session so that the updates on updatedOrganization are not directly saved in db
        em.detach(updatedOrganization);
        updatedOrganization
            .uuid(UPDATED_UUID)
            .permalink(UPDATED_PERMALINK)
            .name(UPDATED_NAME)
            .alsoknownas(UPDATED_ALSOKNOWNAS)
            .shortdescription(UPDATED_SHORTDESCRIPTION)
            .description(UPDATED_DESCRIPTION)
            .profileimageid(UPDATED_PROFILEIMAGEID)
            .primaryrole(UPDATED_PRIMARYROLE)
            .rolecompany(UPDATED_ROLECOMPANY)
            .roleinvestor(UPDATED_ROLEINVESTOR)
            .rolegroup(UPDATED_ROLEGROUP)
            .roleschool(UPDATED_ROLESCHOOL)
            .foundedon(UPDATED_FOUNDEDON)
            .foundedontrustcode(UPDATED_FOUNDEDONTRUSTCODE)
            .closedon(UPDATED_CLOSEDON)
            .closedontrustcode(UPDATED_CLOSEDONTRUSTCODE)
            .numemployeesmin(UPDATED_NUMEMPLOYEESMIN)
            .numemployeesmax(UPDATED_NUMEMPLOYEESMAX)
            .totalfundingusd(UPDATED_TOTALFUNDINGUSD)
            .totalfundingvnd(UPDATED_TOTALFUNDINGVND)
            .stockexchange(UPDATED_STOCKEXCHANGE)
            .stocksymbol(UPDATED_STOCKSYMBOL)
            .numberofinvestments(UPDATED_NUMBEROFINVESTMENTS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT)
            .permalinkaliases(UPDATED_PERMALINKALIASES)
            .investortype(UPDATED_INVESTORTYPE)
            .contactemail(UPDATED_CONTACTEMAIL)
            .phonenumber(UPDATED_PHONENUMBER)
            .rank(UPDATED_RANK)
            .primaryimageid(UPDATED_PRIMARYIMAGEID)
            .ownedbyid(UPDATED_OWNEDBYID)
            .headquartersid(UPDATED_HEADQUARTERSID)
            .acquiredbyid(UPDATED_ACQUIREDBYID)
            .ipoid(UPDATED_IPOID)
            .domain(UPDATED_DOMAIN)
            .homepageurl(UPDATED_HOMEPAGEURL)
            .facebookurl(UPDATED_FACEBOOKURL)
            .twitterurl(UPDATED_TWITTERURL)
            .linkedinurl(UPDATED_LINKEDINURL)
            .cityname(UPDATED_CITYNAME)
            .regionname(UPDATED_REGIONNAME)
            .countrycode(UPDATED_COUNTRYCODE);

        restOrganizationMockMvc.perform(put("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrganization)))
            .andExpect(status().isOk());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
        Organization testOrganization = organizationList.get(organizationList.size() - 1);
        assertThat(testOrganization.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testOrganization.getPermalink()).isEqualTo(UPDATED_PERMALINK);
        assertThat(testOrganization.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrganization.getAlsoknownas()).isEqualTo(UPDATED_ALSOKNOWNAS);
        assertThat(testOrganization.getShortdescription()).isEqualTo(UPDATED_SHORTDESCRIPTION);
        assertThat(testOrganization.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOrganization.getProfileimageid()).isEqualTo(UPDATED_PROFILEIMAGEID);
        assertThat(testOrganization.getPrimaryrole()).isEqualTo(UPDATED_PRIMARYROLE);
        assertThat(testOrganization.isRolecompany()).isEqualTo(UPDATED_ROLECOMPANY);
        assertThat(testOrganization.isRoleinvestor()).isEqualTo(UPDATED_ROLEINVESTOR);
        assertThat(testOrganization.isRolegroup()).isEqualTo(UPDATED_ROLEGROUP);
        assertThat(testOrganization.isRoleschool()).isEqualTo(UPDATED_ROLESCHOOL);
        assertThat(testOrganization.getFoundedon()).isEqualTo(UPDATED_FOUNDEDON);
        assertThat(testOrganization.getFoundedontrustcode()).isEqualTo(UPDATED_FOUNDEDONTRUSTCODE);
        assertThat(testOrganization.getClosedon()).isEqualTo(UPDATED_CLOSEDON);
        assertThat(testOrganization.getClosedontrustcode()).isEqualTo(UPDATED_CLOSEDONTRUSTCODE);
        assertThat(testOrganization.getNumemployeesmin()).isEqualTo(UPDATED_NUMEMPLOYEESMIN);
        assertThat(testOrganization.getNumemployeesmax()).isEqualTo(UPDATED_NUMEMPLOYEESMAX);
        assertThat(testOrganization.getTotalfundingusd()).isEqualTo(UPDATED_TOTALFUNDINGUSD);
        assertThat(testOrganization.getTotalfundingvnd()).isEqualTo(UPDATED_TOTALFUNDINGVND);
        assertThat(testOrganization.getStockexchange()).isEqualTo(UPDATED_STOCKEXCHANGE);
        assertThat(testOrganization.getStocksymbol()).isEqualTo(UPDATED_STOCKSYMBOL);
        assertThat(testOrganization.getNumberofinvestments()).isEqualTo(UPDATED_NUMBEROFINVESTMENTS);
        assertThat(testOrganization.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testOrganization.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
        assertThat(testOrganization.getPermalinkaliases()).isEqualTo(UPDATED_PERMALINKALIASES);
        assertThat(testOrganization.getInvestortype()).isEqualTo(UPDATED_INVESTORTYPE);
        assertThat(testOrganization.getContactemail()).isEqualTo(UPDATED_CONTACTEMAIL);
        assertThat(testOrganization.getPhonenumber()).isEqualTo(UPDATED_PHONENUMBER);
        assertThat(testOrganization.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testOrganization.getPrimaryimageid()).isEqualTo(UPDATED_PRIMARYIMAGEID);
        assertThat(testOrganization.getOwnedbyid()).isEqualTo(UPDATED_OWNEDBYID);
        assertThat(testOrganization.getHeadquartersid()).isEqualTo(UPDATED_HEADQUARTERSID);
        assertThat(testOrganization.getAcquiredbyid()).isEqualTo(UPDATED_ACQUIREDBYID);
        assertThat(testOrganization.getIpoid()).isEqualTo(UPDATED_IPOID);
        assertThat(testOrganization.getDomain()).isEqualTo(UPDATED_DOMAIN);
        assertThat(testOrganization.getHomepageurl()).isEqualTo(UPDATED_HOMEPAGEURL);
        assertThat(testOrganization.getFacebookurl()).isEqualTo(UPDATED_FACEBOOKURL);
        assertThat(testOrganization.getTwitterurl()).isEqualTo(UPDATED_TWITTERURL);
        assertThat(testOrganization.getLinkedinurl()).isEqualTo(UPDATED_LINKEDINURL);
        assertThat(testOrganization.getCityname()).isEqualTo(UPDATED_CITYNAME);
        assertThat(testOrganization.getRegionname()).isEqualTo(UPDATED_REGIONNAME);
        assertThat(testOrganization.getCountrycode()).isEqualTo(UPDATED_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganization() throws Exception {
        int databaseSizeBeforeUpdate = organizationRepository.findAll().size();

        // Create the Organization

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganizationMockMvc.perform(put("/api/organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organization)))
            .andExpect(status().isBadRequest());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganization() throws Exception {
        // Initialize the database
        organizationService.save(organization);

        int databaseSizeBeforeDelete = organizationRepository.findAll().size();

        // Delete the organization
        restOrganizationMockMvc.perform(delete("/api/organizations/{id}", organization.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Organization.class);
        Organization organization1 = new Organization();
        organization1.setId(1L);
        Organization organization2 = new Organization();
        organization2.setId(organization1.getId());
        assertThat(organization1).isEqualTo(organization2);
        organization2.setId(2L);
        assertThat(organization1).isNotEqualTo(organization2);
        organization1.setId(null);
        assertThat(organization1).isNotEqualTo(organization2);
    }
}
