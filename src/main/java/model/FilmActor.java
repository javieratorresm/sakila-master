package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the film_actor database table.
 * 
 */
@Entity
@Table(name="film_actor")
@IdClass(FilmActorKey.class)
@NamedQuery(name="FilmActor.findAll", query="SELECT fa FROM FilmActor fa")
public class FilmActor implements Serializable {
	private static final long serialVersionUID = 3L;

	@Id
	@Column(name="actor_id", unique=true, nullable=false)
	private int actorId;

	@Id
	@Column(name="film_id", unique=true, nullable=false)
	private int filmId;
	
	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;

	public FilmActor() {
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	

}