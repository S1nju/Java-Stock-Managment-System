package Dashboard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import SqlConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashboardController  implements Initializable {
	private double x=0,y=0;
	 @FXML
	    private TableColumn<SourcesData, Integer> Dis_col_did;

	    @FXML
	    private TableColumn<SourcesData, String> Dis_col_lastname;

	    @FXML
	    private TableColumn<SourcesData, String> Dis_col_name;

	    @FXML
	    private TableColumn<SourcesData, String> Dis_col_wilaya;

	    @FXML
	    private Button Dis_create;

	    @FXML
	    private Button Dis_delete;

	    @FXML
	    private TextField Dis_did;

	    @FXML
	    private TextField Dis_name;
	    @FXML
	    private TextField Dis_firstname;
	    @FXML
	    private TextField Dis_willaya;

	    @FXML
	    private TextField Dis_search;

	    @FXML
	    private Button Dis_update;

	    @FXML
	    private AnchorPane catg;

	    @FXML
	    private Button catgoriesbtn;

	    @FXML
	    private TextField cid_cid;

	    @FXML
	    private TableColumn<?, ?> cid_col_name;

	    @FXML
	    private Button cid_create;

	    @FXML
	    private Button cid_delete;

	    @FXML
	    private TextField cid_search;

	    @FXML
	    private Button cid_update;

	    @FXML
	    private Button close;

	    @FXML
	    private Button createbtn4;

	    @FXML
	    private AnchorPane dashboard;

	    @FXML
	    private Button dashboardbtn;

	    @FXML
	    private Button deletebtn4;

	    @FXML
	    private TableView<SourcesData> dtableview;
	    @FXML
	    private TableView<CatgData> ctableview;

	    @FXML
	    private TableView<TypeData> ttableview;
	    @FXML
	    private TableView<ProductData> protableview;

	    @FXML
	    private Button logout;

	    @FXML
	    private ChoiceBox<String> pro_catg;

	    @FXML
	    private TableColumn<?, ?> pro_col_catg;

	    @FXML
	    private TableColumn<?, ?> pro_col_name;

	    @FXML
	    private TableColumn<?, ?> pro_col_pid;

	    @FXML
	    private TableColumn<?, ?> pro_col_price;

	    @FXML
	    private TableColumn<?, ?> pro_col_qte;

	    @FXML
	    private TableColumn<?, ?> pro_col_source;

	    @FXML
	    private TableColumn<?, ?> pro_col_type;

	    @FXML
	    private Button pro_confirm;

	    @FXML
	    private Button pro_delete;

	    @FXML
	    private Button pro_edit;

	    @FXML
	    private TextField pro_name;

	    @FXML
	    private TextField pro_pid;

	    @FXML
	    private TextField pro_price;

	    @FXML
	    private TextField pro_qte;

	    @FXML
	    private TextField pro_search;

	    @FXML
	    private ChoiceBox<String> pro_source;

	    @FXML
	    private ChoiceBox<String> pro_type;

	    @FXML
	    private Button productbtn;

	    @FXML
	    private AnchorPane products;

	    @FXML
	    private TextField search4;

	    @FXML
	    private Button sourcebtn;

	    @FXML
	    private AnchorPane sources;

	    @FXML
	    private TextField tid;
	    @FXML
	    private TextField cid_name ;

	    @FXML
	    private TableColumn<TypeData, String> tid_name;

	    @FXML
	    private TextField type_name;

	    @FXML
	    private Button typebtn;

	    @FXML
	    private AnchorPane types;

	    @FXML
	    private Button updatebtn4;

	    @FXML
	    private Label username;
	    @FXML
	    private Label qtetotale;
	    @FXML
	    private Label moneytotal;
	    @FXML
	    private PieChart pie;

   private  Connection con;
   private Statement s;
   private PreparedStatement prepare;
   private ResultSet res;
   
  
   
   public void productDelete() throws SQLException {
	   

	   String sql = "DELETE FROM product WHERE PID= "+pro_pid.getText();
	   con = JDBC.getcon();
	   try {
		   if(pro_pid.getText().isEmpty()||pro_price.getText().isEmpty()||pro_qte.getText().isEmpty()||pro_type.getSelectionModel().getSelectedItem()==null
				   ||pro_source.getSelectionModel().getSelectedItem()==null 
				   
				   ||pro_catg.getSelectionModel().getSelectedItem()==null || pro_name.getText().isEmpty()) {
				Alert alert2 = new Alert(AlertType.ERROR);
							
							alert2.setTitle("ERROR");
							alert2.setHeaderText("Blank Fields");
							alert2.setContentText("please Fill all the Texts Feilds");
							alert2.show();
						   
					   }else {
	Alert alert2 = new Alert(AlertType.CONFIRMATION);
				
				alert2.setTitle("CONFIRMATION");
				alert2.setHeaderText("Confirm command");
				alert2.setContentText("Are you sure that you want to delete"+ pro_name.getText());
				
				if(alert2.showAndWait().get()== ButtonType.OK) {
					 s  = con.createStatement();
					   s.executeUpdate(sql);	
					   
						 addproductShowliastData();
						 resetafteraddproduct();
						 getpiechartdata();
				
				
				
				}
					   }
		   
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }finally{
		   s.close();
		   con.close();
		   
	   }
	   
   }
   public void productUpdate() throws SQLException {
	   String sql = "UPDATE product SET name= '"+  pro_name.getText() +"',price="+ pro_price.getText()+",qte="+pro_qte.getText()+
			   ",source='"+pro_source.getSelectionModel().getSelectedItem().toString()
			   +"',Category='"+pro_catg.getSelectionModel().getSelectedItem().toString()+
			   "',type='"+pro_type.getSelectionModel().getSelectedItem().toString()+"' WHERE PID="+Integer.parseInt(pro_pid.getText())+"";
	   con = JDBC.getcon();
	   try {
		   if(pro_pid.getText().isEmpty()||pro_price.getText().isEmpty()||pro_qte.getText().isEmpty()||pro_type.getSelectionModel().getSelectedItem()==null
				   ||pro_source.getSelectionModel().getSelectedItem()==null 
				   
				   ||pro_catg.getSelectionModel().getSelectedItem()==null || pro_name.getText().isEmpty()) {
	Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
			   
		   }else {
	Alert alert2 = new Alert(AlertType.CONFIRMATION);
				
				alert2.setTitle("CONFIRMATION");
				alert2.setHeaderText("Confirm command");
				alert2.setContentText("Are you sure that you want to edit"+ pro_name.getText());
				
				if(alert2.showAndWait().get()== ButtonType.OK) {
					 s  = con.createStatement();
					   s.executeUpdate(sql);	
					   
						 addproductShowliastData();
						 resetafteraddproduct();
						 getpiechartdata();
				
				
				
				}
		
		   
	}
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }finally{
		   s.close();
		   con.close();
		   
	   }
	   
   }
   public void productadd() throws SQLException {
	   String querry = "INSERT INTO product (name,Category,qte,type,price,source) VALUES (?,?,?,?,?,?)";
	   con = JDBC.getcon();
	   try {
		   if(pro_price.getText().isEmpty()||pro_qte.getText().isEmpty()||pro_type.getSelectionModel().getSelectedItem()==null
				   ||pro_source.getSelectionModel().getSelectedItem()==null 
				   
				   ||pro_catg.getSelectionModel().getSelectedItem()==null || pro_name.getText().isEmpty()) {
	Alert alert2 = new Alert(AlertType.ERROR);
				
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
			   
		   }else {
		   prepare = con.prepareStatement(querry);
		   prepare.setString(1, pro_name.getText());
		   prepare.setString(2, pro_catg.getSelectionModel().getSelectedItem().toString());
		   prepare.setInt(3,Integer.parseInt(pro_qte.getText()) );
		   prepare.setString(4, pro_type.getSelectionModel().getSelectedItem().toString());
		   prepare.setInt(5, Integer.parseInt(pro_price.getText()));
		   prepare.setString(6,  pro_source.getSelectionModel().getSelectedItem().toString());
		 prepare.executeUpdate();
		 addproductShowliastData();
		 resetafteraddproduct();
		 getpiechartdata();}
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }finally{
		   prepare.close();
		   con.close();
		   
	   }
   }
   public void resetafteraddproduct() {
	   pro_name.setText("");
	   pro_qte.setText("");
	   pro_price.setText("");
	   pro_source.getSelectionModel().clearSelection();
	   pro_type.getSelectionModel().clearSelection();
	   pro_catg.getSelectionModel().clearSelection();
	   
	   
   }
   
   
    public ObservableList<ProductData> addProductData() throws SQLException{
    	ObservableList<ProductData> data = FXCollections.observableArrayList();
    	String sql="SELECT * FROM product";
    	con = JDBC.getcon();
    	try {
    		prepare = con.prepareStatement(sql);
    		res =prepare.executeQuery();
    		ProductData source ;
    		while(res.next()) {
    			source=new ProductData(res.getInt("PID"),res.getString("name"),res.getString("Category"),res.getInt("qte"),res.getString("type"),res.getInt("price"),res.getString("source"));
    			data.add(source);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally{
 		   prepare.close();
 		   con.close();
 		   res.close();
 		   
 	   }
    	return data;
    	
    }
    private ObservableList<ProductData> addproductlist;
    public void addproductShowliastData() throws SQLException {
    	addproductlist= addProductData();
    	pro_col_pid.setCellValueFactory(new PropertyValueFactory<>("Pid"));
    	pro_col_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	pro_col_catg.setCellValueFactory(new PropertyValueFactory<>("Category"));
    	pro_col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
    	pro_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    	pro_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    	pro_col_source.setCellValueFactory(new PropertyValueFactory<>("source"));
    	
    	
    	protableview.setItems(addproductlist);
    	
    }
    public void addproductSelect() {
    	ProductData sd = protableview.getSelectionModel().getSelectedItem();
    	int num = protableview.getSelectionModel().getSelectedIndex();
    	if(num-1<-1) {
    		return ;
    	}
    	pro_pid.setText(sd.getPid().toString());
    	pro_name.setText(sd.getName());
    	pro_price.setText(sd.getPrice().toString());
    	pro_qte.setText(sd.getQte().toString());
    	pro_catg.setValue(sd.getCategory());
    	pro_type.setValue(sd.getType());
    	pro_source.setValue(sd.getSource());
    	
    		
    }
   public void typeDelete() throws SQLException {
	   String sql = "DELETE FROM taille WHERE TID='"+type_name.getText()+"'";
	   con = JDBC.getcon();
	   try {
		   if(type_name.getText().isEmpty()) {
	Alert alert2 = new Alert(AlertType.ERROR);
				
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
			   
		   }else {

	Alert alert2 = new Alert(AlertType.CONFIRMATION);
				
				alert2.setTitle("CONFIRMATION");
				alert2.setHeaderText("Confirm command");
				alert2.setContentText("Are you sure that you want to delete "+ type_name.getText());
				
				if(alert2.showAndWait().get()== ButtonType.OK) {
					 s  = con.createStatement();
					   s.executeUpdate(sql);	
					   
						 addtypeShowliastData();
						 resetafteraddtype();
				
				
				
				}
		
		   }
	   }catch(Exception e) {
			Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }finally{
 		   s.close();
 		   con.close();
 		  
 		   
 	   }
	   
   }
   public void typeUpdate() throws SQLException {
	   String sql = "UPDATE taille SET TID= '"+ type_name.getText() +"' WHERE TID='"+tid.getText()+"'";
	   con = JDBC.getcon();
	   try {
		   if(type_name.getText().isEmpty()) {
	Alert alert2 = new Alert(AlertType.ERROR);
				
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
			   
		   }else {
	Alert alert2 = new Alert(AlertType.CONFIRMATION);
				
				alert2.setTitle("CONFIRMATION");
				alert2.setHeaderText("Confirm command");
				alert2.setContentText("Are you sure that you want to edit "+ type_name.getText());
				
				if(alert2.showAndWait().get()== ButtonType.OK) {
					 s  = con.createStatement();
					   s.executeUpdate(sql);	
						addproductShowliastData();
						 addtypeShowliastData();
						 resetafteraddtype();
				
				
				
				}
		
		   
	}
	   }catch(Exception e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }finally{
 		   s.close();
 		   con.close();
 		
 		   
 	   }
	   
   }
   public void typeadd() throws SQLException {
	   String querry = "INSERT INTO taille (TID) VALUES (?)";
	   con = JDBC.getcon();
	   try {
		   if(type_name.getText().isEmpty()) {
	Alert alert2 = new Alert(AlertType.ERROR);
				
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
			   
		   }else {
		   prepare = con.prepareStatement(querry);
		   prepare.setString(1, type_name.getText());

		 prepare.executeUpdate();
		 addtypeShowliastData();
		 resetafteraddtype();}
	   }catch(Exception e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }finally{
 		   prepare.close();
 		   con.close();
 		 
 		   
 	   }
   }
   public void resetafteraddtype() {
	   type_name.setText("");
	   tid.setText("");
		
	   
   }
   private  List<String> list=new ArrayList<>();
   
    public ObservableList<TypeData> addtypeData() throws SQLException{
    	list=new ArrayList<>();
    	ObservableList<TypeData> data = FXCollections.observableArrayList();
    	String sql="SELECT * FROM taille";
    	con = JDBC.getcon();
    	try {
    		prepare = con.prepareStatement(sql);
    		res =prepare.executeQuery();
TypeData source ;
    		while(res.next()) {
    			
    			list.add(res.getString("TID"));
    			
    			source=new TypeData(res.getString("TID"));
    			data.add(source);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally{
  		   prepare.close();
  		   con.close();
  		   res.close();
  		   
  	   }
    	return data;
    	
    }
  
    private ObservableList<TypeData> addtypelist;
    
  private void typechoisebox() throws SQLException {
	  
	  addtypeData();
	  ObservableList<String> addtypelist2= FXCollections.observableArrayList(list);
	  pro_type.setItems(addtypelist2);
    }
    public void addtypeShowliastData() throws SQLException {
    	addtypelist= addtypeData();
    	tid_name.setCellValueFactory(new PropertyValueFactory<>("TID"));
    	ttableview.setItems(addtypelist);
    	
    }
    public void addtypeSelect() {
    	TypeData sd = ttableview.getSelectionModel().getSelectedItem();
    	int num = ttableview.getSelectionModel().getSelectedIndex();
    	if(num-1<-1) {
    		return ;
    	}
    	
    	type_name.setText(sd.getTID());
    	tid.setText(sd.getTID());
    	
    		
    }
   public void catgDelete() {
	   String sql = "DELETE FROM catg WHERE CID='"+cid_cid.getText()+"'";
	   con = JDBC.getcon();
	   try {
		   if(cid_name.getText().isEmpty()) {
	Alert alert2 = new Alert(AlertType.ERROR);
				
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
			   
		   }else {

	Alert alert2 = new Alert(AlertType.CONFIRMATION);
				
				alert2.setTitle("CONFIRMATION");
				alert2.setHeaderText("Confirm command");
				alert2.setContentText("Are you sure that you want to delete "+ cid_name.getText());
				
				if(alert2.showAndWait().get()== ButtonType.OK) {
					 s  = con.createStatement();
					   s.executeUpdate(sql);	
					   
						 addcatgShowliastData();
						 resetafteraddcatg();
				
				
				
				}
		
		   }
	   }catch(Exception e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }
	   
   }
   public void catgUpdate() {
	   String sql = "UPDATE catg SET CID= '"+ cid_name.getText() +"' WHERE CID='"+cid_cid.getText()+"'";
	   con = JDBC.getcon();
	   try {
		   if(cid_name.getText().isEmpty()) {
	Alert alert2 = new Alert(AlertType.ERROR);
				
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
			   
		   }else {
	Alert alert2 = new Alert(AlertType.CONFIRMATION);
				
				alert2.setTitle("CONFIRMATION");
				alert2.setHeaderText("Confirm command");
				alert2.setContentText("Are you sure that you want to edit "+ cid_name.getText());
				
				if(alert2.showAndWait().get()== ButtonType.OK) {
					 s  = con.createStatement();
					   s.executeUpdate(sql);	
						addproductShowliastData();
						 addcatgShowliastData();
						 resetafteraddcatg();
				
				
				
				}
		
		   
	}
	   }catch(Exception e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }
	   
   }
   public void catgadd() {
	   String querry = "INSERT INTO catg (CID) VALUES (?)";
	   con = JDBC.getcon();
	   try {
		   if(cid_name.getText().isEmpty()) {
	Alert alert2 = new Alert(AlertType.ERROR);
				
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
			   
		   }else {
		   prepare = con.prepareStatement(querry);
		   prepare.setString(1, cid_name.getText());

		 prepare.executeUpdate();
		 addcatgShowliastData();
		 resetafteraddcatg();}
	   }catch(Exception e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }
   }
   public void resetafteraddcatg() {
	   cid_name.setText("");
	   cid_cid.setText("");
		
	   
   }
   private List<String>list2=new ArrayList<>();
    public ObservableList<CatgData> addcatgData(){
    	list2=new ArrayList<>();
    	ObservableList<CatgData> data = FXCollections.observableArrayList();
    	String sql="SELECT * FROM catg";
    	con = JDBC.getcon();
    	try {
    		prepare = con.prepareStatement(sql);
    		res =prepare.executeQuery();
    		CatgData source ;
    		while(res.next()) {
    			list2.add(res.getString("CID"));
    			source=new CatgData(res.getString("CID"));
    			data.add(source);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return data;
    	
    }
    private void catgchoisebox() {
  	  
    	addcatgData();
  	  ObservableList<String> addtypelist2= FXCollections.observableArrayList(list2);
  	  pro_catg.setItems(addtypelist2);
      }
    private ObservableList<CatgData> addcatglist;
    public void addcatgShowliastData() {
    	addcatglist= addcatgData();
    	cid_col_name.setCellValueFactory(new PropertyValueFactory<>("CID"));
    	ctableview.setItems(addcatglist);
    	
    }
    public void addcatgSelect() {
    	CatgData sd = ctableview.getSelectionModel().getSelectedItem();
    	int num = ctableview.getSelectionModel().getSelectedIndex();
    	if(num-1<-1) {
    		return ;
    	}
    	
    	cid_name.setText(sd.getCID());
    	cid_cid.setText(sd.getCID());
    	
    		
    }
   public void SourceDelete() {
	   String sql = "DELETE FROM distributeur WHERE DID= "+Dis_did.getText();
	   con = JDBC.getcon();
	   try {
		   if(Dis_name.getText().isEmpty()||Dis_firstname.getText().isEmpty()||Dis_willaya.getText().isEmpty()) {
				Alert alert2 = new Alert(AlertType.ERROR);
							
							alert2.setTitle("ERROR");
							alert2.setHeaderText("Blank Fields");
							alert2.setContentText("please Fill all the Texts Feilds");
							alert2.show();
						   
					   }else {
	Alert alert2 = new Alert(AlertType.CONFIRMATION);
				
				alert2.setTitle("CONFIRMATION");
				alert2.setHeaderText("Confirm command");
				alert2.setContentText("Are you sure that you want to delete"+ Dis_name.getText());
				
				if(alert2.showAndWait().get()== ButtonType.OK) {
					 s  = con.createStatement();
					   s.executeUpdate(sql);	
					   
						 addSourceShowliastData();
						 resetafteraddsource();
				
				
				
				}
					   }
		   
	   }catch(Exception e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }
	   
   }
   public void SourceUpdate() {
	   String sql = "UPDATE distributeur SET nom= '"+ Dis_name.getText() +"',prenom='"+ Dis_firstname.getText()+"',willaya='"+Dis_willaya.getText()+"' WHERE DID="+Integer.parseInt(Dis_did.getText())+"";
	   con = JDBC.getcon();
	   try {
		   if(Dis_name.getText().isEmpty()||Dis_firstname.getText().isEmpty()||Dis_willaya.getText().isEmpty()) {
	Alert alert2 = new Alert(AlertType.ERROR);
				
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
			   
		   }else {
	Alert alert2 = new Alert(AlertType.CONFIRMATION);
				
				alert2.setTitle("CONFIRMATION");
				alert2.setHeaderText("Confirm command");
				alert2.setContentText("Are you sure that you want to edit "+ Dis_name.getText());
				
				if(alert2.showAndWait().get()== ButtonType.OK) {
					 s  = con.createStatement();
					   s.executeUpdate(sql);	
						addproductShowliastData();
						 addSourceShowliastData();
						 resetafteraddsource();
				
				
				
				}
		
		   
	}
	   }catch(Exception e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }
	   
   }
   public void Sourceadd() {
	   String querry = "INSERT INTO distributeur (nom,prenom,willaya) VALUES (?,?,?)";
	   con = JDBC.getcon();
	   try {
		   if(Dis_name.getText().isEmpty()||Dis_firstname.getText().isEmpty()||Dis_willaya.getText().isEmpty()) {
	Alert alert2 = new Alert(AlertType.ERROR);
				
				alert2.setTitle("ERROR");
				alert2.setHeaderText("Blank Fields");
				alert2.setContentText("please Fill all the Texts Feilds");
				alert2.show();
			   
		   }else {
		   prepare = con.prepareStatement(querry);
		   prepare.setString(1, Dis_name.getText());
		   prepare.setString(2, Dis_firstname.getText());
		   prepare.setString(3, Dis_willaya.getText());
		 prepare.executeUpdate();
		 addSourceShowliastData();
		 resetafteraddsource();}
	   }catch(Exception e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
		   e.printStackTrace();
	   }
   }
   public void resetafteraddsource() {
	   Dis_name.setText("");
	   Dis_firstname.setText("");
	   Dis_willaya.setText("");
	   
   }
   private List<String>list3=new ArrayList<>();
    public ObservableList<SourcesData> addSourcesData(){
    	list3=new ArrayList<>();
    	ObservableList<SourcesData> data = FXCollections.observableArrayList();
    	String sql="SELECT * FROM distributeur";
    	con = JDBC.getcon();
    	try {
    		prepare = con.prepareStatement(sql);
    		res =prepare.executeQuery();
    		SourcesData source ;
    		while(res.next()) {
    			list3.add(res.getString("nom"));
    			source=new SourcesData(res.getInt("DID"),res.getString("nom"),res.getString("prenom"),res.getString("willaya"));
    			data.add(source);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return data;
    	
    }
    private void Sourcechoisebox() {
    	addSourcesData();
  	  ObservableList<String> addtypelist2= FXCollections.observableArrayList(list3);
  	pro_source.setItems(addtypelist2);
      }
    
    private ObservableList<SourcesData> addsourcelist;
    public void addSourceShowliastData() {
    	addsourcelist= addSourcesData();
    	Dis_col_did.setCellValueFactory(new PropertyValueFactory<>("Sid"));
    	Dis_col_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	Dis_col_lastname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
    	Dis_col_wilaya.setCellValueFactory(new PropertyValueFactory<>("Willaya"));
    	dtableview.setItems(addsourcelist);
    	
    }
    public void addEmployeeSelect() {
    	SourcesData sd = dtableview.getSelectionModel().getSelectedItem();
    	int num = dtableview.getSelectionModel().getSelectedIndex();
    	if(num-1<-1) {
    		return ;
    	}
    	Dis_did.setText(sd.getSid().toString());
    	Dis_name.setText(sd.getName());
    	Dis_firstname.setText(sd.getLastname());
    	Dis_willaya.setText(sd.getWillaya());
    	
    		
    }
    public void displayusername() {
    	username.setText("Welcome "+GetData.username);
    	
    }
    public void Switchpages( ActionEvent e) throws SQLException {
    	if(e.getSource()==dashboardbtn) {
    		
    		products.setVisible(false);
    		types.setVisible(false);
    		catg.setVisible(false);
    		sources.setVisible(false);
    		dashboard.setVisible(true);
    		dashboardbtn.setStyle("-fx-background-color:#d9fffe;");
    		productbtn.setStyle("-fx-background-color:#fff;");
    		catgoriesbtn.setStyle("-fx-background-color:#fff;");
    		sourcebtn.setStyle("-fx-background-color:#fff;");
    		typebtn.setStyle("-fx-background-color:#fff;");
    
    		


    	}else if(e.getSource()==productbtn) {
    	
    		types.setVisible(false);
    		catg.setVisible(false);
    		sources.setVisible(false);
    		dashboard.setVisible(false);
    		products.setVisible(true);
    		
    		productbtn.setStyle("-fx-background-color:#d9fffe;");
    		dashboardbtn.setStyle("-fx-background-color:#fff;");
    		
    		catgoriesbtn.setStyle("-fx-background-color:#fff;");
    		sourcebtn.setStyle("-fx-background-color:#fff;");
    		typebtn.setStyle("-fx-background-color:#fff;");
    		typechoisebox();
    		catgchoisebox();
    		Sourcechoisebox();
    		resetafteraddproduct();
    	}else if (e.getSource()==catgoriesbtn) {
    		types.setVisible(false);
    		sources.setVisible(false);
    		dashboard.setVisible(false);
    		products.setVisible(false);
    		catg.setVisible(true);
    		
    		catgoriesbtn.setStyle("-fx-background-color:#d9fffe;");
    		productbtn.setStyle("-fx-background-color:#fff;");
    		dashboardbtn.setStyle("-fx-background-color:#fff;");
    		
    	
    		sourcebtn.setStyle("-fx-background-color:#fff;");
    		typebtn.setStyle("-fx-background-color:#fff;");
    		resetafteraddcatg();
    		
    	}else if(e.getSource()==sourcebtn) {
    		types.setVisible(false);
    		
    		dashboard.setVisible(false);
    		products.setVisible(false);
    		catg.setVisible(false);
    		sources.setVisible(true);
    		sourcebtn.setStyle("-fx-background-color:#d9fffe;");
    		productbtn.setStyle("-fx-background-color:#fff;");
    		dashboardbtn.setStyle("-fx-background-color:#fff;");
    		
    		catgoriesbtn.setStyle("-fx-background-color:#fff;");
    		
    		typebtn.setStyle("-fx-background-color:#fff;");
    		resetafteraddsource();
    	
    		
    	}
    	else if(e.getSource()==typebtn) {
    		
    		
    		dashboard.setVisible(false);
    		products.setVisible(false);
    		catg.setVisible(false);
    		sources.setVisible(false);
    		types.setVisible(true);
    		typebtn.setStyle("-fx-background-color:#d9fffe;");
    		productbtn.setStyle("-fx-background-color:#fff;");
    		dashboardbtn.setStyle("-fx-background-color:#fff;");
    		
    		catgoriesbtn.setStyle("-fx-background-color:#fff;");
    		sourcebtn.setStyle("-fx-background-color:#fff;");
    		
    		resetafteraddtype();
    	}
    	
    	
    }
    public void logout() {
Alert alert = new Alert(AlertType.CONFIRMATION);
    	
		alert.setTitle("logout");
		alert.setHeaderText("you are going to logout");
		alert.setContentText("do you want to continue?");
		if(alert.showAndWait().get()== ButtonType.OK) {
		try {
		
			Parent root = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			root.setOnMousePressed((MouseEvent e)->{
				x= e.getSceneX();
				y=e.getSceneX();
			});
			root.setOnMouseDragged((MouseEvent e)->{
				stage.setX(e.getScreenX()-x);
				stage.setY(e.getScreenY()-y);
				stage.setOpacity(.9);
			});
			root.setOnMouseReleased((MouseEvent e)->{
		
				stage.setOpacity(1);
			});
			
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setScene(scene);
			stage.show();
			logout.getScene().getWindow().hide();
		} catch (IOException e) {
	Alert alert2 = new Alert(AlertType.ERROR);
			
			alert2.setTitle("ERROR");
			alert2.setHeaderText("Error while adding");
			alert2.setContentText("please verify all the text fields "+e );
			alert2.show();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    }
    public void close(ActionEvent e) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	
		alert.setTitle("Closing");
		alert.setHeaderText("you are going to close the system");
		alert.setContentText("do you want to continue?");
		if(alert.showAndWait().get()== ButtonType.OK) {
		System.exit(0);
	}
    }
    private ObservableList<ProductData> product;
   private  int sum =0,sum2=0;
    public int  getQteTotal() throws SQLException {
    	sum=0;
    	sum2=0;
    	try {
			product= addProductData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	product.forEach(i->{
    		
    		sum+=i.getQte();
    		sum2+=i.getPrice();
    		
    	});
    	qtetotale.setText(Integer.toString(sum));
    	moneytotal.setText(Integer.toString(sum2));
    	
    	return sum;
    	
    }
public void getpiechartdata() {
	try {
		product= addProductData();
	
	ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
	int QteTotale;
		QteTotale = getQteTotal();
product.forEach(i->{
	
	
	int Qtep=(i.getQte()*100)/	QteTotale;
 data.add(new PieChart.Data(i.getName(),Qtep));
	
});
	pie.setData(data);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
		displayusername();
		if(GetData.role==1016) {
			
		}else if(GetData.role==1010) {
			catgoriesbtn.setVisible(false);
			typebtn.setVisible(false);
			sourcebtn.setVisible(false);
			pro_confirm.setVisible(false);
			pro_delete.setVisible(false);
			
		}
		addSourceShowliastData();
		addcatgShowliastData();
		
			addproductShowliastData();
	
		addtypeShowliastData();
		typechoisebox();
		catgchoisebox();
		Sourcechoisebox();
		getpiechartdata();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}


}
