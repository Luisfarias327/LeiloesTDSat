import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;

    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    // CADASTRAR PRODUTO
    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        conn = new conectaDAO().connectDB();

        try {

            prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

            JOptionPane.showMessageDialog(
                null,
                "Produto cadastrado com sucesso!"
            );

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                null,
                "Erro ao cadastrar produto: " + erro.getMessage()
            );
        }
    }

    // VENDER PRODUTO
    public void venderProduto(Integer id) {

        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        conn = new conectaDAO().connectDB();

        try {

            prep = conn.prepareStatement(sql);

            prep.setInt(1, id);

            int resultado = prep.executeUpdate();

            if (resultado > 0) {

                JOptionPane.showMessageDialog(
                    null,
                    "Produto vendido com sucesso!"
                );

            } else {

                JOptionPane.showMessageDialog(
                    null,
                    "Produto não encontrado!"
                );
            }

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                null,
                "Erro ao vender produto: " + erro.getMessage()
            );
        }
    }

    // LISTAR PRODUTOS VENDIDOS
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {

        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        String sql = "SELECT id, nome, valor, status "
                   + "FROM produtos "
                   + "WHERE status = 'Vendido'";

        conn = new conectaDAO().connectDB();

        try {

            prep = conn.prepareStatement(sql);

            resultset = prep.executeQuery();

            while (resultset.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                lista.add(produto);
            }

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                null,
                "Erro ao listar produtos vendidos: "
                + erro.getMessage()
            );
        }

        return lista;
    }

    // LISTAR TODOS OS PRODUTOS
    public ArrayList<ProdutosDTO> listarProdutos() {

        listagem.clear();

        String sql = "SELECT id, nome, valor, status FROM produtos";

        conn = new conectaDAO().connectDB();

        try {

            prep = conn.prepareStatement(sql);

            resultset = prep.executeQuery();

            while (resultset.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                null,
                "Erro ao listar produtos: "
                + erro.getMessage()
            );
        }

        return listagem;
    }
}