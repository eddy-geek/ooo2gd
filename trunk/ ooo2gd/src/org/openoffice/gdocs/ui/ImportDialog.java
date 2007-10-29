/*
 * ImportDialog.java
 *
 * Created on October 7, 2007, 5:35 PM
 */

package org.openoffice.gdocs.ui;

import com.google.gdata.data.docs.DocumentListEntry;
import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import org.openoffice.gdocs.GoogleDocsWrapper;

/**
 *
 * @author  rmk
 */
public class ImportDialog extends java.awt.Dialog {
    
    private class DocumentsTableModel extends AbstractTableModel {

        private List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
        
        public Object getValueAt(int rowIndex, int columnIndex) {
            DocumentListEntry entry = list.get(rowIndex);
            Object obj = null;            
            switch (columnIndex) {
                case 0: obj = entry.getTitle().getPlainText(); break;
                case 1: obj = entry.getUpdated().toStringRfc822(); break;
                //case 3: return entry.getAuthors();
            }
            if (obj==null) obj="none";
            return obj;
        }

        public int getColumnCount() {
            return 2;
        }

        public int getRowCount() {
            int result = 0;
            if (list!=null) {
                result = list.size();
            }
            return result;
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public String getColumnName(int column) {
            switch (column) {
                case 0: return "Document Title";
                case 1: return "Published";
            }
            return "";
        }
       
        public void add(DocumentListEntry entry) {
            list.add(entry);
        }

        public DocumentListEntry getEntry(int idx) {            
            return list.get(idx);
        }
        
    }
    
    /** Creates new form ImportDialog */
    public ImportDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jTable1.setModel(new DocumentsTableModel());
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        loginPanel1 = new org.openoffice.gdocs.ui.LoginPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jButton1 = new javax.swing.JButton();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setFocusTraversalPolicyProvider(true);
        setTitle("Import from Google Docs");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.add(loginPanel1);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton2);

        add(jPanel2, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jButton1.setText("Get list");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSplitPane1.setTopComponent(jButton1);

        jSplitPane2.setDividerLocation(300);
        jSplitPane2.setFocusCycleRoot(true);
        jSplitPane2.setPreferredSize(new java.awt.Dimension(300, 134));
        jPanel3.setMaximumSize(new java.awt.Dimension(71, 33));
        jButton3.setText("Open");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel3.add(jButton3);

        jSplitPane2.setRightComponent(jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jSplitPane2.setLeftComponent(jScrollPane1);

        jSplitPane1.setBottomComponent(jSplitPane2);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            
            //DocumentElement entry = (DocumentElement)jList1.getSelectedValue();
            DocumentElement entry = new DocumentElement(((DocumentsTableModel)jTable1.getModel()).getEntry(jTable1.getSelectedRow()));
            String id = entry.getId().split("%3A")[1];
            String type = entry.getId().split("%3A")[0];
            //JOptionPane.showMessageDialog(null,id);
            GoogleDocsWrapper wrapper = new GoogleDocsWrapper();
            try {
                wrapper.login(loginPanel1.getUserName(),loginPanel1.getPassword());
            } catch (Exception e) {
                
            }
            //URL url = new URL("http://docs.google.com/MiscCommands?command=saveasdoc&docID="+id+"&exportFormat=oo");
            //Service.GDataRequest request = uploader.getService().createRequest(com.google.gdata.client.Service.GDataRequest.RequestType.QUERY,url,com.google.gdata.util.ContentType.MULTIPART_RELATED);
            // http://spreadsheets.google.com/fm?id=o13574010685749958148.4092107507226618767.06588863554185365881.3080299690275689473&hl=en&fmcmd=13
            // http://spreadsheets.google.com/fm?id=o13574010685749958148.4092107507226618767.06588863554185365881.8885257468043574331&hl=en&fmcmd=13
            // http://spreadsheets.google.com/fm?id=o13574010685749958148.4092107507226618767.06588863554185365881.8885257468043574331&hl=en&fmcmd=4
            // "http://docs.google.com/MiscCommands?command=saveasdoc&docID="+id+"&exportFormat=oo"
            String uriStr = "";
            if ("document".equals(type)) {
                uriStr = "http://docs.google.com/MiscCommands?command=saveasdoc&docID="+id+"&exportFormat=oo";
            } else if ("spreadsheet".equals(type)) {
                uriStr = "http://spreadsheets.google.com/fm?id="+id+"&hl=en&fmcmd=13";
            } else if ("presentation".equals(type)) {
                uriStr = "http://docs.google.com/MiscCommands?command=saveasdoc&docID="+id+"&exportFormat=ppt";
            }
            Desktop.getDesktop().browse(new URI(uriStr));
            
            //InputStream stream = request.getResponseStream();
            
            //stream.read();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private class DocumentElement {
        private DocumentListEntry entry;
        public DocumentElement(DocumentListEntry entry) {
            this.entry = entry;
        }
        public String getDocumentLink() {
            return entry.getDocumentLink().getHref();
        }
        public String getId() {
            return entry.getId();
        }
        public String toString() {
            return entry.getTitle().getPlainText();
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GoogleDocsWrapper wrapper = new GoogleDocsWrapper();
        try {
            wrapper.login(loginPanel1.getUserName(),loginPanel1.getPassword());
        } catch (Exception e) {
            
        }
        jTable1.setEnabled(true);
        jButton3.setEnabled(true);
        List<DocumentElement> list = new ArrayList<DocumentElement>();
        DocumentsTableModel dtm = new DocumentsTableModel();        
        for (DocumentListEntry entry:wrapper.getListOfDocs()) {
            if ( entry.getId().startsWith("document") ) {
                dtm.add(entry);
            }
        }
        jTable1.setModel(dtm);
        this.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImportDialog(new java.awt.Frame(), true).setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTable jTable1;
    private org.openoffice.gdocs.ui.LoginPanel loginPanel1;
    // End of variables declaration//GEN-END:variables

}
