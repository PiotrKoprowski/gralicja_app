package pl.coderslab.gralicjaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.gralicjaApp.entity.TableReservation;

public interface TableReservationRepository extends JpaRepository<TableReservation, Long> {

}
