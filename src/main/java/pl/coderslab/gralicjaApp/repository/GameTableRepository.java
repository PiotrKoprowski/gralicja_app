package pl.coderslab.gralicjaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.gralicjaApp.entity.GameTable;

public interface GameTableRepository extends JpaRepository<GameTable, Long> {
	
//	@Query("select * from game_tables join game_tables_users on game_tables.id = game_tables_users.gameTables_id and game_tables_users.users_id = ?1")
//	List<GameTable> findByParam(long userId);
	
//	List<GameTable> findByUsers(User user);

}
