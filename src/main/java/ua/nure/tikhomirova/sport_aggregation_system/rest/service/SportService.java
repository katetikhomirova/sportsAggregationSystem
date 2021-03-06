package ua.nure.tikhomirova.sport_aggregation_system.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ua.nure.tikhomirova.sport_aggregation_system.rest.dao.SportCategoryDao;
import ua.nure.tikhomirova.sport_aggregation_system.rest.dao.SportDao;
import ua.nure.tikhomirova.sport_aggregation_system.rest.model.Sport;

@Path("/sports")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@Component
public class SportService {

	private SportDao sportDao;

	private SportCategoryDao sportCategoryDao;

	@Inject
	public SportService(SportDao sportDao, SportCategoryDao sportCategoryDao) {
		this.sportDao = sportDao;
		this.sportCategoryDao = sportCategoryDao;
	}

	@GET
	public List<Sport> getAll() {
		return sportDao.findAll();
	}

	@GET
	@Path("{id}")
	public Sport getOne(@PathParam("id") Integer id) {
		Sport sport = sportDao.findOne(id);
		if (sport == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} else {
			return sport;
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Sport save(@FormParam("name") String name,
			@FormParam("command") String command,
			@FormParam("categoryName") Integer categoryId) {
		Sport sport = new Sport();
		if (command != null && command.equals("on")) {
			sport.setIsCommand((byte) 1);
		} else {
			sport.setIsCommand((byte) 0);
		}
		sport.setName(name);
		sport.setSportcategory(sportCategoryDao.findOne(categoryId));
		return sportDao.save(sport);
	}

	@PUT
	@Path("{id}")
	public Sport update(@PathParam("id") Integer id, @Valid Sport sport) {
		if (sportDao.findOne(id) == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} else {
			sport.setId(id);
			return sportDao.save(sport);
		}
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		Sport sport = sportDao.findOne(id);
		if (sport == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} else {
			sportDao.delete(sport);
		}
	}

}
