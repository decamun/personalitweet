
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import twitter4j.TwitterException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Decamun
 */
public class PTApplet extends javax.swing.JApplet {

    /**
     * Initializes the applet PTApplet
     */
    
    //neccesary variables
    private boolean newUserBoxChecked = false;
    private boolean loggedIn = false;
    private ArrayList<String> handles = new ArrayList<>();
    
    
    
    @Override
    public void init() {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(PTApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PTApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PTApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PTApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        visualizeTab = new javax.swing.JTabbedPane();
        myAcountPane = new javax.swing.JPanel();
        loginName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        usernameBox = new javax.swing.JTextField();
        newUserBox = new javax.swing.JCheckBox();
        updateUserButton = new javax.swing.JButton();
        visualizePane = new javax.swing.JPanel();
        visulizeUser = new javax.swing.JTextField();
        analyzePane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        analysisTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        candidateInputBox = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        analysisOutputBox = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 204, 255));

        loginName.setText("Not Logged In");

        jLabel1.setText("Logged in As:");

        usernameBox.setText("Username");
        usernameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameBoxActionPerformed(evt);
            }
        });
        usernameBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameBoxFocusGained(evt);
            }
        });

        newUserBox.setText("New User");
        newUserBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserBoxActionPerformed(evt);
            }
        });

        updateUserButton.setText("Update");
        updateUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout myAcountPaneLayout = new javax.swing.GroupLayout(myAcountPane);
        myAcountPane.setLayout(myAcountPaneLayout);
        myAcountPaneLayout.setHorizontalGroup(
            myAcountPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myAcountPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(myAcountPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(myAcountPaneLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginName))
                    .addComponent(usernameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(myAcountPaneLayout.createSequentialGroup()
                        .addComponent(newUserBox)
                        .addGap(18, 18, 18)
                        .addComponent(updateUserButton)))
                .addContainerGap(509, Short.MAX_VALUE))
        );
        myAcountPaneLayout.setVerticalGroup(
            myAcountPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myAcountPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(myAcountPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(loginName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usernameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(myAcountPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newUserBox)
                    .addComponent(updateUserButton))
                .addContainerGap(227, Short.MAX_VALUE))
        );

        visualizeTab.addTab("My Account", myAcountPane);

        visulizeUser.setText("Input Twitter Handle");
        visulizeUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visulizeUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout visualizePaneLayout = new javax.swing.GroupLayout(visualizePane);
        visualizePane.setLayout(visualizePaneLayout);
        visualizePaneLayout.setHorizontalGroup(
            visualizePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visulizeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(499, Short.MAX_VALUE))
        );
        visualizePaneLayout.setVerticalGroup(
            visualizePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visulizeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );

        visualizeTab.addTab("Visualize", visualizePane);

        analysisTextArea.setColumns(20);
        analysisTextArea.setRows(5);
        analysisTextArea.setText("Input Text for Analysis Here");
        jScrollPane1.setViewportView(analysisTextArea);

        jLabel3.setText("Text for Analysis");

        candidateInputBox.setText("Add a Twitter Handle");
        candidateInputBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                candidateInputBoxActionPerformed(evt);
            }
        });
        candidateInputBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                candidateInputBoxFocusGained(evt);
            }
        });

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        analysisOutputBox.setColumns(20);
        analysisOutputBox.setRows(5);
        jScrollPane2.setViewportView(analysisOutputBox);

        jButton2.setText("Analyze");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout analyzePaneLayout = new javax.swing.GroupLayout(analyzePane);
        analyzePane.setLayout(analyzePaneLayout);
        analyzePaneLayout.setHorizontalGroup(
            analyzePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(analyzePaneLayout.createSequentialGroup()
                .addComponent(candidateInputBox, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(6, 6, 6))
            .addComponent(jScrollPane2)
            .addGroup(analyzePaneLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        analyzePaneLayout.setVerticalGroup(
            analyzePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analyzePaneLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(analyzePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(candidateInputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        visualizeTab.addTab("Analyze", analyzePane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(visualizeTab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(visualizeTab)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void usernameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameBoxActionPerformed
        login(evt.getActionCommand());
    }//GEN-LAST:event_usernameBoxActionPerformed

    private void login(String s) {
        if (!loggedIn){
            try {
                PersonaliTweet.login(s, newUserBoxChecked);
            } catch (TwitterException ex) {
                Logger.getLogger(PTApplet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PTApplet.class.getName()).log(Level.SEVERE, null, ex);
            }

            loginName.setText(s);
            loggedIn = true;
        }
    }
    
    private void newUserBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserBoxActionPerformed
        //cycle checkbox
        newUserBoxChecked = !newUserBoxChecked;      
    }//GEN-LAST:event_newUserBoxActionPerformed

    private void visulizeUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visulizeUserActionPerformed
        try {
            Category[] data = PersonaliTweet.getTweets(evt.getActionCommand());
            
            
            double mean = 0;
            int numMean = 0;
            
            for(Category c : data) {
                numMean++;
                mean += c.counter;
            }
            mean = mean/numMean;
            for(int i = 0; i < data.length; i++) {
                Category c = data[i];
                if(data[i].counter > mean) {
                    this.getGraphics().setColor(Color.blue);
                    this.getGraphics().drawRect(i*10, 100 - data[i].counter, 10, data[i].counter);
                } else {
                    this.getGraphics().setColor(Color.red);
                    this.getGraphics().drawRect(i*10, 100, 10, -data[i].counter);
                }
                System.out.println(c.name + " " + c.counter);
            }
        } catch (TwitterException ex) {
            Logger.getLogger(PTApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_visulizeUserActionPerformed

    private void updateUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserButtonActionPerformed
        login(usernameBox.getText());
    }//GEN-LAST:event_updateUserButtonActionPerformed

    private void usernameBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameBoxFocusGained
        usernameBox.selectAll();
    }//GEN-LAST:event_usernameBoxFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        handles.add(candidateInputBox.getText());
        candidateInputBox.setText("");
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void candidateInputBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_candidateInputBoxFocusGained
        candidateInputBox.selectAll();
        
    }//GEN-LAST:event_candidateInputBoxFocusGained

    private void candidateInputBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_candidateInputBoxActionPerformed
        handles.add(evt.getActionCommand());
        candidateInputBox.setText("");
    }//GEN-LAST:event_candidateInputBoxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

   
   
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea analysisOutputBox;
    private javax.swing.JTextArea analysisTextArea;
    private javax.swing.JPanel analyzePane;
    private javax.swing.JTextField candidateInputBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel loginName;
    private javax.swing.JPanel myAcountPane;
    private javax.swing.JCheckBox newUserBox;
    private javax.swing.JButton updateUserButton;
    private javax.swing.JTextField usernameBox;
    private javax.swing.JPanel visualizePane;
    private javax.swing.JTabbedPane visualizeTab;
    private javax.swing.JTextField visulizeUser;
    // End of variables declaration//GEN-END:variables
}
