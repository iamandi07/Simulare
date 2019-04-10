import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.AgentService;
import UI.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ManagerWindow.fxml"));
        Parent root = fxmlLoader.load();

        IValidator<Agent> agentValidator = new AgentValidator();

        IRepository<Agent> agentRepository = new InMemoryRepository<>(agentValidator);

        AgentService agentService = new AgentService(agentRepository);
        agentService.AddAndUpdate("1", "Marriage","04.07.2019", 10, "15:00" );
        agentService.AddAndUpdate("2","Bachelor","07.04.2016", 100,  "15:30" );



        Controller managerController = fxmlLoader.getController();
        managerController.setServices(agentService);

        primaryStage.setTitle("Command manager");
        primaryStage.setScene(new Scene(root, 200, 100));
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }
}