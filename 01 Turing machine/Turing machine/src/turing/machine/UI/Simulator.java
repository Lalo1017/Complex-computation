package turing.machine.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author erick
 */
public class Simulator extends javax.swing.JFrame {

    /**
     * *********** GLOBAL VARIABLES ************
     */
    /**
     * ******** TURING MACHINE VARIABLES *******
     */
    private static final String q[] = {"q0", "q1", "q2", "q3", "q4"};
    private static final Character t_symbol = 'X';
    private static final Character symbol = '1';
    private static final String qi = q[0];
    private static final String qf = q[4];
    private static final char B = ' ';
    private String qc = qi;

    private ArrayList<Character> tape;

    /**
     * *********** OTHER VARIABLES  ************
     */
    private int animation = 0;
    private Thread thread;
    private static final char L = 'L';
    private static final char R = 'R';
    private static final char N = 'N';
    private char move_to = N;

    /**
     * ********** END GLOBAL VARIBLES **********
     */

    /**
     * Creates new form Simulator
     */
    public Simulator() {
        initComponents();
        this.setResizable(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("animation to " + move_to + " contains " + animation);
        Font font = new Font("Dialog", Font.PLAIN, 30);
        g.setFont(font);
        int Xi = 20;
        int Xf = this.getWidth() - 45;

        int Yi = 250;
        int Yf = 50;

        int middle = (Xf + Xi) / 2;
        /**
         * ****** BEGIN DRAWING TAPE *******
         */

        try {
            //Drawing container
            g.drawRect(Xi, Yi, Xf, Yf);

            //Drawing cells and text
            for (int i = -(Xf - 1) * 2, k = 0; i < Xf * 2; i += 3) {

                /**
                 * Making each cell*
                 */
                //Xi contains the size of each cell
                g.drawRect(Xi * i + animation, Yi, 0, Yf);
                g.setFont(font);

                if (i >= (Xi - 5) && k < tape.size() - 1) {
                    //Setting text in each cell
                    g.drawString(tape.get(++k) + "", Xi * i + 20 + animation, (Yi + Yf - 15));
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        /**
         * ******* END DRAWING TAPE ********
         */
        /**
         * ****** BEGIN DRAWING HEAD *******
         */
        int x_coordinates[] = {middle - 30, middle - 30, middle, middle + 30, middle + 30, middle - 30};
        int y_coordinates[] = {Yi - 100, Yi - 50, +Yi - 5, Yi - 50, Yi - 100, Yi - 100};
        Polygon head = new Polygon(x_coordinates, y_coordinates, x_coordinates.length);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.DARK_GRAY);
        g2D.fillPolygon(head);
        g2D.drawPolygon(head);
        g2D.setColor(Color.WHITE);
        g2D.drawString(qc, middle - 18, Yi - 60);

        g2D.setColor(Color.RED);
        Rectangle2D rectangle2D = new Rectangle(Yi, Yf, Yf, Yf);
        g2D.setStroke(new BasicStroke(3.0f));
        g2D.drawRect(middle - 37, Yi, 60, Yf);
        /**
         * ******* END DRAWING HEAD ********
         */
    }

    private void animate() {
        if (move_to == 'L') {
            animation++;
        } else if (move_to == 'R') {
            animation--;
        }
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TXTInputString = new javax.swing.JTextField();
        BTNStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setMinimumSize(new java.awt.Dimension(750, 452));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Turing machine simulator");
        jLabel1.setToolTipText("");
        jLabel1.setAlignmentX(0.5F);

        jLabel2.setText("Input string:");

        BTNStart.setText("Start");
        BTNStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTInputString, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTNStart)
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TXTInputString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNStart))
                .addContainerGap(366, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNStartActionPerformed

        try {
            //Getting the input string
            tape = new ArrayList<Character>();
            tape.add(B);
            for (char c : TXTInputString.getText().trim().toCharArray()) {
                tape.add(c);
            }

            for (int i = 0; i <= TXTInputString.getText().length(); i++) {
                tape.add(B);
            }
            System.out.println(tape.size());
            if (!tape.toString().isEmpty()) {
                transitionFunction();
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }//GEN-LAST:event_BTNStartActionPerformed

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Simulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Simulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Simulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Simulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Simulator().setVisible(true);
            }
        });
    }

    /**
     * ***********************************************************
     * * * * * * * * * BEGIN TURING MACHINE CODE * * * * * * * * *
     ************************************************************
     */
    private void transitionFunction() throws Exception {
        System.out.println("turing.machine.UI.Simulator.transitionFunction()");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //This make a undifined cicle
                boolean flag = true;
                //This variable store the position of the current symbol
                int i = 1;
                //This variable contains the actual state
                qc = qi;
                //This variable contains the current symbol
                Character c_symbol = B;

                while (flag) {
                    c_symbol = tape.get(i);
                    move_to = N;
                    if (qc == q[0]) {

                        //If current symbol it's a '1'
                        if (c_symbol == symbol) {
                            //System.out.println("Current state " + qc + " symbol " + c_symbol);
                            qc = q[1];
                            tape.set(i++, symbol);
                            move_to = R;
                        }

                    } else if (qc == q[1]) {

                        //If current symbol it's a '1'
                        if (c_symbol == symbol) {
                            //System.out.println("Current state " + qc + " symbol " + c_symbol);
                            qc = q[1];
                            tape.set(i++, symbol);
                            move_to = R;
                        } //If current symbol it's a ' '
                        else if (c_symbol == B) {
                            //System.out.println("Current state " + qc + " symbol " + c_symbol);
                            qc = q[2];
                            tape.set(i--, B);
                            move_to = L;
                        }
                    } else if (qc == q[2]) {

                        //If current symbol it's a '1'
                        if (c_symbol == symbol) {
                            //System.out.println("Current state " + qc + " symbol " + c_symbol);
                            qc = q[3];
                            tape.set(i++, t_symbol);
                            move_to = R;
                        } //If current symbol it's a 'X'
                        else if (c_symbol == t_symbol) {
                            //System.out.println("Current state " + qc + " symbol " + c_symbol);
                            qc = q[2];
                            tape.set(i--, t_symbol);
                            move_to = L;
                        } else if (c_symbol == B) {
                            //System.out.println("Current state " + qc + " symbol " + c_symbol);
                            qc = q[4];
                            tape.set(i++, B);
                            move_to = R;
                        }
                    } else if (qc == q[3]) {

                        //If current symbol it's a 'X'
                        if (c_symbol == t_symbol) {
                            //System.out.println("Current state " + qc + " symbol " + c_symbol);
                            qc = q[3];
                            tape.set(i++, t_symbol);
                            move_to = R;
                        } //If current symbol it's a ' '
                        else if (c_symbol == B) {
                            //System.out.println("Current state " + qc + " symbol " + c_symbol);
                            qc = q[2];
                            tape.set(i--, t_symbol);
                            move_to = L;
                        }
                    } else if (qc == q[4]) {

                        //If current symbol it's a 'X'
                        if (c_symbol == t_symbol) {
                            //System.out.println("Current state " + qc + " symbol " + c_symbol);
                            qc = q[4];
                            tape.set(i++, symbol);
                            move_to = R;
                        } //If current symbol it's a ' '
                        else if (c_symbol == B) {
                            //System.out.println("Current state " + qc + " symbol " + c_symbol);
                            System.out.println("FINAL");
                            flag = false;
                        }
                    }
                    
                    try{
                        for(int j = 0; j < 60; j++){
                            animate();   
                            Thread.sleep(10);
                        }
                        Thread.sleep(300);
                    } catch(Exception e){
                        System.err.println(e);
                    }
                }
            }
        }).start();

    }

    /**
     * ***********************************************************
     * * * * * * * * * END TURING MACHINE CODE * * * * * * * * * *
     ************************************************************
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNStart;
    private javax.swing.JTextField TXTInputString;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
