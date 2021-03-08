package com.test.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author taufik.budiyanto
 * @date 08/03/2021
 * com.test.model
 */
@Entity
@Data
public class Pegawai {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;
    @NotBlank(message = "Name is mandatory")
    @Column(length = 100,nullable = false)
    private String nama;
    @NotBlank(message = "alamat is mandatory")
    @Column(length = 250,nullable = false)
    private String alamat;
}
