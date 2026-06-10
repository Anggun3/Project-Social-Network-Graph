import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static SocialNetwork sn = new SocialNetwork();

    public static void main(String[] args) {
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║         SOCIAL NETWORK GRAPH SYSTEM              ║");
        System.out.println("  ║      Struktur Data - Informatika UMM             ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println("\n  [*] Inisialisasi Graph: Adjacency List Kosong...");

        loadSampleData();

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("  Pilihan: ");
            String input = sc.nextLine().trim();
            System.out.println();

            switch (input) {
                case "1":
                    menuAddUser();
                    break;
                case "2":
                    menuAddFriendship();
                    break;
                case "3":
                    menuMutualFriends();
                    break;
                case "4":
                    menuRekomendasi();
                    break;
                case "5":
                    menuConnectionPath();
                    break;
                case "6":
                    sn.graphVisualization();
                    break;
                case "7":
                    sn.displayAllUsers();
                    break;
                case "0":
                    System.out.println("  Terima kasih! Program selesai.");
                    running = false;
                    break;
                default:
                    System.out.println("  [!] Pilihan tidak valid.");
            }
        }
        sc.close();
    }

    // ==================== MENU ====================
    static void printMenu() {
        System.out.println("\n  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║                   MENU UTAMA                     ║");
        System.out.println("  ╠══════════════════════════════════════════════════╣");
        System.out.println("  ║  1. Add User                                     ║");
        System.out.println("  ║  2. Add Friendship                               ║");
        System.out.println("  ║  3. Find Mutual Friends                          ║");
        System.out.println("  ║  4. Friend Recommendation                        ║");
        System.out.println("  ║  5. Connection Path + Shortest Path (BFS)        ║");
        System.out.println("  ║  6. Graph Visualization          [BONUS]         ║");
        System.out.println("  ║  7. Tampilkan Semua User                         ║");
        System.out.println("  ║  0. Exit                                         ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
    }

    // ==================== HANDLER MENU ====================
    static void menuAddUser() {
        System.out.print("  Input ID User : "); String id = sc.nextLine().trim();
        System.out.print("  Input Nama    : "); String name = sc.nextLine().trim();
        sn.addUser(id, name);
    }

    static void menuAddFriendship() {
        System.out.print("  Input ID User A : "); String idA = sc.nextLine().trim();
        System.out.print("  Input ID User B : "); String idB = sc.nextLine().trim();
        sn.addFriendship(idA, idB);
    }

    static void menuMutualFriends() {
        System.out.print("  Input ID User A : "); String idA = sc.nextLine().trim();
        System.out.print("  Input ID User B : "); String idB = sc.nextLine().trim();
        sn.findMutualFriends(idA, idB);
    }

    static void menuRekomendasi() {
        System.out.print("  Input ID User : "); String id = sc.nextLine().trim();
        sn.friendRecommendations(id);
    }

    static void menuConnectionPath() {
        System.out.print("  Input ID User Awal   : "); String start = sc.nextLine().trim();
        System.out.print("  Input ID User Tujuan : "); String end = sc.nextLine().trim();
        sn.connectionPath(start, end);
    }

    // ==================== SAMPLE DATA ====================
    static void loadSampleData() {
        System.out.println("\n  [*] Memuat sample data...\n");

        sn.addUser("U001", "Fira");
        sn.addUser("U002", "Budi");
        sn.addUser("U003", "Cika");
        sn.addUser("U004", "Dani");
        sn.addUser("U005", "Eva");
        sn.addUser("U006", "Fandi");
        sn.addUser("U007", "Gita");

        System.out.println();

        sn.addFriendship("U001", "U002"); // Fira  - Budi
        sn.addFriendship("U001", "U003"); // Fira  - Cika
        sn.addFriendship("U002", "U004"); // Budi  - Dani
        sn.addFriendship("U002", "U005"); // Budi  - Eva
        sn.addFriendship("U003", "U005"); // Cika  - Eva
        sn.addFriendship("U003", "U006"); // Cika  - Fandi
        sn.addFriendship("U004", "U007"); // Dani  - Gita
        sn.addFriendship("U005", "U007"); // Eva   - Gita

        System.out.println("\n  [*] Sample data berhasil dimuat!");
    }
}
