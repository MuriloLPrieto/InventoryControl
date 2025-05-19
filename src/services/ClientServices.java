package services;

import dao.ClientDAO;
import dao.ClientDAOImpl;
import model.Clients;

import java.util.List;

public class ClientServices {
    private ClientDAO dao = new ClientDAOImpl();


    public Clients createClient(Clients clients) {
        if (clients.getName() == null || clients.getName().isBlank()) {
            throw new IllegalArgumentException("Error! Name cannot be null or empty!");
        }

        if (clients.getEmail() == null || clients.getEmail().isBlank()) {
            throw new IllegalArgumentException("Error! Email cannot be null or empty!");
        }

        String emailRegex = "^[\\w\\.-]+@[\\w\\.-]+\\.[A-Za-z]{2,}$";

        if (!clients.getEmail().matches(emailRegex)) {
            throw new IllegalArgumentException("Error! Invalid email format, expected: example@email.com!");
        }

        return dao.insertClient(clients);
    }

    public void softDelete(int id){
        Clients client = dao.searchByID(id);

        if (client == null){
            throw new IllegalArgumentException("Error! User not found!");
        } else if (!client.isActive()) {
            throw new IllegalArgumentException("Error! This user isn't available!");
        }
        dao.softDeleteClient(id);
    }

    public Clients listById(int id) {
        return dao.searchByID(id);
    }

    public List<Clients> listAllClients() {
        return dao.listAll();
    }

    public void updateName(Clients client) {
        if (client.getName() == null || client.getName().isBlank()) {
            throw new IllegalArgumentException("Error! Name cannot be null or empty!");
        }
        dao.updateClientName(client);
    }

    public void updateEmail(Clients client) {
        if (client.getEmail() == null || client.getEmail().isBlank()) {
            throw new IllegalArgumentException("Error! Email cannot be null or empty!");
        }
        dao.updateClientEmail(client);
    }
}
