package pl.coderslab.gralicjaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.gralicjaApp.entity.TableNumber;

public interface TableNumberRepository extends JpaRepository<TableNumber, Long> {

}
