/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package component;

import card.Card;
import card_supplier.CardSupplier;
import card_supplier.UserCardSupplier;
import form.Form;
import form.UserForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.util.Vector;
import java.util.function.Supplier;
import record.Record;
import util.EntityHeaderData;
import util.EntityField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import util.Pair;

/**
 * This class is intended to regularize the interface for adding/editing entities.
 * <br>
 * Ideally, it should be used like this: 
 * <br>
 * <code>
 * GenericQueryFrame queryFrame = new GenericQueryFrame(
 * <br>
  utils.EntityHeaderData.USER,
 <br>
 *  new UserCardSupplier()
 * <br>
 * );
 * </code>
 * 
 * 
 * @author Edgar
 * @see CardSupplier
 */
public class GenericQueryFrame extends javax.swing.JFrame {
    protected CardSupplier cardSupplier;
    protected EntityHeaderData entityHeaderData;
    protected Supplier<Form> formSupplier;
    
    
    /**
     * Creates new form GenericQueryFrame
     */
    public GenericQueryFrame() throws ClassNotFoundException, SQLException {
        this(
                EntityHeaderData.USER, 
                new UserCardSupplier(EntityField.of("id", "nombre", "tipo")),
                () -> new UserForm()
        );
    }
    
    public GenericQueryFrame(
            EntityHeaderData entityControlData, 
            CardSupplier cardSupplier,
            Supplier<Form> formSupplier
    ) throws SQLException {
        this.cardSupplier = cardSupplier;
        this.entityHeaderData = entityControlData;
        this.formSupplier = formSupplier;
        
        initComponents();
        reloadCards();
        setupQueryLimitMenu();
        setupOrderByMenu();
        setupSearchComboBox();
        
        cardSupplier.setResetListener(Optional.of(new CardSupplier.ResetListener() {
            @Override
            public void call(Optional<Integer> queryLimit, Optional<EntityField> orderBy, Optional<Pair<EntityField, String>> search) {
                if (search.isPresent()) {
                    removeSearchButton.setEnabled(true);
                    searchForTextField.setText(search.get().getSecond());
                } else {
                    removeSearchButton.setEnabled(false);
                    searchForTextField.setText("");
                }
                reloadCards();
            }
        }));
    }
    
