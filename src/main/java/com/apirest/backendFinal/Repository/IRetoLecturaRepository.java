package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.RetoLecturaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRetoLecturaRepository extends JpaRepository<RetoLecturaModel, Integer> {
}