package application.channel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.Resource;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class Channel extends VBox implements Initializable {

    @Autowired
    private ChannelModel model;

    @FXML
    private HBox channelsHBox;

    public Channel() {
//        if (model == null) {
//            model = new ChannelModel();
//        }
        FXMLLoader loader = new FXMLLoader(Resource.getFXML("channel.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model.getChannelInfos().stream().forEach(info -> addChannelCell(info));
    }

    private void addChannelCell(ChannelInfo info) {
        channelsHBox.getChildren().add(new ChannelCell(this, info));
    }

    @FXML
    protected void saveChannelInfos() {
        model.saveInfos();
    }

    @FXML
    protected void newChannelCell() {
        ChannelInfo info = new ChannelInfo();
        model.addChannelInfo(info);
        addChannelCell(info);
    }

    public void removeChannelCell(ChannelCell cell) {
        model.removeChannelInfo(cell.getChannelInfo());
        channelsHBox.getChildren().remove(cell);
    }

}
