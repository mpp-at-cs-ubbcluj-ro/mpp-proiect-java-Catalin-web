module grup.aplicatie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires java.net.http;


    opens grup.aplicatie to javafx.fxml;
    exports grup.aplicatie;
    exports grup.Controllers;
    opens grup.Controllers to javafx.fxml;
}