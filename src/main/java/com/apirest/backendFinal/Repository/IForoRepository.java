package com.apirest.backendFinal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.backendFinal.Model.ForoModel;

public interface IForoRepository extends JpaRepository<ForoModel, Integer>{

}
