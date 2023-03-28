import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDaVelha extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new JogoDaVelha();
    }
    private JButton[][] botoes;
    private int[][] tabuleiro;
    private boolean jogadorAtual;

    public JogoDaVelha() {
        // Cria a janela principal
        super("Jogo da Velha");

        // Cria os componentes Swing
        botoes = new JButton[3][3];
        tabuleiro = new int[3][3];

        JPanel tabuleiroPanel = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                botoes[i][j].setFont(new Font("Arial", Font.PLAIN, 80));
                botoes[i][j].addActionListener(this);
                tabuleiroPanel.add(botoes[i][j]);
                tabuleiro[i][j] = 0;
            }
        }

        // Adiciona o painel de botões na janela principal
        getContentPane().add(tabuleiroPanel);

        // Configura a janela principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
        setResizable(false);
    }

    public void reiniciaJogo(){
        for (int i=0; i <3; i++){
            for (int j = 0; j < 3; j++){
                tabuleiro[i][j] = 0;
                botoes[i][j].setText("");
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        JButton botao = (JButton) e.getSource();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botao == botoes[i][j] && tabuleiro[i][j] == 0) {
                    if (jogadorAtual) {
                        botao.setText("X");
                        tabuleiro[i][j] = 1;
                    } else {
                        botao.setText("O");
                        tabuleiro[i][j] = -1;
                    }

                    jogadorAtual = !jogadorAtual;

                    int resultado = verificaJogo();

                    if (resultado == 1) {
                        JOptionPane.showMessageDialog(this, "Jogador 1 (X) ganhou!");
                        reiniciaJogo();
                    } else if (resultado == -1) {
                        JOptionPane.showMessageDialog(this, "Jogador 2 (O) ganhou!");
                        reiniciaJogo();
                    } else if (resultado == 0) {
                        JOptionPane.showMessageDialog(this, "Jogo empatado!");
                        reiniciaJogo();
                    }
                }
            }
        }
    }

    private int verificaJogo(){
        // Verifica as linhas
        for (int i = 0; i < 3; i++){
            int somalinha = 0;
            for (int j = 0; j <3; j++){
                somalinha += tabuleiro[i][j];
            }
            if (somalinha ==3) {
                return 1;
            }else if(somalinha == -3){
                return 1;
            }
        }

        int somaDiagonalPrincipal = 0;
    for (int i = 0; i < 3; i++) {
        somaDiagonalPrincipal += tabuleiro[i][i];
    }
    if (somaDiagonalPrincipal == 3) {
        return 1;
    } else if (somaDiagonalPrincipal == -3) {
        return -1;
    }

    int somaDiagonalSecundaria = 0;
    for (int i = 0; i < 3; i++) {
        somaDiagonalSecundaria += tabuleiro[i][2 - i];
    }
    if (somaDiagonalSecundaria == 3) {
        return 1;
    } else if (somaDiagonalSecundaria == -3) {
        return -1;
    }

    boolean tabuleiroCompleto = true;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (tabuleiro[i][j] == 0) {
                tabuleiroCompleto = false;
                break;
            }
        }
        if (!tabuleiroCompleto) {
            break;
        }
    }
    if (tabuleiroCompleto) {
        return 0;
    }

    // Se nenhum dos casos acima for verdadeiro, o jogo ainda não acabou
    return 2;
    }
    
    
}
