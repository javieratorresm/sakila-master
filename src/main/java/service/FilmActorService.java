package service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.ActorFacade;
import facade.FilmActorFacade;
import facade.FilmFacade;
import model.Actor;
import model.Film;
import model.FilmActor;
import model.FilmActorKey;

@Path("/films_actors")
public class FilmActorService {
	
	@EJB 
	FilmActorFacade filmActorFacadeEJB;
	@EJB
	ActorFacade actorFacadeEJB;
	@EJB
	FilmFacade filmFacadeEJB;
	
	Logger logger = Logger.getLogger(FilmActorService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<FilmActor> findAll(){
		return filmActorFacadeEJB.findAll();
	}
	
	@GET
    @Path("findActorsByFilm/{idFilm}")
    @Produces({"application/xml", "application/json"})
    public List<Actor> findActorsByFilm(@PathParam("idFilm") Integer idFilm) {
		List<FilmActor> listaFA = filmActorFacadeEJB.findAll();
		List<Actor> listaA = actorFacadeEJB.findAll();
		List<Actor> result = new ArrayList<Actor>(50);
		for(FilmActor fa : listaFA){
			if(idFilm == fa.getFilmId()){
				for(Actor a: listaA){
					if(a.getActorId() == fa.getActorId()){
						result.add(a);
					}
				}
			}
		}
		return result;
    }
	
	@GET
    @Path("findFilmsByActor/{idActor}")
    @Produces({"application/xml", "application/json"})
    public List<Film> findFilmsByActor(@PathParam("idActor") Integer idActor) {
		List<FilmActor> listaFA = filmActorFacadeEJB.findAll();
		List<Film> listaF = filmFacadeEJB.findAll();
		List<Film> result = new ArrayList<Film>(50);
		for(FilmActor fa : listaFA){
			if(idActor == fa.getActorId()){
				for(Film f: listaF){
					if(f.getFilmId() == fa.getFilmId()){
						result.add(f);
					}
				}
			}
		}
		return result;
    }

	
	@GET
    @Path("{idFilm}/{idActor}")
    @Produces({"application/xml", "application/json"})
    public FilmActor find(@PathParam("idFilm") Integer idFilm, @PathParam("idActor") Integer idActor) {
		FilmActorKey key = new FilmActorKey(idFilm,idActor);
		return filmActorFacadeEJB.find(key);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(FilmActor entity) {
        filmActorFacadeEJB.create(entity);
    }

	/**
	 * Edita por id de filmActor
	 * @param idFilm
	 * @param idActor
	 * @param entity
	 */
    @PUT
    @Path("{idFilm}/{idActor}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("idFilm") Integer idFilm, @PathParam("idActor") Integer idActor, FilmActor entity) {
    	entity.setFilmId(idFilm.intValue());
    	entity.setActorId(idActor.intValue());
    	filmActorFacadeEJB.edit(entity);
    }
	

}


