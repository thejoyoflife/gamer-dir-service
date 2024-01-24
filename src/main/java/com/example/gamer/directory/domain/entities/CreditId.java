package com.example.gamer.directory.domain.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CreditId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long gamer;
	private Integer game;

}
