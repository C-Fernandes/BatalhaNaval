package TratadorEvento;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TratadorEvento  implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent arg0) {
       System.out.println("Teste de evento tratado por classe externa"); }
    
}
