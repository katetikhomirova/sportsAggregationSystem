package ua.nure.tikhomirova.sport_aggregation_system.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.nure.tikhomirova.sport_aggregation_system.rest.model.Unit;

public interface UnitDao extends JpaRepository<Unit, Integer> {

}
