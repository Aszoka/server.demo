package com.aszoka.server.demo.repository;

import com.aszoka.server.demo.model.Server;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServerRepo extends JpaRepository <Server, Long> {

    Server findByIpAddress(String ipAddress);
}
