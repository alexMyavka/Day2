package sample.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public TableView mainTable;

    ObservableList<Food> foodList = FXCollections.observableArrayList();
    FoodModel foodModel = new FoodModel();
     @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainTable.setItems(foodList);
         foodModel.addDataChangedListener(foods -> mainTable.setItems(FXCollections.observableArrayList(foods)));
         foodModel.load();
    }

    public void onAddClick() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FoodForm.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        FoodFormController controller = loader.getController();
        controller.foodModel = foodModel;
        stage.showAndWait();
    }
    public void onEditClick() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FoodForm.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        FoodFormController controller = loader.getController();
        controller.setFood((Food) this.mainTable.getSelectionModel().getSelectedItem());
        controller.foodModel = foodModel;
        stage.showAndWait();
    }

    public void onDeleteClick() {
        Food food = (Food) this.mainTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(String.format("Точно удалить %s?", food.getTitle()));
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            foodModel.delete(food.id);
        }
    }
}