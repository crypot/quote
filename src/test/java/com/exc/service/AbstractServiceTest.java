package com.exc.service;


import com.exc.QuoteApp;
import io.github.jhipster.config.JHipsterConstants;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuoteApp.class)
@EnableTransactionManagement(proxyTargetClass = true, mode = AdviceMode.PROXY)
@ActiveProfiles({JHipsterConstants.SPRING_PROFILE_TEST})
public abstract class AbstractServiceTest {
    @Before
    public void setup() {

    }

}
