package services;

import base.TestDataConfig;
import configs.AppConfig;
import models.bus.Agency;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import services.AgencyService;

import java.util.List;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes={AppConfig.class, TestDataConfig.class})
public class AgencyServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private AgencyService agencyService;

    @Test
    public void createAgency() {
        agencyService.addAgency(new Agency("Dick","Home"));
        agencyService.addAgency(new Agency("Jasper","Cornbuckles"));
        agencyService.addAgency(new Agency("Hans","Van Der Eng"));
        //assertThat(agency.id).isNotNull();
    }

    @Test
    public void getBars() {
        createAgency();
        List<Agency> list = agencyService.getAllAgencies();
        assertTrue(list.size() == 3);
    }
}