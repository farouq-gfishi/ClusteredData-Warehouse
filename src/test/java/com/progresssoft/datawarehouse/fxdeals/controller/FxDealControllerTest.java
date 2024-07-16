package com.progresssoft.datawarehouse.fxdeals.controller;


import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.progresssoft.datawarehouse.fxdeals.exception.DealExistsException;
import com.progresssoft.datawarehouse.fxdeals.service.FXService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = FXDealsController.class)
@ActiveProfiles("test")
public class FxDealControllerTest {

//    @Mock
//    private FXService fxService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testSaveFXDeal() throws Exception, DealExistsException {
//        doNothing().when(fxService).processingDeal();
//        mockMvc.perform(post("/api/save-deal"))
//                .andExpect(status().isOk());
//        verify(fxService, times(1)).processingDeal();
//    }
}
