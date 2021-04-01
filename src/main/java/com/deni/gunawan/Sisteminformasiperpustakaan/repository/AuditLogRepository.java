package com.deni.gunawan.Sisteminformasiperpustakaan.repository;

import com.deni.gunawan.Sisteminformasiperpustakaan.model.AuditLog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends PagingAndSortingRepository<AuditLog, String>{



}

