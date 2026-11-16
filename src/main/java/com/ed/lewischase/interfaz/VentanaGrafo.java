package com.ed.lewischase.interfaz;

import com.ed.lewischase.graph.MatrixDirectedGraph;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class VentanaGrafo extends javax.swing.JFrame {

    private MatrixDirectedGraph<String> grafo = new MatrixDirectedGraph<>();

    private JTextField txtVertice;
    private JTextField txtOrigenArista;
    private JTextField txtDestinoArista;
    private JTextField txtPesoArista;
    private JTextArea txtVisualizar;
    private JTextField txtOrigenSP;
    private JTextField txtDestinoSP;

    private JButton btnAgregarVertice;
    private JButton btnEliminarVertice;
    private JButton btnAgregarArista;
    private JButton btnEliminarArista;
    private JButton btnDFS;
    private JButton btnBFS;
    private JButton btnSP;
    private JButton btnDijkstra;

    public VentanaGrafo() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Grafos - Red Dirigida y Algoritmo de Dijkstra");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(620, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(242, 242, 242));
        setLayout(null);

        JLabel lblTitulo = new JLabel("Grafo Dirigido Ponderado", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTitulo.setBounds(190, 10, 240, 25);
        add(lblTitulo);

        JLabel lblVertices = new JLabel("Vértices", SwingConstants.CENTER);
        lblVertices.setBounds(210, 40, 200, 20);
        add(lblVertices);

        txtVertice = new JTextField();
        txtVertice.setBounds(235, 65, 150, 28);
        add(txtVertice);

        btnAgregarVertice = new JButton("Agregar Vértice");
        btnAgregarVertice.setBackground(new Color(144, 238, 144));
        btnAgregarVertice.setBounds(150, 105, 130, 28);
        btnAgregarVertice.addActionListener(evt -> btnAgregarVerticeActionPerformed());
        add(btnAgregarVertice);

        btnEliminarVertice = new JButton("Eliminar Vértice");
        btnEliminarVertice.setBackground(new Color(240, 50, 50));
        btnEliminarVertice.setForeground(Color.WHITE);
        btnEliminarVertice.setBounds(340, 105, 130, 28);
        btnEliminarVertice.addActionListener(evt -> btnEliminarVerticeActionPerformed());
        add(btnEliminarVertice);

        JLabel lblAristas = new JLabel("Aristas", SwingConstants.CENTER);
        lblAristas.setBounds(210, 145, 200, 20);
        add(lblAristas);

        JLabel lblVerticeOrigen = new JLabel("Vértice Origen", SwingConstants.CENTER);
        lblVerticeOrigen.setBounds(90, 170, 130, 20);
        add(lblVerticeOrigen);

        JLabel lblVerticeDestino = new JLabel("Vértice Destino", SwingConstants.CENTER);
        lblVerticeDestino.setBounds(245, 170, 130, 20);
        add(lblVerticeDestino);

        JLabel lblPesoArista = new JLabel("Costo / Peso", SwingConstants.CENTER);
        lblPesoArista.setBounds(400, 170, 110, 20);
        add(lblPesoArista);

        txtOrigenArista = new JTextField();
        txtOrigenArista.setBounds(90, 195, 130, 28);
        add(txtOrigenArista);

        txtDestinoArista = new JTextField();
        txtDestinoArista.setBounds(245, 195, 130, 28);
        add(txtDestinoArista);

        txtPesoArista = new JTextField("1.0");
        txtPesoArista.setBounds(400, 195, 110, 28);
        add(txtPesoArista);

        btnAgregarArista = new JButton("Agregar Arista");
        btnAgregarArista.setBackground(new Color(144, 238, 144));
        btnAgregarArista.setBounds(150, 235, 130, 28);
        btnAgregarArista.addActionListener(evt -> btnAgregarAristaActionPerformed());
        add(btnAgregarArista);

        btnEliminarArista = new JButton("Eliminar Arista");
        btnEliminarArista.setBackground(new Color(240, 50, 50));
        btnEliminarArista.setForeground(Color.WHITE);
        btnEliminarArista.setBounds(340, 235, 130, 28);
        btnEliminarArista.addActionListener(evt -> btnEliminarAristaActionPerformed());
        add(btnEliminarArista);

        JLabel lblVisualizar = new JLabel("Visualizar", SwingConstants.CENTER);
        lblVisualizar.setBounds(160, 280, 220, 20);
        add(lblVisualizar);

        txtVisualizar = new JTextArea();
        txtVisualizar.setEditable(false);
        txtVisualizar.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollVisualizar = new JScrollPane(txtVisualizar);
        scrollVisualizar.setBounds(70, 305, 380, 135);
        add(scrollVisualizar);

        btnDFS = new JButton("DFS");
        btnDFS.setBackground(new Color(144, 238, 144));
        btnDFS.setBounds(470, 330, 90, 28);
        btnDFS.addActionListener(evt -> btnDFSActionPerformed());
        add(btnDFS);

        btnBFS = new JButton("BFS");
        btnBFS.setBackground(new Color(144, 238, 144));
        btnBFS.setBounds(470, 375, 90, 28);
        btnBFS.addActionListener(evt -> btnBFSActionPerformed());
        add(btnBFS);

        JLabel lblCaminoCorto = new JLabel("Camino Mínimo entre dos Vértices", SwingConstants.CENTER);
        lblCaminoCorto.setBounds(160, 455, 300, 20);
        add(lblCaminoCorto);

        JLabel lblOrigenSP = new JLabel("Origen:");
        lblOrigenSP.setBounds(45, 485, 50, 28);
        add(lblOrigenSP);

        txtOrigenSP = new JTextField();
        txtOrigenSP.setBounds(95, 485, 95, 28);
        add(txtOrigenSP);

        JLabel lblDestinoSP = new JLabel("Destino:");
        lblDestinoSP.setBounds(205, 485, 55, 28);
        add(lblDestinoSP);

        txtDestinoSP = new JTextField();
        txtDestinoSP.setBounds(260, 485, 95, 28);
        add(txtDestinoSP);

        btnSP = new JButton("SP");
        btnSP.setToolTipText("Camino con menor cantidad de aristas (BFS)");
        btnSP.setBackground(new Color(144, 238, 144));
        btnSP.setBounds(370, 485, 65, 28);
        btnSP.addActionListener(evt -> btnSPActionPerformed());
        add(btnSP);

        btnDijkstra = new JButton("Dijkstra (Costo Mín.)");
        btnDijkstra.setToolTipText("Muestra vértices, aristas y costos del camino mínimo usando Dijkstra");
        btnDijkstra.setBackground(new Color(100, 200, 255));
        btnDijkstra.setBounds(445, 485, 145, 28);
        btnDijkstra.addActionListener(evt -> btnDijkstraActionPerformed());
        add(btnDijkstra);
    }

    private void btnAgregarVerticeActionPerformed() {
        String vertice = txtVertice.getText().trim();
        if (vertice.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un vértice");
            return;
        }
        grafo.addVertex(vertice);
        txtVertice.setText("");
        txtVisualizar.setText(grafo.toString());
        JOptionPane.showMessageDialog(this, "Vértice agregado correctamente");
    }

    private void btnEliminarVerticeActionPerformed() {
        String vertice = txtVertice.getText().trim();
        if (vertice.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un vértice para eliminar");
            return;
        }
        grafo.removeVertex(vertice);
        txtVertice.setText("");
        txtVisualizar.setText(grafo.toString());
        JOptionPane.showMessageDialog(this, "Vértice eliminado correctamente");
    }

    private void btnAgregarAristaActionPerformed() {
        String origen = txtOrigenArista.getText().trim();
        String destino = txtDestinoArista.getText().trim();
        String pesoStr = txtPesoArista.getText().trim();

        if (origen.isEmpty() || destino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el vértice de origen y el de destino");
            return;
        }

        double peso = 1.0;
        if (!pesoStr.isEmpty()) {
            try {
                peso = Double.parseDouble(pesoStr);
                if (peso < 0) {
                    JOptionPane.showMessageDialog(this, "Para el algoritmo de Dijkstra el costo no debe ser negativo");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el costo/peso");
                return;
            }
        }

        grafo.addEdge(origen, destino, peso);
        txtOrigenArista.setText("");
        txtDestinoArista.setText("");
        txtPesoArista.setText("1.0");
        txtVisualizar.setText(grafo.toString());
        JOptionPane.showMessageDialog(this, "Arista agregada: (" + origen + " -> " + destino + ") con costo " + peso);
    }

    private void btnEliminarAristaActionPerformed() {
        String origen = txtOrigenArista.getText().trim();
        String destino = txtDestinoArista.getText().trim();
        if (origen.isEmpty() || destino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el vértice de origen y el de destino");
            return;
        }
        grafo.removeEdge(origen, destino);
        txtOrigenArista.setText("");
        txtDestinoArista.setText("");
        txtVisualizar.setText(grafo.toString());
        JOptionPane.showMessageDialog(this, "Arista eliminada correctamente");
    }

    private void btnDFSActionPerformed() {
        if (grafo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El grafo está vacío");
            return;
        }
        String inicio = txtVertice.getText().trim();
        Iterator<String> it = !inicio.isEmpty() ? grafo.iteratorDFS(inicio) : grafo.iteratorDFS(0);
        mostrarRecorrido("Recorrido en Profundidad (DFS):", it);
    }

    private void btnBFSActionPerformed() {
        if (grafo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El grafo está vacío");
            return;
        }
        String inicio = txtVertice.getText().trim();
        Iterator<String> it = !inicio.isEmpty() ? grafo.iteratorBFS(inicio) : grafo.iteratorBFS(0);
        mostrarRecorrido("Recorrido en Anchura (BFS):", it);
    }

    private void btnSPActionPerformed() {
        String origen = txtOrigenSP.getText().trim();
        String destino = txtDestinoSP.getText().trim();
        if (origen.isEmpty() || destino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el origen y el destino");
            return;
        }
        Iterator<String> it = grafo.iteratorShortestPath(origen, destino);
        if (!it.hasNext()) {
            txtVisualizar.setText("No existe un camino dirigido desde " + origen + " hasta " + destino);
        } else {
            mostrarRecorrido("Camino con menos saltos (BFS) de " + origen + " a " + destino + ":", it);
        }
    }

    private void btnDijkstraActionPerformed() {
        String origen = txtOrigenSP.getText().trim();
        String destino = txtDestinoSP.getText().trim();
        if (origen.isEmpty() || destino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el vértice de origen y el de destino");
            return;
        }
        String detalle = grafo.obtenerDetalleCaminoMinimoDijkstra(origen, destino);
        txtVisualizar.setText(detalle);
    }

    private void mostrarRecorrido(String titulo, Iterator<String> it) {
        StringBuilder sb = new StringBuilder(titulo).append("\n");
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(" -> ");
            }
        }
        txtVisualizar.setText(sb.toString());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new VentanaGrafo().setVisible(true));
    }
}