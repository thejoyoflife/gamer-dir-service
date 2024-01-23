package com.example.gamer.directory.domain.entities;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.Objects;

import org.hibernate.proxy.HibernateProxy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Game {
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "game_seq_gen")
	@SequenceGenerator(name = "game_seq_gen", sequenceName = "game_seq")
	private Integer id;
	
	private String name;
	
	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffClass = o instanceof HibernateProxy h 
								? h.getHibernateLazyInitializer().getPersistentClass()
								: o.getClass();
		Class<?> thisEffClass = o instanceof HibernateProxy h
								? h.getHibernateLazyInitializer().getPersistentClass()
								: o.getClass();
		if (oEffClass != thisEffClass) return false;
		Game other = (Game) o;				
		return getName() != null && Objects.equals(getName(), other.getName());
	}
	
	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy h
				? h.getHibernateLazyInitializer().getPersistentClass().hashCode()
				: getClass().hashCode();
	}
}
