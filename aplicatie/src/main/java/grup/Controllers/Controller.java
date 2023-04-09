package grup.Controllers;

import grup.Client.ITripClient;
import grup.Service.Service;

public interface Controller {
    void setService(Service srv);

    void setClient(ITripClient client);
}
