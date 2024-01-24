package com.example.gamer.directory.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@IdClass(CreditId.class)
@Getter
@Setter
@ToString
public class Credit {
	@Id
	@ManyToOne
	private Gamer gamer;
	
	@Id
	@ManyToOne
	private Game game;
	
	private Integer amount;
}
