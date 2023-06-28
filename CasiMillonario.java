package com.mycompany.casimillonario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CasiMillonario extends JFrame {
    private List<Question> questions;
    private int currentQuestion;
    private int score;

    private JLabel questionLabel;
    private JPanel optionsPanel;
    private JLabel resultLabel;

    public CasiMillonario() {
        questions = new ArrayList<>();
        currentQuestion = 0;
        score = 0;

        initializeQuestions();

        setTitle("Casi Millonario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel, BorderLayout.NORTH);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        add(optionsPanel, BorderLayout.CENTER);

        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setVisible(false);
        add(resultLabel, BorderLayout.SOUTH);

        loadQuestion();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeQuestions() {
        questions.add(new Question("¿Cuál es la capital de Francia?", new String[]{"París", "Madrid", "Londres", "Roma"}, 0));
        questions.add(new Question("¿Cuál es el río más largo del mundo?", new String[]{"Nilo", "Amazonas", "Yangtsé", "Misisipi"}, 1));
        questions.add(new Question("¿Cuál es el elemento más abundante en la corteza terrestre?", new String[]{"Hierro", "Oxígeno", "Silicio", "Aluminio"}, 2));
        questions.add(new Question("¿Cuál es el planeta más grande del sistema solar?", new String[]{"Júpiter", "Saturno", "Urano", "Neptuno"}, 0));
        questions.add(new Question("¿Cuál es el océano más grande del mundo?", new String[]{"Antártico", "Atlántico", "Índico", "Pacífico"}, 3));
        questions.add(new Question("¿Cuál es el país más poblado del mundo?", new String[]{"Rusia", "India", "Estados Unidos", "China"}, 3));
        questions.add(new Question("¿Cuál es el monte más alto del mundo?", new String[]{"Monte Everest", "Monte Kilimanjaro", "Monte Aconcagua", "Monte McKinley"}, 0));
        questions.add(new Question("¿Cuál es el metal más pesado?", new String[]{"Osmio", "Iridio", "Platino", "Mercurio"}, 1));
        questions.add(new Question("¿Cuál es el animal más grande del mundo?", new String[]{"Ballena Azul", "Elefante Africano", "Tiburón Ballena", "Jirafa"}, 0));
        questions.add(new Question("¿Cuál es el país más extenso del mundo?", new String[]{"Canada", "Rusia", "China", "Estados Unidos"}, 1));
        questions.add(new Question("¿Cuál es el idioma más hablado del mundo?", new String[]{"Hindi", "Español", "Inglés", "Mandarín"}, 3));
        questions.add(new Question("¿Cuál es el continente más poblado?", new String[]{"Africa", "Asia", "Europa", "América"}, 1));
        questions.add(new Question("¿Cuál es el instrumento musical más antiguo?", new String[]{"Lira", "Arpa", "Tambor", "Flauta"}, 3));
        questions.add(new Question("¿Cuál es el metal más conductor de electricidad?", new String[]{"Aluminio", "Cobre", "Oro", "Plata"}, 1));
    }

    private void loadQuestion() {
        if (currentQuestion >= questions.size()) {
            showResult();
            return;
        }

        Question question = questions.get(currentQuestion);

        questionLabel.setText(question.getQuestion());

        optionsPanel.removeAll();

        for (int i = 0; i < question.getOptions().length; i++) {
            JButton optionButton = new JButton(question.getOptions()[i]);
            optionButton.addActionListener(new OptionButtonListener(i));
            optionButton.setPreferredSize(new Dimension(200, 40));
            optionButton.setFont(new Font("Arial", Font.PLAIN, 14));
            optionButton.setFocusPainted(false);
            optionButton.setBackground(new Color(240, 240, 240));
            optionsPanel.add(optionButton);
        }

        revalidate();
        repaint();
    }

    private void showResult() {
        questionLabel.setText("¡Juego terminado! Tu puntaje final es: " + score + "/" + questions.size());
        optionsPanel.removeAll();
        resultLabel.setText("Gracias por jugar.");
        resultLabel.setVisible(true);
        revalidate();
        repaint();
    }

    private class OptionButtonListener implements ActionListener {
        private int selectedOptionIndex;

        public OptionButtonListener(int selectedOptionIndex) {
            this.selectedOptionIndex = selectedOptionIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Question question = questions.get(currentQuestion);

            if (selectedOptionIndex == question.getAnswer()) {
                score++;
            }

            currentQuestion++;
            loadQuestion();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CasiMillonario();
            }
        });
    }
}
