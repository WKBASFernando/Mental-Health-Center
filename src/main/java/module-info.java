module com.ijse.orm.mentalhealthcenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires static lombok;
    requires java.naming;

    opens com.ijse.orm.mentalhealthcenter.entity to org.hibernate.orm.core;
    opens com.ijse.orm.mentalhealthcenter.config to jakarta.persistence;

    opens com.ijse.orm.mentalhealthcenter.controller to javafx.fxml;
    exports com.ijse.orm.mentalhealthcenter;
}