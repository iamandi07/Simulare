package UI;

import Domain.Agent;
import Service.AgentService;

import java.util.Scanner;

public class Console {

    private AgentService agentService;

    private Scanner scanner;

    public Console(AgentService agentService) {
        this.agentService = agentService;

        this.scanner = new Scanner(System.in);
    }

    private void showMenu() {
        System.out.println("1. Agent CRUD");
        System.out.println("x. Exit");
    }

    public void run() {
        while (true) {
            showMenu();

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    runAgentCrud();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void runAgentCrud() {
        while (true) {
            System.out.println("1. Add or update an event");
            System.out.println("2. Remove an event");
            System.out.println("3. View all events");
            System.out.println("4. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddAndUpdateEvent();
                    break;
                case "2":
                    handleRemoveEvent();
                    break;
                case "3":
                    handleViewEvents();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewEvents() {
        for (Agent agent : agentService.getAll()) {
            System.out.println(agent);
        }
    }

    private void handleRemoveEvent() {
        try {
            System.out.print("Enter the id to remove:");
            String id = scanner.nextLine();
            agentService.Remove(id);

            System.out.println("Event removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddAndUpdateEvent() {
        try {
            System.out.print("Enter id: ");
            String id = scanner.nextLine();
            System.out.print("Enter event name: ");
            String name = scanner.nextLine();
            System.out.print("Enter the date: ");
            String date = scanner.nextLine();
            System.out.print("Enter the event duration: ");
            int minute = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter the starting hour: ");
            String starth = scanner.nextLine();

            Agent agent = agentService.AddAndUpdate(id, name, date, minute, starth);
            System.out.println(String.format("Added event id=%s", agent.getId()));
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }
}
