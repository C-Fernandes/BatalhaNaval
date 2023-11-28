module br.com.imd {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.com.imd to javafx.fxml;
    opens br.com.imd.Controller to javafx.fxml;

    exports br.com.imd;
}
