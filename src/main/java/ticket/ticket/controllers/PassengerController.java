package ticket.ticket.controllers;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import ticket.ticket.models.Mail;
import ticket.ticket.models.Passenger;
import ticket.ticket.repositories.mailRep;
import ticket.ticket.repositories.passengerRep;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@RestController
@RequestMapping(value = "/passenger")
@Api(
		name = "Ticket API",
		description = "Provides a list of methods that manage ticket operations",
		stage = ApiStage.RC)
public class PassengerController {


	private passengerRep repository;


	@Autowired
	public PassengerController(passengerRep repository){
		this.repository = repository;
	}

	@Autowired
	private JavaMailSender emailSender;

	@RequestMapping(value = "/all",  method = RequestMethod.GET)
	public List<Passenger> getAll() {

		return repository.findAll();
	}


	@RequestMapping(value="/save",  method = RequestMethod.POST)
	public List<Passenger> save(@RequestBody Passenger passenger) {

		System.out.println("Save data set" + passenger);
		repository.save(passenger);

		return repository.findAll();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public List<Passenger> update(@ApiPathParam(name = "id") @PathVariable long id , @RequestBody Passenger passenger) {

		Passenger update = repository.findOne(id);
		update.setPnic(passenger.getPnic());
		update.setPname(passenger.getPname());
		update.setPaddress(passenger.getPaddress());
		update.setPmobile(passenger.getPmobile());
		update.setPgender(passenger.getPgender());
		update.setPdob(passenger.getPdob());
		update.setPassengerEmail(passenger.getPassengerEmail());
		update.setCardNo(passenger.getCardNo());
		update.setIssueDate(passenger.getIssueDate());
		update.setExpiryDate(passenger.getExpiryDate());
		update.setValidity(passenger.getValidity());
		update.setBalance(passenger.getBalance());

		repository.save(update);

		return repository.findAll();
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public List<Passenger> remove(@ApiPathParam(name = "id") @PathVariable long id) {

		System.out.println("id = " + id);

		repository.delete(id);

		return repository.findAll();
	}

	@RequestMapping(value = "/sendEmailPassenger", method = RequestMethod.POST)
	public void send(@RequestBody Passenger pass) {

		String recipientAddress = pass.getPassengerEmail();
		String subject = "Public Ticketing System Registration";
		String message = "Dear "+pass.getPname()+",\n\nYou have successfully registered for Public Transport Ticketing System.\nThis is a system generated email.Do not reply.\n\nThank You.\nPublic Transport System Portal." ;

		// prints debug info
		System.out.println("To: " + recipientAddress);
		System.out.println("Subject: " + subject);
		System.out.println("Message: " + message);

		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message);

		// sends the e-mail
		emailSender.send(email);


	}



}