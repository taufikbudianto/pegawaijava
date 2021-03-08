package com.test.controller;

import com.test.model.Pegawai;
import com.test.repo.PegawaiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.awt.event.PaintEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author taufik.budiyanto
 * @date 08/03/2021
 * com.test.controller
 */
@RestController
@RequestMapping("/api")
public class DataController extends ExceptionController{

    @Autowired
    private PegawaiRepo pegawaiRepo;

    @RequestMapping("/getbyuserid")
    public ResponseEntity<Pegawai> getByUserId(@Valid @RequestParam("userid") @Pattern(message = "quantity must be a number", regexp="^[0-9]*$")
                                        Integer userid){
        Pegawai pegawai= null;
        if(pegawaiRepo.findById(userid).isPresent()){
            pegawai=pegawaiRepo.findById(userid).get();
            return ResponseEntity.ok(pegawai);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<String> savePegawai(@Valid @RequestBody Pegawai pegawai){
        pegawaiRepo.save(pegawai);
        return ResponseEntity.ok("Sukses");
    }



}
