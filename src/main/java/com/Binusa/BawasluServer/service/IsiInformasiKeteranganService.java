package com.Binusa.BawasluServer.service;

import com.Binusa.BawasluServer.DTO.IsiInformasiKeteranganApiResponseDTO;
import com.Binusa.BawasluServer.DTO.IsiInformasiKeteranganDTO;
import com.Binusa.BawasluServer.model.IsiInformasiKeterangan;
import com.Binusa.BawasluServer.model.JenisKeterangan;
import com.Binusa.BawasluServer.repository.IsiInformasiKeteranganRepository;
import com.Binusa.BawasluServer.repository.JenisKeteranganRepository;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
public class IsiInformasiKeteranganService {
    @Autowired
    private JenisKeteranganRepository jenisKeteranganRepository;
    @Autowired
    private IsiInformasiKeteranganRepository isiInformasiKeteranganRepository;


    public IsiInformasiKeteranganApiResponseDTO save(IsiInformasiKeteranganDTO isiInformasiKeteranganDTO) {
        IsiInformasiKeterangan isiInformasiKeterangan = new IsiInformasiKeterangan();
        isiInformasiKeterangan.setId(1L);
        isiInformasiKeterangan.setDokumen(isiInformasiKeteranganDTO.getDokumen());

        JenisKeterangan jenisKeterangan = jenisKeteranganRepository.findById(isiInformasiKeteranganDTO.getJenisKeteranganId())
                .orElseThrow(() -> new EntityNotFoundException("JenisKeterangan not found with id: " + isiInformasiKeteranganDTO.getJenisKeteranganId()));

        isiInformasiKeterangan.setJenisKeterangan(jenisKeterangan);

        isiInformasiKeterangan.setPdfDokumen(isiInformasiKeteranganDTO.getPdfDokumen());

        IsiInformasiKeterangan savedIsiInformasiKeterangan = isiInformasiKeteranganRepository.save(isiInformasiKeterangan);
        return convertToApiResponseDTO(savedIsiInformasiKeterangan);
    }

    public IsiInformasiKeteranganApiResponseDTO update(Long id, IsiInformasiKeteranganDTO isiInformasiKeteranganDTO) {
        IsiInformasiKeterangan existingIsiInformasiKeterangan = isiInformasiKeteranganRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("IsiInformasiKeterangan not found with id: " + id));

        existingIsiInformasiKeterangan.setDokumen(isiInformasiKeteranganDTO.getDokumen());

        JenisKeterangan jenisKeterangan = jenisKeteranganRepository.findById(isiInformasiKeteranganDTO.getJenisKeteranganId())
                .orElseThrow(() -> new EntityNotFoundException("JenisKeterangan not found with id: " + isiInformasiKeteranganDTO.getJenisKeteranganId()));

        existingIsiInformasiKeterangan.setJenisKeterangan(jenisKeterangan);

        existingIsiInformasiKeterangan.setPdfDokumen(isiInformasiKeteranganDTO.getPdfDokumen());

        IsiInformasiKeterangan updatedIsiInformasiKeterangan = isiInformasiKeteranganRepository.save(existingIsiInformasiKeterangan);
        return convertToApiResponseDTO(updatedIsiInformasiKeterangan);
    }

    public IsiInformasiKeteranganApiResponseDTO findById(Long id) {
        IsiInformasiKeterangan isiInformasiKeterangan = isiInformasiKeteranganRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("IsiInformasiKeterangan not found with id: " + id));

        return convertToApiResponseDTO(isiInformasiKeterangan);
    }

    public Page<IsiInformasiKeteranganApiResponseDTO> getAllIsiInformasiKeterangan(Pageable pageable) {
        // Menggunakan repository.findAll dengan pageable
        Page<IsiInformasiKeterangan> isiInformasiKeteranganPage = isiInformasiKeteranganRepository.findAll(pageable);

        // Mengubah halaman IsiInformasiKeterangan menjadi halaman DTO
        return isiInformasiKeteranganPage.map(this::convertToApiResponseDTO);
    }

    public void delete(Long id) {
        IsiInformasiKeterangan isiInformasiKeterangan = isiInformasiKeteranganRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("IsiInformasiKeterangan not found with id: " + id));
        isiInformasiKeteranganRepository.delete(isiInformasiKeterangan);
    }

    private IsiInformasiKeteranganApiResponseDTO convertToApiResponseDTO(IsiInformasiKeterangan isiInformasiKeterangan) {
        IsiInformasiKeteranganApiResponseDTO isiInformasiKeteranganDTO = new IsiInformasiKeteranganApiResponseDTO();
        isiInformasiKeteranganDTO.setId(isiInformasiKeterangan.getId());
        isiInformasiKeteranganDTO.setDokumen(isiInformasiKeterangan.getDokumen());
        isiInformasiKeteranganDTO.setPdfDokumen(isiInformasiKeterangan.getPdfDokumen());
        isiInformasiKeteranganDTO.setJenisKeterangan(isiInformasiKeterangan.getJenisKeterangan().getId());
        return isiInformasiKeteranganDTO;
    }

    private IsiInformasiKeteranganApiResponseDTO mapIsiInformasiKeteranganToDTO(IsiInformasiKeterangan isiInformasiKeterangan) {
        IsiInformasiKeteranganApiResponseDTO dto = new IsiInformasiKeteranganApiResponseDTO();
        dto.setId(isiInformasiKeterangan.getId());
        dto.setDokumen(isiInformasiKeterangan.getDokumen());
        dto.setPdfDokumen(isiInformasiKeterangan.getPdfDokumen());
        dto.setJenisKeterangan(isiInformasiKeterangan.getJenisKeterangan().getId());

        return dto;
    }

}
