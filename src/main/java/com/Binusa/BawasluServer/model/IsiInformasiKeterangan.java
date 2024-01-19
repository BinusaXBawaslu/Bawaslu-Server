package com.Binusa.BawasluServer.model;


import javax.persistence.*;

@Entity
@Table(name = "isi_informasi_keterangan")
public class IsiInformasiKeterangan{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dokumen;
    @Lob
    private String pdfDokumen;

    @ManyToOne
    @JoinColumn(name = "jenis_keterangan_id")
    private JenisKeterangan jenisKeterangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDokumen() {
        return dokumen;
    }

    public void setDokumen(String dokumen) {
        this.dokumen = dokumen;
    }

    public JenisKeterangan getJenisKeterangan() {
        return jenisKeterangan;
    }

    public void setJenisKeterangan(JenisKeterangan jenisKeterangan) {
        this.jenisKeterangan = jenisKeterangan;
    }

    public String getPdfDokumen() {
        return pdfDokumen;
    }

    public void setPdfDokumen(String pdfDokumen) {
        this.pdfDokumen = pdfDokumen;
    }

}

