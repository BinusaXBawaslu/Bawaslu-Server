package com.Binusa.BawasluServer.DTO;

public class RegulasiDTO {
    private long id;
    private String dokumen;
    private Long menuRegulasi;
    private String pdfDokumen;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDokumen() {
        return dokumen;
    }

    public void setDokumen(String dokumen) {
        this.dokumen = dokumen;
    }

    public Long getMenuRegulasi() {
        return menuRegulasi;
    }

    public void setMenuRegulasi(Long menuRegulasi) {
        this.menuRegulasi = menuRegulasi;
    }

    public String getPdfDokumen() {
        return pdfDokumen;
    }

    public void setPdfDokumen(String pdfDokumen) {
        this.pdfDokumen = pdfDokumen;
    }
}
