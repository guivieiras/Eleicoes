package br.ufsc.ine5605.trabalho1.apresentacao;

import br.ufsc.ine5605.trabalho1.controle.ControladorUrna;
import br.ufsc.ine5605.trabalho1.entidade.Candidato;
import br.ufsc.ine5605.trabalho1.entidade.Cidade;
import br.ufsc.ine5605.trabalho1.entidade.Tupla;
import br.ufsc.ine5605.trabalho1.entidade.Urna;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class TelaResultadoEleicao extends javax.swing.JFrame {

    private ControladorUrna controlador;

    public TelaResultadoEleicao(ControladorUrna controlador) {
        this.controlador = controlador;
        initComponents();
        imprimeResultado();
        setLocationRelativeTo(null);
        texto_Resultado.setFont(new Font("Consolas", Font.PLAIN, 16));

    }

    public void teste() {

        controlador.getLista().get(0).contabilizaVoto(20, 20);
        controlador.getLista().get(0).contabilizaVoto(21, 21);
        controlador.getLista().get(0).contabilizaVoto(22, 22);
        controlador.getLista().get(0).contabilizaVoto(23, 23);
        controlador.getLista().get(0).contabilizaVoto(24, 24);

        controlador.getLista().get(0).contabilizaVoto(30, 24);
        controlador.getLista().get(0).contabilizaVoto(31, 24);
        controlador.getLista().get(1).contabilizaVoto(30, 24);
        controlador.getLista().get(1).contabilizaVoto(387219, 657657);

        controlador.getLista().get(2).contabilizaVoto(40, 40);
        controlador.getLista().get(2).contabilizaVoto(40, 40);
        controlador.getLista().get(2).contabilizaVoto(40, 40);
        controlador.getLista().get(2).contabilizaVoto(40, 40);
        controlador.getLista().get(2).contabilizaVoto(41, 41);
        controlador.getLista().get(2).contabilizaVoto(41, 41);
        controlador.getLista().get(2).contabilizaVoto(41, 41);
        controlador.getLista().get(2).contabilizaVoto(42, 42);
        controlador.getLista().get(2).contabilizaVoto(42, 42);
        controlador.getLista().get(2).contabilizaVoto(43, 43);
        controlador.getLista().get(2).contabilizaVoto(44, 44);
        controlador.getLista().get(2).contabilizaVoto(51, 9238102);

        //controlador.getLista().get(0).contabilizaVoto(21, 20);
        // controlador.getLista().get(0).contabilizaVoto(21, 20);
        //      controlador.getLista().get(0).contabilizaVoto(20, 50);
        //      controlador.getLista().get(0).contabilizaVoto(20, 50);
        //      controlador.getLista().get(0).contabilizaVoto(20, 50);
    }

    public void imprimeResultado() {
        teste();
        for (Cidade cidade : controlador.controladorPrincipal.controladorCidade.getLista()) {
            //controlador.vereadorVencedorX(cidade);
            for (Urna urna : controlador.getLista()) {
                if (urna.getCidade() == cidade) {
                    insereTexto(String.format("Seção: %1$d Zona: %2$d Cidade: %3$s\n", urna.getSecaoEleitoral(), urna.getZonaEleitoral(), urna.getCidade().getNome()));
                    insereTexto("--------------- Vereadores ---------------\n");
                    LinkedHashMap<Candidato, Integer> vereadores = controlador.ordenaHashMap(urna.getTotalDeVotosPorVereador());
                    for (Entry<Candidato, Integer> entry : vereadores.entrySet()) {
                        insereTexto(entry.getKey().getNome() + "  (" + entry.getValue() + " votos)\n");
                    }
                    insereTexto(urna.getVotosInvalidosParaVerador() + " votos inválidos\n");

                    insereTexto("--------------- Prefeitos  ---------------\n");
                    LinkedHashMap<Candidato, Integer> prefeitos = controlador.ordenaHashMap(urna.getTotalDeVotosPorPrefeito());
                    for (Entry<Candidato, Integer> entry : prefeitos.entrySet()) {
                        insereTexto(entry.getKey().getNome() + "  (" + entry.getValue() + " votos)\n");
                    }
                    insereTexto(urna.getVotosInvalidosParaPrefeito() + " votos inválidos\n");
                    insereTexto("-------------- " + urna.getAbstencoes() + " abstenções --------------\n");

                    insereTexto("\n\n");
                }
            }
            insereTexto("--------- Vencedores " + cidade.getNome() + " --------\n");
            Tupla<Candidato, Integer> prefeitoVotos = controlador.prefeitoVencedor(cidade);
            insereTexto(String.format("Prefeito vencedor: %1$s (%2$d votos)\n", prefeitoVotos.value1.getNome(), prefeitoVotos.value2));
            insereTexto("Vereadores:\n");

            LinkedHashMap<Candidato, Integer> vereadoresVencedores = controlador.vereadorVencedor(cidade);
            int vagas = 3;
            for (Entry<Candidato, Integer> entry : vereadoresVencedores.entrySet()) {
                if (vagas > -100) {
                    insereTexto(entry.getKey().getNome() + "  (" + entry.getValue() + " votos)\n");
                }
                vagas--;
            }

            insereTexto("------------------------------------------\n");
            insereTexto("\n__________________________________________________________________________________________________\n\n");
        }
    }

    public void insereTexto(String txt) {
        texto_Resultado.append(txt);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        texto_Resultado = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Resultado");

        texto_Resultado.setColumns(20);
        texto_Resultado.setRows(5);
        jScrollPane1.setViewportView(texto_Resultado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea texto_Resultado;
    // End of variables declaration//GEN-END:variables
}
