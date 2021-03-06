package ru.levelp.myapp.model;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.levelp.myapp.conf.TestConfiguration;
import ru.levelp.myapp.dao.PartsDAO;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class IndexPageTest {
    @Autowired
    private PartsDAO dao;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mock;

    @Before
    public void setup() {
        mock = MockMvcBuilders.
                webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testIndexPage() throws Exception {
        dao.createInitialData();

        mock.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("indexBean"))
                .andExpect(model().attribute("userName", new BaseMatcher<String>() {
                    @Override
                    public boolean matches(Object o) {
                        return o == null;
                    }

                    @Override
                    public void describeTo(Description description) {
                        description.appendText("user shouldn' be set");
                    }
                })).andReturn();
    }

    @Test
    public void testIndexPageLoggedIn() throws Exception {
        dao.createInitialData();

        mock.perform(MockMvcRequestBuilders
                .get("/").with(
                        user("someUser").password("test").roles("USER")
                ))

                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("indexBean"))
                .andExpect(model().attribute("userName", "someUser")).andReturn();
    }
}
