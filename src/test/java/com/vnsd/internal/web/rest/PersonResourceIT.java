package com.vnsd.internal.web.rest;

import com.vnsd.internal.VnsdinternalApp;
import com.vnsd.internal.domain.Person;
import com.vnsd.internal.repository.PersonRepository;
import com.vnsd.internal.service.PersonService;
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
 * Integration tests for the {@link PersonResource} REST controller.
 */
@SpringBootTest(classes = VnsdinternalApp.class)
public class PersonResourceIT {

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_PERMALINK = "AAAAAAAAAA";
    private static final String UPDATED_PERMALINK = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_ALSOKNOWNAS = "AAAAAAAAAA";
    private static final String UPDATED_ALSOKNOWNAS = "BBBBBBBBBB";

    private static final String DEFAULT_BIO = "AAAAAAAAAA";
    private static final String UPDATED_BIO = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROFILEIMAGEID = 1;
    private static final Integer UPDATED_PROFILEIMAGEID = 2;
    private static final Integer SMALLER_PROFILEIMAGEID = 1 - 1;

    private static final Boolean DEFAULT_ROLEINVESTOR = false;
    private static final Boolean UPDATED_ROLEINVESTOR = true;

    private static final Instant DEFAULT_BORNON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BORNON = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_BORNON = Instant.ofEpochMilli(-1L);

    private static final Integer DEFAULT_BORNONTRUSTCODE = 1;
    private static final Integer UPDATED_BORNONTRUSTCODE = 2;
    private static final Integer SMALLER_BORNONTRUSTCODE = 1 - 1;

    private static final Instant DEFAULT_DIEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DIEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_DIEDON = Instant.ofEpochMilli(-1L);

    private static final Integer DEFAULT_DIEDONTRUSTCODE = 1;
    private static final Integer UPDATED_DIEDONTRUSTCODE = 2;
    private static final Integer SMALLER_DIEDONTRUSTCODE = 1 - 1;

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_CREATEDAT = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_UPDATEDAT = Instant.ofEpochMilli(-1L);

    private static final String DEFAULT_PERMALINKALIASES = "AAAAAAAAAA";
    private static final String UPDATED_PERMALINKALIASES = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;
    private static final Integer SMALLER_RANK = 1 - 1;

    private static final Integer DEFAULT_PRIMARYAFFILIATIONID = 1;
    private static final Integer UPDATED_PRIMARYAFFILIATIONID = 2;
    private static final Integer SMALLER_PRIMARYAFFILIATIONID = 1 - 1;

    private static final Integer DEFAULT_PRIMARYLOCATIONID = 1;
    private static final Integer UPDATED_PRIMARYLOCATIONID = 2;
    private static final Integer SMALLER_PRIMARYLOCATIONID = 1 - 1;

    private static final Integer DEFAULT_PRIMARYIMAGEID = 1;
    private static final Integer UPDATED_PRIMARYIMAGEID = 2;
    private static final Integer SMALLER_PRIMARYIMAGEID = 1 - 1;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

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
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

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

    private MockMvc restPersonMockMvc;

