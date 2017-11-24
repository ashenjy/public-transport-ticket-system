package ticket.ticket.controllers;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import ticket.ticket.models.Mail;
import ticket.ticket.models.Mail;
import ticket.ticket.repositories.mailRep;
import ticket.ticket.repositories.mailRep;

import java.util.List;

@RestController
@RequestMapping(value = "/mail")
@Api(
		name = "Ticket API",
		description = "Provides a list of methods that manage ticket operations",
		stage = ApiStage.RC)
public class mailController {


	private mailRep repository;


	@Autowired
	public mailController(mailRep repository){
		this.repository = repository;
	}

	@Autowired
	private JavaMailSender emailSender;

	@RequestMapping(value = "/all",  method = RequestMethod.GET)
	public List<Mail> getAll() {

		return repository.findAll();
	}


	@RequestMapping(value="/save",  method = RequestMethod.POST)
	public List<Mail> save(@RequestBody Mail passenger) {

		System.out.println("Save data set" + passenger);
		repository.save(passenger);

		return repository.findAll();
	}

//	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//	public List<Mail> update(@ApiPathParam(name = "id") @PathVariable long id , @RequestBody Mail passenger) {
//
//		Mail update = repository.findOne(id);
//		update.setPnic(passenger.getPnic());
//		update.setPname(passenger.getPname());
//		update.setPaddress(passenger.getPaddress());
//		update.setPmobile(passenger.getPmobile());
//		update.setPgender(passenger.getPgender());
//		update.setPdob(passenger.getPdob());
//		update.setCardNo(passenger.getCardNo());
//		update.setIssueDate(passenger.getIssueDate());
//		update.setExpiryDate(passenger.getExpiryDate());
//		update.setValidity(passenger.getValidity());
//		update.setBalance(passenger.getBalance());
//
//		repository.save(update);
//
//		return repository.findAll();
//	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public List<Mail> remove(@ApiPathParam(name = "id") @PathVariable long id) {

		System.out.println("id = " + id);

		repository.delete(id);

		return repository.findAll();
	}

	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public List<Mail> send(@RequestBody Mail mail) {
		String senderAddress = mail.getMailFrom();
		String recipientAddress = mail.getMailTo();
		String subject = mail.getMailSubject();
		String message = mail.getMailContent();

		// prints debug info
		System.out.println("From: " + senderAddress);
		System.out.println("To: " + recipientAddress);
		System.out.println("Subject: " + subject);
		System.out.println("Message: " + message);

		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom(senderAddress);
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message);

		// sends the e-mail
		emailSender.send(email);

		repository.save(mail);

		// forwards to the view named "Result"
		return repository.findAll();
	}


}