import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;


import animatefx.animation.ZoomIn;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ControllerClass implements Initializable{

    @FXML
    private Label TextD1;

    @FXML
    private Label TextD2;

    @FXML
    private Label TextD3;

    @FXML
    private Button btnDash;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMain;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnUsers;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblStatusMini;

    @FXML
    private Pane pnMain;

    @FXML
    private Pane pnProfile;

    @FXML
    private Pane pnlDashboard;

    @FXML
    private Pane pnlStatus;
    @FXML
    private ChoiceBox<String> boxInfect;

    @FXML
    private ChoiceBox<String> boxVac;

    @FXML
    private ChoiceBox<String> boxdoes1;

    @FXML
    private ChoiceBox<String> boxdoes2;

    @FXML
    private ChoiceBox<String> boxdoes3;

    @FXML
    private Text textage;

    @FXML
    private Text textcovid;

    @FXML
    private Text textdisease;

    @FXML
    private Text textdoes1;

    @FXML
    private Text textdoes2;

    @FXML
    private Text textdoes3;

    @FXML
    private Text textheigh;

    @FXML
    private Text textlastname;

    @FXML
    private Text textname;

    @FXML
    private Text textsex;

    @FXML
    private Text textvacc;

    @FXML
    private Text textweight;

    @FXML
    private TableView<CountryData> table;

    @FXML
    private TableColumn<CountryData, String> tabledoes1;

    @FXML
    private TableColumn<CountryData, String> tabledoes2;

    @FXML
    private TableColumn<CountryData, String> tabledoes3;

    @FXML
    private TableColumn<CountryData, String> tableid;

    @FXML
    private TableColumn<CountryData, String> tableinfect;

    @FXML
    private TableColumn<CountryData, String> tablelastname;

    @FXML
    private TableColumn<CountryData, String> tablename;

    @FXML
    private TableColumn<CountryData, String> tablevacc;

    

    @FXML
    private TextField tflastname;

    @FXML
    private TextField tfname;

  

    @FXML
    void ClickClose(ActionEvent event) throws IOException  {
        

        if (event.getSource().equals(btnProfile)) {
            lblStatusMini.setText("/home/profile");
            lblStatus.setText("Profile");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(205, 92, 92),CornerRadii.EMPTY,Insets.EMPTY)));
            new ZoomIn(pnProfile).play();
            pnProfile.toFront();
        }

        if (event.getSource() == btnMain){
            lblStatusMini.setText("/home/");
            lblStatus.setText("Home");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(249, 117, 88),CornerRadii.EMPTY,Insets.EMPTY)));
            new ZoomIn(pnMain).play();
            pnMain.toFront();
        
        }
        if(event.getSource() == btnLogout ){
            System.exit(0);
        }


        if (event.getSource() == btnDash) {
            lblStatusMini.setText("/home/dashboard");
            lblStatus.setText("Dashboard");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(133, 193, 233 ),CornerRadii.EMPTY,Insets.EMPTY)));
            new ZoomIn(pnlDashboard).play();
            pnlDashboard.toFront();
        }

        if (event.getSource() == btnEdit){
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("EditController.fxml"));
            Scene scene = new Scene(root);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
        if(event.getSource() == btnDelete){
             delete("record.txt", controllreLog.getUsername());
             update();
        }
       
    }


    @FXML
    void handleClose(MouseEvent event) throws IOException {
        
    }

    
    Collection<CountryData> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        try {
            list = Files.readAllLines(new File("record.txt").toPath()).stream().map
            (line -> {
                String[] details = line.split(",");
                CountryData cd = new CountryData();
                cd.setCountry(details[2]);
                System.out.println(details[2]);
                cd.setCapital(details[3]);
                System.out.println(details[3]);
                cd.setPopulation(details[4]);
                System.out.println(details[4]);
                cd.setDemocracy(details[10]);
                System.out.println(details[9]);
                cd.setDemocracy4(details[11]);
                cd.setDemocracy5(details[12]);
                cd.setDemocracy6(details[13]);
                cd.setDemocracy7(details[14]);
                cd.setDemocracy8(details[15]);
                return cd;
            })
            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObservableList<CountryData> details = FXCollections.observableArrayList(list);
        

        tableid.setCellValueFactory(data -> data.getValue().countryProperty());
        //System.out.println(Colid);
        tablename.setCellValueFactory(data -> data.getValue().capitalProperty());
        tablelastname.setCellValueFactory(data -> data.getValue().populationProperty());
        tableinfect.setCellValueFactory(data -> data.getValue().democracyProperty());
        tablevacc.setCellValueFactory(data -> data.getValue().democracyProperty4());
        tabledoes1.setCellValueFactory(data -> data.getValue().democracyProperty5());
        tabledoes2.setCellValueFactory(data -> data.getValue().democracyProperty6());
        tabledoes3.setCellValueFactory(data -> data.getValue().democracyProperty7());

        table.setItems(details);

        String fname = "";
        String lname= "";
        String agr= "";
        String sex= "";
        String height = "";
        String weight = "";
        String vacc = "";
        String does1 = "";
        String does2 = "";
        String does3 = "";
        String dis ="";
        String covid = "";
        try {
            File file = new File("record.txt");
            Scanner input = new Scanner(file);
            
            while (input.hasNext())
            {
    
                String[] line = (input.nextLine()).split(",");
                //System.out.pritln(line[0]);
                if (line[0].equals(controllreLog.getUsername())) {
                    fname = line[3];
                    lname = line[4];
                    agr = line[5];
                    sex = line[6];
                    height = line[7];
                    weight = line[8];
                    vacc = line[11];
                    does1 = line[12];
                    does2 = line[13];
                    does3 = line[14];
                    dis = line[15];
                    covid = line[10];
                }
               
            }
            input.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
        textname.setText(fname);
        textlastname.setText(lname);
        textage.setText(agr);
        textsex.setText(sex);
        textheigh.setText(height);
        textweight.setText(weight);
        textvacc.setText(vacc);
        textdoes1.setText(does1);
        textdoes2.setText(does2);
        textdoes3.setText(does3);
        textdisease.setText(dis);
        textcovid.setText(covid);

    }

    public static void delete(String filepath, String editAim){
        String tempfile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempfile);
        
        String Uname ="" ;
        String Pname ="" ;
        String id ="";
        String fname = "";
        String lname= "";
        String agr= "";
        String sex= "";
        String height = "";
        String weight = "";
        String vacc = "";
        String does1 = "";
        String does2 = "";
        String does3 = "";
        String dis ="";
        String covid = "";
        try {
            FileWriter fw = new FileWriter(tempfile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File(filepath));

            while(x.hasNext()) {
                String[] line = (x.nextLine()).split(",");
                Uname = line[0];
                Pname = line[1];
                id = line[2];
                fname = line[3];
                lname = line[4];
                agr = line[5];
                sex = line[6];
                height = line[7];
                weight = line[8];
                covid = line[10];
                vacc = line[11];
                does1 = line[12];
                does2 = line[13];
                does3 = line[14];
                dis = line[15];
                if (!line[0].equalsIgnoreCase(editAim)) {
                    pw.println(Uname +","+Pname+","+id+","+fname+","+lname+","+agr+","+sex+","+height+","+weight+","+line[9]+","+covid+","+vacc+","+does1+","+does2+","+does3+","+dis);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            bw.close();
            x.close();
            fw.close();
            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
        }catch(Exception e){
            System.out.println("ERROR");
        }
    }
    
    public void update(){
        try {
            list = Files.readAllLines(new File("record.txt").toPath()).stream().map
            (line -> {
                String[] details = line.split(",");
                CountryData cd = new CountryData();
                cd.setCountry(details[2]);
                System.out.println(details[2]);
                cd.setCapital(details[3]);
                System.out.println(details[3]);
                cd.setPopulation(details[4]);
                System.out.println(details[4]);
                cd.setDemocracy(details[9]);
                System.out.println(details[9]);
                cd.setDemocracy4(details[10]);
                cd.setDemocracy5(details[11]);
                cd.setDemocracy6(details[12]);
                cd.setDemocracy7(details[13]);
                cd.setDemocracy8(details[14]);
                return cd;
            })
            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObservableList<CountryData> details = FXCollections.observableArrayList(list);
        

        tableid.setCellValueFactory(data -> data.getValue().countryProperty());
        //System.out.println(Colid);
        tablename.setCellValueFactory(data -> data.getValue().capitalProperty());
        tablelastname.setCellValueFactory(data -> data.getValue().populationProperty());
        tableinfect.setCellValueFactory(data -> data.getValue().democracyProperty());
        tablevacc.setCellValueFactory(data -> data.getValue().democracyProperty4());
        tabledoes1.setCellValueFactory(data -> data.getValue().democracyProperty5());
        tabledoes2.setCellValueFactory(data -> data.getValue().democracyProperty6());
        tabledoes3.setCellValueFactory(data -> data.getValue().democracyProperty7());

        table.setItems(details);

        String fname = "";
        String lname= "";
        String agr= "";
        String sex= "";
        String height = "";
        String weight = "";
        String vacc = "";
        String does1 = "";
        String does2 = "";
        String does3 = "";
        String dis ="";
        String covid = "";
        try {
            File file = new File("record.txt");
            Scanner input = new Scanner(file);
            
            while (input.hasNext())
            {
    
                String[] line = (input.nextLine()).split(",");
                //System.out.pritln(line[0]);
                if (line[0].equals(controllreLog.getUsername())) {
                    fname = line[3];
                    lname = line[4];
                    agr = line[5];
                    sex = line[6];
                    height = line[7];
                    weight = line[8];
                    vacc = line[11];
                    does1 = line[12];
                    does2 = line[13];
                    does3 = line[14];
                    dis = line[15];
                    covid = line[10];
                }
               
            }
            input.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
        textname.setText(fname);
        textlastname.setText(lname);
        textage.setText(agr);
        textsex.setText(sex);
        textheigh.setText(height);
        textweight.setText(weight);
        textvacc.setText(vacc);
        textdoes1.setText(does1);
        textdoes2.setText(does2);
        textdoes3.setText(does3);
        textdisease.setText(dis);
        textcovid.setText(covid);
    }
    private class CountryData {
        StringProperty country = new SimpleStringProperty();
        StringProperty capital = new SimpleStringProperty();
        StringProperty population = new SimpleStringProperty();
        StringProperty democracy = new SimpleStringProperty();
        StringProperty democracy4 = new SimpleStringProperty();
        StringProperty democracy5 = new SimpleStringProperty();
        StringProperty democracy6 = new SimpleStringProperty();
        StringProperty democracy7 = new SimpleStringProperty();
        StringProperty democracy8 = new SimpleStringProperty();
        
        public final StringProperty countryProperty() {
            return this.country;
        }

        public final String getCountry() {
            return this.countryProperty().get();
        }

        public final void setCountry(final String details) {
            this.countryProperty().set(details);
        }

        public final StringProperty capitalProperty() {
            return this.capital;
        }

        public final java.lang.String getCapital() {
            return this.capitalProperty().get();
        }

        public final void setCapital(final java.lang.String capital) {
            this.capitalProperty().set(capital);
        }

        public final StringProperty populationProperty() {
            return this.population;
        }

        public final java.lang.String getPopulation() {
            return this.populationProperty().get();
        }

        public final void setPopulation(final java.lang.String population) {
            this.populationProperty().set(population);
        }

        public final StringProperty democracyProperty() {
            return this.democracy;
        }

        public final java.lang.String getDemocracy() {
            return this.democracyProperty().get();
        }

        public final void setDemocracy(final java.lang.String democracy) {
            this.democracyProperty().set(democracy);
        }

        public final StringProperty democracyProperty4() {
            return this.democracy4;
        }

        public final java.lang.String getDemocracy4() {
            return this.democracyProperty4().get();
        }

        public final void setDemocracy4(final java.lang.String democracy4) {
            this.democracyProperty4().set(democracy4);
        }

        public final StringProperty democracyProperty5() {
            return this.democracy5;
        }

        public final java.lang.String getDemocracy5() {
            return this.democracyProperty5().get();
        }

        public final void setDemocracy5(final java.lang.String democracy5) {
            this.democracyProperty5().set(democracy5);
        }

        public final StringProperty democracyProperty6() {
            return this.democracy6;
        }

        public final java.lang.String getDemocracy6() {
            return this.democracyProperty6().get();
        }

        public final void setDemocracy6(final java.lang.String democracy6) {
            this.democracyProperty6().set(democracy6);
        }

        public final StringProperty democracyProperty7() {
            return this.democracy7;
        }

        public final java.lang.String getDemocracy7() {
            return this.democracyProperty7().get();
        }

        public final void setDemocracy7(final java.lang.String democracy7) {
            this.democracyProperty7().set(democracy7);
        }

        public final StringProperty democracyProperty8() {
            return this.democracy8;
        }

        public final java.lang.String getDemocracy8() {
            return this.democracyProperty8().get();
        }

        public final void setDemocracy8(final java.lang.String democracy8) {
            this.democracyProperty8().set(democracy8);
        }
    }

    


}
