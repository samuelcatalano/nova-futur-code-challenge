package co.uk.novafutor.techchallenge;

import co.uk.novafutor.techchallenge.controller.CollatzRESTController;
import co.uk.novafutor.techchallenge.controller.FeedbackRESTController;
import co.uk.novafutor.techchallenge.controller.GraphicsPointRESTController;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Samuel Catalano
 * @since 23 March, 2020
 */

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
class NovaFuturApplicationTests {

    private static final Logger LOG = LoggerFactory.getLogger(NovaFuturApplicationTests.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CollatzRESTController collatzRESTController;

    @Autowired
    private FeedbackRESTController feedbackRESTController;

    @Autowired
    private GraphicsPointRESTController graphicsPointRESTController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void contextLoads() {
        assertThat(this.collatzRESTController).isNotNull();
        assertThat(this.feedbackRESTController).isNotNull();
        assertThat(this.graphicsPointRESTController).isNotNull();
    }

    @Test
    public void testCollatzIterative() throws Exception {
        final String COLLATZ_35 = "35 106 53 160 80 40 20 10 5 16 8 4 2 1";
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/collatz/iterative?number=35")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        final MvcResult result = this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(), COLLATZ_35);
    }

    @Test
    public void testCollatzRecursive() throws Exception {
        final String COLLATZ_12 = "12 6 3 10 5 16 8 4 2 1";
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/collatz/recursive?number=12")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        final MvcResult result = this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(), COLLATZ_12);
    }

    @Test
    public void testDotProduct() throws Exception {
        final String content = "{\"n\":3,\"vectorA\":[30,6,6],\"vectorB\":[20,10,1]}";

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/graphics-point/dot-product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        final MvcResult result = this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(), "Dot product: 666");
    }

    @Test
    public void testFeedback() throws Exception {
        final String  expectedMsg = "\"We are calculating the answer, please come back in an unpredictable time!\"";
        final Integer expectedFeedback = 5051;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/feedback?number=100")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        final String msg = (String) jsonObject.get("msg");

        LOG.info("Testing feedback without cache! Should return a message informaing that the value is being process");
        Assert.assertEquals(msg, expectedMsg);

        requestBuilder = MockMvcRequestBuilders.get("/feedback?number=100")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        result = this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        jsonObject = new JSONObject(result.getResponse().getContentAsString());
        final Integer feedback = (Integer) jsonObject.get("feedback");

        LOG.info("Calling the same number to test the feedback! Now should return the value calculated!");
        Assert.assertEquals(feedback, expectedFeedback);
    }
}