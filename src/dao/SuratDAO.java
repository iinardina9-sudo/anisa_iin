package dao;

import config.Database;
import model.Surat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuratDAO {
    private Connection connection;

    public SuratDAO() {
        this.connection = Database.getConnection();
    }

    public void addSurat(Surat surat) {
        String sql = "INSERT INTO surat (nomor_surat, tanggal, pengirim, perihal, jenis_surat) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, surat.getNomorSurat());
            pstmt.setString(2, surat.getTanggal());
            pstmt.setString(3, surat.getPengirim());
            pstmt.setString(4, surat.getPerihal());
            pstmt.setString(5, surat.getJenisSurat());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Surat> getAllSurat() {
        List<Surat> list = new ArrayList<>();
        String sql = "SELECT * FROM surat";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Surat(
                        rs.getInt("id"),
                        rs.getString("nomor_surat"),
                        rs.getString("tanggal"),
                        rs.getString("pengirim"),
                        rs.getString("perihal"),
                        rs.getString("jenis_surat")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateSurat(Surat surat) {
        String sql = "UPDATE surat SET nomor_surat=?, tanggal=?, pengirim=?, perihal=?, jenis_surat=? WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, surat.getNomorSurat());
            pstmt.setString(2, surat.getTanggal());
            pstmt.setString(3, surat.getPengirim());
            pstmt.setString(4, surat.getPerihal());
            pstmt.setString(5, surat.getJenisSurat());
            pstmt.setInt(6, surat.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSurat(int id) {
        String sql = "DELETE FROM surat WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
