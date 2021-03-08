package com.test.repo;

import com.test.model.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author taufik.budiyanto
 * @date 08/03/2021
 * com.test.repo
 */
@Repository
public interface PegawaiRepo extends JpaRepository<Pegawai,Integer>, JpaSpecificationExecutor<Pegawai> {
}
