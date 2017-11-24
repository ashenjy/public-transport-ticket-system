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
import ticket.ticket.controllers.mailController;
import ticket.ticket.models.Mail;
import ticket.ticket.repositories.mailRep;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class mailTest {

    private MockMvc mockMvc;


    @Mock
    private mailRep mRep;

    @InjectMocks
    private mailController mailC;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(mailC)
                .build();
    }


    @Test
    public void test_getAll() throws Exception {


        List<Mail> maild = Arrays.asList(
                new Mail("ewisms@gmail.com","ewisms@gmail.com",
                        "Test Email","Test Message","11/12/2017"));
        when(mRep.findAll()).thenReturn(maild);
        mockMvc.perform(get("/mail/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].mailFrom", Matchers.is("ewisms@gmail.com")))
                .andExpect(jsonPath("$[0].mailTo", Matchers.is("ewisms@gmail.com")))
                .andExpect(jsonPath("$[0].mailSubject", Matchers.is("Test Email")))
                .andExpect(jsonPath("$[0].mailContent", Matchers.is("Test Message")))
                .andExpect(jsonPath("$[0].mailDate", Matchers.is("11/12/2017")))
                .andExpect(jsonPath("$", hasSize(1)));


       verify(mRep).findAll();
        verify(mRep, times(1)).findAll();
        verify(mRep, atLeastOnce()).findAll();

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
    public void test_create_maild_success() throws Exception {

        Mail maild = new Mail("ewisms@gmail.com","ewisms@gmail.com",
                "Test Email","Test Message","11/12/2017");
        when(mRep.save(maild)).thenReturn(maild);
        mockMvc.perform(
                post("/mail/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(maild)))
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



