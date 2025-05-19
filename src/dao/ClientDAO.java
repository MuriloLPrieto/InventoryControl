package dao;

import java.util.List;

import model.Clients;

public interface ClientDAO {

    Clients insertClient(Clients client);

    void updateClientName(Clients client);

    void updateClientEmail(Clients client);

    void softDeleteClient(int id);

    Clients searchByID(int id);

    List<Clients> listAll();
}
