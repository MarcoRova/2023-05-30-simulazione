package it.polito.tdp.gosales;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.gosales.model.ArcoStampa;
import it.polito.tdp.gosales.model.Model;
import it.polito.tdp.gosales.model.Retailers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAnalizzaComponente;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnSimula;

    @FXML
    private ComboBox<Integer> cmbAnno;

    @FXML
    private ComboBox<String> cmbNazione;

    @FXML
    private ComboBox<?> cmbProdotto;

    @FXML
    private ComboBox<Retailers> cmbRivenditore;

    @FXML
    private TextArea txtArchi;

    @FXML
    private TextField txtN;

    @FXML
    private TextField txtNProdotti;

    @FXML
    private TextField txtQ;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextArea txtVertici;

    @FXML
    void doAnalizzaComponente(ActionEvent event) {
    	
    	Retailers r = this.cmbRivenditore.getValue();
    	
    	if(r == null) {
    		this.txtResult.appendText("Selezionare un rivenditore per continuare.");
    		return;
    	}
    	
    	this.txtResult.appendText("\n\n"+this.model.calcolaComponente(r));
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	this.txtResult.clear();
    	
    	Integer anno = this.cmbAnno.getValue();
    	
    	String country = this.cmbNazione.getValue();
    	
    	if(anno == null || country == null) {
    		this.txtResult.appendText("Selezionare un anno e una nazione per continuare.");
    		return;
    	}
    	
    	Integer x = 0;
    	try {
    	    x = Integer.parseInt(this.txtNProdotti.getText().strip());
    	} catch (NumberFormatException e) {
    	    txtResult.setText("Inserisci un valore numerico per i prodotti.");
    	    return;
    	}
    	if (x <= 0) { 
    	    txtResult.setText("Inserisci un numero positivo.");
    	    return;
    	}
    	
    	this.model.creaGrafo(country, anno, x);
    	
    	this.txtResult.appendText(this.model.infoGrafo());
    	
    	List<Retailers> ret = this.model.getVertici();
    	
    	Collections.sort(ret);
    	
    	for(Retailers r : ret) {
    		this.txtVertici.appendText("\n"+r);
    	}
    	
    	
    	for(ArcoStampa a : this.model.getArchi()) {
    		this.txtArchi.appendText("\n"+a);
    	}
    	
    	this.cmbRivenditore.getItems().addAll(this.model.getVertici());
    	
    	this.cmbRivenditore.setDisable(false);
    	
    	this.btnAnalizzaComponente.setDisable(false);
    	
    }

    @FXML
    void doSimulazione(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnAnalizzaComponente != null : "fx:id=\"btnAnalizzaComponente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbNazione != null : "fx:id=\"cmbNazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProdotto != null : "fx:id=\"cmbProdotto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbRivenditore != null : "fx:id=\"cmbRivenditore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArchi != null : "fx:id=\"txtArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNProdotti != null : "fx:id=\"txtNProdotti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtQ != null : "fx:id=\"txtQ\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVertici != null : "fx:id=\"txtVertici\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	for(int a = 2015; a<=2018; a++) {
    		this.cmbAnno.getItems().add(a);
    	}
    	this.cmbNazione.getItems().addAll(this.model.getCountry());
    }

}
