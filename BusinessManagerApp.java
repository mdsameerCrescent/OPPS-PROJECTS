import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Product {
    String name;
    String category;
    double price;
    int stock;

    public Product(String name, String category, double price, int stock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
}

class Sale {
    Product product;
    int quantity;

    public Sale(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return quantity * product.price;
    }
}

public class BusinessManagerApp extends JFrame {
    private final ArrayList<Product> products = new ArrayList<>();
    private final ArrayList<Sale> sales = new ArrayList<>();

    private final DefaultTableModel productTableModel;
    private final JTable productTable;

    public BusinessManagerApp() {
        setTitle("Business Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        // UI Components
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(10);

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(10);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(5);

        JLabel stockLabel = new JLabel("Stock:");
        JTextField stockField = new JTextField(5);

        JButton addButton = new JButton("Add Product");
        JButton updateButton = new JButton("Update Selected");
        JButton deleteButton = new JButton("Delete Selected");
        JButton sellButton = new JButton("Sell Product");
        JButton reportButton = new JButton("Sales Report");

        productTableModel = new DefaultTableModel(new String[]{"Name", "Category", "Price", "Stock"}, 0);
        productTable = new JTable(productTableModel);

        JScrollPane scrollPane = new JScrollPane(productTable);

        JPanel inputPanel = new JPanel();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(stockLabel);
        inputPanel.add(stockField);
        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(sellButton);
        inputPanel.add(reportButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event: Add Product
        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String category = categoryField.getText();
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());
                Product p = new Product(name, category, price, stock);
                products.add(p);
                productTableModel.addRow(new Object[]{name, category, price, stock});
                clearFields(nameField, categoryField, priceField, stockField);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid data.");
            }
        });

        // Event: Update Product
        updateButton.addActionListener(e -> {
            int selected = productTable.getSelectedRow();
            if (selected != -1) {
                try {
                    Product p = products.get(selected);
                    p.name = nameField.getText();
                    p.category = categoryField.getText();
                    p.price = Double.parseDouble(priceField.getText());
                    p.stock = Integer.parseInt(stockField.getText());
                    updateTable();
                    clearFields(nameField, categoryField, priceField, stockField);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Enter valid data.");
                }
            }
        });

        // Event: Delete Product
        deleteButton.addActionListener(e -> {
            int selected = productTable.getSelectedRow();
            if (selected != -1) {
                products.remove(selected);
                productTableModel.removeRow(selected);
            }
        });

        // Event: Sell Product
        sellButton.addActionListener(e -> {
            int selected = productTable.getSelectedRow();
            if (selected != -1) {
                String qtyStr = JOptionPane.showInputDialog("Enter quantity to sell:");
                try {
                    int qty = Integer.parseInt(qtyStr);
                    Product p = products.get(selected);
                    if (qty <= p.stock && qty > 0) {
                        p.stock -= qty;
                        sales.add(new Sale(p, qty));
                        updateTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid quantity.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Enter a valid number.");
                }
            }
        });

        // Event: Show Sales Report
        reportButton.addActionListener(e -> {
            int totalItems = 0;
            double totalRevenue = 0;
            for (Sale sale : sales) {
                totalItems += sale.quantity;
                totalRevenue += sale.getTotal();
            }
            JOptionPane.showMessageDialog(this, "Total Sold Items: " + totalItems + "\nTotal Revenue: ₹" + totalRevenue);
        });

        // Fill form when row is selected
        productTable.getSelectionModel().addListSelectionListener(e -> {
            int selected = productTable.getSelectedRow();
            if (selected != -1) {
                Product p = products.get(selected);
                nameField.setText(p.name);
                categoryField.setText(p.category);
                priceField.setText(String.valueOf(p.price));
                stockField.setText(String.valueOf(p.stock));
            }
        });

        setVisible(true);
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) field.setText("");
    }

    private void updateTable() {
        productTableModel.setRowCount(0);
        for (Product p : products) {
            productTableModel.addRow(new Object[]{p.name, p.category, p.price, p.stock});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BusinessManagerApp::new);
    }
}