    protected void setupSearchComboBox() {
        searchComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardSupplier.setSearch(Optional.empty());
            }
        });
    }
    
    protected <K> void setupMenu(
            JMenu menu, 
            int length, 
            Function<Integer, String> nameFunction,
            Function<Integer, Optional<K>> valueFunction,
            Consumer<Optional<K>> callback
    ) {
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < length; i++) {
            Optional<K> value = valueFunction.apply(i);
            String name = nameFunction.apply(i);
            
            JRadioButtonMenuItem buttonMenuItem = new JRadioButtonMenuItem(name);
            if (i == 0) {
                buttonMenuItem.setSelected(true);
            }
            
            buttonMenuItem.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        callback.accept(value);
                    }
                }
            });
            buttonGroup.add(buttonMenuItem);
            menu.add(buttonMenuItem);
        }
    }
    
    protected void setupOrderByMenu() {
        setupMenu(orderByMenu,
                cardSupplier.getFields().size() + 1,
                (Integer i) -> {
                    if (i == 0) return "Ninguno";
                    EntityField entityField  = (EntityField) cardSupplier.getFields().get(i - 1);
                    return entityField.getPrettyName();
                },
                (Integer i) -> {
                    if (i == 0) return Optional.empty();
                    EntityField entityField = (EntityField) cardSupplier.getFields().get(i - 1);
                    return Optional.of(entityField);
                },
                (Optional<EntityField> value) -> cardSupplier.setOrderBy(value)
        );
    }
    
    protected void setupQueryLimitMenu() {
        setupMenu(
                queryLimitMenu,
                6,
                (Integer i) -> i == 0? "Ninguno": String.valueOf(i * 5) + " registros máximo",
                (Integer i) -> i == 0? Optional.empty(): Optional.of(i * 5),
                (Optional<Integer> value) -> cardSupplier.setQueryLimit(value)
        );
    }
    
    protected void addSeparatorToContainer(CardContainer cardContainer) {
        javax.swing.Box.Filler filler = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        cardContainer.addComponent(filler);
    }
    
    protected void removeSeparatorFromContainer(CardContainer cardContainer) {
        JPanel contentPane = cardContainer.getContentPane();
        int lastIndex = contentPane.getComponentCount() - 1;
        contentPane.remove(lastIndex);
    }
    
    protected void populateContainerWithCards(CardContainer cardContainer) throws SQLException {
        Optional<LinkedList<Card<Record>>> cards;
        cards = cardSupplier.getNextCardBatch();
        
        if (cards.isPresent()) {
            LinkedList<Card<Record>> cardsList = cards.get();
            
            for (int i = 0; i < cardsList.size(); i++) {
                Card<Record> card = cardsList.get(i);
                if (i != 0) {
                    addSeparatorToContainer(cardContainer);
                }
                cardContainer.addComponent(card);
            }
        }
        cardContainer.getLoadMoreButton().setEnabled(!cardSupplier.isDepleted());
    }
    
    protected CardContainer createCardContainer() {
        CardContainer cardContainer = new CardContainer();
        cardContainer.getLoadMoreButton().addActionListener((ActionEvent e) -> {
            loadMoreCards();
        });
        return cardContainer;
    }
    
    protected void loadMoreCards() {
        try {
            CardContainer cardContainer = (CardContainer) cardsScrollPane.getViewport().getView();
            if (!cardSupplier.isDepleted()) {
                addSeparatorToContainer(cardContainer);
            }
            populateContainerWithCards(cardContainer);
            cardsScrollPane.repaint();
            cardContainer.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Un error en la base de datos evitó que se cargaran más registros", "No se pueden cargar más registros", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(GenericQueryFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void reloadCards() {
        try {
            CardContainer cardContainer = createCardContainer();
            populateContainerWithCards(cardContainer);
            cardsScrollPane.setViewportView(cardContainer);
            
            cardsScrollPane.repaint();
            
            cardContainer.revalidate();
            cardContainer.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Un error en la base de datos evitó que se cargaran más registros", "No se pueden cargar más registros", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(GenericQueryFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        entityHeader1 = new component.EntityHeader(entityHeaderData);
        jPanel2 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        searchComboBox = new javax.swing.JComboBox<>();
        searchForTextField = new javax.swing.JTextField();
        removeSearchButton = new javax.swing.JButton();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        jPanel4 = new javax.swing.JPanel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        cardsScrollPane = new javax.swing.JScrollPane();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        jPanel5 = new javax.swing.JPanel();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        addRecordButton = new javax.swing.JButton();
        reloadButton = new javax.swing.JButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        jMenuBar1 = new javax.swing.JMenuBar();
        orderByMenu = new javax.swing.JMenu();
        queryLimitMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultar " + entityHeaderData.getEntityName());
        setResizable(false);

        jPanel1.setBackground(util.ProjectColor.WHITE.getColor());
        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel1.add(entityHeader1, gridBagConstraints);

        jPanel2.setBackground(util.ProjectColor.WHITE.getColor()
        );
        jPanel2.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(filler2, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Open Sans Medium", 0, 15)); // NOI18N
        jLabel1.setText("Conoce tus datos por medio de consultas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setBackground(util.ProjectColor.WHITE.getColor());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Open Sans Light", 0, 12)); // NOI18N
        jLabel2.setText("Buscar por");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel3.add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel3.add(filler4, gridBagConstraints);

        searchComboBox.setBackground(util.ProjectColor.WHITE.getColor());
        searchComboBox.setFont(new java.awt.Font("Open Sans Medium", 0, 15)); // NOI18N
        searchComboBox.setModel(new DefaultComboBoxModel<EntityField>(new Vector<EntityField>(cardSupplier.getFields())));
        searchComboBox.setSelectedIndex(-1);
        searchComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(searchComboBox, gridBagConstraints);

        searchForTextField.setBackground(util.ProjectColor.WHITE.getColor());
        searchForTextField.setFont(new java.awt.Font("Open Sans Medium", 0, 15)); // NOI18N
        searchForTextField.setEnabled(false);
        searchForTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchForTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(searchForTextField, gridBagConstraints);

        removeSearchButton.setBackground(util.ProjectColor.WHITE.getColor());
        removeSearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/20-exit.png"))); // NOI18N
        removeSearchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeSearchButton.setEnabled(false);
        removeSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSearchButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(removeSearchButton, gridBagConstraints);
        jPanel3.add(filler9, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jPanel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel1.add(filler10, gridBagConstraints);

        jPanel4.setBackground(util.ProjectColor.WHITE.getColor()
        );
        jPanel4.setMinimumSize(new java.awt.Dimension(76, 200));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 200));
        jPanel4.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(filler5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(filler6, gridBagConstraints);

        cardsScrollPane.setBorder(null);
        cardsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cardsScrollPane.setPreferredSize(new java.awt.Dimension(340, 400));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(cardsScrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel1.add(filler7, gridBagConstraints);

        jPanel5.setBackground(util.ProjectColor.WHITE.getColor()
        );
        jPanel5.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel5.add(filler11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel5.add(filler12, gridBagConstraints);

        addRecordButton.setBackground(util.ProjectColor.WHITE.getColor());
        addRecordButton.setFont(new java.awt.Font("Open Sans Light", 0, 12)); // NOI18N
        addRecordButton.setForeground(util.ProjectColor.BLACK.getColor()
        );
        addRecordButton.setText("Insertar registro");
        addRecordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRecordButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(addRecordButton, gridBagConstraints);

        reloadButton.setBackground(util.ProjectColor.WHITE.getColor());
        reloadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/20-autorenew.png"))); // NOI18N
        reloadButton.setToolTipText("Refrescar registros");
        reloadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel5.add(reloadButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jPanel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel1.add(filler8, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        orderByMenu.setBackground(util.ProjectColor.WHITE.getColor());
        orderByMenu.setText("Ordenar por");
        jMenuBar1.add(orderByMenu);

        queryLimitMenu.setBackground(util.ProjectColor.WHITE.getColor());
        queryLimitMenu.setText("Límite");
        jMenuBar1.add(queryLimitMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchComboBoxActionPerformed
        searchForTextField.setEnabled(true);
    }//GEN-LAST:event_searchComboBoxActionPerformed

    private void removeSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSearchButtonActionPerformed
        cardSupplier.setSearch(Optional.empty());
    }//GEN-LAST:event_removeSearchButtonActionPerformed

    private void searchForTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchForTextFieldActionPerformed
        // do some validation
        cardSupplier.setSearch(Optional.of(new Pair((EntityField) searchComboBox.getSelectedItem(), searchForTextField.getText())));
    }//GEN-LAST:event_searchForTextFieldActionPerformed

    private void addRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRecordButtonActionPerformed
        Card.launchAddForm(formSupplier.get(), entityHeaderData);
    }//GEN-LAST:event_addRecordButtonActionPerformed

    private void reloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadButtonActionPerformed
        cardSupplier.resetQuery();
    }//GEN-LAST:event_reloadButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(GenericQueryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenericQueryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenericQueryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenericQueryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GenericQueryFrame().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GenericQueryFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GenericQueryFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRecordButton;
    private javax.swing.JScrollPane cardsScrollPane;
    private component.EntityHeader entityHeader1;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JMenu orderByMenu;
    private javax.swing.JMenu queryLimitMenu;
    private javax.swing.JButton reloadButton;
    private javax.swing.JButton removeSearchButton;
    private javax.swing.JComboBox<EntityField> searchComboBox;
    private javax.swing.JTextField searchForTextField;
    // End of variables declaration//GEN-END:variables
}
