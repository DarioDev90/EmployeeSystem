package com.dariodev;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeController implements Initializable {

    @FXML
    private TableColumn<Employee, String> EmployeeCity;

    @FXML
    private TableColumn<Employee, String> EmployeeFirstName;

    @FXML
    private TableColumn<Employee, String> EmployeeID;

    @FXML
    private TableColumn<Employee, String> EmployeeLastName;

    @FXML
    private TableColumn<Employee, String> EmployeePhone;

    @FXML
    private TableColumn<Employee, String> EmployeeSalary;

    @FXML
    private TableView<Employee> TableViewEmployeeData;

    @FXML
    private Label annualSalary;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonSave;

    @FXML
    private Label city;

    @FXML
    private Label firstName;

    @FXML
    private Label labelTitulo;

    @FXML
    private Label lastName;

    @FXML
    private Label phone;

    @FXML
    private TextField textFieldAnnualSalary;

    @FXML
    private TextField textFieldCity;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldLastName;

    @FXML
    private TextField textFieldPhone;

    Connection conexion;
    PreparedStatement preparedStament;
    ResultSet resultSet;
    int myIndex;
    int id;

    @FXML
    void DeleteEmployeeData(ActionEvent event) {
        myIndex = TableViewEmployeeData.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(TableViewEmployeeData.getItems().get(myIndex).getId()));

        try {
            preparedStament = conexion.prepareStatement("DELETE FROM employee WHERE id = ?");
            preparedStament.setInt(1, id);
            preparedStament.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Delete");
            alert.setHeaderText("Employee Delete");
            alert.setContentText("Record Delete");
            alert.showAndWait();

            TableView();

        } catch (SQLException e) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void EditEmployeeData(ActionEvent event) {
        String firstname, lastname, city, phone, salary;
        myIndex = TableViewEmployeeData.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(TableViewEmployeeData.getItems().get(myIndex).getId()));

        firstname = textFieldFirstName.getText();
        lastname = textFieldLastName.getText();
        city = textFieldCity.getText();
        phone = textFieldPhone.getText();
        salary = textFieldAnnualSalary.getText();

        try {
            preparedStament = conexion.prepareStatement("UPDATE employee SET firstname = ?, lastname = ?, city = ?, phone = ?, salary = ? WHERE id = ?");
            preparedStament.setString(1, firstname);
            preparedStament.setString(2, lastname);
            preparedStament.setString(3, city);
            preparedStament.setInt(4, Integer.parseInt(phone));
            preparedStament.setInt(5, Integer.parseInt(salary));
            preparedStament.setInt(6, id);
            preparedStament.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Udpate");
            alert.setHeaderText("Employee Udpate");
            alert.setContentText("Record Udpated");
            alert.showAndWait();

            TableView();

            LimpiarTable();

        } catch (SQLException e) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void SaveEmployeeData(ActionEvent event) {
        String firstname, lastname, city, phone, salary;

        firstname = textFieldFirstName.getText();
        lastname = textFieldLastName.getText();
        city = textFieldCity.getText();
        phone = textFieldPhone.getText();
        salary = textFieldAnnualSalary.getText();

        try {
            preparedStament = conexion.prepareStatement("INSERT INTO employee(firstname,lastname,city,phone,salary) VALUES(?,?,?,?,?)");
            preparedStament.setString(1, firstname);
            preparedStament.setString(2, lastname);
            preparedStament.setString(3, city);
            preparedStament.setInt(4, Integer.parseInt(phone));
            preparedStament.setInt(5, Integer.parseInt(salary));
            preparedStament.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee Registration");
            alert.setHeaderText("Employee Registration");
            alert.setContentText("Record Added");
            alert.showAndWait();

            TableView();

            LimpiarTable();

        } catch (SQLException e) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void TableView() {
        Connect();
        ObservableList<Employee> employees = FXCollections.observableArrayList();

        try {
            preparedStament = conexion.prepareStatement("SELECT id,firstname,lastname,city,phone,salary FROM employee");
            resultSet = preparedStament.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(String.valueOf(resultSet.getInt("id")));
                employee.setFirstName(resultSet.getString("firstname"));
                employee.setLastName(resultSet.getString("lastname"));
                employee.setCity(resultSet.getString("city"));
                employee.setPhone(String.valueOf(resultSet.getInt("phone")));
                employee.setSalary(String.valueOf(resultSet.getInt("salary")));
                employees.add(employee);
            }

            TableViewEmployeeData.setItems(employees);
            EmployeeID.setCellValueFactory(f -> f.getValue().idProperty());
            EmployeeFirstName.setCellValueFactory(f -> f.getValue().firstnameProperty());
            EmployeeLastName.setCellValueFactory(f -> f.getValue().lastnameProperty());
            EmployeeCity.setCellValueFactory(f -> f.getValue().cityProperty());
            EmployeePhone.setCellValueFactory(f -> f.getValue().phoneProperty());
            EmployeeSalary.setCellValueFactory(f -> f.getValue().SalaryProperty());

        } catch (SQLException e) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, e);
        }

        TableViewEmployeeData.setRowFactory(tv -> {
            TableRow<Employee> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = TableViewEmployeeData.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(TableViewEmployeeData.getItems().get(myIndex).getId()));
                    textFieldFirstName.setText(TableViewEmployeeData.getItems().get(myIndex).getFirstName());
                    textFieldLastName.setText(TableViewEmployeeData.getItems().get(myIndex).getLastName());
                    textFieldCity.setText(TableViewEmployeeData.getItems().get(myIndex).getCity());
                    textFieldPhone.setText(TableViewEmployeeData.getItems().get(myIndex).getPhone());
                    textFieldAnnualSalary.setText(TableViewEmployeeData.getItems().get(myIndex).getSalary());
                }
            });
            return  myRow;
        });
    }

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/employee_system?useTimeZone=true&serverTimeZone=UTC",
                    "root",
                    "1234");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LimpiarTable() {

        textFieldFirstName.setText("");
        textFieldLastName.setText("");
        textFieldCity.setText("");
        textFieldPhone.setText("");
        textFieldAnnualSalary.setText("");
        textFieldFirstName.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connect();
        TableView();
    }
}