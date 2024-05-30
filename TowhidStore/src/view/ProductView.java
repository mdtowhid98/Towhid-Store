/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.DbUtil;

/**
 *
 * @author Admin
 */
public class ProductView extends javax.swing.JFrame {

    DbUtil db = new DbUtil();
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Creates new form ProductView
     */
    public ProductView() {
        initComponents();
        showProductOnTable();
        showStockOnTable();

        comProductName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                getProductSalesPrice(e);
            }

        });

    }

    public boolean getStockProductlist() {

        String sql = "select name from stock";
        boolean status = false;
        String purchaseProductName = txtName.getText().trim();

        try {
            ps = db.getCon().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("name");
                if (productName.equalsIgnoreCase(purchaseProductName)) {
                    status = true;
                    break;
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public void addProductToStock() {

        boolean status = getStockProductlist();

        if (status) {
            String sql = "update stock set quantity=quantity+? where name=?";
            try {
                ps = db.getCon().prepareStatement(sql);

                ps.setFloat(1, Float.parseFloat(txtQuantity.getText().trim()));
                ps.setString(2, txtName.getText().trim());

                ps.executeUpdate();

                ps.close();
                db.getCon().close();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            String sql = "insert into stock(name,unitPrice,quantity) values (?,?,?)";
            PreparedStatement ps;

            try {
                ps = db.getCon().prepareStatement(sql);

                ps.setString(1, txtName.getText().trim());
                ps.setFloat(2, Float.parseFloat(txtUnitPrice.getText().trim()));
                ps.setFloat(3, Float.parseFloat(txtQuantity.getText().trim()));

                ps.executeUpdate();

                ps.close();
                db.getCon().close();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void addProduct() {
        String sql = "insert into product(name,unitPrice,quantity,totalPrice)values(?,?,?,?)";
        PreparedStatement ps;

        try {
            ps = db.getCon().prepareStatement(sql);
            ps.setString(1, txtName.getText().trim());
            ps.setFloat(2, Float.parseFloat(txtUnitPrice.getText().trim()));
            ps.setFloat(3, Float.parseFloat(txtQuantity.getText().trim()));
            ps.setFloat(4, Float.parseFloat(txtTotalPrice.getText().trim()));

            ps.executeUpdate();
            ps.close();
            db.getCon().close();

            JOptionPane.showMessageDialog(this, "Add product successfully");
            addProductToStock();
            showStockOnTable();
            clear();
            showProductOnTable();

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Add product unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Add product unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getTotalPrice() {

        float unitPrice = Float.parseFloat(txtUnitPrice.getText().trim());
        float quantity = Float.parseFloat(txtQuantity.getText().trim());
        float totalPrice = unitPrice * quantity;

        txtTotalPrice.setText(totalPrice + "");

    }

    public void clear() {

        txtId.setText("");
        txtName.setText("");
        txtUnitPrice.setText("");
        txtQuantity.setText("");
        txtTotalPrice.setText("");

    }

    String[] ProductViewTableColumn = {"ID", "Name", "Unit Price", "Quantity", "Total Price"};
    String[] stockViewTableColumn = {"ID", "Name", "Quantity", "Unit Pice"};

    public void showProductOnTable() {

        String sql = "SELECT * FROM product";
        PreparedStatement ps;
        ResultSet rs;
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(ProductViewTableColumn);
        tblProductView.setModel(model);

        try {
            ps = db.getCon().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                float unitPrice = rs.getFloat("unitPrice");
                float quantity = rs.getFloat("quantity");
                float totalPrice = rs.getFloat("totalPrice");

                model.addRow(new Object[]{id, name, unitPrice, quantity, totalPrice});

            }
            rs.close();
            ps.close();
            db.getCon();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showStockOnTable() {

        String sql = "SELECT * FROM stock";
        PreparedStatement ps;
        ResultSet rs;
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(stockViewTableColumn);
        tblStock.setModel(model);

        try {
            ps = db.getCon().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");

                float quantity = rs.getFloat("quantity");
                float unitPrice = rs.getFloat("unitPrice");

                model.addRow(new Object[]{id, name, quantity, unitPrice});

            }
            rs.close();
            ps.close();
            db.getCon();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProduct() {
        String sql = "delete from product where id=?";
        PreparedStatement ps;

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtId.getText()));
            ps.executeUpdate();

            ps.close();
            db.getCon();
            JOptionPane.showMessageDialog(this, "Delete Product Successfully");
            clear();
            showProductOnTable();

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Delete Product Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Delete Product Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void editProduct() {
        String sql = "update product set name=?, unitPrice=?, quantity=?, totalPrice=? where id=?";
        PreparedStatement ps;

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setString(1, txtName.getText().trim());
            ps.setFloat(2, Float.parseFloat(txtUnitPrice.getText().trim()));
            ps.setFloat(3, Float.parseFloat(txtQuantity.getText().trim()));
            ps.setFloat(4, Float.parseFloat(txtTotalPrice.getText().trim()));
            ps.setInt(5, Integer.parseInt(txtId.getText()));

            ps.executeUpdate();

            ps.close();
            db.getCon();
            JOptionPane.showMessageDialog(this, "Update Product Successfully");
            clear();
            showProductOnTable();

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Update Product Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Update Product Unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showProductToCombo() {
        String sql = "select name from product";
        PreparedStatement ps;
        ResultSet rs;

        comProductName.removeAllItems();

        try {
            ps = db.getCon().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("name");
                comProductName.addItem(productName);
            }
            ps.close();
            db.getCon();
            rs.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getProductSalesPrice(ItemEvent e) {

        String selectedProductName = "";

        if (e.getStateChange() == ItemEvent.SELECTED) {
            selectedProductName = (String) e.getItem();
            //TODO your actitons
            extractSalesPrice(selectedProductName);
        }
        
        String sql="select quantity from stock where name=?";
        
        try {
            ps=db.getCon().prepareStatement(sql);
            
            ps.setString(1, selectedProductName);
            rs=ps.executeQuery();
            
            while(rs.next()){
            float quantity=rs.getFloat("quantity");
            lblStock.setText(quantity+"");
            }
            ps.close();
            db.getCon().close();
            rs.close();
           
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void extractSalesPrice(String productName) {

        String sql = "select unitPrice from product where name=?";
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = db.getCon().prepareStatement(sql);
            ps.setString(1, productName);

            rs = ps.executeQuery();

            while (rs.next()) {
                String salesPrice = rs.getString("unitPrice");
                txtSalesUnitPrice.setText(salesPrice);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getTotalSalesPrice() {

        float quantity = Float.parseFloat(txtSalesQuantity.getText().toString().trim());
        float unitPrice = Float.parseFloat(txtSalesUnitPrice.getText().toString().trim());
        float salesTotalPrice = quantity * unitPrice;

        txtSalesTotalPrice.setText(salesTotalPrice + "");

    }

    public String formatDateToDDMMYYYY(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);

    }

    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {

        if (utilDate != null) {
            return new java.sql.Date(utilDate.getTime());
        }
        return null;
    }

    public static java.sql.Date convertstringDateToSqlDate(String dateString) {

        SimpleDateFormat inputFomat = new SimpleDateFormat("MM-dd-yyyy");
        try {
            java.util.Date utildate = inputFomat.parse(dateString);

            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = outputFormat.format(utildate);
            return java.sql.Date.valueOf(formatDate);
        } catch (ParseException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void stockUpdateOnSales() {

        String sql = "update stock set quantity=quantity-? where name=?";
        
        try {
            ps = db.getCon().prepareStatement(sql);
            
              ps.setFloat(1, Float.parseFloat(txtSalesQuantity.getText().trim()));
                ps.setString(2, comProductName.getSelectedItem().toString());

                ps.executeUpdate();

                ps.close();
                db.getCon().close();
                
                
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addSales() {

        Date date = convertUtilDateToSqlDate(salesDate.getDate());
        PreparedStatement ps;
        String sql = "insert into sales(name,salesUnitPrice,salesQuantity,salesTotalPrice,salesDate)values(?,?,?,?,?)";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setString(1, comProductName.getSelectedItem().toString());
            ps.setFloat(2, Float.parseFloat(txtSalesUnitPrice.getText()));
            ps.setFloat(3, Float.parseFloat(txtSalesQuantity.getText()));
            ps.setFloat(4, Float.parseFloat(txtSalesTotalPrice.getText()));
            ps.setDate(5, convertUtilDateToSqlDate(date));

            ps.executeUpdate();
            db.getCon().close();
            JOptionPane.showMessageDialog(this, "Add sales Successfully");
             stockUpdateOnSales();
             showStockOnTable();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Add sales unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Add sales unsuccessfully");
            Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mainView = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnSales = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        mainView2 = new javax.swing.JTabbedPane();
        add = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtUnitPrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotalPrice = new javax.swing.JTextField();
        btnProductAdd = new javax.swing.JButton();
        btnProductDelete = new javax.swing.JButton();
        btnProductEdit = new javax.swing.JButton();
        btnProductReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductView = new javax.swing.JTable();
        sales = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSalesQuantity = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        salesDate = new com.toedter.calendar.JDateChooser();
        comProductName = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtSalesUnitPrice = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtSalesTotalPrice = new javax.swing.JTextField();
        btnSalesSave = new javax.swing.JButton();
        btnSalesEdit = new javax.swing.JButton();
        btnSalesReset = new javax.swing.JButton();
        btnSalesDelete = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        stock = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        report = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Towhid Store");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 130));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 130));

        mainView.setBackground(new java.awt.Color(51, 102, 255));
        mainView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAdd.setBackground(new java.awt.Color(0, 0, 0));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 51, 51));
        btnAdd.setText("Add");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        mainView.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 290, -1));

        btnSales.setBackground(new java.awt.Color(204, 204, 0));
        btnSales.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnSales.setForeground(new java.awt.Color(0, 0, 255));
        btnSales.setText("Sales Product");
        btnSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalesMouseClicked(evt);
            }
        });
        btnSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalesActionPerformed(evt);
            }
        });
        mainView.add(btnSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 120, 300, -1));

        btnStock.setBackground(new java.awt.Color(255, 0, 255));
        btnStock.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnStock.setText("Stock");
        btnStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStockMouseClicked(evt);
            }
        });
        mainView.add(btnStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 290, -1));

        btnReport.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnReport.setForeground(new java.awt.Color(51, 255, 51));
        btnReport.setText("Report");
        btnReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportMouseClicked(evt);
            }
        });
        mainView.add(btnReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 290, -1));

        getContentPane().add(mainView, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 290, 410));

        add.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Add Product");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 90));

        add.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 90));

        jLabel6.setText("ID");
        add.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 50, 30));

        txtId.setEditable(false);
        add.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 210, -1));

        jLabel7.setText("Name");
        add.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 50, 20));
        add.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 210, -1));

        jLabel8.setText("Unit Price");
        add.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));
        add.add(txtUnitPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 210, -1));

        jLabel9.setText("Quantity");
        add.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        txtQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQuantityFocusLost(evt);
            }
        });
        add.add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 210, -1));

        jLabel10.setText("Total Price");
        add.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        txtTotalPrice.setEditable(false);
        add.add(txtTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 210, -1));

        btnProductAdd.setBackground(new java.awt.Color(51, 255, 51));
        btnProductAdd.setText("Add");
        btnProductAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductAddMouseClicked(evt);
            }
        });
        add.add(btnProductAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, -1, -1));

        btnProductDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnProductDelete.setText("Delete");
        btnProductDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductDeleteMouseClicked(evt);
            }
        });
        add.add(btnProductDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, -1, -1));

        btnProductEdit.setBackground(new java.awt.Color(255, 255, 0));
        btnProductEdit.setText("Edit");
        btnProductEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductEditMouseClicked(evt);
            }
        });
        add.add(btnProductEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, -1, -1));

        btnProductReset.setBackground(new java.awt.Color(255, 102, 102));
        btnProductReset.setText("Reset");
        btnProductReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductResetMouseClicked(evt);
            }
        });
        add.add(btnProductReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, -1, -1));

        tblProductView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProductView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductView);

        add.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 387, 710, 100));

        mainView2.addTab("add", add);

        sales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        sales.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 704, -1));

        jPanel4.setBackground(new java.awt.Color(51, 255, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Sales product");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 70));

        sales.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 70));

        jLabel11.setText("Name");
        sales.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel12.setText("Quantity");
        sales.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));

        txtSalesQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSalesQuantityFocusLost(evt);
            }
        });
        sales.add(txtSalesQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 110, -1));

        jLabel13.setText("Date");
        sales.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));
        sales.add(salesDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, -1, -1));

        comProductName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sales.add(comProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 110, -1));

        jLabel14.setText("Unit Price");
        sales.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));
        sales.add(txtSalesUnitPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 120, -1));

        jLabel15.setText("Total Price");
        sales.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));
        sales.add(txtSalesTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 120, -1));

        btnSalesSave.setBackground(new java.awt.Color(0, 255, 0));
        btnSalesSave.setText("Save");
        btnSalesSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalesSaveMouseClicked(evt);
            }
        });
        sales.add(btnSalesSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        btnSalesEdit.setBackground(new java.awt.Color(204, 255, 51));
        btnSalesEdit.setText("Edit");
        sales.add(btnSalesEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        btnSalesReset.setBackground(new java.awt.Color(255, 204, 204));
        btnSalesReset.setText("Reset");
        sales.add(btnSalesReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, -1, -1));

        btnSalesDelete.setBackground(new java.awt.Color(255, 0, 51));
        btnSalesDelete.setText("Delete");
        sales.add(btnSalesDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, -1));

        jLabel16.setText("Stock");
        sales.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, -1, -1));
        sales.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 90, 20));

        mainView2.addTab("sales", sales);

        stock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 255, 204));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Stock");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 70));

        stock.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 70));

        tblStock.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblStock);

        stock.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 127, 710, 360));

        mainView2.addTab("stock", stock);

        report.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("report");
        report.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 180, 140, 130));

        mainView2.addTab("report", report);

        getContentPane().add(mainView2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 710, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalesActionPerformed

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        mainView2.setSelectedIndex(0);
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalesMouseClicked
        // TODO add your handling code here:
        mainView2.setSelectedIndex(1);
        showProductToCombo();
    }//GEN-LAST:event_btnSalesMouseClicked

    private void btnStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStockMouseClicked
        // TODO add your handling code here:
        mainView2.setSelectedIndex(2);
    }//GEN-LAST:event_btnStockMouseClicked

    private void btnReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportMouseClicked
        // TODO add your handling code here:
        mainView2.setSelectedIndex(3);
    }//GEN-LAST:event_btnReportMouseClicked

    private void btnProductAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductAddMouseClicked
        // TODO add your handling code here:
        addProduct();
    }//GEN-LAST:event_btnProductAddMouseClicked

    private void txtQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantityFocusLost
        // TODO add your handling code here:
        getTotalPrice();
    }//GEN-LAST:event_txtQuantityFocusLost

    private void btnProductResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductResetMouseClicked
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnProductResetMouseClicked

    private void tblProductViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductViewMouseClicked
        // TODO add your handling code here:

        int rowIndex = tblProductView.getSelectedRow();

        String id = tblProductView.getModel().getValueAt(rowIndex, 0).toString();
        String name = tblProductView.getModel().getValueAt(rowIndex, 1).toString();
        String unitPrice = tblProductView.getModel().getValueAt(rowIndex, 2).toString();
        String quantity = tblProductView.getModel().getValueAt(rowIndex, 3).toString();
        String totalPrice = tblProductView.getModel().getValueAt(rowIndex, 4).toString();

        txtId.setText(id);
        txtName.setText(name);
        txtUnitPrice.setText(unitPrice);
        txtQuantity.setText(quantity);
        txtTotalPrice.setText(totalPrice);

    }//GEN-LAST:event_tblProductViewMouseClicked

    private void btnProductDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductDeleteMouseClicked
        // TODO add your handling code here:
        deleteProduct();
    }//GEN-LAST:event_btnProductDeleteMouseClicked

    private void btnProductEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductEditMouseClicked
        // TODO add your handling code here:
        editProduct();
    }//GEN-LAST:event_btnProductEditMouseClicked

    private void txtSalesQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSalesQuantityFocusLost
        // TODO add your handling code here:
        getTotalSalesPrice();
    }//GEN-LAST:event_txtSalesQuantityFocusLost

    private void btnSalesSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalesSaveMouseClicked
        // TODO add your handling code here:
        addSales();
    }//GEN-LAST:event_btnSalesSaveMouseClicked

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
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel add;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnProductAdd;
    private javax.swing.JButton btnProductDelete;
    private javax.swing.JButton btnProductEdit;
    private javax.swing.JButton btnProductReset;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnSales;
    private javax.swing.JButton btnSalesDelete;
    private javax.swing.JButton btnSalesEdit;
    private javax.swing.JButton btnSalesReset;
    private javax.swing.JButton btnSalesSave;
    private javax.swing.JButton btnStock;
    private javax.swing.JComboBox<String> comProductName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblStock;
    private javax.swing.JPanel mainView;
    private javax.swing.JTabbedPane mainView2;
    private javax.swing.JPanel report;
    private javax.swing.JPanel sales;
    private com.toedter.calendar.JDateChooser salesDate;
    private javax.swing.JPanel stock;
    private javax.swing.JTable tblProductView;
    private javax.swing.JTable tblStock;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSalesQuantity;
    private javax.swing.JTextField txtSalesTotalPrice;
    private javax.swing.JTextField txtSalesUnitPrice;
    private javax.swing.JTextField txtTotalPrice;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
