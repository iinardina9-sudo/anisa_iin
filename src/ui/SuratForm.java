package ui;

import dao.SuratDAO;
import model.Surat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SuratForm extends JFrame {
    private JTextField txtNomor, txtTanggal, txtPengirim, txtPerihal;
    private JComboBox<String> cbJenis;
    private JTable table;
    private DefaultTableModel tableModel;
    private SuratDAO dao;
    private int selectedId = -1;

    public SuratForm() {
        dao = new SuratDAO();
        setTitle("Aplikasi Surat Masuk & Keluar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel lblTitle = new JLabel("Manajemen Surat", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle, BorderLayout.NORTH);

        // Form Panel
        JPanel pnlForm = new JPanel(new GridLayout(6, 2, 5, 5));
        pnlForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pnlForm.add(new JLabel("Nomor Surat:"));
        txtNomor = new JTextField();
        pnlForm.add(txtNomor);

        pnlForm.add(new JLabel("Tanggal (YYYY-MM-DD):"));
        txtTanggal = new JTextField();
        pnlForm.add(txtTanggal);

        pnlForm.add(new JLabel("Pengirim/Penerima:"));
        txtPengirim = new JTextField();
        pnlForm.add(txtPengirim);

        pnlForm.add(new JLabel("Perihal:"));
        txtPerihal = new JTextField();
        pnlForm.add(txtPerihal);

        pnlForm.add(new JLabel("Jenis Surat:"));
        cbJenis = new JComboBox<>(new String[]{"Masuk", "Keluar"});
        pnlForm.add(cbJenis);

        // Buttons
        JPanel pnlButtons = new JPanel();
        JButton btnAdd = new JButton("Simpan");
        JButton btnUpdate = new JButton("Edit");
        JButton btnDelete = new JButton("Hapus");
        JButton btnReset = new JButton("Bersihkan");

        pnlButtons.add(btnAdd);
        pnlButtons.add(btnUpdate);
        pnlButtons.add(btnDelete);
        pnlButtons.add(btnReset);
        pnlForm.add(pnlButtons);

        add(pnlForm, BorderLayout.WEST);

        // Table
        String[] columns = {"ID", "Nomor Surat", "Tanggal", "Pengirim/Penerima", "Perihal", "Jenis"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load Data
        loadData();

        // Event Listeners
        btnAdd.addActionListener(e -> {
            Surat s = new Surat(txtNomor.getText(), txtTanggal.getText(), txtPengirim.getText(), txtPerihal.getText(), (String) cbJenis.getSelectedItem());
            dao.addSurat(s);
            loadData();
            clearFields();
        });

        btnUpdate.addActionListener(e -> {
            if (selectedId != -1) {
                Surat s = new Surat(selectedId, txtNomor.getText(), txtTanggal.getText(), txtPengirim.getText(), txtPerihal.getText(), (String) cbJenis.getSelectedItem());
                dao.updateSurat(s);
                loadData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data di tabel terlebih dahulu.");
            }
        });

        btnDelete.addActionListener(e -> {
            if (selectedId != -1) {
                dao.deleteSurat(selectedId);
                loadData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data di tabel terlebih dahulu.");
            }
        });

        btnReset.addActionListener(e -> clearFields());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                selectedId = (int) tableModel.getValueAt(row, 0);
                txtNomor.setText((String) tableModel.getValueAt(row, 1));
                txtTanggal.setText((String) tableModel.getValueAt(row, 2));
                txtPengirim.setText((String) tableModel.getValueAt(row, 3));
                txtPerihal.setText((String) tableModel.getValueAt(row, 4));
                cbJenis.setSelectedItem(tableModel.getValueAt(row, 5));
            }
        });
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Surat> list = dao.getAllSurat();
        for (Surat s : list) {
            tableModel.addRow(new Object[]{s.getId(), s.getNomorSurat(), s.getTanggal(), s.getPengirim(), s.getPerihal(), s.getJenisSurat()});
        }
    }

    private void clearFields() {
        txtNomor.setText("");
        txtTanggal.setText("");
        txtPengirim.setText("");
        txtPerihal.setText("");
        cbJenis.setSelectedIndex(0);
        selectedId = -1;
        table.clearSelection();
    }
}
