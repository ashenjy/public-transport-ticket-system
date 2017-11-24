package ticket.ticket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ticket.ticket.models.PaymentL;
import ticket.ticket.repositories.PaymentLRepository;

import java.util.List;

/**
 * Created by lahiru on 11/24/2017.
 */

@RestController
public class PaymentLController
{
    @Autowired
    PaymentLRepository paymentLRepository;

    @PostMapping("/createPaymentL")
    public boolean createPaymentL(@RequestBody PaymentL paymentL)
    {
        paymentLRepository.save(paymentL);
        return true;
    }

    @GetMapping("/getPaymentL")
    public List<PaymentL> getPaymentL()
    {
        return paymentLRepository.findAll();
    }

}
