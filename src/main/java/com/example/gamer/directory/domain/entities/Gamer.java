package com.example.gamer.directory.domain.entities;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.hibernate.proxy.HibernateProxy;

import com.example.gamer.directory.domain.Gender;
import com.example.gamer.directory.domain.Geography;
import com.example.gamer.directory.domain.InterestLevel;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Gamer {
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "gamer_seq_gen")
	@SequenceGenerator(name = "gamer_seq_gen", sequenceName = "gamer_seq")
	@ToString.Include
	private Long id;
	
	@ToString.Include
	private String name;
	private String nickname;
	
	@Enumerated(EnumType.STRING)
	@ToString.Include
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	@ToString.Include
	private Geography geography;
	
	@ElementCollection
	@CollectionTable(name = "gamer_interests",
			joinColumns = @JoinColumn(name = "gamer_id")			
			)
	@Column(name = "interest_level")
	@MapKeyJoinColumn(name = "game_id")
	@Enumerated(EnumType.STRING)
	private Map<Game, InterestLevel> interests = new HashMap<>();
	
	@Version
	private int version;
	
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
		Gamer other = (Gamer) o;				
		return getId() != null && Objects.equals(getId(), other.getId());
	}
	
	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy h
				? h.getHibernateLazyInitializer().getPersistentClass().hashCode()
				: getClass().hashCode();
	}
}
