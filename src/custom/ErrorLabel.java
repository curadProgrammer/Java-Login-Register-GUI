package custom;

import javax.swing.*;
import java.awt.*;

public class ErrorLabel extends JLabel {
    public ErrorLabel(String errorText){
        super(errorText);
        setForeground(Color.RED);
        setVisible(false);
    }
}
