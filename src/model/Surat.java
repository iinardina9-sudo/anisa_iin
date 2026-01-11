package model;

public class Surat {
    private int id;
    private String nomorSurat;
    private String tanggal;
    private String pengirim;
    private String perihal;
    private String jenisSurat;

    public Surat() {}

    public Surat(int id, String nomorSurat, String tanggal, String pengirim, String perihal, String jenisSurat) {
        this.id = id;
        this.nomorSurat = nomorSurat;
        this.tanggal = tanggal;
        this.pengirim = pengirim;
        this.perihal = perihal;
        this.jenisSurat = jenisSurat;
    }

    public Surat(String nomorSurat, String tanggal, String pengirim, String perihal, String jenisSurat) {
        this.nomorSurat = nomorSurat;
        this.tanggal = tanggal;
        this.pengirim = pengirim;
        this.perihal = perihal;
        this.jenisSurat = jenisSurat;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomorSurat() { return nomorSurat; }
    public void setNomorSurat(String nomorSurat) { this.nomorSurat = nomorSurat; }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    public String getPengirim() { return pengirim; }
    public void setPengirim(String pengirim) { this.pengirim = pengirim; }

    public String getPerihal() { return perihal; }
    public void setPerihal(String perihal) { this.perihal = perihal; }

    public String getJenisSurat() { return jenisSurat; }
    public void setJenisSurat(String jenisSurat) { this.jenisSurat = jenisSurat; }
}
