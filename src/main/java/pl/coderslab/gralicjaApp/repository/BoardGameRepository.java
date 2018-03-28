package pl.coderslab.gralicjaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.gralicjaApp.entity.BoardGame;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long>{

}
