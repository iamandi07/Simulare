package UI;

import Domain.Agent;
import Service.AgentService;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {

    public TableView tableViewAgent;
    public TableColumn tableColumnId;
    public TableColumn tableColumnName;
    public TableColumn tableColumnDate;
    public TableColumn tableColumnMinute;
    public TableColumn tableColumnStarth;
    public Button btnAgentAdd;
    public Button btnAgentDelete;

    private AgentService agentService;

    private ObservableList<Agent> agents = FXCollections.observableArrayList();

    public void setServices(AgentService agentService) {
        this.agentService = agentService;
    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            tableColumnMinute.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            agents.addAll(agentService.getAll());
            tableViewAgent.setItems(agents);
        });
    }

    public void editAgentName(TableColumn.CellEditEvent cellEditEvent) {
        Agent editedAgent = (Agent)cellEditEvent.getRowValue();
        try {
            String newName = (String)cellEditEvent.getNewValue();
            agentService.AddAndUpdate(editedAgent.getId(), newName, editedAgent.getDate(), editedAgent.getMinute(), editedAgent.getStarth());
            editedAgent.setName(newName);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
        tableViewAgent.refresh();
    }

    public void editAgentMinutes(TableColumn.CellEditEvent cellEditEvent) {
        Agent editedAgent = (Agent)cellEditEvent.getRowValue();
        try {
            int newMinutes = (Integer) cellEditEvent.getNewValue();
            agentService.AddAndUpdate(editedAgent.getId(), editedAgent.getName(), editedAgent.getDate(), newMinutes, editedAgent.getStarth());
            editedAgent.setMinute(newMinutes);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
        tableViewAgent.refresh();
    }

    public void btnAgentAddClick(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("agentAdd.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 200);
            Stage stage = new Stage();
            stage.setTitle("Cake add");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            AgentAddController controller =  fxmlLoader.getController();
            controller.setService(agentService);
            stage.showAndWait();
            agents.clear();
            agents.addAll(agentService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Cake add.", e);
        }
    }

    public void btnAgentDeleteClick(ActionEvent actionEvent) {
        Agent selected = (Agent)tableViewAgent.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                agentService.Remove(selected.getId());
                agents.clear();
                agents.addAll(agentService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }
}
