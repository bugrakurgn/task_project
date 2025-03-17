package com.example.task_project.repository;

import com.example.task_project.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<WeatherEntity, String> {

    Optional<WeatherEntity> findFirstByRequestedCityNameOrderByUpdateTimeDesc(String city);

    //List<WeatherEntity> findAllByRequestedCityNameOOrderByUpdatedTimeDesc(String city);
}
