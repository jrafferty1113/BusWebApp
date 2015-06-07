package models.path;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import base.TestDataConfig;
import configs.AppConfig;

@ContextConfiguration(classes={AppConfig.class, TestDataConfig.class})
public class GeoUtilTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void distanceTest() {
        running(fakeApplication(), new Runnable() {
            public void run() {
        		double l1 = 37.7879999;
        		double lo1 = -122.4036599;
        		double l2 = 37.78764;
          		double lo2 = -122.4064899;
        		
        		assertThat(GeoUtil.getDistanceInMeters(l1, lo1, l2, lo2) == 251.8876365301628);
            }
        });
	}
	
	@Test
	public void toRadiansTest() {
        running(fakeApplication(), new Runnable() {
            public void run() {
        		assertThat(GeoUtil.toRadians(37.7879999) == 0.6595250159982881);
        		assertThat(GeoUtil.toRadians(-122.4036599) == -2.136346881746353);
            }
        });
	}
}
