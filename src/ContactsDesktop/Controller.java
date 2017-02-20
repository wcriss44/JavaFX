package ContactsDesktop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable{

    /*********************
     * Variables
     ********************/

    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    @FXML
    TextField textName;
    @FXML
    TextField textEmail;
    @FXML
    TextField textPhone;
    @FXML
    ListView contactList;

    /*********************
     * Constructors
     ********************/

    public Controller() throws IOException{

    }
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        contactList.setItems(contacts);
        try {
            readJson();
        } catch (Exception e) {
            System.out.println("Something went wrong while reading!");
        }
    }

    /*********************
     * Add and Remove
     ********************/
    // Phone must be shorter than 14 and contain a " - ".
    // Email must contain a " @ " and " . "
    // All three fields must have values
    public void addContact() throws IOException{
        if(textName.getText().isEmpty() || (textEmail.getText().isEmpty() && textEmail.getText().length() > 14 ) || textPhone.getText().isEmpty()){
            contactAlerts();
        } else  {
            if(textEmail.getText().contains("@") && textEmail.getText().contains(".")){
                StringBuilder builder = new StringBuilder(textPhone.getText());
                if(textPhone.getText().contains("-")){
                    Contact contact = new Contact(textName.getText(), textPhone.getText(), textEmail.getText());
                    contacts.add(contact);
                    textName.setText("");
                    textPhone.setText("");
                    textEmail.setText("");
                    updateContacts();
                } else {
                    contactAlerts();
                }
            } else {
                contactAlerts();
            }
        }
    }
    //gets the selected contact and removes it from collections, then calls updateContacts to refresh the JSON file
    public void removeContact() throws IOException{
        Contact contact = (Contact)contactList.getSelectionModel().getSelectedItem();
        contacts.remove(contact);
        updateContacts();

    }

    /*********************
     * Read and Write JSON
     ********************/
    //used to write an updated contact list after a contact is added or removed
    public void updateContacts() throws IOException{
        File file = new File("contacts.json");
        FileWriter jsonMaker = new FileWriter(file);
        JsonSerializer serializer = new JsonSerializer();
        for(Contact contactLoop: contacts){
            String json = serializer.serialize(contactLoop);
            jsonMaker.append(json);
            jsonMaker.append("\n");
        }
        jsonMaker.close();
    }
    //Used to initially read in the JSON file when app starts
    public void readJson() throws IOException{
        File file = new File("contacts.json");
        Scanner scannerFile = new Scanner(file);
        JsonParser parser = new JsonParser();

        scannerFile.useDelimiter("\n");
        while(scannerFile.hasNext()){
          String  contactInfo = scannerFile.nextLine();
          Contact contact = parser.parse(contactInfo, Contact.class);
           contacts.add(contact);
        }
    }

    /*********************
     * Alert and other
     ********************/
    //used to pop up Alert windows if invalid contact information is used
    public void contactAlerts(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Invalid contact entry");
        alert.setHeaderText("Invalid contact");
        alert.setContentText("Please enter a valid contact");
        alert.showAndWait();
    }


}
