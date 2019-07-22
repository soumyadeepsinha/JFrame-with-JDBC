/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package students.niit;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class Students_NIIT extends JFrame implements ActionListener {

    JLabel heading, lid, lBatch, lName, lcourse, lPhone, laddress, lcity, lstate, lpin;
    JTextField idField, batchField, NameField, coursefield, phoneField, addressField,
            cityField, pinField;
    JComboBox<String> jc, jcb;
    JButton insert, exit;
    JPanel p1;
    JFrame f1;
    Connection con = null;
    Statement stmt = null;
    PreparedStatement stat = null;

    public Students_NIIT() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            con = DriverManager.getConnection("jdbc:derby://localhost:1527/getdetails", "sa", "123654");
            con.setAutoCommit(false);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error : " + e);
        }
    }

    public void studentsdetails() {

        String arr[] = {"DT", "GNIIT", "DCA", "PGFA", "C++", "Python", "PHP", "Basic"};
        String arr2[] = {"Andra Pradesh", "Delhi", "Goa", "Gujarat", "Karnataka", "Maharashtra","Punjab", "Rajasthan","West Bengal"};


        f1 = new JFrame("   Welcome to students.com  ");
        p1 = new JPanel();
        heading = new JLabel("ENTER STUDENT DETAILS");
        lid = new JLabel("Student ID :");
        lBatch = new JLabel("Batch ID :");
        lName = new JLabel("Name :");
        lcourse = new JLabel("Course :");
        lPhone = new JLabel("Contact No :");
        laddress = new JLabel("Address :");
        lcity = new JLabel("City :");
        lstate = new JLabel("State :");
        lpin = new JLabel("PIN :");

        idField = new JTextField(30);
        batchField = new JTextField(30);
        NameField = new JTextField(40);
        jc = new JComboBox<>(arr);
        phoneField = new JTextField(50);
        addressField = new JTextField(100);
        cityField = new JTextField(15);
        jcb = new JComboBox<>(arr2);
        //stateField = new JTextField(15);
        pinField = new JTextField(15);
        insert = new JButton("Insert");
        exit = new JButton("Exit");

        p1.setLayout(null);
        heading.setBounds(200, 35, 250, 40);
        p1.add(heading);

        lid.setBounds(75, 90, 100, 30);
        idField.setBounds(400, 90, 200, 25);
        p1.add(lid);
        p1.add(idField);

        lBatch.setBounds(75, 120, 100, 30);
        batchField.setBounds(400, 120, 200, 25);
        p1.add(lBatch);
        p1.add(batchField);

        lName.setBounds(75, 150, 200, 30);
        NameField.setBounds(400, 150, 200, 25);
        p1.add(lName);
        p1.add(NameField);

        lcourse.setBounds(75, 180, 200, 30);
        jc.setBounds(400, 180, 200, 25);
        p1.add(lcourse);
        p1.add(jc);

        lPhone.setBounds(75, 210, 200, 30);
        phoneField.setBounds(400, 210, 200, 25);
        p1.add(lPhone);
        p1.add(phoneField);

        laddress.setBounds(75, 240, 200, 30);
        addressField.setBounds(400, 240, 200, 25);
        p1.add(laddress);
        p1.add(addressField);

        lcity.setBounds(75, 270, 200, 30);
        cityField.setBounds(400, 270, 200, 25);
        p1.add(lcity);
        p1.add(cityField);

        lstate.setBounds(75, 300, 200, 30);
        jcb.setBounds(400, 300, 200, 25);
        p1.add(lstate);
        p1.add(jcb);

        lpin.setBounds(75, 330, 200, 30);
        pinField.setBounds(400, 330, 200, 25);
        p1.add(lpin);
        p1.add(pinField);

        insert.setBounds(175, 400, 100, 30);
        exit.setBounds(325, 400, 100, 30);
        p1.add(insert);
        p1.add(exit);

        f1.add(p1);
        f1.setSize(800, 650);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        insert.addActionListener(this);
        exit.addActionListener((ActionListener) this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("Exit".equals(ae.getActionCommand())) {
            System.exit(0);
        }
        if ("Insert".equals(ae.getActionCommand())) {
            if (!idField.getText().equals("")) {
                try {
                    stat = con.prepareStatement("INSERT INTO STUDENTSINFO VALUES(?,?,?,?,?,?,?,?,?)");
                    String pid = idField.getText();
                    String bid = batchField.getText();
                    String pname = NameField.getText();
                    Object pcourse = jc.getSelectedItem();
                    String pphone = phoneField.getText();
                    String padd = addressField.getText();
                    String pcity = cityField.getText();
                    Object pstate = jcb.getSelectedItem();
                    //String pstate = stateField.getText();
                    String pzip = pinField.getText();
                    int pin = Integer.parseInt(pzip);

                    stat.setString(1, pid);
                    stat.setString(2, bid);
                    stat.setString(3, pname);
                    stat.setObject(4, pcourse);
                    stat.setString(5, pphone);
                    stat.setString(6, padd);
                    stat.setString(7, pcity);
                    stat.setObject(8, pstate);
                    stat.setInt(9, pin);

                    System.out.println(stat.executeUpdate());
                    con.commit();
                    idField.setText("");
                    batchField.setText("");
                    NameField.setText("");
                    jc.setSelectedItem("");
                    phoneField.setText("");
                    addressField.setText("");
                    cityField.setText("");
                    jcb.setSelectedItem("");
                    //stateField.setText("");
                    pinField.setText("");

                    JOptionPane.showMessageDialog(f1, "Record Inserted Successfuly.", "Success Message", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Done");
                } catch (SQLException | HeadlessException e) {
                    String msg = e.getMessage();
                    System.err.println(msg);

                    if (e.getMessage().equals(msg)) {
                        JOptionPane.showMessageDialog(f1, "Record already exists.", "Warning Message", JOptionPane.WARNING_MESSAGE);
                        idField.setText("");
                        batchField.setText("");
                        NameField.setText("");
                        jc.setSelectedItem("");
                        phoneField.setText("");
                        addressField.setText("");
                        cityField.setText("");
                        jcb.setSelectedItem("");
                       // stateField.setText("");
                        pinField.setText("");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(f1, "Please fill the details properly.", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
