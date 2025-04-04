package Triptop.Applicatie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Triptop.Applicatie.model.Betaling;

@Repository
public interface BetalingRepository extends JpaRepository<Betaling, String> {
    // You can add custom query methods here if needed
}