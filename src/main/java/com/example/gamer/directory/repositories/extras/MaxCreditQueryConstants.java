package com.example.gamer.directory.repositories.extras;

public class MaxCreditQueryConstants {
	public static final String GAME_ID = "game_id";
	public static final String GAME_NAME = "game_name";
	public static final String INTEREST_LEVEL = "interest_level";
	public static final String GAMER_ID = "gamer_id";
	public static final String GAMER_NAME = "gamer_name";
	public static final String CREDIT = "credit";
	public static final String UNKNOWN_INTEREST_LEVEL = "UNKNOWN";
	
	
	public static final String MAX_CREDIT_CALCULATION_QUERY = """
			select
				f.game_id,
				f.game_name,
				f.interest_level,
				f.gamer_id,
				f.gamer_name,
				f.credit
			from
				(
				select
					e.*,
					m."name" gamer_name,
					rank() over (partition by e.game_id,
					e.interest_level
				order by
					e.credit desc) credit_rank
				from
					(
					select
						coalesce(d.gamer_id, c.gamer_id) gamer_id,
						coalesce(d.game_id, c.game_id) game_id,
						coalesce(d.game_name, c.game_name) game_name,
						coalesce(d.interest_level, '%s') interest_level,
						coalesce(c.amount, 0) credit
					from
						(
						select
							gi.gamer_id,
							g.id game_id,
							g.name game_name,
							gi.interest_level
						from
							game g
						left outer join gamer_interests gi on
							g.id = gi.game_id) d
					full outer join 
			(
						select
							c.game_id,
							g.name game_name,
							c.gamer_id,
							c.amount
						from
							credit c
						inner join game g on
							c.game_id = g.id) c
			on
						d.gamer_id = c.gamer_id
						and d.game_id = c.game_id) e
				left outer join gamer m on
					e.gamer_id = m.id) f
			where
				f.credit_rank = 1
				and f.credit > 0
				and f.gamer_id is not null
			order by
				f.game_id,
				f.interest_level
			""".formatted(UNKNOWN_INTEREST_LEVEL);
}
