import javax.swing.*;

public class Mini4 {

    JFrame f;
    JLabel qLabel, tLabel;
    JRadioButton a, b, c, d;
    JButton next;
    ButtonGroup g;

    int q = 0;
    int score = 0;
    int time = 5;

    String questions[][] = {
            {"Java is?", "Language", "OS", "Browser", "DB"},
            {"Thread is?", "Heavy", "Lightweight", "None", "All"},
            {"JVM stands for?", "Java Virtual Machine", "Java Variable Method", "Joint VM", "None"},
            {"Which is not OOP concept?", "Inheritance", "Encapsulation", "Compilation", "Polymorphism"}
    };

    int answers[] = {0, 1, 0, 2};

    public Mini4() {

        f = new JFrame("Quiz");
        f.setSize(350, 300);
        f.setLayout(null);

        qLabel = new JLabel();
        qLabel.setBounds(20, 20, 300, 20);
        f.add(qLabel);

        tLabel = new JLabel("Time: 5");
        tLabel.setBounds(250, 20, 100, 20);
        f.add(tLabel);

        a = new JRadioButton();
        b = new JRadioButton();
        c = new JRadioButton();
        d = new JRadioButton();

        a.setBounds(20, 60, 250, 20);
        b.setBounds(20, 90, 250, 20);
        c.setBounds(20, 120, 250, 20);
        d.setBounds(20, 150, 250, 20);

        g = new ButtonGroup();
        g.add(a); g.add(b); g.add(c); g.add(d);

        f.add(a); f.add(b); f.add(c); f.add(d);

        next = new JButton("Next");
        next.setBounds(120, 200, 80, 25);
        f.add(next);

        next.addActionListener(e -> nextQ());

        loadQ();

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void loadQ() {
        if (q >= questions.length) {
            JOptionPane.showMessageDialog(f, "Final Score: " + score + "/" + questions.length);
            System.exit(0);
        }

        qLabel.setText("Q" + (q + 1) + ": " + questions[q][0]);
        a.setText(questions[q][1]);
        b.setText(questions[q][2]);
        c.setText(questions[q][3]);
        d.setText(questions[q][4]);

        g.clearSelection(); // clear previous answer

        startTimer();
    }

    void nextQ() {
        int ans = -1;

        if (a.isSelected()) ans = 0;
        if (b.isSelected()) ans = 1;
        if (c.isSelected()) ans = 2;
        if (d.isSelected()) ans = 3;

        if (ans == answers[q]) score++;

        q++;
        loadQ();
    }

    void startTimer() {
        time = 5;
        tLabel.setText("Time: " + time);

        new Thread(() -> {
            try {
                while (time > 0) {
                    Thread.sleep(1000);
                    time--;
                    tLabel.setText("Time: " + time);
                }
                nextQ();
            } catch (Exception e) {}
        }).start();
    }

    public static void main(String[] args) {
        new Mini4();
    }
}