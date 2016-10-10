package connectR;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 10/10/2016.
 */
@Data
public class Board {
    List<List<String>> board;

    Board(){
        board = new ArrayList<>();
    }
    
}
