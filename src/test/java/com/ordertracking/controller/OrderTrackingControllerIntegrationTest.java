package com.ordertracking.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderTrackingControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    @DisplayName("POST /order/tracking/")
    public void shouldReturnSuccessWhenCallPost() throws Exception {

        String trackingOrder = "{\"orderId\":230688717,\"trackingStatusId\":1,\"changeStatusDate\":\"2019-01-30T07:18:23.108+00:00\"}";

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "trackingOrder.json",
                MediaType.APPLICATION_JSON_VALUE,
                trackingOrder.getBytes()
        );

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mvc.perform(multipart("/order/tracking/").file(file))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /order/tracking/")
    public void shouldReturnSuccessWhenCallPostWithStatusInTransit() throws Exception {

        String trackingOrder = "{\"orderId\":230688717,\"trackingStatusId\":2,\"changeStatusDate\":\"2019-01-30T07:18:23.108+00:00\"}";

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "trackingOrder.json",
                MediaType.APPLICATION_JSON_VALUE,
                trackingOrder.getBytes()
        );

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mvc.perform(multipart("/order/tracking/").file(file))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /order/tracking/")
    public void shouldReturnSuccessWhenCallPostWithStatusDeliveryException() throws Exception {

        String trackingOrder = "{\"orderId\":230688717,\"trackingStatusId\":3,\"changeStatusDate\":\"2019-01-30T07:18:23.108+00:00\"}";

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "trackingOrder.json",
                MediaType.APPLICATION_JSON_VALUE,
                trackingOrder.getBytes()
        );

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mvc.perform(multipart("/order/tracking/").file(file))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /order/tracking/")
    public void shouldReturnSuccessWhenCallPostWithStatusDelivered() throws Exception {

        String trackingOrder = "{\"orderId\":230688717,\"trackingStatusId\":4,\"changeStatusDate\":\"2019-01-30T07:18:23.108+00:00\"}";

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "trackingOrder.json",
                MediaType.APPLICATION_JSON_VALUE,
                trackingOrder.getBytes()
        );

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mvc.perform(multipart("/order/tracking/").file(file))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /order/tracking/")
    public void shouldThrowExceptionWhenStatusIsInvalid() throws Exception {

        String trackingOrder = "{\"orderId\":230688717,\"trackingStatusId\":5,\"changeStatusDate\":\"2019-01-30T07:18:23.108+00:00\"}";

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "trackingOrder.json",
                MediaType.APPLICATION_JSON_VALUE,
                trackingOrder.getBytes()
        );

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mvc.perform(multipart("/order/tracking/").file(file))
                .andExpect(status().is4xxClientError());
    }

}
