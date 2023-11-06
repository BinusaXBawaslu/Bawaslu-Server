package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.DTO.PengumumanDTO;
import com.Binusa.BawasluServer.model.Pengumuman;
import com.Binusa.BawasluServer.response.CommonResponse; // Impor kelas CommonResponse
import com.Binusa.BawasluServer.service.PengumumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PengumumanController {

    @Autowired
    private PengumumanService pengumumanService;

    @RequestMapping(value = "/pengumuman/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CommonResponse<PengumumanDTO>> createpengumuman(@RequestBody PengumumanDTO pengumuman) throws SQLException, ClassNotFoundException {
        CommonResponse<PengumumanDTO> response = new CommonResponse<>();
        try {
            pengumumanService.save(pengumuman);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(pengumuman);
            response.setMessage("Pengumuman created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to create pengumuman: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/pengumuman", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CommonResponse<List<Pengumuman>>> listAllPengumuman() throws SQLException, ClassNotFoundException {
        CommonResponse<List<Pengumuman>> response = new CommonResponse<>();
        try {
            List<Pengumuman> pengumuman = pengumumanService.findAll();
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(pengumuman);
            response.setMessage("Pengumuman list retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to retrieve pengumuman list: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/pengumuman/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CommonResponse<PengumumanDTO>> updatePengumuman(@PathVariable("id") long id, @RequestBody PengumumanDTO pengumuman) throws SQLException, ClassNotFoundException {
        CommonResponse<PengumumanDTO> response = new CommonResponse<>();
        try {
            Optional<Pengumuman> currentPengumuman = pengumumanService.findById(id);

            if (!currentPengumuman.isPresent()) {
                response.setStatus("error");
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setData(null);
                response.setMessage("Pengumuman with id " + id + " not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Update pengumuman here...

            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(pengumuman);
            response.setMessage("Pengumuman updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to update pengumuman: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/pengumuman/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CommonResponse<String>> deletepengumuman(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        CommonResponse<String> response = new CommonResponse<>();
        try {
            pengumumanService.delete(id);
            response.setStatus("success");
            response.setCode(HttpStatus.NO_CONTENT.value());
            response.setData("Pengumuman deleted successfully.");
            response.setMessage("Pengumuman with id " + id + " deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Failed to delete pengumuman: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}