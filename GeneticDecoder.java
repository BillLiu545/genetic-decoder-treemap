import javafx.application.Application;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.Alert.*;
import java.util.*;

public class GeneticDecoder extends Application
{
    // Initialize our gene
    private Gene gene = new Gene();
    public void start(Stage mainStage)
    {
        // Set the main layout and scene
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root, 600,400);
        mainStage.setScene(mainScene);
        VBox mainLayout = new VBox();
        root.setCenter(mainLayout);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setSpacing(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainStage.setTitle("Genetic TreeMap Decoder App");
        
        // Add a title header
        Label titleLabel = new Label("Genetic TreeMap Decoder");
        titleLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        titleLabel.setTextFill(Color.FUCHSIA);
        titleLabel.setBackground(new Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
        mainLayout.getChildren().add(titleLabel);
        
        // Box to show the entire gene
        HBox geneShowBox = new HBox();
        geneShowBox.setStyle("-fx-font-size: 18px;");
        Label boldLabel = new Label ("Gene Shown: ");
        boldLabel.setStyle("-fx-font-weight: bold;");
        Label geneLabel = new Label(gene.fullGene());
        geneShowBox.setAlignment(Pos.valueOf("CENTER"));
        geneShowBox.getChildren().addAll(boldLabel, geneLabel);
        mainLayout.getChildren().add(geneShowBox);
        
        // HBox for buttons to add/remove chemicals
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-font-size: 18px;");
        
        // Add chemical
        Button addButton = new Button("Add New Chemical");
        addButton.setOnAction((event->
        {
            String added = gene.add_input();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Message");
            alert.setContentText(added);
            alert.showAndWait();
            geneLabel.setText(gene.fullGene());
        }));
        
        // Remove chemical
        Button removeButton = new Button("Remove Chemical");
        removeButton.setOnAction((event->
        {
            String removed = gene.removeChem();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Message");
            alert.setContentText(removed);
            alert.showAndWait();
            geneLabel.setText(gene.fullGene());
        }));
        
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(addButton, removeButton);
        mainLayout.getChildren().add(buttonBox);
        
        // Top menu for gene functions
        MenuBar topMenu = new MenuBar();
        topMenu.setStyle("-fx-font-size: 15px;");
        root.setTop(topMenu);
        
        // File menu
        Menu fileMenu = new Menu("File");
        topMenu.getMenus().add(fileMenu);
        // Menu item - manual mutation
        MenuItem mutateItem = new MenuItem("Mutate");
        mutateItem.setOnAction((event->{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Enter Node Number");
            dialog.setHeaderText("Enter Node # for Mutation: ");
            dialog.setContentText("Node #: ");
            Optional<String> optional = dialog.showAndWait();
            int node_num;
            if (optional.isPresent())
            {
                try
                {
                    node_num = Integer.parseInt(optional.get());
                }
                catch (NumberFormatException err)
                {
                    node_num = 0;
                }
            }
            else
            {
                node_num = 0;
            }
            String mutated = gene.mutate_input(node_num);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Message");
            alert.setContentText(mutated);
            alert.showAndWait();
            geneLabel.setText(gene.fullGene());
        }));
        // Menu item - random mutation
        MenuItem randMutateItem = new MenuItem("Random Mutate");
        randMutateItem.setOnAction((event->{
            String mutated = gene.mutate_rand();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Message");
            alert.setContentText(mutated);
            alert.showAndWait();
            geneLabel.setText(gene.fullGene());
        }));
        // Menu item - random mutation at all positions
        MenuItem allMutateItem = new MenuItem("Mutate All");
        allMutateItem.setOnAction((event->{
            String mutated = gene.mutate_all();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Message");
            alert.setContentText(mutated);
            alert.showAndWait();
            geneLabel.setText(gene.fullGene());
        }));
        // Menu Item - all chemical compositions
        MenuItem chemCompItem = new MenuItem("Chemical Composition");
        chemCompItem.setOnAction((event->{
            String comp = gene.allChemBases();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Chemical Compositions");
            alert.setHeaderText("Current Chemical Compositions");
            alert.setContentText(comp);
            alert.showAndWait();
        }));
        // Menu Item - close
        MenuItem closeItem = new MenuItem("Exit");
        closeItem.setOnAction((e->{
            mainStage.close();
        }));
        fileMenu.getItems().addAll(mutateItem, randMutateItem, allMutateItem, chemCompItem, closeItem);
        
        mainStage.show();
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
