package com.platform.yourregimen.diet.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platform.yourregimen.diet.model.InfoApi;

public interface InfoApiRepository extends JpaRepository<InfoApi, UUID> {

	public List<InfoApi> findByInfoApiContainingIgnoreCase(String infoApi);

}