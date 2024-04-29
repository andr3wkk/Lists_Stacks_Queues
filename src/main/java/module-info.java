module com.example.lists_stacks_queues {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lists_stacks_queues to javafx.fxml;
    exports com.example.lists_stacks_queues;
}