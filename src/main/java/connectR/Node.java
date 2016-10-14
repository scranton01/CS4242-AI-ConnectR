package connectR;

import lombok.Data;

import java.util.List;

/**
 * Created by n_jun on 10/13/2016.
 */
@Data
public class Node {
    Board parent;
    List<Board> children;
}
