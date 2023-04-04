package com.jsp.medlife.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.medlife.Model.Entities.ImageStore;

@Repository
public interface ImageRepo  extends JpaRepository<ImageStore, Integer>
{
  
}
