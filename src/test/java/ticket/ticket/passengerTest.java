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
import ticket.ticket.controllers.PassengerController;
import ticket.ticket.models.Passenger;
import ticket.ticket.repositories.passengerRep;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class passengerTest {

    private MockMvc mockMvc;


    @Mock
    private passengerRep pRep;

    @InjectMocks
    private PassengerController pController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(pController)
                .build();
    }


    @Test
    public void test_getAllPassengers() throws Exception {


        List<Passenger> pass = Arrays.asList(
                new Passenger("951221228V","Ashen","195/7,Heerassagala",
                        "0772789898","Male", "01/05/1995",
                        "jayasinghe.ashen@gmail.com","3456",
                        "12/11/2017","12/11/2018","Yes",234.00));
        when(pRep.findAll()).thenReturn(pass);
        mockMvc.perform(get("/passenger/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].pnic", Matchers.is("951221228V")))
                .andExpect(jsonPath("$[0].pname", Matchers.is("Ashen")))
                .andExpect(jsonPath("$[0].paddress", Matchers.is("195/7,Heerassagala")))
                .andExpect(jsonPath("$[0].pmobile", Matchers.is("0772789898")))
                .andExpect(jsonPath("$[0].pgender", Matchers.is("Male")))
                .andExpect(jsonPath("$[0].pdob", Matchers.is("01/05/1995")))
                .andExpect(jsonPath("$[0].cardNo", Matchers.is("3456")))
                .andExpect(jsonPath("$[0].issueDate", Matchers.is("12/11/2017")))
                .andExpect(jsonPath("$[0].expiryDate", Matchers.is("12/11/2018")))
                .andExpect(jsonPath("$[0].validity", Matchers.is("Yes")))
                .andExpect(jsonPath("$[0].balance", Matchers.is(234.00)))

                .andExpect(jsonPath("$", hasSize(1)));

       verify(pRep).findAll();
        verify(pRep, times(1)).findAll();
        verify(pRep, atLeastOnce()).findAll();

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
    public void test_create_expense_success() throws Exception {

        Passenger pass = new Passenger("951221228V","Ashen","195/7,Heerassagala",
                "0772789898","Male", "01/05/1995","jayasinghe.ashen@gmail.com","3456",
                "12/11/2017","12/11/2018","Yes",234.00);
        when(pRep.save(pass)).thenReturn(pass);
        mockMvc.perform(
                post("/passenger/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pass)))
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



