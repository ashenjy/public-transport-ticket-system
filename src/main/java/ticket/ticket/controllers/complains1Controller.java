package ticket.ticket.controllers;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ticket.ticket.models.complains1;
import ticket.ticket.repositories.complains1Rep;

import java.util.List;

@RestController
@RequestMapping(value = "/complainss")
@Api(
		name = "Ticket API",
		description = "Provides a list of methods that manage ticket operations",
		stage = ApiStage.RC)
public class complains1Controller {


	private complains1Rep repository;


	@Autowired
	public complains1Controller(complains1Rep repository){
		this.repository = repository;
	}


	@RequestMapping(value = "/all",  method = RequestMethod.GET)
	public List<complains1> getAll() {

		return repository.findAll();
	}


	@RequestMapping(value="/save",  method = RequestMethod.POST)
	public List<complains1> save(@RequestBody complains1 complainss) {

		System.out.println("Save data set" + complainss);
		repository.save(complainss);

		return repository.findAll();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public List<complains1> update(@ApiPathParam(name = "id") @PathVariable long id , @RequestBody complains1 complainss) {

		complains1 update = repository.findOne(id);
		update.setPassengerName(complainss.getPassengerName());
		update.setPemail(complainss.getPemail());
		update.setComplainDesc(complainss.getComplainDesc());
		update.setComplainDesc(complainss.getComplainDesc());;
		update.setComplainStatus(complainss.getComplainStatus());

		repository.save(update);

		return repository.findAll();
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public List<complains1> remove(@ApiPathParam(name = "id") @PathVariable long id) {

		System.out.println("id = " + id);

		repository.delete(id);

		return repository.findAll();
	}


}