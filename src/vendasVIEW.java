import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

public class vendasVIEW extends JPanel {

    private JTable tabela;
    private JButton btnVoltar;

    public vendasVIEW() {

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel(
                "Lista de Produtos Vendidos",
                JLabel.CENTER
        );

        add(titulo, BorderLayout.NORTH);

        tabela = new JTable();

        tabela.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Nome", "Valor", "Status"
                }
        ));

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {
            setVisible(false);
        });

        painelInferior.add(btnVoltar);

        add(painelInferior, BorderLayout.SOUTH);

        listarProdutosVendidos();
    }

    private void listarProdutosVendidos() {

        ProdutosDAO dao = new ProdutosDAO();

        ArrayList<ProdutosDTO> lista =
                dao.listarProdutosVendidos();

        DefaultTableModel modelo =
                (DefaultTableModel) tabela.getModel();

        modelo.setRowCount(0);

        for (ProdutosDTO produto : lista) {

            modelo.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getStatus()
            });
        }
    }
}