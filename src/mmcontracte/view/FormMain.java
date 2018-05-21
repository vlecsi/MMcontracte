/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmcontracte.view;

import java.awt.Color;
import mmcontracte.repo.Database;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import mmcontracte.model.Contract;
import net.sf.jasperreports.engine.JREmptyDataSource;


import org.quinto.swing.table.model.IModelFieldGroup;
import org.quinto.swing.table.model.ModelData;
import org.quinto.swing.table.model.ModelField;
import org.quinto.swing.table.model.ModelFieldGroup;
import org.quinto.swing.table.model.ModelRow;
import org.quinto.swing.table.view.JBroTable;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;





/**
 *
 * @author Levi
 */
public class FormMain extends javax.swing.JFrame {

    private JBroTable mytable;
    private ModelData mydata;
    private ModelRow[] rows;

  
    
    private void loadTableData() {

	int rowIndex = 0;
	Database data = new Database();

	//dm.setRowCount(0);
	ArrayList<Contract> list = data.queryContracte(tfCauta.getText());
	System.out.println(" LEFUTOTTAM !!!!!!!!!!!!!!!!");

	rows = new ModelRow[list.size()];
	if (!list.isEmpty()) {

	    Object[] rowField = new Object[12];
	    for (int i = 0; i < list.size(); i++) {
		rowField[0] = list.get(i).getId();
		rowField[1] = list.get(i).getNrContract();
		rowField[2] = list.get(i).getDataContract();
		rowField[3] = (boolean) false;
		rowField[4] = (boolean) false;
		if ("PERSOANA FIZICA".equals(list.get(i).getTip_contract())) {
		    rowField[3] = (boolean) true;
		}
		if ("PERSOANA JURIDICA".equals(list.get(i).getTip_contract())) {
		    rowField[4] = (boolean) true;
		}

		rowField[5] = list.get(i).getBeneficiar().toUpperCase();

		String dcpattern = "#0.00#";
		DecimalFormat df = new DecimalFormat(dcpattern);

		rowField[6] = df.format(list.get(i).getValoareRon());
		rowField[7] = df.format(list.get(i).getValoareEur());
		rowField[8] = df.format(list.get(i).getAvansRon());
		rowField[9] = df.format(list.get(i).getAvansEur());
		rowField[10] = df.format(list.get(i).getRestRon());
		rowField[11] = df.format(list.get(i).getRestEur());

	//	System.out.println(list.get(i).getRestEur());
	//	System.out.println("ROW NR:" + list.size());
		rows[rowIndex] = new ModelRow(rowField[0], rowField[1], rowField[2], rowField[3], rowField[4], rowField[5], rowField[6], rowField[7], rowField[8], rowField[9], rowField[10], rowField[11]);
		++rowIndex;
	    }
	    mydata.setRows(rows);
	    mytable.revalidate();
	    mytable.repaint();
	} else {
	    mydata.setRows(rows);
	    mytable.revalidate();
	    mytable.repaint();
	    JOptionPane.showMessageDialog(null, "Nu gasesc date care corespund criteriilor de cautare !", "Cautare ", JOptionPane.ERROR_MESSAGE);

	}
    }

    private void createTable() {
	IModelFieldGroup groups[] = new IModelFieldGroup[]{
	    new ModelField("ID", "ID"), new ModelFieldGroup("CONTRACT", "CONTRACT").withChild(new ModelField("Nr.", "Nr."))
	    .withChild(new ModelField("Data", "Data").withRowspan(2)), new ModelFieldGroup("TIP", "TIP").withChild(new ModelField("Pf.", "Pf.")).withChild(new ModelField("Pj.", "Pj.")),
	    new ModelField("BENEFICIAR", "BENEFICIAR"), new ModelFieldGroup("VALOARE", "VALOARE").withChild(new ModelField("Ron1", "Ron")).withChild(new ModelField("Eur1", "Eur")),
	    new ModelFieldGroup("AVANS", "AVANS").withChild(new ModelField("Ron2", "Ron")).withChild(new ModelField("Eur2", "Eur")),
	    new ModelFieldGroup("REST", "REST").withChild(new ModelField("Ron3", "Ron")).withChild(new ModelField("Eur3", "Eur"))
	};

	mydata = new ModelData(groups);

	mytable = new JBroTable(mydata);
	mytable.setDefaultEditor(Object.class, null);
	mytable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	mytable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
	mytable.setFont(new Font("Arial", Font.PLAIN, 12));
	mytable.setRowHeight(25);

	DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	rightRenderer.setHorizontalAlignment(RIGHT);
	DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
	leftRenderer.setHorizontalAlignment(LEFT);
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	centerRenderer.setHorizontalAlignment(CENTER);

	mytable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
	mytable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
	mytable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	mytable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	mytable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	mytable.getColumnModel().getColumn(5).setCellRenderer(leftRenderer);
	mytable.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
	mytable.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
	mytable.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
	mytable.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
	mytable.getColumnModel().getColumn(10).setCellRenderer(rightRenderer);
	mytable.getColumnModel().getColumn(11).setCellRenderer(rightRenderer);

	mytable.getColumnModel().getColumn(0).setMaxWidth(50);
	mytable.getColumnModel().getColumn(1).setMaxWidth(40);
	mytable.getColumnModel().getColumn(2).setMaxWidth(80);
	mytable.getColumnModel().getColumn(3).setMaxWidth(25);
	mytable.getColumnModel().getColumn(4).setMaxWidth(25);
	//mytable.getColumnModel().getColumn(5).setMaxWidth(650);
	mytable.getColumnModel().getColumn(6).setMaxWidth(70);
	mytable.getColumnModel().getColumn(7).setMaxWidth(70);
	mytable.getColumnModel().getColumn(8).setMaxWidth(70);
	mytable.getColumnModel().getColumn(9).setMaxWidth(70);
	mytable.getColumnModel().getColumn(10).setMaxWidth(70);
	mytable.getColumnModel().getColumn(11).setMaxWidth(70);

	mytable.getColumn("Pj.").setCellRenderer(new DefaultTableCellRenderer() {
	    final JCheckBox checkBox = new JCheckBox();

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		checkBox.setSelected(((Boolean) value));
		//checkBox.setSelected(true);
		return checkBox;
	    }
	});

