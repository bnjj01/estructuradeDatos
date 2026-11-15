package com.ed.lewischase.interfaz;

import com.ed.lewischase.graph.MatrixDirectedGraph;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class VentanaGrafo extends javax.swing.JFrame {

    // Instancia del grafo dirigido como indica la página 6 del tutorial
    private MatrixDirectedGraph<String> grafo = new MatrixDirectedGraph<>();

    // Componentes de la interfaz (nombres iguales a los del tutorial)
    private JTextField txtVertice;
    private JTextField txtOrigenArista;
    private JTextField txtDestinoArista;
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

    public VentanaGrafo() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Grafos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(580, 560);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(242, 242, 242));
        setLayout(null); // Diseño libre equivalente al área de diseño del tutorial

        // --- TÍTULO PRINCIPAL ---
        JLabel lblTitulo = new JLabel("Grafo Dirigido", SwingConstants.CENTER);
        lblTitulo.setBounds(190, 10, 200, 25);
        add(lblTitulo);

        // --- SECCIÓN 1: VÉRTICES ---
        JLabel lblVertices = new JLabel("Vértices", SwingConstants.CENTER);
        lblVertices.setBounds(190, 40, 200, 20);
        add(lblVertices);

        txtVertice = new JTextField();
        txtVertice.setBounds(215, 65, 150, 28);
        add(txtVertice);

        btnAgregarVertice = new JButton("Agregar Vértice");
        btnAgregarVertice.setBackground(new Color(144, 238, 144)); // Verde
        btnAgregarVertice.setBounds(130, 105, 130, 28);
        btnAgregarVertice.addActionListener(evt -> btnAgregarVerticeActionPerformed());
        add(btnAgregarVertice);

        btnEliminarVertice = new JButton("Eliminar Vértice");
        btnEliminarVertice.setBackground(new Color(240, 50, 50)); // Rojo
        btnEliminarVertice.setForeground(Color.WHITE);
        btnEliminarVertice.setBounds(320, 105, 130, 28);
        btnEliminarVertice.addActionListener(evt -> btnEliminarVerticeActionPerformed());
        add(btnEliminarVertice);

        // --- SECCIÓN 2: ARISTAS ---
        JLabel lblAristas = new JLabel("Aristas", SwingConstants.CENTER);
        lblAristas.setBounds(190, 150, 200, 20);
        add(lblAristas);

        JLabel lblVerticeOrigen = new JLabel("Vértice Origen", SwingConstants.CENTER);
        lblVerticeOrigen.setBounds(130, 175, 130, 20);
        add(lblVerticeOrigen);

        JLabel lblVerticeDestino = new JLabel("Vertice Destino", SwingConstants.CENTER);
        lblVerticeDestino.setBounds(320, 175, 130, 20);
        add(lblVerticeDestino);

        txtOrigenArista = new JTextField();
        txtOrigenArista.setBounds(130, 200, 130, 28);
        add(txtOrigenArista);

        txtDestinoArista = new JTextField();
        txtDestinoArista.setBounds(320, 200, 130, 28);
        add(txtDestinoArista);

        btnAgregarArista = new JButton("Agregar Arista");
        btnAgregarArista.setBackground(new Color(144, 238, 144));
        btnAgregarArista.setBounds(130, 240, 130, 28);
        btnAgregarArista.addActionListener(evt -> btnAgregarAristaActionPerformed());
        add(btnAgregarArista);

        btnEliminarArista = new JButton("Eliminar Arista");
        btnEliminarArista.setBackground(new Color(240, 50, 50));
        btnEliminarArista.setForeground(Color.WHITE);
        btnEliminarArista.setBounds(320, 240, 130, 28);
        btnEliminarArista.addActionListener(evt -> btnEliminarAristaActionPerformed());
        add(btnEliminarArista);

        // --- SECCIÓN 3: VISUALIZAR Y RECORRIDOS (DFS / BFS) ---
        JLabel lblVisualizar = new JLabel("Visualizar", SwingConstants.CENTER);
        lblVisualizar.setBounds(160, 290, 200, 20);
        add(lblVisualizar);

        txtVisualizar = new JTextArea();
        txtVisualizar.setEditable(false);
        JScrollPane scrollVisualizar = new JScrollPane(txtVisualizar);
        scrollVisualizar.setBounds(140, 315, 240, 100);
        add(scrollVisualizar);

        btnDFS = new JButton("DFS");
        btnDFS.setBackground(new Color(144, 238, 144));
        btnDFS.setBounds(410, 325, 80, 28);
        btnDFS.addActionListener(evt -> btnDFSActionPerformed());
        add(btnDFS);

        btnBFS = new JButton("BFS");
        btnBFS.setBackground(new Color(144, 238, 144));
        btnBFS.setBounds(410, 365, 80, 28);
        btnBFS.addActionListener(evt -> btnBFSActionPerformed());
        add(btnBFS);

        // --- SECCIÓN 4: CAMINO MÁS CORTO (SP) ---
        JLabel lblCaminoCorto = new JLabel("Camino más corto", SwingConstants.CENTER);
        lblCaminoCorto.setBounds(190, 435, 200, 20);
        add(lblCaminoCorto);

        JLabel lblOrigenSP = new JLabel("Origen");
        lblOrigenSP.setBounds(75, 465, 50, 28);
        add(lblOrigenSP);

        txtOrigenSP = new JTextField();
        txtOrigenSP.setBounds(130, 465, 110, 28);
        add(txtOrigenSP);

        JLabel lblDestinoSP = new JLabel("Destino");
        lblDestinoSP.setBounds(260, 465, 55, 28);
        add(lblDestinoSP);

        txtDestinoSP = new JTextField();
        txtDestinoSP.setBounds(315, 465, 110, 28);
        add(txtDestinoSP);

        btnSP = new JButton("SP");
        btnSP.setBackground(new Color(144, 238, 144));
        btnSP.setBounds(445, 465, 70, 28);
        btnSP.addActionListener(evt -> btnSPActionPerformed());
        add(btnSP);
    }

    // =========================================================================
    // EVENTOS DE LOS BOTONES (Siguiendo la lógica del tutorial PDF)
    // =========================================================================

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
        if (origen.isEmpty() || destino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el vértice de origen y el de destino");
            return;
        }
        grafo.addEdge(origen, destino);
        txtOrigenArista.setText("");
        txtDestinoArista.setText("");
        txtVisualizar.setText(grafo.toString());
        JOptionPane.showMessageDialog(this, "Arista dirigida agregada: " + origen + " -> " + destino);
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
        // Si escribió un vértice en el campo "Vértices", inicia desde ahí; si no, lo pide o inicia desde el índice 0
        String inicio = txtVertice.getText().trim();
        Iterator<String> it;
        if (!inicio.isEmpty()) {
            it = grafo.iteratorDFS(inicio);
        } else {
            it = grafo.iteratorDFS(0);
        }
        mostrarRecorrido("Recorrido en Profundidad (DFS):", it);
    }

    private void btnBFSActionPerformed() {
        if (grafo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El grafo está vacío");
            return;
        }
        String inicio = txtVertice.getText().trim();
        Iterator<String> it;
        if (!inicio.isEmpty()) {
            it = grafo.iteratorBFS(inicio);
        } else {
            it = grafo.iteratorBFS(0);
        }
        mostrarRecorrido("Recorrido en Anchura (BFS):", it);
    }

    private void btnSPActionPerformed() {
        String origen = txtOrigenSP.getText().trim();
        String destino = txtDestinoSP.getText().trim();
        if (origen.isEmpty() || destino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el origen y el destino para el camino más corto");
            return;
        }
        Iterator<String> it = grafo.iteratorShortestPath(origen, destino);
        if (!it.hasNext()) {
            txtVisualizar.setText("No existe un camino dirigido desde " + origen + " hasta " + destino);
        } else {
            mostrarRecorrido("Camino más corto (" + origen + " -> " + destino + "):", it);
        }
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