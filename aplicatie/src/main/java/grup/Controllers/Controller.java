package grup.Controllers;

import grup.Client.ITripClient;

import java.io.IOException;

public interface Controller {
    void setClient(ITripClient client) throws Exception;
}