    private Person person;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PersonResource personResource = new PersonResource(personService);
        this.restPersonMockMvc = MockMvcBuilders.standaloneSetup(personResource)
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
    public static Person createEntity(EntityManager em) {
        Person person = new Person()
            .uuid(DEFAULT_UUID)
            .permalink(DEFAULT_PERMALINK)
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .alsoknownas(DEFAULT_ALSOKNOWNAS)
            .bio(DEFAULT_BIO)
            .profileimageid(DEFAULT_PROFILEIMAGEID)
            .roleinvestor(DEFAULT_ROLEINVESTOR)
            .bornon(DEFAULT_BORNON)
            .bornontrustcode(DEFAULT_BORNONTRUSTCODE)
            .diedon(DEFAULT_DIEDON)
            .diedontrustcode(DEFAULT_DIEDONTRUSTCODE)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT)
            .permalinkaliases(DEFAULT_PERMALINKALIASES)
            .gender(DEFAULT_GENDER)
            .rank(DEFAULT_RANK)
            .primaryaffiliationid(DEFAULT_PRIMARYAFFILIATIONID)
            .primarylocationid(DEFAULT_PRIMARYLOCATIONID)
            .primaryimageid(DEFAULT_PRIMARYIMAGEID)
            .title(DEFAULT_TITLE)
            .homepageurl(DEFAULT_HOMEPAGEURL)
            .facebookurl(DEFAULT_FACEBOOKURL)
            .twitterurl(DEFAULT_TWITTERURL)
            .linkedinurl(DEFAULT_LINKEDINURL)
            .cityname(DEFAULT_CITYNAME)
            .regionname(DEFAULT_REGIONNAME)
            .countrycode(DEFAULT_COUNTRYCODE);
        return person;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Person createUpdatedEntity(EntityManager em) {
        Person person = new Person()
            .uuid(UPDATED_UUID)
            .permalink(UPDATED_PERMALINK)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .alsoknownas(UPDATED_ALSOKNOWNAS)
            .bio(UPDATED_BIO)
            .profileimageid(UPDATED_PROFILEIMAGEID)
            .roleinvestor(UPDATED_ROLEINVESTOR)
            .bornon(UPDATED_BORNON)
            .bornontrustcode(UPDATED_BORNONTRUSTCODE)
            .diedon(UPDATED_DIEDON)
            .diedontrustcode(UPDATED_DIEDONTRUSTCODE)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT)
            .permalinkaliases(UPDATED_PERMALINKALIASES)
            .gender(UPDATED_GENDER)
            .rank(UPDATED_RANK)
            .primaryaffiliationid(UPDATED_PRIMARYAFFILIATIONID)
            .primarylocationid(UPDATED_PRIMARYLOCATIONID)
            .primaryimageid(UPDATED_PRIMARYIMAGEID)
            .title(UPDATED_TITLE)
            .homepageurl(UPDATED_HOMEPAGEURL)
            .facebookurl(UPDATED_FACEBOOKURL)
            .twitterurl(UPDATED_TWITTERURL)
            .linkedinurl(UPDATED_LINKEDINURL)
            .cityname(UPDATED_CITYNAME)
            .regionname(UPDATED_REGIONNAME)
            .countrycode(UPDATED_COUNTRYCODE);
        return person;
    }

    @BeforeEach
    public void initTest() {
        person = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerson() throws Exception {
        int databaseSizeBeforeCreate = personRepository.findAll().size();

        // Create the Person
        restPersonMockMvc.perform(post("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isCreated());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeCreate + 1);
        Person testPerson = personList.get(personList.size() - 1);
        assertThat(testPerson.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testPerson.getPermalink()).isEqualTo(DEFAULT_PERMALINK);
        assertThat(testPerson.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testPerson.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testPerson.getAlsoknownas()).isEqualTo(DEFAULT_ALSOKNOWNAS);
        assertThat(testPerson.getBio()).isEqualTo(DEFAULT_BIO);
        assertThat(testPerson.getProfileimageid()).isEqualTo(DEFAULT_PROFILEIMAGEID);
        assertThat(testPerson.isRoleinvestor()).isEqualTo(DEFAULT_ROLEINVESTOR);
        assertThat(testPerson.getBornon()).isEqualTo(DEFAULT_BORNON);
        assertThat(testPerson.getBornontrustcode()).isEqualTo(DEFAULT_BORNONTRUSTCODE);
        assertThat(testPerson.getDiedon()).isEqualTo(DEFAULT_DIEDON);
        assertThat(testPerson.getDiedontrustcode()).isEqualTo(DEFAULT_DIEDONTRUSTCODE);
        assertThat(testPerson.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testPerson.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
        assertThat(testPerson.getPermalinkaliases()).isEqualTo(DEFAULT_PERMALINKALIASES);
        assertThat(testPerson.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPerson.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testPerson.getPrimaryaffiliationid()).isEqualTo(DEFAULT_PRIMARYAFFILIATIONID);
        assertThat(testPerson.getPrimarylocationid()).isEqualTo(DEFAULT_PRIMARYLOCATIONID);
        assertThat(testPerson.getPrimaryimageid()).isEqualTo(DEFAULT_PRIMARYIMAGEID);
        assertThat(testPerson.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testPerson.getHomepageurl()).isEqualTo(DEFAULT_HOMEPAGEURL);
        assertThat(testPerson.getFacebookurl()).isEqualTo(DEFAULT_FACEBOOKURL);
        assertThat(testPerson.getTwitterurl()).isEqualTo(DEFAULT_TWITTERURL);
        assertThat(testPerson.getLinkedinurl()).isEqualTo(DEFAULT_LINKEDINURL);
        assertThat(testPerson.getCityname()).isEqualTo(DEFAULT_CITYNAME);
        assertThat(testPerson.getRegionname()).isEqualTo(DEFAULT_REGIONNAME);
        assertThat(testPerson.getCountrycode()).isEqualTo(DEFAULT_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void createPersonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personRepository.findAll().size();

        // Create the Person with an existing ID
        person.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonMockMvc.perform(post("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setUuid(null);

        // Create the Person, which fails.

        restPersonMockMvc.perform(post("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPermalinkIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setPermalink(null);

        // Create the Person, which fails.

        restPersonMockMvc.perform(post("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setFirstname(null);

        // Create the Person, which fails.

        restPersonMockMvc.perform(post("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setLastname(null);

        // Create the Person, which fails.

        restPersonMockMvc.perform(post("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPeople() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList
        restPersonMockMvc.perform(get("/api/people?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(person.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].permalink").value(hasItem(DEFAULT_PERMALINK.toString())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME.toString())))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME.toString())))
            .andExpect(jsonPath("$.[*].alsoknownas").value(hasItem(DEFAULT_ALSOKNOWNAS.toString())))
            .andExpect(jsonPath("$.[*].bio").value(hasItem(DEFAULT_BIO.toString())))
            .andExpect(jsonPath("$.[*].profileimageid").value(hasItem(DEFAULT_PROFILEIMAGEID)))
            .andExpect(jsonPath("$.[*].roleinvestor").value(hasItem(DEFAULT_ROLEINVESTOR.booleanValue())))
            .andExpect(jsonPath("$.[*].bornon").value(hasItem(DEFAULT_BORNON.toString())))
            .andExpect(jsonPath("$.[*].bornontrustcode").value(hasItem(DEFAULT_BORNONTRUSTCODE)))
            .andExpect(jsonPath("$.[*].diedon").value(hasItem(DEFAULT_DIEDON.toString())))
            .andExpect(jsonPath("$.[*].diedontrustcode").value(hasItem(DEFAULT_DIEDONTRUSTCODE)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())))
            .andExpect(jsonPath("$.[*].permalinkaliases").value(hasItem(DEFAULT_PERMALINKALIASES.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].primaryaffiliationid").value(hasItem(DEFAULT_PRIMARYAFFILIATIONID)))
            .andExpect(jsonPath("$.[*].primarylocationid").value(hasItem(DEFAULT_PRIMARYLOCATIONID)))
            .andExpect(jsonPath("$.[*].primaryimageid").value(hasItem(DEFAULT_PRIMARYIMAGEID)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
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
    public void getPerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get the person
        restPersonMockMvc.perform(get("/api/people/{id}", person.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(person.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.permalink").value(DEFAULT_PERMALINK.toString()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME.toString()))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME.toString()))
            .andExpect(jsonPath("$.alsoknownas").value(DEFAULT_ALSOKNOWNAS.toString()))
            .andExpect(jsonPath("$.bio").value(DEFAULT_BIO.toString()))
            .andExpect(jsonPath("$.profileimageid").value(DEFAULT_PROFILEIMAGEID))
            .andExpect(jsonPath("$.roleinvestor").value(DEFAULT_ROLEINVESTOR.booleanValue()))
            .andExpect(jsonPath("$.bornon").value(DEFAULT_BORNON.toString()))
            .andExpect(jsonPath("$.bornontrustcode").value(DEFAULT_BORNONTRUSTCODE))
            .andExpect(jsonPath("$.diedon").value(DEFAULT_DIEDON.toString()))
            .andExpect(jsonPath("$.diedontrustcode").value(DEFAULT_DIEDONTRUSTCODE))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()))
            .andExpect(jsonPath("$.permalinkaliases").value(DEFAULT_PERMALINKALIASES.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.primaryaffiliationid").value(DEFAULT_PRIMARYAFFILIATIONID))
            .andExpect(jsonPath("$.primarylocationid").value(DEFAULT_PRIMARYLOCATIONID))
            .andExpect(jsonPath("$.primaryimageid").value(DEFAULT_PRIMARYIMAGEID))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
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
    public void getNonExistingPerson() throws Exception {
        // Get the person
        restPersonMockMvc.perform(get("/api/people/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerson() throws Exception {
        // Initialize the database
        personService.save(person);

        int databaseSizeBeforeUpdate = personRepository.findAll().size();

        // Update the person
        Person updatedPerson = personRepository.findById(person.getId()).get();
        // Disconnect from session so that the updates on updatedPerson are not directly saved in db
        em.detach(updatedPerson);
        updatedPerson
            .uuid(UPDATED_UUID)
            .permalink(UPDATED_PERMALINK)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .alsoknownas(UPDATED_ALSOKNOWNAS)
            .bio(UPDATED_BIO)
            .profileimageid(UPDATED_PROFILEIMAGEID)
            .roleinvestor(UPDATED_ROLEINVESTOR)
            .bornon(UPDATED_BORNON)
            .bornontrustcode(UPDATED_BORNONTRUSTCODE)
            .diedon(UPDATED_DIEDON)
            .diedontrustcode(UPDATED_DIEDONTRUSTCODE)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT)
            .permalinkaliases(UPDATED_PERMALINKALIASES)
            .gender(UPDATED_GENDER)
            .rank(UPDATED_RANK)
            .primaryaffiliationid(UPDATED_PRIMARYAFFILIATIONID)
            .primarylocationid(UPDATED_PRIMARYLOCATIONID)
            .primaryimageid(UPDATED_PRIMARYIMAGEID)
            .title(UPDATED_TITLE)
            .homepageurl(UPDATED_HOMEPAGEURL)
            .facebookurl(UPDATED_FACEBOOKURL)
            .twitterurl(UPDATED_TWITTERURL)
            .linkedinurl(UPDATED_LINKEDINURL)
            .cityname(UPDATED_CITYNAME)
            .regionname(UPDATED_REGIONNAME)
            .countrycode(UPDATED_COUNTRYCODE);

        restPersonMockMvc.perform(put("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPerson)))
            .andExpect(status().isOk());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeUpdate);
        Person testPerson = personList.get(personList.size() - 1);
        assertThat(testPerson.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testPerson.getPermalink()).isEqualTo(UPDATED_PERMALINK);
        assertThat(testPerson.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testPerson.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testPerson.getAlsoknownas()).isEqualTo(UPDATED_ALSOKNOWNAS);
        assertThat(testPerson.getBio()).isEqualTo(UPDATED_BIO);
        assertThat(testPerson.getProfileimageid()).isEqualTo(UPDATED_PROFILEIMAGEID);
        assertThat(testPerson.isRoleinvestor()).isEqualTo(UPDATED_ROLEINVESTOR);
        assertThat(testPerson.getBornon()).isEqualTo(UPDATED_BORNON);
        assertThat(testPerson.getBornontrustcode()).isEqualTo(UPDATED_BORNONTRUSTCODE);
        assertThat(testPerson.getDiedon()).isEqualTo(UPDATED_DIEDON);
        assertThat(testPerson.getDiedontrustcode()).isEqualTo(UPDATED_DIEDONTRUSTCODE);
        assertThat(testPerson.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testPerson.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
        assertThat(testPerson.getPermalinkaliases()).isEqualTo(UPDATED_PERMALINKALIASES);
        assertThat(testPerson.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPerson.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testPerson.getPrimaryaffiliationid()).isEqualTo(UPDATED_PRIMARYAFFILIATIONID);
        assertThat(testPerson.getPrimarylocationid()).isEqualTo(UPDATED_PRIMARYLOCATIONID);
        assertThat(testPerson.getPrimaryimageid()).isEqualTo(UPDATED_PRIMARYIMAGEID);
        assertThat(testPerson.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testPerson.getHomepageurl()).isEqualTo(UPDATED_HOMEPAGEURL);
        assertThat(testPerson.getFacebookurl()).isEqualTo(UPDATED_FACEBOOKURL);
        assertThat(testPerson.getTwitterurl()).isEqualTo(UPDATED_TWITTERURL);
        assertThat(testPerson.getLinkedinurl()).isEqualTo(UPDATED_LINKEDINURL);
        assertThat(testPerson.getCityname()).isEqualTo(UPDATED_CITYNAME);
        assertThat(testPerson.getRegionname()).isEqualTo(UPDATED_REGIONNAME);
        assertThat(testPerson.getCountrycode()).isEqualTo(UPDATED_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void updateNonExistingPerson() throws Exception {
        int databaseSizeBeforeUpdate = personRepository.findAll().size();

        // Create the Person

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonMockMvc.perform(put("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePerson() throws Exception {
        // Initialize the database
        personService.save(person);

        int databaseSizeBeforeDelete = personRepository.findAll().size();

        // Delete the person
        restPersonMockMvc.perform(delete("/api/people/{id}", person.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Person.class);
        Person person1 = new Person();
        person1.setId(1L);
        Person person2 = new Person();
        person2.setId(person1.getId());
        assertThat(person1).isEqualTo(person2);
        person2.setId(2L);
        assertThat(person1).isNotEqualTo(person2);
        person1.setId(null);
        assertThat(person1).isNotEqualTo(person2);
    }
}