	mytable.getColumn("Pf.").setCellRenderer(new DefaultTableCellRenderer() {
	    final JCheckBox checkBox = new JCheckBox();

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		checkBox.setSelected(((Boolean) value));
		//checkBox.setSelected(true);
		return checkBox;
	    }
	});

	TableColumn tcol = mytable.getColumnModel().getColumn(0);
	mytable.removeColumn(tcol);

	JScrollPane tableContainer = new JScrollPane(mytable);
	jPanel4.add(tableContainer);
	jPanel4.revalidate();
	jPanel4.repaint();

    }

  
    public FormMain() {
	initComponents();
	createTable();
        loadTableData();
	bt_import.setEnabled(false);
	bt_export.setEnabled(false);
      //  this.getContentPane().setBackground(Color.white );

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tfCauta = new javax.swing.JTextField();
        btCauta = new javax.swing.JButton();
        btAnulareCautare = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btContractNou = new javax.swing.JButton();
        btModifica = new javax.swing.JButton();
        btSterge = new javax.swing.JButton();
        bt_import = new javax.swing.JButton();
        btTipareste = new javax.swing.JButton();
        bt_export = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btInchide = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 800));
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cauta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tfCauta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btCauta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btCauta.setText("Cauta dupa Criterii");
        btCauta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCautaActionPerformed(evt);
            }
        });

        btAnulareCautare.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btAnulareCautare.setText("Anulare");
        btAnulareCautare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnulareCautareActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfCauta, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addGap(34, 34, 34)
                .addComponent(btCauta, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btAnulareCautare, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAnulareCautare, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCauta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCauta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel3.setBackground(java.awt.SystemColor.textHighlight);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MM Contracte");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista Contractelor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Meniu Operatii"));

        btContractNou.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btContractNou.setText("Contract Nou");
        btContractNou.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btContractNou.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btContractNouActionPerformed(evt);
            }
        });

        btModifica.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btModifica.setText("Modifica Contract Existent");
        btModifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificaActionPerformed(evt);
            }
        });

        btSterge.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSterge.setText("Sterge Contract");
        btSterge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStergeActionPerformed(evt);
            }
        });

        bt_import.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_import.setText("Import Baza de Date");
        bt_import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_importActionPerformed(evt);
            }
        });

        btTipareste.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btTipareste.setText("Tipareste Contract");
        btTipareste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTiparesteActionPerformed(evt);
            }
        });

        bt_export.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bt_export.setText("Export Baza de Date");
        bt_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btContractNou, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btModifica, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(btSterge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_import, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btTipareste, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(bt_export, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btContractNou, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btModifica, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btSterge, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btTipareste, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
                .addComponent(bt_import, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(458, Short.MAX_VALUE)
                    .addComponent(bt_export, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(153, 153, 153)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Meniu Formular"));

        btInchide.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btInchide.setText("Inchide Program");
        btInchide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInchideActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btInchide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addComponent(btInchide, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btStergeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStergeActionPerformed

	if (mytable.getSelectedRow() < 0) {  return; }
	int row = mytable.getSelectedRow();
	String value = mytable.getModel().getValueAt(row, 0).toString();
        String nrContract=mytable.getModel().getValueAt(row, 1).toString();
	
         int response = JOptionPane.showConfirmDialog(null, "Doriti sa stergeti contractul cu nr #"+nrContract+ " ?", "Confirmare stergere contrcat",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
          if (response == JOptionPane.YES_OPTION) {

              //sterge ID-ul
              JOptionPane.showMessageDialog(null, "Contractul cu nr #"+nrContract+" a fost sters !", "Informatii", JOptionPane.WARNING_MESSAGE);
              loadTableData(); 
           }   
        
        
        
        
    }//GEN-LAST:event_btStergeActionPerformed

    private void btInchideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInchideActionPerformed
	System.exit(0);
    }//GEN-LAST:event_btInchideActionPerformed

    private void bt_importActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_importActionPerformed
    }//GEN-LAST:event_bt_importActionPerformed

    private void btCautaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCautaActionPerformed

	loadTableData();

    }//GEN-LAST:event_btCautaActionPerformed

    private void btContractNouActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btContractNouActionPerformed
	int id = 0;
	FormContract fc = new FormContract(id);
	fc.pack();
	fc.setVisible(true);

    }//GEN-LAST:event_btContractNouActionPerformed

    private void btAnulareCautareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnulareCautareActionPerformed
	tfCauta.setText("");
	loadTableData();

	
      
    }//GEN-LAST:event_btAnulareCautareActionPerformed

    private void bt_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exportActionPerformed
       
	// TODO add your handling code here:
    }//GEN-LAST:event_bt_exportActionPerformed

    private void btModificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificaActionPerformed

	if (mytable.getSelectedRow() < 0) { return; }
	String value = mytable.getModel().getValueAt(mytable.getSelectedRow(), 0).toString();
	if (value == null) {   return; 	}

	System.out.println("Selected:" + value);
	FormContract fcontract = new FormContract(Integer.parseInt(value));
	fcontract.pack();
	fcontract.setVisible(true);

	


    }//GEN-LAST:event_btModificaActionPerformed

    private void btTiparesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTiparesteActionPerformed
        //Tiparire 

        // TODO add your handling code here:
        
        HashMap param = new HashMap();
        
        param.put("contractDetalii", "NR. 12 din data de 11/12/2015");
        
        
        String fDescriere ="Avand sediul in ONCESTI nr 460, judetul MARAMURES, inregistrat la registrul comertului sub J24/194 2008, avand cod unic de inregistrare RO 23179020, reprezentata prin domnul DRAGUS ION, avand functia de ADMINISTRATOR in calitate de FURNIZOR.";
        param.put("furnizorNume", "SC. MARIANA-MARINEL SRL.");
        param.put("furnizorDescriere", fDescriere );


        String bDescriere ="Avand sediul in ONCESTI nr 460, judetul MARAMURES, inregistrat la registrul comertului sub J24/194 2008, avand cod unic de inregistrare RO 23179020, reprezentata prin domnul DRAGUS ION, avand functia de ADMINISTRATOR in calitate de FURNIZOR.";
        param.put("beneficiarNume", "SC. MARIANA-MARINEL SRL.");
        param.put("beneficiarDescriere", fDescriere );
        
        String profil,factura_seria,factura_nr,chitanta_serie,chitanta_nr,bon_de_casa,cont_bancar,trezorarie;        

