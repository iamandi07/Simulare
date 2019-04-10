package UI;

import Service.AgentService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgentAddController {
    public TextField txtID;
    public TextField txtName;
    public TextField txtDate;
    public TextField txtMinute;
    public TextField txtStarth;
    public Button btnAdd;
    public Button btnCancel;

    private AgentService agentService;

    public void setService(AgentService agentService) {
        this.agentService = agentService;
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnAddClick(ActionEvent actionEvent) {

        try {
            String id = txtID.getText();
            String name = txtName.getText();
            String date = txtDate.getText();
            int minute = Integer.parseInt(txtMinute.getText());
            String starth = txtStarth.getText();

            agentService.AddAndUpdate(id, name, date, minute, starth);

            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }
}
