package ticket.ticket.controllers;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ticket.ticket.models.Passenger;
import ticket.ticket.repositories.passengerRep;

import java.util.List;

@RestController
@RequestMapping(value = "/passenger")
@Api(
		name = "Hotel API",
		description = "Provides a list of methods that manage hotel operations",
		stage = ApiStage.RC)
public class PassengerController {


	private passengerRep repository;

	@Autowired
	public PassengerController(passengerRep repository){
		this.repository = repository;
	}


	@RequestMapping(value = "/all",  method = RequestMethod.GET)
	public List<Passenger> getAll() {

		return repository.findAll();
	}

//	@RequestMapping(value = "/getOne/{id}",  method = RequestMethod.GET)
//	public List<Passenger> getOne(@ApiPathParam(name = "id") @PathVariable long id) {
//
//		System.out.println("get one" + id);
//
//		return repository.findById(id);
//	}

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
		update.setCardNo(passenger.getCardNo());


		repository.save(update);

		return repository.findAll();
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public List<Passenger> remove(@ApiPathParam(name = "id") @PathVariable long id) {

		System.out.println("id = " + id);

		repository.delete(id);

		return repository.findAll();
	}


}