String obiectulContractului="1. Obiectul contractului il reprezinta furnizarea de prestari servicii cu TAMPLARIE din PVC cu profil OKMAN"+"\n"+
        "2. Contractul este valabil numai pe baza de facturi seria SN nr 01112, achitata print";
        
                 
//   String obiectulContractului="1. Obiectul contractului il reprezinta furnizarea de prestari servicii cu TAMPLARIE din PVC cu profil "+profil+chr(13)+
//   "2. Contractul este valabil numai pe baza de facturi seria "+factura_seria+" nr "+factura_nr+", achitata print"+chr(13)+
//   "   [-] Chitanta seria "+chitanta_serie+", nr "+chitanta_nr+chr(13)+
//   "   [-] Bon de casa "+bon_de_casa+chr(13)+
//   "   [-] Cont bancar "+cont_bancar+chr(13)+
//   "   [-] Trezorarie"+trezorarie+chr(13)+
//   "3. Contractul este prevazut in 2 exemplare."+chr(13)+
//   "4. Obiectul contractului priveste confectionarea si montarea tamplariei conform dimensiunilor , deschiderilor si tipului de geam stabilite de comun acord cu beneficiarul la fata locului cu ocazia masuratorilor. "+chr(13)+
//   "5. Executantul se obliga sa realizeze lucrarea iar beneficiarul sa plateasca pretul convenit de ambele parti la termenele si conditiile din contract."+chr(13)+
//   "6. Lucrarea se determina in tablou de tamplarie ";

                

param.put("obiectulContractului", obiectulContractului );


        try {
                   
            String source = System.getProperty("user.dir")+"\\report\\report1.jrxml";
            JasperReport jc = JasperCompileManager.compileReport(source); //give your report.jrxml file path
            JasperPrint print = JasperFillManager.fillReport(jc, param, new JREmptyDataSource());
            //JasperViewer.viewReport(print);
            JasperViewer.viewReport(print, false);
            
        } catch (JRException ex) {
            Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
            //Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
        
    }//GEN-LAST:event_btTiparesteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAnulareCautare;
    private javax.swing.JButton btCauta;
    private javax.swing.JButton btContractNou;
    private javax.swing.JButton btInchide;
    private javax.swing.JButton btModifica;
    private javax.swing.JButton btSterge;
    private javax.swing.JButton btTipareste;
    private javax.swing.JButton bt_export;
    private javax.swing.JButton bt_import;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField tfCauta;
    // End of variables declaration//GEN-END:variables

}
