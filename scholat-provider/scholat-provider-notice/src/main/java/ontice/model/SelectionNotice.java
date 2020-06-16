package ontice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionNotice {

    private int acceptId;

    private String msg;

    private String agreeUrl;

    private String refuseUrl;





}
