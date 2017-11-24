package ticket.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ticket.ticket.controllers.complains1Controller;
import ticket.ticket.models.complains1;
import ticket.ticket.repositories.complains1Rep;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class complains1Test {

    private MockMvc mockMvc;


    @Mock
    private complains1Rep cRep;

    @InjectMocks
    private complains1Controller cController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(cController)
                .build();
    }


    @Test
    public void test_getAllComplaints() throws Exception {


        List<complains1> com = Arrays.asList(
                new complains1("Ashen","jayasinghe.ashen@gmail.com",
                        "12/11/2017","Discipline of staff",
                        "not resolved"));
        when(cRep.findAll()).thenReturn(com);
        mockMvc.perform(get("/complainss/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].passengerName", Matchers.is("Ashen")))
                .andExpect(jsonPath("$[0].pemail", Matchers.is("jayasinghe.ashen@gmail.com")))
                .andExpect(jsonPath("$[0].complainDate", Matchers.is("12/11/2017")))
                .andExpect(jsonPath("$[0].complainDesc", Matchers.is("Discipline of staff")))
                .andExpect(jsonPath("$[0].complainStatus", Matchers.is("not resolved")))
                .andExpect(jsonPath("$", hasSize(1)));

       verify(cRep).findAll();
        verify(cRep, times(1)).findAll();
        verify(cRep, atLeastOnce()).findAll();

    }
//
//    @Test
//    public void test_get_by_id_success() throws Exception {
//        Rooms rooms = new Rooms(1L,"232","323","dsad","sdad","sdad","dsad","dsad","4234","4324");
//        when(roomsR.findOne(1L));
//        mockMvc.perform(post("/rooms/update/{id}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
////                .andExpect(jsonPath("$.id", Matchers.is(1L)))
////                .andExpect(jsonPath("$.roomNo", Matchers.is("232")))
////                .andExpect(jsonPath("$.roomType", Matchers.is("323")))
////                .andExpect(jsonPath("$.roomStatus", Matchers.is("dsad")))
////                .andExpect(jsonPath("$.roomRate", Matchers.is("sdad")))
////                .andExpect(jsonPath("$.noOfPerson", Matchers.is("sdad")))
////                .andExpect(jsonPath("$.adultRate", Matchers.is("dsad")))
////                .andExpect(jsonPath("$.childRate", Matchers.is("dsad")))
////                .andExpect(jsonPath("$.remarks", Matchers.is("4234")))
////                .andExpect(jsonPath("$.assigned", Matchers.is("4324")));
//
////        verify(roomC, times(1)).findOne(1);
////        verifyNoMoreInteractions(roomC);
//    }

    @Test
    public void test_create_user_success() throws Exception {

        complains1 com =  new complains1("Ashen","jayasinghe.ashen@gmail.com",
                "12/11/2017","Discipline of staff",
                "not resolved");
        when(cRep.save(com)).thenReturn(com);
        mockMvc.perform(
                post("/complainss/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(com)))
                .andExpect(status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}



