package com.Binusa.BawasluServer.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "jenis_keterangan")
public class JenisKeterangan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keterangan;

    @ManyToOne
    @JoinColumn(name = "jenis_informasi_id")
    private JenisInformasi jenisInformasi;

    @OneToMany(mappedBy = "jenisKeterangan")
    private List<IsiInformasiKeterangan> isiInformasiKeteranganList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public JenisInformasi getJenisInformasi() {
        return jenisInformasi;
    }

    public void setJenisInformasi(JenisInformasi jenisInformasi) {
        this.jenisInformasi = jenisInformasi;
    }

    public List<IsiInformasiKeterangan> getIsiInformasiKeteranganList() {
        return isiInformasiKeteranganList;
    }

    public void setIsiInformasiKeteranganList(List<IsiInformasiKeterangan> isiInformasiKeteranganList) {
        this.isiInformasiKeteranganList = isiInformasiKeteranganList;
    }
}